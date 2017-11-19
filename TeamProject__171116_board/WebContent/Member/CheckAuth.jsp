<%@page import="net.member.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/CheckAuth.css?v=7" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script type="text/javascript">
//회원정보 수정 전 확인 및 회원탈퇴 파트
function ck_au(){
	var na = $("#name").val();
	var emm = $("#email").val();
	var mob = $("#mobile").val();
	
	if(na==""){
		alert("이름을 입력하세요");
		document.fr.name.focus();
		return false;
	}else if(emm==""){
		alert("이메일을 입력하세요");
		document.fr.email.focus();
		return false;
	}else if(mob==""){
		alert("휴대전화번호를 입력하세요");
		document.fr.mobile.focus();
		return false;
	}else{
		ck_au_save();
	}
}
</script>
<script type="text/javascript">
function ck_au_save() {
	var str = document.getElementById('auth');
	str.submit();
// 	alert("본인인증 확인 되셨습니다.");
}
</script>

</head>
<body>
	<div id="wrap">
		<jsp:include page="../inc/header.jsp" />
		<%
			String id = (String) session.getAttribute("id");
			if (id == null) {
				response.sendRedirect("./MemberLogin.me");
			}
		%>
		<div class="clear"></div>
		<div class="body">
			<article class="article">
				<h2>본인인증</h2>
				<form action="./MemberCheckAuthAction.me" method="post" name="fr" id="auth">
					<table id="notice">
						<tr>
							<td></td>
							<td><input type="hidden" name="id" value="<%=id%>" readonly></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" id="name"></td>
						</tr>
						
						<tr>
							<td>이메일</td>
							<td><input type="email" name="email" id="email"></td>
						</tr>
						
						<tr>
							<td>휴대전화번호</td>
							<td><input type="text" name="mobile" id="mobile"></td>
						</tr>
					</table>
					<div id="table_search">
						<input type="button" name="delete" value="인증시도" class="btn btn_submit" onclick="javascript:ck_au()">
						<input type="button" name="main" value="메인"
							onclick="location.href='./Main.me'" class="btn">
					</div>
				</form>
				<div class="clear"></div>
				<div id="page_control"></div>
			</article>
		</div>
		<jsp:include page="../inc/footer.jsp" />
	</div>
</body>
</html>
