package com.ssh.dao;

import java.util.List;
import java.util.Set;

import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;

public interface ReplyDao {

	//根据评论id查询所有回复
	public List<Reply> findReplyByCommentId(int commentId);
	
	//增加回复
	public void addReply(Comment commentId,String userName,String replyContent);
	
}
