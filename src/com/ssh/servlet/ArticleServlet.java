package com.ssh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssh.dao.ArticleDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Comment;
import com.ssh.pojo.Reply;
import com.ssh.pojo.Upvote;
import com.ssh.pojo.Zone;
import com.ssh.service.ArticleService;
import com.ssh.service.CommentService;
import com.ssh.service.ReplyService;
import com.ssh.service.ZoneService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("article")
public class ArticleServlet {

	@Autowired
	ArticleDao articledao;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ZoneService zoneService;
	
	@Autowired
	ReplyService replyService;
	
	
	//进入帖子详情页
	@RequestMapping("articltDetail.do")
	public String articltDetail(@RequestParam("articleId") int articleId,HttpServletRequest req,Model model){
//		int isUpvote;
		String order=req.getParameter("order");
		Article article=articleService.findArticleById(articleId);
		List<Comment> comments=commentService.findComByArticleId(articleId,order);
		
		String userName=(String) req.getSession().getAttribute("userName");
		Upvote upvote=articledao.queryUpvote(userName, articleId);
		if (null!=upvote) {
			req.getSession().setAttribute("upvoteStatus", 1);
		}
		
		System.out.println(article);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		model.addAttribute("order",order);
//		req.getSession().setAttribute("upvote",upvote);
//		System.out.println("点赞？？？："+upvote);
		return "forward:/pages/detail.jsp";
	}
	
	//搜索栏搜帖子
	@RequestMapping("findArticleBytext.do")
	public String findArticleBytext(@RequestParam("text") String text,Model model){
		List<Article> articles=articleService.findArticleBytext(text);
		model.addAttribute("findList",articles);
		model.addAttribute("textResult", text);
		return "forward:/pages/findindex.jsp";
	}
	
	//发帖
	@RequestMapping("addArticle.do")
	public String addArticle(HttpServletRequest req,HttpServletResponse resp,@RequestParam("zoneId") Integer zoneId,@RequestParam("title") String title,@RequestParam("content") String content,Model model){
		
			// 验证是否已经登录了，如果没有登录则提示用户，不做发帖的操作
			String o =(String) req.getSession().getAttribute("userName");
//			System.out.println("打印一下用户名"+o);
			if (o == null) {
//				PrintWriter writer = resp.getWriter();
//				writer.println("<script>");
//				writer.println("	alert('请先登录');");
//				writer.println("</script>");
				JOptionPane.showMessageDialog(null, "请先登录");
				return "redirect:/to/index.do";
			} else {
				if (null==zoneId) {
					// 获取默认交流区的id
					Zone zone = zoneService.getDefaultZone();
					zoneId = zone.getZoneId();
					System.out.println("打印一下区号"+zoneId);
				}
				
				articleService.addArticle(title, content,o, zoneId);
				
//				PrintWriter writer = resp.getWriter();
//				writer.println("<script>");
//				writer.println("	alert('发帖成功');");
//				writer.println("	location = '/bbs/index?zoneId=' + " + zoneId); // 返回上次的页面
//				writer.println("</script>");
				JOptionPane.showMessageDialog(null, "发帖成功");
				return "redirect:/to/index.do?zoneId="+zoneId;
			}
		} 
	

	//点赞
	@ResponseBody
	@RequestMapping("upvote.do")
	public Map<String,Object> upvote(@RequestParam("articleId") int articleId,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		int upvoteStatus=-1;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Object o = req.getSession().getAttribute("userName");
		if (o == null) {
			JOptionPane.showMessageDialog(null, "请先登录");
		} else {
			String userName = (String) o;
			//点赞情况    1：点击后成功点赞  返回标记变为满心     0：未点赞
			upvoteStatus = articleService.addUpvote(userName, articleId);
			req.getSession().setAttribute("upvoteStatus", upvoteStatus);
			//返回该帖子新的点赞数
			Article article=articleService.findArticleById(articleId);
			resultMap.put("upvoteStatus", upvoteStatus);
			resultMap.put("upvoteCount", article.getUpvoteCount());
			
			System.out.println("点赞数："+article.getUpvoteCount());
		}
		return resultMap;
	}
	
	
	
	
	
}
