package org.crow.executor;

import org.crow.pojo.Configuration;
import org.crow.transaction.Transaction;

import java.sql.SQLException;

public abstract class BaseExecutor implements Executor{
    protected final Configuration configuration;
    protected final Transaction transaction;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
    }

    @Override
    public void commit() throws SQLException {
        transaction.commit();
    }

    @Override
    public void rollback() throws SQLException {
        transaction.rollback();
    }
}
