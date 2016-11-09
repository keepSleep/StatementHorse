<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script
	src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
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
<%-- <script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script> --%>
<script
	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
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
</style>
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="stockno" value="${stockVO.stockNo}" />
	<input type="hidden" id="stockname" value="${stockVO.stockName}" />
	<%@include file="Top.jsp"%>
	<div class="container">
		<div class="page-header">
			<h3 style="font-family: '新細明體'; color: #00BBFF">
				<a href="#" id=first_page> 個股資料</a> <span
					class="pull-right label label-default"></span> <span
					style="margin: 0px 0px 0px 850px">


					<div class="btn-group">
						<button type="button" class="btn btn-info btn-xs dropdown-toggle"
							data-toggle="dropdown" style="font-size: x-large;">
							加入追蹤 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right" role="menu"
							style="font-size: x-large" id="TrackListing_id">
							<c:forEach var="TrackListingVO" items="${list}">
								<li><input type="checkbox"
									value="${TrackListingVO.listingNo}">${TrackListingVO.listingName}</li>
							</c:forEach>
						</ul>
					</div>
				</span>
			</h3>
		</div>

		<div style="width: 300px; position: absolute; left: 250px; top: 250px">
			<ul class="nav" id="side-menu" style="font-size: x-large;">

				<li id="show_mgr"><a href="#"><i
						class="fa fa-file-text fa-fw"></i>公司最新動態<span class="active"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
					</ul></li>
				<li><a href="#"><i class="fa fa-file-text fa-fw"></i>三大財務報表<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
						<li><a href="#" id="show_incomestatement">損益表</a></li>
						<li><a href="#" id="show_balancesheet_1">總資產</a></li>
						<li><a href="#" id="show_balancesheet_2">負債與股東權益</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>獲利分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
						<li><a href="#" id="show_profit">利潤比率</a></li>
					
					</ul></li>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>安全性分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level" style="padding-left: 30px">
						<li><a href="#" id="show_balance">財務結構比率</a></li>
						<li><a href="#" id="">流速動比率</a></li>
					</ul></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<!-- 					</div> -->

			<!----------------------每頁不同從這裡開始----------------------------------------------------->
			<!--個股資料-->
			<table border='3 bordercolor='
				black'
				style="text-indent: 20px; width: 800px; height: 500px; position: absolute; left: 300px; top: 0px; display:; font-size: x-large; margin-bottom: 50px"
				id="show_stock">
				<tr>
					<td style="background-color: lightblue">股號</td>
					<td style="background-color: #FFFFBB">${stockVO.stockNo}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">股名</td>
					<td style="background-color: #FFFFBB">${stockVO.stockName}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">產業類別</td>
					<td style="background-color: #FFFFBB">${stockVO.industry}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">董事長</td>
					<td style="background-color: #FFFFBB">${stockVO.chairman}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">公司成立日期</td>
					<td style="background-color: #FFFFBB">${stockVO.companyEstablishDate}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">統一編號</td>
					<td style="background-color: #FFFFBB">${stockVO.companyTaxId}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">上市日期</td>
					<td style="background-color: #FFFFBB">${stockVO.listingDate}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">股東會日期</td>
					<td style="background-color: #FFFFBB">${stockVO.stockholdersMeetingDate}</td>
				</tr>
				<tr>

					<td style="background-color: lightblue">資本額(單位:百萬)</td>
					<td style="background-color: #FFFFBB">${stockVO.capital}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">公司網址</td>
					<td style="background-color: #FFFFBB">${stockVO.stockWebaddress}</td>
				</tr>
				<tr>
					<td style="background-color: lightblue">簽證會計師</td>
					<td style="background-color: #FFFFBB">${stockVO.accountingFirm}</td>

				</tr>
			</table>
			<!--個股最新動態 -->
			<div class="col-md-6"
				style="width: 800px; position: absolute; left: 300px; top: 0px; display: none"
				id="show_div_mgr">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-size: x-large">
							<li class="active"><a href="#tab1info" data-toggle="tab"
								id="111">月營收</a></li>
							<li><a href="#tab2info" data-toggle="tab">每股盈餘</a></li>
							<li><a href="#tab3info" data-toggle="tab">股價</a></li>
							<li><a href="#tab4info" data-toggle="tab">股利</a></li>
							<li><a href="#tab5info" data-toggle="tab">本益比</a></li>
						</ul>
					</div>
					<div class="panel-body" style="height: 630px">
						<div class="tab-content" id="container">
							<div class="tab-pane fade in active" id="tab1info"
								style="width: 730px"></div>
							<div class="tab-pane fade" id="tab2info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab3info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab4info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab5info" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 損益表 -->
			<div class="col-md-6"
				style="width: 800px; position: absolute; left: 300px; top: 0px; display: none"
				id="show_div_incomestatement">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-size: x-large">
							<li class="active"><a href="#tab1info_1" data-toggle="tab">季營收</a></li>
							<li><a href="#tab2info_1" data-toggle="tab">季毛利</a></li>
							<li><a href="#tab3info_1" data-toggle="tab">營業利益</a></li>
							<li><a href="#tab4info_1" data-toggle="tab">稅前淨利</a></li>
							<li><a href="#tab5info_1" data-toggle="tab">稅後淨利</a></li>
						</ul>
					</div>
					<div class="panel-body" style="height: 630px">
						<div class="tab-content" id="container_1" >
							<div class="tab-pane fade in active" id="tab1info_1"
								style="width: 730px"></div>
							<div class="tab-pane fade" id="tab2info_1" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab3info_1" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab4info_1" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab5info_1" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 資產表 -->
			<div class="col-md-6"
				style="width: 800px; position: absolute; left: 300px; top: 0px; display: none"
				id="show_div_balancesheet_1">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-size: x-large">
							<li class="active"><a href="#tab1info_2" data-toggle="tab">流動資產</a></li>
							<li><a href="#tab2info_2" data-toggle="tab">固定資產</a></li>
							<li><a href="#tab3info_2" data-toggle="tab">長期投資</a></li>
							<li><a href="#tab4info_2" data-toggle="tab">總資產</a></li>
						</ul>
					</div>
					<div class="panel-body" style="height: 630px">
						<div class="tab-content" id="container_2">
							<div class="tab-pane fade in active" id="tab1info_2"
								style="width: 730px"></div>
							<div class="tab-pane fade" id="tab2info_2" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab3info_2" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab4info_2" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab5info_2" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 負債表 -->
			<div class="col-md-6"
				style="width: 800px; position: absolute; left: 300px; top: 0px; display: none"
				id="show_div_balancesheet_2">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-size: x-large">
							<li class="active"><a href="#tab1info_3" data-toggle="tab">流動負債</a></li>
							<li><a href="#tab2info_3" data-toggle="tab">長期負債</a></li>
							<li><a href="#tab3info_3" data-toggle="tab">淨值</a></li>
							<li><a href="#tab4info_3" data-toggle="tab">總負債+淨值</a></li>
						</ul>
					</div>
					<div class="panel-body" style="height: 630px">
						<div class="tab-content" id="container_2">
							<div class="tab-pane fade in active" id="tab1info_2"
								style="width: 730px"></div>
							<div class="tab-pane fade" id="tab2info_3" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab3info_3" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab4info_3" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab5info_3" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>
<!--利潤比率  -->
			<div class="col-md-6" style="border:3px solid lightblue;width: 780px; height:500px;padding:0px; position: absolute; left: 315px; top: 0px; display:none;margin-bottom: 50px" 
				id="show_div_profit">
			</div>
<!-- 財務結構比率 -->
			<div class="col-md-6" style="border:3px solid lightblue;width: 780px; height:500px;padding:0px; position: absolute; left: 315px; top: 0px; display:none;margin-bottom: 50px" 
				id="show_div_balance">
			</div>










			<!----------------------每頁不同從這裡ㄐㄧㄝ----------------------------------------------------->

			<!-- 			</tr> -->
			</table>

			<script>

				
				$(function() {
					var stockno = $("#stockno").val();
					var stockname = $("#stockname").val();
					var url = "ShowStockServlet?action=stock&stock_no="
							+ stockno;
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
					$("#show_profit").click(function(){
						$("#show_div_mgr").css("display", "none");
						$("#show_stock").css("display", "none");
						$("#show_div_incomestatement").css("display", "none");
						$("#show_div_balancesheet_1").css("display", "none");
						$("#show_div_balancesheet_2").css("display", "none");
						$("#show_div_balance").css("display", "none");
						$("#show_div_profit").css("display", "");
					
					
					
					})
					$("#show_balance").click(function(){
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
							$("#TrackListing_id>li>input:eq(0)").prop(
									"checked", true)
						} else {
							$("#TrackListing_id>li>input:eq(0)").prop(
									"checked", false)
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
							$("#TrackListing_id>li>input:eq(1)").prop(
									"checked", true)
						} else {
							$("#TrackListing_id>li>input:eq(1)").prop(
									"checked", false)
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
					$("#show_mgr").click(function() {
						
						//mgrjson
						var mgrurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=mgrjson&stock_no='
								+ stockno;
						$.getJSON(mgrurl, function(data) {

							$('#tab1info').highcharts('StockChart', {
								rangeSelector : {
									selected : 5
								},
								title : {
									text : '月營收(' + stockno + ')'
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
						
						
						
						

						//pricejson
						var priceurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=pricejson&stock_no='
								+ stockno
						$.getJSON(priceurl, function(data) {
							$('#tab3info').highcharts('StockChart', {
								rangeSelector : {
									selected : 1
								},
								title : {
									text : '股價(' + stockno + ')'
								},
								series : [ {
									type : 'candlestick',
									name : stockname,
									data : data,
									dataGrouping : {
										units : [ [ 'week', // unit name
										[ 1 ] // allowed multiples
										], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
									}
								} ]
							});
						});
						//incomestatementjson
						var incomestatementjsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=earningPerShare&stock_no='
								+ stockno;
						$.getJSON(incomestatementjsonurl, function(data) {
							// Create the chart
							$('#tab2info').highcharts('StockChart', {
								rangeSelector : {
									selected : 5
								},
								title : {
									text : '每股盈餘(' + stockno + ')'
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
						//divident 股利
						var dividenturl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=dividendjson&stock_no='
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
									name :"",
									data : data,
								//				                 dataGrouping: {
								//				                     units: [[
								//				                         'week', // unit name
								//				                         [1] // allowed multiples
								//				                     ], [
								//				                         'month',
								//				                         [1, 2, 3, 4, 6]
								//				                     ]]
								//				                 }
								} ]
							});
						});
						//本益比JSON
						var PERurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=PERjson&stock_no='
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
					
					$("#show_incomestatement").click(function() {
						//incomestatementjson季營收
						var revenuejsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingRevenue&stock_no='
								+ stockno;
						$.getJSON(revenuejsonurl, function(data) {

							// creaㄅte the chart
							$('#tab1info_1').highcharts('StockChart', {
								chart : {
									alignTicks : false
								},

								rangeSelector : {
									selected : 5
								},

								title : {
									text : '季營收(' + stockno + ')'
								},

								series : [ {
									type : 'column',
									name :"",
									data : data,
								//				                 dataGrouping: {
								//				                     units: [[
								//				                         'week', // unit name
								//				                         [1] // allowed multiples
								//				                     ], [
								//				                         'month',
								//				                         [1, 2, 3, 4, 6]
								//				                     ]]
								//				                 }
								} ]
							});
						});
						//incomestatementjson季毛利
						var operatingMargainjsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingMargain&stock_no='
								+ stockno;
						$.getJSON(operatingMargainjsonurl, function(data) {

							// creaㄅte the chart
							$('#tab2info_1').highcharts('StockChart', {
								chart : {
									alignTicks : false
								},

								rangeSelector : {
									selected : 5
								},

								title : {
									text : '季毛利(' + stockno + ')'
								},

								series : [ {
									type : 'column',
									name :"",
									data : data,
								//				                 dataGrouping: {
								//				                     units: [[
								//				                         'week', // unit name
								//				                         [1] // allowed multiples
								//				                     ], [
								//				                         'month',
								//				                         [1, 2, 3, 4, 6]
								//				                     ]]
								//				                 }
								} ]
							});
						});
					var operatingIncomejsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=operatingIncome&stock_no='
							+ stockno;
					$.getJSON(operatingIncomejsonurl, function(data) {
						// Create the chart
						$('#tab3info_1').highcharts('StockChart', {
							rangeSelector : {
								selected : 5
							},
							title : {
								text : '營業利益(' + stockno + ')'
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
					var oibtjsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=oibt&stock_no='
						+ stockno;
				$.getJSON(oibtjsonurl, function(data) {

					// creaㄅte the chart
					$('#tab4info_1').highcharts('StockChart', {
						chart : {
							alignTicks : false
						},

						rangeSelector : {
							selected : 5
						},

						title : {
							text : '稅前淨利(' + stockno + ')'
						},

						series : [ {
							type : 'column',
							name :"",
							data : data,
						//				                 dataGrouping: {
						//				                     units: [[
						//				                         'week', // unit name
						//				                         [1] // allowed multiples
						//				                     ], [
						//				                         'month',
						//				                         [1, 2, 3, 4, 6]
						//				                     ]]
						//				                 }
						} ]
					});
				});
						
				var netIncomejsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=incomestatementjson&need=netIncome&stock_no='
					+ stockno;
				
				 $.getJSON(netIncomejsonurl, function (data) {

				        // Create the chart
				        Highcharts.stockChart('tab5info_1', {


				            rangeSelector: {
				                selected: 5
				            },

				            title: {
				                text: '稅後淨利(' + stockno + ')'
				            },

				            series: [{
				                name: stockname,
				                data: data,
				                marker: {
				                    enabled: true,
				                    radius: 3
				                },
				                shadow: true,
				                tooltip: {
				                    valueDecimals: 2
				                }
				            }]
				        });
				    });

					})	
					$("#show_balance").click(function() {
						balancejsonurl = 'http://localhost:8081/StatementHorse/ShowStockServlet?json=balancesheetjson&need=balance&stock_no='
							+ stockno;
						
						 $.getJSON(balancejsonurl, function (data) {

						        // Create the chart
						        Highcharts.stockChart('show_div_balance', {


						            rangeSelector: {
						                selected: 5
						            },

						            title: {
						                text: '負債比(' + stockno + ')'
						            },

						            series: [{
						                name: stockname,
						                data: data,
						                marker: {
						                    enabled: true,
						                    radius: 3
						                },
						                shadow: true,
						                tooltip: {
						                    valueDecimals: 2
						                }
						            }]
						        });
						    });

					})
				})
			</script>
<!-- 獲利分析 -->
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript">
 	$(function(){
 		var result=new Array();
 		var stockno = $("#stockno").val();
 		var stockname = $("#stockname").val();
 		$.getJSON("http://localhost:8081/StatementHorse/ShowStockServlet?json=profitjson&need=grossMargin&stock_no="+stockno,function(data){
 			result=data
 		})
 		$("#show_profit").click(function(){
 		
 		

      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable(result)
        var options = {
          title: stockname,
          curveType: 'function',
          legend: { position: 'bottom'}
        };

        var chart = new google.visualization.LineChart(document.getElementById('show_div_profit'));

        chart.draw(data, options);
      }
 		})
		
 	})
    </script>

			
			


			
			</body>
</html>