<%@page import="net.history.db.HistoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<HistoryBean> hb = (ArrayList<HistoryBean>) request.getAttribute("hb"); //호스팅글 정보
%>
<link href="./css/default.css?v=17" rel="stylesheet" type="text/css">
<link href="./css/history.css?v=5" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="pay_body">
		<h1>결제내역</h1>
		<table class="pay_table">
			<tr>
				<td class="Pay_td td1">구분</td>
				<td class="Pay_td td2">금액</td>
				<td class="Pay_td td3">결제일자</td>
			</tr>
			<%
				for (int i = 0; i < hb.size(); i++) {
					if (hb.get(i).getFlag() == 1) {
			%>
			<tr>
				<td>사용</td>
				<%
					} else {
				%>
			<tr>
				<td>충전</td>
				<%
					}
				%>
				<td><%=hb.get(i).getMileage()%></td>
				<td><%=hb.get(i).getDate()%></td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>