<%@page import="net.booking.action.Mydatecarculator"%>
<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>booking</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/booking.css?v=4" rel="stylesheet" type="text/css">

<%
request.setCharacterEncoding("UTF-8");
HostingBean hb= (HostingBean)request.getAttribute("hb");
// int num=1;//임시값
String subject=(String)request.getParameter("subject");
String host_id=request.getParameter("host_id");
// String guest_id=(String)session.getAttribute("id");
String checkin=request.getParameter("checkin");
String checkout=request.getParameter("checkout");
int price=Integer.parseInt(request.getParameter("price"));

Mydatecarculator master = new Mydatecarculator();
int y,m,d,yy,mm,dd;
String []qe = checkin.split("-");
yy =Integer.parseInt(qe[0]);
mm =Integer.parseInt(qe[1]);
dd =Integer.parseInt(qe[2]);
String []qee = checkout.split("-");
y =Integer.parseInt(qee[0]);
m =Integer.parseInt(qee[1]);
d =Integer.parseInt(qee[2]);
int interval = master.GetDifferenceOfDate(y, m, d, yy, mm, dd);
%>


</head>
    
<body>
<div id="booking_wrap">
<div id="booking_logo">
	<img src="./img/hoster.png">
	<div class="clear"></div>
</div>

    	<h2>예약할 숙소 정보</h2>
    	<form action="./HostingBookingAction.bo" class="booking" method="post">
        <div id="subject"><%=subject %></div>
    	<dl class="dl-horizontal">
        <dt>호스트</dt><dd><%=host_id %></dd>
        <dt>예약예정일</dt><dd> <%=checkin %> ~  <%=checkout %></dd>
        <dt>예약자</dt> <dd><%=session.getAttribute("id") %></dd>
        <dt>1박기준 금액</dt> <dd><%=price %></dd>
        <dt>결제방법</dt><dd>
        <label class="radio-inline">
       	<input type="radio" name="ra" class="ok" id="ok1" value="카드결제" >카드결제
        </label>
        <label class="radio-inline">
			<input type="radio" name="ra" class="ok" id="ok2"  value="계좌이체">계좌이체
		</label>
		<label class="radio-inline">
        	<input type="radio" name="ra" class="ok" id="ok3"  value="마일리지결제">마일리지결제
        </label></dd>
		<dt>결제금액<dt> <dd><%=price*interval%></dd>
		<dt>요구사항<dt> <dd><textarea name="etc" rows="5" cols="50%" style="padding-left:5px;"></textarea></dd>
		</dl>
			<input type="hidden" name="subject" value="<%=subject%>">
			<input type="hidden" name="host_id" value="<%=host_id%>">
			<input type="hidden" name="checkin" value="<%=checkin%>">
			<input type="hidden" name="checkout" value="<%=checkout%>">
			<input type="hidden" name="price"  value="<%=price%>">
			<input type="hidden" name="num"  value="<%=hb.getNum()%>">
			<input type="submit" value="예약하기" style="float:right; margin-bottom:10px;">		
		</form>
</div>
</body>
</html>