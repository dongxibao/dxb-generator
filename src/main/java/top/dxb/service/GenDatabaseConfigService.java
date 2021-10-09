package top.dxb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.dxb.dao.sys.DatabaseConfigMapper;
import top.dxb.entity.sys.DatabaseConfig;
import top.dxb.utils.PageUtils;
import top.dxb.utils.Query;

import java.util.List;

/**
 * 数据库配置
 *
 * @author Dongxibao
 * @date 2020-12-30
 */
@Service
public class GenDatabaseConfigService {

    @Autowired
    private DatabaseConfigMapper databaseConfigMapper;


    public DatabaseConfig getById(Long id) {
        DatabaseConfig genDatabaseConfig = databaseConfigMapper.getById(id);
        return genDatabaseConfig;
    }

    public PageUtils selectByCondition(Query query) {
        List<DatabaseConfig> list = databaseConfigMapper.selectByCondition(null);
        return new PageUtils(list, list.size(), query.getLimit(), query.getPage());
    }

    @Transactional(rollbackFor = Exception.class)
    public DatabaseConfig save(DatabaseConfig genDatabaseConfig) {
        databaseConfigMapper.insert(genDatabaseConfig);
        return genDatabaseConfig;
    }

    @Transactional(rollbackFor = Exception.class)
    public DatabaseConfig updateById(DatabaseConfig genDatabaseConfig) {
        databaseConfigMapper.update(genDatabaseConfig);
        return genDatabaseConfig;
    }


    public boolean removeById(Long id) {
        databaseConfigMapper.deleteById(id);
        return true;
    }

    public boolean deleteBatchIds(List<Long> ids) {
        databaseConfigMapper.deleteBatchIds(ids);
        return true;
    }
}

