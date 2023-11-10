package org.crow.handler;

import org.crow.pojo.MappedStatement;
import org.crow.transaction.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    void parameterize(PreparedStatement preparedStatement) throws Exception;

    PreparedStatement createStatement(Transaction transaction) throws SQLException;

    <E> List<E> query(PreparedStatement statement) throws Exception;

    int update(PreparedStatement statement) throws SQLException;
}
