package com.ym.ssm.chapter4.datasource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class DbcpDataSourceFactory implements DataSourceFactory {
    private Properties props = null;

    public void setProperties(Properties props) {
        this.props = props;
    }

    public DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataSource;
    }
}