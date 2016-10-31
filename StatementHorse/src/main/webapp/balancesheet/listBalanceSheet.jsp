<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <jsp:useBean id="balanceSheetVO" scope="request" class="com.balancesheet.model.BalanceSheetVO" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border ='1' width='800'>
	<tr>
		<th>股號</th>
		<th>股名</th>
		<th>流動資產</th>
		<th>非流動資產</th>
		<th>總資產</th>
		<th>流動負債</th>
	</tr>
	
<c:forEach var="balanceSheetVO" items="${balanceSheetVO}">
	<tr>
		<td>${balanceSheetVO.stockNo }</td>
		<td></td>
		<td>${balanceSheetVO.currentAssets }</td>
		<td>${balanceSheetVO.fixedAssets }</td>
		<td>${balanceSheetVO.assets }</td>
		<td>${balanceSheetVO.currentLiabilities }</td>
	</tr>
</c:forEach>
</table>



</body>
</html>