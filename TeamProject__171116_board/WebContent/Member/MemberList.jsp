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
<link href="css/MemberList.css?v=5" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	  $('table tr:odd').css("backgroundColor","white");         
	  $('table tr:even').css("backgroundColor","#EBEBEB");
	  $('.first_tr').css("backgroundColor","#666");
	}); 
</script>
<script>
//삭제할 아이디를 넘김
    function idDelete(delID){
	if(confirm("정말 삭제하시겠습니까?")==true){
		location.href = "./MemberDel.me?id=" + delID;
	}else{
		return;
	}
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
		<tr class="first_tr">
		<th id="pro">프로필</th>
		<th id="id">아이디</th>
		<th id="pass">비밀번호</th>
		<th id="name">이름</th>
		<th id="reg_date">가입날짜</th>
		<th id="age">나이</th>
		<th id="gender">성별</th>
		<th id="email">이메일</th>
		<th id="zip">우편번호</th>
		<th id="add">주소1</th>
		<th id="add2">주소2</th>
		<th id="phone">집전화</th>
		<th id="mobile">휴대전화</th>
		<th id="info">자기소개</th>
		<th id="delete">구분</th>
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
            <td><%=mb.get(i).getZip_code() %></td>
            <td><%=mb.get(i).getAddress() %></td> 
            <td><%=mb.get(i).getAddress2() %></td>
            <td><%=mb.get(i).getPhone() %></td>
            <td><%=mb.get(i).getMobile() %></td>
<%    String info_content=mb.get(i).getSelfinfo();
if(mb.get(i).getSelfinfo().length()>16){
	info_content=mb.get(i).getSelfinfo().substring(0,15)+"...";
} %>
			<td data-toggle="tooltip" data-placement="bottom" title="<%=mb.get(i).getSelfinfo()%>"><%=info_content %></td>
			<td>
			<%
            if("admin".equals(mb.get(i).getId())){
            %>
            <label><span style="color:red; font-weight: bold;">불가</span></label></td>
            <%
            }else{
            %><input type="button" value="탈퇴" class="delete_btn" onclick="idDelete('<%=mb.get(i).getId() %>');"></td>
            <%
            }
            %>
        </tr>
    <%
    } 
    %>
	</table>
	<div  class="MemberList_count_wrap">
		<div class="MemberList_count">
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
		</div>
	</div>
	<div class="MemberList_back_wrap">
		<div class="MemberList_back"><a href="./Main.me">메인으로</a></div>
	</div>
	</div>
	<!-- 본문 -->
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>