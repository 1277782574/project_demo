package com.alivecaren.controller;

import com.alivecaren.model.RespBean;
import com.alivecaren.model.User;
import com.alivecaren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/add/{flag}")
    public RespBean add(@PathVariable("flag") String flag){
        RespBean res=new RespBean();
            User user =new User();
            user.setId(1l);
            user.setPassword("1");
            user.setUsername("1");
            userService.add(user,flag);
            res.setStatus(200);
        return res;
    }

}
