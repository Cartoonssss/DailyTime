package com.song.dailytime.dailytime.controller;

import com.song.dailytime.dailytime.Entity.User;
import com.song.dailytime.dailytime.service.RegisterUser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RegisterController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private RegisterUser registerUser;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String home() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerOneUserRecord(@RequestBody Map<String,String> param){
        String username=param.get("username");
        String password=param.get("password");
        String email=param.get("email");
        String telephone=param.get("telephone");
        logger.info("=====username=====" + username);
        logger.info("=====password=====" + password);
        logger.info("=====email=====" + email);
        logger.info("=====telephone=====" + telephone);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setTelephone(telephone);
        registerUser.registerOneUserRecord(user);
        return "user";
    }
}
