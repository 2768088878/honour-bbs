package com.ssh.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;
import com.ssh.service.CommentService;
import com.ssh.service.ReplyService;

@Controller
@RequestMapping("reply")
public class ReplyServlet {

	@Autowired
	CommentService commentService;
	
	@Autowired
	ReplyService replyService;
	
	

	
	//增加回复
	@RequestMapping("addReply.do")
	public String addReply(@RequestParam("commentId") int commentId,@RequestParam("articleId") int articleId,@RequestParam("replyContent") String replyContent,HttpServletRequest req){
		
		// 验证是否已经登录了，如果没有登录则提示用户，不做发帖的操作
		String replyUserName =(String) req.getSession().getAttribute("userName");
		if (null==replyUserName) {
			JOptionPane.showMessageDialog(null, "请先登录");
		}else {
			System.out.println("测试有没有获取到评论id："+commentId);
			System.out.println("测试有没有获取到帖子id："+articleId);
			//回复需要获取评论信息
			Comment comment=commentService.findCommentById(commentId);
			replyService.addReply(comment, replyUserName, replyContent);
			JOptionPane.showMessageDialog(null, "评论成功");
		}
		
		return "redirect:/article/articltDetail.do?articleId="+articleId;
	}
	
	
}
