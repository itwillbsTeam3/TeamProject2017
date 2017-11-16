<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Info.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
String id =(String)session.getAttribute("id");

if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
MemberBean mb = (MemberBean)request.getAttribute("mb");
%>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<h1>회원정보</h1><br>
			<table class="form_table">
			<tr class="form_row">
				<td class="form_col">아이디</td><td><%=mb.getId() %></td>
			</tr>
			<tr>
				<td>비밀번호</td><td><%=mb.getPass() %></td>
			</tr>
			<tr>
				<td>이름</td><td><%=mb.getName() %></td>
			</tr>
			<tr>
				<td>나이</td><td><%=mb.getAge() %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input type="radio" name="gender" value="남자" 
					<% if(mb.getGender() == null || mb.getGender().equals("남자")){ %> checked <%} %>> 남자
					<input type="radio" name="gender" value="여자" 
					<% if((mb.getGender()).equals("여자")){ %> checked <% } %>>여자</td>
			</tr>
			<tr>
				<td>이메일</td><td><%=mb.getEmail() %></td>
			</tr>
			<tr>
				<td>우편번호</td><td><%=mb.getZip_code() %></td>
			</tr>
			
			<tr>
				<td>도로명 주소</td><td><%=mb.getAddress() %></td>
			</tr>
			<tr>
				<td>지번 주소</td><td><%=mb.getAddress2() %></td>
			</tr>
			<tr>
				<td>집전화</td><td><%=mb.getPhone() %></td>
			</tr>
			<tr>
				<td>휴대전화</td><td><%=mb.getMobile() %></td>
			</tr>
			<tr>
				<td>프로필</td><td><img src="./upload/<%=mb.getProfile() %>" width="100px" height="100px" style="border-radius:50%;"></td>
			</tr>
			<tr>
				<td>자기소개</td><td><textarea name="selfinfo" cols="60" rows="5" readyonly><%=mb.getSelfinfo() %></textarea></td>
			</tr>
			</table>
			 <div id="Info_btn">
				<span></span><input type="button" value="정보수정" onclick="location.href='./MemberUpdate.me'" class="btn"> 
				<input type="button" value="회원탈퇴" onclick="location.href='./MemberDelete.me'" class="btn">
			</div>
		</div>
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>