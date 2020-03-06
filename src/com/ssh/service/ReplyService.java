package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;

public interface ReplyService {
	
	/*
	 * 回复评论
	 * username 用户名
	 * replyContent 回复内容
	 * commentId 评论id
	 */
	public void addReply(Comment commentId,String userName,String replyContent);

	
	//根据评论id查询所有回复
	public List<Reply> findReplyByCommentId(int commentId);
}
