<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC
"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="dao" class="net.member.db.MemberDAO" />	
	<%     
        int result = dao.delMemberlist(request.getParameter("id"));
        String message="";
        if(result == 0 )
        {
        	message = "탈퇴되었습니다.";
        }else{
        	message = "탈퇴실패.";
        }
    %>
	
<!-- 	해당 회원 삭제시 메시지 출력후 리스트로 이동 -->
	<script>   
        alert("<%=message%>");
        location.href="./MemberList.me";  
    </script>
</body>
</html>