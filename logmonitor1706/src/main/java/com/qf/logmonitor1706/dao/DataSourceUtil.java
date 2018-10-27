package com.qf.logmonitor1706.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * 数据源接口池
 */
public class DataSourceUtil {
    private static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("logmonitor");
    }

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new ComboPooledDataSource("logmonitor");
        }
        return dataSource;
    }

}
