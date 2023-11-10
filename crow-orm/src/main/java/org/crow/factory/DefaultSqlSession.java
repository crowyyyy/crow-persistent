package org.crow.factory;

import org.crow.Exception.PersistenceException;
import org.crow.executor.Executor;
import org.crow.pojo.Configuration;
import org.crow.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;
    private Executor executor;
    private boolean autoCommit;
    private boolean dirty;
    public DefaultSqlSession(Configuration configuration,Executor executor,boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.autoCommit = autoCommit;
        this.dirty = false;
    }


    @Override
    public <T> T getOne(String statementId, Object param) throws Exception {
        List<T> list = getList(statementId, param);
        if(list.size()>1){
            throw new IllegalArgumentException();
        }else if(list.size()==1){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public <V> List<V> getList(String statementId, Object param) throws Exception {
        MappedStatement statement = configuration.getMapperStatement(statementId);
        return executor.query(statement, param);
    }

    @Override
    public int insert(String statementId,Object paramter){
        return update(statementId,paramter);
    }

    @Override
    public int delete(String statementId, Object paramter) {
        return update(statementId,paramter);
    }

    @Override
    public int update(String statementId, Object paramter) {
        dirty = true;
        MappedStatement statement = configuration.getMapperStatement(statementId);
        try {
            return executor.update(statement,paramter);
        } catch (Exception e) {
            throw new PersistenceException("Error updating database.  Cause: "+e,e);
        }
    }

    @Override
    public void commit() {
        if(isCommitOrRollbackRequired(false)){
            try {
                executor.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void rollback() {
        if(isCommitOrRollbackRequired(false)){
            try {
                executor.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isCommitOrRollbackRequired(boolean force) {
        return (!autoCommit && dirty) || force;
    }

}
