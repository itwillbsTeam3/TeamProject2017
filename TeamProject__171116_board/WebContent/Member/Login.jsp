<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=2" rel="stylesheet" type="text/css">
<link href="css/Login.css?v=1" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp"/>
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
		<div class="Login_table">
			<h1>Login</h1><br>
			<form action="./MemberLoginAction.me" name="fr" method="post" >
			<table class="form_table">
			<tr class="form_row">
				<td class="form_col">아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr class="form_row">
				<td class="form_col">비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			</table>
			<div class="login_btn">
				<input type="submit" value="Login" class="click_btn btn_login"> 
				<input type="reset" value="Cancel" class="click_btn btn_cancel">
			</div>
			<div class="login_btn_find">
				<input type="button" value="아이디 찾기" class="click_btn btn_findid" onclick="location.href='./MemberSearchId.me'">
				<input type="button" value="비밀번호 찾기" class="click_btn btn_findpass" onclick="location.href='./MemberSearchPwd.me'">
			</div>
		</form>
		</div>
		</div>
	<!-- 본문 -->
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>