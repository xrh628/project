<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

 $(function(){
   $("input[name='bookNum']").blur(function(){
	   var checkNum = /^[0-9]\d*$/; 
	   var x= $("input[name='bookNum']");
      for(var i=0;i<x.length;i++ ){
    	  if(!(checkNum.test(x[i].value))){
    		   alert("请填入正确格式的书本数量");
    		  return false;
    	  }
    	  if(x[i].value>$("#"+i).val()){
    		  alert("请注意库存！")
    	  }
      }
	   document.forms[1].action="fgwc3.do";
       document.forms[1].submit();
   });

	   $("input[name='tijiao']").click(function(){
		   var checkNum = /^[0-9]\d*$/; 
		   var x= $("input[name='bookNum']");
	      for(var i=0;i<x.length;i++ ){
	    	  if(!(checkNum.test(x[i].value))){
	    		   alert("请填入正确格式的商品数量");
	    		  return false;
	    	  }
	      }
		 return true;
	   });
	 });
	 
 
</script>
</head>
<body>
	<%@include file="head.jsp"%>
	<div id="content" class="wrap">
		<div class="list bookList">
			<form method="post"  name="shopping"   action="scdd.do">
		       
				<table>
					<tr class="title">
						<th class="view" align="left">图片预览</th>
						<th align="center"  width="120px">书名</th>
					
						<th>库存</th>
						<th class="nums">数量</th>
						<th class="price">价格</th>
					</tr>
					<c:forEach items="${list}" var="shopping" varStatus="vs">
					<input type="hidden"  id="${vs.index }"  value="${shopping.book.bookSum }" />
						<tr <c:if test="${vs.index%2==0 }">class="odd" </c:if>>
						
							<td align="left" class="thumb" ><img src="images/book/${shopping.book.bookPic }" /></td>
							<td class="title" width="110px" align="right">${shopping.book.bookName }</td>
							<td> <b>${shopping.book.bookSum }</b></td>
							<td><input class="input-text" type="text" name="bookNum" value="${shopping.bookNum }" /></td>
							<td>￥<span><fmt:formatNumber pattern="0.00">${shopping.book.bookPrice*shopping.bookNum }</fmt:formatNumber></span></td></tr>
					</c:forEach>
				</table>
				<div class="button">
					<h4>
						总价：￥<span><fmt:formatNumber pattern="0.00">${pS }</fmt:formatNumber></span>&nbsp;&nbsp;元
					</h4>
					<input class="input-chart" type="submit"  name="tijiao" value="" />
				</div>
			</form>
		</div>
	</div>
	<div id="footer" class="wrap">北大青鸟网上书城 &copy; 版权所有</div>
</body>
</html>
