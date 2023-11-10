import org.crow.factory.SqlSession;
import org.crow.factory.SqlSessionFactory;
import org.crow.factory.SqlSessionFactoryBuilder;
import org.crow.io.Resources;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import pojo.Course;
import pojo.User;

import java.io.InputStream;
import java.util.List;

public class CrowOrmTest {
    private SqlSession sqlSession;

    /**
     * 前置准备
     */
    @Before
    public void before() throws DocumentException {
        InputStream inputStream = Resources.getResource("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = SqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sessionFactory.openSqlSession(true);
    }

    /**
     * 查询多个封装为集合
     */
    @Test
    public void selectListTest() throws Exception {
        List<User> list = sqlSession.getList("user.selectList", null);
        list.forEach(System.out::println);
    }

    /**
     * 查询单个
     */
    @Test
    public void selectOneTest() throws Exception {
        User user = sqlSession.getOne("user.selectOne","1");
        System.out.println(user);
    }

    /**
     * 多表联查
     */
    @Test
    public void selectByJoinTest() throws Exception {
        List<Course> list = sqlSession.getList("user.joinSelect", "cls");
        list.forEach(System.out::println);
    }

    /**
     * 插入数据
     */
    @Test
    public void insertTest() throws Exception {
        User user = new User("xxq2", 4);
        sqlSession.insert("user.insertUser",user);
    }

    /**
     * 删除数据
     */
    @Test
    public void deleteTest() throws Exception {
        sqlSession.delete("user.deleteUser","2");
    }

    /**
     * 更新数据
     */
    @Test
    public void updateTest() throws Exception {
        User user = new User("tom2", 1);
        sqlSession.update("user.updateUser",user);
    }

    /**
     * 建表
     */
    @Test
    public void createTableTest() throws Exception {
        sqlSession.update("user.createTable","test");
    }

    /**
     * 删除表
     */
    @Test
    public void dropTableTest(){

    }

    /**
     * 创建视图
     */
    @Test
    public void createView(){

    }

    /**
     * 关闭事务测试（系统默认开启）
     */
    @Test
    public void transactionInsertTest() throws Exception {
        InputStream inputStream = Resources.getResource("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = SqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sessionFactory.openSqlSession(true);
        User user = new User("autocommit", 999);
        sqlSession.insert("user.insertUser", user);
    }
    /**
     * 测试事务效果
     */
    @Test
    public void transactionEffectTest() throws DocumentException {
        InputStream inputStream = Resources.getResource("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = SqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sessionFactory.openSqlSession(false);
        User user = new User("u1", 1234);
        User user2 = new User("u2", 1234);
        try {
            sqlSession.insert("user.insertUser", user);
            sqlSession.insert("user.insertUser", user2);
            sqlSession.commit();
        }catch(Exception e){
            System.out.println("transaction rollback");
            sqlSession.rollback();
            e.printStackTrace();
        }

    }


}
