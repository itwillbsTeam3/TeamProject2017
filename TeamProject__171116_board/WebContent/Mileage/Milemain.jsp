<%@page import="net.mileage.db.MileBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Milemain.css?v=5" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style type="text/css">
<style>
th,td {padding:15px;}
td{float:left;}
table{border-spacing:20px;}
</style>
<script type="text/javascript">
function mi_ch_check(){
	
	var mn = $("#m_num").val();
	var mNumEx = /^[0-9]{4}-[0-9]{4}-[0-9]{9}$/;
	 
	var mil = $("#mileage").val();
	
	 if(!mNumEx.test(mn)) {
		 alert("요청실패. 코드를 정확히 입력해주세요.");
		return false;	  
	}else if(mn==""){
		alert("코드를 입력해주세요");
		fr.m_num.focus();
		return false;
	}else if(mil==""){
		alert("충전 금액을 선택해주세요.");
		fr.mileage.focus();
		return false;
	}else{
		mil_charge();
	}
	
}	

</script>
<script type="text/javascript">
function mil_charge() {
	var str = document.getElementById('charge');
	str.submit();
	alert("마일리지가 정상적으로 충전되었습니다.");
}
</script>
</head>
<body>
<%
	MileBean mb = (MileBean)request.getAttribute("mb");
	%>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<h1>마일리지 충전</h1><br>
			<form action="./MileageUpdateAction.mi" method="post" class="form" name="fr" id="charge">
				<table class="form_table">
				<tr>
				<th>충전방법</th>
				<td><img alt="" src="./img/m_pic.jpg" width="150px" height="75px" ></td>
				</tr>
				<tr>
				<th>코드입력</th>
				<td><input type="text" name="m_num" id="m_num" placeholder="ex)예시 번호[하이픈('-'포함')] : 1111-1111-111111111" size="55"></td>
				</tr>
					<tr class="form_row">
						<th class="form_col">금액선택</th>
						<td>
						<select id="mileage" name="mileage" id="mileage"> 
					        <option value="">선택</option>
					        <option value="1000">1,000원</option> 
					        <option value="5000">5,000원</option> 
					        <option value="10000">10,000원</option> 
					        <option value="50000">50,000원</option> 
					        <option value="100000">100,000원</option> 
  						</select>
						</td>
					</tr>
				</table>
					<div id="login_btn">
						<input type="button" value="충전" class="btn btn_submit" onclick="javascript:mi_ch_check()"> 
						<input type="button" value="Cancel" class="btn" onclick="location.href='./Main.me'"/>
					</div>
			</form>
		</div>		
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>