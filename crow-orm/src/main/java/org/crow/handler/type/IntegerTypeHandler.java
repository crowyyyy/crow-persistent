package org.crow.handler.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler<Integer>{
    @Override
    public void setParameter(Integer idx,PreparedStatement statement, Integer parameter) throws SQLException {
        statement.setInt(idx,parameter);
    }
}
