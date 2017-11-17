<%@page import="net.comment.db.CommentBean"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="net.booking.action.Mydatecarculator"%>
<%@page import="net.booking.action.checkinout"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.booking.db.BookingDAO"%>
<%@page import="net.booking.db.BookingBean"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="net.Option.db.HostingOptionBean"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/default.css?v=3" rel="stylesheet" type="text/css">
<link href="./css/board.css?v=23" rel="stylesheet" type="text/css">
<link href="./css/star.css?v=6" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/style.css" type="text/css">
<!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->

<link rel = "stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script> var vm = jQuery.noConflict(); </script>
<script src = "https://code.jquery.com/jquery-1.12.4.js"></script>
<script> var jb = jQuery.noConflict(); </script>
<script src = "https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
request.setCharacterEncoding("UTF-8");
HostingBean hb = (HostingBean)request.getAttribute("hb");	//호스팅글 정보
MemberBean mb = (MemberBean)request.getAttribute("mb");
HostingOptionBean hto = (HostingOptionBean)request.getAttribute("hto");
ArrayList<String> listtemp = (ArrayList<String>)request.getAttribute("listtemp");
ArrayList<String> listtemp1 = (ArrayList<String>)request.getAttribute("listtemp1");
ArrayList<CommentBean> clist = (ArrayList<CommentBean>)request.getAttribute("clist");

int ccount = (int)request.getAttribute("ccount");
boolean scroll;
if(session.getAttribute("scroll")==null){
	scroll = false;
}
else{ 
	scroll = (boolean)session.getAttribute("scroll");
	session.setAttribute("scroll", false);
}
%>
<script>
jb(function(){
	var disabledDays1 = [<%for(int i = 0 ; i < listtemp.size(); i++){%><%=listtemp.get(i).substring(1,listtemp.get(i).length()-1)%><%if(i != listtemp.size()-1){%>,<%}}%>];
	jb(".datepicker1").datepicker({
		showOtherMonths:true,
		selectOtherMonths:true,
		dateFormat : "yy-mm-dd",
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames:['일','월','화','수','목','금','토'],
		dayNamesShort:['일','월','화','수','목','금','토'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		showMonthAfteryear:true,
		yearSuffix:'년',
		beforeShowDay: noBefore
	});
 	function noBefore(date){
		
 		var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
 		if(disabledDays1.length==0){
 			if(date < new Date()) {
 				return [false];
 				}
 		}
 		else{
 		for (i = 0; i < disabledDays1.length; i++) { 
 			if($.inArray(y + '-' +(m+1) + '-' + d,disabledDays1) != -1 || date < new Date()) {
 				return [false];
 				}
 		}
 		}
 		return [true];
 	}
});
</script>
<script>
jb(function(){
	var disabledDays2 = [<%for(int i = 0 ; i < listtemp1.size(); i++){%><%=listtemp1.get(i).substring(1,listtemp1.get(i).length()-1)%><%if(i != listtemp1.size()-1){%>,<%}}%>];
	jb(".datepicker2").datepicker({
		showOtherMonths:true,
		selectOtherMonths:true,
		dateFormat : "yy-mm-dd",
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames:['일','월','화','수','목','금','토'],
		dayNamesShort:['일','월','화','수','목','금','토'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		showMonthAfteryear:true,
		yearSuffix:'년',
		beforeShowDay: noBefore
	});
 	function noBefore(date){
		
 		var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
 		if(disabledDays2.length==0){
 			if(date < new Date()) {
 				return [false];
 				}
 		}
 		else{
 		for (i = 0; i < disabledDays2.length; i++) { 
 			if($.inArray(y + '-' +(m+1) + '-' + d,disabledDays2) != -1 || date < new Date()) {
 				return [false];
 				}
 		}
 		}
 		return [true];
 	}
});
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAM56gRYD0iGeLl1iWXFpAuiqiWM9BBK7w&callback=initMap"></script>



<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script>

      function first(geocoder, resultsMap) {
          var address = "<%=hb.getAddress()%>";
          geocoder.geocode({'address': address}, function(results, status) {
            if (status === 'OK') {
              resultsMap.setCenter(results[0].geometry.location);
              //alert(results[0].geometry.location);
              var marker = new google.maps.Marker({
                map: resultsMap,
                position : results[0].geometry.location,
                icon : 'img/circle-button.png'
              });
            } else {
              alert('주소를 불러올수없습니다.!');
            }
          });
        }
      function initMap() {
        var busan = {lat: 35.1578157, lng: 129.0600331};
        var geocoder = new google.maps.Geocoder();
        
        var map = new google.maps.Map(document.getElementById('content_map'), {
          zoom: 18,
          center: busan
        });
        
        first(geocoder,map);
   		}
</script>
<script>

$(document).ready(function(){
	
	<%if(scroll){%>
	$(window).scrollTop(650);
	<%}%>
	$(window).scroll(function () {
		
		var height = $(document).scrollTop();
		var map_height= $('#content_map').offset().top;
		if(height>map_height-700){
			$('#content_box').css('position','absolute');
			$('#content_box').css('top',map_height-636);
		}else{
			$('#content_box').css('position','fixed');
			$('#content_box').css('top',65);
		}
		
		log(height);
		}); 
	
	$('#option_down').click(function(){

			$('#option_down').css('display','none');
			$('#option_up').css('display','block');
			$('#option_wrap').slideToggle(1500);
		});
	
	$('#option_up').click(function(){

			$('#option_down').css('display','block');
			$('#option_up').css('display','none');
			$('#option_wrap').slideToggle(1500);
	});
	
	$('#test2').click(function(){
		alert($('#content_map').offset().top);
	})
	$('#content_map').offset().top-700
	
	$('.commentbtn').click(function(){
		
		$(this).parent().parent().parent().next().slideToggle(500);
	});
	
});

function log(str){
		$('#test').val(str);
}


</script>
<script>
var check;

function popup()
{
 document.yy.action ="./Booking.bo?num="+<%=hb.getNum()%>;
 document.yy.target="_self";
 document.yy.submit();
}
	function go(){
		alert("로그인 이후에 이용하실수 있습니다.");
		return false;
	}
	function fo(){
		if(document.getElementById('checkin').value == ""){
			alert("체크인 날짜를 입력해주세요");
			return false;
		}
		else if(document.getElementById('checkout').value == ""){
			alert("체크아웃 날짜를 입력해주세요");
			return false;
		}
		else{
			vm.ajax({
				type : "POST",
				url : "./host/bookingcheck.jsp",
				data : {
					"host_id" : "<%=hb.getId()%>",
			        "checkin" : document.getElementById('checkin').value,
			        "checkout" : document.getElementById('checkout').value
				},
				success : function(data) {
					check = parseInt(data.trim());
					  if(check == 0){
					    	alert("예약을 할수없습니다 기간을 확인해 주세요.");
					    	return false;
					  }
					  else{	 
						  return popup();
					  }
				}
			});
			
		}
	}
	function onClick(element) {
			document.getElementById("img01").src = element.src;
			document.getElementById("modal01").style.display = "block";
		}
		
		window.onclick = function(event) {
			if (event.target == modal) {
			  modal.style.display = 'none';
			}
		}

</script>
</head>
<body id="body">
<span id="idchk"></span>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div id="content_wrap">
			<div id="content_subject">
				<div id="content_top">
					<div style="font-size: 27px;"><%=hb.getSubject()%></div>
					<div>
						<span id="content_host">
						<a href="#" onclick="window.open('./Chat.ch?toId=<%=mb.getId() %>','', 'resizable=no width=500 height=800'); return false">호스트 : <%=mb.getId()%>님</a><br>
						
<!-- 						===================수정================= -->
<%-- 						<span id="content_host">호스트 : <%=mb.getId()%>님</span> --%>
<!-- 						===================수정=================  -->
												
<!-- 						===================추가================= -->
						<%-- <button><a href="./MessageSending.ms?host_id=<%=hb.getId()%>" target="_blank">
						<span style="color:red; font-weight: bold;">호스트에게 메시지 보내기</span></a>
						</button><br> --%>
<!-- 						===================추가================= -->
						
<!-- 						===================수정================= -->
						<a href="#" onclick="window.open('./MessageSending.ms?host_id=<%=hb.getId()%>','', 'resizable=no width=400 height=300'); return false">
						<span style="color:#008489;">호스트에게 메시지 보내기</span></a>
						<br> 
						<img alt=""
							src="./img/man.png"><span class="content_option">인원 <%=hto.getNumberOfGuest() %>
								명</span> <img alt="" src="./img/door.png"><span
							class="content_option">침실 <%=hto.getNumberOfRoom() %> 개</span> <img alt=""
							src="./img/bed.png"><span class="content_option">침대 <%=hto.getNumberOfBed() %>
								개</span> <img alt="" src="./img/beth.png"><span
							class="content_option">욕실 <%=hto.getNumberOfToilet() %>개</span>
						</span> <img alt="" src="./upload/<%=mb.getProfile() %>" class="host_thumnail">
						<div class="clear"></div>
					</div>
				</div>
				<div id="content_mid">
					<div>편의시설</div>
					<div style="width: 100%;min-height : 80px;">
						<div class="option_left">
   <%
   String option1_1[] = {"반려동물","가족/어린이적합","흡연가능","수영장","헬스장","아침식사","자쿠지 욕조","무료주차","건조기","휠체어접근가능","실내벽난로","이벤트/행사가능","경비원","초인종","노트북작업공간"};
   String option1_2[] = {"엘리베이터","다리미","인터넷","필수품목","무선인터넷","드라이기","세탁기","샴푸","옷걸이","에어컨","TV","난방","부엌","케이블TV","게스트전용출입문"};
   for(int i=0; i<2; i++){
   %>
							<div<%if(hto.getOption1().charAt(i)=='0'){%> style="color: rgba(102,102,102,0.5); text-decoration: line-through; "<%} %>>
								<%if(hto.getOption1().charAt(i)=='1'){ %><img alt="" src="./logo/op1_1_<%=i%>.png"><%=option1_1[i]%>
								<%}else{%><%=option1_1[i]%><%} %>
							</div>						
   <%}%>
						</div>
						<div class="option_right">
   <%
   for(int i=0; i<2; i++){
   %>
							<div<%if(hto.getOption1().charAt(i+option1_1.length)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption1().charAt(i+option1_1.length)=='1'){ %><img alt="" src="./logo/op1_1_<%=i%>.png"><%=option1_2[i]%>
								<%}else{%><%=option1_2[i]%><%} %>
							</div>							
   <%}%>
						</div>
						<div class="clear"></div>
					</div>
					<!-- 옵션 숨김 -->
					<div id="option_wrap">
						<div class="option_left">
   <%

   for(int i=2; i<option1_1.length; i++){
   %>
							<div<%if(hto.getOption1().charAt(i)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption1().charAt(i)=='1'){ %><img alt="" src="./logo/op1_1_<%=i%>.png"><%=option1_1[i]%>
								<%}else{%><%=option1_1[i]%><%} %>
							</div>						
   <%}%>
						</div>
						<div class="option_right">
   <%
   for(int i=2; i<option1_2.length; i++){
   %>
							<div<%if(hto.getOption1().charAt(i+option1_1.length)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption1().charAt(i+option1_1.length)=='1'){ %><img alt="" src="./logo/op1_1_<%=i%>.png"><%=option1_2[i]%>
								<%}else{%><%=option1_2[i]%><%} %>
							</div>							
   <%}%>
						</div>
						<div class="clear"></div>
                    <div>가족 편의 시설</div>
					<div class="option_left">
					   <%
   String option2_1[] = {"아기욕조","아기모니터","베이비시터","욕조","기저귀 교환대","어린이용 장남감","어린이용 식기","벽난로 안정장치"};
   String option2_2[] = {"게임기","유아용의자","전원 콘센터","다기능/아기침대","암막커튼","계단 차단문","테이블모서리 보호대","창문 안전장치"};
   for(int i=0; i<option2_1.length; i++){%>
							<div<%if(hto.getOption2().charAt(i)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption2().charAt(i)=='1'){ %><img alt="" src="./logo/op2_1_<%=i%>.png"><%=option2_1[i]%>
								<%}else{%><%=option2_1[i]%><%} %>
							</div>						
   <%}%>
      				</div>
   					<div class="option_right">
   <%
   for(int i=0; i<option2_2.length; i++){
   %>
							<div<%if(hto.getOption2().charAt(i+option2_1.length)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption2().charAt(i+option2_1.length)=='1'){ %><img alt="" src="./logo/op2_2_<%=i%>.png"><%=option2_2[i]%>
								<%}else{%><%=option2_2[i]%><%} %>
							</div>						
   <%}%>
					</div>
					<div>안전 기능</div>
					<div class="option_left">
   <%
   String option3_1[] = {"화재감지기","소화기"};
   String option3_2[] = {"일산화탄소 감지기","후레쉬"};
   for(int i=0; i<option3_1.length; i++){%>
							<div<%if(hto.getOption3().charAt(i)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption3().charAt(i)=='1'){ %><img alt="" src="./logo/op2_1_<%=i%>.png"><%=option3_1[i]%>
								<%}else{%><%=option3_1[i]%><%} %>
							</div>						
   <%}%>
      				</div>
   					<div class="option_right">
   <%
   for(int i=0; i<option3_2.length; i++){
   %>
							<div<%if(hto.getOption3().charAt(i+option3_1.length)=='0'){%> style="color: rgba(102,102,102,0.5);; text-decoration: line-through; "<%} %>>
								<%if(hto.getOption3().charAt(i+option3_1.length)=='1'){ %><img alt="" src="./logo/op2_1_<%=i%>.png"><%=option3_2[i]%>
								<%}else{%><%=option3_2[i]%><%} %>
							</div>						
   <%}%>
					</div>
					</div>
					<!-- 옵션 숨김 -->

					<div>					
						<a id="option_down">편의시설 모두보기 <img src="./img/down.png" width="17px;"></a>
					</div>
					<div>
						<a id="option_up">편의시설 숨기기 <img src="./img/up.png" width="17px;"></a>
					</div>
				</div>
				<div id="content_price">
					<span>가격</span><br>1박 기준 : <%int money = hb.getPrice(); NumberFormat dc = NumberFormat.getInstance();%><%=dc.format(money)%>원
				</div>
				<div id="content_selfinfo">
					<span>숙소소개</span><br>
					<%=hb.getContent() %>
				</div>
				<div id="content_law">
					<span>이용규칙+예약취소</span><br>
					<%=hb.getEtc() %>
				</div>
				<div id="hosting_review_wrap">
					<span>후기 <%=ccount%>개  <span style="vertical-align: middle;"> 
			    <%int s=(int)(hb.getGrade()*10);
				int a=s/10;
				int b=s%10;
				int count=5;
				
				for(int i=0; i<a; i++){
						%><img src="./star/star.png"width="35px" height="35px"><%
					count--;
				}if(b!=0){
					%><img src="./star/star0<%=b%>.png" width="35px" height="35px"><%
					count--;
					for(int i=0; i<count; i++){
						%><img src="./star/star00.png"width="35px" height="35px"><%
					}
				}else{
					for(int i=0; i<count; i++){
				
						%><img src="./star/star00.png"width="35px" height="35px"><%
					}
					}%>  </span> </span><br>
				<%for(int i = 0 ; i < clist.size() ;i++ ) {%>
				<%if(clist.get(i).getRe_lev() == 0){ %>
					<div class="hosting_review">
						<div class="reviewer">
							<div class="reviewer_pic">
								<img src="./upload/<%=clist.get(i).getProfile()%>">
							</div>
							<div class="reviewer_info">
								<div style="margin-left: 15px;">
									<div class="reviewer_name"><%=clist.get(i).getName()%></div>
									<div class="reviewer_date"><%=clist.get(i).getDate().toString().substring(0,19)%></div>
								</div>
							</div>
						</div>
						<div class="review_content"><%=clist.get(i).getContent()%>
					
						
						<form action ="./recommentaction.co" method="post">
						<%String str = (String)session.getAttribute("id"); %>
						<%if(str!=null)if(str.equals(mb.getId())){%><div style ="text-align: right;"><span class="commentbtn" style ="cursor: pointer; border: solid;">댓글달기 </span></div>	<%}%></div>
						<span class="comment" style="display: none;"><textarea name="recomment" class="review_write"cols="80" rows="5"></textarea><input type="submit" value="답글"></span>
							<input type="hidden" name="target" value="<%=clist.get(i).getName()%>">
							<input type="hidden" name="name" value="<%=session.getAttribute("id")%>">
							<input type="hidden" name="num" value="<%=hb.getNum()%>">
							<input type="hidden" name="re_ref" value="<%=clist.get(i).getRe_ref()%>">
						</form>
						
		
						
						
					</div>
				<%}else{ %>
			
					<div class="hosting_review_re">
						<div class="reviewer">
							<div class="reviewer_pic">
								<img src="./upload/<%=clist.get(i).getProfile()%>">
							</div>
							<div class="reviewer_info">
								<div style="margin-left: 15px;">
									<div class="reviewer_name"><%=clist.get(i).getName()%></div>
									<div class="reviewer_date"><%=clist.get(i).getDate().toString().substring(0,19)%></div>
								</div>
							</div>
						</div>
						<div class="review_content"><%=clist.get(i).getContent()%></div>
					</div>
					<%} %>
				<%} %>
<!-- 리뷰글삽입 -->
<!-- 댓글 페이징 -->
<%
int pagesize = (int)request.getAttribute("pagesize"); 
int pageNum = (int)request.getAttribute("pageNum");
int starNum = 1;
int endNum = pagesize;
%>
<input onclick="location.href='./HostingContentAction.ho?num=<%=hb.getNum()%>&pageNum=1&scroll=ok&countstop=ok'" type="button" value="[--">
<%if(pageNum==1) {%>
<%}else{ %>
<input onclick="location.href='./HostingContentAction.ho?num=<%=hb.getNum()%>&pageNum=<%=pageNum-1%>&scroll=ok&countstop=ok'" type="button" style="width: 40px; height: 40px;" value="[-">
<%} %>
<%if(pagesize!=pageNum){ %>
<input onclick="location.href='./HostingContentAction.ho?num=<%=hb.getNum()%>&pageNum=<%=pageNum+1%>&scroll=ok&countstop=ok'" type="button" style="width: 40px; height: 40px;" value="-]">
<%}%>
<input onclick="location.href='./HostingContentAction.ho?num=<%=hb.getNum()%>&pageNum=<%=pagesize%>&scroll=ok&countstop=ok'" type="button" value="--]">
<div class="review_input">
<div class="star-input-wrap"></div>

<%if(session.getAttribute("id")==null) {%>로그인후에 이용해주세요.<%}else if(session.getAttribute("id").equals(mb.getId())){ %><%}%><%else{ %>
<form id ="comment" action="./insertcomment.co" method="post">
		<span class="star-input">
			<span class="input">
		    	<input type="radio" name="star-input" value="0.5" id="p1">
		    		<label for="p1">0.5</label>
		    	<input type="radio" name="star-input" value="1" id="p2">
		    		<label for="p2">1</label>
		    	<input type="radio" name="star-input" value="1.5" id="p3">
		    		<label for="p3">1.5</label>
		    	<input type="radio" name="star-input" value="2" id="p4">
		    		<label for="p4">2</label>
		    	<input type="radio" name="star-input" value="2.5" id="p5">
		    		<label for="p5">2.5</label>
				<input type="radio" name="star-input" value="3" id="p6">
		    		<label for="p6">3</label>
				<input type="radio" name="star-input" value="3.5" id="p7">
		    		<label for="p7">3.5</label>
				<input type="radio" name="star-input" value="4" id="p8">
		    		<label for="p8">4</label>
				<input type="radio" name="star-input" value="4.5" id="p9">
			    	<label for="p9">4.5</label>
				<input type="radio" name="star-input" value="5" id="p10">
			    	<label for="p10">5</label>
		  	</span>
		  	<output for="star-input" name ="grade"><b>0</b>점</output><br>				
		</span>
		  	<textarea name="content" class="review_write"cols="80" rows="5"></textarea>
		<input type="hidden" name ="name" value="<%=session.getAttribute("id")%>">
		<input type="hidden" name = "target" value="<%=mb.getId()%>">
		<input type="hidden" name="num" value="<%=hb.getNum()%>">
		<input type="submit" value="평가하기" class="btn_review_write">
</form>
<%} %>
<!-- <script src="./starRating/js/jquery-1.11.3.min.js"></script> -->

</div>
<script src="./js/star.js"></script>				
				</div>
				<!-- 리뷰끝 -->
				<div id="host_intro_wrap">
<!-- 				css 미설정 영역 -->
					<span>호스트소개</span><br>
					<div id="host_introduce">
						<%=mb.getSelfinfo() %> 
					</div>
				</div>
<!-- 		버튼위치 조정 필요합니다 -->
		<div>
            <input type="button" value="수정" onclick="location.href=''" class="btn">
            <input type="button" value="삭제" onclick="location.href=''" class="btn">
            <input type="button" value="목록" onclick="location.href=''" class="btn">
        </div>
<!-- 		버튼위치 조정 필요합니다 -->        
				<div id="content_map">
					
				</div>
			</div>
<!-- 우측 이동하는 박스 -->
			<div id="content_box_wrap">
				<div id="content_box">
<!-- 					<div id="content_nav">
						<a href="#">개요</a> · <a href="#content_price">가격</a> · <a
							href="#content_selfinfo">숙소소개</a> · <a href="#content_review">후기</a>
					</div> -->

					<div id="content_pic">
						<div class="container">
							<div class="container_row">
								<div class="container_row">
									<!-- First slider -->
									<div id="slider2" class="slider slider_first">
										<div class="slider_viewport">
											<div class="slider_list">
			                                    <%if(hb.getFile1()!=null){%>
			                                    <div class="slider_item"><img src="./upload/<%=hb.getFile1() %>"onclick="onClick(this)"></div>
			                                    <%} %>
			                                    <%if(hb.getFile2()!=null){%>
			                                    <div class="slider_item"><img src="./upload/<%=hb.getFile2() %>"onclick="onClick(this)"></div>
			                                    <%} %>
			                                    <%if(hb.getFile3()!=null){%>
			                                    <div class="slider_item"><img src="./upload/<%=hb.getFile3() %>"onclick="onClick(this)"></div>
			                                    <%} %>
			                                    <%if(hb.getFile4()!=null){%>
			                                    <div class="slider_item"><img src="./upload/<%=hb.getFile4() %>"onclick="onClick(this)"></div>
			                                    <%} %>
			                                    <%if(hb.getFile5()!=null){%>
			                                    <div class="slider_item"><img src="./upload/<%=hb.getFile5() %>"onclick="onClick(this)"></div>
			                                    <%} %>
	                              		   </div>
										</div>
										<div class="slider_nav">
											<div class="slider_arrow slider_arrow__left"></div>
											<div class="slider_arrow slider_arrow__right"></div>
										</div>
										<div class="slider_control-nav"></div>
									</div>
								</div>
							</div>
						</div>
						<script	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
						<script src="./js/index.js"></script>

					</div>
<!-- 					<input type="text" value="test" id="test" style="margin-top:70px;">
					<input type="button" value="test" id="test2">!!!스크롤테스트용 -->
					<form class="option"  method="post" name="yy" id="yy">
			        <input type="hidden" name="num" value="<%=hb.getNum()%>">
			        <input type="hidden" name="subject" value="<%=hb.getSubject()%>">
			        <input type="hidden" name="host_id" value="<%=hb.getId()%>">
			        <input type="hidden" name="price" value="<%=hb.getPrice()%>"> 
			       	<h3 style="text-align: center;">현재까지 <%=hb.getReadcount() %>명이 이글을 보았습니다.</h3>
					<div id="button_wrap"></div>
					<div class="datepicker_wrap">
					<input type="text" name="checkin" id="checkin" placeholder="체크인" class="datepicker1">~
					<input type="text"  name="checkout" id="checkout" placeholder="체크아웃" class="datepicker2"><br>
					</div>
					<button id="content_btn" <%if(session.getAttribute("id")==null){ %>onclick="go()"<%}else{%>onclick="fo()"<%} %>>
						<span>예약</span>
					</button>
					</form>
				</div>	
			</div>
			<div id="modal01" onclick="this.style.display='none'">
  					<div class="modal_content">
    					<img id="img01" style="width:600px">
  					</div>
			</div>

		</div>
		
		<div class="clear"></div>
		
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>