package org.crow.utils;

public class ParameterMapping {
    private String parameterName;

    public ParameterMapping(String content) {
        this.parameterName = content;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
}
