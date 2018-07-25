<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<base href="<%=basePath%>">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>邯郸市公交查询系统-登录</title>
		<link rel="stylesheet" href="<%=basePath%>css/style.css">
		<link rel="shortcut icon" href="<%=basePath%>images/favicon.ico" />
	
	</head>

<body>

<div class="login-container">
	<h1>邯郸市公交查询系统</h1>
	
	<div class="connect">
		<p>Handan Bus Inquiry System</p>
	</div>
	
	<form  method="post" id="loginForm" onsubmit="return false;">
		<div>
			<input type="text" name="username" class="username" placeholder="用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<button id="submit" type="submit" id="loginBtn">登 录</button>
	</form>

	<a href="register.html">
		<button type="button" class="register-tis" >还有没有账号？</button>
	</a>

</div>
<%--  --%>
</body>
<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
<script src="<%=basePath%>js/loginUi.js"></script>
<!--背景图片自动更换-->
<script src="<%=basePath%>js/supersized.3.2.7.min.js"></script>
<script src="<%=basePath%>js/supersized-init.js"></script>
<!--表单验证-->
<script src="<%=basePath%>js/jquery.validate.min.js?var1.14.0"></script>
<script src="<%=basePath%>plugins/layui/layui.js"></script>


<script>

	function toIndex() {
		window.location.href="<%=basePath%>admin";
	}
	layui.use(['layer', 'form'], function() {
	var layer = layui.layer,
	$ = layui.jquery,
	form = layui.form();
	
	var ajaxUrl="<%=basePath%>admin/check";
	 
	$("#loginForm").submit(function (){
		var usernameVal=$(".username").val();
		var passwordVal=$(".password").val();
		$.post(ajaxUrl, {
			"username": usernameVal
			, "password": passwordVal
		}, function (data, status) {
			if(data=="1") {
				layer.msg("登录成功。", {icon: 6, anim: 0,offset: '100px'});
				setTimeout("toIndex()", 500);
				
			} else {
				layer.msg("密码错误", {icon: 5,anim: 0,offset: '100px'});
			}
		}); 
	});
	
});
</script> 

</html>