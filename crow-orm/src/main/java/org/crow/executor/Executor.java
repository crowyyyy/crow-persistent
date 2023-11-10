package org.crow.executor;

import org.crow.handler.StatementHandler;
import org.crow.pojo.Configuration;
import org.crow.pojo.MappedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 事务隔离
 * TODO 缓存隔离
 */
public interface Executor {
    <V> List<V> query(MappedStatement statement, Object param) throws Exception;

    int update(MappedStatement statement, Object parameter) throws Exception;

    Statement prepareStatement(MappedStatement statement, Configuration configuration, Object param, StatementHandler handler) throws Exception;

    void commit() throws SQLException;

    void rollback() throws SQLException;
}
