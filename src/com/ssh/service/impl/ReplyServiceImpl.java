package com.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.ReplyDao;
import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements com.ssh.service.ReplyService {

	@Autowired
	ReplyDao replyDao;
	
	@Override
	public void addReply(Comment commentId, String userName, String replyContent) {
		replyDao.addReply(commentId, userName, replyContent);
	}
	
	
	//根据评论id查询所有回复
	public List<Reply> findReplyByCommentId(int commentId){
		return replyDao.findReplyByCommentId(commentId);
	}

}
