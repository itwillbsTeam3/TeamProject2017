<%@page import="net.email.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String y = request.getParameter("y");
String email = request.getParameter("email");

EmailDAO edao = new EmailDAO();
edao.send(email,y);
%>