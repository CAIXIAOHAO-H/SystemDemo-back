package com.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.back.entity.Resourse;
import com.back.entity.Role;
import com.back.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(tags = "系统管理控制层")
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @GetMapping("/getAllResourse")
    @ResponseBody
    @ApiOperation("获取所有资源")
    public String getAllResourse(){
        JSONObject result = new JSONObject();
        List<Resourse> resourses = systemService.getAllResourse();
        result.put("data",resourses);
        return result.toJSONString();
    }

    @GetMapping("/getAllRole")
    @ResponseBody
    @ApiOperation("获取所有角色")
    public String getAllRole(){
        JSONObject result = new JSONObject();
        List<Role> roles = systemService.getAllRoles();
        result.put("data",roles);
        return result.toJSONString();
    }

}
