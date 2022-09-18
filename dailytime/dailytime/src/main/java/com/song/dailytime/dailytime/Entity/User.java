package com.song.dailytime.dailytime.Entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private Date loginTime;
    private String registerTime;
}
