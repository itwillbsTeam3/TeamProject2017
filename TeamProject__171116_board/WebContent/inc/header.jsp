<%@page import="net.chat.db.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.chat.db.ChatDAO"%>
<%@page import="net.Host.db.HostingBean"%>
<%@page import="net.Host.db.HostingDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="net.mileage.db.MileBean"%>
<%@page import="net.mileage.db.MileDAO"%>
<%@page import="net.alram.db.AlramDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <script src ="js/jquery-3.2.1.min.js"></script> -->
<link href="css/Login.css?v=12" rel="stylesheet" type="text/css">
<link href="css/header.css?v=6" rel="stylesheet" type="text/css">
<style>
.dropdown_c {
    position: relative;
    display: inline-block;
}
.dropdown-content_c {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 200px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    padding: 12px 16px;
    z-index: 1;
    overflow:scroll;
}
.dropdown-content_c a{
	line-height: 30px;
}
.dropdown_c:hover .dropdown-content_c {
    display: block;
}
</style>
<script	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		//	alert($('.popuptext').html())
		if ($('.popuptext').html() != 0) {
			$('.popuptext').show();
		}
		var check;
		var text;
		$("#simple_2").click(function(){
			check = $(this).is(":checked");
				$.ajax({
					type : "POST",
					url : "./inc/OpenClose.jsp",
					data : {
						"check" : check,
					},
					success : function(data) {
						text = data.trim();
						$("#text").html(text);
					}
				});
		});
		/*    	if($('.headerProfile').attr('src')=='./upload/null'){
		 $('.headerProfile').attr('src','./img/noimg.jpg');
		 } */

		var modal = document.getElementById('myModal');

		var btn = document.getElementById('myBtn');

		var close = document.getElementById('btn_cancel');

		btn.onclick = function() {
			modal.style.display = 'block';
		}

		close.onclick = function() {
			modal.style.display = 'none';
		}

		//close modal
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = 'none';
			}
		}

	});
	
// 	modal
	function onClick(element) {
		document.getElementById("img01").src = element.src;
		document.getElementById("modal01").style.display = "block";
	}
	
</script>

<%
	String id = (String) session.getAttribute("id");

	MemberDAO mdao = new MemberDAO();
	MemberBean mb = mdao.getMember(id);
	
	HostingDAO hdao = new HostingDAO();
	HostingBean hb = hdao.getContent(id);

	AlramDAO adao = new AlramDAO();
	int alrampopup = 0;
	if (session.getAttribute("id") != null) {
		alrampopup = adao.getAlramCount((String) session.getAttribute("id"));
	}

	MileDAO midao = new MileDAO();
	MileBean mibean = midao.getMileage(id);

	DecimalFormat dc = new DecimalFormat("###,###,###,###");
	
	ChatDAO cdao = new ChatDAO();
	ArrayList<ChatBean> clist = new ArrayList<ChatBean>();
	clist = cdao.getChatList((String)session.getAttribute("id"));
	
%>

<!-- 추가 -->
<div id="modal01" onclick="this.style.display='none'">
  <div class="modal_content_header">
  	<img id="img01" style="width:600px;">
  </div>
</div>
<!-- 추가 -->

<div id="topmenu">
	<div id="topmenu_left">
		<img alt="" src=""> <a href="./Main.me"><img alt=""
			src="./img/hoster.png" width="60px"></a>
	</div>
	<div id="topmenu_right">
		<%
			if (id != null && !("admin".equals(id))) {
		%><div id="topmenu_in">
			<%
			String profile = mb.getProfile(); 
			if(profile != null){
			%>
			<img src="./upload/<%=mb.getProfile()%>" width="50px" height="50px" style="border-radius: 50%; cursor:pointer;" onclick="onClick(this)">
			<%}else if(profile == null){ %>
			<img src="./img/nopro.png" width="50px" height="50px" style="border-radius: 50%;">
			<%} %>
			
			<span class="dropdown_c">
				<button class="login">
					<table><tr><th><img src="./img/c1.png" style="width:15px; height:15px;"></th></tr>
					<tr><td><small>CHAT MESSAGE</small></td></tr></table>
				</button>
				
				<%
					for(int i = 0; i<clist.size();i++){
						if(clist.get(i).getChatRead() == 0){
							%><span class="dropdown-content_c"><%
							%><a href="#" onclick="window.open('./Chat.ch?toId=<%=clist.get(i).getFromId() %>','', 'resizable=no width=500 height=800'); return false">
						 	<img src="./img/plus.png" style="width:15px; height:15px;">&nbsp;<b><%=clist.get(i).getFromId() %>님과의 대화</b></a><br>
						 	</span><%
						}
					}
				%>
					
				
			</span>
			
			<span class="dropdown">
				<button class="login">
					<%
						if (alrampopup != 0) {
					%><b><%=id%>(<%=alrampopup%>)</b><%
						} else {
					%><b><%=id%></b><%
						}
					%>
				</button>
				<span class="dropdown-content">
					<a href="./MemberCheckPw.me">정보수정</a>
					<a href="./MemberEmailUpdate.me">이메일 변경</a>
					<a href="./History.hi">결제내역</a>
					<!-- 호스팅내용이 없으면 안뜨게 -->
					<%if(hb.getNum()!=0){ %>
					<a href="./Host_history.hi">호스팅내역</a>
					<a href="./Hostingupdate.ho">호스팅수정하기</a>
					<span id="close_hosting"><span id="text"><%if(hb.getOc()==1){%>Open<%}else{%>Close<%}%></span><input type="checkbox" <%if(hb.getOc()==0){%>checked<%}%> id="simple_2"><label for="simple_2" class="red"></label></span>
					<%} %>
					<!-- 호스팅 내용이 있으면 수정가능하게 -->
					<a href="./Booking_history.hi">예약내역</a>
					<a href="#" onclick="window.open('./ChatIdFind.ch','', 'resizable=no width=500 height=860 location=no left=600px'); return false">1:1채팅</a>
					<a href="#" onclick="window.open('./Alram.ar','', 'resizable=no width=770 height=800 location=no left=500px'); return false">알림(<%=alrampopup%>)</a>


				</span>
			</span>
			
			<!-- 호스팅 한 id에 한번만 하도록 -->
			<%if(hb.getNum()==0){ %>
			<span><a href="Hostinginsert.ho">호스팅하기</a></span><%} %>
			<!-- 호스팅 한 id에 한번만 하도록 -->
			
			<span><a
				href="./MemberCheckAuth.me"> <%=dc.format(mibean.getMileage())%> <img
				src="./img/milege.png" width="20px" height="20px"></a></span>
				<span><a
				href="./MemberLogoutAction.me">로그아웃</a></span>
		</div>
		<%
			} else if ("admin".equals(id)) {
		%><div id="topmenu_in">
			<%
			String profile = mb.getProfile(); 
			if(profile != null){
			%>
			<img src="./upload/<%=mb.getProfile()%>" width="50px" height="50px" style="border-radius: 50%; cursor:pointer;" onclick="onClick(this)">
			<%}else if(profile == null){ %>
			<img src="./img/nopro.png" width="50px" height="50px" style="border-radius: 50%;">
			<%} %>
			
			<span class="dropdown_c">
				<button class="login">
					<table><tr><th><img src="./img/c1.png" style="width:15px; height:15px;"></th></tr>
					<tr><td><small>CHAT MESSAGE</small></td></tr></table>
				</button>
				<%
					for(int i = 0; i<clist.size();i++){
						if(clist.get(i).getChatRead() == 0){
						%><span class="dropdown-content_c"><%	
						%><a href="#" onclick="window.open('./Chat.ch?toId=<%=clist.get(i).getFromId() %>','', 'resizable=no width=500 height=800'); return false">
						 <img src="./img/plus.png" style="width:15px; height:15px;">&nbsp;<b><%=clist.get(i).getFromId() %>님과의 대화</b></a><br>
						 </span><%
						}
					}
				%>
			</span>
			
			<span class="dropdown">
				<button class="login">
					<%
						if (alrampopup != 0) {
					%><b><%=id%>(<%=alrampopup%>)</b><%
						} else {
					%><b><%=id%></b><%
						}
					%>
				</button>
				<span class="dropdown-content">
					<a href="./MemberCheckPw.me">정보수정</a><a href="./MemberList.me">회원관리</a>
					<a href="#" onclick="window.open('./ChatIdFind.ch','', 'resizable=no width=500 height=800 location=no left=600px'); return false">1:1채팅</a>
					<a href="#" onclick="window.open('./Alram.ar','', 'resizable=no width=770 height=800 location=no left=500px'); return false">알림(<%=alrampopup%>)</a>
				</span>
			</span>
			<span>
			<a href="./MemberLogoutAction.me">로그아웃</a>
			</span>
		</div>
		<%
			} else if (id == null) {
		%>
		<div id="topmenu_out">
			<span id="myBtn">로그인</span><a href="./MemberJoinreg.me">회원가입</a>
		</div>
		<%
			}
		%>

	</div>
</div>

<!-- Modal -->
<div id="myModal" class="modal">
	<!-- Modal Content -->
	<div class="modal_login">
		<!-- login -->
		<div class="Login_table">
			<h1>로그인</h1>
			<br>
			<form action="./MemberLoginAction.me" name="fr" method="post">
				<table class="inner_table">
					<tr class="login_row">
						<td class="form_col" width=80px>아이디</td>
						<td><input type="text" name="id" autocomplete="off"></td>
					</tr>
					<tr class="login_row">
						<td class="form_col">비밀번호</td>
						<td><input type="password" name="pass"></td>
					</tr>
				</table>
				<div class="login_btn">
					<input type="submit" value="로그인" class="click_btn btn_login">
					<input type="reset" value="취소" class="click_btn btn_cancel"
						id="btn_cancel">
				</div>
				<div class="login_btn_find">
					<input type="button" value="아이디 찾기" class="click_btn btn_findid"
						onclick="location.href='./MemberSearchId.me'"> <input
						type="button" value="비밀번호 찾기" class="click_btn btn_findpass"
						onclick="location.href='./MemberSearchPwd.me'">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- login -->
