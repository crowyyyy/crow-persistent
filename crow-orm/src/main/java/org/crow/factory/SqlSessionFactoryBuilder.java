package org.crow.factory;

import org.crow.config.XMLConfigBuilder;
import org.crow.pojo.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public static SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);
        return new DefaultSqlSessionFactory(configuration);
    }
}
