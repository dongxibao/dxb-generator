package top.dxb.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.dxb.entity.sys.SysConfig;

import java.util.List;

/**
 * 系统配置
 * 
 * @author Dongxibao
 * @date 2020-12-24
 */
@Mapper
public interface SysConfigMapper {

    /** MyBatisPlus 以下可以删除 */
    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    SysConfig selectById(@Param("id") Long id);

    /**
     * 查询系统配置列表
     *
     * @param sysConfig 系统配置
     * @return 系统配置集合
     */
    List<SysConfig> selectByCondition(SysConfig sysConfig);

    /**
     * 新增系统配置
     *
     * @param sysConfig 系统配置
     * @return 结果
     */
    int insert(SysConfig sysConfig);

    /**
     * 修改系统配置
     *
     * @param sysConfig 系统配置
     * @return 结果
     */
    int update(SysConfig sysConfig);

    /**
     * 删除系统配置
     *
     * @param id 系统配置ID
     * @return 结果
     */
    int deleteById(@Param("id") Long id);

    /**
     * 批量删除系统配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteByIds(@Param("ids") Long[] ids);
}
