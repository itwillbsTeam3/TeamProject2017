<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/default.css?v=2" rel="stylesheet" type="text/css">
<link href="./css/insert.css?v=7" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.2.1.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key= AIzaSyAM56gRYD0iGeLl1iWXFpAuiqiWM9BBK7w&callback=initMap"></script>
<script>

$(document).ready(function(){
	$('#File2').css('display','none');
	$('#File3').css('display','none');
	$('#File4').css('display','none');
	$('#File5').css('display','none');
	
	$('#btn_plus').on('click',function(){
	if($('#File2').css('display')=='none'){
		$('#File2').css('display','block');
		}else
	if($('#File3').css('display')=='none'){
		$('#File3').css('display','block');
		}else
	if($('#File4').css('display')=='none'){
		$('#File4').css('display','block');
		}else
	if($('#File5').css('display')=='none'){
		$('#File5').css('display','block');
		}
	});//파일넣는 버튼 나타나게 하는것

});
<%
String id = "";
if(session.getAttribute("id")==null){
	
}
else{
	id = (String)session.getAttribute("id"); 
}
MemberDAO mdao = new MemberDAO();
MemberBean mb = new MemberBean();
mb = mdao.getMember(id);
%>
		function first(geocoder, resultsMap) {
		    var address = "<%=mb.getAddress2()%>";
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
      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        alert(address);
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
            alert('주소를 찾을수없습니다. 다시입력해 주세요! ');
          }
        });
      }
      function initMap() {
        var busan = {lat: 35.1578157, lng: 129.0600331};
        
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 16,
          center: busan
        });
        
        var geocoder = new google.maps.Geocoder();
        
        document.getElementById('upmap').addEventListener('click', function() {
        	map = new google.maps.Map(document.getElementById('map'), {
                zoom: 16,
                center: busan
              });
              
            geocodeAddress(geocoder, map);
        });
        first(geocoder,map);
      }
      function test()
      {
       var fileName = new Array("File1", "File2", "File3", "File4", "File5");

       for (var i=0; i<fileName.length-1; i++)
       {
           if (document.getElementById(fileName[i]).value == "")
                continue;
            
        for (var j=i+1; j<fileName.length; j++)
        {
            if (document.getElementById(fileName[j]).value == "")
                continue;
                
         if (document.getElementById(fileName[i]).value == document.getElementById(fileName[j]).value)
         {
          alert("같은사진이 있습니다.");
          return false;
         }
        }
       }
      
       return true;
      }

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
			<div id="background">
				<div id="hosting_intro">
					<span style="font-size: 31px;">자신의 집을 공유해서</span><br> <span
						style="font-size: 44px;">수입을 올려보세요<br> HOSTER가 도와드립니다.
					</span>
				</div>
				<div id="host_3table">
				<div>
					<div style="font-size: 28px; font-weight: bold">Hoster의 장점</div><br> <span style="font-size: 19px;">
						호스트가 공유하는 숙소와 방의 종류와 관계없이 간편하고 안전하게 수입을 창출하도록 지원하며, 
						숙소를 찾는 수억 명의 여행자에게 호스트의 숙소를 소개합니다.</span>
				</div>
				<div>
					<div style="font-size: 28px; font-weight: bold">포인트제도</div><br> <span style="font-size: 19px;">
						숙소예약을 통해 얻은 포인트로 다른 호스트의 숙소를 예약할 수 있습니다.</span>
				</div>
				<div>
					<div style="font-size: 28px; font-weight: bold">한방에 가능합니다.</div><br> <span style="font-size: 19px;">
						번거로운 절차가 필요없이 한방에 숙소를 등록하고 예약을 할수 있으며 알람기능을 제공합니다.</span>
				</div>
			</div>
				<div id="gradient"></div>
			</div>
			<div class="clear"></div>
			
			<!-- 입력 폼 -->
			<form action="./HostinginsertAction.ho" method="post" enctype="multipart/form-data">
			<div id="hosting">
				
					<div class="section">주제</div>
					<input type="text" name="subject" required>
						<div class="border_bottom"> </div>
					<div class="section">소개</div>
					<textarea rows="5" cols="60" name="content" required></textarea>
						<div class="border_bottom"> </div>
					<div class="section">가격(원)</div>
					<input type="text" name="price" required>
						<div class="border_bottom"> </div>
					<div class="section">인원/침실</div>
					인원 : 
				
					<select name="numberOfGuest">
					<option value="1">1인</option>
					<option value="2">2인</option>
					<option value="3">4인</option>
					<option value="4">8인</option>
					</select>
					침실 : 
					<select name="numberOfRoom">
					<option value="1">1개</option>
					<option value="2">2개</option>
					<option value="3">3개</option>
					<option value="4">4개</option>
					</select>
					침대 : 
					<select name = "numberOfBed">
					<option value="1">1개</option>
					<option value="2">2개</option>
					<option value="3">3개</option>
					<option value="4">4개</option>
					</select>
					욕실 : 
					<select name ="numberOfToilet">
					<option value="1">1개</option>
					<option value="2">2개</option>
					<option value="3">3개</option>
					<option value="4">4개</option>
					</select>
	<div class="border_bottom"> </div>
					<div class="section">이용 규칙</div>
					<textarea rows="5" cols="60" name="Etc"></textarea>
						<div class="border_bottom"> </div>
					<div class="section">숙소이미지</div>
					<div id="insert_img">
					<input type="file" id="File1" name="File1">
					<input type="button" id="btn_plus" value="사진 추가하기">
					<input type="file" id="File2" name="File2">
					<input type="file" id="File3" name="File3">
					<input type="file" id="File4" name="File4">
					<input type="file" id="File5" name="File5">
					</div>
					<div class="border_bottom"> </div>
					<div  class="section">편의시설</div>
					<div id="insert_checkbox" style="font-size : 14px;">
					
   <%
   String option1_1[] = {"반려동물","가족/어린이적합","흡연가능","수영장","헬스장","아침식사","자쿠지 욕조","무료주차","건조기","휠체어접근가능","실내벽난로","이벤트/행사가능","경비원","초인종","노트북작업공간"};
   String option1_2[] = {"엘리베이터","다리미","인터넷","필수품목","무선인터넷","드라이기","세탁기","샴푸","옷걸이","에어컨","TV","난방","부엌","케이블TV","게스트전용출입문"};
   for(int i=0; i<option1_1.length; i++){
      %><div class="option1_wrap"><span><input type="checkbox" name="op1_<%=i%>"></span> <span><%=option1_1[i]%></span>
      <span class="option1_right"><span><input type="checkbox" name="op1_<%=option1_1.length+i%>"></span> <span><%=option1_2[i]%></span></span></span></div>
   <%}%>
   </div>
   <div class="border_bottom"> </div>
   <div class="section">가족 편의 시설</div>
   <div id="insert_checkbox" style="font-size : 14px;">
   <%
   String option2_1[] = {"아기욕조","아기모니터","베이비시터","욕조","기저귀 교환대","어린이용 장남감","어린이용 식기","벽난로 안정장치"};
   String option2_2[] = {"게임기","유아용의자","전원 콘센터","다기능/아기침대","암막커튼","계단 차단문","테이블모서리 보호대","창문 안전장치"};
   for(int i=0; i<option2_1.length; i++){
      %><div class="option1_wrap"><span><input type="checkbox" name="op2_<%=i%>"></span> <span><%=option2_1[i]%></span>
      <span class="option1_right"><span><input type="checkbox" name="op2_<%=option2_1.length+i%>"></span> <span><%=option2_2[i]%></span></span></div>
      <%}%> 
   </div>
   <div class="border_bottom"> </div>
   <div class="section">안전 기능</div>
   <div id="insert_checkbox" style="font-size : 14px;">
   <%
   String option3_1[] = {"화재감지기","소화기"};
   String option3_2[] = {"일산화탄소 감지기","후레쉬"};
   for(int i=0; i<option3_1.length; i++){
      %><div class="option1_wrap"><span><input type="checkbox" name="op3_<%=i%>"></span> <span><%=option3_1[i]%></span>
     <span class="option1_right"><span><input type="checkbox" name="op3_<%=option3_1.length+i%>"></span> <span><%=option3_2[i]%></span></span></div>
   <%}%>   
   </div>
	</div>
	<div class="border_bottom"> </div>
	<div id="insert_map">
					<div style="font-size:17px;">주소 (기본값은 본인의 주소로 되어있습니다.)</div>
					<input type="text" name="address" id="address" value="<%=mb.getAddress2()%>"><input type="button" id = "upmap" value="위치 확인"><br>
					<div id= "map" style="width: 700px; height: 700px;margin-left: auto;
    margin-right: auto; "></div>
					</div>
					<div id="insert_btn">
						<input type="submit" value="호스팅하기" onclick="test()" class="hosting_button btn">  
						<input type="reset" value="다시쓰기" class="btn">
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