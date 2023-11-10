package org.crow.config;

import org.crow.constants.enums.SqlCommandType;
import org.crow.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder extends BaseBuilder{

    public XMLMapperBuilder(Configuration configuration) {
        super(configuration);
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        for (SqlCommandType sqlCommandType : SqlCommandType.values()) {
            String sqlType = sqlCommandType.name().toLowerCase();
            List<Node> selectList = rootElement.selectNodes("//"+sqlType);
            for (Node node : selectList) {
                XMLStatementBuilder statementBuilder = new XMLStatementBuilder(configuration, node,namespace);
                statementBuilder.parse();
            }
        }
    }
}
