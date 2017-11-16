<%@page import="net.booking.db.BookingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script> var vm = jQuery.noConflict(); </script>

<title>Insert title here</title>
<%
BookingDAO dao = new BookingDAO();


%>
<script type="text/javascript">
function check(){
alert("ddddddddddd");
vm.ajax({
	type : "POST",
	url : "bookingcheck.jsp",
	data : {
		"host_id" : "test3",
        "checkin" : "2017-11-15",
        "checkout" : "2017-11-16"
	},
	success : function(data) {
		vm('#idchk').html(data);
	},
	error : function(){
		alert("오류가 발생");
	}
});
}
</script>
</head>
<body>
<%=dao.checkbooking("test3", "2017-11-15", "2017-11-16") %>
<input type="button" id="dddd" value="ttt" onclick="check()">
<span id="idchk"></span>
</body>
</html>