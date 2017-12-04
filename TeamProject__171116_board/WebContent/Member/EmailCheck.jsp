<%@page import="net.email.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>

<style type="text/css">
.title{font-weight: bold;}
</style>
<title>Insert title here</title>
<%
EmailRand x = new EmailRand();
String y = x.getRand();
String email = (String)request.getParameter("email");
%>
<!-- 이메일인증 및 체크시 -->
<script type="text/javascript">
$(document).ready(function(){
	var flag = 0;
	var m = 1;
	var s = 00;
	$(".s").html(s);
	$(".m").html(m);
	var x = "<%=y%>";

	$(".gogo").click(function(){
		$(".in").css("display","");
		$.ajax("EmailSend.em",{ 
			data : {"y":"<%=y%>","email":"<%=email%>"},
			success : function(data){
				alert("인증번호가 전송되었습니다.")
			}
		});
	var qwe = setInterval(function(){
	if(flag == 0){
		s--;
		if(s<0){	
		m--;
		s = 59;
	}
		$(".s").html(s);
		$(".m").html(m);
		
			if(m==0 && s==0){
				flag = 1;
			}
	}
	else{
		alert("시간이 만료되었습니다.");
		x = "w1q123ie2ur33io3p11jak4df5s";
		$(".in").html(x);
		$(".gogo").css("display","none");
		$(".regogo").css("display","");
		clearInterval(qwe);
	}
	},1000);
	});
	$(".regogo").click(function(){
		location.reload();
	});
	
	$(".okay").click(function(){
	var q =$(".text").val().toUpperCase();
	x = x.toUpperCase();
	if(x==q){
		alert("인증되었습니다.");
		$("#emailcheck", opener.document).prop("checked", true);
		self.close();
	}
	else{
		alert("인증 실패");
	}	
	}); 
});
</script>

</head>
<body>
<div align="center" class="title"><h1>이메일 인증</h1></div>
<div class ="con" align="center">
<span align="center">인증시간 :</span>
<span class="m"></span><span>분</span>
<span class="s"></span><span>초</span>
<div align="center"><input type="text" class="text">
<input type="button" value="인증확인" class="okay">
<input type="button" value="인증번호발급" class="gogo">
<input type="button" value="인증번호재발급" class="regogo" style="display: none;"></div>

<!-- Cafe24 올릴시 주석처리 할 것! -->
<%-- <span class="in" style="display: none;"><%=y%></span> --%>

</div>

</body>
</html>