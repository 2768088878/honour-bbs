package com.ssh.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.dao.ReplyDao;
import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;

@Repository("replyDao")
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	//根据评论id查询回复
	@Override
	public List<Reply> findReplyByCommentId(int commentId) {
		String hql="from Reply r where r.comment = ? ";
		List<Reply> list=getSession().createQuery(hql).setInteger(0,commentId).list();
//		Set<Reply> set=new HashSet<Reply>(list);
		return list;
	}
	
	//增加回复
	public void addReply(Comment commentId,String userName,String replyContent){
		Reply reply=new Reply();
		reply.setReplyUserName(userName);
		reply.setReplyContent(replyContent);
		reply.setComment(commentId);
		getSession().save(reply);
	}
	
	
	//根据评论id查询回复
//	public List<Reply> findReplyByCommentId(int commentId){
//		String hql="from Reply r where r.article = ? order by replyTime desc";
//		List<Reply> query=getSession().createQuery(hql).setInteger(0,commentId).list();
//		return query;
//	}
	
	

}
