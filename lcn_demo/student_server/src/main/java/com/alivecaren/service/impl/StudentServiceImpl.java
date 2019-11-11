package com.alivecaren.service.impl;

import com.alivecaren.dao.StudentDao;
import com.alivecaren.model.Student;
import com.alivecaren.service.StudentService;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;

    @LcnTransaction
    public void add() {
        Student s=new Student();
        s.setId(1l);
        s.setPassword("1");
        s.setUsername("1");
        studentDao.save(s);
    }
}
