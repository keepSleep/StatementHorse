<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.financialstatements.model.FinancialStatementsVO"%>
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
<c:forEach var="financialStatementVO" items="${financialStatementsVO}" >
	<tr>
		<td>${financialStatementVO.getStockNo()}</td>
<%-- 		<td>${financialStatementVO.incomeStatements} }</td> --%>
		<c:forEach var="xx" items="${financialStatementVO.incomeStatements}">
			<c:if test="${xx.stockVO.stockNo == financialStatementVO.getStockNo()}">
			<td>${xx.stockVO.stockName }</td>
			</c:if>
		</c:forEach>
		
		<c:set var="vv" value="${financialStatementVO.postDate}" scope="request" />
		<%  java.util.Date dd= (java.util.Date)request.getAttribute("vv");
			String date = dd.toString().substring(0, 10);
			request.setAttribute("datex",date);
		%>
		<td>${datex}</td>
		<td>${financialStatementVO.postTime}</td>
<%-- 		${${financialStatementVO.incomeStatements}.iterator().next().stockVO.stockName} --%>
	</tr>
</c:forEach>

	
</table>


</body>
</html>