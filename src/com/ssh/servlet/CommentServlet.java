package com.ssh.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssh.pojo.Article;
import com.ssh.service.ArticleService;
import com.ssh.service.CommentService;

@Controller
@RequestMapping("comment")
public class CommentServlet {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
//	发表评论
	@RequestMapping("addComment.do")
	public String addComment(@RequestParam("articleId") int articleId,@RequestParam("content") String commentContent,HttpServletRequest req){
		// 验证是否已经登录了，如果没有登录则提示用户，不做发帖的操作
		String commentUserName =(String) req.getSession().getAttribute("userName");
		if (null==commentUserName) {
			JOptionPane.showMessageDialog(null, "请先登录");
		}else {
			Article article=articleService.findArticleById(articleId);
			System.out.println("帖子信息"+article);
			commentService.addComment(article, commentContent, commentUserName);
			JOptionPane.showMessageDialog(null, "评论成功");
		}
		//评论无论成功还是失败 都根据帖子id重新访问一遍帖子页
		return "redirect:/article/articltDetail.do?articleId="+articleId;
	}

}
