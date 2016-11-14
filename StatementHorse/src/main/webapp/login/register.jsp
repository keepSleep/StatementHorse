<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>註冊財報馬</title>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${pageContext.request.contextPath}/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@include file="/top/Top.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">免費加入財報馬</h3>
					</div>
					<div class="panel-body">
						<form id="registerForm" role="form" method="post"
							action="${pageContext.request.contextPath}/account/register">
							<fieldset>
								<div class="row form-group">
									<div class="col-lg-6">
										<button type="button" class="btn btn-info btn-block">
											<span class="fa fa-facebook-square  "></span> FACEBOOK登入
										</button>
									</div>
									<div class="col-lg-6">
										<button type="button" class="btn btn-info btn-block">
											<span class="fa fa-google  "></span> GOOGLE登入
										</button>
									</div>
								</div>
								<div class="form-group">
									<label>帳號：</label> <input class="form-control"
										placeholder="輸入E-mail做為您的帳號" name="email" type="email"
										autofocus data-toggle="popover" data-placement="bottom"
										value="${email}">
								</div>
								<div class="form-group">
									<label>密碼：</label> <input class="form-control"
										placeholder="至少6個字母，英數皆可" name="password" type="password"
										value="">
								</div>
								<div class="form-group">
									<label>再次輸入密碼：</label> <input class="form-control"
										placeholder="再次輸入您的密碼" name="password2" type="password"
										value="">
								</div>
								<div class="form-group">
									<label>輸入驗證碼： </label> <input class="form-control"
										placeholder="請輸入4位數驗證碼" name="checkword" type="checkword"
										value=""> <br> <img
										src="${pageContext.request.contextPath}/account/identityServlet"
										id="identity" onload="btn.disabled = false;" /> <span
										id="btn" style="cursor: pointer;" class="fa fa-refresh"
										onclick="reloadImage()"></span>
								</div>
								<div class="row form-group">
									<div class="col-lg-6">
										<label> <a
											href="${pageContext.request.contextPath}/login/registermailagain.jsp">
												沒有收到註冊信</a></label>
									</div>
									<div class="col-lg-6">
										<label><a
											href="${pageContext.request.contextPath}/login/login.jsp">
												登入財報馬 </a></label>
									</div>

								</div>
								<c:if test="${not empty errormsg}">
									<div id="ErrorMsg" class="alert alert-danger">${errormsg}</div>
								</c:if>
								<div>
									<!-- Change this to a button or input when using this as a form -->
									<button type="submit" class="btn btn-lg btn-info btn-block">
										<label>註冊</label>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/js/sb-admin-2.js"></script>
	<!--4位數驗證碼 -->
	<script>
		function reloadImage() {
			document.getElementById('btn').disabled = true;
			document.getElementById('identity').src = '${pageContext.request.contextPath}/account/identityServlet?ts'
					+ new Date().getTime();
		}
		$("#registerForm").on("submit", function(event) {
			event.preventDefault();
			$.post('register', $(this).serialize(), function(msg, status) {
				document.open();
				document.write(msg);
				document.close();
			})
		})
	</script>
</body>

</html>
