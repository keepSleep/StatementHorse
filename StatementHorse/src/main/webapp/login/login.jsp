<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/top/Top.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="google-signin-client_id"
	content="241585761463-5rgcpko29g39k2kjseog6ursgpnhnve7.apps.googleusercontent.com">

<title>登入財報馬</title>

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
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">登入財報馬</h3>
					</div>
					<div class="panel-body">
						<form id="loginForm" role="form" method="post"
							action="${pageContext.request.contextPath}/account/login">
							<fieldset>
								<div class="row form-group">
									<div class="form-group col-lg-6">
										<!-- 										<div class="fb-login-button" data-max-rows="1" -->
										<!-- 											data-size="large" data-show-faces="false" -->
										<!-- 											data-auto-logout-link="false" data-scope="email" -->
										<!-- 											onlogin="fblogin"></div> -->
										<button id="fbLoginBtn" type="button"
											class="btn btn-info btn-block">
											<span class="fa fa-facebook-square"></span> FACEBOOK登入
										</button>
									</div>
									<!-- 									<div class="form-group col-lg-6 g-signin2" -->
									<!-- 										data-onsuccess="onSignIn"> -->
									<div class="form-group col-lg-6">
										<button id="googleLoginBtn" type="button"
											class="btn btn-info btn-block">
											<span class="fa fa-google"></span> GOOGLE登入
										</button>
									</div>
								</div>

								<div class="form-group">
									<label>帳號：</label> <input class="form-control"
										placeholder="E-mail" name="email" type="email" autofocus
										data-toggle="popover" data-placement="bottom" value="${email}">
								</div>
								<div class="form-group">
									<label>密碼：</label> <input class="form-control"
										placeholder="Password" name="password" type="password"
										value="">
								</div>
								<div class="form-group">
									<c:if test="${not empty errormsg}">
										<div id="ErrorMsg" class="alert alert-danger">${errormsg}</div>
									</c:if>
								</div>
								<div class="form-group">
									<div class="col-lg-6">
										<!-- 								<div class="checkbox"> -->
										<!-- 									<label> <input name="remember" type="checkbox" -->
										<!-- 										value="Remember Me">保持登入 -->
										<!-- 								</label> -->
										<label><a
											href="${pageContext.request.contextPath}/account/forgetpw">
												忘記密碼 </a></label>
									</div>
									<div class="col-lg-6">
										<label> <a
											href="${pageContext.request.contextPath}/account/register">
												註冊財報馬 </a>
										</label>
									</div>

								</div>
					</div>
					<div>
						<!-- Change this to a button or input when using this as a form -->
						<button type="submit" class="btn btn-lg btn-info btn-block">
							登入
					</div>
					</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1663652687259440',
				xfbml : true,
				version : 'v2.8'
			});

		};

		$('#fbLoginBtn').on('click', function(e) {
			FB.login(function(response) {
				console.log('Welcome!  Fetching your information.... ');
				FB.api('/me', {
					fields : 'name, email'
				}, function(response) {

					console.log(response);
					$.post('fblogin', {
						email : response.email
					}, function(msg, status) {
						document.open();
						document.write(msg);
						document.close();
					})

				});
			}, {
				scope : 'email'
			});
		});

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.8&appId=1663652687259440";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/js/sb-admin-2.js"></script>

	<!-- Google登入驗證 -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://apis.google.com/js/api:client.js"></script>

	<script>
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile();
			console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
			console.log('Name: ' + profile.getName());
			console.log('Image URL: ' + profile.getImageUrl());
			console.log('Email: ' + profile.getEmail());

			$.post('googlelogin', {
				email : profile.getEmail()
			}, function(msg, status) {
				document.open();
				document.write(msg);
				document.close();
			})
		}
		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
			});
		}
		gapi
				.load(
						'auth2',
						function() {
							// Retrieve the singleton for the GoogleAuth library and set up the client.
							auth2 = gapi.auth2
									.init({
										client_id : '241585761463-5rgcpko29g39k2kjseog6ursgpnhnve7.apps.googleusercontent.com',
										cookiepolicy : 'single_host_origin',
									// Request scopes in addition to 'profile' and 'email'
									//scope: 'additional_scope'
									});
							attachSignin(document
									.getElementById('googleLoginBtn'));
						});
		function attachSignin(element) {
			console.log(element.id);
			auth2.attachClickHandler(element, {}, function(googleUser) {
				onSignIn(googleUser);
			}, function(error) {
				alert(JSON.stringify(error, undefined, 2));
			});
		}
	</script>

</body>

</html>
