package com.ssh.service;

public interface UserService {
	
	//登录
	public boolean userLogin(String userName,String userPass);
	
	//注册
	public String register(String userName,String userPass,String email);
	
	//修改用户信息
	public void updateUser(String picUrl,int userId);

}
