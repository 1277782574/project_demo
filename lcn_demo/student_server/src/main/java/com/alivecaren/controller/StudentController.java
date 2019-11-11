package com.alivecaren.controller;


import com.alivecaren.model.RespBean;
import com.alivecaren.service.StudentService;
import com.alivecaren.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/add")
    public RespBean add(){
        RespBean res=new RespBean();
        try {
            studentService.add();
            res.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
            res.setStatus(500);
        }
        return  res;

    }


}
