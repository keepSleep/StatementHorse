<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">

<%@include file="/top/Top.jsp"%>
<!-- <link rel="stylesheet" -->
<%-- 	href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"> --%>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/metisMenu.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/sb-admin-2.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/MyCSS.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/morris.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/metisMenu2.min.css">
<%-- <script src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script> --%>
<%-- <script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script> --%>
<%-- <script	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script> --%>
<script
	src="${pageContext.servletContext.contextPath}/js/metisMenu.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/js/raphael.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/morris.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/js/morris-data.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/sb-admin-2.js"></script>

<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>

<style>
a:link {
	text-decoration: none
}
.th{
		white-space:nowrap;
		text-align:center;
		padding:0px 20px;
	}
	.td{
		white-space:nowrap;
		text-align:center;
		padding:5px 20px


}
</style>
<title>Insert title here</title>
</head>
<body>

	<input type="hidden" id="stockno" value="${stockVO.stockNo}" />
	<input type="hidden" id="stockname" value="${stockVO.stockName}" />
	<div class="container">
		<div class="page-header">
			<h3 style="font-family: '微軟正黑體';font-size:26px; color: #00BBFF">
				<a href="#" id="first_page"><i  class="fa fa-stack-exchange fa-fw"></i> 個股資料</a> <span
					class="pull-right label label-default"></span> <span
					style="margin-left:650px">


					<div class="btn-group">
						<a href="#"><button type="button" class="btn btn-info btn-xs dropdown-toggle"
							style="font-family: '微軟正黑體';font-size:26px; id="mylisting">我的追蹤清單 <span></span>
						</button>
						<span></span>
						</div>
						<div class="btn-group">
						<a href="#"><button type="button"class="btn btn-info btn-xs dropdown-toggle"
							data-toggle="dropdown" style="font-family: '微軟正黑體';font-size:26px; id="listing">加入追蹤 <span></span>
						</button>
						
					</div>
				</span>
			</h3>
		</div>

		<div style="width: 300px; position: absolute; left: 370px; top: 250px">
			<ul class="nav" id="side-menu" style="font-family: '微軟正黑體';font-size:26px;">

				<li id="show_mgr"><a href="#"><i
						class="fa fa-search-plus fa-fw"></i>公司最新動態<span class="active"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
					</ul></li>
				<li><a href="#"><i class="fa fa-file-text fa-fw"></i>三大財務報表<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
						<li><a href="#" id="show_incomestatement">損益表</a></li>
						<li><a href="#" id="show_balancesheet_1">資產表</a></li>
						<li><a href="#" id="show_balancesheet_2">負債表</a></li>
					</ul></li>
				
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>比率分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
						<li><a href="#" id="show_balance">安全性分析</a></li>
						<li><a href="#" id="show_profit">獲利分析</a></li>
					</ul></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			
		
		<table  style="color:red;text-indent: 20px; width: 800px; height: 500px; position: absolute; left: 300px; top: 0px; display:; text-align:center;font-family: '微軟正黑體';font-size:26px; margin-bottom: 200px"
				id="show_stock">
				<tr>
					<td valign="top" ><br>您輸入的股號或股名不存在唷!!~
						<br><br>請重新查詢</td>
					
				</tr>
		</table>
</div>
</body>
</html>