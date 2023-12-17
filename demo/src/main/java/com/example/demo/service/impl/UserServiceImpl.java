

package com.example.demo.service.impl;


import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConfigConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {


	@Override
	public String login(UserEntity user) {

		if (!ConfigConstant.USERNAME.equals(user.getUsername())){
			return "用户名不存在";
		}
		if (!ConfigConstant.PASSWORD.equals(user.getPassword())){
			return "密码错误";
		}

		return "SUCCESS";
	}
}
