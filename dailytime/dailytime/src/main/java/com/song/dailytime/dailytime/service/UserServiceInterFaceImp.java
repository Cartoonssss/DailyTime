package com.song.dailytime.dailytime.service;

import com.song.dailytime.dailytime.Entity.UserVO;
import com.song.dailytime.dailytime.dao.UserDaoMapper;
import com.song.dailytime.dailytime.service.serviceInterface.UserServiceInterFace;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceInterFaceImp implements UserServiceInterFace {
    @Resource
    private UserDaoMapper userDaoMapper;

    @Override
    public int registerOneUserRecord(UserVO userVO) {
        return userDaoMapper.registerOneUserRecord(userVO);
    }

    @Override
    public UserVO userLogin(String username, String password) {
        return userDaoMapper.userLogin(username,password);
    }

    @Override
    public List<UserVO> queryUserList() {
        return userDaoMapper.queryUserList();
    }

    @Override
    public int deleteUserById(String id) {
        return userDaoMapper.deleteUserById(id);
    }

    @Override
    public int updateUserLastLoginTime(UserVO userVO) {
        return userDaoMapper.updateUserLastLoginTime(userVO);
    }

    @Override
    public int updateUserInfo(UserVO userVO) {
        return userDaoMapper.updateUserInfo(userVO);
    }

    @Override
    public UserVO selectUserInfoById(String id) {
        return userDaoMapper.selectUserInfoById(id);
    }



}
