<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" charset="utf-8">
<title>jQuery UI 日期选择器（Datepicker） - 限制日期范围</title>
<!--   <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script> -->
<!--   <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script> -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate : -20,
			maxDate : 0
		});
	});
</script>

</head>
<body>

	<form>
	<p>
		日期：<input type="text" name="date" id="datepicker">
	</p>
	<p id="show"></p>
	<input type ="button" id="show1" value="按按看">
	<input type= "submit" value="確認">
	</form>
	
	
	<script>
	//更換text中的日期格式
	$( "#datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
	
// 	$("#datepicker").change(function(){
// 		console.log($("#datepicker").datepicker("getDate"))
// 	})
	var str1 = $('#datepicker').datepicker("getDate");
	var str = new Date(str1);
	
	var dateFormat = require('dateformat');
	str = dateFormat(str,'yyyy-mm-dd')
	
	$('#show1').click(function(){
		$('#show').append(str);
	});
	
	</script>

</body>
</html>