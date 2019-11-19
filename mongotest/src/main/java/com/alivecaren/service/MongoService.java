package com.alivecaren.service;

import com.alivecaren.dao.MongoDao;
import com.alivecaren.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MongoService {
    @Resource
    private MongoDao mongoDao;



    public void save(User user) {
        mongoDao.save(user);

    }

    public List<User> findAll() {
        return mongoDao.findAll();
    }
}
