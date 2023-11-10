package org.crow.factory;

import org.crow.Exception.PersistenceException;

import java.util.List;

public interface SqlSession {
    <T> T getOne(String statementId,Object paramter) throws Exception;

    <V> List<V> getList(String statementId,Object paramter) throws Exception;

    int insert(String statementId,Object paramter);

    int delete(String statementId,Object paramter);

    int update(String statementId,Object paramter) throws PersistenceException;

    void commit();

    void rollback();
}
