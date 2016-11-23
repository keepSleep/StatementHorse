<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>StatementHorse</title>
<%@include file="/top/Top.jsp"%>
<!--Bootstrap樣式-->
<%-- 	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"> --%>
<!--JQuery/JQueryUI/Bootstrap JS-->

<%-- 	<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script> --%>
	<script	src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
<%-- 	<script	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script> --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>

	<div class="container-fluid">
	
<!-- 頁面主體 開始 -->
			<div class="row-fluid">
				<div id="left-Blank" class="col-md-1"></div>
				<div id="mid-Blank" class="col-md-10" style="height:600px">
					<div class="row-fluid">
						<p/>
			
<!-- JQuery UI 左邊的區塊 -->
<div style="float:left; padding-right :30px ">

<div id="accordion">

<!-- 取出找到的追蹤清單並設置版面 -->
  <c:forEach begin="0" end="${tlSize }" var="num" >
  
  <div value="${tlName[num]}" name="show" id="${tlNo[num]}" style="background-color: lightblue ; color:black ; height: 45px">
  
 <span style="display:inline;font-size:26px;font-family: Microsoft JhengHei;">${tlName[num]}</span>
 <input type="button" style="float: right; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115112233859.ico); width:32px; height:32px; background-color: lightblue; border:none" name="chooesList" id="${tlNo[num]}">
  
  </div>
  
  <div style="width:300px;border:2px #d0d0d0 solid">	
    <table id="table${tlNo[num]}"></table><br>
    <form id="createText${tlNo[num]}" name="${mId[num]}"></form>
  </div>

  </c:forEach>
  
</div>
 
  <br>
  <fieldset style="font-size: 16px ; font-family: Microsoft JhengHei;">
    <label >資料顯示筆數</label>
    <label for="radio-1">10</label>
    <input type="radio" name="radio-1" value="10" id="radio-1" checked>
    <label for="radio-2">50</label>
    <input type="radio" name="radio-1" value="50" id="radio-2">
    <label for="radio-3">100</label>
    <input type="radio" name="radio-1" value="100" id="radio-3">
    <label for="radio-4">全部</label>
    <input type="radio" name="radio-1" value="0" id="radio-4">
  </fieldset>
  
   <input type="text" placeholder="新增追蹤清單" name="createListingText" style="width: 150px ; height: 25px ; font-family: Microsoft JhengHei;font-size: 16px">
   <input type="button" value="新增" name="createListing" style="height: 26px ; font-family: Microsoft JhengHei; font-size: 16px"><br>
   <span style='font-size: 16px ; font-family: Microsoft JhengHei ; color:red' id="showError2"></span>

</div>

<!-- JQuery UI 右邊的區塊 -->
<div id="tabs" style="float:left ; width:1000px ; height: 600px; overflow:auto;">
  
  <ul style=" font-size: 26px ; font-family: Microsoft JhengHei;">
<!--     <li><a href="#tabs-1">全部動態</a></li> -->
    <li><a href="#tabs-2" style="background-color: lightblue ; color:black ; padding: 5px">相關新聞</a></li>
    <li><a href="#tabs-3" style="background-color: lightblue ; color:black ; padding: 5px">每月營收</a></li>
    <li><a href="#tabs-4" style="background-color: lightblue ; color:black ; padding: 5px">財務報表</a></li>
<!--     <li><a href="#tabs-5">資產負債表</a></li> -->
<!--     <li><a href="#tabs-6">損益平衡表</a></li> -->
  </ul>
  
<!--   <div id="tabs-1" name="div1"></div> -->
  <div style=" font-size: 16px ; font-family: Microsoft JhengHei" id="tabs-2" name="div1"></div>
  <div style=" font-size: 16px ; font-family: Microsoft JhengHei" id="tabs-3" name="div1"></div>
  <div style=" font-size: 16px ; font-family: Microsoft JhengHei" id="tabs-4" name="div1"></div>
<!--   <div id="tabs-5" name="div1"></div> -->
<!--   <div id="tabs-6" name="div1"></div> -->

<!-- <input type="button" id="tsetbutton" value="showmore"> -->
  
</div>

<div id="dialog-1" title="設定" style="display:none">
  <br>
  <input style="font-size: 16px ; font-family: Microsoft JhengHei" type="text" name="changeNameText" placeholder="修改追蹤清單名稱">
  <input style="font-size: 16px ; font-family: Microsoft JhengHei" type="button" value="修改" name="changeName"><br>
  <br>
  <input style="font-size: 16px ; font-family: Microsoft JhengHei" type="button" value="刪除追蹤清單" name="deleteListing"><br>
  <br>
  <span style='font-size: 16px ; font-family: Microsoft JhengHei ; color:red' id="showError1"></span> 
</div>

<div id="dialog-2" style="display:none"></div>
<div id="dialog-3" style="display:none"></div>
<div id="dialog-4" style="display:none"></div>

<!-- 匯入需要的檔案 -->
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>

<script>

// function showFinancialStatements(StockNo,StatementDate){	
// 	alert(StockNo + "," + StatementDate);	
// };

var showMGRNum = 1;

function showMGR(StockNo,stockName){
	
	showMGRNum++;
	if(showMGRNum==5)
		showMGRNum=2;
	
	$( "#dialog-" + showMGRNum ).dialog({
		height: 400,
		 width: 600,
// 		 beforeClose: function( event, ui ) {
// 			  $('#dialog-' + showMGRNum ).empty();
//           },
	});
	
	$.getJSON("ShowStockServlet",{"json":"mgrjson","stock_no":StockNo}, function(data) {
		
		$('#dialog-' + showMGRNum ).highcharts('StockChart', 
				{
					rangeSelector : {
						selected : 5
					},
					
					title : {
						text : '月營收(' + StockNo + ')(單位:千元)'
					},
					
					series : [ {
						name : stockName,
						data : data,
						tooltip : {
							valueDecimals : 2
						}
					} ]
				});
	});
		
}

//JQuery UI
$( function() {
    $( "#accordion" ).accordion({heightStyle: "content"});
    $( "#tabs" ).tabs();
    });
    
//點選清單展開明細
$(function() {
	
	//設置按鈕
	var btns = $('div[name="show"]');
	var btnc = $('input[name="chooesList"]');
	var btncn = $('input[name="changeName"]');
	var btndl = $('input[name="deleteListing"]');
	var btncl = $('input[name="createListing"]');
			
	//點擊設定
	btnc.click(function(){
		
		var tlno = $(this).attr("id");
		var mId = $('form[id*="createText"]').attr("name");

		$( "#dialog-1" ).dialog({
			 height: 250,
			 width: 300,
			 draggable: false,
			 modal: true,
			 beforeClose: function( event, ui ) {
				  tlno = "";
				  mId = "";	
				  $('input[name="changeNameText"]').val("");
				  $('#showError1').empty();
               },
		}).prev(".ui-dialog-titlebar").css("background","lightblue").css("font-size","150%").css("font-family","Microsoft JhengHei");

		event.stopPropagation();
		
		//點擊修改清單名稱
		btncn.click(function() {
			
			var tlName = $('input[name="changeNameText"]').val();
			
			$.get("ChangeName?",{"listingName":tlName,"memberId":mId,"listingNo":tlno},function(data) {
				
				if(data=="修改追蹤清單名稱成功"){
				location.reload();
				}else{
					$('#showError1').empty();
					$('#showError1').append(data)
				}

				});
			});
				
				//點擊刪除清單
		btndl.click(function() {
				
			$.get("DeleteListing?",{"listingNo":tlno,"memberId":mId},function(data) {
				
				if(data=="刪除追蹤清單成功"){
				location.reload();
				}else{
					$('#showError1').empty();
					$('#showError1').append(data)
				}

				});
			});	
	});
	
	//點擊新增清單
	btncl.click(function() {
		var mId = $('form[id*="createText"]').attr("name");
		var tlName = $('input[name="createListingText"]').val();
			
		$.get("CreateListing?",{"listingName":tlName,"memberId":mId},function(data) {
			
			if(data=="新增追蹤清單成功"){
			location.reload();
			}else{
				$('#showError2').empty();
				$('#showError2').append(data)
				$('input[name="createListingText"]').val("");
			}

			});
		});
	
	//點擊查詢
	btns.click(function() {
		
		//設置屬性
		var tlno = $(this).attr("id");
		var tab = $("#table" + tlno);
		var cText = $("#createText"+ tlno );
		
		//清空列表
		$('table[id*="table"]').empty();
		$('form[id*="createText"]').empty();
		$('input[name="stockNo"]').empty();
		$('div[name="div1"]').empty();
		
		 //checkbox　radio
	    var searchNum =$("input[name='radio-1']:checked").val();
		 
		//查詢新聞		
		$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//				var jsonobj = eval("(" + data + ")");

			$.each(data,function(){

//				var StockNo = this.StockNo;
				var NewsDate = this.NewsDate;	
				var NewsTitle = this.NewsTitle;	
				var NewsWebaddress = this.NewsWebaddress;
				

				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
// 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
// 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
		
				});
		
		});
		
		//查詢月營收			
		$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var RevenueDate = this.RevenueDate;	
				var Revenue = this.Revenue;	
				var PostDate = this.PostDate;
				
				$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
// 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
		
				});
			
// 			searchMGRNum += 10 ;
		
		});
		
		//查詢資產負債表		
// 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
			
// 			$.each(data,function(){
				
// 				var StockNo = this.StockNo;
// 				var StockName = this.StockName;
// 				var StatementDate = this.StatementDate;
// //					var Assets = this.Assets;
// //					var RetainedEarnings = this.RetainedEarnings;
				
// 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
		
// 				});
		
// 		});
		
		//查詢損益平衡表	
// 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
			
// 			$.each(data,function(){
				
// 				var StockNo = this.StockNo;
// 				var StockName = this.StockName;
// 				var StatementDate = this.StatementDate;
// //					var OperatingRevenue = this.OperatingRevenue;
// //					var NetIncome = this.NetIncome;
				
// 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
		
// 				});
		
// 		});
		
		//查詢財務報表	
		$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var StatementDate = this.StatementDate;
				var PostDate = this.PostDate;
// 				var PostTime = this.PostTime;
				
				$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
// 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
// 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
				
				});
		
		});
		
		//新增列表 + 設置 del
		$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
			
			if(data==""){
				
				tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
				$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
				$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
				$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
			}
			
			$.each(data,function(){
				var ListingNo = this.ListingNo;
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
			tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
					'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
			});	
			$(del);
			});
					
		//delete
		function del(){
			
			//設置刪除鍵
		var btnl = $('input[value="取消追蹤"]');
			
			//點擊刪除
			btnl.click(function() {
				
				$.get("DeleteStock?",{"stockNo":$(this).attr("id"),"listingNo":$(this).attr("name")},function(data) {
				
				//清空列表
				$('table[id*="table"]').empty();
				$('div[name="div1"]').empty();
				$('#showError3').empty();
				
				var searchNum =$("input[name='radio-1']:checked").val();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//						var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
						var Revenue = this.Revenue;	
						var PostDate = this.PostDate;
						
						$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
					
//		 			searchMGRNum += 10 ;
				
				});
				
				//查詢資產負債表		
//		 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var Assets = this.Assets;
		// //					var RetainedEarnings = this.RetainedEarnings;
						
//		 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢損益平衡表	
//		 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var OperatingRevenue = this.OperatingRevenue;
		// //					var NetIncome = this.NetIncome;
						
//		 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢財務報表	
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
//		 				var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					
					if(data==""){
						
						tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
						$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
					}
					
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
							'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del);
					});
				});
			});
		};
					
		//設置選擇列
		$("#createText"+tlno).append('<input type="text" placeholder="請輸入股號" name="godText" style="width:150px ; margin-right:10px ; font-size: 16px ; font-family: Microsoft JhengHei">');	
		$("#createText"+tlno).append('<input type="button" style="background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-2016111512562799.ico); width:16px; height:16px; background-color: #FFFFFF; border:none " name="createStock" id="' + tlno + '"><br>');
		$("#createText"+tlno).append('<span style="font-size: 16px ; font-family: Microsoft JhengHei ; color:red" id="showError3"></span>');
		
		//自動完成
		$.getJSON("GetStock?",{},function(data) {
				
			var stock = [];
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
				
				stock.push(StockNo + " " + StockName);

			});
			
			$('input[name="godText"]').autocomplete({source: stock});
			
		});
		
		//禁止按 ENTER 自動送出
		$('input[name="godText"]').keypress(function(){			
		    var keycode = window.event.keyCode;
		    if( keycode == 13 ){
		        window.event.returnValue=false;
		    }	
		});
		
		//設置選擇鍵
		var btncs = $('input[name="createStock"]');			
		
		//點擊新增個股
		btncs.click(function() {
			var tlno = $(this).attr("id");
			var sno = $('input[name="godText"]').val();
			$.get("CreateStock?",{"listingNo":tlno,"stockNo":sno},function(data) {
				
				if(data=="追蹤個股成功"){

				//清空列表
				$('table[id*="table"]').empty();
				$('input[name="godText"]').val("");
				$('div[name="div1"]').empty();
				$('#showError3').empty();
				
				var searchNum =$("input[name='radio-1']:checked").val();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//						var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
						var Revenue = this.Revenue;	
						var PostDate = this.PostDate;
						
						$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
					
//		 			searchMGRNum += 10 ;
				
				});
				
				//查詢資產負債表		
//		 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var Assets = this.Assets;
		// //					var RetainedEarnings = this.RetainedEarnings;
						
//		 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢損益平衡表	
//		 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var OperatingRevenue = this.OperatingRevenue;
		// //					var NetIncome = this.NetIncome;
						
//		 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢財務報表	
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
//		 				var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					
					if(data==""){
						
						tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
						$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
					}
					
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
							'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del);
					});
				
				}else{
					 $('#showError3').empty();
					 $('#showError3').append(data);
					 $('input[name="godText"]').val("");
				}
				
				});
			});	
		});
	});
					
									
///////////////////////////////// 進入頁面時預設展開第一個清單 //////////////////////////////////////////

$(function() {
	
	//設置屬性預設排序 0 放第一個
		var tlno = ${tlNo[0]};
		var mId = ${mId[0]};
		var tab = $("#table" + tlno);
		var cText = $("#createText"+ tlno );
		
	    //checkbox　radio
	    var searchNum =$("input[name='radio-1']:checked").val();
	    
	    $("input[name='radio-1']").click(function(){
	    	
	    	var searchNum =$("input[name='radio-1']:checked").val();
	    	$('div[name="div1"]').empty();
	    	
	    	//查詢新聞		
			$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//					var jsonobj = eval("(" + data + ")");

				$.each(data,function(){

//					var StockNo = this.StockNo;
					var NewsDate = this.NewsDate;	
					var NewsTitle = this.NewsTitle;	
					var NewsWebaddress = this.NewsWebaddress;
					

					$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
//	 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
			
					});
			
			});
			
			//查詢月營收			
			$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
				
				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var RevenueDate = this.RevenueDate;	
					var Revenue = this.Revenue;	
					var PostDate = this.PostDate;
					
					$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
//	 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
			
					});
				
//	 			searchMGRNum += 10 ;
			
			});
	    	
			//查詢財務報表	
			$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var StatementDate = this.StatementDate;
					var PostDate = this.PostDate;
//	 				var PostTime = this.PostTime;
					
					$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//	 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//	 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
					
					});
			
			});
	    	
	    });
		
		//查詢新聞		
		$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//				var jsonobj = eval("(" + data + ")");

			$.each(data,function(){

//				var StockNo = this.StockNo;
				var NewsDate = this.NewsDate;	
				var NewsTitle = this.NewsTitle;	
				var NewsWebaddress = this.NewsWebaddress;
				

				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
// 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
// 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
		
				});
		
		});
		
		//查詢月營收			
		$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var RevenueDate = this.RevenueDate;	
				var Revenue = this.Revenue;	
				var PostDate = this.PostDate;
				
				$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
// 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
		
				});
			
// 			searchMGRNum += 10 ;
		
		});
		
		//查詢資產負債表		
// 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
			
// 			$.each(data,function(){
				
// 				var StockNo = this.StockNo;
// 				var StockName = this.StockName;
// 				var StatementDate = this.StatementDate;
// //					var Assets = this.Assets;
// //					var RetainedEarnings = this.RetainedEarnings;
				
// 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
		
// 				});
		
// 		});
		
		//查詢損益平衡表	
// 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
			
// 			$.each(data,function(){
				
// 				var StockNo = this.StockNo;
// 				var StockName = this.StockName;
// 				var StatementDate = this.StatementDate;
// //					var OperatingRevenue = this.OperatingRevenue;
// //					var NetIncome = this.NetIncome;
				
// 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
		
// 				});
		
// 		});
		
		//查詢財務報表	
		$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var StatementDate = this.StatementDate;
				var PostDate = this.PostDate;
// 				var PostTime = this.PostTime;
				
				$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
// 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
// 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
				
				});
		
		});
		
		//新增列表 + 設置 del
		$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
			
			if(data==""){
				
				tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
				$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
				$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
				$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
			}
			
			$.each(data,function(){
				var ListingNo = this.ListingNo;
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
			tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
					'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
			});	
			$(del2);
			});
	
		//del
	function del2(){
			
			//設置刪除鍵
		var btnl = $('input[value="取消追蹤"]');
			
			//點擊刪除
		btnl.click(function() {
			$.get("DeleteStock?",{"stockNo":$(this).attr("id"),"listingNo":$(this).attr("name")},function(data) {
			
			//清空列表
			$('table[id*="table"]').empty();
			$('div[name="div1"]').empty();
			$('#showError3').empty();
			
			var searchNum =$("input[name='radio-1']:checked").val();
			
			//查詢新聞		
			$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//					var jsonobj = eval("(" + data + ")");

				$.each(data,function(){

//					var StockNo = this.StockNo;
					var NewsDate = this.NewsDate;	
					var NewsTitle = this.NewsTitle;	
					var NewsWebaddress = this.NewsWebaddress;
					

					$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
//	 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
			
					});
			
			});
			
			//查詢月營收			
			$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
				
				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var RevenueDate = this.RevenueDate;	
					var Revenue = this.Revenue;	
					var PostDate = this.PostDate;
					
					$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
//	 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
			
					});
				
//	 			searchMGRNum += 10 ;
			
			});
			
			//查詢資產負債表		
//	 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
				
//	 			$.each(data,function(){
					
//	 				var StockNo = this.StockNo;
//	 				var StockName = this.StockName;
//	 				var StatementDate = this.StatementDate;
	// //					var Assets = this.Assets;
	// //					var RetainedEarnings = this.RetainedEarnings;
					
//	 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
			
//	 				});
			
//	 		});
			
			//查詢損益平衡表	
//	 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
				
//	 			$.each(data,function(){
					
//	 				var StockNo = this.StockNo;
//	 				var StockName = this.StockName;
//	 				var StatementDate = this.StatementDate;
	// //					var OperatingRevenue = this.OperatingRevenue;
	// //					var NetIncome = this.NetIncome;
					
//	 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
			
//	 				});
			
//	 		});
			
			//查詢財務報表	
			$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var StatementDate = this.StatementDate;
					var PostDate = this.PostDate;
//	 				var PostTime = this.PostTime;
					
					$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//	 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//	 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
					
					});
			
			});
			
			//新增列表 + 設置 del
			$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
				
				if(data==""){
					
					tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
					$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
					$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
					$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
				}
				
				$.each(data,function(){
					var ListingNo = this.ListingNo;
					var StockNo = this.StockNo;	
					var StockName = this.StockName;	
				tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
						'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
				});	
				$(del2);
				});
			});
		});
	};
			
				//設置選擇列
		$("#createText"+tlno).append('<input type="text" placeholder="請輸入股號" name="godText" style="width:150px ; margin-right:10px ; font-size: 16px ; font-family: Microsoft JhengHei">');	
		$("#createText"+tlno).append('<input type="button" style="background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-2016111512562799.ico); width:16px; height:16px; background-color: #FFFFFF; border:none " name="createStock" id="' + tlno + '"><br>');
		$("#createText"+tlno).append('<span style="font-size: 16px ; font-family: Microsoft JhengHei ; color:red" id="showError3"></span>');
		
		//自動完成
		$.getJSON("GetStock?",{},function(data) {
				
			var stock = [];
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
				
				stock.push(StockNo + " " + StockName);

			});
			
			$('input[name="godText"]').autocomplete({source: stock});
			
		});
		
				//禁止按 ENTER 自動送出
		$('input[name="godText"]').keypress(function(){			
		    var keycode = window.event.keyCode;
		    if( keycode == 13 ){
		        window.event.returnValue=false;
		    }	
		});
		
				//設置選擇鍵
		var btncs = $('input[name="createStock"]');
						
				//點擊新增個股
		btncs.click(function() {
			var tlno = $(this).attr("id");
			var sno = $('input[name="godText"]').val();
			$.get("CreateStock?",{"listingNo":tlno,"stockNo":sno},function(data) {
				
				if(data=="追蹤個股成功"){
				
				//清空列表
				$('table[id*="table"]').empty();
				$('input[name="godText"]').val("");
				$('div[name="div1"]').empty();
				$('#showError3').empty();
				
				var searchNum =$("input[name='radio-1']:checked").val();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//						var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + NewsTitle + ' (' + NewsDate + ')' + '</div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
						var Revenue = this.Revenue;	
						var PostDate = this.PostDate;
						
						$('#tabs-3').append( '<a href="javascript: void(0)" onclick="showMGR(' + StockNo + ',\'' + StockName + '\')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + RevenueDate + ' 月營收 ' + Revenue + ' (單位:千元) (' + PostDate + ')' +'</div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
					
//		 			searchMGRNum += 10 ;
				
				});
				
				//查詢資產負債表		
//		 		$.getJSON("SearchBalanceSheet?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var Assets = this.Assets;
		// //					var RetainedEarnings = this.RetainedEarnings;
						
//		 				$('#tabs-5').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季資產負債表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢損益平衡表	
//		 		$.getJSON("SearchIncomeStatement?",{"ListingNo":tlno},function(data) {
					
//		 			$.each(data,function(){
						
//		 				var StockNo = this.StockNo;
//		 				var StockName = this.StockName;
//		 				var StatementDate = this.StatementDate;
		// //					var OperatingRevenue = this.OperatingRevenue;
		// //					var NetIncome = this.NetIncome;
						
//		 				$('#tabs-6').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季損益平衡表</a><br>');
				
//		 				});
				
//		 		});
				
				//查詢財務報表	
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno,"searchNum":searchNum},function(data) {

					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
//		 				var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="${pageContext.servletContext.contextPath}/SelectStatementServlet?stock_no='+StockNo+'&statement_date='+StatementDate+'" style="text-decoration:none;" Target="_blank"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="#" onclick="showFinancialStatements(' + StockNo + "," + StatementDate + ')" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + ')</div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					
					if(data==""){
						
						tab.append('<span style="font-size: 16px ; font-family: Microsoft JhengHei">還沒有追蹤的個股喔!</span>');
						$('#tabs-2').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-3').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
						$('#tabs-4').append('<span style="font-size: 36px ; font-family: Microsoft JhengHei ; width:400px ; text-align:center">追蹤個股後可查看相關資訊</span>');
					}
					
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr style="font-size: 16px ; font-family: Microsoft JhengHei"><td width=20%>' + StockNo + '</td><td width=75%><a href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no='+StockNo+'" Target="_blank">' + StockName + 
							'</a></td><td width=5% align=right><input type="button" value="取消追蹤" style="line-height:0; font-size:0; overflow:hidden; background-image:url(${pageContext.servletContext.contextPath}/picture/favicon-20161115124600343.ico); width:16px; height:16px; background-color: #FFFFFF; border:none" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del2);
					});
				
				}else{
					 $('#showError3').empty();
					 $('#showError3').append(data);
					 $('input[name="godText"]').val("");
				}
				
				});
			});						
		});	
 
</script>

					</div>
				</div>
				<div id="right-Blank" class="col-md-1"></div>
			</div>
<!-- 頁面主體 結束 -->
	</div>
</body>
</html>