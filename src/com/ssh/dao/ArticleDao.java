package com.ssh.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ssh.pojo.Article;
import com.ssh.pojo.Upvote;



public interface ArticleDao {
	
	//按区号查找文章
	public List<Article> findArticlesByZoneId(String zoneId);

	//查找默认交流区的文章
	public List<Article> findDefArticle();
	
	//文章总数
	public int countArticle();
	

	//根据帖子id查询评论总数
	public int queryCountComByArt(int articleId);
	
	//今天文章
	public int countArticleToday();
	
	//根据帖子id查询帖子
	public Article findArticleById(int articleId);
	
	
	//搜索栏搜帖子
	public List<Article> findArticleBytext(String text);
	
	//发帖
	public void addArticle(String title,String content,String username,int zoneId);
	
	/*
	 * 帖子点赞 1 增加点赞信息
	 */
	public void addUpvote(String userName,int articleId);
	
	/*
	 * 帖子点赞2 增加点赞次数
	 */
	public void addUpvoteCount(int articleId);
	
	//查找有没有存在已点赞操作
	public Upvote queryUpvote(String userName,int articleId);
	
	/*
	 * 取消点赞 1 删除点赞信息
	 */
	public void updateUpvote(String userName,int articleId);
	
	/*
	 * 取消点赞2 减少点赞次数
	 */
	public void downUpvoteCount(int articleId);
	
	
	
}
