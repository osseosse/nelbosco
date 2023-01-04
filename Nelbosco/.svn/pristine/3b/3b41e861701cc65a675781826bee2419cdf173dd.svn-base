/* -------------------------------------------------------------------
 * Plugin Name           : Matychale - Masonry & Grid Gallery
 * Author Name           : Yucel Yilmaz
 * Author URI            : https://codecanyon.net/user/aip_theme3434
 * Created Date          : 12 February 2020
 * Version               : 1.0
 * File Name             : main.js
------------------------------------------------------------------- */

/* -------------------------------------------------------------------
 [Table of contents]
 * 01.Gallery Cobbles
 * 02.Gallery Masonry
 * 03.Gallery Grid
 * 04.Dynamic Copyright
*/

$(function() {
    "use strict";

   // Call all ready functions
    matychale_Gallery_Cobbles();
   // matychale_Gallery_Masonry();
   // matychale_Gallery_Masonry_Two();
   // matychale_Gallery_Grid();
   // matychale_Copyright();
});

/* ------------------------------------------------------------------- */
/* 01.Gallery Cobbles
/* ------------------------------------------------------------------- */
function matychale_Gallery_Cobbles() {
    "use-strict";

    // Variables 
    var galleryWrapper         = $('.matychale-gallery-cobbles'),
        portfolioFilterBtn     = galleryWrapper.find('.portfolio-filter > a'),
        portfolioCobbles       = $('.portfolio-cobbles-item');

     //  Portfolio Gallery Popup */
     portfolioCobbles.magnificPopup({
         delegate: '.portfolio-zoom-link',
         type: 'image',
         gallery: {
             enabled: true
         }
     });

    // Porfolio Filtering Click Events
    portfolioFilterBtn.on("click", function() {
        portfolioFilterBtn.removeClass('current');
        $(this).addClass('current');
        return false;
    });

    // Portfolio Masonary Gallery
    galleryWrapper.imagesLoaded(function() {
        var grid = $('.portfolio-gallery').isotope({
            itemSelector: '.portfolio-cobbles-item',
            percentPosition: true,
            masonry: {
                columnWidth: '.portfolio-cobbles-item',
            }
        });

        // filter items on button click
        portfolioFilterBtn.on('click', function() {
            var filterValue = $(this).attr('data-gallery-filter');
            grid.isotope({
                filter: filterValue
            });
        });
    });
}

/* ------------------------------------------------------------------- */
/* pp slide > 메인
/* ------------------------------------------------------------------- */

/* ------------------------------------------------------
		*****  PP slider *****
--------------------------------------------------------*/
$(window).load(function(){
	
	/* main > 공간 */
	$('#slider1 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false
	});

	
	/* main > DISCOVER MENU */
	
	$('#sliderh6-1 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-2 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-3 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-4 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-5 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-6 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-7 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});
	$('#sliderh6-8 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:3,
		transitionTime:250
	});


	/* 브런치레스토랑 > 메뉴 > 디너 */
	$('#slider2 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:1,
		transitionTime:250
	});

	/* 루프탑 > 메인 */
	$('#slider3 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:1,
		transitionTime:250

	});

	/* 루프탑 > 메인 */
	$('#slider4 .slider').prrpleSlider({
		autoPlay:true,				//play slider automatically?  //false 사용시 멈춤
		autoPlayInterval:	4000,		
		csstransforms:	false,
		richSwiping:false,
		multiple:1,
		transitionTime:250

	});

	
});





