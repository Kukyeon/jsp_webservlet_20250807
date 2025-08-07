<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h2>회원 로그인</h2>
	<hr>
	<form action="loginOk.do" method="post">
		아이디 : <input type="text" name="mid"> <br><br>
		비밀번호 : <input type="text" name="mpw"> <br><br>
		<input type="submit" value="로그인">
	</form>
	<hr>
	<c:if test="${not empty errorMsg}">
		<!-- 로그인 실패 시 출력 될 메세지 -->
		<h3 style="color:red;">${errorMsg}</h3>
	</c:if>	
</body>
</html>