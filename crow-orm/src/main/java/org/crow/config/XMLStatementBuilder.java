package org.crow.config;

import org.crow.constants.enums.SqlCommandType;
import org.crow.pojo.Configuration;
import org.crow.pojo.MappedStatement;
import org.crow.pojo.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;

public class XMLStatementBuilder extends BaseBuilder{
    private Node context;
    private String namespace;
    public XMLStatementBuilder(Configuration configuration, Node context,String namespace) {
        super(configuration);
        this.context = context;
        this.namespace = namespace;
    }
    public void parse(){
        Element element = (Element) context;
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(element.getName().toUpperCase());
        String id = element.attributeValue("id");
        String statementId = String.join(".",namespace,id);
        String resultType = element.attributeValue("resultType");
        String parameterType = element.attributeValue("parameterType");
        XMLScriptBuilder xmlScriptBuilder = new XMLScriptBuilder(configuration,context);
        SqlSource sqlSource = xmlScriptBuilder.parse();
        MappedStatement statement = new MappedStatement(resultType,parameterType,sqlSource,sqlCommandType);
        configuration.addMapperStatement(statementId,statement);
    }


}
