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
<c:if test="${empty single}">�d�ݰӫ~�ԲӰT������</c:if>
<c:if test="${!empty single}">


<table border="0" cellpadding="3" cellspacing="3" width="100%" height="100%">

<tr height="400px">
<td width="50%" style="padding-left: 91px; padding-top: 160px;"><img alt="�ԲӰT��" src="images/goods/${single.goodViewpic }" width="220px" height="180px"/></td>
<td valign="bottom" style="padding-bottom: 80px; font-size: 20px; line-height: 30px;">
�ӫ~�W��:${single.goodName}<br/>
�ӫ~����:${single.goodPrice}<br/>
�W�[�ɶ�:${single.goodStocktime}<br/>
�Ͳ��t��:${single.goodMaker}<br/>
<c:choose>
<c:when test="${single.goodStore<=0}">
<font color="red">��p!�Ȯɯʳf</font>
</c:when>
<c:otherwise>
<a href="buy?buygoodid=${single.id}">��J�ʪ���</a>
</c:otherwise>
</c:choose>

&nbsp;&nbsp;&nbsp;&nbsp;<a href="showshopcart">�d���ʪ���</a>
</td>
</tr>

<tr height="200px">
<td colspan="2" valign="top" style="padding-left: 30px;">

<b>�ӫ~�y�z:</b><br/>
${single.goodInfo}
</td>

</tr>


</table>


</c:if>

</div>
</center>
</body>
</html>