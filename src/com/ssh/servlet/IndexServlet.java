package com.ssh.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssh.dao.ArticleDao;
import com.ssh.dao.UserDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Upvote;
import com.ssh.pojo.User;
import com.ssh.pojo.Zone;
import com.ssh.service.ArticleService;
import com.ssh.service.UserService;
import com.ssh.service.ZoneService;


@Controller
@RequestMapping("to")
public class IndexServlet {
	
	@Autowired
	ArticleDao articledao;
	
	@Autowired
	ZoneService zoneService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	UserService userService;

	@RequestMapping("index.do")
	public String index(Model model,HttpServletRequest req) throws ServletException, IOException {
		
			//第一次进入首页的时候，zoneId为空。
			String zoneId=req.getParameter("zoneId");
//			System.out.println("zoneId = " + zoneId);
			
			
			List<Zone> zones = zoneService.Zones();
			
			//第一次进入首页的时候，查询默认区的文章
			List<Article> articles = articleService.findArticle(zoneId);
//			for (Article article : articles) {
//				System.out.println("帖子id："+article.getArticleId());
//				int commentCount=articleService.queryCountComByArt(article.getArticleId());
//				article.setReplyCount(commentCount);
//			}
			int totalOfArticle = articleService.countArticle();	
			int totalOfArticleToday = articleService.countArticleToday();


			//默认交流区
			model.addAttribute("zones", zones);
			
			model.addAttribute("articles", articles);		
			model.addAttribute("zoneId", zoneId);
				
			model.addAttribute("totalOfArticle", totalOfArticle);
			model.addAttribute("totalOfArticleToday", totalOfArticleToday);
			
			return "forward:/pages/index.jsp";
	}
	
	//前往注册页
	@RequestMapping("toRegister.do")
	public String toregister() throws ServletException, IOException {
			return "forward:/pages/register.jsp";
	}
	
	
	
	//登录
	@RequestMapping("login.do")
	public String login(@RequestParam("userName") String userName,@RequestParam("userPass") String pass,HttpServletRequest req,HttpServletResponse resp) throws IOException{

		
		
		//在登录表单有隐藏域 用于表面登录之前所在页面
		String redirectUrl = req.getParameter("redirectUrl");
		
		
		
		if (userService.userLogin(userName, pass)) {
			User user=userdao.userLogin(userName, pass);
			if (req.getParameter("articleId")!=null) {
				//查询该用户点赞状态
				int articleId=Integer.parseInt(req.getParameter("articleId"));
				Upvote upvote=articledao.queryUpvote(userName, articleId);
				if (null!=upvote) {
					req.getSession().setAttribute("upvoteStatus", 1);
				}
			}
			req.getSession().setAttribute("userName", userName);
			req.getSession().setAttribute("user", user);
			System.out.println("头像的url："+user.getPicUrl());
			
//			System.out.println("username:"+userName);
			return redirectUrl;
		}else {
			JOptionPane.showMessageDialog(null,"用户名或者密码错误！");
//			resp.setContentType("text/html;charset=utf-8");
//			PrintWriter writer = resp.getWriter();
//			writer.println("<script>");
//			writer.println(" alert('用户名或密码不正确');");
//			writer.println(" location = '/bbs/index';");
//			writer.println("</script>");
			return redirectUrl;
		}

	}
		
	//注销
	@RequestMapping("sessionInvalidate.do")
	public String sessionInvalidate(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.invalidate();
	
		return "redirect:/to/index.do";
	}
	
	
	//注册
	@RequestMapping("register.do")
	public String register(@RequestParam("userName") String userName,@RequestParam("userPass") String userPass,HttpServletRequest req){
		String email=req.getParameter("email");
		String mes=userService.register(userName, userPass, email);
		if ("注册成功".equals(mes)) {
			return "redirect:/to/index.do";
		}else {
			return "redirect:/pages/register.jsp";
		}
	}
	
	
	//前往用户信息页
	@RequestMapping("userInfo.do")
	public String userInfo(){
		return "forward:/pages/userInfo.jsp";
	}
	
	
	
	//修改用户信息+头像
	@RequestMapping("picUrlUp.do")
	public String fileUp(@RequestParam("picUrl") MultipartFile picUrl,@RequestParam("userId") int userId,HttpServletRequest req) throws IOException {
		InputStream input;
		String fileName;
		String pictureUrl=null;
		if (picUrl!=null||!"".equals(picUrl)) {
			//获取文件输入流
			input=picUrl.getInputStream();
			
			fileName=picUrl.getOriginalFilename();
			
			//输出流 将文件保存到哪
//			OutputStream out = new FileOutputStream("d:\\upload\\"+fileName);
//			/upload
			OutputStream out = new FileOutputStream("e:\\study\\tomcat\\apache-tomcat-8.5.47\\apache-tomcat-8.5.47\\webapps\\bbs\\upload\\"+fileName);
//			OutputStream out = new FileOutputStream("/upload/"+fileName);
			//缓冲区
			byte[] bs = new byte[1024];	
			int len=-1;
			while ((len=input.read(bs))!=-1) {
				out.write(bs, 0, len);
			}
			input.close();
			out.close();
			pictureUrl="/bbs/upload/"+fileName;
		}
	
		
//		String userName=req.getParameter("userName");
//		String email=req.getParameter("email");
		
		userService.updateUser(pictureUrl, userId);
		
		
		req.getSession().setAttribute("pictureUrl", pictureUrl);
		System.out.println("图片：-------------"+pictureUrl);
		
		
		//将文件上传到服务器某个硬盘中
	return "forward:/pages/userInfo.jsp";
		
	}
}
