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
<td style="background-image: url('images/car_m.jpg');padding-left: 15px;padding-top: 10px;"><b>${user.username}���q��</b></td>
</tr>
</table>
<c:set var="myorder" value="${orderform}"></c:set>
<c:if test="${!empty myorder}">
<table border="1" bordercolor="lightgrey" width="800px" cellpadding="3" cellspacing="0" align="center">
<tr height="30px">
<td colspan="2" align="center">
�q�渹:${myorder.orderNumber}
�U��ɶ�:${myorder.orderTime}
</td>
</tr>
<tr>
<td colspan="2" bgcolor="#F2F3F5">
���f�H�T��
</td>
</tr>
<tr>
<td width="25%" style="padding-left: 40px;">���f�H:</td>
<td>${myorder.orderGetter}</td>
</tr>
<tr>
<td style="padding-left: 40px;">�ԲӦa�}:</td>
<td>${myorder.orderAddress}</td>
</tr>
<tr>
<td style="padding-left: 40px;">�l�F�s��:</td>
<td>${myorder.orderPostalcode}</td>
</tr>
<tr>
<td style="padding-left: 40px;">�pô�q��:</td>
<td>${myorder.orderPhone}</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">�e�f�覡:</td>
</tr>
<tr>
<td colspan="2" style="padding-left: 40px;">
<c:if test="${myorder.orderShipment eq '1'}">
�v��K�e�f�W��,�e�f�ɶ�:
<c:if test="${myorder.orderShipmenttime eq '1'}">�����ɶ�</c:if>
<c:if test="${myorder.orderShipmenttime eq '2'}">3�Ѥ�</c:if>
<c:if test="${myorder.orderShipmenttime eq '3'}">1�P��</c:if>
<c:if test="${myorder.orderShipmenttime eq '4'}">1�뤺</c:if>
</c:if>
<c:if test="${myorder.orderShipment eq '2'}">�l���]�q</c:if>
<c:if test="${myorder.orderShipment eq '3'}">�l���S��</c:if>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">��I�覡</td>
</tr>
<tr>
<td colspan="2" style="padding-left: 40px;">
<c:if test="${myorder.orderPayment eq '1'}">
������I,
<c:if test="${myorder.orderNetworkpayment eq '1'}">�z�L��I</c:if>
<c:if test="${myorder.orderNetworkpayment eq '2'}">�z�L��I</c:if>
<c:if test="${myorder.orderNetworkpayment eq '3'}">�z�L��I</c:if>
<c:if test="${myorder.orderNetworkpayment eq '4'}">�z�L��I</c:if>
</c:if>
<c:if test="${myorder.orderPayment eq '2'}">�f��I��</c:if>
<c:if test="${myorder.orderPayment eq '3'}">�l���״�</c:if>
<c:if test="${myorder.orderPayment eq '4'}">�Ȧ���b</c:if>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="2">�ӫ~�M��</td>
</tr>
<tr>
<td colspan="2" align="center">
<c:if test="${!empty myorder.orderBuygoods}">
<table border="0" width="100%" cellpadding="0" cellspacing="0">
<tr height="45px">
<th width="15%" align="left">�Ǹ�</th>
<th width="25%" align="left">�ӫ~�W��</th>
<th width="15%" align="left">����</th>
<th width="15%" align="left">�ƶq</th>
<th width="15%" align="left">�`�p</th>
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
�ӫ~�X�p:${goodprice}
<hr color="black" width="100%"/>
</td>
</tr>
<tr height="50px">
<td colspan="5">
�z�ݭn���ӭq���I:${totalprice}(�t�B�O)
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
<div align="center"><input type="button" value="�T�{�q��" onclick="submitform();"/></div>
</form>
</c:if>
<div align="center">
<img alt="" src="images/end.jpg">
</div>
</body>
</html>