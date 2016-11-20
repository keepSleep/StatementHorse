<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
	$(document).ready(
			function() {
				$("#gobutton").click(function(){
					//營收的jquery
// 				$.getJSON("BackStageMgr",{"action":"mgr","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"month":$("#month").val()},function(){
// 				console.log("營收更新完成");
// 				})
// 				$.getJSON("BackStageMgr",{"action":"mgrdemoclick","stockno":$("#stockNo").val()},function(data){
// 						console.log(data)
						//此為營收點擊後後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,201610.... ],[1476,201608.....  ]   ] 用$.each(data)可取出
						
						
						
						
						
						
						
// 				})
				})
				$("#stockNo").blur(function(){
// 					$.getJSON("BackStageMgr",{"action":"mgrdemoblur","stockno":$("#stockNo").val()},function(data){
// 						console.log(data)
						//此為營收失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,201610.... ],[1476,201608.....  ]   ] 用$.each(data)可取出
						
						
						
						
						
						
						
						
						
						
						
// 					})
				})
				var BS;
				for(e=0;e<10;e++){
					BS += "<tr>"
				for(i=0;i<4;i++){
					BS += "<td>" + i + "</td>"
				}
					BS += "</tr>"
				}
				$("#MGRbody").append(BS);
			}
	)
	</script>
<div class="panel panel-default">
	<div class="panel-heading">
			<div class="h1 text-info text-bold">新增月營收表</div>
				<div class="row">
						<div class="col-sm-1">
							<input type="text" class="form-control" id="stockNo" placeholder="stockNo" maxlength="4">
						</div>
						<div class="col-sm-1">
							<input type="text" class="form-control" id="yyyy" placeholder="yyyy" maxlength="4">
						</div>
						<div class="col-sm-1">
							<input type="text" class="form-control" id="month" placeholder="month" maxlength="2">
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
					});
					</script>
					
					<div class="h2 text-info">月營收表</div>
					<table id="example-1" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>股號</th>
								<th>年月份</th>
								<th>營收</th>
								<th>公布日期</th>
							</tr>
						</thead>
					
						<tfoot>
							<tr>
								<th>股號</th>
								<th>年月份</th>
								<th>營收</th>
								<th>公布日期</th>
							</tr>
						</tfoot>
					
						<tbody id="MGRbody"></tbody>
					</table>
				</div>
			</div>
