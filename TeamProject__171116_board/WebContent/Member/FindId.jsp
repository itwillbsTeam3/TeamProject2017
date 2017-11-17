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
   String mobile = request.getParameter("mobile");
   String id = mdao.searchId(name, mobile);
   MemberBean mb = mdao.getMember(id);
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
   	<h1>회원님의 아이디는 아래와 같습니다.</h1>
   	<h1><span style="font-weight:bold; color:red;"><%=mb.getId()%></span>
   	(가입일 : <% String reg = mb.getReg_date().toString();%><%=reg.substring(0,reg.length()-10)%>)</h1>
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