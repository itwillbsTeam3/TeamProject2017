<%@page import="java.text.DecimalFormat"%>
<%@page import="net.Host.db.HostingBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.Host.db.HostingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" buffer="128kb"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/default.css?v=18" rel="stylesheet" type="text/css">
<link rel = "stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src = "https://code.jquery.com/jquery-1.12.4.js"></script>
<script> var jb = jQuery.noConflict(); </script>
<script src = "https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="./js/jquery-3.2.1.js"></script>
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
<script>
	$(document).ready(function() {
		
		var list_num1 = $('.list_num.n1').val();
		var list_num2 = $('.list_num.n1').val();
		var list_num3 = $('.list_num.n1').val();
		$('.slider.n1').css('width',list_num1*264);
		$('.slider.n2').css('width',list_num2*264);
		$('.slider.n3').css('width',list_num3*264);
		
		if(list_num1>4){
		$('.left.n1').on('click', moveSlider1);
		$('.right.n1').on('click', moveSlider1);
		}
		if(list_num2>4){
			$('.left.n2').on('click', moveSlider2);
			$('.right.n2').on('click', moveSlider2);
			}
		if(list_num3>4){
			$('.left.n3').on('click', moveSlider3);
			$('.right.n3').on('click', moveSlider3);
			}

		$('.dropdown_span').on('click',function(){
			var content = $(this).text();
			$('.search_address').val(content);
			$('.dropdown-content_where').css('display','none');
			//검색창 드롭다운
		})
		
		$('#where_click').mouseenter(function(){
			$('.dropdown-content_where').css('display','block');
		})
		$('#where_click').mouseleave(function(){
			$('.dropdown-content_where').css('display','none');
		})	//검색창 드롭다운
		
	function moveSlider1() {
		var width = 264;
		var count = list_num1-4;
		var check = $(this).attr('class');
		var end = $('.slider.n1').position().left;
		if (check == 'right n1') {
			if (end > (count * width * -1)) {
				$('.slider.n1').filter(':not(:animated)').animate({
					left : '-=264px'
				}, 300);
			}
		} else {
			if (end < 0) {
				$('.slider.n1').filter(':not(:animated)').animate({
					left : '+=264px'
				}, 300);
			}
		}
	}//01
	function moveSlider2() {
		var width = 264;
		var count = list_num2-4;
		var check = $(this).attr('class');
		var end = $('.slider.n2').position().left;
		if (check == 'right n2') {
			if (end > (count * width * -1)) {
				$('.slider.n2').filter(':not(:animated)').animate({
					left : '-=264px'
				}, 300);
			}
		} else {
			if (end < 0) {
				$('.slider.n2').filter(':not(:animated)').animate({
					left : '+=264px'
				}, 300);
			}
		}
	}//02
	function moveSlider3() {
		var width = 264;
		var count = list_num3-4;
		var check = $(this).attr('class');
		var end = $('.slider.n3').position().left;
		if (check == 'right n3') {
			if (end > (count * width * -1)) {
				$('.slider.n3').filter(':not(:animated)').animate({
					left : '-=264px'
				}, 300);
			}
		} else {
			if (end < 0) {
				$('.slider.n3').filter(':not(:animated)').animate({
					left : '+=264px'
				}, 300);
			}
		}
	}//03
		 
		 
	});


</script>
</head>
<body>
	<div class="wrap">
		<!--헤더들어가는곳-->
		<jsp:include page="../inc/header.jsp" />
		<!--헤더들어가는곳-->
<!-- 		<script src="./js/jquery-3.2.1.min.js"></script> -->
		<div class="clear"></div>
		<!-- 본문 -->
		<div class="body">
			<!-- <input type="button" value="leftcheck" onclick="a()"> -->
			<div id="maintext">
				<span>Hoster</span><br> Book unique homes &<br>Experience
				a city like a local.
			</div>
			<div id="search">
				<form action="./HostingListAction.ho" method="post">
					<table id="search_bar" border="1" width="100%">
						<tr>
							<td id="where">
								<div id="where_where">위치</div>
								<div id="where_click">
									<input type="text" name="address" placeholder="부산광역시" class="search_address">
									<input type="hidden" name="pageNum" value="1">
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
									<input type="text" name="checkin" class="datepicker" placeholder="체크인">
								</div>
							</td>
							<td class="when">
								<div class="when_when">체크아웃</div>
								<div class="when_click">
									<input type="text" name="checkout" class ="datepicker" placeholder="체크아웃">
								</div>
							</td>
							<td style="text-align: center;">
								<button type="button" id="search_btn" onclick="submit()">
									<span>검색</span>
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%
			request.setCharacterEncoding("utf-8");
			HostingDAO htdao = new HostingDAO();
			ArrayList<HostingBean> plist = (ArrayList<HostingBean>)request.getAttribute("plist");
			int plist_num = plist.size();
			ArrayList<HostingBean> rlist = (ArrayList<HostingBean>)request.getAttribute("rlist");
			int rlist_num = rlist.size();
			ArrayList<HostingBean> glist = (ArrayList<HostingBean>)request.getAttribute("glist");
			int glist_num = glist.size();
			DecimalFormat dc = new DecimalFormat("#,###");
			%>
			<div id="bookingtext">
				<div id="bookingtext_left">호스팅 (전체 <%=htdao.gethostingcount()%> 개의 호스팅이 있습니다.)</div>
				<div id="bookingtext_right">
					<a href="./HostingListAction.ho?pageNum=1">전체보기<img alt=""
						src="./img/right.png" width="17px"></a>
				</div>
			</div>
			<div class="clear"></div>

			<div class="mainbooking_wrap">
			<!-- 별점 -->
			<div class="mainslider_font">가격순</div>
			<div class="mainslider_star">
				<img src="./img/left.png" alt="" class="left n1"> <img
					src="./img/right.png" alt="" class="right n1">
				<div class="mainbooking n1">
					<div class="slider n1">

						<%
							for (int i = 0; i < plist.size(); i++) {
						%>
						<div class="mainbooking_slide n1" style="cursor: pointer;" onclick="location.href = './HostingContentAction.ho?num=<%=plist.get(i).getNum()%>'">
							<div class="mainbooking_img">
								<img alt="" src="./upload/<%=plist.get(i).getFile1()%>">
							</div>
							<div class="mainbooking_content"><%=plist.get(i).getSubject()%></div>
							<div class="mainbooking_price"><%=dc.format(plist.get(i).getPrice())%>원</div>
							<div class="mainbooking_star"><%if(plist.get(i).getGrade()==0){%> NEW!!
						<%}else{%> <%int s=(int)(plist.get(i).getGrade()*10);
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
							}%> <%}%></div>
						</div>
						<%
							}
						%>
						<input type="hidden" value =<%=plist_num %> class="list_num n1">
					</div>
				</div>
				</div>
				<!-- 조회 -->
			<div class="mainslider_font">조회순</div>
			<div class="mainslider_star">
				<img src="./img/left.png" alt="" class="left n2"> <img
					src="./img/right.png" alt="" class="right n2">
				<div class="mainbooking n2">
					<div class="slider n2">

						<%
							for (int i = 0; i < rlist.size(); i++) {
						%>
						<div class="mainbooking_slide n2" style="cursor: pointer;" onclick="location.href = './HostingContentAction.ho?num=<%=rlist.get(i).getNum()%>'">
							<div class="mainbooking_img">
								<img alt="" src="./upload/<%=rlist.get(i).getFile1()%>">
							</div>
							<div class="mainbooking_content"><%=rlist.get(i).getSubject()%></div>
							<div class="mainbooking_price"><%=dc.format(rlist.get(i).getPrice())%>원</div>
							<div class="mainbooking_star"><%if(rlist.get(i).getGrade()==0){%> NEW!!
						<%}else{%> <%int s=(int)(rlist.get(i).getGrade()*10);
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
							}%> <%}%></div>
						</div>
						<%
							}
						%>
						<input type="hidden" value =<%=rlist_num %> class="list_num n2">
					</div>
				</div>
				</div>
				<!-- 최신 -->
			<div class="mainslider_font">평점순</div>
			<div class="mainslider_star">
				<img src="./img/left.png" alt="" class="left n3"> <img
					src="./img/right.png" alt="" class="right n3">
				<div class="mainbooking n3">
					<div class="slider n3">

						<%
							for (int i = 0; i < glist.size(); i++) {
						%>
						<div class="mainbooking_slide n3" style="cursor: pointer;" onclick="location.href = './HostingContentAction.ho?num=<%=glist.get(i).getNum()%>'">
							<div class="mainbooking_img">
								<img alt="" src="./upload/<%=glist.get(i).getFile1()%>">
							</div>
							<div class="mainbooking_content"><%=glist.get(i).getSubject()%></div>
							<div class="mainbooking_price"><%=dc.format(glist.get(i).getPrice())%>원</div>
							<div class="mainbooking_star"><%if(glist.get(i).getGrade()==0){%> NEW!!
						<%}else{%> <%int s=(int)(glist.get(i).getGrade()*10);
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
							}%> <%}%> </div>
						</div>
						<%
							}
						%>
						<input type="hidden" value =<%=glist_num %> class="list_num n3">
					</div>
				</div>
				</div>
				<!-- 최신순 -->
			</div>
		</div>
		<!-- 본문 -->
		<!-- 푸터들어가는곳 -->
		<jsp:include page="../inc/footer.jsp" />
		<!-- 푸터들어가는곳 -->
	</div>
</body>
</html>