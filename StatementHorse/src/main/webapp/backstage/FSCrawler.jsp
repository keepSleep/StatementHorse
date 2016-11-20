<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
	$(document).ready(
			function() {
				$("#example-1").dataTable({
					"aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"流動資產"},
					             {"sTitle":"非流動資產"},{"sTitle":"總資產"},
					             {"sTitle":"流動負債"},{"sTitle":"非流動負債"},
					             {"sTitle":"總負債"},{"sTitle":"股本"},
					             {"sTitle":"資本公積"},{"sTitle":"保留盈餘"},
					             {"sTitle":"其他權益"},{"sTitle":"歸屬母公司業主之權益"},
					             {"sTitle":"權益總計(資產-負債)"}],
					"bDestroy" : true,
					aLengthMenu: [
						[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
					]
				});
				
				$("#example-2").dataTable({
					"aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"營業收入"}
								,{"sTitle":"營業成本"},{"sTitle":"營業毛利"},{"sTitle":"營業毛利淨額"},
								{"sTitle":"營業費用"},{"sTitle":"營業利益"},{"sTitle":"業外收入及支出"}
								,{"sTitle":"稅前淨利"},{"sTitle":"所得稅費用"},{"sTitle":"繼續營業單位本期淨利"},
								{"sTitle":"本期淨利"},{"sTitle":"其他綜合損益"},{"sTitle":"本期綜合損益"}
								,{"sTitle":"基本每股盈餘"}],
					"bDestroy" : true,
					aLengthMenu: [
						[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
					]
				});
				
				
				
				
				$("#gobutton").click(function(){
// 				$.get("BackStageMgr",{"action":"financialstatements","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"season":$("#season").val()},function(){
// 					console.log("財報更新完成");				
// 				})	
				$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
					console.log(data)
					//此為資產負債表點擊後產生的狀態
					var opt={
						   "bJQueryUI":true,
						   "aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"流動資產"},
							             {"sTitle":"非流動資產"},{"sTitle":"總資產"},
							             {"sTitle":"流動負債"},{"sTitle":"非流動負債"},
							             {"sTitle":"總負債"},{"sTitle":"股本"},
							             {"sTitle":"資本公積"},{"sTitle":"保留盈餘"},
							             {"sTitle":"其他權益"},{"sTitle":"歸屬母公司業主之權益"},
							             {"sTitle":"權益總計(資產-負債)"}],
						   "aaData": data,
						   "bDestroy" : true,
						   aLengthMenu: [
							[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
								]
						 };         
					table=$("#example-1").dataTable(opt);		
					
				
				})
				$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為損益表失焦後產生的狀態
						var opt={
							   "bJQueryUI":true,
							   "aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"營業收入"}
								,{"sTitle":"營業成本"},{"sTitle":"營業毛利"},{"sTitle":"營業毛利淨額"},
								{"sTitle":"營業費用"},{"sTitle":"營業利益"},{"sTitle":"業外收入及支出"}
								,{"sTitle":"稅前淨利"},{"sTitle":"所得稅費用"},{"sTitle":"繼續營業單位本期淨利"},
								{"sTitle":"本期淨利"},{"sTitle":"其他綜合損益"},{"sTitle":"本期綜合損益"}
								,{"sTitle":"基本每股盈餘"}],
								"aaData": data,
								"bDestroy" : true,
							   aLengthMenu: [
								[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
									]
							 };         
						table=$("#example-2").dataTable(opt);		
			
				})
				})
				
				
				$("#stockNo").blur(function(){
					$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為資產負債表失焦後產生的狀態
						var opt={
						   "bJQueryUI":true,
						   "aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"流動資產"},
							             {"sTitle":"非流動資產"},{"sTitle":"總資產"},
							             {"sTitle":"流動負債"},{"sTitle":"非流動負債"},
							             {"sTitle":"總負債"},{"sTitle":"股本"},
							             {"sTitle":"資本公積"},{"sTitle":"保留盈餘"},
							             {"sTitle":"其他權益"},{"sTitle":"歸屬母公司業主之權益"},
							             {"sTitle":"權益總計(資產-負債)"}],
						   "aaData": data,
						   "bDestroy" : true,
						   aLengthMenu: [
							[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
								]
						 };         
					table=$("#example-1").dataTable(opt);		
					})
				
					$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為損益表失焦後產生的狀態
						var opt={
							   "bJQueryUI":true,
							   "aoColumns":[{"sTitle":"股號"},{"sTitle":"季度"},{"sTitle":"營業收入"}
								,{"sTitle":"營業成本"},{"sTitle":"營業毛利"},{"sTitle":"營業毛利淨額"},
								{"sTitle":"營業費用"},{"sTitle":"營業利益"},{"sTitle":"業外收入及支出"}
								,{"sTitle":"稅前淨利"},{"sTitle":"所得稅費用"},{"sTitle":"繼續營業單位本期淨利"},
								{"sTitle":"本期淨利"},{"sTitle":"其他綜合損益"},{"sTitle":"本期綜合損益"}
								,{"sTitle":"基本每股盈餘"}],
								"aaData": data,
								"bDestroy" : true,
							   aLengthMenu: [
								[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
									]
							 };         
						table=$("#example-2").dataTable(opt);
					
					
					})	
				})
			})
	</script>

<div class="panel panel-default">
	<div class="panel-heading">
			<div class="h1 text-info text-bold">新增財務報表</div>
				<div class="row">
						<div class="col-sm-1">
							<input type="text" class="form-control" id="stockNo" placeholder="stockNo" maxlength="4">
						</div>
						<div class="col-sm-1">
							<input type="text" class="form-control" id="yyyy" placeholder="yyyy" maxlength="4">
						</div>
						<div class="col-sm-1">
							<input type="text" class="form-control" id="season" placeholder="season" maxlength="1">
						</div>
						<button class="btn btn-info" id="gobutton">Go</button>
				</div>
			</div>
			<div class="panel-body">
					
					
					<div class="h2 text-info">資產負債表</div>
					<table id="example-1" class="table table-striped table-bordered" cellspacing="0" width="100%">
					</table>
					
					<div class="h2 text-info">損益表</div>
					<table id="example-2" class="table table-striped table-bordered" cellspacing="0" width="100%">
					</table>
				</div>
			</div>



