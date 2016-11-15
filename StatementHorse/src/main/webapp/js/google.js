function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	console.log('ID: ' + profile.getId()); // Do not send to your backend! Use
	// an ID token instead.
	console.log('Name: ' + profile.getName());
	console.log('Image URL: ' + profile.getImageUrl());
	console.log('Email: ' + profile.getEmail());

	$.post('googlelogin', {
		email : profile.getEmail()
	}, function(msg, status) {
		// document.open();
		// document.write(msg);
		// document.close();
		document.location.href = msg;
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
					// Retrieve the singleton for the GoogleAuth library and set
					// up the client.
					auth2 = gapi.auth2
							.init({
								client_id : '241585761463-5rgcpko29g39k2kjseog6ursgpnhnve7.apps.googleusercontent.com',
								cookiepolicy : 'single_host_origin',
							// Request scopes in addition to 'profile' and
							// 'email'
							// scope: 'additional_scope'
							});
					attachSignin(document.getElementById('googleLoginBtn'));
				});
function attachSignin(element) {
	console.log(element.id);
	auth2.attachClickHandler(element, {}, function(googleUser) {
		onSignIn(googleUser);
	}, function(error) {
		alert(JSON.stringify(error, undefined, 2));
	});
}