<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Update.css?v=5" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<style type="text/css">
.body{
	width: auto;
} 
.form_table{
	margin: auto;
}
.form_table th {
	width: 100px !important;
}
#login_btn{
	margin-top: 20px;
}
.btn_sub{
	margin-left: 140px;
}
.article{
	margin-left: auto;
	margin-right: auto; 
	width: 520px;
	padding: 30px 30px;
    margin-top: 200px;
    margin-bottom: -200px;
    border: 1px solid #DBDBDB
}
</style>
<script type="text/javascript">
function up_em_echeck(){
		var em = $(".email").val();

		if(em == ""){
			alert("이메일을 입력하세요.");
			fr.email.focus();
			return false;
		}
		if($('input:checkbox[id="emailcheck"]').is(":checked") == true){
			up_em_save();	
		 }else{
		   alert("이메일을 인증해주세요.");
			return false;
		 }
		
}	
</script>
<script type="text/javascript">
function up_em_save() {
	var str = document.getElementById('update');
	str.submit();
	alert("이메일이 변경되었습니다.");
}
</script>
<script type="text/javascript">
//이메일 인증시 
function up_em_check(){
	var em = $(".email").val();
 	if(em == ""){
 		alert("이메일을 입력해주세요.")
 		document.fr.email.focus();
 		return false;
 	}
 	window.open(
 			"EmailCheck.em?email="+em,"","top =300,left=630,width=500,height=220");
	}
	
function sub(){
	if(document.fr.emailcheck.checked == false){
		alert("이메일 인증을 해주세요")
		return false;
	}
}
</script>
</head>
<body>
	<%
	String id = (String)session.getAttribute("id");
	if(id == null){response.sendRedirect("./MemberLogin.me");}
	MemberBean mb = (MemberBean)request.getAttribute("mb");
	%>
	<div class="wrap">
		<jsp:include page="../inc/header.jsp" />
		<div class="clear"></div>
		<div class="body">
		<article class="article">
			<h1 class="UpdateMail h1"><img src="img/email.png" height="27px" width="27px" style="margin-right:10px;">이메일 변경</h1><br>
			<form action="./MemberEmailUpdateAction.me" class="form" name="fr" id="update" method="post" enctype="multipart/form-data">
			<table class="form_table">
				<input type="hidden" name="id" id="id" value="<%=mb.getId()%>" readonly>
				<input type="hidden" name="name" id="name" value="<%=mb.getName() %>" readonly>
				<input type="hidden" name="pass" id="pass" value="<%=mb.getPass() %>" readonly>
				<input type="hidden" name="age" id="age" value="<%=mb.getAge()%>" readonly>
				<input type="hidden" name="gender" value="<%=mb.getGender()%>" readonly>
				<tr><th>기존 이메일</th><td><%=mb.getEmail() %></td></tr>
				<tr><th>변경 이메일</th>
				<td>
				<input type="email" name="email" class="email" requried>
				<input type="button" value="이메일인증" class="dup" onclick="up_em_check()">
				<input type="checkbox" id="emailcheck" disabled="disabled" > 인증여부
				</td>
				</tr>	
				<input type="hidden" name="zip_code" id="sample4_postcode" value="<%=mb.getZip_code()%>" readonly>
				<input type="hidden" name="address" id="sample4_roadAddress" value="<%=mb.getAddress()%>" readonly>
				<input type="hidden" name="address2" id="sample4_jibunAddress" value="<%=mb.getAddress2()%>" readonly>
				<input type="hidden" name="phone" id="phone" value="<%=mb.getPhone()%>" readonly>
				<input type="hidden" name="mobile" id="mobile" value="<%=mb.getMobile()%>" readonly>
				<%if(mb.getProfile()!=null){ %>				
              	<input type="hidden" name="PreFile" value="<%=mb.getProfile() %>">
				<input type="hidden" name="profile" id="input_file" >
				<%} %>
				<%if(mb.getProfile()==null){ %>
				<input type="hidden" name="profile" id="input_file" >
				<%}%>
				<textarea name="selfinfo" id="selfinfo" name="selfinfo" style="display:none;"><%=mb.getSelfinfo()%></textarea>
			</table> 
				<div id="login_btn">
					<input type="button" value="업데이트" class="btn btn_sub" onclick="javascript:up_em_echeck()">
					<input type="button" value="취소" onclick="location.href='./Main.me' " class="btn">
				</div>
			</form>
			</article>
		</div>
		<jsp:include page="../inc/footer.jsp" />
	</div>
</body>
</html>