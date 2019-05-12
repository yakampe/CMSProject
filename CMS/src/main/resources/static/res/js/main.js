$(function(){
	if (window.location.href.split('/').pop() === "") {
		window.onscroll = function() {
			scrollFunction()
		};
	} else {
		console.log($("#navbar-wrap").show());
	}

});


function scrollFunction() {

	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
		document.getElementById('bgVideo').pause();
		$("#navbar-wrap").fadeIn(300);
	} else {
		$("#navbar-wrap").fadeOut(300);
		document.getElementById('bgVideo').play();
	}
}
