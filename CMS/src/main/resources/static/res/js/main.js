//show navbar in all pages apart form root
$(function(){
	if (window.location.href.split('/').length < 5) {
		window.onscroll = function() {
			scrollFunction()
		};
	} else {
		console.log($("#navbar-wrap").show());
	}

});

//show navbar and stop video in homepage
function scrollFunction() {

	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
		document.getElementById('bgVideo').pause();
		$("#navbar-wrap").fadeIn(300);
	} else {
		$("#navbar-wrap").fadeOut(300);
		document.getElementById('bgVideo').play();
	}
}

//Scrolling sidebar in blog
$(function() {

    var $sidebar   = $("#sidebar"), 
        $window    = $(window),
        offset     = $sidebar.offset(),
        topPadding = 150;

    $window.scroll(function() {
        if ($window.scrollTop() > offset.top) {
            $sidebar.stop().animate({
                marginTop: $window.scrollTop() - offset.top + topPadding
            });
        } else {
            $sidebar.stop().animate({
                marginTop: 0
            });
        }
    });
    
});
