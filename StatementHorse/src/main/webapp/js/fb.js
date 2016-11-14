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
				document.location.href = msg;
				// document.open();
				// document.write(msg);
				// document.close();
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