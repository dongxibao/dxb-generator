/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package top.dxb.service;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.dxb.dao.GeneratorDao;
import top.dxb.dao.sys.DatabaseConfigMapper;
import top.dxb.entity.sys.DatabaseConfig;
import top.dxb.enums.DbSelectEnum;
import top.dxb.utils.GenUtils;
import top.dxb.utils.PageUtils;
import top.dxb.utils.Query;
import top.dxb.utils.SqlUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@Service
public class SysGeneratorService {
    @Autowired
    private GeneratorDao generatorDao;
    @Autowired
    private DatabaseConfigMapper databaseConfigMapper;

    DbSelectEnum dbSelectEnum = null;
    DatabaseConfig databaseConfig = null;

    @PostConstruct
    public DbSelectEnum initDbSelectEnum() {
        log.info("initDbSelectEnum初始化执行DbSelectEnum和DatabaseConfig");
        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setBeUse(1);
        List<DatabaseConfig> databaseConfigs = databaseConfigMapper.selectByCondition(databaseConfig);
        if (CollectionUtils.isEmpty(databaseConfigs)) {
            throw new RuntimeException("databaseConfigs查询为空");
        }
        this.databaseConfig = databaseConfigs.get(0);
        DbSelectEnum dbEnum = DbSelectEnum.urlOf(databaseConfigs.get(0).getJdbcUrl().trim());
        this.dbSelectEnum = dbEnum;
        return dbEnum;
    }

    public PageUtils queryList(Query query) {
        initDbSelectEnum();
//		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        String sql = StrUtil.format(dbSelectEnum.getQueryListSql(), query.get("tableName") == null ? "" : query.get("tableName"));
        log.info("queryListSql：{}", sql);
        List<Map<String, Object>> list = SqlUtils.executeQuery(SqlUtils.getConnection(databaseConfig.getJdbcUrl(),
                databaseConfig.getUserName(),
                databaseConfig.getPasswd()),
                sql);
//		List<Map<String, Object>> list = generatorDao.queryList(query);
        return new PageUtils(list, list.size(), query.getLimit(), query.getPage());
    }

    public Map<String, Object> queryTable(String tableName) {
        String sql = StrUtil.format(dbSelectEnum.getQueryTableSql(), tableName);
        log.info("queryTableSql：{}", sql);
        return SqlUtils.executeQuery(SqlUtils.getConnection(databaseConfig.getJdbcUrl(),
                databaseConfig.getUserName(),
                databaseConfig.getPasswd()),
                sql).get(0);
        //		return generatorDao.queryTable(tableName);
    }

    public List<Map<String, Object>> queryColumns(String tableName) {
        String sql = StrUtil.format(dbSelectEnum.getQueryColumnsSql(), tableName);
        log.info("queryColumnsSql：{}", sql);
        return SqlUtils.executeQuery(SqlUtils.getConnection(databaseConfig.getJdbcUrl(),
                databaseConfig.getUserName(),
                databaseConfig.getPasswd()),
                sql);
        //		return generatorDao.queryColumns(tableName);
    }

    public byte[] generatorCode(String[] tableNames) throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            String queryTableSql = StrUtil.format(dbSelectEnum.getQueryTableSql(), tableName);
            log.info("queryTableSql：{}", queryTableSql);
            List<Map<String, Object>> tableList = SqlUtils.executeQuery(SqlUtils.getConnection(databaseConfig.getJdbcUrl(),
                    databaseConfig.getUserName(),
                    databaseConfig.getPasswd()),
                    queryTableSql);
            if (CollectionUtils.isEmpty(tableList)) {
                return null;
            }
            Map<String, Object> table = tableList.get(0);
//			Map<String, String> table = queryTable(tableName);
            //查询列信息
            String queryColumnsSql = StrUtil.format(dbSelectEnum.getQueryColumnsSql(), tableName);
            log.info("queryColumnsSql：{}", queryColumnsSql);
            List<Map<String, Object>> columns = SqlUtils.executeQuery(SqlUtils.getConnection(databaseConfig.getJdbcUrl(),
                    databaseConfig.getUserName(),
                    databaseConfig.getPasswd()),
                    queryColumnsSql);
//			List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
