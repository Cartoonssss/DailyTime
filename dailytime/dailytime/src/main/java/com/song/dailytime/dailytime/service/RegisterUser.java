package com.song.dailytime.dailytime.service;

import com.song.dailytime.dailytime.Entity.User;

import java.util.List;

public interface RegisterUser {

    int registerOneUserRecord(User user);

    User userLogin(String username,String password);

    List<User> queryUserList();
}
