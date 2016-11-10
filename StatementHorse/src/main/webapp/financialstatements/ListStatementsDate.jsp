<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!--              設定樣式                                  -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="../css/jquery-ui.css"> -->

<!--              設定外掛                                  -->
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">


<script src="../js/jquery-ui.min.js"/>
<script>

// var old = $.fn.datepicker

// $.fn.datepicker = function (option) {
//   	  }


// $.fn.datepicker.noConflict = function () {
//   $.fn.datepicker = old
//   return this
// }
	$(function() {
		$('#datepicker').datepicker({
			minDate : -20,
			maxDate : 0
		});
	});
	
	
	
	
</script>
</head>
<body>
	<%@include file="/top/Top.jsp"%>
	</p>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-1"></div>
			<div class="col-md-3"">
				<div class="panel-heading" role="tab" id="headingOne"
					style="background-color: lightblue">
					<h2 class="panel-title">選擇日期</h2>
				</div>
				<div class="panel-body">
<!-- 					<form method="post" action="financialstatements.do"> -->
						<p>
							日期：<input type="text" name="date" id="datepicker">
						</p>
						<input type="submit" value="確認"> <input type="hidden"
							name="action" value="financialstatements">
<!-- 					</form> -->
					
					<script>
						//更換text中的日期格式
						$("#datepicker").datepicker({
							dateFormat : 'yy-mm-dd'
						});

						$("#datepicker").change(function() {
							console.log($("#datepicker").datepicker("getDate"))
						});
					</script>
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
</body>
</html>