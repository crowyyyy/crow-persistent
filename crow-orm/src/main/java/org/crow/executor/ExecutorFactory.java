package org.crow.executor;

import org.crow.pojo.Configuration;
import org.crow.transaction.JDBCTransaction;
import org.crow.transaction.Transaction;

public class ExecutorFactory {
    private Configuration configuration;
    public ExecutorFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SimpleExecutor newSimpleExecutor(boolean autoCommit){
        Transaction ts = new JDBCTransaction(autoCommit,configuration.getDataSource());
        return new SimpleExecutor(configuration,ts);
    }
}
