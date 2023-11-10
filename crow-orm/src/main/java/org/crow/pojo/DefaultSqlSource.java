package org.crow.pojo;

import org.crow.mapping.BoundSql;
import org.crow.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSource implements SqlSource{
    String sql;
    private List<ParameterMapping> parameterMappings;


    public DefaultSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(sql, parameterObject,parameterMappings);
    }
}
