package com.alivecaren.controller;

import com.alivecaren.model.User;
import com.alivecaren.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MongoController {
    @Autowired
    private MongoService mongoService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody User user){
        mongoService.save(user);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Object findAll(){
        List<User> list=mongoService.findAll();
        return list;
    }

    /**
     * 测试 MongoDB副本集的事务
     * @param user
     * @param e
     */
    @RequestMapping(value = "/save/{e}",method = RequestMethod.POST)
    @Transactional
    public void saveE(@RequestBody User user, @PathVariable("e") String e){
        mongoService.save(user);
        if("e".equals(e)){
            throw  new RuntimeException("模拟异常");
        }

    }
}
