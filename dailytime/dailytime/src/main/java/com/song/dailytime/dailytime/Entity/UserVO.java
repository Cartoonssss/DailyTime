package com.song.dailytime.dailytime.Entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class UserVO {
    private String id;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private String lastLoginTime;
    private String registerTime;
    private String address;
    private String birthday;
    private String userRole;
}
