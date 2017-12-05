<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/FindPw.css?v=3" rel="stylesheet" type="text/css">
<title>비밀번호 찾기</title>
</head>
<%
   request.setCharacterEncoding("UTF-8");
   MemberDAO mdao = new MemberDAO();
   String id = request.getParameter("id");
   String mobile = request.getParameter("mobile");
   String email = request.getParameter("email");
   String pass = mdao.searchPwd(id, mobile, email);
   MemberBean mb = mdao.getMember(id);
%>
<body>
<div id="wrap" >
<!--헤더들어가는곳-->
<jsp:include page="../inc/header.jsp" />
<!--헤더들어가는곳-->
<div class="clear"></div>
<!-- 본문 -->
<div class="body">
<article>
	<form method="post" action="./MemberFindPwd.me">
	<%
	if(pass!=null){ 
	%>
	<h1><span style="font-weight:bold; color:red;"><%=mb.getId() %></span> 회원님의 비밀번호는 아래와 같습니다.</h1>
	<h1>(비밀번호 : <%=pass.replaceAll("(?<=.{2}).", "*") %>)</h1>
	<div id="notice">
	<input type="button" class="btn btn_login_Pwd" value="로그인" onclick="location.href='./MemberLogin.me'">
	<%
	}else{
	%>
	<h1><%=id %>님은 가입정보가 없습니다.</h1>
	<div id="notice">
	<input type="button" value="회원가입" class="btn btn_submit" onclick="location.href='./MemberJoinreg.me'">
	<input type="button" value="로그인" class="btn" onclick="location.href='./Main.me'">
   <%
   } 
   %>
	</div>
	</form>
</article>
</div>
<!-- 본문 -->
	<div class="clear"></div>
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>