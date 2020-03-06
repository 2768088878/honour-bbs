package com.ssh.dao;

import java.util.List;
import java.util.Set;

import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;

public interface CommentDao {

	//根据帖子id查询所有评论
	public List<Comment> findCommentByArticleId(int articleId, String order);
	

	/*根据帖子id评论
	 * articleId:帖子id
	 * comment：评论内容
	 * commentUserName：评论人
	 */
	public void addComment(Article article,String commentContent,String commentUserName);
	
	/*
	 * 评论顺便增加帖子reply数
	 */
	public void addArticleReplyCount(int articleId);
	
	/*
	 * 根据帖子id查询所有评论
	 */
	public List<Comment> findComByArticleId(int article,String order);
	
	//根据评论id查询评论信息
	public Comment findCommentById(int commentId);
}
