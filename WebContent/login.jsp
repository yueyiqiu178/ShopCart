<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<style type="text/css">

#maindiv{

	background:url(images/login_bk.jpg);
	width:720px;
	height:583px;
	
}


</style>




</head>
<body>

<center>
<div id="maindiv">
<form action="login" method="post">

<table style="padding-top: 260px">

<tr>

<td>用戶名:</td>
<td><input type="text" value="tom" name="username" class="login"></td>

</tr>

<tr>

<td>密&nbsp;&nbsp;&nbsp;&nbsp;碼:</td>
<td><input type="password" value="123456" name="userpwd" class="login"></td>

</tr>

<tr>

<td colspan="2">

<input type="submit" value="送出" />
<input type="reset" value="重置"/>

</td>


</tr>
</table>
</form>
</div>
</center>
</body>
</html>