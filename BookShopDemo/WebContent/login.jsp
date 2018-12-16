<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#tijiao").click(function(){
		var un=$("#un").val();
		var pw=$("#pw").val();
		if(un==""){
			alert("用户名不能为空！");
			return ;
		}
		if(pw==""){
			alert("密码不能为空！");
			return ;
		}
				$.ajax({
					type:"post",
					url:"login.do",
					data:{un:un,pw:pw},
					success:function(result){
						if(result=="ok"){
							if($("#tz").val()==""){
							window.location="index.do";}
							else{
								window.location=$("#tz").val();
							}
						}else{
							$("#tishi").html("用户名或密码错误！");
						}
					},
					error:function(data){
						alert("error");
					}
				});
	});
	
	$("#un").focus(function(){
		$("#tishi").html("");
	});
	$("#pw").focus(function(){
		$("#tishi").html("");
	});
});
</script>
</head>
<body>
<input id="tz"  type="hidden"   value="${url2 }"/>

	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
		<div id="navbar"></div>
	</div>
	<div id="login">
		<h2>用户登陆</h2>
		<form name="login"  method="post"
			action=" ">
			<br />
			<div id="tishi"  style="text-indent: 5em; font-size: 15px; color: red;">${error }</div>
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input id="un"  class="input-text" type="text" name="username"
						value="${username }" />
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input id="pw" class="input-text" type="password" name="password"
						value="${password }" />
				</dd>
				<dt></dt>
				<dd class="button">
					<input id="tijiao"  class="input-btn" type="button"
						value="" /><input class="input-reg" type="button" value=""
						onclick="window.location='register.jsp';" />
				</dd>
			</dl>
		</form>
	</div>
	<div id="footer" class="wrap">北大青鸟网上书城 &copy; 版权所有</div>
</body>
</html>
