<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원가입 </title>
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/Joinreg.css?v=4" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#req2").click(function(){
        if($("#req2").prop("checked")){
            $("input[name=req]").prop("checked",true);
            $("input[name=req1]").prop("checked",true);
        }else{
        	 $("input[name=req]").prop("checked",false);
             $("input[name=req1]").prop("checked",false);
        }
    })
})
</script>
</head>
<body>
	<div id="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>	
		<!-- 본문 -->
		<div class="body">
		 <form id="join" action="./MemberJoin.me" name="regfr" method="post" onsubmit="return chkok()">
			<div class ="Join_Main">
			<h1><span style="color:#525252;">회원가입</span> </h1>
			<h3 style="color:#ccc; margin:0 0 10px 5px; ">
			<span style="color:#525252;">약관 동의</span></h3></div>
		<div class="content">
			<span><label>모두 동의</label>
			<input type="checkbox" name="req2" id="req2" class="che_box che_box_all">
			</span>
			<div  style="border-bottom : 1px solid #DBDBDB"></div><br>
			<span><label> 개인정보 수집 및 이용에 동의합니다. </label>
			<input type="checkbox" name="req" id="req" class="che_box che_box_per1"> <br>
			</span>
		    <textarea cols="85" rows="10">
가. 수집하는 개인정보의 항목첫째, 회사는 회원가 입, 원활한 고객상담, 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 최소한의 개인정보를 필수항목으로 수집하고 있습니다.
회원가입
- 이름, 생년월일, 성별, 아이디, 비밀번호, 별명, 연락처(메일주소, 휴대폰 번호 중 선택), 가입인증정보 만14세 미만 아동 회원가입 
- 이름, 생년월일, 성별, 법정대리인 정보, 아이디, 비밀번호, 연락처 (메일주소, 휴대폰 번호 중 선택), 가입인증정보
단체아이디 회원가입 
- 단체아이디, 회사명, 대표자명, 대표 전화번호, 대표 이메일 주소, 단체주소, 관리자 아이디, 관리자 연락처, 관리자 부서/직위
- 선택항목 : 대표 홈페이지, 대표 팩스번호
둘째, 서비스 이용과정이나 사업처리 과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.
- IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록
셋째, 네이버 아이디를 이용한 부가 서비스 및 맞춤식 서비스 이용 또는 이벤트 응모 과정에서 해당 서비스의 이용자에 한해서만 개인정보 추가 수집이 발생할 수 있으며, 이러한 경우 별도의 동의를 받습니다. 
넷째, 성인컨텐츠, 유료/게임 등 일부 서비스 이용시 관련 법률 준수를 위해 본인인증이 필요한 경우, 아래와 같은 정보들이 수집될 수 있습니다. 
- 이름, 생년월일, 성별, 중복가입확인정보(DI), 암호화된 동일인 식별정보(CI), 휴대폰 번호(선택), 아이핀 번호(아이핀 이용시), 내/외국인 정보
다섯째, 유료 서비스 이용 과정에서 아래와 같은 결제 정보들이 수집될 수 있습니다. 
- 신용카드 결제시 : 카드사명, 카드번호 등
- 휴대전화 결제시 : 이동전화번호, 통신사, 결제승인번호 등
- 계좌이체시 : 은행명, 계좌번호 등
- 상품권 이용시 : 상품권 번호
나. 개인정보 수집방법회사는 다음과 같은 방법으로 개인정보를 수집합니다. 
- 홈페이지, 서면양식, 팩스, 전화, 상담 게시판, 이메일, 이벤트 응모, 배송요청
- 협력회사로부터의 제공 
- 생성정보 수집 툴을 통한 수집
           </textarea><br><br>
         <div class="clear"></div>

       
		 <span>
		 <label>개인정보 위탁 동의</label>
		  <input type="checkbox" name="req1" id="req1" class="che_box che_box_per2"><br>
		  </span>
		     <textarea cols="85" rows="10">
정보통신망법 제25조(개인정보의 처리위탁)
① 정보통신서비스 제공자와 그로부터 제24조의2제1항에 따라 이용자의 개인정보를 제공받은 자
(이하 "정보통신서비스 제공자등"이라 한다)는 제3자에게 이용자의 개인정보를 수집, 생성, 연계, 연동, 기록, 저장, 보유, 가공, 편집, 검색, 출력, 정정(訂正), 
복구, 이용, 제공, 공개, 파기(破棄), 그 밖에 이와 유사한 행위(이하 "처리"라 한다)를 할 수 있도록 업무를 위탁(이하 "개인정보 처리위탁"이라 한다)하는 경우에는 
다음 각 호의 사항 모두를 이용자에게 알리고 동의를 받아야 한다. 다음 각 호의 어느 하나의 사항이 변경되는 경우에도 또한 같다.
1. 개인정보 처리위탁을 받는 자(이하 "수탁자"라 한다)
2. 개인정보 처리위탁을 하는 업무의 내용

② 정보통신서비스 제공자등은 정보통신서비스의 제공에 관한 계약을 이행하고 이용자 편의 증진 등을 위하여 필요한 경우로서 제1항 각 호의 사항 
모두를 제27조의2제1항에 따라 공개하거나 전자우편 등 대통령령으로 정하는 방법에 따라 이용자에게 알린 경우에는 개인정보 처리위탁에 따른 제1항의 
고지절차와 동의절차를 거치지 아니할 수 있다. 제1항 각 호의 어느 하나의 사항이 변경되는 경우에도 또한 같다.
           </textarea><br>
         <div class="clear"></div>   
		 <div class="btn_all">
		 <input type="submit" value="동의" class="button" />
		 <input type="button" value="비동의" class="button" onclick="location.href='./Main.me'"/>
		</div>
		</div>
		</form>
		</div>
	<div class="clear"></div>
	<!-- 푸터들어가는곳 -->
	<jsp:include page="../inc/footer.jsp"/>
	<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>