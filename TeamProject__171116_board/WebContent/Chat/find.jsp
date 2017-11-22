<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css?v=1">
	<title>Insert title here</title>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
			<%
		String id = null;
		if (session.getAttribute("id") != null){
			id = (String) session.getAttribute("id");
			}
		
		if(id == null){
 			session.setAttribute("messageType", "오류 메시지");
 			session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("./Main.me");
			return;
		}
	%>
	<script type="text/javascript">
		function findFunction(){
			var id = $('#findId').val();
			$.ajax({
				type: "POST",
				url: './ChatIdCheck.ch',
				data: {id : id},
				success: function(result){
					if(result == 1){
						$('#checkMessage').html('친구 찾기에 성공했습니다.');
						$('#checkType').attr('class', 'modal-content panel-success');
						getFriend(id);
					}else{
						$('#checkMessage').html('친구를 찾을 수 없습니다.');
						$('#checkType').attr('class', 'modal-content panel-warning');
						failFriend();
					}
					$('#checkModal').modal("show");
				}
			});
		}
		function getFriend(findId){
			$('#friendResult').html('<thead>'+
					'<tr>' +
					'<th><h4>검색 결과</h4></th>' +
					'</thead>' +
					'<tbody>' +
					'<tr>' +
					'<td style="text-align: center;"><h3>' + findId + '</h3><a href="./Chat.ch?toId=' +encodeURIComponent(findId) + '"class="btn btn-primary pull-right">' + '메시지 보내기</a></td>'+
					'</tr>' +
					'</tbody>');
		}
		function failFriend(){
			$('#friendResult').html('');
		}
	</script>	
</head>
<body>

<%-- 	<nav class="navbar navbar-default">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="./Main.me">메인</a></li>
				<li class="active"><a href="./ChatIdFind.ch">아이디 검색</a></li>
				<li><a>현재 로그인 된 아이디 : <%=id %></a></li>					
			</ul>

				<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="buton" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="./MemberLogoutAction.me">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav> --%>
	<div class="container">
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd";>
			<thead>
				<tr>
					<th colspan="2"><h4>검색으로 아이디 찾기</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
<!-- 					<td style="width: 110px;"><h5>친구 아이디</h5></td> -->
					<td><input class="form-control" type="text" id="findId" maxlength="20" placeholder="찾을 아이디를 입력하세요."></td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-primary pull-right" onclick="findFunction();">검색</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<table id="friendResult" class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd;"></table>
	</div>
	<%
		String messageContent = null;
		
		if(session.getAttribute("messageContent") != null){
			messageContent = (String) session.getAttribute("messageContent");
		}
		
		String messageType = null;
		if(session.getAttribute("messageType") != null){
			messageType = (String) session.getAttribute("messageType");
		}
		if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <%if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.println("panel-success"); %>"></div>
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<%= messageType %>
							</h4>
						</div>
						<div class="modal-body">
							<%= messageContent %>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		<script>
			$('#messageModal').modal("show");
		</script>
		
	<%		
		session.removeAttribute("messageContent");		
		session.removeAttribute("messageType");		
		}
	%>	
		<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content panel-info"></div>
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								확인 메세지
							</h4>
						</div>
						<div id="checkMessage" class="modal-body">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>

</body>
</html>