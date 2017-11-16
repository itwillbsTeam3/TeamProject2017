<%@page import="java.util.ArrayList"%>
<%@page import="net.member.db.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/member.css?v=2" rel="stylesheet" type="text/css">

<script>
//삭제할 아이디를 넘김
    function idDelete(delID){
		location.href = "./MemberDel.me?id=" + delID;
	}   
</script>

<title>Insert title here</title>
</head>
<body>
<%
	MemberDAO mdao = new MemberDAO();
	ArrayList<MemberBean> mb = (ArrayList<MemberBean>)request.getAttribute("mb");
	
	int count = ((Integer) request.getAttribute("count")).intValue();
	String pageNum = (String) request.getAttribute("pageNum");
	int pageSize = ((Integer) request.getAttribute("pageSize")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	%>
<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
		<h1>회원 목록 및 관리</h1><h2>회원 수 : <%=count%>명</h2>
	
	<table border="1">
		<tr>
		<th>프로필</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>가입날짜</th>
		<th>나이</th>
		<th>성별</th>
		<th>이메일</th>
		<th>주소1</th>
		<th>주소2</th>
		<th>우편번호</th>
		<th>집전화</th>
		<th>휴대전화</th>
		<th>자기소개</th>
		<th>구분</th>
		</tr>
		<%
        for(int i=0; i< mb.size();i++){
    %>            
        <tr>
        	<td><img src="./upload/<%=mb.get(i).getProfile() %>" width="50px" height="50px" style="border-radius:50px;"></td>
            <td><%=mb.get(i).getId() %></td>
            <td><% String ps = mb.get(i).getPass().toString(); %>
            <%=ps.replaceAll("(?<=.{2}).", "*") %></td>
            <td><%=mb.get(i).getName() %></td>
            <td><% String reg = mb.get(i).getReg_date().toString(); %>
            <%=reg.substring(0, reg.length()-10)%></td>
            <td><%=mb.get(i).getAge() %></td>
            <td><%=mb.get(i).getGender() %></td>
            <td><%=mb.get(i).getEmail() %></td> 
            <td><%=mb.get(i).getAddress() %></td> 
            <td><%=mb.get(i).getAddress2() %></td>
            <td><%=mb.get(i).getZip_code() %></td>
            <td><%=mb.get(i).getPhone() %></td>
            <td><%=mb.get(i).getMobile() %></td>
			<td><%=mb.get(i).getSelfinfo() %></td>
			<td>
			<%
            if("admin".equals(mb.get(i).getId())){
            %>
            <label><span style="color:red; font-weight: bold;">불가</span></label></td>
            <%
            }else{
            %><input type="button" value="탈퇴" onclick="idDelete('<%=mb.get(i).getId() %>');"></td>
            <%
            }
            %>
        </tr>
    <%
    } 
    %>
	</table>
	<%
		if (count != 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 10;
			int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			//이전
			if (startPage > pageBlock) {%><a href="./MemberList.me?pageNum=<%=startPage - pageBlock%>">[이전]</a><%}
			// 1~10
			for (int i = startPage; i <= endPage; i++) {%><a href="./MemberList.me?pageNum=<%=i%>">[<%=i%>]</a><%}
			//다음
			if (endPage < pageCount) {%><a href="./MemberList.me?pageNum=<%=startPage + pageBlock%>">[다음]</a><%}
		}
	%>
	<br><a href="./Main.me">이전으로</a><br>
	</div>
	<!-- 본문 -->
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>