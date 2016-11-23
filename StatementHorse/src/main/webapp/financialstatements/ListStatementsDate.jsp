<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--Bootstrap樣式-->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.min.css">
	<!-- 林文的TOP -->
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StatementHorse</title>
<!--JQuery/JQueryUI/Bootstrap JS-->
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
<!-- <script -->
<%-- 	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script> --%>
	<%@include file="/top/Top.jsp"%>
<script
	src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js"></script>

</head>



<body>

</p>
	
	<div class="container-fluid">
		<div class="row-fluid">
			
			<div class="col-md-1">
			</div>
			
			<div class="col-md-3">
			
			<div class="panel-heading" role="tab" id="headingOne"
					style="background-color: lightblue">
					<h2 class="panel-title">選擇日期</h2>
				</div>
			
			<div class="panel-body">		
			<form method="post" action="financialstatements.do">
			
				<div class="input-group date" data-date-end-date="0d">
					<input type="text" class="form-control" id = "datepicker" name="date"> <span
						class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						
					<input type="hidden" name="action" value="financialstatements">
				</div>
			</form>
			</div>
		</div>
			
			<div class="col-md-7">
				<!--查詢表格 -->

				<div class="row-fluid">
					<div class="table table-responsive ">
						<p />
						<table id="simpleTable" class="table table-bordered">
							<thead id="Fhead">
								<tr>
									<td class="col-md-1"></td>
									<td class="col-md-3">股票號碼</td>
									<td class="col-md-3">股票名稱</td>
									<td class="col-md-3">公布日期</td>
									<td class="col-md-3">公布時間</td>
								</tr>
								
							</thead>
							<tbody id="Fbody">

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>	
		
</body>

<script>
$('.input-group.date').datepicker({
	
format:"yyyy-mm-dd",
language : "zh-CN",
daysOfWeekDisabled : "0,6",
autoclose : true,
toggleActive : true
});

$('#datepicker').change(function(){
// 	alert($(this).val())
	$('#Fbody').empty();
	
	$.getJSON("financialstatements.do",{"action":"financialstatements","date":$(this).val()},function(data){
		$.each(data,function(key){
 			$('#Fbody').append("<tr border=1><td></td><td>"+data[key][0]+"</td><td>"+data[key][1]+"</td><td>"+data[key][2].substring(0,10)+"</td><td>"+data[key][3]+"</td></tr>")
			
// 			高明寫法 by 葉哲
// 			$('#Fbody').append("<tr border=1><td></td></tr>")
// 			$.each(cc,function(aa,value){
// 				$('#Fbody tr:eq('+key+')').append("<td>"+value+"</td>")
// 			})
			
		})
		
	})
});
</script>

</html>