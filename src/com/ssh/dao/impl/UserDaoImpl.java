package com.ssh.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.UserDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	

	
	//登录
	@Override
	public User userLogin(String userName,String userPass) {
		String hql="from User where userName=? and userPass=?";
		User user=(User) getSession().createQuery(hql).setString(0,userName).setString(1,userPass).uniqueResult();
		return user;
	}
	//注册前判断是否存在该用户
	public User ifExist(String userName){
		String hql="from User where userName=?";
		User user=(User) getSession().createQuery(hql).setString(0,userName).uniqueResult();
		System.out.println("判断是否存在该用户:"+user);
		return user;
	}
	//注册
	public void register(String userName,String userPass,String email){
		String hql;
		int query=0;
		if (null==email||"".equals(email)) {
			/*hql没有insert
			 * hql="insert into User (userName,userPass) values (?,?)";
			 * query=getSession().createQuery(hql).setString(0,userName).setString(1,userPass).executeUpdate();
			 */
			User user = new User();
			user.setUserName(userName);
			user.setUserPass(userPass);
			getSession().save(user);		
		}else {
			User user = new User();
			user.setUserName(userName);
			user.setUserPass(userPass);
			user.setEmail(email);
			getSession().save(user);	
		}
	}
	

	//修改用户信息
	public void updateUser(String picUrl,int userId){	
		String hql="update User set picUrl=? where userId=?";
		getSession().createQuery(hql).setString(0,picUrl).setInteger(1,userId).executeUpdate();
	}
}
