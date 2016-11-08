<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Title自己改</title>
<!--Bootstrap樣式-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<!--JQuery/JQueryUI/Bootstrap JS-->
	<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
	<script	src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
	<script	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/top/Top.jsp"%>
	<div class="container-fluid">
	<p/>
<!-- 頁面主體 開始 -->
			<div class="row-fluid">
				<div id="left-Blank" class="col-md-1"></div>
				<div id="mid-Blank" class="col-md-10" style="background-color:#C6D8FF; height:600px">
					<div class="row-fluid">
						<p/>
				<!-- 頁面左方區塊 -->
						<div id="mid-left-Blank" class="col-xs-3" style="border:2px dotted #648381; height:580px">
						左方
						</div>
				<!-- 頁面右方區塊 -->
						<div id="mid-right-Blank" class="col-xs-9" style="border:2px solid #648381; height:580px">
						右方
						</div>
					</div>
				</div>
				<div id="right-Blank" class="col-md-1"></div>
			</div>
<!-- 頁面主體 結束 -->
	</div>
</body>
</html>