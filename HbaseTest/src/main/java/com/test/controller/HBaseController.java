package com.test.controller;


import com.test.util.HBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hbase")
public class HBaseController {

    @Autowired
    private HBaseUtil hBaseUtil;

    @RequestMapping("/findAll")
    public String findAll(){
        String user = hBaseUtil.findAll("User");
        System.out.println(user);
        return user;
    }
}
