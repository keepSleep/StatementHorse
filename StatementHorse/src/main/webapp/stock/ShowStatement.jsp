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
	width: 200px white-space: nowrap;
	text-align: center;
	padding: 0px 20px;
}

.td {
	width: 300px white-space: nowrap;
	text-align: center;
	padding: 5px 20px
}
</style>
<title>StatementHorse</title>
</head>
<body>

	<input type="hidden" id="stockno" value="${stockVO.stockNo}" />
	<input type="hidden" id="stockname" value="${stockVO.stockName}" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="left-Blank" class="col-md-2"></div>
			<div id="mid-Blank" class="col-md-8">
				<div class="row-fluid page-header">
					<div class="col-md-3">
						<p style="font-family: '微軟正黑體'; font-size: 26px; color: #00BBFF">
							<a href="#" id="first_page"><i
								class="fa fa-stack-exchange fa-fw"></i> 個股資料</a> <span
								class="pull-right label label-default"></span> <span
								style="margin-left: 650px">
					</div>
					<div class="col-md-5"></div>
					<div class="btn-group" class="col-md-2">
						<a href="#">
							<button type="button" class="btn btn-info btn-xs dropdown-toggle"
								style="width:150px;font-family: '微軟正黑體'; font-size: 26px" id="show_incomestatement">
								損益表 <span></span>
							</button>
						</a> <span></span>
					</div>
					<div class="btn-group" class="col-md-2">
						<a href="#">
							<button type="button" class="btn btn-info btn-xs dropdown-toggle"
								style="font-family: '微軟正黑體'; font-size: 26px" id="show_balancesheet">
								資產負債表 <span></span>
							</button>
						</a> <span></span>
					</div>


				</div>
				</span>
				</p>


				<div style="width: 300px; left: 370px; top: 250px">

					<div>
						<table border='3 bordercolor='
							black'
				style="text-indent: 20px; width: 1100px; display:; font-family: '微軟正黑體'; font-size: 26px; margin-bottom: 200px"
							id="show_incomestatement_table">
							<tr>
								<td style="background-color: lightblue" >股號</td>
								<td style="background-color: lightcyan" >${IncomeStatementVO.stockVO.stockNo}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">股名</td>
								<td style="background-color: lightcyan">${stockVO.stockName}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">季度</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.statementDate}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業收入</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.operatingRevenue}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業成本</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.operatingCost}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業毛利</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.operatingMargain}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業毛利淨額</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.netOperatingMargain}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業費用</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.operatingExpenses}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">營業利益</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.operatingIncome}</td>
							</tr>
							<tr>

								<td style="background-color: lightblue">業外收入及支出</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.nonOperatingRevenue}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">稅前淨利</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.oibt}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">所得稅費用</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.incomeTaxExpense}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">繼續營業單位本期淨利</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.coiat}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">本期淨利</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.netIncome}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">其他綜合損益</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.oci}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">本期綜合損益</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.currentProfitAndLoss}</td>
							</tr>

							<tr>
								<td style="background-color: lightblue">基本每股盈餘</td>
								<td style="background-color: lightcyan">${IncomeStatementVO.earningPerShare}</td>
							</tr>

						</table>
					</div>
					<div>
						<table border='3 bordercolor='
							black'
				style="display: none; text-indent: 20px; width: 1100px; display:; font-family: '微軟正黑體'; font-size: 26px; margin-bottom: 200px"
							id="show_balancesheet_table">
							<tr>
								<td style="background-color: lightblue">股號</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.stockVO.stockNo}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">股名</td>
								<td style="background-color: lightcyan">${stockVO.stockName}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">季度</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.statementDate}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">流動資產</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.currentAssets}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">非流動資產</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.fixedAssets}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">總資產</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.assets}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">流動負債</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.currentLiabilities}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">非流動負債</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.longTermLiabilities}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">總負債</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.liabilities}</td>
							</tr>
							<tr>

								<td style="background-color: lightblue">股本</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.capitalStock}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">資本公積</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.additionalPaidInCapital}</td>
							</tr>
							<tr>
								<td style="background-color: lightblue">保留盈餘</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.retainedEarnings}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">其他權益</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.otherEquity}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">母公司業主之權益</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.consolidatedNetIncomeAttributedToStockholdersOfTheCompany}</td>

							</tr>
							<tr>
								<td style="background-color: lightblue">權益總計</td>
								<td style="background-color: lightcyan">${BalanceSheetVO.totalEquity}</td>

							</tr>


						</table>
					</div>
				</div>
			</div>
			<div id="right-Blank" class="col-md-2"></div>
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