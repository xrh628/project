
<%@page import="java.util.List"%>
<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
</head>
<body>
	<%@include file="head.jsp"%>
	<div id="content" class="wrap">
		<div class="list bookList">
			<form method="post" name="shoping"
				action="<c:if test="${loginUser !=null }">fgwc.do</c:if><c:if test="${loginUser==null }">login.jsp</c:if>">
				<input type="hidden" name="pageNum" value="${page.pageNum }" /> <input
					type="hidden" name="bookName" value="${book.bookName }" />
				<table>
					<tr class="title">
						<th class="checker"></th>
						<th align="left">书名</th>
						<th class="price">价格</th>
						<th class="store">库存</th>
						<th class="view">图片预览</th>
					</tr>
					<c:forEach items="${page.list }" var="book" varStatus="vs">
						<tr <c:if test="${vs.index%2==0 }">  class="odd"  </c:if>>
							<td><input type="checkbox" name="bookId"
								value="${book.bookId }" onclick="quxiao(this)" /></td>
							<td class="title2">${book.bookName }</td>
							<td>￥<fmt:formatNumber pattern="0.00">${book.bookPrice }</fmt:formatNumber></td>
							<td><b>${book.bookSum }</b></td>
							<td class="thumb"><img src="images/book/${book.bookPic }" /></td>
						</tr>
					</c:forEach>
				</table>
				<div class="page-spliter">
					<c:if test="${page.pageNum>1 }">
						<a onclick="go( ${page.pageNum-1 })" href="#">&lt;</a>
					</c:if>
					<c:if test="${page.count>1 }">
						<a onclick="go( ${1 })" href="#">首页</a>
					</c:if>
					<c:forEach begin="1" end="${page.totalPage }" var="i">
						<c:if test="${page.pageNum==i }">
							<span class="current">${i}</span>
						</c:if>
						<c:if test="${page.pageNum != i }">
							<a href="#" onclick="go(${i })">${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${page.count>1 }">

						<a href="#" onclick="go(${page.totalPage})">尾页</a>
					</c:if>
					<c:if test="${page.pageNum<page.totalPage }">
						<a href="#" onclick="go(${page.pageNum+1 })">&gt;</a>
					</c:if>

				</div>
				<div class="button">
					<input class="input-btn" type="submit" value="" />
				</div>
			</form>

		</div>
	</div>

	<div id="footer" class="wrap">北大青鸟网上书城 &copy; 版权所有</div>
	<script type="text/javascript">
var arr=new Array();
<%List<String> bookIds = (List<String>) session.getAttribute("bookIds");
			if (bookIds != null && bookIds.size() != 0) {
				for (String bookId : bookIds) {%>
arr.push("<%=bookId%>");
<%}
			}%>

	var bookIds=document.getElementsByName("bookId");
	document.write(arr[0]+"<br>");
	document.write(bookIds[0].value);

	for(var i=0;i<arr.length;i++){
		for(var j=0;j<bookIds.length;j++){
			if(arr[i]==bookIds[j].value){
				bookIds[j].checked=true;
			}
		}
	}
	
	function quxiao(check){
		var bookId=check.value;
		if(check.checked==false){
			$.ajax({
				type:"post",
				url:"bmBook.do",
				data:{"bookId":bookId}
			});
		}
		if(check.checked==true){
			$.ajax({
				type:"get",
				url:"addBook2.do",
				data:{"bookId":bookId}
			});
		}
	}
	
function go(pageNum){
				document.forms[0].pageNum.value=pageNum;
				document.forms[0].submit();
}

</script>
</body>
</html>
