<%@page import="java.text.DecimalFormat"%>
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
	DecimalFormat dc = new DecimalFormat("#,###");
%>
<link href="./css/default.css?v=17" rel="stylesheet" type="text/css">
<link href="./css/history.css?v=6" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	  $('.pay_table tr:odd').css("backgroundColor","white");         
	  $('.pay_table tr:even').css("backgroundColor","#EBEBEB");
	  $('.first_tr').css("backgroundColor","#666")
	}); 
<%
String s="";
%>
</script>
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="pay_body">
		<h1>결제내역</h1>
		<table class="pay_table">
			<tr class="first_tr">
				<td class="Pay_td td1">구분</td>
				<td class="Pay_td td2">금액</td>
				<td class="Pay_td td3">결제일자</td>
			</tr>
			<%
				for (int i = 0; i < hb.size(); i++) {
					if (hb.get(i).getFlag() == 1) {
			%>
			<tr>
				<td>사용</td><%s="-"; %>
				<%
					} else if(hb.get(i).getFlag() == 0){
				%>
			<tr>
				<td>충전</td><%s="+"; %>
				<%
					} else if(hb.get(i).getFlag() == 2){
				%>
			<tr>
				<td>입금</td><%s="+"; %>
				<%
					} else if(hb.get(i).getFlag() == 3){
				%>
			<tr>
				<td>예약취소금</td><%s="+"; %>
				<%
					} else if(hb.get(i).getFlag() == 4){
				%>
			<tr>
				<td>환불금</td><%s="-"; %>
				<%
					}
				%>
				<td><%=s+dc.format(hb.get(i).getMileage())%>원</td>
				<td><%=hb.get(i).getDate()%></td>
			</tr>
			<%
				}
			%>
		</table>
			<div class="main_wrap">
			<div class="history_back payhistory"><a href="./Main.me">메인으로</a></div>
			</div>
		</div>
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>