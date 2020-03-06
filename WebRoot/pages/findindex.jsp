<%@ page language="java" import="java.util.List,com.ssh.pojo.Zone" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8" />
	<title>王者荣耀论坛</title>
	<link rel="stylesheet" href="../css/common.css" />
	<link rel="stylesheet" href="../css/common-new.css" />
	<link rel="stylesheet" href="../css/index.css" />
	<link rel="stylesheet" href="../css/search.css" />
	<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../js/hm-bbs.js"></script>
	<style>
		body {
	    background: url(../images/bg.jpg) no-repeat;
		    background-position: center top;
		    height: 100%;
		}
		.hm-bbs-info {
			position: relative;
		}
		.search-box {
			width: 400px;
			position: absolute;
			right:15px;
			top:30px;
		}
		.search-box .txt {
			width: 380px;
		}
		.hm-header {
		    height: 350px;
		}
		.fixedBar {
			bottom:173px
		}	
	</style>	
</head>
<body>
	<!-- 头部 -->
	<div class="hm-top-nav">
		<div class="hm-inner clearfix">
			<div class="hm-inner-l l">
			</div>
			<div class="hm-inner-r r">
				<div class="box">
					<c:if test="${sessionScope.userName == null}">
						<a href="javascript:;" id="login" class="to-login">游客登录</a>
						<a href="/bbs/to/toRegister.do">【新用户注册】</a>
					</c:if>
					<c:if test="${sessionScope.userName != null}">
						欢迎${sessionScope.userName}回来了，<a href="/bbs/to/sessionInvalidate.do">【注销】</a>
					</c:if>
					<div id="dialogBg"></div>
					<div id="dialog" class="animated">
						<img class="dialogIco" width="50" height="40" src="../images/ico.png" />
						<div class="dialogTop" style="height:25px;">
							<a href="javascript:;" class="closeDialogBtn">关闭</a>
						</div>
						<form action="/bbs/to/login.do" method="post">
						<input type="hidden" name="redirectUrl" value="redirect:/to/index.do"/>
							<ul class="editInfos">
								<li>用户名：<input type="text" id="userName" name="userName"
									class="ipt" /></li>
								<li>密&nbsp;&nbsp;&nbsp;码：<input type="password"
									id="userPass" name="userPass" class="ipt" /></li>
								<li><input type="submit" value="登录" class="submitBtn" /></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Banner部分 -->
	<div class="hm-header">
	</div>
	<div class="hm-body hm-body-bgc">
		<div class="hm-inner">
			<div class="hm-banner">
			</div>
			<div class="hm-bbs-info">
				<div class="hm-bbs-icon l" style="width:130px;">
					<span><img src="../images/bbs-icon.png" height="80" /></span>
				</div>
				<div class="hm-bbs-info-in l" style="margin-left:30px;">
					<div class="t clearfix">
						<h2 class="l">王者荣耀</h2>
					</div>
					<p>
						<span>今日帖子<strong>${totalOfArticleToday}</strong></span> <span>全部帖子<strong>${totalOfArticle}</strong></span>
					</p>
				</div>
				<div class="search-box l">
						<form action="/bbs/article/findArticleBytext.do" method="post">
								<input type="text" class="txt l" placeholder="请输入关键字" name="text">
								<input type="submit" value="搜索" class="btn l" /> 
						</form>
				</div> 
			</div>
			<!-- 导航 -->
			<ul class="hm-bbs-nav border-lrb clearfix">
				<!-- 循环遍历集合 -->
            	搜索结果：${textResult}
			</ul>
			<!-- 主体部分 -->
			<div class="hm-bbs-main border-lrb clearfix">
				<!-- 左侧列表 -->
				<div class="list-view l">
					<ul>
						<c:forEach items="${findList}" var="findList">
							<li class="clearfix <c:if test='${findList.isTop == 1}'>ding</c:if>">
								<div class="hm-index-title">
									<c:if test="${findList.isTop == 1}"><i class="set-to-top">顶</i></c:if><a href="detail.html">${findList.title}</a>
								</div>
								<div class="hm-index-con">${findList.content}</div>
								<div class="hm-index-info l">
									<span class="article-username">${findList.senderName}</span><span
										class="post-time"><fmt:formatDate value="${findList.sendTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
								</div>
								<div class="hm-index-fun r">
									<span class="icon-like"><i></i>${findList.upvoteCount}</span>
									<span class="icon-talk"><i></i>${findList.replyCount}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<!-- 右侧侧边栏 -->
				<div class="aside l">
					<div class="aside-box">
						<h3 class="t">
							<a ahref="javascript:;">在线用户(2)</a>
						</h3>
						<ul class="b clearfix">
							<li>
								<div>
										<img src="../images/default.png" height="55" />
								</div>
								<p>Mr.King</p>
							</li>
							<li>
								<div>
										<img src="../images/default.png" height="55" />
								</div>
								<p>疯子</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->	
	<div class="hm-footer" style="padding-top:10px;">
		<div class="hm-inner">
			<div class="hm-footer-cpr">
				<p>Copyright@2006-2017 GEC. All Rights Reserved</p>
				<p>粤嵌科技 版权所有</p>
			</div>
		</div>
	</div>

	<div class="fixedBar" id="j_fixedBar">
		<a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
		<a href="#" class="goTop"><i></i><span>返回<br />顶部</span></a>
	</div>
	
	<!-- 发帖弹出框 -->
	<form action="/bbs/article?method=addArticle" method="post" onSubmit="return check()">
		<div class="pop-box ft-box">
			<div class="mask"></div>
			<div class="win">
				<div class="win_hd">
					<h4 class="l">主题帖</h4>
					<span class="close r">&times;</span>
				</div>
				<div class="win_bd">
					<div class="win_bd_t">
						<input type="text" id="title" name="title" placeholder="帖子标题" />
					</div>
					<div class="win_bd_b">
						<textarea id="content" name="content" placeholder="正文"></textarea>
					</div>
				</div>
				<div class="win_ft">
					<div class="win_ft_in">
						<input type="submit" class="btn" value="发表"/>
						<input type="hidden" name="zoneId" value="${zoneId}"/>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
<script type="text/javascript">

	// 发帖前的验证
	function check() {
		var title = $("#title").val();
		var content = $("#content").val();
		
		
		if (title.trim() == '') {
			alert('标题不能够为空！');
			return false;
		}
		
		if (content.trim() == '') {
			alert('正文不能够为空！');
			return false;
		}
		
		if (content.trim().length < 10) {
			alert('正文内容不能够少于10个字！');
			return false;
		}
	}

	var w,h,className;
	function getSrceenWH(){
		w = $(window).width();
		h = $(window).height();
		$('#dialogBg').width(w).height(h);
	}
	
	window.onresize = function(){  
		getSrceenWH();
	}  
	$(window).resize();  
	
	$(function(){
		getSrceenWH();
		
		//显示弹框
		$('.box #login').click(function(){
			className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('#userName').focus();
			$("#j_fixedBar").hide();
		});
		
		//关闭弹窗
		$('.closeDialogBtn').click(function(){
			$('#dialogBg').fadeOut(300,function(){
				$('#dialog').addClass('bounceOutUp').fadeOut();
				$("#j_fixedBar").show();
			});
		});
	});
</script>