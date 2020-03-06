<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" />
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="../css/common.css" />
		<link rel="stylesheet" href="../css/common-new.css" />
    <link rel="stylesheet" href="../css/user_info.css" />
		<link rel="stylesheet" href="../css/search.css" />
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/hm-bbs.js"></script>
		<style type="text/css">
  		.hm-header-b {
  			border-bottom: 1px solid #d9d9d9;
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
						欢迎<a href="user_info.html" style="margin-right:0px;padding:0px 5px;color:blue;">${sessionScope.userName}</a>回来！
		                <a href="#">【注销】</a>
						<div id="dialog" class="animated">
						
							
						<!--	<form action="" method="post">
								<ul class="editInfos">
									<li>用户名：<input type="text" id="userName" name="userName"
										class="ipt" /></li>
									<li>密&nbsp;&nbsp;&nbsp;码：<input type="password"
										id="userPass" name="userPass" class="ipt" /></li>
									<li><input type="submit" value="登录" class="submitBtn" /></li>
								</ul>
							</form>-->
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
						<a href="/bbs/to/index.do">首页</a><span>></span>个人信息
				</div>
      </div>
    </div>
    <div class="hm-body hm-body-bgc">
        <div class="hm-inner"> 
            <div class="user-info clearfix">
            	<div class="user-info-t" style="height:20px;"></div> 
                <div class="user-info-l l">
                    <div class="user-info-l-t">
                        <img src="${user.picUrl}"/>
            		<!-- 	<img src="/bbs/upload/75591148_p0.jpg"> -->
            <!--           <img src="../upload/75591148_p0.jpg" />  -->  
                        <div class="username">${user.userName}</div>
                    </div>
                    <ul class="user-info-l-b">
                        <li class="cur"><i class="info-icon"></i>我的资料</li>
                        <li><i class="safe-icon"></i>修改密码</li>
                    </ul>
                </div>
                <div class="user-info-r r">
                    <ul class="clearfix hd">
                        <li class="cur"><a href="user_info.html">修改头像</a></li>
                        <li><a href="user_pwd.html">修改密码</a></li>
                    </ul>
                    <form action="/bbs/to/picUrlUp.do" method="post" enctype="multipart/form-data">
                    <ul class="bd">
                    
                    <!-- 
                  		<li class="clearfix">
                            <div class="info-l"><i class="red">*</i>用户名：</div>
                            <div class="info-r"><input type="text" name="userName" class="txt" value="${sessionScope.userName}" readonly="readonly"/></div>
                    -->  
                         
                    <!-- 
                        </li>
                        <li class="clearfix">
                            <div class="info-l">邮箱地址：</div>
                            <div class="info-r"><input type="text" name="email" class="txt" value="${user.email}"/></div>
                        </li>
                    -->
                        <li class="clearfix">
                            <div class="info-l">上传头像：</div>
                            <input type="hidden" name="userId" value="${user.userId}"/>
                            <div class="info-r"><input type="file" name="picUrl" class="file-btn"/></div>
                        </li>
                        
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                            	<input type="submit" class="btn" value="保存" />
                          <!--	<span style="color:red;">修改成功！</span>  -->  
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
					<p> 粤嵌科技版权所有</p>
				</div>
			</div>
		</div>
</body>
</html>