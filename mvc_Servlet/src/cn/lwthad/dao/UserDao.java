package cn.lwthad.dao;

import cn.lwthad.entity.User;
import cn.lwthad.utils.JDBCUtils;
import cn.lwthad.utils.MyRSHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /**
     * 使用dbutils的queryRunner方法和c3p0连接池
     */
    public int find(User user) throws SQLException {
        DataSource c3p0DataSource = JDBCUtils.getC3p0DataSource();
        QueryRunner queryRunner = new QueryRunner(c3p0DataSource);
        String sql = "select * from user where username= ? and passwd= ?";

        List<User> userList = queryRunner.query(sql, new MyRSHandler(), new Object[]{user.getUsername(), user.getPasswd()});
        if(userList.size()!=0){
            System.out.println("用户： "+ userList.get(0));
            return 1;
        }
        return 0;
    }

    /**
     * 使用自定义Connection连接数据库 Manual
     */
    /*public int find(User user) throws SQLException {
        Connection conn = JDBCUtils.getConn();

        String sql = "select * from user where username= ? and passwd= ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPasswd());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("Passwd: "+ resultSet.getString("passwd"));
            return 1;
        }
        return 0;
    }*/


    public User find(){
        return new User("ZhangSan","123123");
    }

    /*public int find(User user) {
        if(user.getUsername().equals("Zhangsan")&&user.getPasswd().equals("123123")){
            return 1;
        }
        return 0;
    }*/
}

