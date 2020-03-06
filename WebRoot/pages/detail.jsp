<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css" />
		<link rel="stylesheet" type="text/css" href="../css/common-new.css" />
		<link rel="stylesheet" type="text/css" href="../css/index.css" />
		<link rel="stylesheet" type="text/css" href="../css/search.css" />
    <link rel="stylesheet" type="text/css" href="../css/detail.css" />
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
			background-color: none;
		}
		.search-box .txt {
			width: 380px;
			height: 40px;			
		}		
		.hm-header {
		    height: 350px;
		}
		.hm-bbs-info-in h2 {		   
		    height: 100px;
		    line-height: 142px;
		}
		.hm-detail-fun {
		    height: 100px;
		    line-height: 142px;
		   margin-top: 0px;
		}
		.new-to-old {
			margin:0;
		}
		.fixedBar {
			bottom:173px
		}
		.detail-page-box a, .detail-page-box span {
			border:none;
			background:none;
			padding: 0 2px;
			font-size: 15px;
			margin:0px;
		}
		.detail-page-box .new-to-old{
			border: 1px solid #d9d9d9;
		}
		.detail-page-box {
			border:none;
			padding: 9px 15px 9px 20px;
		}
		.replyBtn {
			width:60px;
			height:30px;
			line-height:30px;
			font-family:"微软雅黑","microsoft yahei";
			cursor:pointer;
			margin-top:10px;
			display:inline-block;
			border-radius:5px;
			-webkit-border-radius:5px;
			text-align:center;
			background-color:#00c400;
			color:#fff;
			box-shadow: 0 -3px 0 #2a6496 inset;
			-webkit-box-shadow: 0 -3px 0 #2a6496 inset;
			float:right
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
							<input type="hidden" name="articleId" value="${article.articleId}"/>
							<input type="hidden" name="redirectUrl" value="redirect:/article/articltDetail.do?articleId=${article.articleId}"/>
							<ul class="editInfos">
								<li>用户名：<input type="text" id="userName" name="userName"
									class="ipt" /></li>
								<li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt" /></li>
								<li><input type="submit" value="登录" class="submitBtn" /></li>
							</ul>
						</form>
					</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="hm-header">
    </div>
    <div class="hm-body hm-body-bgc">
        <div class="hm-inner">
        	<div class="hm-bbs-info">
				<div class="hm-bbs-icon l" style="width:130px;">
					<span><img src="../images/bbs-icon.png"
						height="80" /></span>
				</div>
				<div class="hm-bbs-info-in l" style="margin-left:30px;">
					<div class="t clearfix">
						<h2 class="l">${article.title}</h2>
						<div class="hm-detail-fun l" >
								<!-- 赞 -->
								<c:if test="${sessionScope.upvoteStatus == 1}">
									<span class="icon-liked" id="upvoteDiv"><a href="javascript:upvote()"><i></i><a id="upvoteCount">${article.upvoteCount}</a></a>
									</span>
            					</c:if>
								<c:if test="${sessionScope.upvoteStatus != 1}">
									<span class="icon-like" id="upvoteDiv"><a href="javascript:upvote()"><i></i><a id="upvoteCount">${article.upvoteCount}</a></a>
									</span>
            					</c:if>
            					<span class="icon-talk">
										<i></i>${article.replyCount}
								</span>
            					
						</div>
					</div>					
				</div>
				<div class="search-box l">
						<form action="javascript:;">
								<input type="text" class="txt l" placeholder="请输入关键字">
								<input type="button" value="搜索" class="btn l" /> 
						</form>
				</div> 
			</div>
			
			
			
			
            <div class="detail-page-box clearfix">
            	
            	<a href="/bbs/to/index.do"><i class="hm-ico-home"></i>首页</a><span>></span><a href="#">${article.title}</a>
            	
            	<c:if test="${order == 'asc'}">
            		<a class="old-to-new r" href="/bbs/article/articltDetail.do?articleId=${article.articleId}&order=desc" style="font-size:12px;float: right; border:1px solid #ccc">
	               <i></i>从旧到新查看 </a>
            	</c:if>
            	<c:if test="${order == 'desc'}">
            		<a class="new-to-old r" href="/bbs/article/articltDetail.do?articleId=${article.articleId}&order=asc" style="font-size:12px;float: right;">
	               <i></i>从新到旧查看 </a>
            	</c:if>
            </div>
            
            
            
            
            <div class="detail-box">
                <ul class="detail-floors">
                    <li class="floor clearfix">
                        <div class="floorer-info l">
                            <div class="floorer-photo">
								<img src="../images/default.png" />
                            </div>
                            <div class="floorer-name">${article.senderName}</div>
                        </div>
                        <div class="floor-con l">
                            <div class="floor-info clearfix">
                                <div class="floor-time l">发帖时间：<fmt:formatDate value="${article.sendTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></div>                                
                                <div class="r">${article.browseCount}次查看</div>
                            </div>
                            <div class="floor-art-ans">
                                <div class="floor-art">
                                    <p>
                                    	${article.content}
                                    </p>
                                </div>
                                <div class="floor-ans"></div>
                            </div>
                            <span class="icon-comment">
                            	<a href="#comment"> <i ></i> 评论</a> 
                            </span>
                        </div>
                    </li>
                   
                  <!-- 评论部分 -->
                    <c:forEach items="${comments}" var="comment" varStatus="st">
						<li class="floor clearfix">
								<div class="floorer-info l">
										<div class="floorer-photo">
											<img src="../images/default.png" />
										</div>
										<div class="floorer-name">
											${comment.commentUserName}
										</div>
								</div>
								<div class="floor-con l">
										<div class="floor-info clearfix">
												<div class="floor-time l">回贴时间：<fmt:formatDate value="${comment.commentTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></div>
												<div class="r">${st.count}楼</div>
										</div>
										<div class="floor-art-ans">
												<div class="floor-art">
														<p>${comment.commentContent}</p>
												</div>
												<div class="floor-ans">
													<ul>
														<!-- 回复部分 -->
														<c:forEach items="${comment.replys}" var="reply">
															<li class="clearfix">
																<div class="floor-ans-pho l"><img src="../images/default.png"/></div>
																<div class="floor-ans-con l">
																		<span class="name">${reply.replyUserName}</span>：${reply.replyContent} 
																		<span class="ans-time"><fmt:formatDate value="${reply.replyTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
																</div>
															</li>
														</c:forEach>
													</ul>
												</div>
												
										<form action="/bbs/reply/addReply.do?commentId=${comment.commentId}" method="post" >
											<div class="win_bd_b">
												<textarea id="replyContent${comment.commentId}" name="replyContent" placeholder="回复内容限于40字以内"></textarea>
											</div>
												<input type="hidden" name="articleId" value="${article.articleId}"/>
											<div class="win_bd_b">
												<input type="submit" value="回复" class="replyBtn" onclick="return checkContent(${comment.commentId})"/>			
											</div>													
										</form>								
									</div>
								</div>
						</li>
						
					</c:forEach>	   				
                </ul>
            </div>
            <div class="detail-to-comment">
                <div class="tit"><a name="comment">发表评论</a></div>
								
								
                <!-- 登录后显示评论输入框-->
				<form action="/bbs/comment/addComment.do" method="post" onSubmit="return check()">
					<div class="con con-loged">
						<div class="con-t">
							<textarea id="content" name="content" placeholder="请在此输入您要回复的信息"></textarea>
						</div>
						<div class="con-b">
							<input type="submit" class="btn" />
							<input type="hidden" name="articleId" value="${article.articleId}"/>
							<span class="num">不能超过5000字</span>
						</div>
					</div>
				</form>
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

<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------  -->
 	<!-- 回复弹出框 -->    
		
	
	
	<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
	<div class="fixedBar" id="j_fixedBar">
			<a href="#comment" class="newTopic"><span></span>回复</a>
			<a href="#" class="goTop"><i></i><span>返回<br />顶部</span></a>
	</div>
</body>
</html>


<script type="text/javascript">

	// 点赞
	function upvote() {
		//ajax 固定形式1：url请求地址  2.你请求是否带参数    3.服务端处理完毕后的回调函数（服务端会返回值
		$.post("/bbs/article/upvote.do",{"articleId":${article.articleId}},function(result) {
			//1代表点赞，0代表取消点赞, 2代表未登录
			$("#upvoteCount").text(result.upvoteCount);
			if (result.upvoteStatus == 1) {
				$("#upvoteDiv").attr('class', 'icon-liked');
			} else if (result.upvoteStatus == 0) {
				$("#upvoteDiv").attr('class', 'icon-like');
			} else {
				alert(data);
			}
		});
	} 

	
	// 发表评论的验证
	function check() {
		var content = $("#content").val();
		if (content.trim().length == 0) {
			alert('评论内容不能为空');
			return false;
		}
		if (content.trim().length > 5000) {
			alert('评论内容过长，不能够超过5000字符');
			return false;
		}
	}
	
	// 回复的验证												
	function checkReply() {
			var replyContent = $("#replyContent").val();
			alert(replyContent.trim().length);
			if (replyContent.trim().length == 0) {
				alert('回复内容不能为空');
				return false;
			}
			if (replyContent.trim().length > 5) {
				alert('评论内容过长，不能够超过5字符');
				return false;
			}
		}
	function checkContent(commentId){
		//var a = "#replyContent"+articleId+"";
	//alert(a);
	//	var replyContent = document.getElementById(a).value();
		 var replyContent =$("#replyContent"+commentId+"").val();  
	//	alert(replyContent.trim().length);
		if (replyContent.trim().length == 0) {
			alert('回复内容不能为空');
			return false;
		}
		if (replyContent.trim().length > 5) {
			alert('回复内容过长，不能够超过5字符');
			return false;
		} 

	}
	//弹出回复框
	function showDialog(num) {
		$('.pop-box').css('display','block');
		$("#floorSpan").html(num);	
			
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