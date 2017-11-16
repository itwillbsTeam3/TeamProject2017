<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{
		padding:0; margin:0;
	}
	div {
		border:1px solid blue;
		float: left;
	}
	div#reviews{
		width: 600px; height: 1500px;
	}
	div#head{
		width:595px; height: 30px;
	}
	div#comment{
		margin:5px 0 0 25px;
		width:250px;
	}
	div#search{
		float: right;
		margin:5px 0 0 0;
	}
</style>
</head>
<body>
<div id="reviews">
	<div id="head">
			<div id="comment">후기12개</div>
			<div id="search"><input type="text"></div>
	</div>
<link rel="stylesheet" type="text/css" href="starRating.css"/>

<h1>Star Rating</h1>
<p><span class="starRating"><span style="width:10%">1점</span></span></p>
<p><span class="starRating"><span style="width:20%">2점</span></span></p>
<p><span class="starRating"><span style="width:30%">3점</span></span></p>
<p><span class="starRating"><span style="width:40%">4점</span></span></p>
<p><span class="starRating"><span style="width:50%">5점</span></span></p>
<p><span class="starRating"><span style="width:60%">6점</span></span></p>
<p><span class="starRating"><span style="width:70%">7점</span></span></p>
<p><span class="starRating"><span style="width:80%">8점</span></span></p>
<p><span class="starRating"><span style="width:90%">9점</span></span></p>
<p><span class="starRating"><span style="width:100%">10점</span></span></p>
<button type="button" onclick="$('link').attr('href','');">CSS(X)</button>
<button type="button" onclick="$('link').attr('href','starRating.css');">CSS(O)</button>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

</div>
</body>
</html>