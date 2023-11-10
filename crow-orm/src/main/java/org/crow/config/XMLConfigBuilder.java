package org.crow.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.crow.io.Resources;
import org.crow.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder extends BaseBuilder{

    public XMLConfigBuilder() {
        super(new Configuration());
    }

    public Configuration parse(InputStream inputStream) throws DocumentException {;
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        //解析DataSource
        List<Node> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Node node : list) {
            Element element = (Element) node;
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setDefaultAutoCommit(Boolean.valueOf(properties.getProperty("autoCommit")));
        configuration.setDataSource(dataSource);
        //解析mapper.xml文件
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        List<Node> mapperList = rootElement.selectNodes("//mapper");
        for (Node node : mapperList) {
            Element element = (Element) node;
            String mapperPath = element.attributeValue("resource");
            InputStream mapperInputStream = Resources.getResource(mapperPath);
            xmlMapperBuilder.parse(mapperInputStream);
        }
        return configuration;
    }
}
