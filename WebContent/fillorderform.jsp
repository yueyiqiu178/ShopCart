<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script type="text/javascript" src="js/validateorderform.js"></script>

</head>
<body>
<center>
<div><img alt="" src="images/top.jpg"></div>

<div style="background-image:url(images/car_m.jpg);width: 600px;height: 35px;padding-left: 30px;padding-top:20px;text-align: left;">
<b>填寫訂單</b>
</div>
<form action="createorderform" name="orderform" method="post">
<input type="hidden" name="buygoodids" value="${buygoodids}"/>
<input type="hidden" name="buygoodnums" value="${buygoodnums}"/>
<input type="hidden" name="goodprices" value="${param.goodtotalprice}"/>
<table border="1" bordercolor="#F2F3F5">
<tr bgcolor="#F2F3F5">
<td colspan="3">
收貨人訊息
</td>
</tr>
<tr>
<td>
收貨人
</td>
<td colspan="2">
<input type="text" name="getter" onblur="clear_gettermessage();"/>&nbsp;<b><span id="gettermessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
詳細地址
</td>
<td colspan="2">
<input type="text" name="address" onblur="clear_addressmessage();"/>&nbsp;<b><span id="addressmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
郵政編號
</td>
<td colspan="2">
<input type="text" name="postalcode" onblur="clear_postalcodemessage();"/>&nbsp;<b><span id="postalcodemessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
連絡電話
</td>
<td>
行動電話:<input type="text" name="mobile" onblur="clear_mobilemessage();"/>&nbsp;<b><span id="mobilemessage" style="color: red"></span></b>
</td>
<td>
固定電話:<input type="text" name="tel" onblur="clear_telmessage()"/>&nbsp;<b><span id="telmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td colspan="3">
<b><span></span></b>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="3">
送貨方式<b><span id="shipmentmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
<input type="radio" value="1" id="shipment1" name="shipment" onclick="shipment1_ckick();"/>普通快遞送貨上門
</td>
<td colspan="2">
<div id="shipmenttime" style="display: none;">
送貨時間:
<select name="shipmenttime" onchange="">
<option value="">-請選擇時間-</option>
<option value="1">不限時間</option>
<option value="2">三天內</option>
<option value="3">一周內</option>
<option value="4">一月內</option>
</select>
(支持貨到付款)運費:100元
</div>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="2" id="shipment2" name="shipment" onclick="shipment_2_3_ckick();"/>貨到付款
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="3" id="shipment3" name="shipment" onclick="shipment_2_3_ckick();"/>全家店到店
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="3">
付款方式<b><span id="paymentmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="1" id="payment1" name="payment" onclick="payment_1_click();"/>網上支付<br/>
<div id="network" style="padding-left: 20px;display: none;">
<input type="radio" value="1" id="networkpayment1" name="networkpayment"/>台灣銀行<br/>
<input type="radio" value="2" id="networkpayment2" name="networkpayment"/>土地銀行<br/>
<input type="radio" value="3" id="networkpayment3" name="networkpayment"/>合作銀行<br/>
<input type="radio" value="4" id="networkpayment4" name="networkpayment"/>彰化銀行<br/>
</div>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="2" id="payment2" name="payment" onclick="payment_2_3_4_click();"/>銀行轉帳<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="3" id="payment3" name="payment" onclick="payment_2_3_4_click();"/>貨到付款<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="4" id="payment4" name="payment" onclick="payment_2_3_4_click();"/>郵局匯款<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="button" name="formbutton" value="生成訂單" onclick="myorderformsubmit()"/>

</td>
</tr>
</table>
</form>
<div>
<img alt="" src="images/end.jpg" width="790px" height="104px">
</div>
</center>
</body>
</html>