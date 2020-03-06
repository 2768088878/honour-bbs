package com.ssh.pojo;

import java.util.Date;

/*
	用户的实体类
*/
public class User {
private int userId;
private String userName; // 用户名
private String userPass; // 密码
private String email; // 邮箱
private String picUrl; // 头像的url
private int role; // 角色
private Date lastLoginTime; // 最后登录时间
private int loginStatus; // 登录状态
private int talkStatus; // 是否允许发言
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getTalkStatus() {
		return talkStatus;
	}
	public void setTalkStatus(int talkStatus) {
		this.talkStatus = talkStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPass=" + userPass + ", email=" + email + ", picUrl="
				+ picUrl + ", role=" + role + ", lastLoginTime="
				+ lastLoginTime + ", loginStatus=" + loginStatus
				+ ", talkStatus=" + talkStatus + "]";
	}
	
}
