package com.ssh.test;



import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ssh.dao.ArticleDao;
import com.ssh.dao.impl.ArticleDaoImpl;
import com.ssh.pojo.Article;
import com.ssh.service.ArticleService;
import com.ssh.service.impl.ArticleServiceImpl;


public class test {
	
	//避免每次写单元测试的时候都要开启事务和关闭事务
		private SessionFactory sessionFactory;
		private Session session;
		private Transaction tx;
		@Before
		public void init(){
			//1.创建SessionFactory 使用无参数的重载方法 默认关联类目录下的hibernate.cfg.xml
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
					applySettings(configuration.getProperties()).buildServiceRegistry();
				//4.0版本之前创建工厂的方法
				sessionFactory=configuration.buildSessionFactory(serviceRegistry);
			
			//2.创建Session对象
				session=sessionFactory.openSession();
			
			//3.开启事务
				tx=session.beginTransaction();

		}
		
		@After
		public void destory(){
			tx.commit();
			session.close();
			sessionFactory.close();

		}
	

	
	@Test
	public void findArticle(String zongId){	
//		String hql="from Article,Zone where Article.zoneId = Zone.zoneId and Zone.isDef = 1 " +
//				"order by isTop desc, sendTime asc";
//			Query query=session.createQuery(hql);
//			System.out.println(query.list());
	}
}
