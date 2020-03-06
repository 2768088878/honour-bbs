package com.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.UserDao;
import com.ssh.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;
	
	@Override
	public boolean userLogin(String userName,String userPass) {
		boolean flag=false;
		if (null!=userdao.userLogin(userName, userPass)) {
			flag=true;
		}
		return flag;
	}
	
	//注册
	public String register(String userName,String userPass,String email){
		//返回错误信息
		String mes;
		//注册前先判断是否有该用户
		if (userdao.ifExist(userName)==null) {
			userdao.register(userName,userPass,email);
			mes="注册成功";
		}else {
			mes="该用户已存在";
		}
		return mes;
	}

	
	//修改用户信息
	public void updateUser(String picUrl,int userId){
		userdao.updateUser(picUrl, userId);
	}
	
	
	
	
}
