<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script type="text/javascript">

function autosize(){
	
	var ptr=document.getElementById("listbrowsegoods");
	ptr.height=document.body.scrollHeight;
}

</script>
</head>
<body onload="autosize();">

<center>

<table border="0" cellpadding="0" cellspacing="0" style="background-image: url('images/left_bk.jpg');">

<tr>
<td colspan="2"><img alt="" src="images/left_m.jpg"></td>
</tr>

<c:set var="allbrowsegoods" value="${browsegoodlist}"></c:set>

<c:if test="${!empty allbrowsegoods}">

<c:forEach var="onebrowsegood" items="${allbrowsegoods}">

<tr height="25px">
<td colspan="2" style="padding-left: 15px;">
${onebrowsegood["browsename"]}
</td>
</tr>

<tr height="60px">
<td style="padding-left: 15px;" align="center"><a href="viewgood?goodid=${onebrowsegood['browseid']}" target="_blank"><img alt="" src="images/goods/${onebrowsegoods['browseviewpic']}" title="點擊查看商品詳細資訊" width="50" height="45" style="border: 1 solid;border-color: black;"></a></td>
<td style="padding-left: 3px;color: gray;" valign="bottom">瀏覽時間<br/>${onebrowsegood["browsetime"]}</td>
</tr>

<tr>
<td colspan="2"><hr color="black"/></td>
</tr>

</c:forEach>

<tr>
<td align="center" colspan="2"><a href="clearbrowse">清除瀏覽的商品</a></td>
</tr>

</c:if>

</table>

</center>

</body>
</html>