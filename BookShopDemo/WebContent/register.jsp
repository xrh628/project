<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />

  <script src="js/bdyz2.js" >  </script>
<script>
  function check(){
	  checkName()&&checkPsw()&&checkPsw2()&&checkEmail();
	  if((checkName()&&checkPsw()&&checkPsw2()&&checkEmail())==true){
        document.forms[1].submit();}
  }
  </script>
</head>
<body>
	<%@include file="head.jsp"%>
<div id="register">
	<div class="title">
		<h2>欢迎注册北大青鸟网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form method="post" action="insert.do" >
		<dl>
	
			<dt>用 户 名：</dt>
			<dd><input id="userName" class="input-text" type="text" name="username"   onblur="checkName()"/><span  id="userMsg"></span>(可包含a-z、0-9和下划线)</dd>
			<dt>密　　码：</dt>
			<dd><input id="psw" class="input-text" type="password" name="password" onblur="checkPsw()" /><span id="pswMsg"></span>(至少包含6个字符)</dd>
			<dt>确认密码：</dt>
			<dd><input id="psw2"  class="input-text" type="password" name="rePassWord"  onblur="checkPsw2()" /><span id="psw2Msg"></span></dd>
			<dt>Email地址：</dt>
			<dd><input id="email"  class="input-text" type="text" name="email" onblur="checkEmail()" /><span  id="emailMsg"></span>(请输入正确的电子邮箱地址)</dd>
			<dt></dt>
			<dd></dd>
			<dt></dt>
			 <dd class="button"><input class="input-reg" type="button"  onclick="check()"  name="register" value="" /></dd>
		</dl>
		
	</form>
</div>
<div id="footer" class="wrap">
	北大青鸟网上书城 &copy; 版权所有
</div>
</body>
</html>
