<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StatementHorse</title>
</head>
<style>
tr.border td {
	border:"1px black solid"
}
</style>
<body>
<jsp:include page="/top/Top.jsp"/>
	<table style="border: 1px black solid">
		<tr class="border">
			<td>
			${firstmessagefs.stockNo} ${firstmessagefs.statementDate} ${firstmessagefs.postDate}				
			</td>
		</tr>
		<tr class="border">
			<td>
				${firstmessagemg.stockNo} ${firstmessagemg.revenueDate} ${firstmessagemg.revenue} ${firstmessagemg.postDate}
			</td>
		</tr>
		<tr class="border">
			<td>
			${secondmessagefs.stockNo} ${secondmessagefs.statementDate} ${secondmessagefs.postDate}
			</td>
		</tr>
		<tr class="border">
			<td>
			${secondmessagemg.stockNo} ${secondmessagemg.revenueDate} ${secondmessagemg.revenue} ${secondmessagemg.postDate}
			</td>
		</tr>
		<tr class="border">
			<td>顯示全部</td>
		</tr>
	</table>
</body>
</html>