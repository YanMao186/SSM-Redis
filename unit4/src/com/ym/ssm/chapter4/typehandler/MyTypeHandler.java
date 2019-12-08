package com.ym.ssm.chapter4.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//启用扫描注册的时候需要注解
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler implements TypeHandler<String> {
    Logger logger = Logger.getLogger(MyTypeHandler.class);
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        logger.info("设置string参数【"+s+"】");
        preparedStatement.setString(i,s);
    }

    public String getResult(ResultSet resultSet, String s) throws SQLException {
        String result = resultSet.getString(s);
        logger.info("读取string参数1【"+result+"】");
        return result;
    }

    public String getResult(ResultSet resultSet, int i) throws SQLException {
        String result = resultSet.getString(i);
        logger.info("读取string参数2【"+result+"】");
        return result;
    }

    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        String result = callableStatement.getString(i);
        logger.info("读取string参数3【"+result+"】");
        return result;
    }
}
