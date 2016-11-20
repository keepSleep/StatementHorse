<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
	$(document).ready(
			function() {
				$("#gobutton").click(function(){
					alert("go");
// 				$.get("BackStageMgr",{"action":"financialstatements","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"season":$("#season").val()},function(){
// 					console.log("財報更新完成");				
// 				})	
// 				$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
// 					console.log(data)
					//此為資產負債表點擊後產生的狀態
					//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					//此處因為line上說不demo 所以複製下面失焦狀態就好
					
					
					
					
					
					
					
				
// 				})
// 				$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
// 						console.log(data)
						//此為損益表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					
						//此處因為line上說不demo 所以複製下面失焦狀態就好
						
						
						
						
						
					
					
// 				})
				})
				$("#stockNo").blur(function(){
// 					$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
// 						console.log(data)
						//此為資產負債表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
						
					alert($(this).val())	
						
						
						
						
						
		
// 					})
				
// 					$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
// 						console.log(data)
						//此為損益表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					
						
						
						
						
						
						
					
					
// 					})
					
					
				})
				//載入頁面時查詢財報資料並輸出到表格中
				var BS;
				for(e=0;e<10;e++){
					BS += "<tr>"
				for(i=0;i<14;i++){
					BS += "<td>" + i + "</td>"
				}
					BS += "</tr>"
				}
				$("#BSbody").append(BS);
				
				var IS;
				for(e=0;e<20;e++){
					IS += "<tr>"
				for(i=0;i<16;i++){
					IS += "<td>" + i + "</td>"
				}
					IS += "</tr>"
				}
				$("#ISbody").append(IS);
				
	
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
					
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-1").dataTable({
							aLengthMenu: [
								[5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
							]
						});
						$("#example-2").dataTable({
							aLengthMenu: [
								[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
							]
						});
						
					});
					</script>
					
					<div class="h2 text-info">資產負債表</div>
					<table id="example-1" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>股號</th>
								<th>季度</th>
								<th>流動資產</th>
								<th>非流動資產</th>
								<th>總資產</th>
								<th>流動負債</th>
								<th>非流動負債</th>
								<th>總負債</th>
								<th>股本</th>
								<th>資本公積</th>
								<th>保留盈餘</th>
								<th>其他權益</th>
								<th>歸屬母公司業主之權益</th>
								<th>權益總計(資產-負債)</th>
							</tr>
						</thead>
					
						<tfoot>
							<tr>
								<th>股號</th>
								<th>季度</th>
								<th>流動資產</th>
								<th>非流動資產</th>
								<th>總資產</th>
								<th>流動負債</th>
								<th>非流動負債</th>
								<th>總負債</th>
								<th>股本</th>
								<th>資本公積</th>
								<th>保留盈餘</th>
								<th>其他權益</th>
								<th>歸屬母公司業主之權益</th>
								<th>權益總計(資產-負債)</th>
							</tr>
						</tfoot>
					
						<tbody id="BSbody"></tbody>
					</table>
					
					<div class="h2 text-info">損益表</div>
					<table id="example-2" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>股號</th>
								<th>季度</th>
								<th>營業收入</th>
								<th>營業成本</th>
								<th>營業毛利</th>
								<th>營業毛利淨額</th>
								<th>營業費用</th>
								<th>營業利益</th>
								<th>業外收入及支出</th>
								<th>稅前淨利</th>
								<th>所得稅費用</th>
								<th>繼續營業單位本期淨利</th>
								<th>本期淨利</th>
								<th>其他綜合損益</th>
								<th>本期綜合損益</th>
								<th>基本每股盈餘</th>
							</tr>
						</thead>
					
						<tfoot>
							<tr>
								<th>股號</th>
								<th>季度</th>
								<th>營業收入</th>
								<th>營業成本</th>
								<th>營業毛利</th>
								<th>營業毛利淨額</th>
								<th>營業費用</th>
								<th>營業利益</th>
								<th>業外收入及支出</th>
								<th>稅前淨利</th>
								<th>所得稅費用</th>
								<th>繼續營業單位本期淨利</th>
								<th>本期淨利</th>
								<th>其他綜合損益</th>
								<th>本期綜合損益</th>
								<th>基本每股盈餘</th>
							</tr>
						</tfoot>
					
						<tbody id="ISbody"></tbody>
					</table>
				</div>
			</div>



