<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<% 

String forward="";
String payment=request.getParameter("payment");

if(payment.equals("1")){
	
	String network=request.getParameter("networkpayment");
	
	if(network.equals("1")){
		forward="/payment/bank1payment.jsp";
	}
	else if(network.equals("2")){
		forward="/payment/bank2payment.jsp";
	}
	else if(network.equals("3")){
		forward="/payment/bank3payment.jsp";
	}
	else if(network.equals("4")){
		forward="/payment/bank4payment.jsp";
	}
	else{
		forward="/payment/fail.jsp";
		}

}

else if(payment.equals("2")){
	forward="/payment/shipment2.jsp.jsp";
}
else if(payment.equals("3")){
	forward="/payment/shipment3.jsp";
}
else if(payment.equals("4")){
	forward="/payment/shipment4.jsp";
}
else{
	forward="fail.jsp";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<jsp:forward page="<%=forward%>"></jsp:forward>
</body>
</html>