package top.dxb.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库配置
 * 
 * @author Dongxibao
 * @date 2020-12-24
 */
@Data
public class DatabaseConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/** 数据库名称 */
	private String name;
	/** 数据库连接地址 */
	private String jdbcUrl;
	/** 数据库密码 */
	private String passwd;
	/** 数据库用户名 */
	private String userName;
	/** 是否使用(1是;0否) */
	private Integer beUse;
	/**  */
	private Date createTime;
	/**  */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBeUse() {
		return beUse;
	}

	public void setBeUse(Integer beUse) {
		this.beUse = beUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "DatabaseConfig{" +
				"id=" + id +
				", name='" + name + '\'' +
				", jdbcUrl='" + jdbcUrl + '\'' +
				", passwd='" + passwd + '\'' +
				", userName='" + userName + '\'' +
				", beUse=" + beUse +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
