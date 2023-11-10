package org.crow.config;

import org.crow.pojo.Configuration;

public abstract class BaseBuilder {
    protected final Configuration configuration;
    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


}
