package com.song.dailytime.dailytime.service.serviceInterface;

import com.song.dailytime.dailytime.Entity.UserVO;

import java.util.List;

public interface UserServiceInterFace {

    int registerOneUserRecord(UserVO userVO);

    UserVO userLogin(String username, String password);

    List<UserVO> queryUserList();

    int deleteUserById(String id);

    int updateUserLastLoginTime(UserVO userVO);

    int updateUserInfo(UserVO userVO);

    UserVO selectUserInfoById(String id);

}
