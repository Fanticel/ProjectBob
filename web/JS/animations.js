//Made by Josip Brljevic

//Makes the element with the firstAnimation class fade in in two seconds
$(".firstAnimation").fadeIn(2000);

//This function activates the fade in animation on scroll
$(document).ready(function () {
  $(window).scroll(function () {
    //for each element with class animation
    $(".animation").each(function (i) {
      var bottom_of_element = $(this).offset().top + $(this).outerHeight();

      var bottom_of_window = $(window).scrollTop() + $(window).height() + 500;
      //calculations for handling the positioning on the screen currently, if we have passed a certain point of the element

      if (bottom_of_window > bottom_of_element) {
        //sets the opacity to 1 (visible) in one second
        $(this).animate({ opacity: "1" }, 1000);
      }
    });
  });
});
