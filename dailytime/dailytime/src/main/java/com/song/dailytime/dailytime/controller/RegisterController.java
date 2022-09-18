package com.song.dailytime.dailytime.controller;

import com.song.dailytime.dailytime.Entity.User;
import com.song.dailytime.dailytime.common.ResponseStatus;
import com.song.dailytime.dailytime.common.RestResponse;
import com.song.dailytime.dailytime.service.RegisterUser;
import freemarker.template.utility.DateUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class RegisterController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private RegisterUser registerUser;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "register";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerOneUserRecord(@RequestBody Map<String, String> param) {
        String username = param.get("username");
        String password = param.get("password");
        String email = param.get("email");
        String telephone = param.get("telephone");
        logger.info("=====username=====" + username);
        logger.info("=====password=====" + password);
        logger.info("=====email=====" + email);
        logger.info("=====telephone=====" + telephone);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setTelephone(telephone);
        SimpleDateFormat registerTime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String date=null;
        date = registerTime.format(new Date());
        user.setRegisterTime(date);
        registerUser.registerOneUserRecord(user);
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public RestResponse UserLogin(@RequestBody Map<String, String> param) {
        RestResponse<User> restResponse = new RestResponse();
        String username = param.get("username");
        String password = param.get("password");
        logger.info("=====username=====" + username);
        logger.info("=====password=====" + password);
        User user = registerUser.userLogin(username, password);
        if (user != null) {
            restResponse.setData(user).setStatus(ResponseStatus.Ok);
        } else {
            String msg = "login failed";
            restResponse.setMsg(msg).setStatus(ResponseStatus.Error);
            logger.error(msg);
        }
        return restResponse;
    }
}
