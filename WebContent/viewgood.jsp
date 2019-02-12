<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>

<center>

<div style="background-image: url('images/viewbk.jpg');width: 720px;height: 600px;background-repeat: no-repeat;">

<c:set var="single" value="${goodsingle}"></c:set>
<c:if test="${empty single}">查看商品詳細訊息失敗</c:if>
<c:if test="${!empty single}">


<table border="0" cellpadding="3" cellspacing="3" width="100%" height="100%">

<tr height="400px">
<td width="50%" style="padding-left: 91px; padding-top: 160px;"><img alt="詳細訊息" src="images/goods/${single.goodViewpic }" width="220px" height="180px"/></td>
<td valign="bottom" style="padding-bottom: 80px; font-size: 20px; line-height: 30px;">
商品名稱:${single.goodName}<br/>
商品價格:${single.goodPrice}<br/>
上架時間:${single.goodStocktime}<br/>
生產廠商:${single.goodMaker}<br/>
<c:choose>
<c:when test="${single.goodStore<=0}">
<font color="red">抱歉!暫時缺貨</font>
</c:when>
<c:otherwise>
<a href="buy?buygoodid=${single.id}">放入購物車</a>
</c:otherwise>
</c:choose>

&nbsp;&nbsp;&nbsp;&nbsp;<a href="showshopcart">查看購物車</a>
</td>
</tr>

<tr height="200px">
<td colspan="2" valign="top" style="padding-left: 30px;">

<b>商品描述:</b><br/>
${single.goodInfo}
</td>

</tr>


</table>


</c:if>

</div>
</center>
</body>
</html>