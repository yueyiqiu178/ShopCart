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
<b>��g�q��</b>
</div>
<form action="createorderform" name="orderform" method="post">
<input type="hidden" name="buygoodids" value="${buygoodids}"/>
<input type="hidden" name="buygoodnums" value="${buygoodnums}"/>
<input type="hidden" name="goodprices" value="${param.goodtotalprice}"/>
<table border="1" bordercolor="#F2F3F5">
<tr bgcolor="#F2F3F5">
<td colspan="3">
���f�H�T��
</td>
</tr>
<tr>
<td>
���f�H
</td>
<td colspan="2">
<input type="text" name="getter" onblur="clear_gettermessage();"/>&nbsp;<b><span id="gettermessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
�ԲӦa�}
</td>
<td colspan="2">
<input type="text" name="address" onblur="clear_addressmessage();"/>&nbsp;<b><span id="addressmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
�l�F�s��
</td>
<td colspan="2">
<input type="text" name="postalcode" onblur="clear_postalcodemessage();"/>&nbsp;<b><span id="postalcodemessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
�s���q��
</td>
<td>
��ʹq��:<input type="text" name="mobile" onblur="clear_mobilemessage();"/>&nbsp;<b><span id="mobilemessage" style="color: red"></span></b>
</td>
<td>
�T�w�q��:<input type="text" name="tel" onblur="clear_telmessage()"/>&nbsp;<b><span id="telmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td colspan="3">
<b><span></span></b>
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="3">
�e�f�覡<b><span id="shipmentmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td>
<input type="radio" value="1" id="shipment1" name="shipment" onclick="shipment1_ckick();"/>���q�ֻ��e�f�W��
</td>
<td colspan="2">
<div id="shipmenttime" style="display: none;">
�e�f�ɶ�:
<select name="shipmenttime" onchange="">
<option value="">-�п�ܮɶ�-</option>
<option value="1">�����ɶ�</option>
<option value="2">�T�Ѥ�</option>
<option value="3">�@�P��</option>
<option value="4">�@�뤺</option>
</select>
(����f��I��)�B�O:100��
</div>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="2" id="shipment2" name="shipment" onclick="shipment_2_3_ckick();"/>�f��I��
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="3" id="shipment3" name="shipment" onclick="shipment_2_3_ckick();"/>���a���쩱
</td>
</tr>
<tr bgcolor="#F2F3F5">
<td colspan="3">
�I�ڤ覡<b><span id="paymentmessage" style="color: red"></span></b>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="1" id="payment1" name="payment" onclick="payment_1_click();"/>���W��I<br/>
<div id="network" style="padding-left: 20px;display: none;">
<input type="radio" value="1" id="networkpayment1" name="networkpayment"/>�x�W�Ȧ�<br/>
<input type="radio" value="2" id="networkpayment2" name="networkpayment"/>�g�a�Ȧ�<br/>
<input type="radio" value="3" id="networkpayment3" name="networkpayment"/>�X�@�Ȧ�<br/>
<input type="radio" value="4" id="networkpayment4" name="networkpayment"/>���ƻȦ�<br/>
</div>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="2" id="payment2" name="payment" onclick="payment_2_3_4_click();"/>�Ȧ���b<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="3" id="payment3" name="payment" onclick="payment_2_3_4_click();"/>�f��I��<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="radio" value="4" id="payment4" name="payment" onclick="payment_2_3_4_click();"/>�l���״�<br/>
</td>
</tr>
<tr>
<td colspan="3">
<input type="button" name="formbutton" value="�ͦ��q��" onclick="myorderformsubmit()"/>

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