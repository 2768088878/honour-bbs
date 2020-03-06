package com.ssh.service;

import java.util.List;
import java.util.Set;

import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;

public interface CommentService {
	
	//发表评论
	public void addComment(Article article,String commentContent,String commentUserName);
	
	//根据帖子id查询所有的评论
	public List<Comment> findComByArticleId(int article,String order);
	
	//根据评论id查询评论信息
	public Comment findCommentById(int commentId);
	
}
