package org.crow.factory;

import org.crow.executor.ExecutorFactory;
import org.crow.executor.SimpleExecutor;
import org.crow.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession()  {
        return openSqlSession(false);
    }

    @Override
    public SqlSession openSqlSession(boolean autoCommit) {
        ExecutorFactory executorFactory = new ExecutorFactory(configuration);
        SimpleExecutor executor = executorFactory.newSimpleExecutor(autoCommit);
        return new DefaultSqlSession(configuration,executor,autoCommit);
    }
}
