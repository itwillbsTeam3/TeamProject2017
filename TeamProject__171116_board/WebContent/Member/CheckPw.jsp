<%@page import="net.member.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/CheckPw.css?v=6" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
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
				<h2>비밀번호 확인</h2>
				<form action="./MemberCheckPwAction.me" method="post" name="fr"
					id="CheckPw_fr" onsubmit="return fun_pass()">
					<table id="notice">
						<%
							MemberDAO mdao = new MemberDAO();
							MemberBean mb = mdao.getMember(id);
						%>
						<span style="color: blue; font-weight: bold"><%=mb.getName()%></span>
						님의 회원정보를 안전하게 보호하기 위해
						<br> 비밀번호를 한번 더 입력해주세요.
						<tr>
							<td><input type="hidden" name="id" value="<%=id%>" readonly></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
					</table>
					<div id="table_search">
						<input type="submit" name="delete" value="회원정보수정" class="btn">
						<input type="button" name="main" value="메인"
							onclick="location.href='./Main.me'" class="btn">
					</div>
				</form>
				<div class="clear"></div>
				<div id="page_control"></div>
			</article>
			<!-- <div class="clear"></div> -->
		</div>
		<jsp:include page="../inc/footer.jsp" />
	</div>
</body>
</html>
