window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	  document.getElementById('bgVideo').pause();
	$("#navbar-wrap").fadeIn(300);
  } else {
    $("#navbar-wrap").fadeOut(300);
    document.getElementById('bgVideo').play();
  }
}
