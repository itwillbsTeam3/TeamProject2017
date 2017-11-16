<%@page import="net.member.db.MemberDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<style type="text/css">
#a1{font-size: 12pt; color: red;}
#a2{font-size: 12pt; color: blue;}
</style>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    int flag = 0;
    MemberDAO mdao = new MemberDAO();
    
    if(id == null){
        id = "";
    }
    if(!id.equals("")){
        flag = mdao.joinIdCheck(id);
    }
    if(flag==1){    // 이미 존재하는 계정
	%><span><strong><a id="a1">해당 아이디는 사용이 불가합니다.</a></strong></span><% 
    }else{        // 사용가능한 계정
    %><span><strong><a id="a2">해당 아이디는 사용이 가능합니다.</a></strong></span><%
    }
	%>
</html>