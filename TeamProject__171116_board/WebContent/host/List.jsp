<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.Host.db.HostingDAO"%>
<%@page import="net.Host.db.HostingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/default.css?v=4" rel="stylesheet" type="text/css">
<link href="./css/list.css?v=11" rel="stylesheet" type="text/css">

<link rel = "stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src = "https://code.jquery.com/jquery-1.12.4.js"></script>
<script> var jb = jQuery.noConflict(); </script>
<script src = "https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<%
request.setCharacterEncoding("utf-8");
HostingBean hb = new HostingBean();
HostingDAO hbdao = new HostingDAO();
ArrayList<HostingBean> list = new ArrayList<HostingBean>();
list =(ArrayList<HostingBean>)request.getAttribute("list");
DecimalFormat dc = new DecimalFormat("#,###");
%>
  <script type="text/javascript">
  $(document).ready(function(){
 	  var str;
 	  $(".mainbooking_slide").mouseenter(function(){ 
 		 str = $(this).find(".index").html();
 		 $("#content"+str).css('font-weight','bold');
 		 $("#content"+str).css({color:'#000000',position:'relative'});		 
 	  });
 	  
 	  $(".mainbooking_slide").mouseleave(function(){
 		  $("#content"+str).css('font-weight','normal');
 		  $("#content"+str).css('color','#777777');
 	  }); 
 	  
		$('.dropdown_span').on('click',function(){
			var content = $(this).text();
			$('.search_address').val(content);
			$('.dropdown-content_where').css('display','none');
			//$('.dropdown-content').css('display','');
		})
		
		$('#where_click').mouseenter(function(){
			$('.dropdown-content_where').css('display','block');
		})
		$('#where_click').mouseleave(function(){
			$('.dropdown-content_where').css('display','none');
		})//검색드롭다운
		
		
   });//jquery
  
  </script>
  <script>
      // This example displays a marker at the center of Australia.
      // When the user clicks the marker, an info window opens.
//       function geocodeAddress(geocoder, resultsMap) {
//         var address = "부산 진구 부전동";
//         geocoder.geocode({'address': address}, function(results, status) {
//           if (status === 'OK') {
//             resultsMap.setCenter(results[0].geometry.location);
//             alert(results[0].geometry.location);
//             var marker = new google.maps.Marker({
//               map: resultsMap,
//               position : results[0].geometry.location,
//               icon : 'img/blank.png'
//             });
//           } else {
//             alert('주소가 정확하지않습니다.');
//           }
//         });
//       }
     function initMap() {
        var busan = {lat: 35.179554, lng: 129.075641};
        var map = new google.maps.Map(document.getElementById('map_wrap'), {
          zoom: 16,
          center: busan
        });
        var geocoder = new google.maps.Geocoder();
        
//         document.getElementById('search_btn').addEventListener('click', function() {
//           alert("지도 go"); 
//           geocodeAddress(geocoder, map);
//         });
        
        //-----------------------------------------------------

    	<%
   		 ArrayList<String> str = new ArrayList<String>();
        for(int i = 0 ; i < list.size();i++){
        	str.add(list.get(i).getAddress());
        }
    	%>   
        <%for(int i = 0 ; i < str.size() ; i++){%>

        geocoder.geocode({address : '<%=str.get(i)%>' },
        function(results, status) {
            if (status === 'OK') {
			  
              var contentString<%=i%> = "<div class='content' id='content<%=i%>'>₩<%=list.get(i).getPrice()%></div>";
              var infowindow<%=i%> = new google.maps.InfoWindow({
                content: contentString<%=i%>
              });
              var marker<%=i%> = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map,
                title: '<%=str.get(i)%>',
                icon : 'img/circle-button.png'
              });
              
              marker<%=i%>.addListener('click',function(){
              infowindow<%=i%>.open(map, marker<%=i%>);
              });	 
              infowindow<%=i%>.open(map, marker<%=i%>);
            } else {
              alert('주소가 잘못되어 마크가 표시가 되지 않습니다.');
            }  
        });
        <%}%>
       //---------------------------------------------------------
      }
    </script>
     <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAM56gRYD0iGeLl1iWXFpAuiqiWM9BBK7w&callback=initMap">
    </script>
    <script>
jb(function(){
	jb(".datepicker").datepicker({
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
 			if(date < new Date()) {
 				return [false];
 				}
 		return [true];
 	}
});
</script>
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
		<div class="clear"></div>
		<!-- 본문 -->
		<div id="list_wrap">
		<div id="list_body">
			<div id="search">
				<form action="./HostingListAction.ho?pageNum=1" method="post" >
					<table id="search_bar" border="1" width="95%">
						<tr>
							<td id="where">
								<div id="where_where">위치</div>
								<div id="where_click">
									<input type="text" name="address" id ="address"  class="search_address" placeholder="부산광역시" value="<%=(String)request.getAttribute("address")%>">
									<div class="dropdown-content_where">
										<div class="dropdown_span">기장군</div>
										<div class="dropdown_span">금정구, 동래구</div>
										<div class="dropdown_span">해운대구, 수영구, 남구</div>
										<div class="dropdown_span">연제구, 부산진구</div>
										<div class="dropdown_span">북구, 사상구, 사하구</div>
										<div class="dropdown_span">동구, 중구, 서구, 영도구</div>
										<div class="dropdown_span">강서구</div>
									</div>
								</div>
							</td>
							<td class="when">
								<div class="when_when">체크인</div>
								<div class="when_click">
									<input type="text" class="datepicker" name="checkin" placeholder="체크인" value="<%if(request.getParameter("checkin")==null){}else{%><%=request.getParameter("checkin")%><%}%>">
								</div>
							</td>
							<td class="when">
								<div class="when_when">체크아웃</div>
								<div class="when_click">
									<input type="text" class="datepicker" name="checkout" value ="<%if(request.getParameter("checkout")==null){}else{%><%=request.getParameter("checkout")%><%}%>" placeholder="체크아웃">
								</div>
							</td>
							<td style="text-align : center;">
								<button onclick="submit()" type="button" id="search_btn">
									<span>검색</span>
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="bookingtext">
				<div id="bookingtext_left">예약 (조건에 해당하는 호스팅이 <%=request.getAttribute("count")%>건 검색되었습니다.)</div>
				<!-- <div id="bookingtext_right">전체보기<img alt="" src="./img/right.png" width="17px"></div> -->
			</div>
			<div class="clear"></div>
			
			<div class="mainbooking">
<!--첫번째줄  --><!-- 최대 3개까지 출력되고 다음줄로 넘어가게 해야함 -->
			<%for(int i = 0 ; i < list.size() ; i++) {%>
			<div class="list_wrap">
				<div class="mainbooking_slide" style="cursor: pointer;margin-left:auto;margin-right:auto;float:'none';flex:0;" onclick="location.href = './HostingContentAction.ho?num=<%=list.get(i).getNum()%>'">
				<div class="index" style="display: none;"><%=i%></div>
					<div class="mainbooking_img">
						<img alt="" src="upload/<%=list.get(i).getFile1()%>">
					</div>
					<div class="mainbooking_content">
						<%=list.get(i).getSubject()%>
					</div>
					<div class="mainbooking_price">
						<%=dc.format(list.get(i).getPrice()) %>원
					</div>
					<div class="mainbooking_star">
						<%if(list.get(i).getGrade()==0){%> NEW!!
						<%}else{%> <%int s=(int)(list.get(i).getGrade()*10);
						int a=s/10;
						int b=s%10;
						int count=5;
						
						for(int j=0; j<a; j++){
								%><img src="./star/star.png"width="20px" height="20px"><%
							count--;
						}if(b!=0){
							%><img src="./star/star0<%=b%>.png" width="20px" height="20px"><%
							count--;
							for(int j=0; j<count; j++){
								%><img src="./star/star00.png"width="20px" height="20px"><%
							}
						}else{
							for(int j=0; j<count; j++){
						
								%><img src="./star/star00.png"width="20px" height="20px"><%
							}
							}%> <%}%>
					</div>
				</div>
			</div>
			<%} %>	

			<!-- 페이지버튼 -->
			<!-- 페이지버튼 -->

			</div>
<%
int pagesize = (int)request.getAttribute("pagesize"); 
int pageNum = (int)request.getAttribute("pageNum");
int starNum = 1;
int endNum = pagesize;
String address= "";
if((String)request.getAttribute("address")!=null){
	address = (String)request.getAttribute("address");
}
%>
<div style="text-align : center;">

<span onclick="location.href='./HostingListAction.ho?pageNum=<%=starNum%>&address=<%=address%>&checkin=<%if(request.getParameter("checkin")==null){}else{%><%=request.getParameter("checkin")%><%}%>&checkout=<%if(request.getParameter("checkout")==null){}else{%><%=request.getParameter("checkout")%><%}%>'" class="reviewBtn btn1">
<img src="./img/arrow/l.png" width="30px" style="cursor: pointer;">
</span>
<%if(pageNum==1) {%>
<%}else{ %>
<span onclick="location.href='./HostingListAction.ho?pageNum=<%=pageNum-1%>&address=<%=address%>&checkin=<%if(request.getParameter("checkin")==null){}else{%><%=request.getParameter("checkin")%><%}%>&checkout=<%if(request.getParameter("checkout")==null){}else{%><%=request.getParameter("checkout")%><%}%>'" class="reviewBtn btn2">
<img src="./img/arrow/ll.png" width="30px" style="cursor: pointer;">
</span>
<%} %>
<%if(pageNum==pagesize){ %>
<%}else{ %>
<span onclick="location.href='./HostingListAction.ho?pageNum=<%=pageNum+1%>&address=<%=address%>&checkin=<%if(request.getParameter("checkin")==null){}else{%><%=request.getParameter("checkin")%><%}%>&checkout=<%if(request.getParameter("checkout")==null){}else{%><%=request.getParameter("checkout")%><%}%>'" class="reviewBtn btn3">
<img src="./img/arrow/r.png" width="30px" style="cursor: pointer;">
</span>
<%}%>
<span onclick="location.href='./HostingListAction.ho?pageNum=<%=endNum%>&address=<%=address%>&checkin=<%if(request.getParameter("checkin")==null){}else{%><%=request.getParameter("checkin")%><%}%>&checkout=<%if(request.getParameter("checkout")==null){}else{%><%=request.getParameter("checkout")%><%}%>'" class="reviewBtn btn4">
<img src="./img/arrow/rr.png" width="30px" style="cursor: pointer;">
</span>
</div>

		</div>
		<%-- <h1>호스팅 list 사이즈 : <%=list.size()%></h1> --%>
		<div id="map_wrap">
		
		</div>
		<div class="clear"></div>
		</div>
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>