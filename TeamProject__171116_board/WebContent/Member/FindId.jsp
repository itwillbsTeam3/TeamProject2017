<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/FindId.css?v=3" rel="stylesheet" type="text/css">
<title>아이디 찾기</title>
</head>
<%
   request.setCharacterEncoding("UTF-8");
   MemberDAO mdao = new MemberDAO();
   String name = request.getParameter("name");
   String phone = request.getParameter("phone");
   String id = mdao.searchId(name, phone);
%>
<body>
<div id="wrap">
<jsp:include page="../inc/header.jsp" />
<div class="clear"></div>
<article>
<div class="body">
   <form method="post" action="./MemberFindId.me">
   <%
   if(id!=null){ 
   %>
 	<h1>이름이 <%=name %>인 님의 아이디는 <%=id.replaceAll("(?<=.{2}).", "*") %>입니다.</h1>
	<div id="notice">
	<input type="button" value="로그인" onclick="location.href='./Main.me'" class="btn">
	</div>
   <%
   }else{
   %>
	<h1><%=name %> 님은 가입정보가 없습니다.</h1>
	<div id="notice">
	<input type="button" value="회원가입" class="btn btn_submit" onclick="location.href='./MemberJoinreg.me'">
	<input type="button" value="뒤로가기" class="btn btn_back" onclick="location.href='./Main.me'">
	</div>
   <%
   } 
   %>
</form>
</div>
</article>
<div class="clear"></div>
<jsp:include page="../inc/footer.jsp" />
</div>
</body>
</html>