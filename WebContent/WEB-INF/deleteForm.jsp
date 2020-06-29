
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	int _id = (int) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook2/guestbook" method="get">
		비밀번호 <input type="password" name="pw">
		<button type="submit">확인</button>
		<input type="hidden" name="_id" value=<%=_id%>> <input
			type="hidden" name="action" value="delete">
	</form>
	<br>
	<a href="/guestbook2/guestbook?action=list">메인으로 돌아가기</a>
</body>
</html>