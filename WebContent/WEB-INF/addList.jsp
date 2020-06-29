<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>

<%
	List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("guestList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/gb2/gbc" method="post">
		<table border="1" style="width: 500px;">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea style="width: 100%;" name="content"></textarea></td>
			</tr>
			<tr>
				<input type="hidden" name="action" value="insert">
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
	</form>

	<%
		for (GuestbookVo guest : list) {
	%>
	<table border="1" style="width: 500px;">
		<tr>
			<td style="width: 50px;"><%=guest.getNumber()%></td>
			<td style="width: 200px;"><%=guest.getName()%></td>
			<td style="width: 200px;"><%=guest.getDate()%></td>
			<td style="width: 50px;"><a
				href="/guestbook2/guestbook?id=<%=guest.getNumber()%>&action=dPage">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=guest.getContent()%></td>
		</tr>
	</table>
	<%
		}
	%>
</body>
</html>