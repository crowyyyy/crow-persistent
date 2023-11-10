package org.crow.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {

    void rollback() throws SQLException;

    void commit() throws SQLException;

    Connection getConnection() throws SQLException;
}
