<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Join.css?v=4" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/script.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
//이메일 인증시 
function jo_em_check(){
	var em = $(".email").val();
 	if(em == ""){
 		alert("이메일을 입력해주세요.")
 		document.fr.email.focus();
 		return false;
 	}
 	window.open(
 			"EmailCheck.em?email="+em,"","top =300,left=630,width=500,height=220");
	}
	
function sub(){
	if(document.fr.emailcheck.checked == false){
		alert("이메일 인증을 해주세요")
		return false;
	}
}
</script>

<script type="text/javascript">
//join 파트		
//회원가입 시 기입내역 검사
function jo_check(){
		var idn = $("#id").val();
		var pw = $("#pass").val();
		var pw2 = $("#pass2").val();
		var nm = $("#name").val();
		var ag = $("#age").val();
		var em = $(".email").val();
		var sp = $("#sample4_postcode").val();
		var ra = $("#sample4_roadAddress").val();
		var ja = $("#sample4_jibunAddress").val();
		var ph = $("#phone").val();
		var mo = $("#mobile").val();
		var pf = $("#input_file").val();
		var sf = $("#selfinfo").val();

		if(idn==""){alert("아이디를 입력하세요");fr.id.focus();return false;
		}else if(idn.length < 4 || idn.length > 12){alert("아이디는 4자 ~ 12자 사이를 입력하세요.");fr.id.focus();return false;
		}else if(pw==""){alert("비밀번호를 입력하세요.");fr.pass.focus();return false;
		}else if(pw.length < 4 || pw.length > 12){alert("비밀번호는 4자 ~ 12자 사이를 입력하세요.");fr.pass.focus();return false;
	 	}else if(pw2==""){alert("비밀번호확인을 입력하세요.");fr.pass2.focus();return false;
		}else if(pw2.length < 4 || pw2.length > 12){alert("비밀번호확인은 4자 ~ 12자 사이를 입력하세요.");fr.pass2.focus();return false;
 		}else if(pw != pw2){alert("비밀번호가 일치하지 않습니다.");fr.pass.focus();return false;
		}else if(nm == ""){alert("이름을 입력하세요.");fr.name.focus();return false;
		}else if(nm.length < 2 || nm.length > 5){alert("이름은 2자 ~ 5자 사이를 입력하세요.");fr.name.focus();return false;
		}else if(ag == ""){alert("나이를 입력하세요.");fr.age.focus();return false;
		}else if(em == ""){alert("이메일을 입력하세요.");fr.email.focus();return false;
		}else if(sp == ""){alert("우편번호를 입력하세요.");fr.zip_code.focus();return false;
		}else if(ra == ""){alert("도로명 주소를 입력하세요.");fr.address.focus();return false;
		}else if(ja == ""){alert("지번 주소를 입력하세요.");fr.address2.focus();return false;
		}else if(ph == ""){alert("집전화번호를 입력하세요.");fr.address2.focus();return false;
		}else if(mo == ""){alert("휴대전화번호를 입력하세요.");fr.mobile.focus();return false;
		}else if(pf == ""){alert("프로필 사진을 넣어주세요.");fr.profile.focus();return false;
		}else if(sf == ""){alert("자기소개를 입력하세요.");fr.selfinfo.focus();return false;
		}else if(sf.length>100){alert("자기소개는 100자 이내입니다.");fr.selfinfo.focus();return false;
		}
		if($('input:checkbox[id="emailcheck"]').is(":checked") == true){
			jo_save();	
		 }else{
		   alert("이메일을 인증해주세요.");
			return false;
		 }
		
// 		else{save();}
}	
</script>
<script type="text/javascript">
function jo_save() {
	var str = document.getElementById('join');
	str.submit();
	alert("가입이 완료되었습니다. 가입시 10,000,000 마일리지가 지급됩니다.");
}
</script>

<script type="text/javascript">
//join 파트
//아이디 중복검사 여부
$(function() {
	$('#id').keyup(function() {
		$.ajax({
			type : "POST",
			url : "MemberIdCheck.me",
			data : {
				"id" : $('#id').val()
			},
			success : function(data) {
				$('#idchk').html(data);
			}
		});
	});
});
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

	    

	});//
</script>
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<h1>회원가입</h1>
			<br>
			<form action="./MemberJoinAction.me" class="form" name="fr" id="join"
				method="post" enctype="multipart/form-data" >
				<table class="form_table">
					<tr>
						<td class="table_text">개인 신상정보</td>
					</tr>
					<tr class="form_row">
						<td class="form_col">아이디</td>
						<td><input type="text" name="id" class="id" id="id"><span
							id="idchk"></span></td>
					</tr>
					<tr>
						<td class="form_col">비밀번호</td>
						<td class="width_pass"><input type="password" name="pass"
							id="pass"><font name="check" size="2" color="red"></font></td>
					</tr>
					<tr>
						<td class="form_col">비밀번호확인</td>
						<td><input type="password" name="pass2" id="pass2"></td>
					</tr>

					<tr>
						<td class="form_col">이름</td>
						<td><input type="text" name="name" id="name"></td>
					</tr>

					<tr>
						<td class="form_col">나이</td>
						<td><select class="age" name="age">
								<%
									for (int i = 1; i <= 100; i++) {
								%><option value="<%=i%>"><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td class="form_col">성별</td>
						<td class="form_col form_col_radio""><input type="radio"
							name="gender" value="남자" checked>남자 <input type="radio"
							name="gender" value="여자" class="form_radio_g">여자</td>
					</tr>
					<tr>
						<td class="form_col">이메일</td>
						<td>
						
<!-- 				원본 -->	
<!-- 						<input type="text" name="email" class="email"> -->
<!-- 				추가 -->
						<input type="email" name="email" class="email" requried>
						<input type="button" value="이메일인증" class="dup" onclick="jo_em_check()">
						<input type="checkbox" id="emailcheck" disabled="disabled" > 인증여부
						
						</td>
					</tr>

	<!-- 			이메일 인증을 통한 회원가입 -->
	<!-- 			<label><span style="color:red">*</span>이메일</label> -->
	<!-- 			<input type="email" name="email" required> -->
	<!-- 			<input type="button" value="이메일인증" class="dup" onclick="echeck()"> -->
	<!-- 			인증여부:<input type="checkbox" id="emailcheck" disabled="disabled"><br> -->
				
				
	<!-- 			내용 기입하여 회원가입 -->
	<!-- 			<label><span style="color:red">*</span>이메일</label> -->
	<!-- 			<input type="email" name="email"><br> -->

					
					<tr>
						<td class="form_col">주소</td>
						<td><input type="text" name="zip_code" id="sample4_postcode"
							placeholder="우편번호" readonly> <input type="button"
							onclick="sample4_execDaumPostcode()" value="우편번호 찾기"
							class="dup button_sub"><br> <input type="text"
							size="40" name="address" id="sample4_roadAddress"
							placeholder="도로명주소"><br> <input type="text"
							size="40" name="address2" id="sample4_jibunAddress"
							placeholder="지번주소"> <span id="guide" style="color: #999;"></span></td>
					</tr>
					<tr>
						<td class="form_col">집전화</td>
						<td><input type="text" name="phone" id="phone"></td>
					</tr>
					<tr>
						<td class="form_col">휴대전화</td>
						<td><input type="text" name="mobile" id="mobile"></td>
					</tr>
					<tr class="fileBox">
						<td class="form_col">프로필</td>
						<td>
							<div class="filebox bs3-primary preview-image">
								<input class="upload-name" value="파일선택" disabled="disabled"
									style="width: 200px;"> <label for="input_file">업로드</label>
								<input type="file" id="input_file" name="profile" class="upload-hidden" id="profile" >
							</div>
						</td>
					</tr>
					<tr>
						<td class="form_col">자기소개</td>
						<td><textarea name="selfinfo" cols="60" rows="5" id="selfinfo"></textarea>
						<div class ="remaining">남은 글자수: <span class="count">100</span></div>
						</td>
					</tr>
				</table>
				<div id="login_btn">
					<input type="button" value="Join" class="button_all button_submit" onclick="javascript:jo_check()">
					<input type="button" value="Cancel" class="button_all" onclick="location.href='./Main.me'"/>
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