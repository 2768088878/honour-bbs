package com.ssh.service;

import java.sql.SQLException;
import java.util.List;

import com.ssh.dao.ArticleDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;


public interface ArticleService {

	public List<Article> findArticle(String zongId);
	
	public int countArticle();
	
	public int countArticleToday();
	
	//根据帖子id查询帖子
	public Article findArticleById(int articleId);
	
	//在搜索栏搜索帖子
	public List<Article> findArticleBytext(String text);
	
	
	//发帖
	public void addArticle(String title,String contentint,String username,int zoneId);
	

	//根据帖子id查询评论总数
	public int queryCountComByArt(int articleId);
	
	//点赞帖子
	public int addUpvote(String userName,int articleId);
	
}






