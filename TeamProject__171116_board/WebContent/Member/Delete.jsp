<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Delete.css?v=1" rel="stylesheet" type="text/css">
<script src="js/script.js"></script>
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<h1>Delete Member</h1>
			<br>
			<%
			String id =(String)session.getAttribute("id");
			%>

			<form action="./MemberDeleteAction.me" name="fr" method="post" id="join" onsubmit="return fun_pass()">
				<table class="form_table">
					<tr class="form_row">
						<td class="form_col">아이디</td>
						<td><input type="text" name="id" value=<%=id %> readonly></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pass"></td>
					</tr>
				</table>
				<div id="login_btn">
					<input type="button" name="main" value="메인" class="btn btn_main" onclick="location.href='./Main.me'">
					<input type="button" name="update" value="정보수정" class="btn" onclick="location.href='./MemberUpdate.me'">
					<input type="submit" name="delete" value="회원탈퇴" class="btn btn_submit">
					<div style="margin:10px;  width:550px; background-color:#EAEAEA; padding: 5px 0 5px 15px;">
					※ 탈퇴하시면 복구 불가능합니다.<br>
					- 탈퇴할 시 해당 계정의 정보가 지워집니다.<br>
					- 탈퇴했을 시 다시 복구가 불가능하며, 신중하게 탈퇴해 주시기 바랍니다.<br>
					</div>
				</div>
			</form>
		</div>
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>