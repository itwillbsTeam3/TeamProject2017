<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/MessageSending.css?v=11" rel="stylesheet"
	type="text/css">
</head>
<body>
	<%
		HostingBean hb = (HostingBean) request.getAttribute("hb");
		String host_id = request.getParameter("host_id");
		String id = (String) session.getAttribute("id");

		if (id == null) {
	%>
	<script>
		alert("로그인 해주세요.");
		location.href = "./MemberLogin.me";
		self.close();
	</script>
	<%
		}
	%>
	<%
	if (host_id.equals(id)) {
	%>
	<script>
		alert("동일 아이디는 메시지 보내기가 불가능합니다.");
		self.close();
	</script>
	<%
		}
	%>
	
	<form action="./MessageSendingAction.ms" method="post">
		<h2>호스트에게 메시지 보내기</h2>
		<table class="message_table">
			<tr>
				<td class="right">호스트ID(받는이) </td>
				<td><input type="text" name="rid" class="rid" id="rid"
					value="<%=host_id%>" placeholder="target" readonly="readonly"></td>
			</tr>
			<!-- 받는 사람 -->
			<tr>
				<td class="right">세션ID(보낸이) </td>
				<td><input type="text" name="sid" class="sid" id="sid" value="<%=id%>"
				placeholder="name" readonly="readonly"></td>
			</tr>
			<!-- 보내는 사람 -->
			<tr><td class="right">메시지 입력 </td>
			<td>
			<textarea rows="10" cols="20" name="message" class="message"
				id="message"></textarea></td>
				</tr>
		</table>
		<div style="text-align : center;"><input type="submit" value="메시지 보내기" class="btn"></div>
	</form>
</body>
</html>