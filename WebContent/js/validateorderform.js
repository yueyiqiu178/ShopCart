function myorderformsubmit(){
	
	if(validateForm()){
		orderform.formbutton.enable=false;
		orderform.submit();
		}
	else
		return false;
}

function validateForm(){
	
	
	var message=document.getElementById("gettermessage");
	var get=document.getElementById("getter");
	var address=document.getElementById("address");
	var addressmessage=document.getElementById("addressmessage");
	var postalcode=document.getElementById("postalcode");
	var postalcodemessage=document.getElementById("postalcodemessage");
	var mobile=document.getElementById("mobile");
	var mobilemessage=document.getElementById("mobilemessage");
	var tel=document.getElementById("tel");
	var telmessage=document.getElementById("telmessage");
	
	var shipmentmessage=document.getElementById("shipmentmessage");
	var paymentmessage=document.getElementById("paymentmessage");


	if(trim(orderform.getter.value).length==0){

		message.innerHTML="�п�J���f�H";
		get.focus();
		return false;
		
		}
	if(trim(orderform.address.value).length==0){

		addressmessage.innerHTML="�п�J���f�a�}";
		address.focus();
		return false;
		
		}
	if(trim(orderform.postalcode.value).length==0){

		postalcodemessage.innerHTML="�п�J�l���ϸ�";
		postalcode.focus();
		return false;
		
		}
	if(trim(orderform.mobile.value).length==0){

		mobilemessage.innerHTML="�п�J���";
		mobile.focus();
		return false;
		
		}
	if(trim(orderform.tel.value).length==0){

		telmessage.innerHTML="�п�J�T�w�q��";
		tel.focus();
		return false;
		
		}
	
	
	if(!ischecked(document.all.shipment1)&&!ischecked(document.all.shipment2)&&!ischecked(document.all.shipment3)){
		
		shipmentmessage.innerHTML="�п�ܤ@�ذe�f�覡";
		return false;
	}
	
	if(ischecked(document.all.shipment1)){
		if(orderform.shipmenttime.value==""||orderform.shipmenttime.value==null){
			shipmentmessage.innerHTML="�п�ܰe�f�ɶ�";
			return false;
		}
	}

	if(!ischecked(document.all.payment1)&&!ischecked(document.all.payment2)&&!ischecked(document.all.payment3)&&!ischecked(document.all.payment4)){
		paymentmessage.innerHTML="�п�ܤ@�إI�ڤ覡";
		return false;
	}

	if(ischecked(document.all.payment1)){
		if(!ischecked(document.all.networkpayment1)&&!ischecked(document.all.networkpayment2)&&!ischecked(document.all.networkpayment3)&&!ischecked(document.all.networkpayment4)){
			
			paymentmessage.innerHTML="�п�ܤ@�غ����I�ڤ覡";
			return false;
		}
	}

	return true;
}


function trim(str){

	if(str!=null&&str!=""){
		var re=/(^\s*)|(\s*$)/;
		str=str.replace(re,"");
		}
	else{
		str="";
		}
	return str;
}


function ischecked(field){
	if(field.checked==true)
		return true;
	else
		return false;
}


function shipment1_ckick(){
	
	var ptr=document.getElementById("shipmenttime");
	var payment=document.getElementById("payment2");
	ptr.style.display="";
	payment.disable=false;
}


function shipment_2_3_ckick(){
	
	var ptr=document.getElementById("shipmenttime");
	var payment=document.getElementById("payment2");
	ptr.style.display="none";
	payment.disable=true;
	payment.checked=false;

}

function payment_1_click(){
	
	var network=document.getElementById("network");
	network.style.display="";

}

function payment_2_3_4_click(){
	
	var network=document.getElementById("network");
	network.style.display="none";
}


function clear_gettermessage(){
	var message=document.getElementById("gettermessage");
	message.innerHTML="";
}

function clear_addressmessage(){
	var message=document.getElementById("addressmessage");
	message.innerHTML="";
}

function clear_postalcodemessage(){
	var message=document.getElementById("postalcodemessage");
	message.innerHTML="";
}

function clear_mobilemessage(){
	var message=document.getElementById("mobilemessage");
	message.innerHTML="";
}

function clear_telmessage(){
	var message=document.getElementById("telmessage");
	message.innerHTML="";
}