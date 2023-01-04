


// -------------------------------
//  prrple.slider 
//---------------------------------




// -------------------------------
//  Tab
//---------------------------------

/* 2021-06-03 메인 DISCOVER MENU 텝  */
$(document).ready(function(){
  $.fn.basicTabs2 = function(options){ /* basicTabs2 변경 */
    var settings = $.extend({
      active_class: "current",
      list_class: "tabs2", /* tabe2 변경*/
      content_class: "tab_content2", /* tab_content2 */
      starting_tab: 1
    }, options );
    var $content = $('.' + settings.content_class);
    var $list = $('.' + settings.list_class);
    $content.find('dd').hide();
    $content.find("dd:nth-child(" + settings.starting_tab + ")").show();
    $list.find("li:nth-child(" + settings.starting_tab + ")").addClass(settings.active_class);

    $("." + settings.list_class + ' li a').click(function(e){
        $list.find("li").removeClass(settings.active_class);
        $("." + settings.content_class + " > dd").hide();


        $(this).parent().addClass(settings.active_class);
        var currentTab = $(this).attr('href');
        $(currentTab).show();
        e.preventDefault();
    });
  };
}( jQuery ));



/* 2021-08-11 레스토랑 > 남촌투어  */
(function( $ ) {
  $.fn.basicTabs3 = function(options){ /* basicTabs3 변경 */
    var settings = $.extend({
      active_class: "current",
      list_class: "tabs3", /* tabe3 변경*/
      content_class: "tab_content3", /* tab_content3 */
      starting_tab: 1
    }, options );
    var $content = $('.' + settings.content_class);
    var $list = $('.' + settings.list_class);
    $content.find('div').hide();
    $content.find("div:nth-child(" + settings.starting_tab + ")").show();
    $list.find("li:nth-child(" + settings.starting_tab + ")").addClass(settings.active_class);

    $("." + settings.list_class + ' li a').click(function(e){
        $list.find("li").removeClass(settings.active_class);
        $("." + settings.content_class + " > div").hide();
        $(this).parent().addClass(settings.active_class);
        var currentTab = $(this).attr('href');
        $(currentTab).show();
        e.preventDefault();
    });
  };
}( jQuery ));


/* 2021-08-11 루프탑 > 브랜드 */
(function( $ ) {
  $.fn.basicTabs4 = function(options){ /* basicTabs4 변경 */
    var settings = $.extend({
      active_class: "current",
      list_class: "tabs4", /* tabe4 변경*/
      content_class: "tab_content4", /* tab_content4 */
      starting_tab: 1
    }, options );
    var $content = $('.' + settings.content_class);
    var $list = $('.' + settings.list_class);
    $content.find('div').hide();
    $content.find("div:nth-child(" + settings.starting_tab + ")").show();
    $list.find("li:nth-child(" + settings.starting_tab + ")").addClass(settings.active_class);

    $("." + settings.list_class + ' li a').click(function(e){
        $list.find("li").removeClass(settings.active_class);
        $("." + settings.content_class + " > div").hide();
        $(this).parent().addClass(settings.active_class);
        var currentTab = $(this).attr('href');
        $(currentTab).show();
        e.preventDefault();
    });
  };
}( jQuery ));

