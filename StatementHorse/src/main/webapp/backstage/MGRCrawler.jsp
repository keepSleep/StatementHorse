<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
	$(document).ready(
			function() {
				var table=$("#example-1").dataTable({
					"aoColumns":[{"sTitle":"股號"},{"sTitle":"年月份"},{"sTitle":"營收"},{"sTitle":"公布日期"}],
					"bDestroy" : true,
					aLengthMenu: [
						[10, 15, 20, 25, -1], [10, 15, 20, 25, "All"]
					]
				});
				
				$("#gobutton").click(function(){
					if($("#stockNo").val().trim()!=""&&$("#yyyy").val().trim()!=""&&$("#month").val().trim()!=""){
					$("#picture").html("<img src='${pageContext.servletContext.contextPath}/picture/ring.gif'>")
					//營收的jquery	
				$.getJSON("BackStageMgr",{"action":"mgr","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"month":$("#month").val()},function(){
		
					$.getJSON("BackStageMgr",{"action":"mgrdemoclick","stockno":$("#stockNo").val()},function(data){
						console.log(data)
		
						var opt={
								   "bJQueryUI":true,
								   "aoColumns":[{"sTitle":"股號"},{"sTitle":"年月份"},{"sTitle":"營收"},{"sTitle":"公布日期"}],
								   "aaData": data,
								   "bDestroy" : true,
								   aLengthMenu: [
									[10, 15, 20, 25, -1], [10, 15, 20, 25, "All"]
										]
								 };         
						table=$("#example-1").dataTable(opt);
						$("#picture>img").remove();
						})
					})
				

				}else{
					$("#picture").empty();
					if($("#stockNo").val().trim()==""){	
					$("#picture").append("<h3 style='color:red'>股號不得為空值</h3>")
					}
					if($("#yyyy").val().trim()==""){	
						$("#picture").append("<h3 style='color:red'>年份不得為空值</h3>")
						}
					if($("#month").val().trim()==""){	
						$("#picture").append("<h3 style='color:red'>月份不得為空值</h3>")
						}
					
				}
				})
				
				$("#stockNo").blur(function(){
					$.getJSON("BackStageMgr",{"action":"mgrdemoblur","stockno":$("#stockNo").val()},function(data){
						
						console.log(data)
							var opt={
							    "bJQueryUI":true,
							    "aoColumns":[{"sTitle":"股號"},{"sTitle":"年月份"},{"sTitle":"營收"},{"sTitle":"公布日期"}],
							    "aaData": data,
							    "bDestroy" : true,
							    aLengthMenu: [
									[10, 15, 20, 25, -1], [10, 15, 20, 25, "All"]
											]
							        };         
							table=$("#example-1").dataTable(opt);			
						
						
						
						
						
					})
				})
				
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
						<div  id="picture"></div>
				</div>
			</div>
			<div class="panel-body" id="firstchild" >
				
					
					<div class="h2 text-info" >月營收表</div>
					<table id="example-1" class="table table-striped table-bordered" cellspacing="0" width="100%">			
					</table>
				</div>
			</div>
