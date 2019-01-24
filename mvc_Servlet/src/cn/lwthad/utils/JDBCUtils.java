package cn.lwthad.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/crm";
    static final String USER = "root";
    static final String PASS = "123456";

    /**
     * 自定义获取Connection
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //****************************测试commons-dbutils-1.6.jar、c3p0-0.9.1.jar*************************//
//    创建c3p0连接池对象
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    //    获得数据库连接对象
    public static Connection getConnectionByC3p0() throws SQLException {
        return cpds.getConnection();
    }

    //    获得c3p0连接池对象
    public static DataSource getC3p0DataSource() {
        return cpds;
    }

}
