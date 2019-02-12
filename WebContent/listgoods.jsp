<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<script type="text/javascript">

function addbrowsegood(){

		window.setTimeout("showbrowsegood();",3000)
}

function showbrowsegood(){

	var ptr=parent.document.getElementById("listbrowsegoods");
	ptr.src="listbrowsegoods";
}


</script>

</head>
<body>

<center>

<div>


<c:set var="allgoodlist" value="${goodlist}"></c:set>

<c:if test="${empty allgoodlist}">沒有商品</c:if>
<c:if test="${!empty allgoodlist}">

<table width="100%" border="0" cellpadding="5" cellspacing="5">

<c:forEach var="onegoodlist" items="${allgoodlist}">

<tr>

<c:forEach var="onegood" items="${onegoodlist}">

<td>

<c:if test="${!empty onegood}">

<div style="width: 123px;height:90px;">
<a href="viewgood?goodid=${onegood.id}" target="_blank"><img width="90px" height="75px" src="images/goods/${onegood.goodViewpic}" title="查看詳細訊息" onclick="addbrowsegood()"></a>
</div>

商品名稱:${onegood.goodName}<br/>
商品價格:${onegood.goodPrice }<br/>
<a href="viewgood?goodid=${onegood.id}" target="_blank">查看詳細訊息</a>


</c:if>

</td>

</c:forEach>
</tr>

</c:forEach>

<tr>
<td colspan="4">
${pagebar.pageLink}
</td>

</tr>

</table>
</c:if>

</div>

</center>


</body>
</html>