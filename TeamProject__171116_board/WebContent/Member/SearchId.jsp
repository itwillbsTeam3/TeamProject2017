﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/default.css?v=1" rel="stylesheet" type="text/css">
<link href="css/member.css?v=2" rel="stylesheet" type="text/css">
<link href="css/SearchId.css?v=1" rel="stylesheet" type="text/css">

<script src="js/script.js"></script>
<title>아이디 찾기</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/header.jsp" />
<div class="clear"></div>
<div class="body">
<article>
<form action="./MemberFindId.me" name="id" method="post" onsubmit="return searchId()">
<table id="notice">
<tr><td colspan="2"><h2><img src="img/id.png" height="27px" width="27px" style="margin-right:10px;">아이디 찾기</h2></td></tr>
<tr><td>이름</td><td><input type="text" name="name"></td></tr>
<tr><td>휴대전화번호</td><td><input type="text" name="mobile"></td></tr>
<tr><td><input type="submit" name="search" value="찾기" class="btn submit" ></td>
<td><input type="button" name="cancel" value="취소" class="btn" onclick="location.href='./Main.me'"></td>
</tr>
</table>
</form>
</article>
<div class="clear"></div>
</div>
<jsp:include page="../inc/footer.jsp" />
</div>
</body>
</html>