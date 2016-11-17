<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


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
<!-- <div class="col-sm-1"> -->
<!-- 	<input type="text" class="form-control" id="dd" placeholder="dd" maxlength="2"> -->
<!-- </div> -->
<button class="btn btn-info" id="gobutton">Go</button>
</div>

<script>
	$(document).ready(
			function() {
				$("#gobutton").click(function(){
					//營收的jquery
// 				$.get("BackStageMgr",{"action":"mgr","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"month":$("#MM").val()},function(){
// 				console.log("營收更新完成");
// 				})
				$.get("BackStageMgr",{"action":"financialstatements","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"season":$("#season").val()},function(){
					console.log("財報更新完成");
				})	
				
				})
			
	
			}
	)
	</script>
<div class="h1 text-info text-bold">
表格

</div>

