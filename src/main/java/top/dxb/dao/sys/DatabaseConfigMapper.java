package top.dxb.dao.sys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.dxb.entity.sys.DatabaseConfig;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 数据库配置
 * 
 * @author Dongxibao
 * @date 2020-12-24
 */
@Mapper
public interface DatabaseConfigMapper {

    /**
     * 查询数据库配置
     *
     * @param id 数据库配置ID
     * @return 数据库配置
     */
    DatabaseConfig getById(@Param("id") Long id);

    /**
     * 查询数据库配置列表
     *
     * @param genDatabaseConfig 数据库配置
     * @return 数据库配置集合
     */
    List<DatabaseConfig> selectByCondition(DatabaseConfig genDatabaseConfig);

    /**
     * 新增数据库配置
     *
     * @param genDatabaseConfig 数据库配置
     * @return 结果
     */
    int insert(DatabaseConfig genDatabaseConfig);

    /**
     * 修改数据库配置
     *
     * @param genDatabaseConfig 数据库配置
     * @return 结果
     */
    int update(DatabaseConfig genDatabaseConfig);

    /**
     * 删除数据库配置
     *
     * @param id 数据库配置ID
     * @return 结果
     */
    int deleteById(@Param("id") Long id);

    /**
     * 批量删除数据库配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBatchIds(@Param("ids") Collection<? extends Serializable> ids);
}
