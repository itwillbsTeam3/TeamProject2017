<%@page import="java.text.DecimalFormat"%>
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
	DecimalFormat dc = new DecimalFormat("#,###");
%>
<link href="./css/default.css?v=17" rel="stylesheet" type="text/css">
<link href="./css/history.css?v=5" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	  $('.hosting_table tr:odd').css("backgroundColor","white");         
	  $('.hosting_table tr:even').css("backgroundColor","#EBEBEB");
	  $('.first_tr').css("backgroundColor","#666")
	}); 
</script>
</head>
<body>
<div class="wrap">
	<!--헤더들어가는곳-->
	<jsp:include page="../inc/header.jsp" />
	<!--헤더들어가는곳-->
	<div class="pay_body">
	<h1>호스팅내역</h1>
	<table class="hosting_table">
		<tr class="first_tr">
			<td class="Hos_td td1">No.</td>
			<td class="Hos_td td2">숙소이름</td>
			<td class="Hos_td td3">예약자</td>
			<td class="Hos_td td4">체크인</td>
			<td class="Hos_td td5">체크아웃</td>
			<td class="Hos_td td6">결제액</td>
			<td class="Hos_td td7">결제일자</td>
			<td class="Hos_td td8">취소</td>
			<td class="Hos_td td9">취소승인</td>
		</tr>
		<%
			for (int i = 0; i < hb.size(); i++) {
		%>
		<form action="./HistorydeleteAction.hi?num=<%=hb.get(i).getNum() %>&flag=4" method="post">
		<tr>
			<td><%=a=i+1%></td>
			<td class="Hos_td td2"><%=hb.get(i).getSubject()%></td>
			<td><%=hb.get(i).getGuest_id()%></td>
			<td><%=hb.get(i).getCheckin()%></td>
			<td><%=hb.get(i).getCheckout()%></td>
			<td><%=dc.format(hb.get(i).getPrice())%>원</td>
			<td><%=hb.get(i).getDate()%></td>
			<td><input type="submit" value="취소" class="cancel"></td>
			<td><%if(hb.get(i).getFlag()==3){%><input type="submit" value="승인" class="cancel"><%} %></td>
		</tr>
		</form>
		<%
			}
		%>
		</table>

	<div class="main_wrap">
		<div class="history_back"><a href="./Main.me">메인으로</a></div>
	</div>
	</div>
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>