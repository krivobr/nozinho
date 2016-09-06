"use strict";

jQuery(document).ready(function($){

	/************** Menu Content Opening *********************/
	
	$("#menu-container .contact").addClass("animated fadeInDown").show();
	loadScript();


	/************** Gallery Hover Effect *********************/
	$(".overlay").hide();

	$('.gallery-item').hover(
	  function() {
	    $(this).find('.overlay').addClass('animated fadeIn').show();
	  },
	  function() {
	    $(this).find('.overlay').removeClass('animated fadeIn').hide();
	  }
	);


	/************** LightBox *********************/
	$(function(){
		$('[data-rel="lightbox"]').lightbox();
	});


	$("a.menu-toggle-btn").click(function() {
	  $(".responsive_menu").stop(true,true).slideToggle();
	  return false;
	});
 
    $(".responsive_menu a").click(function(){
		$('.responsive_menu').hide();
	});

});


function loadScript() {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
      'callback=initialize';
  document.body.appendChild(script);
}

function initialize() {
    var mapOptions = {
      zoom: 15,
      center: new google.maps.LatLng(-19.9348755,-43.9370836)
    };
    var map = new google.maps.Map(document.getElementById('templatemo_map'),  mapOptions);
}
