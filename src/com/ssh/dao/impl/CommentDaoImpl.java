package com.ssh.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.dao.CommentDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;


@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	

	@Override
	public List<Comment> findCommentByArticleId(int articleId, String order) {
		String hql;
		if ("desc".equals(order)) {
			hql="from Comment c where c.article = ? order by commentTime desc";
		} else {
			hql="from Comment c where c.article = ? order by commentTime asc";
		}
//		Set<Comment> query=(Set<Comment>) getSession().createQuery(hql).setInteger(0,articleId).list();
		List<Comment> list=getSession().createQuery(hql).setInteger(0,articleId).list();
//		System.out.println("list————————："+list);
		return list;
	}

	/*根据帖子id评论
	 * articleId:帖子id
	 * comment：评论内容
	 * commentUserName：评论人
	 */
	public void addComment(Article article,String commentContent,String commentUserName){
//		String hql="insert into Comment (commentContent,commentUserName,article) value (?,?,?)";
//		Query query=getSession().createQuery(hql);
//		query.setString(0,commentContent);
//		query.setString(1,commentUserName);
//		query.setInteger(2,articleId);
//		query.executeUpdate();
		Comment comment=new Comment();
		comment.setCommentContent(commentContent);
		comment.setCommentUserName(commentUserName);
		comment.setArticle(article);
		getSession().save(comment);
	}
	
	/*
	 * 评论顺便增加帖子reply数
	 */
	public void addArticleReplyCount(int articleId){
		String hql="update Article a set a.replyCount = a.replyCount+1 where a.articleId=?";
		getSession().createQuery(hql).setInteger(0,articleId).executeUpdate();
	}
	
	/*
	 * 根据帖子id查询所有评论
	 */
	public List<Comment> findComByArticleId(int article,String order){
		String hql;
		if ("asc".equals(order)) {
			hql="from Comment c where c.article = ? order by commentTime asc";
		} else {
			hql="from Comment c where c.article = ? order by commentTime desc";
		}
		List<Comment> query=getSession().createQuery(hql).setInteger(0,article).list();
		return query;
	}
	
	
	//根据评论id查询评论信息
	public Comment findCommentById(int commentId){
		String hql="from Comment where commentId=?";
		Comment query=(Comment) getSession().createQuery(hql).setInteger(0,commentId).uniqueResult();
		return query;
		
	}
	
}
