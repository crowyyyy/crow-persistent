package org.crow.config;

import org.crow.pojo.Configuration;
import org.crow.pojo.DefaultSqlSource;
import org.crow.pojo.SqlSource;
import org.crow.utils.GenericTokenParser;
import org.crow.utils.ParameterMappingTokenHandler;


public class SqlSourceBuilder extends BaseBuilder{
    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }
    public SqlSource parse(String originalSql){
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        return new DefaultSqlSource(sql,handler.getParameterMappings());
    }
}
