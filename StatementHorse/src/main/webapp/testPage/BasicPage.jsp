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
	<div class="container-fluid">
<!-- 巡覽列 開始 -->
		<nav class="navbar navbar-default" role="navigation" style="background-color:#C6D8FF">
			<div class="navbar-header">
	<!-- 功能巡覽列按鈕 開始 -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar" style="background-color:#C6D8FF">
					<span class="sr-only">Toggle navigation</span>
		<!-- 三條線 開始 -->
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
		<!-- 三條線 結束 -->
				</button>
	<!-- 功能巡覽列按鈕 結束 -->
	<!-- Logo連結 開始 -->
				<a class="navbar-brand" href="#">Project name</a>
	<!-- Logo連結 結束 -->
			</div>
	<!-- 巡覽列功能區塊 開始 --><!-- 螢幕縮小時，區塊內的項目會自動收起 -->
			<div id="navbar" class="navbar-collapse collapse" aria-expanded="true">
				<ul class="nav navbar-nav">
					<li><a href="#">#1</a></li>
					<li><a href="#">#2</a></li>
					<li><a href="#">#3</a></li>
		<!-- 下拉選單 開始 -->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">#4</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">#4-1</a></li>
							<li><a href="#">#4-2</a></li>
							<li><a href="#">#4-3</a></li>
			<!-- 分隔線 開始 -->
							<li class="divider"></li>
			<!-- 分隔線 結束 -->
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">#4-4</a></li>
							<li><a href="#">#4-5</a></li>
						</ul>
					</li>
		<!-- 下拉選單 結束 -->
				</ul>
		<!-- 巡覽列靠右功能區塊 開始 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">#5</a></li>
					<li><a href="#">#6</a></li>
					<li><a href="#">#7</a></li>
				</ul>
		<!-- 巡覽列靠右功能區塊 結束 -->
			</div>
	<!-- 巡覽列功能區塊 結束 -->
		</nav>
<!-- 巡覽列 結束 -->
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