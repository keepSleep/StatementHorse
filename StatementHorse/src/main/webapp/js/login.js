$(function() {
//	 $('[data-toggle="popover"]').popover();
	$("#loginForm").on("submit", function(event) {
		event.preventDefault();
		console.log($(this).serialize());
//		$('[data-toggle="popover"]').popover({
//			trigger : 'manual'
//		});
		$.post('login', $(this).serialize(), function(msg, status) {
			document.write(msg);
//			var popover = $('[name="email"]').data('bs.popover');
//			popover.options.content = msg;
//			 $('[name="email"]').popover('show');
		})
	})
});