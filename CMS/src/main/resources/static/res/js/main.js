window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	  $("#footer").fadeIn(500);
	  $("#navbar").fadeIn(500);
  } else {
    $("#footer").fadeOut(500);
    $("#navbar").fadeOut(500);
  }
}
