package org.crow.pojo;

import com.alibaba.druid.pool.DruidDataSource;
import org.crow.factory.TypeHandlerRegistry;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private DataSource dataSource;
    private TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
    private Map<String, MappedStatement> map = new HashMap<>();



    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMapperStatement(String key, MappedStatement value){
        map.put(key,value);
    }
    public MappedStatement getMapperStatement(String statementId){
        return map.get(statementId);
    }
}
