package com.song.dailytime.dailytime.dao;

import com.song.dailytime.dailytime.Entity.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
public interface UserDaoMapper {

    //注册一个用户记录
    int registerOneUserRecord(UserVO userVO);

    //用户登录
    UserVO userLogin(String username, String password);

    //查询用户列表
    List<UserVO> queryUserList();

    //删除用户
    int deleteUserById(String id);

    //用户上次登录时间
    int updateUserLastLoginTime(UserVO userVO);

    //更新用户信息
    int updateUserInfo(UserVO userVO);

    //根据id查询用户信息
    UserVO selectUserInfoById(String id);

}
