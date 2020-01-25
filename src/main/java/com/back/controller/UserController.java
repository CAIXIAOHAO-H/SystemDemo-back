package com.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.back.entity.Resourse;
import com.back.entity.User;
import com.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@Api(tags = "用户控制层")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("test");
        return "test";
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    @ApiOperation("获取所有用户")
    public String getAllUser(){
        JSONObject result = new JSONObject();
       List<User> users =  userService.getAllUser();
       result.put("data",users);
       return result.toJSONString();
    }

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("登录")
    public String login(@RequestBody User user){
        JSONObject result = new JSONObject();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(),user.getPassword());
            subject.login(usernamePasswordToken);
            //登入成功后存入session
            Session session = subject.getSession();
            session.setAttribute("currentUser",user);
            result.put("msg","登入成功");
            result.put("status",1);

        }catch (DisabledAccountException e) {
            result.put("status",0);
            result.put("msg","用户已被禁用");
        }
        catch(UnknownAccountException e) {
            result.put("status",0);
            result.put("msg","用户不存在");
        }
        catch(IncorrectCredentialsException e) {
            result.put("status",0);
            result.put("msg","用户账号密码错误");
        }
        catch (AuthenticationException e) {
            result.put("status",0);
            result.put("msg","登入失败");
        }
        return result.toJSONString();
    }

    @PostMapping("/regist")
    @ResponseBody
    @ApiOperation("用户注册")
    public String regist(@RequestBody User user){
        JSONObject result = new JSONObject();
        if(userService.registUser(user)>0){
            result.put("status",1);
            result.put("msg","新增成功");
        }
        else{
            result.put("status",0);
            result.put("msg","新增失败");
        }
        return result.toJSONString();
    }

    @GetMapping("/userLogout")
    @ResponseBody
    @ApiOperation("用户退出")
    public void userLogout(){
        JSONObject result = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.removeAttribute("currentUser");
    }

    @GetMapping("/getResourseByUserName")
    @ResponseBody
    @ApiOperation("根据用户名称获取权限id")
    public String getResourseByUserName(String userName){
        JSONObject result = new JSONObject();
        List<Resourse> resourses = userService.getResourseByUserName(userName);
        result.put("data",resourses);
        return result.toJSONString();
    }


}
