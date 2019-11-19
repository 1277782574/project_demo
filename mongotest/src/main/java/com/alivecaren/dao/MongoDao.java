package com.alivecaren.dao;

import com.alivecaren.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDao extends MongoRepository<User,String> {
}
