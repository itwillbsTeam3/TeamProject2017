<%@page import="net.alram.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	
	AlramDAO alram = new AlramDAO();
	alram.setsysflag(num);
	
	response.sendRedirect("./Alram.ar");
%>
<html>
<head></head>
<body>
<%=num%>
</body>
</html>
