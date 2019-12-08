package com.ym.ssm.chapter4.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class MyTransactionFactory implements TransactionFactory {
    public void setProperties(Properties properties) {

    }

    public Transaction newTransaction(Connection connection) {
        return new MyTransaction(connection);
    }

    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return new MyTransaction(dataSource,transactionIsolationLevel,b);
    }
}
