package com.casicloud.controller;

import com.casicloud.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    //创建线程安全的map
    static Map<Integer, User> map = Collections.synchronizedMap(new HashMap<Integer, User>());

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/getUserById/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Integer id){
        return new User(1,"hekaikai","123456");
    }

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public List<User> getUserList (){
        List<User> list=new ArrayList<>();
        list.add(new User(1,"hekaikai","123456"));
        list.add(new User(2,"xiaoma","123456"));
        return list;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save (@RequestBody User user){

        return "保存成功";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete (@PathVariable(value = "id") Integer id){

        return "删除成功";
    }


    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public User update (@PathVariable("id") Integer id, @RequestBody User user){

        return user;
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }

}
