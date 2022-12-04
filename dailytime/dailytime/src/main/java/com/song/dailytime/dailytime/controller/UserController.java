package com.song.dailytime.dailytime.controller;

import com.song.dailytime.dailytime.Entity.UserVO;
import com.song.dailytime.dailytime.common.ResponseStatus;
import com.song.dailytime.dailytime.common.RestResponse;
import com.song.dailytime.dailytime.service.serviceInterface.UserServiceInterFace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserServiceInterFace userServiceInterFace;

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "register";
    }

    /**
     * 跳转到用户列表页面
     *
     * @return
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "users";
    }

    /**
     * 注册用户
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String userRegister(@RequestBody Map<String, String> param) {
        String username = param.get("username");
        String password = param.get("password");
        String email = param.get("email");
        String telephone = param.get("telephone");
        logger.info("=====username=====" + username);
        logger.info("=====password=====" + password);
        logger.info("=====email=====" + email);
        logger.info("=====telephone=====" + telephone);
        UserVO userVO = new UserVO();
        String id = UUID.randomUUID().toString().replace("-", "");
        userVO.setId(id);
        userVO.setUsername(username);
        userVO.setPassword(password);
        userVO.setEmail(email);
        userVO.setTelephone(telephone);
        SimpleDateFormat registerTime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String date = registerTime.format(new Date());
        userVO.setRegisterTime(date);
        userServiceInterFace.registerOneUserRecord(userVO);
        return "login";
    }

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/userLogin")
    @ResponseBody
    public RestResponse userLogin(@RequestBody Map<String, String> param) {
        RestResponse<UserVO> restResponse = new RestResponse();
        String username = param.get("username");
        String password = param.get("password");
        logger.info("=====username=====" + username);
        UserVO userVO = userServiceInterFace.userLogin(username, password);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginTime = currentTime.format(new Date(System.currentTimeMillis()));
        userVO.setLastLoginTime(loginTime);
        userServiceInterFace.updateUserLastLoginTime(userVO);
        if (userVO != null) {
            restResponse.setData(userVO).setStatus(ResponseStatus.Ok);
        } else {
            restResponse.setMsg("登录失败").setStatus(ResponseStatus.Error);
            logger.error("登录失败");
        }
        return restResponse;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @PostMapping(value = "/queryUserList")
    @ResponseBody
    public RestResponse queryUserList() {
        RestResponse<List<UserVO>> restResponse = new RestResponse<>();
        List<UserVO> userVOS = userServiceInterFace.queryUserList();
        if (userVOS.size() > 0) {
            restResponse.setData(userVOS).setStatus(ResponseStatus.Ok);
        } else {
            restResponse.setMsg("暂无用户").setStatus(ResponseStatus.Error);
        }
        return restResponse;
    }

    /**
     * 根据id删除用户
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/deleteUserById")
    @ResponseBody
    public Integer deleteUserById(@RequestBody Map<String, String> param) {
        return userServiceInterFace.deleteUserById(param.get("id"));
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/updateUserInfo")
    @ResponseBody
    public Integer updateUserInfo(@RequestBody UserVO userVO) {
        return userServiceInterFace.updateUserInfo(userVO);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectUserInfoById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse selectUserInfoById(@PathVariable("id") String id) {
        RestResponse<UserVO> userInfo = new RestResponse<>();
        UserVO userVO = userServiceInterFace.selectUserInfoById(id);
        if (null != userVO) {
            userInfo.setData(userVO).setStatus(ResponseStatus.Ok);
        } else {
            userInfo.setMsg("用户不存在！").setStatus(ResponseStatus.Error);
        }
        return userInfo;
    }
}
