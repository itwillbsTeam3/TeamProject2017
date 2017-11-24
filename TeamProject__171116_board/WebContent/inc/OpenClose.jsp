<%@page import="net.Host.db.HostingDAO"%>
<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HostingBean hb = new HostingBean();
HostingDAO hdao = new HostingDAO();
String id = (String)session.getAttribute("id");
String text = hdao.OpenClose(id);
%>
<%=text%>