package com.ssh.dao;

import java.sql.SQLException;
import java.util.List;



import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ssh.pojo.User;
import com.ssh.pojo.Zone;


public interface UserDao {

	//登录
	public User userLogin(String userName,String userPass);
	
	//注册前判断是否存在该用户
	public User ifExist(String userName);
	
	//注册
	public void register(String userName,String userPass,String email);
	
	//修改用户信息
	public void updateUser(String picUrl,int userId);
}
