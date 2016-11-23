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
<title>StatementHorse</title>
</head>
<body>
<div class="container-fluid">	
	<input type="hidden" id="stockno" value="${stockVO.stockNo}" />
	<input type="hidden" id="stockname" value="${stockVO.stockName}" />
	<div class="row-fluid">
		<div id="left-Blank" class="col-md-2"></div>
		<div id="mid-Blank" class="col-md-8" >
		<div class="row-fluid page-header">

<!-- 	<div class="container "> -->
<!-- 		<div class="page-header"> -->
		<div class="col-md-3">
			<p style="font-family: '微軟正黑體'; font-size: 26px; color: #00BBFF">
				<a href="#" id="first_page"><i
					class="fa fa-stack-exchange fa-fw"></i> 個股資料</a> <span
					class="pull-right label label-default"></span> <span
					style="margin-left: 650px">


		</div>
		<div class="col-md-7"></div>
<!-- 		<div class="btn-group" class="col-md-2"> -->
<%-- 				<a href="${pageContext.servletContext.contextPath}/GetTrackListing?"> --%>
<!-- 				<button type="button" class="btn btn-info btn-xs dropdown-toggle" -->
<!-- 						style="font-family: '微軟正黑體'; font-size: 26px;"mylisting"> -->
<!-- 						我的追蹤清單 <span></span> -->
<!-- 				</button></a> <span></span> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-1"></div> -->
		<div class="btn-group" class="col-md-1">
				<button type="button" class="btn btn-info btn-xs dropdown-toggle"
					data-toggle="dropdown"style="font-family: '微軟正黑體'; font-size: 26px;"listing">
					加入追蹤 
<!-- 					<span class="caret"></span> -->
				</button> 
				<ul class="dropdown-menu pull-right" role="menu"
					style="font-size: x-large" id="TrackListing_id">
					<c:forEach var="TrackListingVO" items="${list}">
					<li><input type="checkbox"
							value="${TrackListingVO.listingNo}">${TrackListingVO.listingName}</li>
					</c:forEach>
				</ul>
<!-- 		</div> -->
<!-- 				</span> -->

		</div>
		</div>
		<br>
		<br>
		<div class="row-fluid">
<!-- 		<div style="width: 300px"> -->
		<div class="nav col-md-3" >
			<ul id="side-menu"
				style="list-styleLfont-family: '微軟正黑體'; font-size: 26px;">

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

			</ul>
			</div>
			

			<!----------------------每頁不同從這裡開始----------------------------------------------------->
			<!--個股資料-->
			
			<div class="col-md-9">
			<table border='3 bordercolor='
				black'
				style="text-indent: 20px; width: 800px; height: 500px; display:; font-family: '微軟正黑體'; font-size: 26px; margin-bottom: 200px"
				id="show_stock" >
				<tr>
					<td style="background-color: lightblue">股號</td>
					<td style="background-color: lightcyan">${stockVO.stockNo}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">股名</td>
					<td style="background-color: lightcyan">${stockVO.stockName}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">產業類別</td>
					<td style="background-color: lightcyan">${stockVO.industry}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">董事長</td>
					<td style="background-color: lightcyan">${stockVO.chairman}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">公司成立日期</td>
					<td style="background-color: lightcyan">${stockVO.companyEstablishDate}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">統一編號</td>
					<td style="background-color: lightcyan">${stockVO.companyTaxId}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">上市日期</td>
					<td style="background-color: lightcyan">${stockVO.listingDate}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">股東會日期</td>
					<td style="background-color: lightcyan">${stockVO.stockholdersMeetingDate}</td>
				</tr>
				<tr>

					<td style="background-color: lightblue">資本額(單位:百萬)</td>
					<td style="background-color: lightcyan">${stockVO.capital}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">公司網址</td>
					<td style="background-color: lightcyan">${stockVO.stockWebaddress}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">簽證會計師</td>
					<td style="background-color: lightcyan">${stockVO.accountingFirm}</td>

				</tr>
			</table>
			</div>
			
			<!--個股最新動態 -->
			<div class="col-md-9"
				style="width: 800px; display: none"
				id="show_div_mgr">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 26px">
							<li class="active"><a href="#tab1info1" data-toggle="tab"
								id="111">月營收</a></li>
							<li><a href="#tab2info1" data-toggle="tab">每股盈餘</a></li>
							<li><a href="#tab3info1" data-toggle="tab">股價</a></li>
							<li><a href="#tab4info1" data-toggle="tab">股利</a></li>
							<li><a href="#tab5info1" data-toggle="tab">本益比</a></li>
						</ul>
					</div>
					<div class="panel-body" style="margin-bottom: 100px">
						<div class="tab-content" id="container">
							<div class="tab-pane fade in active" id="tab1info1">
								<div id="tab1info" style="width:730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_mgr_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
<!-- 								<div style="padding-top: 30px"> -->
<!-- 									<table style="font-family: '微軟正黑體'; font-size: 20px"> -->
<!-- 										<tr> -->
<!-- 											<th Class="th">每月營收 -->
<!-- 											<td>定義：企業因銷售產品或提供勞務而取得的各項收入 營業收入是企業尚未扣除任何成本和費用前的收入， -->
<!-- 												上市櫃公司強制每個月都得公佈，因此是財報中最即時的數據。 營收有淡旺季的特性，因此觀察月、季增率沒什麼意義， -->
<!-- 												投資人應把重點放在年增率的趨勢。</td> -->
<!-- 											</th> -->
<!-- 										</tr> -->
<!-- 									</table> -->
<!-- 								</div> -->
							</div>
							<div class="tab-pane fade" id="tab2info1">
								<div id="tab2info" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_earningPerShare_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab3info1">
								<div id="tab3info" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_price_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab4info1">
								<div id="tab4info" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="width: 730px; color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_dividend_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab5info1">
								<div id="tab5info" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_per_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 損益表 -->
			<div class="col-md-9"
				style="width: 800px; display: none"
				id="show_div_incomestatement">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 25px">
							<li class="active"><a href="#tab1info_11" data-toggle="tab">季營收</a></li>
							<li><a href="#tab2info_11" data-toggle="tab">季毛利</a></li>
							<li><a href="#tab3info_11" data-toggle="tab">營業利益</a></li>
							<li><a href="#tab4info_11" data-toggle="tab">稅前淨利</a></li>
							<li><a href="#tab5info_11" data-toggle="tab">稅後淨利</a></li>
							<li><a href="#tab6info_11" data-toggle="tab">損益表</a></li>
						</ul>
					</div>
					<div class="panel-body" style="margin-bottom: 100px">
						<div class="tab-content" id="container_1">
							<div class="tab-pane fade in active" id="tab1info_11">
								<div id="tab1info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_operatingRevenue_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>

							<div class="tab-pane fade" id="tab2info_11">
								<div id="tab2info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_operatingMargain_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab3info_11">
								<div id="tab3info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_operatingIncome_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab4info_11" style="width: 730px">
								<div id="tab4info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_oibt_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>

							</div>
							<div class="tab-pane fade" id="tab5info_11" style="width: 730px">
								<div id="tab5info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_netIncome_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab6info_11" >
							<div id="tab6info_1" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_income_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 資產表 -->
			<div class="col-md-9"
				style="width: 800px;  display: none"
				id="show_div_balancesheet_1">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 26px">
							<li class="active"><a href="#tab1info_21" data-toggle="tab">流動資產</a></li>
							<li><a href="#tab2info_21" data-toggle="tab">固定資產</a></li>
							<li><a href="#tab3info_21" data-toggle="tab">總資產</a></li>
							<li><a href="#tab4info_21" data-toggle="tab">資產表</a></li>
						</ul>
					</div>
					<div class="panel-body" style="margin-bottom: 100px">
						<div class="tab-content" id="container_2">
							<div class="tab-pane fade in active" id="tab1info_21">
								<div id="tab1info_2" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_currentAssets_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab2info_21">
								<div id="tab2info_2" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_fixedAssets_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab3info_21">
								<div id="tab3info_2" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_assets_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab4info_21" style="width: 730px">
							<div id="tab4info_2" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_assetsall_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 負債表 -->
			<div class="col-md-9"
				style="width: 800px;  display: none"
				id="show_div_balancesheet_2">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 26px">
							<li class="active"><a href="#tab1info_31" data-toggle="tab">流動負債</a></li>
							<li><a href="#tab2info_31" data-toggle="tab">長期負債</a></li>
							<li><a href="#tab3info_31" data-toggle="tab">淨值</a></li>
							<li><a href="#tab4info_31" data-toggle="tab">總負債+淨值</a></li>
							<li><a href="#tab5info_3" data-toggle="tab">負債表</a></li>
						</ul>
					</div>
					<div class="panel-body" style="margin-bottom: 100px">
						<div class="tab-content" id="container_3">
							<div class="tab-pane fade in active" id="tab1info_31">
								<div id="tab1info_3" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_currentLiabilities_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab2info_31">
								<div id="tab2info_3" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_longTermLiabilities_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab3info_31">
								<div id="tab3info_3" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_net_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab4info_31">
								<div id="tab4info_3" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_assets_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade" id="tab5info_3" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>

			<!-- 安全性分析 -->
			<div class="col-md-9"
				style="width: 800px;  display: none"
				id="show_div_balance">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 26px">
							<li class="active"><a href="#tab1info_41" data-toggle="tab">負債比率</a></li>
							<li><a href="#tab2info_41" data-toggle="tab">流動比率</a></li>
						</ul>
					</div>
					<div class="panel-body" style="margin-bottom: 100px">
						<div class="tab-content" id="container_4">
							<div class="tab-pane fade in active" id="tab1info_41">
								<div id="tab1info_4" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_balance_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="tab-pane fade " id="tab2info_41">
								<div id="tab2info_4" style="width: 730px"></div>
								<div style="overflow-x: auto; padding-top: 30px">
									<table border='3' bordercolor='#DCDCDC'
										style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
										id="show_flow_table">
										<thead style="background-color: lightblue"></thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<!-- 獲利分析 -->
		<div class="col-md-9"
			style="width: 800px; display: none"
			id="show_div_profit">
			<div class="panel with-nav-tabs panel-info">
				<div class="panel-heading" style="background-color: lightblue">
					<ul class="nav nav-tabs"
						style="background-color: lightblue; font-family: '微軟正黑體'; font-size: 26px">
						<li class="active"><a href="#tab1info_51" data-toggle="tab">利潤比率</a></li>
					</ul>
				</div>
				<div class="panel-body" style="margin-bottom: 100px">
					<div class="tab-content" id="container_5">
						<div class="tab-pane fade in active" id="tab1info_51">
							<div id="tab1info_5" style="width: 730px"></div>
							<div style="overflow-x: auto; padding-top: 30px">
								<table border='3' bordercolor='#DCDCDC'
									style="color: steelblue; font-family: '微軟正黑體'; font-size: 20px"
									id="show_profit_table">
									<thead style="background-color: lightblue"></thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 	</div> -->
	


			</div>
		</div>
		<div id="right-Blank" class="col-md-2"></div>
	</div>
</div>


	<!----------------------每頁不同從這裡ㄐㄧㄝ----------------------------------------------------->

	<!-- 			</tr> -->


	<script>
		$(function() {

			var stockno = $("#stockno").val();
			var stockname = $("#stockname").val();
			var url = "ShowStockServlet?action=stock&stock_no=" + stockno;
			var thousandComma = function(number) {
				var num = number.toString();
				var pattern = /(-?\d+)(\d{3})/;
				while (pattern.test(num)) {
					num = num.replace(pattern, "$1,$2");
				}
				return num;
			}

			$("#first_page").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_stock").css("display", "");

			})
			$("#show_mgr").click(function() {
				$("#show_stock").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_div_mgr").css("display", "");

			})
			$("#show_incomestatement").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_stock").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_div_incomestatement").css("display", "");

			})
			$("#show_balancesheet_1").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_stock").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "");

			})
			$("#show_balancesheet_2").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_stock").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "");

			})
			$("#show_profit").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_stock").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_balance").css("display", "none");
				$("#show_div_profit").css("display", "");

			})
			$("#show_balance").click(function() {
				$("#show_div_mgr").css("display", "none");
				$("#show_stock").css("display", "none");
				$("#show_div_incomestatement").css("display", "none");
				$("#show_div_balancesheet_1").css("display", "none");
				$("#show_div_balancesheet_2").css("display", "none");
				$("#show_div_profit").css("display", "none");
				$("#show_div_balance").css("display", "");

			})

			//判別checkbox

			var list_no = $("#TrackListing_id>li>input:eq(0)").val();
			// 					console.log(i)
			$.get("ShowStockServlet", {
				"listNo" : list_no,
				"stock_no" : stockno,
				"insert_or_delete" : "select"
			}, function(data) {
				console.log(data)

				if (data == stockno) {
					$("#TrackListing_id>li>input:eq(0)").prop("checked", true)
				} else {
					$("#TrackListing_id>li>input:eq(0)").prop("checked", false)
					// 							console.log(i);
				}
			})
			var list_no = $("#TrackListing_id>li>input:eq(1)").val();
			// 					console.log(i)
			$.get("ShowStockServlet", {
				"listNo" : list_no,
				"stock_no" : stockno,
				"insert_or_delete" : "select"
			}, function(data) {
				console.log(data)

				if (data == stockno) {
					$("#TrackListing_id>li>input:eq(1)").prop("checked", true)
				} else {
					$("#TrackListing_id>li>input:eq(1)").prop("checked", false)
					// 							console.log(i);
				}
			})
			var list_no = $("#TrackListing_id>li>input:eq(2)").val();
			// 					console.log(i)
			$.get("ShowStockServlet", {
				"listNo" : list_no,
				"stock_no" : stockno,
				"insert_or_delete" : "select"
			}, function(data) {
				console.log(data)

				if (data == stockno) {
					$("#TrackListing_id>li>input:eq(2)").prop("checked", true)
				} else {
					$("#TrackListing_id>li>input:eq(2)").prop("checked", false)
					// 							console.log(i);
				}
			})

			//加入追蹤清單
			$("#TrackListing_id>li>input").change(function() {
				if ($(this).prop("checked")) {
					$.post("ShowStockServlet", {
						"listNo" : $(this).val(),
						"stock_no" : stockno,
						"insert_or_delete" : "insert"
					})
				} else {
					$.post("ShowStockServlet", {
						"listNo" : $(this).val(),
						"stock_no" : stockno,
						"insert_or_delete" : "delete"
					})                                                                                                                                                                                                                                                                                                                                                     
				}
			})
			$("#show_mgr")
					.click(
							function() {
								//mgrjson
								var mgrurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=mgrjson&stock_no='
										+ stockno;
								$.getJSON(mgrurl, function(data) {
									$('#tab1info').highcharts(
											'StockChart',
											{
												rangeSelector : {
													selected : 5
												},
												title : {
													text : '月營收(' + stockno
															+ ')(單位:千元)'
												},
												series : [ {
													name : stockname,
													data : data,
													tooltip : {
														valueDecimals : 2
													}
												} ]
											});

								});

								//mgr_table
								var myHead = $("#show_mgr_table>thead")
								var myBody = $("#show_mgr_table>tbody")
								myHead.empty();
								myBody.empty();
								//http://http://localhost:8080/StatementHorse/GetStockTableServlet?json=mgrjson&stock_no=2330
								$.getJSON("GetStockTableServlet", {
									"json" : "mgrjson",
									"stock_no" : stockno
								}, function(data) {
									var length = data.length
									myHead.append("<th>年度/月份</th>")
									myBody.append("<td>營收(單位:千元)</td>")
									$.each(data, function(key2, value2) {
										var date = $("<th></th>").append(
												value2.RevenueDate);
										var revenue = $("<td></td>").append(
												thousandComma(value2.Revenue));
										myHead.append(date)
										myBody.append(revenue)
									})
									$("#show_mgr_table>thead>th")
											.addClass("th")
									$("#show_mgr_table>tbody>td")
											.addClass("td")

								})

								//pricejson
								var priceurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=pricejson&stock_no='
										+ stockno
								$
										.getJSON(
												priceurl,
												function(data) {
													$('#tab3info')
															.highcharts(
																	'StockChart',
																	{
																		rangeSelector : {
																			selected : 5
																		},
																		title : {
																			text : '股價('
																					+ stockno
																					+ ')'
																		},
																		series : [ {
																			type : 'candlestick',
																			name : stockname,
																			data : data,
																			dataGrouping : {
																				units : [
																						'week',
																						[
																								'month',
																								[
																										1,
																										2,
																										3,
																										4,
																										6 ] ] ]
																			}
																		// 									                                      unit name[ 1 ] allowed multiples],
																		} ]
																	})
												});
								//price_table
								var myHead1 = $("#show_price_table>thead")
								var myBody1 = $("#show_price_table>tbody")
								myHead1.empty();
								myBody1.empty();
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=mgrjson&stock_no=2330
								$.getJSON("GetStockTableServlet", {
									"json" : "pricejson",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>日期</th>")
									myBody1.append("<td>股價(單位:元)</td>")
									$.each(data, function(key2, value2) {
										var date = $("<th></th>").append(
												value2.Date);
										var price = $("<td></td>").append(
												thousandComma(value2.Price));
										myHead1.append(date)
										myBody1.append(price)
									})
									$("#show_price_table>thead>th").addClass(
											"th")
									$("#show_price_table>tbody>td").addClass(
											"td")

								})
								//incomestatementjson
								var incomestatementjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=earningPerShare&stock_no='
										+ stockno;
								$
										.getJSON(
												incomestatementjsonurl,
												function(data) {
													// Create the chart
													// 						$('#tab2info').highcharts('StockChart',{rangeSelector : {selected : 5},
													// 										title : {text : '每股盈餘('+ stockno+ ')'},
													// 										series : [ {name : stockname,data : data,tooltip : {
													// 										valueDecimals : 2}
													// 										} ]
													// 								});

													Highcharts
															.chart(
																	'tab2info',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '每股盈餘'
																		},
																		subtitle : {
																			text : stockname
																					+ "("
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'earningPerShare'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '每股盈餘: <b>{point.y:.1f}</b>'
																		},
																		series : [ {
																			name : 'Population',
																			data : data
																		// 						            dataLabels: {
																		// 						                enabled: true,
																		// 						                rotation: -90,
																		// 						                color: '#FFFFFF',
																		// 						                align: 'right',
																		// 						                format: '{point.y:.1f}', // one decimal
																		// 						                y: 10, // 10 pixels down from the top
																		// 						                style: {
																		// 						                    fontSize: '13px',
																		// 						                    fontFamily: 'Verdana, sans-serif'
																		// 						                }
																		// 						            }
																		} ]
																	});

												});
								//earningPerShare_table
								var myHead2 = $("#show_earningPerShare_table>thead")
								var myBody2 = $("#show_earningPerShare_table>tbody")
								myHead2.empty();
								myBody2.empty();
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&stock_no=2330&need=EarningPerShare
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "earningPerShare",
									"stock_no" : stockno
								}, function(data) {
									myHead2.append("<th>年度/季度</th>")
									myBody2.append("<td>每股盈餘</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead2.append(date)
										myBody2.append(revenue)
									})
									$("#show_earningPerShare_table>thead>th")
											.addClass("th")
									$("#show_earningPerShare_table>tbody>td")
											.addClass("td")

								})

								//divident 股利
								var dividenturl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=dividendjson&stock_no='
										+ stockno;
								$.getJSON(dividenturl, function(data) {
									// create the chart
									$('#tab4info').highcharts('StockChart', {
										chart : {
											alignTicks : false
										},
										rangeSelector : {
											selected : 3
										},
										title : {
											text : '股利(' + stockno + ')'
										},
										series : [ {
											type : 'column',
											name : "",
											data : data,
										//				                 dataGrouping: {units: [['week', // unit name [1] // allowed multiples
										//				                     ], ['month', [1, 2, 3, 4, 6] ]] }
										} ]
									});
								});
								//dividend_table
								var myHead3 = $("#show_dividend_table>thead")
								var myBody3 = $("#show_dividend_table>tbody")
								myHead3.empty();
								myBody3.empty();
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=dividendjson&stock_no=2330
								$
										.getJSON(
												"GetStockTableServlet",
												{
													"json" : "dividendjson",
													"stock_no" : stockno
												},
												function(data) {
													myHead3
															.append("<th>年度</th>")
													myBody3
															.append("<td>股利</td>")
													$
															.each(
																	data,
																	function(
																			key2,
																			value2) {
																		var date = $(
																				"<th></th>")
																				.append(
																						value2.Date);
																		var dividend = $(
																				"<td></td>")
																				.append(
																						thousandComma(value2.Dividend));
																		myHead3
																				.append(date)
																		myBody3
																				.append(dividend)
																	})
													$(
															"#show_dividend_table>thead>th")
															.addClass("th")
													$(
															"#show_dividend_table>tbody>td")
															.addClass("td")

												})

								//本益比JSON
								var PERurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=PERjson&stock_no='
										+ stockno;
								$.getJSON(PERurl, function(data) {
									$('#tab5info').highcharts('StockChart', {
										rangeSelector : {
											selected : 5
										},
										title : {
											text : '本益比(' + stockno + ')'
										},
										series : [ {
											name : stockname,
											data : data,
											tooltip : {
												valueDecimals : 2
											}
										} ]
									});
								});
							})
							//per_table
								var myHead4 = $("#show_per_table>thead")
								var myBody4 = $("#show_per_table>tbody")
								myHead4.empty();
								myBody4.empty();
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=PERjson&stock_no=2330
								$.getJSON("GetStockTableServlet", {
									"json" : "PERjson",
									"stock_no" : stockno
								}, function(data) {
									myHead4.append("<th>日期</th>")
									myBody4.append("<td>本益比</td>")
									$.each(data, function(key2, value2) {
										var date = $("<th></th>").append(
												value2.Date);
										var price = $("<td></td>").append(
												thousandComma(value2.Price));
										myHead4.append(date)
										myBody4.append(price)
									})
									$("#show_per_table>thead>th").addClass(
											"th")
									$("#show_per_table>tbody>td").addClass(
											"td")

								})

			$("#show_incomestatement")
					.click(
							function() {
								//incomestatementjson季營收
								var revenuejsonurl = "http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingRevenue&stock_no="
										+ stockno;

								$
										.getJSON(
												revenuejsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab1info_1',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '季營收'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Revenue(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '營收: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Revenue',
																			data : data,
																		// 					            dataLabels: {
																		// 					                enabled: true,
																		// 					                rotation: -90,
																		// 					                color: '#FFFFFF',
																		// 					                align: 'right',
																		// 					                format: '{point.y:.1f}', // one decimal
																		// 					                y: 10, // 10 pixels down from the top
																		// 					                style: {
																		// 					                    fontSize: '13px',
																		// 					                    fontFamily: 'Verdana, sans-serif'
																		// 					                }
																		// 					            }
																		} ]
																	});
												})
								//operatingRevenue_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingRevenue&stock_no=2330;
								var myHead = $("#show_operatingRevenue_table>thead")
								var myBody = $("#show_operatingRevenue_table>tbody")
								myHead.empty();
								myBody.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "operatingRevenue",
									"stock_no" : stockno
								}, function(data) {
									myHead.append("<th>年度/季度</th>")
									myBody.append("<td>季營收(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead.append(date)
										myBody.append(revenue)
									})
									$("#show_operatingRevenue_table>thead>th")
											.addClass("th")
									$("#show_operatingRevenue_table>tbody>td")
											.addClass("td")

								})

								//incomestatementjson季毛利
								var operatingMargainjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingMargain&stock_no='
										+ stockno;
								$
										.getJSON(
												operatingMargainjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab2info_1',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '季毛利'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Margain(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '毛利: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Margain',
																			data : data,
																		// 					            dataLabels: {
																		// 					                enabled: true,
																		// 					                rotation: -90,
																		// 					                color: '#FFFFFF',
																		// 					                align: 'right',
																		// 					                format: '{point.y:.1f}', // one decimal
																		// 					                y: 10, // 10 pixels down from the top
																		// 					                style: {
																		// 					                    fontSize: '13px',
																		// 					                    fontFamily: 'Verdana, sans-serif'
																		// 					                }
																		// 					            }
																		} ]
																	});
												});
								//operatingMargain_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingMargain&stock_no=2330;
								var myHead1 = $("#show_operatingMargain_table>thead")
								var myBody1 = $("#show_operatingMargain_table>tbody")
								myHead1.empty();
								myBody1.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "operatingMargain",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>年度/季度</th>")
									myBody1.append("<td>季毛利(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead1.append(date)
										myBody1.append(revenue)
									})
									$("#show_operatingMargain_table>thead>th")
											.addClass("th")
									$("#show_operatingMargain_table>tbody>td")
											.addClass("td")

								})
								//營業利益
								var operatingIncomejsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingIncome&stock_no='
										+ stockno;
								$
										.getJSON(
												operatingIncomejsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab3info_1',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '營業利益'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'operatingIncome(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '營業利益: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'operatingIncome',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//operatingIncome_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingIncome&stock_no=2330;
								var myHead2 = $("#show_operatingIncome_table>thead")
								var myBody2 = $("#show_operatingIncome_table>tbody")
								myHead2.empty();
								myBody2.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "operatingIncome",
									"stock_no" : stockno
								}, function(data) {
									myHead2.append("<th>年度/季度</th>")
									myBody2.append("<td>營業利益(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead2.append(date)
										myBody2.append(revenue)
									})
									$("#show_operatingIncome_table>thead>th")
											.addClass("th")
									$("#show_operatingIncome_table>tbody>td")
											.addClass("td")

								})
								//稅前淨利
								var oibtjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=oibt&stock_no='
										+ stockno;
								$
										.getJSON(
												oibtjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab4info_1',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '稅前淨利'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Profit(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '稅前淨利: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Profit',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//oibt_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=oibt&stock_no=2330;
								var myHead3 = $("#show_oibt_table>thead")
								var myBody3 = $("#show_oibt_table>tbody")
								myHead3.empty();
								myBody3.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "oibt",
									"stock_no" : stockno
								}, function(data) {
									myHead3.append("<th>年度/季度</th>")
									myBody3.append("<td>稅前淨利(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead3.append(date)
										myBody3.append(revenue)
									})
									$("#show_oibt_table>thead>th").addClass(
											"th")
									$("#show_oibt_table>tbody>td").addClass(
											"td")

								})
								//稅後淨利
								var netIncomejsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=netIncome&stock_no='
										+ stockno;
								$
										.getJSON(
												netIncomejsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab5info_1',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '稅後淨利'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Profit(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '稅後淨利: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Profit',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//netIncome_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=incomestatementjson&need=netIncome&stock_no=2330;
								var myHead4 = $("#show_netIncome_table>thead")
								var myBody4 = $("#show_netIncome_table>tbody")
								myHead4.empty();
								myBody4.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "incomestatementjson",
									"need" : "netIncome",
									"stock_no" : stockno
								}, function(data) {
									myHead4.append("<th>年度/季度</th>")
									myBody4.append("<td>稅後淨利(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead4.append(date)
										myBody4.append(revenue)
									})
									$("#show_netIncome_table>thead>th")
											.addClass("th")
									$("#show_netIncome_table>tbody>td")
											.addClass("td")

								})
								//統整表
								var incomejsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomejson&need=income&stock_no='
										+ stockno;
								$.getJSON(incomejsonurl, function(data) {
									Highcharts.chart('tab6info_1', {
										title : {
											text : "損益表",
											x : -20
										//center
										},
										subtitle : {
											text : stockname + "(" + stockno
													+ ")",
											x : -20
										},
										xAxis : data[0],
										yAxis : {
											title : {
												text : ''
											},
											plotLines : [ {
												value : 0,
												width : 1,
												color : '#808080'
											} ]
										},
										tooltip : {
											valueSuffix : '千元'
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'middle',
											borderWidth : 0
										},
										series : [ data[1], data[2], data[4],
												data[5] ]
									});

								})
								//profit_table
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=assetsjson&need=flow&stock_no=2330;
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=incomestatementjson&need=profit&stock_no=	
								
								var myHead6 = $("#show_income_table>thead")
								var myBody6 = $("#show_income_table>tbody")
								myHead6.empty();
								myBody6.empty();
								$.getJSON("GetStockTableServlet", {
									"json" : "incomestatementjson",
									"need" : "income",
									"stock_no" : stockno
								}, function(data) {
									myHead6.append("<th>年度/季度</th>")
									$.each(data, function(key, value) {
										
										var date = $("<th></th>").append(
												thousandComma(value.Date));
										myHead6.append(date)
									})
									var Body1 = $("<tr></tr>").append("<td>季營收(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue1 = $("<td></td>").append(thousandComma(value.Revenue));
										Body1.append(revenue1);
									})
// 									

									var Body2 = $("<tr></tr>").append(
											"<td>季毛利(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue2 = $("<td></td>").append(
												thousandComma(value.Margain));
										Body2.append(revenue2);
									})
// 								
									var Body3 = $("<tr></tr>").append(
											"<td>稅前淨利(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue3 = $("<td></td>").append(
												thousandComma(value.Oibt));
										Body3.append(revenue3);
									})
									var Body4 = $("<tr></tr>").append(
											"<td>稅後淨利(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue4 = $("<td></td>").append(
												thousandComma(value.NetIncome));
										Body4.append(revenue4);
									})
									
									myBody6.append(Body1,Body2,Body3,Body4);
									$("#show_income_table>thead>th").addClass(
											"th")
									$("#show_income_table>tbody>tr>td")
											.addClass("td")

								})
							})

			$("#show_balancesheet_1")
					.click(
							function() {
// 								var assetsAlljsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=assetsjson&need=assetsAll&stock_no='
// 										+ stockno;
								$.getJSON("ShowStockServlet",{"json":"assetsjson","need":"assetsAll","stock_no":stockno}, function(data) {
									Highcharts.chart('tab4info_2', {
										title : {
											text : "資產(" + stockno + ")",
											x : -20
										//center
										},
										subtitle : {
											text : "",
											x : -20
										},
										xAxis : data[0],
										yAxis : {
											title : {
												text : ''
											},
											plotLines : [ {
												value : 0,
												width : 1,
												color : '#808080'
											} ]
										},
										tooltip : {
											valueSuffix : '元'
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'middle',
											borderWidth : 0
										},
										series : [ data[1], data[2], data[3] ]
									});
								})
								//統整表
								var myHead6 = $("#show_assetsall_table>thead")
								var myBody6 = $("#show_assetsall_table>tbody")
								myHead6.empty();
								myBody6.empty();
								$.getJSON("GetStockTableServlet", {
									"json" : "assetsjson",
									"need" : "assetsall",
									"stock_no" : stockno
								}, function(data) {
									myHead6.append("<th>年度/季度</th>")
									$.each(data, function(key, value) {
										
										var date = $("<th></th>").append(
												value.Date);
										myHead6.append(date)
									})
									var Body1 = $("<tr></tr>").append("<td>流動資產(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue1 = $("<td></td>").append(thousandComma(value.CurrentAssets));
										Body1.append(revenue1);
									})
// 									

									var Body2 = $("<tr></tr>").append(
											"<td>固定資產(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue2 = $("<td></td>").append(
												thousandComma(value.FixedAssets));
										Body2.append(revenue2);
									})
// 								
									var Body3 = $("<tr></tr>").append(
											"<td>總資產(單位:百萬)</td>")

									$.each(data, function(key, value) {
										var revenue3 = $("<td></td>").append(
												thousandComma(value.Assets));
										Body3.append(revenue3);
									})
									
									myBody6.append(Body1,Body2,Body3);
									$("#show_assetsall_table>thead>th").addClass(
											"th")
									$("#show_assetsall_table>tbody>tr>td")
											.addClass("td")

								})

								
								//流動資產
								var fixedAssetsjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=currentAssets&stock_no='
										+ stockno;
								$
										.getJSON(
												fixedAssetsjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab1info_2',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '流動資產'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'CurrentAssets(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '流動資產: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'currentAssets',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//currentAssets_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=currentAssets&stock_no=2330;
								var myHead1 = $("#show_currentAssets_table>thead")
								var myBody1 = $("#show_currentAssets_table>tbody")
								myHead1.empty();
								myBody1.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "balancesheetjson",
									"need" : "currentAssets",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>年度/季度</th>")
									myBody1.append("<td>流動資產(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead1.append(date)
										myBody1.append(revenue)
									})
									$("#show_currentAssets_table>thead>th")
											.addClass("th")
									$("#show_currentAssets_table>tbody>td")
											.addClass("td")

								})
								//固定資產
								var netIncomejsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=fixedAssets&stock_no='
										+ stockno;
								$
										.getJSON(
												netIncomejsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab2info_2',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '固定資產'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'FixedAssets(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '固定資產: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'fixedAssets',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//fixedAssets_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=fixedAssets&stock_no=2330;
								var myHead2 = $("#show_fixedAssets_table>thead")
								var myBody2 = $("#show_fixedAssets_table>tbody")
								myHead2.empty();
								myBody2.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "balancesheetjson",
									"need" : "fixedAssets",
									"stock_no" : stockno
								}, function(data) {
									myHead2.append("<th>年度/季度</th>")
									myBody2.append("<td>固定資產(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead2.append(date)
										myBody2.append(revenue)
									})
									$("#show_fixedAssets_table>thead>th")
											.addClass("th")
									$("#show_fixedAssets_table>tbody>td")
											.addClass("td")

								})
								//總資產

								var assetsjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=assets&stock_no='
										+ stockno;
								$
										.getJSON(
												assetsjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab3info_2',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '總資產'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Assets(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '總資產: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Assets',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});

												})
								//Assets_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=assets&stock_no=2330;
								var myHead3 = $("#show_assets_table>thead")
								var myBody3 = $("#show_assets_table>tbody")
								myHead3.empty();
								myBody3.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "balancesheetjson",
									"need" : "assets",
									"stock_no" : stockno
								}, function(data) {
									myHead3.append("<th>年度/季度</th>")
									myBody3.append("<td>總資產(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead3.append(date)
										myBody3.append(revenue)
									})
									$("#show_assets_table>thead>th").addClass(
											"th")
									$("#show_assets_table>tbody>td").addClass(
											"td")

								})
								
							})
			$("#show_balancesheet_2")
					.click(
							function() {
								//流動負債
								var currentLiabilitiesjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=currentLiabilities&stock_no='
										+ stockno;
								$
										.getJSON(
												currentLiabilitiesjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab1info_3',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '流動負債'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'CurrentLiabilities(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '流動負債: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'CurrentLiabilities',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//currentLiabilities_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=currentLiabilities&stock_no=2330;
								var myHead3 = $("#show_currentLiabilities_table>thead")
								var myBody3 = $("#show_currentLiabilities_table>tbody")
								myHead3.empty();
								myBody3.empty();
								$
										.getJSON(
												"ShowStockServlet",
												{
													"json" : "balancesheetjson",
													"need" : "currentLiabilities",
													"stock_no" : stockno
												},
												function(data) {
													myHead3
															.append("<th>年度/季度</th>")
													myBody3
															.append("<td>流動負債(單位:百萬元)</td>")
													$
															.each(
																	data,
																	function(
																			key,
																			value) {
																		var date = $(
																				"<th></th>")
																				.append(
																						data[key][0]);
																		var revenue = $(
																				"<td></td>")
																				.append(
																						thousandComma(data[key][1]));
																		myHead3
																				.append(date)
																		myBody3
																				.append(revenue)
																	})
													$(
															"#show_currentLiabilities_table>thead>th")
															.addClass("th")
													$(
															"#show_currentLiabilities_table>tbody>td")
															.addClass("td")

												})
								//長期負債
								var longTermLiabilitiesjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=longTermLiabilities&stock_no='
										+ stockno;
								$
										.getJSON(
												longTermLiabilitiesjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab2info_3',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '長期負債'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'LongTermLiabilities(thousand)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '長期負債: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'LongTermLiabilities',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//longTermLiabilities_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=longTermLiabilities&stock_no=2330;
								var myHead2 = $("#show_longTermLiabilities_table>thead")
								var myBody2 = $("#show_longTermLiabilities_table>tbody")
								myHead2.empty();
								myBody2.empty();
								$
										.getJSON(
												"ShowStockServlet",
												{
													"json" : "balancesheetjson",
													"need" : "longTermLiabilities",
													"stock_no" : stockno
												},
												function(data) {
													myHead2
															.append("<th>年度/季度</th>")
													myBody2
															.append("<td>長期負債(單位:百萬元)</td>")
													$
															.each(
																	data,
																	function(
																			key,
																			value) {
																		var date = $(
																				"<th></th>")
																				.append(
																						data[key][0]);
																		var revenue = $(
																				"<td></td>")
																				.append(
																						thousandComma(data[key][1]));
																		myHead2
																				.append(date)
																		myBody2
																				.append(revenue)
																	})
													$(
															"#show_longTermLiabilities_table>thead>th")
															.addClass("th")
													$(
															"#show_longTermLiabilities_table>tbody>td")
															.addClass("td")

												})
								//淨值
								var Netjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=net&stock_no='
										+ stockno;
								$
										.getJSON(
												Netjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab3info_3',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '淨值'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Net(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '淨值: <b>{point.y:f}百萬</b>'
																		},
																		series : [ {
																			name : 'Net',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//net_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=net&stock_no=2330;
								var myHead1 = $("#show_net_table>thead")
								var myBody1 = $("#show_net_table>tbody")
								myHead1.empty();
								myBody1.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "balancesheetjson",
									"need" : "net",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>年度/季度</th>")
									myBody1.append("<td>淨值(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead1.append(date)
										myBody1.append(revenue)
									})
									$("#show_net_table>thead>th")
											.addClass("th")
									$("#show_net_table>tbody>td")
											.addClass("td")

								})
								//總資產(負債+淨值)
								var Netjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=assets&stock_no='
										+ stockno;
								$
										.getJSON(
												Netjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab4info_3',
																	{
																		chart : {
																			type : 'column'
																		},
																		title : {
																			text : '總負債+淨值'
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')'
																		},
																		xAxis : {
																			type : 'category',
																			labels : {
																				rotation : -45,
																				style : {
																					fontSize : '13px',
																					fontFamily : 'Verdana, sans-serif'
																				}
																			}
																		},
																		yAxis : {
																			min : 0,
																			title : {
																				text : 'Assets(millions)'
																			}
																		},
																		legend : {
																			enabled : false
																		},
																		tooltip : {
																			pointFormat : '總負債+淨值: <b>{point.y}百萬</b>'
																		},
																		series : [ {
																			name : 'Assets',
																			data : data,
																		//	 					            dataLabels: {
																		//	 					                enabled: true,
																		//	 					                rotation: -90,
																		//	 					                color: '#FFFFFF',
																		//	 					                align: 'right',
																		//	 					                format: '{point.y:.1f}', // one decimal
																		//	 					                y: 10, // 10 pixels down from the top
																		//	 					                style: {
																		//	 					                    fontSize: '13px',
																		//	 					                    fontFamily: 'Verdana, sans-serif'
																		//	 					                }
																		//	 					            }
																		} ]
																	});
												})
								//assets_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=balancesheetjson&need=assets&stock_no=2330;
								var myHead = $("#show_assets_table>thead")
								var myBody = $("#show_assets_table>tbody")
								myHead.empty();
								myBody.empty();
								$.getJSON("ShowStockServlet", {
									"json" : "balancesheetjson",
									"need" : "assets",
									"stock_no" : stockno
								}, function(data) {
									myHead.append("<th>年度/季度</th>")
									myBody.append("<td>總負債+淨值(單位:百萬元)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												data[key][0]);
										var revenue = $("<td></td>").append(
												thousandComma(data[key][1]));
										myHead.append(date)
										myBody.append(revenue)
									})
									$("#show_assets_table>thead>th").addClass(
											"th")
									$("#show_assets_table>tbody>td").addClass(
											"td")

								})
								//統整表
								var debtjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=assetsjson&need=debt&stock_no='
										+ stockno;
								$.getJSON(debtjsonurl, function(data) {
									Highcharts.chart('tab5info_3', {
										title : {
											text : "負債表",
											x : -20
										//center
										},
										subtitle : {
											text : stockname + "(" + stockno
													+ ")",
											x : -20
										},
										xAxis : data[0],
										yAxis : {
											title : {
												text : ''
											},
											plotLines : [ {
												value : 0,
												width : 1,
												color : '#808080'
											} ]
										},
										tooltip : {
											valueSuffix : '千元'
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'middle',
											borderWidth : 0
										},
										series : [ data[1], data[2], data[3],
												data[4] ]
									});

								})

							})

			$("#show_balance")
					.click(
							function() {
								balancejsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=assetsjson&need=balance&stock_no='
										+ stockno;
								$
										.getJSON(
												balancejsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab1info_4',
																	{
																		title : {
																			text : "負債比率",
																			x : -20
																		//center
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')',
																			x : -20
																		},
																		xAxis : data[0],
																		yAxis : {
																			title : {
																				text : 'Asset liability ratio(%)'
																			},
																			plotLines : [ {
																				value : 0,
																				width : 1,
																				color : '#808080'
																			} ]
																		},
																		tooltip : {
																			valueSuffix : '%'
																		},
																		legend : {
																			layout : 'vertical',
																			align : 'right',
																			verticalAlign : 'middle',
																			borderWidth : 0
																		},
																		series : [ data[1] ]
																	});
												});
								//balance_table
								//http://localhost:8080/StatementHorse/ShowStockServlet?json=assetsjson&need=balance&stock_no=2330;
								var myHead = $("#show_balance_table>thead")
								var myBody = $("#show_balance_table>tbody")
								myHead.empty();
								myBody.empty();
								$.getJSON("GetStockTableServlet", {
									"json" : "assetsjson",
									"need" : "balance",
									"stock_no" : stockno
								}, function(data) {
									myHead.append("<th>年度/季度</th>")
									myBody.append("<td>負債比率(單位:%)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												value.Date);
										var revenue = $("<td></td>").append(
												value.Balance);
										myHead.append(date)
										myBody.append(revenue)

									})
									$("#show_balance_table>thead>th").addClass(
											"th")
									$("#show_balance_table>tbody>td").addClass(
											"td")

								})
								flowjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=assetsjson&need=flow&stock_no='
										+ stockno;
								$
										.getJSON(
												flowjsonurl,
												function(data) {
													Highcharts
															.chart(
																	'tab2info_4',
																	{
																		title : {
																			text : "流動比率",
																			x : -20
																		//center
																		},
																		subtitle : {
																			text : stockname
																					+ '('
																					+ stockno
																					+ ')',
																			x : -20
																		},
																		xAxis : data[0],
																		yAxis : {
																			title : {
																				text : 'Current Asset liability ratio(%)'
																			},
																			plotLines : [ {
																				value : 0,
																				width : 1,
																				color : '#808080'
																			} ]
																		},
																		tooltip : {
																			valueSuffix : '%'
																		},
																		legend : {
																			layout : 'vertical',
																			align : 'right',
																			verticalAlign : 'middle',
																			borderWidth : 0
																		},
																		series : [ data[1] ]
																	});
												});

								//flow_table
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=assetsjson&need=flow&stock_no=2330;
								var myHead1 = $("#show_flow_table>thead")
								var myBody1 = $("#show_flow_table>tbody")
								myHead1.empty();
								myBody1.empty();
								$.getJSON("GetStockTableServlet", {
									"json" : "assetsjson",
									"need" : "flow",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>年度/季度</th>")
									myBody1.append("<td>流動比率(單位:%)</td>")
									$.each(data, function(key, value) {
										var date = $("<th></th>").append(
												value.Date);
										var revenue = $("<td></td>").append(
												value.Balance);
										myHead1.append(date)
										myBody1.append(revenue)
									})
									$("#show_flow_table>thead>th").addClass(
											"th")
									$("#show_flow_table>tbody>td").addClass(
											"td")

								})

							})
			$("#show_profit")
					.click(
							function() {
								profitjsonurl = 'http://localhost:8080/StatementHorse/ShowStockServlet?json=incomejson&need=profit&stock_no='
										+ stockno;
								$.getJSON(profitjsonurl, function(data) {
									Highcharts.chart('tab1info_5', {
										title : {
											text : "利潤比例",
											x : -20
										//center
										},
										subtitle : {
											text : stockname + '(' + stockno
													+ ')',
											x : -20
										},
										xAxis : data[0],
										yAxis : {
											title : {
												text : 'Profit ratio(%)'
											},
											plotLines : [ {
												value : 0,
												width : 1,
												color : '#808080'
											} ]
										},
										tooltip : {
											valueSuffix : '%'
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'middle',
											borderWidth : 0
										},
										series : [ data[1], data[2], data[3] ]
									});

								})
								//flow_table
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=assetsjson&need=flow&stock_no=2330;
								//http://localhost:8080/StatementHorse/GetStockTableServlet?json=incomestatementjson&need=profit&stock_no=	
								
								var myHead1 = $("#show_profit_table>thead")
								var myBody1 = $("#show_profit_table>tbody")
								myHead1.empty();
								myBody1.empty();
								$.getJSON("GetStockTableServlet", {
									"json" : "incomestatementjson",
									"need" : "profit",
									"stock_no" : stockno
								}, function(data) {
									myHead1.append("<th>年度/季度</th>")
									$.each(data, function(key, value) {
										
										var date = $("<th></th>").append(
												value.Date);
										myHead1.append(date)
									})
									var Body1 = $("<tr></tr>").append("<td>毛利率(單位:%)</td>")

									$.each(data, function(key, value) {
										var revenue1 = $("<td></td>").append(value.MargainRatio);
										Body1.append(revenue1);
									})
// 									

									var Body2 = $("<tr></tr>").append(
											"<td>營業利率(單位:%)</td>")

									$.each(data, function(key, value) {
										var revenue2 = $("<td></td>").append(
												value.IncomeRatio);
										Body2.append(revenue2);
									})
// 									myBody1.after(Body2);
									var Body3 = $("<tr></tr>").append(
											"<td>淨利率(單位:%)</td>")

									$.each(data, function(key, value) {
										var revenue3 = $("<td></td>").append(
												value.NetIncomeRatio);
										Body3.append(revenue3);
									})
// 									myBody1.after(Body3);
									myBody1.append(Body1,Body2,Body3);
									$("#show_profit_table>thead>th").addClass(
											"th")
									$("#show_profit_table>tbody>tr>td")
											.addClass("td")

								})

							})

		})
	</script>






</body>




</html>