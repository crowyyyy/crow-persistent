package org.crow.transaction;

import org.crow.executor.BaseExecutor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTransaction extends BaseTransaction {


    public JDBCTransaction(boolean autocommit, DataSource dataSource) {
        super(autocommit, dataSource);
    }

    @Override
    public void rollback() throws SQLException {
        if(connection!=null){
            connection.rollback();
        }
    }

    @Override
    public void commit() throws SQLException {
        if(connection!=null){
            connection.commit();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        if(connection.getAutoCommit()!=autocommit){
            connection.setAutoCommit(autocommit);
        }
        return connection;
    }
}
