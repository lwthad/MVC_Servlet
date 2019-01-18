package cn.lwthad.service;

import cn.lwthad.dao.UserDao;
import cn.lwthad.entity.User;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao = new UserDao();

    public User find(){
        return userDao.find();
    }

    public int find(User user) {
        try {
            return userDao.find(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
