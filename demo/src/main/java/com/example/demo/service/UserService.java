

package com.example.demo.service;


import com.example.demo.entity.UserEntity;

public interface UserService {


	/**
	 * 用户登录
	 * @param user
	 */
	String login(UserEntity user);
}
