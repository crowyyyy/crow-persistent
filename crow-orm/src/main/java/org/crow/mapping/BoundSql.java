package org.crow.mapping;

import org.crow.utils.ParameterMapping;

import java.util.List;

public class BoundSql {

    private String finalSql;
    private Object parameterObject;
    private List<ParameterMapping> list;

    public BoundSql(String finalSql, Object parameterObject, List<ParameterMapping> list) {
        this.finalSql = finalSql;
        this.parameterObject = parameterObject;
        this.list = list;
    }

    public Object getParameterObject() {
        return parameterObject;
    }

    public void setParameterObject(Object parameterObject) {
        this.parameterObject = parameterObject;
    }

    public String getFinalSql() {
        return finalSql;
    }

    public void setFinalSql(String finalSql) {
        this.finalSql = finalSql;
    }

    public List<ParameterMapping> getList() {
        return list;
    }

    public void setList(List<ParameterMapping> list) {
        this.list = list;
    }
}
