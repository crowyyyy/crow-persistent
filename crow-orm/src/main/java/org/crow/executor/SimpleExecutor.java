package org.crow.executor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.crow.handler.DefaultStatementHandler;
import org.crow.handler.StatementHandler;
import org.crow.mapping.BoundSql;
import org.crow.pojo.Configuration;
import org.crow.pojo.MappedStatement;
import org.crow.pojo.SqlSource;
import org.crow.transaction.Transaction;
import org.crow.utils.ParameterMapping;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor extends BaseExecutor{

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration,transaction);
    }

    @Override
    public <V> List<V> query(MappedStatement statement, Object parameter) throws Exception {
        DefaultStatementHandler handler = new DefaultStatementHandler(configuration,statement,statement.getSqlSource().getBoundSql(parameter));
        PreparedStatement preparedStatement = prepareStatement(statement, configuration, parameter,handler);
        return handler.query(preparedStatement);

    }

    @Override
    public int update(MappedStatement statement, Object parameter) throws Exception {
        DefaultStatementHandler handler = new DefaultStatementHandler(configuration, statement, statement.getSqlSource().getBoundSql(parameter));
        PreparedStatement preparedStatement = prepareStatement(statement, configuration, parameter, handler);
        return handler.update(preparedStatement);
    }

    @Override
    public PreparedStatement prepareStatement(MappedStatement statement, Configuration configuration, Object param, StatementHandler handler) throws Exception {
        PreparedStatement preparedStatement = handler.createStatement(transaction);
        handler.parameterize(preparedStatement);
        return preparedStatement;
    }


}
