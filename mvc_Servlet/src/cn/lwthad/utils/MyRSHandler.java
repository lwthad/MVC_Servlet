package cn.lwthad.utils;

import cn.lwthad.entity.User;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyRSHandler implements ResultSetHandler<List<User>> {
    /**
     * 重写查询返回 集封装结果集策略对象
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> handle(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setPasswd(resultSet.getString("passwd"));

            list.add(user);
        }
        return list;
    }
}
