package org.crow.transaction;


import javax.sql.DataSource;
import java.sql.Connection;

public abstract class BaseTransaction implements Transaction {
    protected Connection connection;
    protected boolean autocommit;
    protected DataSource dataSource;

    public BaseTransaction(boolean autocommit, DataSource dataSource) {
        this.autocommit = autocommit;
        this.dataSource = dataSource;
    }
}
