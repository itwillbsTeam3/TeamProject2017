<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
HostingBean hb = (HostingBean)request.getAttribute("hb");
String host_id = request.getParameter("host_id");
String id = (String)session.getAttribute("id");

if (id == null) {
	%>
	<script>
	alert("로그인 해주세요.");
	location.href="./MemberLogin.me";
	self.close();
	</script>
	<%
}
%>
		<form action="./MessageSendingAction.ms" method="post">
	        <h2>호스트에게 메시지 보내기</h2>	        
	        호스트ID(받는이)<input type="text" name="rid" class="rid" id="rid" value="<%=host_id %>"placeholder="target" readonly="readonly"><br> <!-- 받는 사람 -->
	        세션ID(보낸이)<input type="text" name="sid" class="sid" id="sid"  value="<%=id %>" placeholder="name" readonly="readonly"><br> <!-- 보내는 사람 -->
	        메시지:
	        <textarea rows="10" cols="20" name="message" class="message" id="message"></textarea><br>
	       <input type="submit" value="메시지 보내기">  
       </form>
</body>
</html>