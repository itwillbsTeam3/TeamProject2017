<%@page import="net.alram.db.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/alram.css?v=3" rel="stylesheet" type="text/css">

<script src ="js/jquery-3.2.1.min.js"></script>	
<script type="text/javascript">
$(document).ready(function(){
	$('.plus').parent().next().hide();
	$('.plus').click(function(){$(this).parent().next().slideToggle('slow');});
	$('.what').mouseover(function(){$(this).css('text-decoration','underline');});
	$('.what').mouseleave(function(){$(this).css('text-decoration','none');});
});
</script>

</head>

<%
String name = "";
if(session.getAttribute("id")==null){
	%>
	<script>
	alert("로그인후에 사용해주세요");
	location.href="./Main.me";
	</script>
	<%
}
else{
	name = (String)session.getAttribute("id");
}
AlramDAO alram = new AlramDAO();

ArrayList<AlramBean> list = new ArrayList<AlramBean>();
list = alram.getAlram(name);
ArrayList<AlramBean> list1 = new ArrayList<AlramBean>();
list1 = alram.getsysAlram(name);

%>
<body>
<div id="wrap">

<div class="clear"></div>
<!-- 본문 -->

<article class="body">

<h1>알림</h1>
<div class="container">
<div class="item">메시지 알림 [<%=list.size()%>]<img class="plus" src = "./img/+.png" style="cursor:pointer;"></div><div>
<%if(list.size()==0) {%>
<div class ="content"><div>등록된 알림이 없습니다.</div></div><%
} %><% for(int i = 0 ; i < list.size();i++) { %>
<div class ="content">
<div class = "what" style="color : 
<%if(list.get(i).getFlag()==1){%>rgba(0,0,0,0.4);<% 
}else{ //list.get(i).getFlag()==0%>rgba(0,0,0,1.0);<%}%>">

<%if(list.get(i).getType()==2){%><img class="imgs" src ="./img/blue.png"><%
}else if(list.get(i).getType()==1) {%><img class="imgs" src ="./img/red.png"><%
}else{
}%>
<%=list.get(i).getTarget()%><%=list.get(i).getContent()%>
<a href="Sysalram.ar?num=<%=list.get(i).getNum()%>">
<img src="./img/x.png" style ="width:20px;background-color: black;cursor:pointer;"></a><br>
<%String timestr = list.get(i).getDate().toString(); %>
<span class="time"><%=timestr.substring(0, timestr.length()- 2)%></span><br>
</div>
</div>
<%} %>
</div>

<div class="item" id="sys">시스템 알림 [<%=list1.size()%>]<img class="plus" src = "./img/+.png" style="cursor:pointer;"></div><div>
<%if(list1.size()==0) {%>
<div class ="content">
<div>등록된 알림이 없습니다.</div></div><%}
%><%for(int i = 0 ; i < list1.size();i++) {%>
<div class ="content">
<div style="color : 
<%if(list1.get(i).getFlag()==1){%>rgba(0,0,0,0.4);<%
}else{ //list.get(i).getFlag()==0%>rgba(0,0,0,1.0);<%}
%>">

<img class="imgs" src ="./img/c1.png">
<%=list1.get(i).getContent()%>
<a href="Sysalram.ar?num=<%=list1.get(i).getNum()%>">
<img src="./img/x.png" style ="width:20px;background-color: black;cursor:pointer;"></a><br>
<%String timestr = list1.get(i).getDate().toString();%>
<span class="time"><%=timestr.substring(0, timestr.length()-2)%></span><br>
</div>
</div>
<%} %>
</div>
</div>
</article>
<div class="clear"></div>

<!-- 본문 -->


</div>
</body>
</html>