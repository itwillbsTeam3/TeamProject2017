<%@page import="net.booking.db.BookingDAO"%>
<%@page import="net.member.db.MemberDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("UTF-8");
	String host_id = request.getParameter("host_id");
    String checkin = request.getParameter("checkin");
    String checkout = request.getParameter("checkout");
    int flag = 0;
    BookingDAO bkdao = new BookingDAO();
    flag = bkdao.checkbooking(host_id, checkin, checkout);
    
    if(flag==0){    
	%>0<% 
    }else if(flag==1){       
    %>1<%
    }
	%>
