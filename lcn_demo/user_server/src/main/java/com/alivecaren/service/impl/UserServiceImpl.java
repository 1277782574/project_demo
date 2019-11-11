package com.alivecaren.service.impl;

import com.alivecaren.call.CallController;
import com.alivecaren.call.Serverb1Controller;
import com.alivecaren.dao.UserDao;
import com.alivecaren.model.RespBean;
import com.alivecaren.model.User;
import com.alivecaren.service.UserService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CallController callController;

    @Autowired
    private Serverb1Controller serverb1Controller;


    @LcnTransaction
    public void add(User user, String flag){
        //本地
        userDao.save(user);

        //远程调用
        RespBean add = callController.add();
        System.out.println(add);

        if("e".equals(flag)){
            throw new IllegalStateException("模拟异常");
        }else{

        }

    }
}
