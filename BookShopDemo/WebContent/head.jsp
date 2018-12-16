<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<li <c:if test="${flag==1 }"> class="current"</c:if>><a href="index.do">${loginUser.username }首页</a></li>
					<li <c:if test="${flag==2 }"> class="current"</c:if>><a
						href="xsdd.do?flag=find">我的订单</a></li>
					<li <c:if test="${flag==3 }"> class="current"</c:if>><a
						href="fgwc2.do">购物车</a></li>
					<li><c:if test="${loginUser == null }">
							<a href="login.jsp">登陆</a>
						</c:if> <c:if test="${loginUser != null }">
							<a href="zhuxiao.do">注销</a>
						</c:if>
					<li <c:if test="${flag==4 }">class="current"</c:if >><a href="zhuce.do">注册</a></li>
					<li>&nbsp;&nbsp; &nbsp; &nbsp;</li>
					<li><c:if test="${loginUser == null }">您未登陆!</c:if> <c:if
							test="${loginUser != null }">您已登陆!</c:if></li>
				</ul>

			</div>
			<form method="post" name="search" action="index.do">
				<input type="hidden" name="pageNum" value="1" /> 搜索：<input
					class="input-text" type="text" name="bookName"
					value="${book.bookName }" /> <input class="input-btn"
					type="submit" value="" />
			</form>
		</div>
	</div>
</body>
</html>