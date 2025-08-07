<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="jakarta.tags.core" %>
   <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
        body {
            font-family: "Noto Sans KR", sans-serif;
            background-color: #f9f9f9;
            padding: 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        thead {
            background-color: #f1f1f1;
        }

        th, td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            font-weight: bold;
        }

        td:nth-child(1),
        td:nth-child(4),
        th:nth-child(1),
        th:nth-child(4) {
            text-align: center;
            width: 100px;
        }

        td:nth-child(3),
        th:nth-child(3) {
            text-align: center;
            width: 180px;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        a {
            text-decoration: none;
            color: #333;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>

<title>회원 목록 보기</title>



</head>
<body>
<h2>회원 목록</h2>
	<hr>
	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>회원 아이디</th>
				<th>회원 이름</th>
				<th>회원 나이</th>
				<th>회원 등록일</th>
			</tr>
		</thead>
		
		<c:forEach var="memberDto" items="${memberList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${memberDto.mid }</td>			
				<td>${memberDto.mname}</td>
				<td>${memberDto.mage }</td>
				<td>${memberDto.mdate }</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<br>
	<a href="welcome.do">이전으로 돌아가기</a>
</body>
</html>