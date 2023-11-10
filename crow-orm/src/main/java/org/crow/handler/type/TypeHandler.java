package org.crow.handler.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TypeHandler<T> {
    void setParameter(Integer idx,PreparedStatement statement, T parameter) throws SQLException;
}
