<%@page import="net.booking.db.BookingBean"%>
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
	ArrayList<BookingBean> hb = (ArrayList<BookingBean>) request.getAttribute("hb"); //호스팅글 정보
	int a = 0;
%>
<link href="./css/default.css?v=17" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="body">
		<h1>예약내역</h1>
		<table border="1px">
			<tr>
				<td></td>
				<td>숙소이름</td>
				<td>호스트</td>
				<td>체크인</td>
				<td>체크아웃</td>
				<td>결제액</td>
				<td>결제일자</td>
			</tr>
			<%
				for (int i = 0; i < hb.size(); i++) {
			%>
			<tr>
				<td><%=a = i + 1%></td>
				<td><%=hb.get(i).getSubject()%></td>
				<td><%=hb.get(i).getHost_id()%></td>
				<td><%=hb.get(i).getCheckin()%></td>
				<td><%=hb.get(i).getCheckout()%></td>
				<td><%=hb.get(i).getPrice()%></td>
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