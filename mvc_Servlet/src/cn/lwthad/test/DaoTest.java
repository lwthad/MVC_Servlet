package cn.lwthad.test;

import cn.lwthad.entity.User;
import cn.lwthad.utils.JDBCUtils;
import cn.lwthad.utils.MyRSHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class DaoTest {
    /**
     * ResultSetHandler实现类介绍（由DbUtils框架提供）
     * 备注：DbUtils给我们提供了10个ResultSetHandler实现类，分别是：
     *      ①ArrayHandler：     将查询结果的第一行数据，保存到Object数组中
     *      ②ArrayListHandler     将查询的结果，每一行先封装到Object数组中，然后将数据存入List集合
     *      ③BeanHandler     将查询结果的第一行数据，封装到user对象
     *      ④BeanListHandler     将查询结果的每一行封装到user对象，然后再存入List集合
     *      ⑤ColumnListHandler     将查询结果的指定列的数据封装到List集合中
     *      ⑥MapHandler     将查询结果的第一行数据封装到map结合（key==列名，value==列值）
     *      ⑦MapListHandler     将查询结果的每一行封装到map集合（key==列名，value==列值），再将map集合存入List集合
     *      ⑧BeanMapHandler     将查询结果的每一行数据，封装到User对象，再存入map集合中（key==列名，value==列值）
     *      ⑨KeyedHandler     将查询的结果的每一行数据，封装到map1（key==列名，value==列值 ），然后将map1集合（有多个）存入map2集合（只有一个）
     *      ⑩ScalarHandler     封装类似count、avg、max、min、sum......函数的执行结果
     */
    @Test
    public void TestBeanListHandler(){
        QueryRunner qr = new QueryRunner(JDBCUtils.getC3p0DataSource());
        String sql = "select * from user";
        List<User> users = null;
        try {
            users = qr.query(sql, new BeanListHandler<>(User.class));
            for(User u : users){
                System.out.println(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestBeanHandler(){
        QueryRunner qr = new QueryRunner(JDBCUtils.getC3p0DataSource());
        String sql = "select * from user";
        User user = null;
        try {
            user = qr.query(sql, new BeanHandler<>(User.class));
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  QueryRunner的query方法和ResultSetHandler接口的使用
     *  自定义实现ResultSetHandler封装查询结果集
     */
    @Test
    public void TestQueryRunnerAndC3p0Conn(){
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select username, passwd from user";
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionByC3p0();
            List<User> list = queryRunner.query(conn, sql, new MyRSHandler());
            System.out.println("查询的user：");
            for(User u:list){
                System.out.println("    "+ u);
            }
        } catch (SQLException e) {
            if(conn == null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    /**
     * 测试dbutils的QueryRunner()和c3p0 数据源
     * @throws SQLException
     */
    @Test
    public void TestQueryRunnerAndC3p0Ds() throws SQLException {
        DataSource c3p0DataSource = JDBCUtils.getC3p0DataSource();

        QueryRunner queryRunner = new QueryRunner(c3p0DataSource);

        String sql = "insert into user values(?,?)";
        int i = queryRunner.update(sql, "LI hua", 123123);

        System.out.println("插入结果："+i);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 测试自定义Conn
     *
     * 查询 Zhangsan
     */
    @Test
    public void testConn(){
        Connection conn = JDBCUtils.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT username, passwd from user where username = 'Zhangsan'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                String username = rs.getString("username");
                String passwd = rs.getString("passwd");
                System.out.println("用户名: "+username);
                System.out.println("密  码："+passwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
