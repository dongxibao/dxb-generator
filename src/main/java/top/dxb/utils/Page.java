package top.dxb.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 分页
 *
 * @param <T>
 * @author Leon Wong
 */
public class Page<T> {

    private static final int PAGE_SIZE = 20;

    private static final String PARAM_PAGE_SIZE = "pageSize";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_ORDER_BY = "orderBy";

    private List<T> list;
    /** 每页条数 */
    private Integer pageSize;

    /** 总页数 */
    private int totalPage;

    /** 当前页码 */
    private int currPage;

    @JsonIgnore
    private Integer offSet;

    //总记录数
    private int totalCount;

    /** 标准查询有效， 实例： updateDate desc, name asc */
    private String orderBy = "";

    private String orderByType = "";

    public Page(List<T> result, int totalCount, int pageSize, int currPage) {
        this.list = result;
        if (pageSize != 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = PAGE_SIZE;
        }
        if (currPage != 0) {
            this.currPage = currPage;
        } else {
            this.currPage = 1;
        }
        this.totalCount = this.list.size();
        this.totalCount = totalCount;
        this.totalPage = totalCount / this.pageSize + 1;
        if (this.currPage > this.totalPage) {
            this.currPage = this.totalPage;
        }
    }

    public List<T> getResult() {
        if (this.list.size()-1 >= pageSize) {
            int toIndex = this.pageSize * this.currPage;
            if (toIndex > list.size()) {
                toIndex = list.size();
            }
            return this.list.subList(this.pageSize * (this.currPage - 1), toIndex);
        } else {
            return this.list;
        }

    }


    /**
     * 获取查询排序字符串
     *
     * @return
     */
    public String getOrderBy() {
        // SQL过滤，防止注入
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }

    /**
     * 设置查询排序，标准查询有效， 实例： updateDate desc, name asc
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
