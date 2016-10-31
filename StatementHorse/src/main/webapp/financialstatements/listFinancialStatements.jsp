<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border='1' width='800'>
	<tr>
		<th>股號</th>
		<th>股名</th>
		<th>公布日期</th>
		<th>公布時間</th>
	</tr>
<c:forEach var="financialStatementsVO" items="${financialStatementsVO}">
	<tr>
		<td>${financialStatementsVO.stockNo}</td>
		<td></td>
		<td>${financialStatementsVO.postDate}</td>
		<td>${financialStatementsVO.postTime}</td>
	</tr>
</c:forEach>

	
</table>


</body>
</html>