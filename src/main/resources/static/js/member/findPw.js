var $tablink = $('.second-box-tabs div').click(function (e) {
    var idx = $tablink.index(this);
    $('.second-box-tabs div').css('color', 'rgb(170, 170, 170)');
    $('.second-box-tabs div').css('font-weight', '400');
    $(this).css('color', '#00ce7c');
    $(this).css('font-weight', '600');

    var marginLeftValue = idx * 33.3333;
    $('.second-box-tabs-clicked-bar').animate({
        'margin-left': marginLeftValue + '%'
    }, 200)
    $('.sections-con').removeClass('show');
    $('.third-box section:first > div').eq(idx).addClass('show')
});
