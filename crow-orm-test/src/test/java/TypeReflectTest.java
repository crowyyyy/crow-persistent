import com.alibaba.druid.pool.DruidDataSource;
import org.crow.config.SqlSourceBuilder;
import org.crow.config.XMLStatementBuilder;
import org.crow.constants.enums.SqlCommandType;
import org.crow.pojo.Configuration;
import org.crow.pojo.SqlSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import pojo.User;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TypeReflectTest {
    @Test
    public void classReflectTest(){
        String t = "cls";
        Object s = t;
        System.out.println(s.getClass().getName());
    }
    @Test
    public void dom4jTest() throws DocumentException {
        InputStream inputStream = TypeReflectTest.class.getClassLoader().getResourceAsStream("mapper/UserMapper.xml");
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

//        List<Element> elements = rootElement.elements();
        List<Node> selectList = rootElement.selectNodes("//select");
//        String sql = elements.get(1).getTextTrim();
//        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(new Configuration());
//        SqlSource sqlSource = sqlSourceBuilder.parse(sql);
        System.out.println();
    }
    @Test
    public void test1() throws DocumentException {
        InputStream inputStream = TypeReflectTest.class.getClassLoader().getResourceAsStream("mapper/UserMapper.xml");
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        for (SqlCommandType sqlCommandType : SqlCommandType.values()) {
            String sqlType = sqlCommandType.name().toLowerCase();
            List<Node> selectList = rootElement.selectNodes("//"+sqlType);
            for (Node node : selectList) {
                System.out.println();
            }
        }
    }
    @Test
    public void getConnectionTest() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/crow_mybatis?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("Qq13826061107@");
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("CREATE TABLE ? ( id bigint(20) NOT NULL AUTO_INCREMENT, entityId bigint(20) NOT NULL, dx double NOT NULL, dy double NOT NULL, dz double NOT NULL, ntype varchar(32) NOT NULL, gnssTime bigint(20) NOT NULL, speed float DEFAULT NULL, direction float DEFAULT NULL, attributes varchar(255) DEFAULT NULL, PRIMARY KEY (id))");
//        Statement preparedStatement = dataSource.getConnection().createStatement();
//        preparedStatement.executeUpdate("CREATE TABLE test ( id bigint(20) NOT NULL AUTO_INCREMENT, entityId bigint(20) NOT NULL, dx double NOT NULL, dy double NOT NULL, dz double NOT NULL, ntype varchar(32) NOT NULL, gnssTime bigint(20) NOT NULL, speed float DEFAULT NULL, direction float DEFAULT NULL, attributes varchar(255) DEFAULT NULL, PRIMARY KEY (id))");
        preparedStatement.setString(1,"test");
        preparedStatement.executeUpdate();
//        System.out.println();
        System.out.println(new User().getClass()==User.class);
    }
}
