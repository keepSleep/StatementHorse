<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>StatementHorse</title>

<!--Bootstrap樣式-->
<%-- 	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"> --%>
		<%@include file="/top/Top.jsp"%>
<!--JQuery/JQueryUI/Bootstrap JS-->
<%-- 	<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script> --%>
	<script	src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
<%-- 	<script	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script> --%>
<style>
*{
font-family:微軟正黑體;
}
div img{
position:static !important;　
position:relative; 　
top:-50%;
left:-50%;
}

</style>

</head>
<body>

	<div class="container-fluid" style="text-align:center">
	<p/>
<!-- 頁面主體 開始 -->
			<div class="row-fluid">
				<div id="left-Blank" class="col-md-1"></div>
				<div id="mid-Blank" class="col-md-10" >
					<div class="row-fluid">
						<div id="mid-left-Blank" class="col-xs-12" style="height:340px">
						<div class="row-fluid" style="height:100px"></div>
						<p><h1 style="text-shadow: 5px 5px 5px #f9f9f9"><strong>StatementHorse</strong></h1></p>
						<h1>－－－－－－－－－－－－－－－－－－－－－</h1>
						</div>
					</div>
					<div class="row-fluid">
						<p/>
						<div id="mid-left-Blank" class="col-xs-4" style="height:280px">
						<img src="picture/analysis.png" width="150" height="150">
							<h3>Data visualization</h3>
							
							</div>
						<div id="mid-left-Blank" class="col-xs-4" style="height:280px">
<!-- 							<img src="picture/promotion.png" width="150" height="150"> -->
							<img src="picture/statementdog_logo.png" width="150" height="150">
							<h3>Faster than dog</h3>
						</div>
						<div id="mid-left-Blank" class="col-xs-4" style="height:280px">
							<img src="picture/piggy-bank.png" width="150" height="150">
							<h3>FREE</h3>
						</div>
					</div>
				</div>
				<div id="right-Blank" class="col-md-1"></div>
			</div>
<!-- 頁面主體 結束 -->
	</div>
</body>
</html>