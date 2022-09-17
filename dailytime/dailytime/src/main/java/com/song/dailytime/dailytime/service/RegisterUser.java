package com.song.dailytime.dailytime.service;

import com.song.dailytime.dailytime.Entity.User;

public interface RegisterUser {

    int registerOneUserRecord(User user);

    User userLogin(String username,String password);
}
