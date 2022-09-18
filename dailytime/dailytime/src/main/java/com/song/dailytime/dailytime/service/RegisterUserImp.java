package com.song.dailytime.dailytime.service;

import com.song.dailytime.dailytime.Entity.User;
import com.song.dailytime.dailytime.dao.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterUserImp implements RegisterUser {
    @Autowired
    private UserDaoMapper userDao;

    @Override
    public int registerOneUserRecord(User user) {
        return userDao.registerOneUserRecord(user);
    }

    @Override
    public User userLogin(String username, String password) {
        return userDao.userLogin(username,password);
    }

    @Override
    public List<User> queryUserList() {
        return userDao.queryUserList();
    }
}
