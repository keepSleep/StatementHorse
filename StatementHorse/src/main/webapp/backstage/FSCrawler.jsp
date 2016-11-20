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

				$.get("BackStageMgr",{"action":"financialstatements","stockno":$("#stockNo").val(),"year":$("#yyyy").val(),"season":$("#season").val()},function(){
					console.log("財報更新完成");				
				})	
				$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
					console.log(data)
					//此為資產負債表點擊後產生的狀態
					//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					//此處因為line上說不demo 所以複製下面失焦狀態就好
					
					
					
					
					
					
					
				
				})
				$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為損益表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					
						//此處因為line上說不demo 所以複製下面失焦狀態就好
						
						
						
						
						
					
					
				})
				})
				$("#stockNo").blur(function(){
					$.getJSON("BackStageMgr",{"action":"balancesheet","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為資產負債表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
						
						
						
						
						
						
						
		
					})
				
					$.getJSON("BackStageMgr",{"action":"incomestatement","stockno":$("#stockNo").val()},function(data){
						console.log(data)
						//此為損益表失焦後產生的狀態
						//葉哲 data我擺在console 格式就是[ [1476,10503.... ],[1476,10502.....  ]   ] 用$.each(data)可取出
					
					
					
						
						
						
						
						
						
					
					
					})
					
					
				})
				
				
	
			})
	</script>
<div class="h1 text-info text-bold">
表格

</div>

