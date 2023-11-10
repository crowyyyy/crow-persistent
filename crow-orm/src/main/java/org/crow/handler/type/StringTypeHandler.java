package org.crow.handler.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StringTypeHandler implements TypeHandler<String>{
    @Override
    public void setParameter(Integer idx, PreparedStatement statement, String parameter) throws SQLException {
        statement.setString(idx,parameter);
    }
}
