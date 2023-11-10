package org.crow.factory;

public interface SqlSessionFactory {
    SqlSession openSqlSession();

    SqlSession openSqlSession(boolean autoCommit);
}
