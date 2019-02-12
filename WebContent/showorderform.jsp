<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script type="text/javascript">

function submitform(){

	var ptr=document.getElementById("affirmorderform");
	ptr.submit();
}

</script>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="center">
<tr>
<td><img width="800px" height="150px" src="images/top.jpg"></td>
</tr>
<tr height="54px">
<td style="background-image: url('images/car_m.jpg');padding-left: 15px;padding-top: 10px;"><b>${user.username}的訂單</b></td>
</tr>
</table>
<c:set var="myorder" value="${orderform}"></c:set>
<c:if test="${!empty myorder}">
<table border="1" bordercolor="lightgrey" width="800px" cellpadding="3" cellspacing="0" align="center">
<tr height="30px">
<td colspan="2" align="center">
訂單號:${myorder.orderNumber}
下單時間:${myorder.orderTime}
</td>
</tr>
<tr>
<td colspan="2" bgcolor="#F2F3F5">
收貨人訊息
</td>
</tr>
<tr>
<td width="25%" style="padding-left: 40px;">收貨人:</td>
<td>${myorder.orderGetter}</td>
</tr>
<tr>
<td style="padding-left: 40px;">詳細地址:</td>
<td>${myorder.orderAddress}</td>
</tr>
<tr>
<td style="padding-left: 40px;">郵政編號:</td>
<td>${myorder.orderPostalcode}</td>
</tr>
<tr>
<td style="padding-left: 40px;">聯繫電話:</td>
<td>${myorder.orderPhone}</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">送貨方式:</td>
</tr>
<tr>
<td colspan="2" style="padding-left: 40px;">
<c:if test="${myorder.orderShipment eq '1'}">
宅急便送貨上門,送貨時間:
<c:if test="${myorder.orderShipmenttime eq '1'}">不限時間</c:if>
<c:if test="${myorder.orderShipmenttime eq '2'}">3天內</c:if>
<c:if test="${myorder.orderShipmenttime eq '3'}">1周內</c:if>
<c:if test="${myorder.orderShipmenttime eq '4'}">1月內</c:if>
</c:if>
<c:if test="${myorder.orderShipment eq '2'}">郵局包裹</c:if>
<c:if test="${myorder.orderShipment eq '3'}">郵局特快</c:if>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">支付方式</td>
</tr>
<tr>
<td colspan="2" style="padding-left: 40px;">
<c:if test="${myorder.orderPayment eq '1'}">
網路支付,
<c:if test="${myorder.orderNetworkpayment eq '1'}">透過支付</c:if>
<c:if test="${myorder.orderNetworkpayment eq '2'}">透過支付</c:if>
<c:if test="${myorder.orderNetworkpayment eq '3'}">透過支付</c:if>
<c:if test="${myorder.orderNetworkpayment eq '4'}">透過支付</c:if>
</c:if>
<c:if test="${myorder.orderPayment eq '2'}">貨到付款</c:if>
<c:if test="${myorder.orderPayment eq '3'}">郵局匯款</c:if>
<c:if test="${myorder.orderPayment eq '4'}">銀行轉帳</c:if>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">商品清單</td>
</tr>
<tr>
<td colspan="2" align="center">
<c:if test="${!empty myorder.orderBuygoods}">
<table border="0" width="100%" cellpadding="0" cellspacing="0">
<tr height="45px">
<th width="15%" align="left">序號</th>
<th width="25%" align="left">商品名稱</th>
<th width="15%" align="left">價格</th>
<th width="15%" align="left">數量</th>
<th width="15%" align="left">總計</th>
</tr>
<c:forEach var="ptr" varStatus="status" items="${myorder.orderBuygoods}">
<tr>
<td>${status.count}</td>
<td>${ptr.goodName}</td>
<td>${ptr.goodPrice}</td>
<td>${ptr.goodBuy}</td>
<td>${ptr.goodPrice*ptr.goodBuy}</td>
</tr>
</c:forEach>
<tr height="50px">
<td colspan="5" valign="bottom">
商品合計:${goodprice}
<hr color="black" width="100%"/>
</td>
</tr>
<tr height="50px">
<td colspan="5">
您需要為該訂單支付:${totalprice}(含運費)
</td>
</tr>
</table>
</c:if>
</td>
</tr>
</table>
<form action="affirmorderform.jsp" name="affirmorderform" method="post">
<input type="hidden" name="payment" value="${myorder.orderPayment}"/>
<input type="hidden" name="networkpayment" value="${myorder.orderNetworkpayment}"/>
<input type="hidden" name="orderid" value="${myorder.orderNumber}"/>
<input type="hidden" name="ordertime" value="${myorder.orderTime}"/>
<input type="hidden" name="amount" value="${totalprice}"/>
<div align="center"><input type="button" value="確認訂單" onclick="submitform();"/></div>
</form>
</c:if>
<div align="center">
<img alt="" src="images/end.jpg">
</div>
</body>
</html>