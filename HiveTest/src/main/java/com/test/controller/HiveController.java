package com.test.controller;


import com.alibaba.fastjson.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hive")
public class HiveController {


    @Resource(name="hiveDruidTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建数据库
     * hive不会在数据库中再创建数据库
     * 不管你配置文件中的url配的是什么，他都会在根目录下创建这个数据库
     */
    @RequestMapping("/createDataBase")
    public void createDataBase(){
        try {
            jdbcTemplate.execute("create database hkk");
            System.out.println("=============创建成功===============");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("=============创建失败=================");
        }

    }


    /**
     * 创建表
     * 之后的操作都需要改配置文件中的连接信息
     * url：jdbc:hive2://hserver1:10000/hkk
     */
    @RequestMapping("/createTable")
    public void createTable(){
        //这句sql的意思就是：字段与字段之间用“\t”间隔，下一行用 “\n”间隔，也就是 Tab键和回车键
        String sql="create table user_info (id int, name string, sex string) row format delimited fields terminated by '\\t' lines terminated by '\\n'";
        try {
            jdbcTemplate.execute(sql);
            System.out.println("=============创建成功===============");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("=============创建失败=================");
        }
    }

    /**
     * 添加数据，需要把hadoop集群中yarn-site中的内存调大
     */
    @RequestMapping("/insert")
    public void insert(){
        String sql="insert into  user_info (id,name,sex) values (1,'和凯凯','男')";
        try {
            jdbcTemplate.execute(sql);
            System.out.println("=============添加成功===============");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("=============添加失败=================");
        }
    }

    /**
     * 把结构化的数据直接追加到数据库表中
     */
    @RequestMapping("/loadIntoTable")
    public void loadIntoTable(){
        String filePath="/opt/hive/user_info.txt";
        String sql="load data local inpath '"+filePath+"'into table user_info";
        try {
            jdbcTemplate.execute(sql);
            System.out.println("=============导入成功=================");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("=============导入失败=================");
        }
    }

    /**
     * 查询表中所有数据
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll() {
        String jsonString = null;
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("select *from user_info");
            jsonString = JSONArray.toJSONString(maps);
            System.out.println("==================得到的数据======================");
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("====================出错了=======================");
        }
        return jsonString;
    }

    /**
     * 删除表
     */
    @RequestMapping("/deleteTable")
    public void deleteTable(){
        try {
            jdbcTemplate.execute("drop TABLE if EXISTS user_info");
            System.out.println("==================删除表成功======================");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("==================error======================");
        }
    }

    /**
     * 删除数据库
     * 此时的连接信息
     * url：jdbc:hive2://hserver1:10000
     */
    @RequestMapping("/deleteDataBase")
    public void deleteDataBase(){
        try {
            jdbcTemplate.execute("drop database if EXISTS hkk");
            System.out.println("=============删除成功===============");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("=============删除失败=================");
        }
    }

}
