<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Update.css?v=4" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	   var fileTarget = $('.filebox .upload-hidden');

	    fileTarget.on('change', function(){
	        if(window.FileReader){
	            // 파일명 추출
	            var filename = $(this)[0].files[0].name;
	        } 

	        else {
	            // Old IE 파일명 추출
	            var filename = $(this).val().split('/').pop().split('\\').pop();
	        };

	        $(this).siblings('.upload-name').val(filename);
	    });

	    //preview image 
	    var imgTarget = $('.preview-image .upload-hidden');
		
	    imgTarget.on('change', function(){
	        var parent = $(this).parent();
	        parent.children('.upload-display').remove();

	        if(window.FileReader){
	            //image 파일만
	            if (!$(this)[0].files[0].type.match(/image\//)) return;
	            
	            var reader = new FileReader();
	            reader.onload = function(e){
	                var src = e.target.result;
	                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
	            }
	            reader.readAsDataURL($(this)[0].files[0]);
	        }

	        else {
	            $(this)[0].select();
	            $(this)[0].blur();
	            var imgSrc = document.selection.createRange().text;
	            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

	            var img = $(this).siblings('.upload-display').find('img');
	            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
	        }
	    });
	});
</script>
</head>
<body>
	<%
	String id = (String)session.getAttribute("id");
	String pass = (String)session.getAttribute("pass");
	String pass2 = (String)request.getParameter("pass2");
	if(id == null){
		response.sendRedirect("./MemberLogin.me");
	}
	//requeset 속성값 가져오기
	MemberBean mb = (MemberBean)request.getAttribute("mb");
	%>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<h1>업데이트</h1><br>
			<form action="./MemberUpdateAction.me" class="form" name="fr" id="join" method="post" onsubmit="return check2()" enctype="multipart/form-data">
			<table class="form_table">
				<tr>
					<th class="form_col">아이디</th><td><input type="text" name="id" value="<%=mb.getId()%>" readonly></td>
				</tr>
				<tr>
					<th>새비밀번호</th><td><input type="password" name="pass" id="pass"><font name="check" size="2" color="red"></font></td>
				</tr>
				<tr>
					<th>새비밀번호확인</th><td><input type="password" name="pass2" id="pass2"></td>
				</tr>
				<tr>
					<th>이름</th><td><input type="text" name="name" value="<%=mb.getName() %>"></td>
				</tr>
				<tr>
					<th>나이</th><td><input type="text" name="age" value="<%=mb.getAge()%>"></td>
				</tr>
				<tr>
					<th>성별</th><td>
					<input type="radio" name="gender" value="남자" 
					<% if(mb.getGender() == null || mb.getGender().equals("남자")){ %> checked <%} %>> 남자
					<input type="radio" name="gender" value="여자" 
					<% if((mb.getGender()).equals("여자")){ %> checked <% } %>>여자</td>
				</tr>
				<tr>
					<th>이메일</th><td><input type="text" name="email" value="<%=mb.getEmail() %>"></td>
				</tr>
				<tr>
					<th>우편번호</th><td><input type="text" name="zip_code" id="sample4_postcode" value="<%=mb.getZip_code()%>" readonly>
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="dup"></td>
				</tr>
				<tr>
					<th>도로명 주소</th><td><input type="text" size="30" name="address" id="sample4_roadAddress" value="<%=mb.getAddress()%>"></td>
				</tr>
				<tr>
					<th>지번 주소</th><td><input type="text" size="30" name="address2" id="sample4_jibunAddress" value="<%=mb.getAddress2()%>"><span id="guide" style="color:#999;"></span></td>
				</tr>
				
				<tr>
					<th>집전화</th><td><input type="text" name="phone" value="<%=mb.getPhone()%>"></td>
				</tr>
				<tr>
					<th>휴대전화</th><td><input type="text" name="mobile" value="<%=mb.getMobile()%>"></td>
				</tr>
				<tr class="fileBox">
				<td class="form_col">프로필</td>
				<td>
				<div class="fileBox">
				<%if(mb.getProfile()!=null){ %>
				<div class="filebox bs3-primary preview-image">
				<div class="upload-display"><div class="upload-thumb-wrap"><img src="./upload/<%=mb.getProfile() %>" class="upload-thumb" ></div></div>
				<input class="upload-name" value="<%=mb.getProfile() %>"  disabled="disabled"
				style="width: 200px;"> <label for="input_file">업로드</label>
				<input type="file" id="input_file" name="profile" class="upload-hidden">
				<%} %>
				<%if(mb. getProfile()==null){ %>
				<div class="filebox bs3-primary preview-image">
				<input class="upload-name" value="파일선택" disabled="disabled"
				style="width: 200px;"> <label for="input_file">업로드</label>
				<input type="file" id="input_file" name="profile" class="upload-hidden">
				<%} %>
				</div>
				</td>
			</tr>
				<tr>
					<th>자기소개</th><td><textarea name="selfinfo" cols="60" rows="5" name="selfinfo"><%=mb.getSelfinfo()%></textarea></td>
				</tr>
			</table> 
				<div id="login_btn">
					<input type="submit" value="Update" class="btn btn_sub"> 
					<input type="reset" value="Cancel" class="btn">
				</div>
			</form>
		</div>
				<script>
		var uploadFile = $('.fileBox .uploadBtn');
		uploadFile.on('change', function(){
			if(window.FileReader){
				var filename = $(this)[0].files[0].name;
			} else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings('.fileName').val(filename);
		});

		</script>
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>