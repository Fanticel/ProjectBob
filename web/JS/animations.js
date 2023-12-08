$(".firstAnimation").fadeIn(2000);

$(document).ready(function () {
  $(window).scroll(function () {
    $(".animation").each(function (i) {
      var bottom_of_element = $(this).offset().top + $(this).outerHeight();

      var bottom_of_window = $(window).scrollTop() + $(window).height() + 500;

      if (bottom_of_window > bottom_of_element) {
        $(this).animate({ opacity: "1" }, 1000);
      }
    });
  });
});
