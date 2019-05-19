//menu button
$(function() {
	// phone functionality for blog
	if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i
			.test(navigator.userAgent)) {
		$("#sidebar").hide();
		$("#sidebarbutton").show();
	}
	$("#sidebarbutton").click(function() {
		$("#sidebar").toggle("slow");
	});

	// layered menu
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

	// blog sidebar
	var $sidebar = $("#sidebar"), $window = $(window), offset = $sidebar
			.offset(), topPadding = 150;

	$window.scroll(function() {
		if ($window.scrollTop() > offset.top && !isMobile()) {
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

function menuLinks(open) {
	if (open) {
		$(".menuLink").first().show(900, function showNext() {
			$(this).next(".menuLink").show("fast", showNext);
		});
		$(".menuhr")
				.each(
						function(index) {
							$(this)
									.css(
											"width",
											((100 / $(".menuhr").length) * ($(".menuhr").length - index))
													+ "%");
						});
	} else {
		$(".menuLink").first().hide(200, function showNext() {
			$(this).next(".menuLink").hide("fast", showNext);
		});

	}
}

function scrollFunction() {

	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
		document.getElementById('bgVideo').pause();
	} else {
		document.getElementById('bgVideo').play();
	}
}

// Scrolling sidebar in blog

function isMobile() {
	return /Android|webOS|iPhone|iPod|iPad|BlackBerry|IEMobile|Opera Mini/i
			.test(navigator.userAgent);
}
