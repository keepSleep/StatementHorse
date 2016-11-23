<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StatementHorse</title>
</head>
<body>

<form method="post" action = "balanceSheet.do">

請輸入季度(格式10X0X)<BR>
<input type="text" name="statementdate"><br>

股號<input type="text" name ="stockno1"><br>
股號<input type="text" name="stockno2"><br>
	<input type="submit" value ="確認">
	<input type="hidden" name ="action" value="select">
</form>

<form method="post" action="balanceSheet.do">
<input type="checkbox" name="BSaccounting1" id="current_assets">流動資產<br>
<input type="checkbox" name="BSaccounting2" id="fixed_assets" value="accountings">非流動資產<br>
<input type="hidden" name ="action" value="accountings">
<input type="submit" value ="確認">
</form>


</body>
</html>