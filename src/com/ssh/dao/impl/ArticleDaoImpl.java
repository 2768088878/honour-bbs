package com.ssh.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ssh.dao.ArticleDao;
import com.ssh.ex.ArticleException;
import com.ssh.pojo.Article;
import com.ssh.pojo.Upvote;
import com.ssh.pojo.User;

import sun.launcher.resources.launcher;


@Repository("articledao")
@Transactional
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
//		return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}
	
	public void testQuery(int id){
		Article article=(Article)getSession().get(Article.class,id);
		System.out.println(article);
		
	}
	
	
	
	//按区号查找文章
	public List<Article> findArticlesByZoneId(String zoneId){
		String hql="from Article a where a.zoneId =? order by isTop desc, sendTime asc";
		Query query=getSession().createQuery(hql).setString(0, zoneId);
		return query.list();
	}

	//查找默认交流区的文章
	public List<Article> findDefArticle(){
		String hql="from Article a where a.zoneId =1 order by isTop desc, sendTime asc";
		Query query=getSession().createQuery(hql);
		return query.list();
	}

	
	//所有交流区文章总数
	public int countArticle(){
		String hql="select count(a.articleId) from Article a";
		Query query=getSession().createQuery(hql);
		return ((Number)query.uniqueResult()).intValue();
	}
		
	//今天文章数
	public int countArticleToday(){
		String hql="select count(a.articleId) from Article a where to_days(a.sendTime) = to_days(now())";
		Query query=getSession().createQuery(hql);
		return ((Number)query.uniqueResult()).intValue();
	}
	
	//根据帖子id查询评论总数
	public int queryCountComByArt(int articleId){
		String hql="select count(a.commentId) from Comment a where a.article=?";
		Query query=getSession().createQuery(hql).setInteger(0,articleId);
		return ((Number)query.uniqueResult()).intValue();
	}
	
	//根据帖子id查询帖子
	public Article findArticleById(int articleId){
		String hql="from Article a where a.articleId=?";
		String hql1="select a.title,a.content,a.sendTime,a.senderName,a.isTop,a.replyCount,a.upvoteCount,a.browseCount,a.zoneId from Article a where a.articleId=?";
		Article query=(Article) getSession().createQuery(hql).setInteger(0,articleId).uniqueResult();
		return query;
	}
	
	
	//搜索栏搜帖子
	public List<Article> findArticleBytext(String text){
		String hql="from Article a where a.title like ? or a.content like ?";
		List<Article> query=getSession().createQuery(hql).setString(0,"%"+text+"%").setString(1,"%"+text+"%").list();
		return query;
	}
	
	
	//发帖
	/*
	 * (non-Javadoc)
	 * 发帖需要交流区id，用户名，标题，正文
	 */
	public void addArticle(String title,String content,String username,int zoneId){
		Article article = new Article();
		article.setZoneId(zoneId);
		article.setSenderName(username);
		article.setTitle(title);
		article.setSendTime(new Date());
		article.setContent(content);
		getSession().save(article);
//		String hql="from Article a where a.title like ? or a.content like ?";
	}
	
	
	/*
	 * 帖子点赞 1 增加点赞信息
	 */
	public void addUpvote(String userName,int articleId){
		//hibernate不能使用insert语句
//		String hql="insert into Upvote(upvoteUserName, upvoteArticleId) values(?, ?)";
//		getSession().createQuery(hql).setString(0,userName).setInteger(1, articleId).executeUpdate();
		Upvote upvote =new Upvote();
		upvote.setUpvoteUserName(userName);
		upvote.setUpvoteArticleId(articleId);
		getSession().save(upvote);
	}
	
	/*
	 * 帖子点赞2 增加点赞次数
	 */
	public void addUpvoteCount(int articleId){
		String hql="update Article a set a.upvoteCount = a.upvoteCount+1 where a.articleId=?";
		getSession().createQuery(hql).setInteger(0,articleId).executeUpdate();
	}
	
	//查找有没有存在已点赞操作
	public Upvote queryUpvote(String userName,int articleId){
		String hql="from Upvote where upvoteUserName=? and upvoteArticleId=?";
		Upvote query=(Upvote) getSession().createQuery(hql).setString(0, userName).setInteger(1, articleId).uniqueResult();
		return query;
	}
	
	
	/*
	 * 取消点赞 1 删除点赞信息
	 */
	public void updateUpvote(String userName,int articleId){
		String hql="delete from Upvote where upvoteUserName=? and upvoteArticleId=?";
		getSession().createQuery(hql).setString(0,userName).setInteger(1, articleId).executeUpdate();
	}
	
	/*
	 * 取消点赞2 减少点赞次数
	 */
	public void downUpvoteCount(int articleId){
		String hql="update Article a set a.upvoteCount = a.upvoteCount-1 where a.articleId=?";
		getSession().createQuery(hql).setInteger(0,articleId).executeUpdate();
	}
}



















