<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function go(pageNum){
	document.forms[0].pageNum.value=pageNum;
	document.forms[0].submit();
}
</script>
</head>
<body>

	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<li <c:if test="${flag==1 }">class="current"</c:if>><a
						href="index.do">${loginUser.username }首页</a></li>
					<li <c:if test="${flag==2 }">class="current"</c:if>><a
						href="xsdd.do?flag=find">我的订单</a></li>
					<li <c:if test="${flag==3 }">class="current"</c:if>><a
						href="<c:if test="${loginUser !=null }">fgwc2.do</c:if><c:if test="${loginUser==null }">login.jsp</c:if>">购物车</a></li>
					<li><c:if test="${loginUser == null }">
							<a href="login.jsp">登陆</a>
						</c:if> <c:if test="${loginUser != null }">
							<a href="zhuxiao.do">注销</a>
						</c:if></li>
					<li <c:if test="${flag==4 }">class="current"</c:if>><a
						href="register.jsp">注册</a></li>
					<li>&nbsp;&nbsp; &nbsp; &nbsp;</li>
					<li><c:if test="${loginUser == null }">您未登陆!</c:if> <c:if
							test="${loginUser != null }">您已登陆!</c:if></li>
				</ul>

			</div>
			<form method="post" name="search" action="xsdd.do">
				<input type="hidden" name="flag" value="${page.flag }" /> <input
					type="hidden" name="userId"
					value="${sessionScope.loginUser.userId}" /> <input type="hidden"
					name="pageNum" value="1" /> 搜索：<input class="input-text"
					type="text" name="bookName" value="${bookName }" /> <input
					class="input-btn" type="submit" value="" />
			</form>
		</div>
	</div>
	<div id="content" class="wrap">
		<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th>订单商品</th>
					<th>商品名称</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
				</tr>
				<c:forEach items="${page.list }" var="order" varStatus="vs">
					<tr <c:if test="${vs.index%2==0 }" >class="odd"</c:if>>
						<td>${order.orderId }</td>
						<td width="210px" class="thumb"><c:forEach
								items="${order.details }" var="detail">
								<img src="images/book/${detail.book.bookPic }" />
							</c:forEach></td>
						<td class="title"><b><c:forEach items="${order.details}"
									var="detail">${detail.book.bookName }&nbsp;&nbsp;</c:forEach></b></td>
						<td>${order.user.username }</td>
						<td>￥<fmt:formatNumber pattern="0.00">${order.sumPrice } </fmt:formatNumber></td>
						<td width="150px"><fmt:formatDate
								pattern="yyyy-MM-dd HH:mm:ss" value="${order.orderDate }" /></td>
						<td width="200px"><c:if test="${order.state==0 }">
								<a href="qsdd.do?orderId=${order.orderId }&pageNum=${page.pageNum}"  onclick="return confirm('确定吗？')">
									未完成,点此完成。 </a>
							</c:if> <c:if test="${order.state != 0 }">已完成</c:if></td>

					</tr>
				</c:forEach>

			</table>

			<div class="page-spliter">
				<c:if test="${page.pageNum>1 }">
					<a onclick="go(${page.pageNum-1})" href="#">&lt;</a>
				</c:if>
				<c:if test="${page.count>1 }">
					<a onclick="go(1)" href="#">首页</a>
				</c:if>
				<c:forEach begin="1" end="${page.totalPage }" var="i">
					<c:if test="${page.pageNum==i }">
						<span class="current">${i }</span>
					</c:if>
					<c:if test="${page.pageNum != i }">
						<a href="#" onclick="go(${i})">${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${page.count>1 }">

					<a href="#" onclick="go(${page.totalPage})">尾页</a>
				</c:if>
				<c:if test="${page.pageNum<page.totalPage }">
					<a href="#" onclick="go(${page.pageNum+1})">&gt;</a>
				</c:if>
			</div>
			<div class="button">
				<a href="IM.do?pageNum=1"><input class="input-gray"
					type="button" value="查看一个月内的订单" /></a> <a href="BM.do?pageNum=1"><input
					class="input-gray" type="button" value="查看一个月之前的订单" /></a>
			</div>
		</div>
	</div>
	<div id="footer" class="wrap">北大青鸟网上书城 &copy; 版权所有</div>
</body>
</html>
