package com.song.dailytime.dailytime.controller;

import com.github.pagehelper.PageHelper;
import com.song.dailytime.dailytime.Entity.UserVO;
import com.song.dailytime.dailytime.common.ResponseStatus;
import com.song.dailytime.dailytime.common.RestResponse;
import com.song.dailytime.dailytime.service.serviceInterface.UserServiceInterFace;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
        logger.info("=====username=====" + param.get("username"));
        logger.info("=====password=====" + param.get("password"));
        logger.info("=====email=====" + param.get("email"));
        logger.info("=====telephone=====" + param.get("telephone"));
        UserVO userVO = new UserVO();
        String id = UUID.randomUUID().toString().replace("-", "");
        userVO.setId(id);

        /*Set<String> keys = param.keySet();
        for (String key : keys) {
            String s = param.get(key);
        }*/
        if (StringUtils.isNoneBlank(param.get("username"))) {
            userVO.setUsername(param.get("username"));
        }
        if (StringUtils.isNoneBlank(param.get("password"))) {
            userVO.setPassword(param.get("password"));
        }
        if (StringUtils.isNoneBlank(param.get("email"))) {
            userVO.setEmail(param.get("email"));
        }
        if (StringUtils.isNoneBlank(param.get("telephone"))) {
            userVO.setTelephone(param.get("telephone"));
        }
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
        if (null!=userVO) {
            SimpleDateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginTime = currentTime.format(new Date(System.currentTimeMillis()));
            userVO.setLastLoginTime(loginTime);
            userServiceInterFace.updateUserLastLoginTime(userVO);
            restResponse.setData(userVO).setStatus(ResponseStatus.Ok);
        } else {
            restResponse.setMsg("登录失败").setStatus(ResponseStatus.Error);
            logger.error("账号或密码错误，登录失败");
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

    /**
     * 查询用户列表-分页
     *
     * @return
     */
    @PostMapping(value = "/queryUserByPagination")
    @ResponseBody
    public RestResponse queryUserByPagination(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        RestResponse<List<UserVO>> restResponse = new RestResponse<>();
        PageHelper.startPage(pageNum, pageSize);
        List<UserVO> userVOS = userServiceInterFace.queryUserList();
        if (userVOS.size() > 0) {
            restResponse.setData(userVOS).setStatus(ResponseStatus.Ok);
        } else {
            restResponse.setMsg("暂无用户").setStatus(ResponseStatus.Error);
        }
        return restResponse;
    }
}
