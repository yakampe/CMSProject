//menu button
$(function() {

	var bodyOverflow = $("body").css("overflow");
	$('#menuButton').click(function() {
		if ($("body").css("overflow") == "hidden") {
			$("#mainMenu").slideUp(900);
			menuLinks(false);
			$("body").css("overflow", "auto");
		} else {
			$("#mainMenu").slideDown(900);
			menuLinks(true);
			$("body").css("overflow", "hidden");
		}

	});
});

function menuLinks(open) {
	if (open) {
		$(".menuLink").first().show(900, function showNext() {
			$(this).next(".menuLink").show("fast", showNext);
			console.log("doing");
		});
		$(".menuhr").each(
				function(index) {
					console.log(index
							+ " : "
							+ $(this).css("width",
									((100 / $(".menuhr").length) * ($(".menuhr").length -index))+"%"));
				});
	} else {
		$(".menuLink").first().hide(200, function showNext() {
			$(this).next(".menuLink").hide("fast", showNext);
			console.log("doing");
		});

	}
}

// show navbar and stop video in homepage
function scrollFunction() {

	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
		document.getElementById('bgVideo').pause();
		$("#navbar-wrap").fadeIn(300);
	} else {
		$("#navbar-wrap").fadeOut(300);
		document.getElementById('bgVideo').play();
	}
}

// Scrolling sidebar in blog
$(function() {

	var $sidebar = $("#sidebar"), $window = $(window), offset = $sidebar
			.offset(), topPadding = 150;

	$window.scroll(function() {
		if ($window.scrollTop() > offset.top) {
			$sidebar.stop().animate({
				marginTop : $window.scrollTop() - offset.top + topPadding
			});
		} else {
			$sidebar.stop().animate({
				marginTop : 0
			});
		}
	});
});
