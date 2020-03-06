<%@ page language="java" import="java.util.List,com.ssh.pojo.Zone" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="../css/common.css" />
		<link rel="stylesheet" href="../css/common-new.css" />
    <link rel="stylesheet" href="../css/search.css" />
    <link rel="stylesheet" href="../css/register.css" />
		<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
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
						<a href="/bbs/to/register.do">【新用户注册】</a>
						</c:if>
						<c:if test="${sessionScope.userName != null}">
							欢迎${sessionScope.userName}回来了，<a href="/bbs/to/sessionInvalidate.do">【注销】</a><a href="/bbs/to/toRegister.do">【新用户注册】</a>
						</c:if>
						<div id="dialogBg"></div>
						<div id="dialog" class="animated">
							<img class="dialogIco" width="50" height="40" src="../images/ico.png" />
							<div class="dialogTop" style="height:25px;">
								<a href="javascript:;" class="closeDialogBtn">关闭</a>
							</div>
							<!-- <form action="" method="post">
								<ul class="editInfos">
									<li>用户名：<input type="text" id="userName" name="userName"
										class="ipt" /></li>
									<li>密&nbsp;&nbsp;&nbsp;码：<input type="password"
										id="userPass" name="userPass" class="ipt" /></li>
									<li><input type="submit" value="登录" class="submitBtn" /></li>
								</ul>
							</form> -->
						</div>
					</div>
				</div>
			</div>
		</div>
    <div class="hm-header">
        <div class="hm-inner clearfix">
            <div class="hm-header-t clearfix">
                <h1 class="logo l">
                    <a href="javascript:;"><img src="../images/logo.png" 
                    	height="64" width="168" alt="" /></a>    
                </h1>
                <div class="search-box l">
                    <form action="javascript:;">
                        <input type="text" class="txt l" placeholder="请输入关键字">
                        <input type="button" value="搜索" class="btn l" />
                    </form>
                </div>    
            </div>
            <div class="hm-header-b">
                <i class="hm-ico-home"></i>
                <a href="index.html">首页</a><span>></span>注册页面
            </div>
        </div>
    </div>
    <div class="hm-body hm-body-bgc">
        <div class="hm-inner">
            <div class="reg-box">
                <h2>用户注册<span>（红色型号代表必填）</span></h2>
                <div class="reg-info">
                    <form action="/bbs/to/register.do" method="post" onsubmit="return check()">
                        <ul>
                            <li>
                                <div class="reg-l"><span class="red">*</span> 用户名：</div>
                                <div class="reg-c">
                                    <input type="text" id="userName" name="userName" class="txt" value="${userName}"/>
                                </div>
                                <span class="tips">用户名必须是由英文、数字、下划线组成</span>
                            </li>
                             <li>
                                <div class="reg-l"><span class="red">*</span> 密&nbsp;&nbsp;&nbsp;码：</div>
                                <div class="reg-c">
                                    <input type="password" id="userPass" name="userPass" class="txt" value="${userPass}"/>
                                </div>
                                <span class="tips">密码长度必须6~10位的英文或数字</span>
                            </li>
                            <li class="no-tips">
                                <div class="reg-l">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;箱：</div> 
                                <div class="reg-c"><input type="text" id="email" name="email" class="txt" value="${email}"/></div>
                            </li>
                            <li>
                                <div class="reg-l"></div>
                                <div class="reg-c">
                                    <input type="submit" class="submit-btn" value="注册" /><br/>
                                    <span style="color:red;">${msg}</span>
                                </div>
                            </li>
                        </ul>
                    </form>    
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
</body>
</html>
<script type="text/javascript">

	// 表单校验
	function check() {
		// 获取用户名
		var userName = $("#userName").val();
		// 获取密码
		var userPass = $("#userPass").val();
		// 获取邮箱
		var email = $("#email").val();
		
		// 验证它们
		if (!(/^\w+$/i.test(userName))) { // 正版表达式
			alert("用户名格式不正确！");
			return false;
		}
		
		if (!(/^[a-z0-9]{6,10}$/i.test(userPass))) { // 正版表达式
			alert("用户密码的格式不正确！");
			return false;
		}
		
		if (email != '') {
			if (!(/^([a-z]|[0-9])(\w|\-)+@[a-z0-9]+\.([a-z]{2,4})$/i.test(email))) { // 正版表达式
				alert("邮箱的格式不正确！");
				return false;
			}
		}
		return true;	
			
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