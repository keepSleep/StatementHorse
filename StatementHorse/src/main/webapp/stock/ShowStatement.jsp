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

.th {
	white-space: nowrap;
	text-align: center;
	padding: 0px 20px;
}

.td {
	white-space: nowrap;
	text-align: center;
	padding: 5px 20px
}
</style>
<title>Insert title here</title>
</head>
<body>

	<input type="hidden" id="stockno" value="${stockVO.stockNo}" />
	<input type="hidden" id="stockname" value="${stockVO.stockName}" />
	<div class="container">
		<div class="page-header">
			<h3 style="font-family: '微軟正黑體'; font-size: 26px; color: #00BBFF">
				<a
					href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no=${incomeStatementVO.stockVO.stockNo}"
					id="first_page"><i class="fa fa-stack-exchange fa-fw"></i> 個股資料</a>
				<span class="pull-right label label-default"></span> <span
					style="margin-left: 650px">


					<div class="btn-group">
						<a href="#"><button type="button"
								class="btn btn-info btn-xs dropdown-toggle"
								style="font-family: '微軟正黑體'; font-size: 26px; width: 150px" id="show_incomestatement">
								損益表<span></span>
							</button></a> <span></span>
					</div>
					<div class="btn-group">
						<a href="#"><button type="button"
								class="btn btn-info btn-xs dropdown-toggle"
								style="font-family: '微軟正黑體'; font-size: 26px;" id="show_balancesheet">
								資產負債表<span></span>
							</button></a> <span></span>
					</div>
				</span>
			</h3>
		</div>

		<div style="width: 300px; position: absolute; left: 370px; top: 250px">

			<div>
				<table border='3 bordercolor='
					black'
				style="text-indent: 20px; width: 1100px; height: 500px; position: absolute; top: 0px; display:; font-family: '微軟正黑體'; font-size: 26px; margin-bottom: 200px"
					id="show_incomestatement_table">
					<tr>
						<td style="background-color: lightblue">股號</td>
						<td style="background-color: lightcyan">${incomeStatementVO.stockVO.stockNo}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">股名</td>
						<td style="background-color: lightcyan">${incomeStatementVO.stockVO.stockName}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">季度</td>
						<td style="background-color: lightcyan">${incomeStatementVO.statementDate}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業收入</td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingRevenue}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業成本</td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingCost}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業毛利</td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingMargain}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業毛利淨額</td>
						<td style="background-color: lightcyan">${incomeStatementVO.netOperatingMargain}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業費用</td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingExpenses}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">營業利益</td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingIncome}</td>
					</tr>
					<tr>

						<td style="background-color: lightblue">業外收入及支出</td>
						<td style="background-color: lightcyan">${incomeStatementVO.nonOperatingRevenue}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">稅前淨利</td>
						<td style="background-color: lightcyan">${incomeStatementVO.oibt}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue">所得稅費用</td>
						<td style="background-color: lightcyan">${incomeStatementVO.incomeTaxExpense}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue">繼續營業單位本期淨利</td>
						<td style="background-color: lightcyan">${incomeStatementVO.coiat}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue">本期淨利</td>
						<td style="background-color: lightcyan">${incomeStatementVO.netIncome}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue">其他綜合損益</td>
						<td style="background-color: lightcyan">${incomeStatementVO.oci}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue">本期綜合損益</td>
						<td style="background-color: lightcyan">${incomeStatementVO.currentProfitAndLoss}</td>
					</tr>

					<tr>
						<td style="background-color: lightblue">基本每股盈餘</td>
						<td style="background-color: lightcyan">${incomeStatementVO.earningPerShare}</td>
					</tr>

				</table>
			</div>
			<div>
				<table border='3 bordercolor='
					black'
				style="display:none;text-indent: 20px; width: 1100px; height: 500px; position: absolute; top: 0px; display:; font-family: '微軟正黑體'; font-size: 26px; margin-bottom: 200px"
					id="show_balancesheet_table">
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.stockVO.stockNo}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.stockVO.stockName}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.statementDate}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingRevenue}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingCost}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingMargain}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.netOperatingMargain}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingExpenses}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.operatingIncome}</td>
					</tr>
					<tr>

						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.nonOperatingRevenue}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.oibt}</td>
					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.incomeTaxExpense}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.coiat}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.netIncome}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.oci}</td>

					</tr>
					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.currentProfitAndLoss}</td>
					</tr>

					<tr>
						<td style="background-color: lightblue"></td>
						<td style="background-color: lightcyan">${incomeStatementVO.earningPerShare}</td>
					</tr>

				</table>
			</div>
		</div>
</body>
<script>
$("#show_incomestatement").click(function() {
	$("#show_incomestatement_table").css("display", "");
	$("#show_balancesheet_table").css("display", "none");

})
$("#show_balancesheet").click(function() {
	$("#show_incomestatement_table").css("display", "none");
	$("#show_balancesheet_table").css("display", "");

})




</script>
</html>