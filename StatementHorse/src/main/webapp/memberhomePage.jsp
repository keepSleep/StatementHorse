<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>追蹤清單</title>
<!--Bootstrap樣式-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<!--JQuery/JQueryUI/Bootstrap JS-->
	<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
	<script	src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
	<script	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

<%@include file="/top/Top.jsp"%>
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
  
  <div value="${tlName[num]}" name="show" id="${tlNo[num]}" style="background-color: lightblue">
  
 <h3 style="display:inline;">${tlName[num]}</h3>
 <input type="button" value="設定" style="float: right" name="chooesList" id="${tlNo[num]}">
  
  </div>
  
  <div style="width:400px;border:3px #cccccc dashed;">	
    <table id="table${tlNo[num]}"></table><br>
    <form id="createText${tlNo[num]}" name="${mId[num]}"></form>
  </div>

  </c:forEach>
  
</div>

 <br>
 <input type="text" placeholder="新增追蹤清單" name="createListingText" style="width: 150px ; height: 20px">
 <input type="button" value="新增" name="createListing" style="height: 25px">

</div>

<!-- JQuery UI 右邊的區塊 -->
<div id="tabs" style="float:left ; width:900px ; height: 600px; overflow:auto">
  
  <ul>
<!--     <li><a href="#tabs-1">全部動態</a></li> -->
    <li><a href="#tabs-2" style="background-color: lightblue">相關新聞</a></li>
    <li><a href="#tabs-3" style="background-color: lightblue">每月營收</a></li>
    <li><a href="#tabs-4" style="background-color: lightblue">財務報表</a></li>
<!--     <li><a href="#tabs-5">資產負債表</a></li> -->
<!--     <li><a href="#tabs-6">損益平衡表</a></li> -->
  </ul>
  
<!--   <div id="tabs-1" name="div1"></div> -->
  <div id="tabs-2" name="div1"></div>
  <div id="tabs-3" name="div1"></div>
  <div id="tabs-4" name="div1"></div>
<!--   <div id="tabs-5" name="div1"></div> -->
<!--   <div id="tabs-6" name="div1"></div> -->
  
</div>

<div id="dialog-1" title="設定" style="display:none">
  
  <input type="text" name="changeNameText">
  <input type="button" value="修改追蹤清單名稱" name="changeName"><br>
  <input type="button" value="刪除追蹤清單" name="deleteListing">
  
</div>

<!-- 匯入需要的檔案 -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>

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
			 draggable: false,
			 modal: true,
			 beforeClose: function( event, ui ) {
				  tlno = "";
				  mId = "";	
				  $('input[name="changeNameText"]').val("");
               },
		});
		
		event.stopPropagation();
		
		//點擊修改清單名稱
		btncn.click(function() {
			
			var tlName = $('input[name="changeNameText"]').val();
			
			$.get("ChangeName?",{"listingName":tlName,"memberId":mId,"listingNo":tlno},function(data) {
				alert(data);
				
				location.reload();

				});
			});
				
				//點擊刪除清單
		btndl.click(function() {
				
			$.get("DeleteListing?",{"listingNo":tlno,"memberId":mId},function(data) {
				alert(data);
				
				location.reload();

				});
			});	
	});
	
	//點擊新增清單
	btncl.click(function() {
		var mId = $('form[id*="createText"]').attr("name");
		var tlName = $('input[name="createListingText"]').val();
			
		$.get("CreateListing?",{"listingName":tlName,"memberId":mId},function(data) {
			alert(data);
			
			location.reload();

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
			
		//查詢新聞		
		$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//				var jsonobj = eval("(" + data + ")");

			$.each(data,function(){

//					var StockNo = this.StockNo;
				var NewsDate = this.NewsDate;	
				var NewsTitle = this.NewsTitle;	
				var NewsWebaddress = this.NewsWebaddress;
				

				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
// 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
// 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
		
				});
		
		});
		
		//查詢月營收			
		$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var RevenueDate = this.RevenueDate;	
//					var Revenue = this.Revenue;	
//					var PostDate = this.PostDate;	
				
				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
// 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
		
				});
		
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
		$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var StatementDate = this.StatementDate;
				var PostDate = this.PostDate;
				var PostTime = this.PostTime;
				
				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
// 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
				
				});
		
		});
		
		//新增列表 + 設置 del
		$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
			$.each(data,function(){
				var ListingNo = this.ListingNo;
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
			tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
					'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
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
				alert(data);
				
				//清空列表
				$('table[id*="table"]').empty();
				$('div[name="div1"]').empty();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//							var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
//							var Revenue = this.Revenue;	
//							var PostDate = this.PostDate;	
						
						$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
				
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
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
						var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
							'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del);
					});
				});
			});
		};
					
		//設置選擇列
		$("#createText"+tlno).append('<input type="text" placeholder="請輸入股號" name="godText" style="width:150px">');	
		$("#createText"+tlno).append('<input type="button" value="追蹤個股" name="createStock" id="' + tlno + '"><br>');
		
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
				alert(data);
				
				//清空列表
				$('table[id*="table"]').empty();
				$('input[name="godText"]').val("");
				$('div[name="div1"]').empty();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//							var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
//							var Revenue = this.Revenue;	
//							var PostDate = this.PostDate;	
						
						$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
				
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
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
						var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
							'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del);
					});
				});
			});	
		});
	});
					
									
//進入頁面時預設展開第一個清單
$(function() {
		
	//設置屬性預設排序 0 放第一個
		var tlno = ${tlNo[0]};
		var mId = ${mId[0]};
		var tab = $("#table" + tlno);
		var cText = $("#createText"+ tlno );
		
		//查詢新聞		
		$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//				var jsonobj = eval("(" + data + ")");

			$.each(data,function(){

//					var StockNo = this.StockNo;
				var NewsDate = this.NewsDate;	
				var NewsTitle = this.NewsTitle;	
				var NewsWebaddress = this.NewsWebaddress;
				

				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
// 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
// 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
		
				});
		
		});
		
		//查詢月營收			
		$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var RevenueDate = this.RevenueDate;	
//					var Revenue = this.Revenue;	
//					var PostDate = this.PostDate;	
				
				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
// 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
		
				});
		
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
		$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
			
			$.each(data,function(){
				
				var StockNo = this.StockNo;
				var StockName = this.StockName;
				var StatementDate = this.StatementDate;
				var PostDate = this.PostDate;
				var PostTime = this.PostTime;
				
				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
// 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
// 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
				
				});
		
		});
		
		//新增列表 + 設置 del
		$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
			$.each(data,function(){
				var ListingNo = this.ListingNo;
				var StockNo = this.StockNo;	
				var StockName = this.StockName;	
			tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
					'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
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
			alert(data);
			
			//清空列表
			$('table[id*="table"]').empty();
			$('div[name="div1"]').empty();
			
			//查詢新聞		
			$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//					var jsonobj = eval("(" + data + ")");

				$.each(data,function(){

//						var StockNo = this.StockNo;
					var NewsDate = this.NewsDate;	
					var NewsTitle = this.NewsTitle;	
					var NewsWebaddress = this.NewsWebaddress;
					

					$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
//	 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
			
					});
			
			});
			
			//查詢月營收			
			$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
				
				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var RevenueDate = this.RevenueDate;	
//						var Revenue = this.Revenue;	
//						var PostDate = this.PostDate;	
					
					$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
//	 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
			
					});
			
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
			$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
				
				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var StatementDate = this.StatementDate;
					var PostDate = this.PostDate;
					var PostTime = this.PostTime;
					
					$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
//	 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//	 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
					
					});
			
			});
			
			//新增列表 + 設置 del
			$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
				$.each(data,function(){
					var ListingNo = this.ListingNo;
					var StockNo = this.StockNo;	
					var StockName = this.StockName;	
				tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
						'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
				});	
				$(del2);
				});
			});
		});
	};
			
				//設置選擇列
		$("#createText"+tlno).append('<input type="text" placeholder="請輸入股號 " name="godText" style="width:150px">');	
		$("#createText"+tlno).append('<input type="button" value="追蹤個股" name="createStock" id="' + tlno + '"><br>');
		
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
				alert(data);
				
				//清空列表
				$('table[id*="table"]').empty();
				$('input[name="godText"]').val("");
				$('div[name="div1"]').empty();
				
				//查詢新聞		
				$.getJSON("SearchNews?",{"ListingNo":tlno},function(data) {

//						var jsonobj = eval("(" + data + ")");

					$.each(data,function(){

//							var StockNo = this.StockNo;
						var NewsDate = this.NewsDate;	
						var NewsTitle = this.NewsTitle;	
						var NewsWebaddress = this.NewsWebaddress;
						

						$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + NewsTitle + '(' + NewsDate + ')' + '</h5></div></a>');				
//		 				$('#tabs-2').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="' + NewsWebaddress + '"Target="_blank">' + NewsTitle + ' (' + NewsDate + ')</a><br>');
				
						});
				
				});
				
				//查詢月營收			
				$.getJSON("SearchMGR?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var RevenueDate = this.RevenueDate;	
//							var Revenue = this.Revenue;	
//							var PostDate = this.PostDate;	
						
						$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收</h5></div></a>');
//		 				$('#tabs-3').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + RevenueDate + '月營收報表</a><br>');
				
						});
				
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
				$.getJSON("SearchFinancialStatements?",{"ListingNo":tlno},function(data) {
					
					$.each(data,function(){
						
						var StockNo = this.StockNo;
						var StockName = this.StockName;
						var StatementDate = this.StatementDate;
						var PostDate = this.PostDate;
						var PostTime = this.PostTime;
						
						$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank" style="text-decoration:none;"><div style="border:2px #d0d0d0 solid ; padding:10px ; margin:1px 0px"><h5>' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</h5></div></a>');
//		 				$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
//		 				$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
						
						});
				
				});
				
				//新增列表 + 設置 del
				$.getJSON("GetListingDetails?",{"ld":tlno},function(data) {
					$.each(data,function(){
						var ListingNo = this.ListingNo;
						var StockNo = this.StockNo;	
						var StockName = this.StockName;	
					tab.append('<tr><td>' + StockNo + '</td><td><a href="https://www.google.com.tw/" Target="_blank">' + StockName + 
							'</a></td><td><input type="button" value="取消追蹤" name="' + ListingNo + '" id="' + StockNo + '"></td></tr>');
					});	
					$(del2);
					});
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