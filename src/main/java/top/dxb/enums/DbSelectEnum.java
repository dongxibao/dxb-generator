package top.dxb.enums;

import lombok.Getter;

/**
 * @ClassName DbSelectEnum
 * @Description 1
 * @Author Dongxibao
 * @Date 2020/12/24
 * @Version 1.0
 */
@Getter
public enum DbSelectEnum {

    /** mysql */
    MYSQL("mysql", "select table_name tableName, engine, table_comment tableComment, create_time createTime from " +
            "information_schema.tables where table_schema = (select database()) and table_comment is not null and " +
            "table_name like concat('%', '{}', '%') and table_name not in ( 'gen_sys_config','gen_database_config') " +
            "order by create_time desc",
            "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = '{}'",
            "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = '{}' and table_schema = (select database()) order by ordinal_position"),

    /** oracle */
    ORACLE("oracle", "SELECT dt.table_name AS \"tableName\", dtc.comments \"tableComment\", uo.created \"createTime\" from user_tables dt, user_tab_comments dtc, user_objects uo where dt.table_name = dtc.table_name and dt.table_name = uo.object_name and uo.object_type='TABLE' and dtc.comments is not null and dt.table_name like concat('%', UPPER('{}')) order by uo.CREATED desc",
            "select dt.table_name \"tableName\",dtc.comments \"tableComment\",dt.last_analyzed \"createTime\" from user_tables dt,user_tab_comments dtc where dt.table_name=dtc.table_name and dt.table_name = UPPER('{}')",
            "select temp.column_name \"columnName\",temp.data_type \"dataType\",temp.comments \"columnComment\",case temp.constraint_type when 'P' then 'PRI' when 'C' then 'UNI' else '' end \"columnKey\",'' \"extra\" from (select col.column_id,col.column_name,col.data_type,colc.comments,uc.constraint_type,row_number() over (partition by col.column_name order by uc.constraint_type desc) as row_flg from user_tab_columns col left join user_col_comments colc on colc.table_name = col.table_name and colc.column_name = col.column_name left join user_cons_columns ucc on ucc.table_name = col.table_name and ucc.column_name = col.column_name left join user_constraints uc on uc.constraint_name = ucc.constraint_name where col.table_name = upper('{}')) temp where temp.row_flg = 1 order by temp.column_id"),

    /** sql server */
    SQLSERVER("sqlserver", "select * from(select cast(so.name as varchar(500)) as tableName,cast(sep.value as varchar(500)) as tableComment,getDate() as createTime from sysobjects so left JOIN sys.extended_properties sep on sep.major_id=so.id and sep.minor_id=0 where (xtype='U' or xtype='v')) t where 1=1 and t.tableComment is not null and t.tableName like '%'+'{}'+'%' order by t.tableName",
            "select * from(select cast(so.name as varchar(500)) as tableName,'mssql' as engine,cast(sep.value as varchar(500)) as tableComment,getDate() as createTime from sysobjects so left JOIN sys.extended_properties sep on sep.major_id=so.id and sep.minor_id=0 where (xtype='U' or xtype='v')) t where t.tableName='{}'",
            "SELECT cast(b.NAME AS VARCHAR(500)) AS columnName,cast(sys.types.NAME AS VARCHAR(500)) AS dataType,cast(c.VALUE AS VARCHAR(500)) AS columnComment,(SELECT CASE count(1) WHEN 1 then 'PRI' ELSE '' END FROM syscolumns,sysobjects,sysindexes,sysindexkeys,systypes WHERE syscolumns.xusertype = systypes.xusertype AND syscolumns.id = object_id(A.NAME) AND sysobjects.xtype = 'PK' AND sysobjects.parent_obj = syscolumns.id AND sysindexes.id = syscolumns.id AND sysobjects.NAME = sysindexes.NAME AND sysindexkeys.id = syscolumns.id AND sysindexkeys.indid = sysindexes.indid AND syscolumns.colid = sysindexkeys.colid AND syscolumns.NAME = B.NAME) as columnKey,'' as extra FROM(select name,object_id from sys.tables UNION all select name,object_id from sys.views) a INNER JOIN sys.COLUMNS b ON b.object_id = a.object_id LEFT JOIN sys.types ON b.user_type_id = sys.types.user_type_id LEFT JOIN sys.extended_properties c ON c.major_id = b.object_id AND c.minor_id = b.column_id WHERE a.NAME = '{}' and sys.types.NAME != 'sysname'"),
    ;

    private String feature;
    private String queryListSql;
    private String queryTableSql;
    private String queryColumnsSql;
    DbSelectEnum(String feature, String queryListSql, String queryTableSql, String queryColumnsSql) {
        this.feature = feature;
        this.queryListSql = queryListSql;
        this.queryTableSql = queryTableSql;
        this.queryColumnsSql = queryColumnsSql;
    }

    private static final String JDBC_URL_PREFIX = "jdbc:";
    public static DbSelectEnum urlOf(String jdbcUrl) {
        String url = jdbcUrl.toLowerCase().trim();
        for (DbSelectEnum dbSelectEnum : values()) {
            if (url.startsWith(JDBC_URL_PREFIX + dbSelectEnum.feature)) {
                return dbSelectEnum;
            }
        }
        return null;
    }
}
