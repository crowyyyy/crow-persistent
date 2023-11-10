package org.crow.factory;

import org.crow.handler.type.IntegerTypeHandler;
import org.crow.handler.type.StringTypeHandler;
import org.crow.handler.type.TypeHandler;

import java.util.HashMap;
import java.util.Map;

public class TypeHandlerRegistry {
    Map<String, TypeHandler> typeHandlerMap = new HashMap<>();

    public TypeHandlerRegistry() {
        register(Integer.class.getName(),new IntegerTypeHandler());
        register(String.class.getName(),new StringTypeHandler());
    }
    private void register(String className,TypeHandler handler){
        typeHandlerMap.put(className, handler);
    }
    public TypeHandler supportParam(String param){
        return typeHandlerMap.get(param);
    }

}
