package com.example.demo.controller;


import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api("APP登录接口")
@RestController
@RequestMapping("vue-element-admin/user")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserEntity user) {

        //用户登录
        String result = userService.login(user);

        //生成token
        String token = jwtUtils.generateToken(user.getUsername());

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        if ("SUCCESS".equals(result)){
            return Result.success(map);
        }else {
            return Result.fail(result);
        }

    }

    @ApiOperation("用户信息")
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.success(map);
    }

    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout() {
        return Result.success();
    }

    @ApiOperation("列表")
    @GetMapping("list")
    public Result list() {
        return Result.success();
    }

}
