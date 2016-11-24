<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>StatementHorse</title>
</head>
<body>
<jsp:include page="/top/Top.jsp"/>
<table>
<thead>
<tr><td>股號</td><td>月營收通知</td><td>財報更新通知</td></tr>
</thead>
<tbody>



<form method="post" action="MsgIUDServlet">
		<c:forEach var="stock" items="${memberlistno}">
		<tr>
			<td>${stock[0]}</td>
			<td><input type="checkbox" name="${stock[0]}1" ${(stock[1]==true)? 'checked' : ''}/></td>
			<td><input type="checkbox" name="${stock[0]}2" ${(stock[2]==true)? 'checked' : ''}/></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td>
			<input type="submit" value="更改">
			<input type="hidden" name="action" value="checkbox_update">
			</td>
		</tr>
</form>


</tbody>		

</table>
<%-- <script src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script> --%>
<script>


	
	$(document).ready(function(){
// 	    $("#setting").click(function(){
// 	    	$.get("MsgServlet1",{"member_id":"Charizard"},function(data){
// 	    		$("#aaa").html(data);
// 	    	}) 
// 	    });
// 	    $("#setting").click(function(){
// 	        $("#myModal").modal();
// 	    });
	   
	
	
	});

</script>
</body>
</html>