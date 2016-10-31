<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!--              設定樣式                                  -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="../css/jquery-ui.css"> -->

<!--              設定外掛                                  -->
<script src="../js/jquery.min.js"></script>
<!-- <script src="../js/jquery-ui.min.js"></script> -->
<script src="../js/bootstrap.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<script>
	$(function() {
		var colNo = 0;
		var start = true;
		//會計科目清單click事件
		$("dd").click(
				function() {
					var dd = $(this);
					start = false;
					$('td[name="tr"]').remove();
					if (dd.attr("flag") == 'true') {
						dd.attr("flag", 'false');
						$('td[name="' + dd.attr('name') + '"]').remove();
						colNo--;
					} else {
						dd.attr("flag", 'true');
						$('thead td:last-child').after(
								"<td name='" + dd.attr('name') + "'>"
										+ dd.text() + "</td>");
						$('tbody td:last-child').after(
								"<td name='" + dd.attr('name') + "'></td>");
						colNo++;
					}
				});
		//刪除個股整行click事件
		$('#simpleTable').on('click', '.btn-danger', function() {
			$(this).parents('tr').remove();
		})
		//增加個股行click事件
		$('#buttonAdd')
				.click(
						function() {
							if (start) {
								$('tbody')
										.append(
												"<tr><td><input type='text'/><a href='#' class='btn btn-danger'>刪除</a></td><td name='tr'></td></tr>");
							} else {
								var tds;
								$('thead td:gt(0)').each(
										function() {
											tds += "<td name='"
													+ $(this).attr("name")
													+ "'></td>";
										})
								$('tbody').append(
										"<tr><td><input type='text'/><a href='#' class='btn btn-danger'>刪除</a></td>"
												+ tds + "</tr>");
							}
						});
		//清空整個頁面
		$('#clear').click(function() {
			$('#simpleTable').empty().prepend('<thead><tr><td>股票號碼</td><td name="tr">+增加會計科目</td></tr></thead><tbody><tr><td><input type="text"/><a href="#" class="btn btn-danger">刪除</a></td><td name="tr"></td></tr></tbody>')
			colNo = 0;
			start = true;	
		})
	});
</script>
</head>
<body>
	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-md-3" id="accordion">
					<div class="panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h2 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne" aria-expanded="true"
										aria-controls="collapseOne"> 資產負債表 </a>
								</h2>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<dl>
										<dd name="currentAssets">流動資產</dd>
										<dd name="fixedAssets">非流動資產</dd>
										<dd name="assets">總資產</dd>
										<dd name="currentLiabilities">流動負債</dd>
										<dd name="longTermLiabilities">非流動負債</dd>
										<dd name="liabilities">總負債</dd>
										<dd name="capitalStock">股本</dd>
										<dd name="additionalPaidInCapital">資本公積</dd>
										<dd name="retainedEarnings">保留盈餘</dd>
										<dd name="otherEquity">其他權益</dd>
										<dd
											name="consolidatedNetIncomeAttributedToStockholdersOfTheCompany">歸屬母公司業主之權益</dd>
										<dd name="totalEquity">權益總計(資產-負債)</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h2 class="panel-title">
									<a class="collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="true" aria-controls="collapseTwo"> 損益表 </a>
								</h2>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
									<dl>
										<dd name="operatingRevenue">營業收入</dd>
										<dd name="operatingCost">營業成本</dd>
										<dd name="operatingMargain">營業毛利</dd>
										<dd name="netOperatingMargain">營業毛利淨額</dd>
										<dd name="operatingExpenses">營業費用</dd>
										<dd name="operatingIncome">營業利益</dd>
										<dd name="nonOperatingRevenue">業外收入及支出</dd>
										<dd name="oibt">稅前淨利</dd>
										<dd name="incomeTaxExpense">所得稅費用</dd>
										<dd name="coiat">繼續營業單位本期淨利</dd>
										<dd name="netIncome">本期淨利</dd>
										<dd name="oci">其他綜合損益</dd>
										<dd name="currentProfitAndLoss">本期綜合損益</dd>
										<dd name="earningPerShare">基本每股盈餘</dd>
									</dl>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<!--查詢表格 -->
					<div>
						<input type="button" value="add row" id="buttonAdd"
							class="btn btn-success">
							
<!-- 							清空開始 -->
						<input type="button" value="clear" id="clear"
							class="btn btn-success">
<!-- 							清空結束 -->

						<div id="msg"></div>
						<div class="table table-responsive">
							<table id="simpleTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<td>股票號碼</td>
										<td name="tr">+增加會計科目</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" /><a href='#'
											class='btn btn-danger'>刪除</a></td>
										<td name="tr"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>