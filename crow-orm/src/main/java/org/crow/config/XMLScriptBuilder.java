package org.crow.config;

import org.crow.pojo.Configuration;
import org.crow.pojo.DefaultSqlSource;
import org.crow.pojo.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.Iterator;
import java.util.List;

import static org.dom4j.Node.TEXT_NODE;

public class XMLScriptBuilder extends BaseBuilder{
    private Node context;
    public XMLScriptBuilder(Configuration configuration,Node context) {
        super(configuration);
        this.context = context;
    }
    //解析标签文本
    public SqlSource parse(){
        Element element = (Element) this.context;
        //TODO 静态文本+动态标签的解析
//        List<Element> elements = element.elements();
//        Iterator<Element> iterator = elements.iterator();
//        while (iterator.hasNext()){
//            Element next = iterator.next();
//            if(next.getNodeType()==TEXT_NODE){
//            }
//        }
        String sql = element.getTextTrim();
        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(configuration);
        SqlSource sqlSource = sqlSourceBuilder.parse(sql);
        return sqlSource;
    }


}
