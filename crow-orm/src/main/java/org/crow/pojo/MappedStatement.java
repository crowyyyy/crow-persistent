package org.crow.pojo;

import org.crow.constants.enums.SqlCommandType;

public class MappedStatement {

    private String resultType;
    private String parameterType;
    private SqlSource sqlSource;
    private SqlCommandType sqlCommandType;

    public MappedStatement() {
    }


    public MappedStatement(String resultType, String parameterType, SqlSource sqlSource, SqlCommandType sqlCommandType) {
        this.resultType = resultType;
        this.parameterType = parameterType;
        this.sqlSource = sqlSource;
        this.sqlCommandType = sqlCommandType;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
}
