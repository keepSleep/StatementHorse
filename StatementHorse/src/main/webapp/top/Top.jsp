<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/metisMenu.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/sb-admin-2.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/MyCSS.css">

<script
	src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<style>
.modal-header, h4, .close {
	background-color: #5cb85c;
	color: white !important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}

table {
	font-family: 微軟正黑體;
}
</style>

	<nav class="navbar navbar-default navbar-static-top"
		role="navigation" style="background-color: lightblue">
<!-- <div class=container-fluid> -->
		<div class="navbar-header">
			<!-- 功能巡覽列按鈕 開始 -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar" style="background-color: lightblue">
				<span class="sr-only">Toggle navigation</span>
				<!-- 三條線 開始 -->
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				<!-- 三條線 結束 -->
			</button>
			<a class="navbar-link"
				href="${pageContext.servletContext.contextPath}/MessageIndex.jsp">
				<img
				src="${pageContext.servletContext.contextPath}/picture/statementhourse.png"
				class="img-circle" width=200 style="padding: 5px;">
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a
					href="${pageContext.servletContext.contextPath}/ShowStockServlet?action=stock&stock_no=2330&member_id=Blastoise">
						<button type="button"
							class="btn btn-default btn-outline btn-info btn-lg"
							style="border: 0px blue none">個股資料</button>
				</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/financialstatements/ListStatementsDate.jsp"><button
							type="button" class="btn btn-outline btn-info btn-lg"
							style="border: 0px blue none">財報日期</button></a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/financialstatements/FinancialContrastList.jsp"><button
							type="button" class="btn btn-outline btn-info btn-lg"
							style="border: 0px blue none">財報比較</button></a></li>
			</ul>
			
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
			
					<input type="text" style="width: 400px;margin-top:12px"
						class="form-control" placeholder="輸入股號或股名...">
					<button class="btn btn-default" type="button" style="height:140%;margin-top:12px">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</form>





			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.user}">
					<li>
						<a
					href="${pageContext.servletContext.contextPath}/account/login"><button type="button" class="btn btn-outline btn-warning btn-lg"
							style="border: 0px blue none" id="login">
							<i class="fa fa-user fa-fw"></i>登入
						</button></a>
					</li>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
					<li>
						<button type="button" class="btn btn-outline btn-warning btn-lg"
							style="border: 0px blue none;margin-top:11px" id="logout">
							<i class="fa fa-user fa-fw"></i>${sessionScope.user.memberId}</button>
					</li>
				</c:if>

								<c:if test="${!empty sessionScope.user}">
				<li>
					<ul class="nav navbar-top-links navbar-right ">
						<li class="dropdown " style="margin-top:12px"><a id="tg"
							class="dropdown-toggle btn-lg " data-toggle="dropdown" href=""
							style="width: 65px"> <i class="fa fa-bell "></i> <i
								class="fa fa-caret-down"></i>
						</a>
							<ul class="dropdown-menu dropdown-messages">
								<li>
									<div class="chat-panel panel panel-default">
										<!-- /.panel-heading -->
										<div class="panel-body">
											<ul class="chat" id="newmessage">
											</ul>
										</div>
										<div style="display: none" id="messagelength"></div>
										<!-- /.panel-footer -->
									</div>
								</li>
								<li><a class="text-center" href="#"> <strong>Read
											All Messages</strong> <i class="fa fa-angle-right"></i>
								</a></li>
							</ul></li>
					</ul>
				</li>
				<li>
					<ul class="nav navbar-top-links navbar-right " style="margin-top:14px">
						<li class="dropdown" id="allsetting"><a
							class="dropdown-toggle btn-lg" data-toggle="dropdown" href=""
							style="width: 65px"> <i class="fa fa-gear fa-fw"></i><i
								class="fa fa-caret-down"></i>
						</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a
									href="${pageContext.servletContext.contextPath}/changepassword/changepassword.jsp"
									style="cursor: pointer"><i class="fa fa-user fa-fw"></i>更改密碼</a>
								</li>
								<li><a id="setting" style="cursor: pointer"><i
										class="fa fa-gear fa-fw"></i>通知設定</a></li>
								<li class="divider"></li>
								<li><a
									href="${pageContext.servletContext.contextPath}/account/logout"><i
										class="fa fa-sign-out fa-fw"></i> Logout</a></li>
							</ul></li>
					</ul>
				</li>
								</c:if>
			</ul>
</nav>
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="padding: 35px 50px;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>
								<i class="fa fa-gear"></i>通知設定
							</h4>
						</div>
						<div class="modal-body" style="padding: 25px 50px;">

							<!--           <form method="post" role="form" action="MsgIUDServlet"> -->
							<table class="table table-condensed">
								<caption>個人通知設定</caption>
								<thead>
									<tr>
										<th>股號</th>
										<th>月營收通知</th>
										<th>財報更新通知</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty memberlistno}">
										<tr>
											<td colspan="3"><h3>
													您目前沒有追蹤股號，請至<a href="">追蹤清單</a>新增
												</h3></td>
										</tr>
									</c:if>
									<c:if test="${!empty memberlistno}">
										<c:forEach var="stock" items="${memberlistno}"
											varStatus="count">
											<tr>
												<td>${stock[0]}</td>
												<td><input type="checkbox" id="${stock[0]}1"
													name="${stock[0]}1" ${(stock[1]==true)? 'checked' : ''} /></td>
												<td><input type="checkbox" id="${stock[0]}2"
													name="${stock[0]}2" ${(stock[2]==true)? 'checked' : ''} /></td>
											</tr>
											<div style="display: none" id="${count.count}">${stock[0]}</div>
											<div style="display: none" id="${count.last}">${count.count}</div>
										</c:forEach>
									</c:if>
									<tr>
										<td colspan="3" align="center"><div
												style="display: none; margin: 0px auto" id="followok">
												<h3>你已更新成功!!!</h3>
											</div></td>
									</tr>
								</tbody>
							</table>
							<c:if test="${!empty memberlistno}">
								<button type="button" class="btn btn-success btn-block"
									style="border: 0px;" id="updatemessage">
									<span class="glyphicon glyphicon-floppy-disk"></span> 完成更新
								</button>
							</c:if>
							<c:if test="${empty memberlistno}">
								<button type="button" class="btn btn-success btn-block"
									style="border: 0px;" id="updatemessage" disabled="disabled">
									<span class="glyphicon glyphicon-floppy-disk"></span> 完成更新
								</button>
							</c:if>
							<!--           </form> -->

						</div>
					</div>

				</div>
			</div>
<!-- </div> -->
	


<script>
	$(document).ready(
			function() {
				var last = $("#true").text();
				// 		setInterval("refreshnews()",5000);
				$("#tg").click(function() {
					$("#tg").attr("style", "color:#337ab7;width:65px")
				})
				// 		for (var i=1; i<=last; i++) {
				// 			var stockno=$("#"+i).text();
				// 			stockcheck1+=$("#"+i).text()+","+$("#"+stockno+"1").prop("checked")+","+$("#"+stockno+"2").prop("checked")+";"
				// 		}
				$("#allsetting").click(function() {
					$("#followok").attr("style", "display:none")
				})

				$("#setting").click(function() {
					$("#myModal").modal();
				});

				$("#updatemessage").click(
						function() {
							$("#followok").attr("style", "color:red")
							var stockcheck1 = "";
							for (var i = 1; i <= last; i++) {
								var stockno = $("#" + i).text();
								stockcheck1 += $("#" + i).text()
										+ ","
										+ $("#" + stockno + "1")
												.prop("checked")
										+ ","
										+ $("#" + stockno + "2")
												.prop("checked") + ";"
							}
							console.log(stockcheck1)
							$.get("MsgIUDServlet", {
								"action" : "jquery_check",
								"stockcheck1" : stockcheck1
							}, function(data) {
								console.log(data)

								var data1 = data.split("]");

								// 	    			console.log(data1)
								for (var i = 0; i < (data1.length - 2); i++) {
									var data2 = data1[i].split(",");
									console.log(data2)

									if (i == 0) {
										var stock = data2[0].substring(2);

										var check1 = data2[1].substring(1)

										var check2 = data2[2].substring(1)

									} else {
										var stock = data2[1].substring(2);
										console.log(stock)
										var check1 = data2[2].substring(1)
										console.log(check1)
										var check2 = data2[3].substring(1)
										console.log(check2)
									}
									//console.log(main)
									// 	    			main[i+]

									// 	    			$("#"+main[i*3]+"1").prop("checked",main[(1+i*3)])
									// 	    			$("#"+main[i*3]+"2").prop("checked",main[(2+i*3)])
									// 	    			console.log($("#"+main[i*3]+"2").prop("checked",main[(2+i*3)]))
								}

							})

						})

			});
	function refreshnews() {
		$
				.getJSON(
						"GetNewsMsgServlet",
						function(data) {
							console.log(data)
							$("#newmessage").empty();
							if ($("#messagelength").val() != data.length) {
								$("#tg").attr("style",
										"color:#c13353;width:65px");
							}
							$("#messagelength").val(data.length);
							$
									.each(
											data,
											function(i, v) {
												$("#newmessage")
														.append(
																"<li class='clearfix'><a style='cursor:pointer'><div class='chat-body clearfix'><div class='header'><small class='pull-right text-muted'></small></div><p>"
																		+ v
																		+ "</p></div></a></li>");
											})

						})

	}
</script>


