package com.ssh.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.CommentDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;
import com.ssh.service.ArticleService;
import com.ssh.service.CommentService;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	CommentDao commentDao;
	
	//发表评论+增加reply回复数
	public void addComment(Article article,String commentContent,String commentUserName){
		commentDao.addComment(article, commentContent, commentUserName);
		System.out.println("喂喂喂service层测试一下帖子id："+article);
		commentDao.addArticleReplyCount(article.getArticleId());
	}

	//根据帖子id查询所有的评论
	public List<Comment> findComByArticleId(int article,String order){
		return commentDao.findComByArticleId(article,order);
	}
	
	//根据评论id查询评论信息
	public Comment findCommentById(int commentId){
		return commentDao.findCommentById(commentId);
	}
}
