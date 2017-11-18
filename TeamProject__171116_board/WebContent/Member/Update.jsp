<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Update.css?v=8" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
    $('.remaining').each(function() {
        var $count = $('.count', this);
        var $input = $(this).prev();
        var maximumCount = $count.text() * 1;
        var update = function() {
            var before = $count.text() * 1;
            var now = maximumCount - $input.val().length;
            if (now < 0) {
                var str = $input.val();
                alert('글자 입력수가 초과하였습니다.');
                $input.val(str.substr(0, maximumCount));
                now = 0;
            }
            if (before != now) {
                $count.text(now);
            }
        };
        $input.bind('input keyup paste', function() {
            setTimeout(update, 0)
        });
        update();
    });
});
</script>
<script type="text/javascript">
function up_check(){
		var pw = $("#pass").val();
		var pw2 = $("#pass2").val();
		var nm = $("#name").val();
		var ag = $("#age").val();
		var sp = $("#sample4_postcode").val();
		var ra = $("#sample4_roadAddress").val();
		var ja = $("#sample4_jibunAddress").val();
		var ph = $("#phone").val();
		var mo = $("#mobile").val();
		var sf = $("#selfinfo").val();
		
		if(pw==""){alert("비밀번호를 입력하세요.");fr.pass.focus();return false;
		}else if(pw.length < 4 || pw.length > 12){alert("비밀번호는 4자 ~ 12자 사이를 입력하세요.");fr.pass.focus();return false;
	 	}else if(pw2==""){alert("비밀번호확인을 입력하세요.");fr.pass2.focus();return false;
		}else if(pw2.length < 4 || pw2.length > 12){alert("비밀번호확인은 4자 ~ 12자 사이를 입력하세요.");fr.pass2.focus();return false;
 		}else if(pw != pw2){alert("비밀번호가 일치하지 않습니다.");fr.pass.focus();return false;
		}else if(nm == ""){alert("이름을 입력하세요.");fr.name.focus();return false;
		}else if(nm.length < 2 || nm.length > 5){alert("이름은 2자 ~ 5자 사이를 입력하세요.");fr.name.focus();return false;
		}else if(ag == ""){alert("나이를 입력하세요.");fr.age.focus();return false;
		}else if(sp == ""){alert("우편번호를 입력하세요.");fr.zip_code.focus();return false;
		}else if(ra == ""){alert("도로명 주소를 입력하세요.");fr.address.focus();return false;
		}else if(ja == ""){alert("지번 주소를 입력하세요.");fr.address2.focus();return false;
		}else if(ph == ""){alert("집전화번호를 입력하세요.");fr.address2.focus();return false;
		}else if(mo == ""){alert("휴대전화번호를 입력하세요.");fr.mobile.focus();return false;
		}else if(sf == ""){alert("자기소개를 입력하세요.");fr.selfinfo.focus();return false;
		}else if(sf.length>100){alert("자기소개는 100자 이내입니다.");fr.selfinfo.focus();return false;
		}else{
			up_save();	
		}
		
}	
</script>
<script type="text/javascript">
function up_save() {
	var str = document.getElementById('update');
	str.submit();
	alert("회원정보가 수정되었습니다.");
}
</script>
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
			<form action="./MemberUpdateAction.me" class="form" name="fr" id="update" method="post" enctype="multipart/form-data">
			<table class="form_table">
				<tr>
					<th class="form_col">아이디</th><td><input type="text" name="id" id="id" value="<%=mb.getId()%>" readonly></td>
				</tr>
				<tr>
					<th>새비밀번호</th><td><input type="password" name="pass" id="pass"><font name="check" size="2" color="red"></font></td>
				</tr>
				<tr>
					<th>새비밀번호확인</th><td><input type="password" name="pass2" id="pass2"></td>
				</tr>
				<tr>
					<th>이름</th><td><input type="text" name="name" id="name" value="<%=mb.getName() %>"></td>
				</tr>
				<tr>
					<th>나이</th><td><input type="text" name="age" id="age" value="<%=mb.getAge()%>"></td>
				</tr>
				<tr>
					<th>성별</th><td>
					<input type="radio" name="gender" value="남자" <% if(mb.getGender() == null || mb.getGender().equals("남자")){ %> checked <%} %>> 남
					<input type="radio" name="gender" value="여자" <% if((mb.getGender()).equals("여자")){ %> checked <% } %>>여</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" class="email" value="<%=mb.getEmail()%>" readonly="readonly"></td>
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
					<th>집전화</th><td><input type="text" name="phone" id="phone" value="<%=mb.getPhone()%>"></td>
				</tr>
				<tr>
					<th>휴대전화</th><td><input type="text" name="mobile" id="mobile" value="<%=mb.getMobile()%>"></td>
				</tr>
				<tr class="fileBox">
				<th class="form_col">프로필</th>
				<td>
				<div class="fileBox">
				<%if(mb.getProfile()!=null){ %>
				<div class="filebox bs3-primary preview-image">
				<div class="upload-display"><div class="upload-thumb-wrap">
				<img src="./upload/<%=mb.getProfile() %>" class="upload-thumb" ></div>
				
				<!-- 기존 파일 -->
              	<input type="hidden" name="PreFile" value="<%=mb.getProfile() %>"></div>
				<!-- 업로드 파일 -->
				<input class="upload-name" value="<%=mb.getProfile() %>" disabled="disabled" style="width: 200px;"> 
				<label for="input_file">업로드</label>
				<input type="file" id="input_file" name="profile" class="upload-hidden">
				<%} %>
				<%if(mb.getProfile()==null){ %>
				<div class="filebox bs3-primary preview-image">
				<input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;"> 
				<label for="input_file">업로드</label>
				<input type="file" id="input_file" name="profile" class="upload-hidden">
				<%}%>
				
				</div>
				</td>
			</tr>
				<tr>
					<th>자기소개</th><td><textarea name="selfinfo" cols="60" rows="5" id="selfinfo" name="selfinfo"><%=mb.getSelfinfo()%></textarea>
					<div class ="remaining">남은 글자수: <span class="count">100</span></div></td>
				</tr>
			</table> 
				<div id="login_btn">

					<input type="button" value="업데이트" class="btn btn_sub" onclick="javascript:up_check()">
					<input type="button" value="취소" onclick="location.href='./MemberInfoAction.me' " class="btn">
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