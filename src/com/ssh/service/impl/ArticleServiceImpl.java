package com.ssh.service.impl;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.ArticleDao;
import com.ssh.dao.CommentDao;
import com.ssh.dao.ReplyDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;
import com.ssh.pojo.Upvote;
import com.ssh.service.ArticleService;




@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleDao articledao;
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	ReplyDao replyDao;
	
	
	public List<Article> findArticle(String zoneId){
		if (zoneId != null && !"".equals(zoneId)) {
			return articledao.findArticlesByZoneId(zoneId);
		}
		return articledao.findDefArticle();
	}

	
	public int countArticle(){
		return articledao.countArticle();
	}
	
	public int countArticleToday(){
		return articledao.countArticleToday();	
	}
	
	//根据帖子id查询帖子
	public Article findArticleById(int articleId){
		
		Article article=articledao.findArticleById(articleId);
		//查询该帖子下的所有评论
//		List<Comment> comments=commentDao.findCommentByArticleId(articleId, order);
//		article.setComments(comments);
//		for (Comment comment : comments) {
////			 根据每一条评论查询对应的回复
//			List<Reply> replys = replyDao.findReplyByCommentId(comment.getCommentId());
//			comment.setReplys(replys);
//		}
		return article;
	}
	
	
	//在搜索栏搜索帖子
	public List<Article> findArticleBytext(String text){
		return articledao.findArticleBytext(text);
	}
	
	
	//发帖
	public void addArticle(String title,String content,String username,int zoneId){
		articledao.addArticle(title,content,username,zoneId);
	}
	
	

	//根据帖子id查询评论总数
	public int queryCountComByArt(int articleId){
		return articledao.queryCountComByArt(articleId);
	}
	
	//点赞帖子
	public int addUpvote(String userName,int articleId){
		//查找有没有存在已点赞操作
		Upvote upvote=articledao.queryUpvote(userName, articleId);
		System.out.println("-----------------"+upvote+"---------------------");
		if (null!=upvote) {
			//如果之前已点赞 则取消点赞
			articledao.updateUpvote(userName, articleId);
			articledao.downUpvoteCount(articleId);
			return 0;
		}else {
			articledao.addUpvote(userName, articleId);
			articledao.addUpvoteCount(articleId);
			return 1;
		}

	}
}
























