<%@page import="com.kkuk.dto.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

<title>자유 게시판</title>

</head>
<body>
	<!-- 게시판 글 리스트 -->
	

	<h2>자유 게시판 목록</h2>
	<hr>
	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날자</th>
			</tr>
		</thead>
		
		<c:forEach var="boardDto" items="${boardList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				
				<!--  글자수 제한으로 보여지는 갯수 선택 이후 ... 으로 보여지게끔 -->
				<td>
					<c:choose> 
						<c:when test="${fn:length(boardDto.btitle) > 30}">
							<a href="#">${fn:substring(boardDto.btitle, 0, 30)}...</a>
						</c:when>
						<c:otherwise>
						${boardDto.btitle }
						</c:otherwise>
					</c:choose>
				</td>
				<!-- 여기까지 하는 방법 -->
				<td>${boardDto.bwriter }</td>
				<td>${boardDto.bdate }</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<br>
	<a href="welcome.do">이전으로 돌아가기</a>
	
	
	
	
</body>
</html>