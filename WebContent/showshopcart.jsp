<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<c:set var="totalmoney" value="${0}"></c:set>

<center>

<div><img width="1000px" height="149px" src="images/top.jpg"></div>

<form action="submitshopcart" method="post">
<table width="800px" border="0" cellpadding="0" cellspacing="0">

<tr height="45px">
<td colspan="6" background="images/car_t.jpg">

</td>

</tr>

<tr height="54px">
<td colspan="6" background="images/car_m.jpg" style="padding-left: 20px">
顧客您好,購物車內的商品將為您保留3天
</td>

</tr>

<tr height="40px">
<th width="10%" align="left">序號</th>
<th align="left">商品名稱</th>
<th width="15%" align="left">價格</th>
<th width="15%" align="left">數量</th>
<th width="15%" align="left">總計</th>
<th width="10%" align="left">刪除</th>
</tr>

<c:set var="myshopcart" value="${shopcart}"></c:set>
<c:if test="${(empty myshopcart)or(empty myshopcart.shopcartbuyGoods)}">

<tr height="80px">
<td colspan="6" align="center">
您還沒有挑選商品到購物車中!!
</td>
</tr>
</c:if>

<c:if test="${(!empty myshopcart)and(!empty myshopcart.shopcartbuyGoods)}">

<c:forEach var="buygood" items="${myshopcart.shopcartbuyGoods}" varStatus="ptr">

<c:if test="${!empty buygood}">

<input type="hidden" name="buygoodid" value="${buygood.id }"/>
<input type="hidden" name="buygoodstorenum" value="${buygood.goodStore}"/>

<tr height="40px">
<td width="10%">${ptr.count}</td>
<td align="left" >${buygood.goodName}</td>
<td width="15%">${buygood.goodPrice}</td>
<td width="15%"><input name="buygoodnum" type="text" value="${buygood.goodBuy}"/>
<font color="red">${message[ptr.index]}</font>
</td>
<td width="15%">總計</td>
<td width="10%"><a href="remove?goodid=${buygood.id}">刪除</a></td>
</tr>

<c:set var="totalmoney" value="${totalmoney+buygood.goodPrice*buygood.goodBuy}"></c:set>
</c:if>



</c:forEach>



<tr height="40px">
<td colspan="6">
<hr width="99%" color="black"/>
</td>

</tr>

<tr height="40px">
<td colspan="6">
總金額:
<input type="text" name="goodtotalprice" value="${totalmoney}" style="border: 0;"/>
</td>

</tr>

<tr height="50px">
<td colspan="3">
<input type="submit" name="whichsubmit" value="修改數量"/>
<a href="clearshopcart">清空購物車</a>
</td>
<td colspan="3" align="center">
<% 

Object user=session.getAttribute("user");
if(user==null){
	out.print("您沒有登入不能進行結帳");
}
else{
	out.print("您有登入可以進行");



%>
<input type="submit" name="whichsubmit" value="商品結算"/>
<%} %>
</td>
</tr>


</c:if>



<tr height="40">
<td colspan="6"><a href="javascript:window.close(0)">關閉</a></td>

</tr>



</table>
</form>
<br/>



<div>
<img alt="" src="images/end.jpg" width="800px" height="104px">
</div>

</center>


</body>
</html>