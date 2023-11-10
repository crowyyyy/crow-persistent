package org.crow.handler;

import org.crow.factory.TypeHandlerRegistry;
import org.crow.handler.type.TypeHandler;
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

public class DefaultStatementHandler implements StatementHandler{
    private Configuration configuration;
    private MappedStatement mappedStatement;
    private BoundSql boundSql;

    public DefaultStatementHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql) {
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
    }

    @Override
    public void parameterize(PreparedStatement preparedStatement) throws Exception {
        List<ParameterMapping> mappingList = boundSql.getList();
        Object parameterObject = boundSql.getParameterObject();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        TypeHandler handler = typeHandlerRegistry.supportParam(mappedStatement.getParameterType());
        if(handler!=null){
            handler.setParameter(1,preparedStatement,parameterObject);
        }else{
            for (int i = 0; i < mappingList.size(); i++) {
                Class<?> parameterClass = Class.forName(mappedStatement.getParameterType());
                Field field = parameterClass.getDeclaredField(mappingList.get(i).getParameterName());
                field.setAccessible(true);
                preparedStatement.setObject(i+1,field.get(boundSql.getParameterObject()));
            }
        }
    }

    @Override
    public PreparedStatement createStatement(Transaction transaction) throws SQLException {
        Connection connection = transaction.getConnection();
        SqlSource sqlSource = mappedStatement.getSqlSource();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSource.getBoundSql(boundSql.getParameterObject()).getFinalSql());
        return preparedStatement;
    }

    @Override
    public <V> List<V> query(PreparedStatement statement) throws Exception {
        ResultSet resultSet = statement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultClass = Class.forName(resultType);
        ArrayList<V> list = new ArrayList<>();
        while(resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();
            Object o = resultClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                Field field = resultClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(o,value);
            }
            list.add((V)o);
        }
        return list;
    }

    @Override
    public int update(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }


}
