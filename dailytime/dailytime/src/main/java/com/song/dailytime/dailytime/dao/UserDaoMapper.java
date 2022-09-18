package com.song.dailytime.dailytime.dao;

import com.song.dailytime.dailytime.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//@Repository
public interface UserDaoMapper {

    int registerOneUserRecord(User user);

    User userLogin(String username,String password);

    List<User> queryUserList();
}
