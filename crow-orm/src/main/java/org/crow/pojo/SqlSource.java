package org.crow.pojo;

import org.crow.mapping.BoundSql;

/**
 * 用于存储crud标签的script
 */
public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
