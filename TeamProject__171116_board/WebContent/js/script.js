//약관 동의
function chkok(){
	 var req = document.regfr.req.checked;
	 var req1 = document.regfr.req1.checked;
	 var num = 0;
	 if(req == true && req1 == true){
		 num = 1;
	 }
	 if(num == 1){
		 document.regfr.submit();
	 }else{
		 alert("개인정보 약관에 동의하셔야 합니다.");    
	 }
   return false;
}
function chkno(){
	 alert("동의하지 않으면 가입하실 수 없습니다");	
}

//join 파트
//비밀번호 실시간 일치여부
$(function(){
$('#pass').keyup(function(){
$('font[name=check]').text('');
$('font[name=check]').css('color','red').css('font-size','12pt');
$('font[name=check]').html("&nbsp;<strong>비밀번호가 일치하지 않거나 없습니다.</strong>");
}); 

$('#pass2').keyup(function(){
if($('#pass').val()!=$('#pass2').val()){
$('font[name=check]').text('');
$('font[name=check]').css('color','red').css('font-size','12pt');
$('font[name=check]').html("&nbsp;<strong>비밀번호가 일치하지 않습니다.</strong>");
}else{
$('font[name=check]').text('');
$('font[name=check]').css('color','green').css('font-size','12pt');
$('font[name=check]').html("&nbsp;<strong>비밀번호가 일치합니다.</strong>");
}
}); 
});

//join 파트
//주소 찾기 - 다음 API 사용
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample4_roadAddress').value = fullRoadAddr;
            document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}

//로그인 파트 아이디 찾기
function searchId(){
  var form = document.id;
  if (form.name.value == '' || form.name.value == null){
   alert('이름을 입력하세요.');
   form.name.focus();
   return false;
  }else {
   form.submit;
  }

  if (form.mobile.value == '' || form.mobile.value == null){
   alert('휴대전화번호를 입력하세요.');
   form.mobile.focus();
   return false;
  }else {
   form.submit;
  }
}

//로그인 파트 비밀번호 찾기
function searchPw(){
  var form = document.pass;
  if (form.id.value == '' || form.id.value == null){
   alert('아이디를 입력하세요.');
   form.id.focus();
   return false;
  }else {
   form.submit;
  }


  if (form.mobile.value == '' || form.mobile.value == null){
   alert('휴대전화번호를 입력하세요.');
   form.mobile.focus();
   return false;
  }else {
   form.submit;
  }
  
  if (form.email.value == '' || form.email.value == null){
   alert('이메일을 입력하세요.');
   form.email.focus();
   return false;
  }else {
   form.submit;
  }
}

//회원정보 수정 전 확인 및 회원탈퇴 파트
function fun_pass(){
	if(document.fr.pass.value==""){
		alert("비밀번호를 입력하세요");
		document.fr.pass.focus();
		return false;
	}	
}

//이미지 업로드/수정 시
//이미지 섬네일 미리보기
function getThumbnailPrivew(html, $target) {
    if (html.files && html.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $target.css('display', '');
            $target.html('<img src="' + e.target.result + '" border="0" alt="" />');
        }
        reader.readAsDataURL(html.files[0]);
    }
}


