<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>StatementHorse</title>
</head>
<body>
  <c:forEach begin="0" end="${tlSize }" var="num" >
  
  <div value="${tlName[num]}" name="show" id="${tlNo[num]}">
  
 <h3 style="display:inline;">${tlName[num]}</h3>
<div>
<div id="tabs-4"></div>
</div>

<script src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>

<script type="text/javascript">
	$.getJSON("SearchFinancialStatements",{"ListingNo":tlno},function(data) {
				
				$.each(data,function(){
					
					var StockNo = this.StockNo;
					var StockName = this.StockName;
					var StatementDate = this.StatementDate;
					var PostDate = this.PostDate;
					var PostTime = this.PostTime;
					
					$('#tabs-4').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');
					$('#tabs-1').append( '<a href="https://www.google.com.tw/" Target="_blank">' + StockNo + ' ' + StockName + ' ' + StatementDate + ' 季財務報表 (' + PostDate + "," + PostTime + ')</a><br>');

					});
			
			});
</script>
</body>
</html>