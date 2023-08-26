$(function () {
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

    /*##################################################################################################################

    update 내용

    ##################################################################################################################*/

    // 모달 열기
    $('.shuttle-button').click(function () {
        $('.modal').css('display', 'block');
        $("body").css('overflow', 'hidden');
    })
    // 모달 닫기

    $('.modal-btn').click(function () {
        $('.modal').css('display', 'none');
        $("body").css('overflow', 'auto');
    })

    // 텍스트 숨기기
    $('.arrow').click(function () {
        if ($(this).hasClass('false')) {
            $('.arrow').removeClass('false');
            $('.arrow').addClass('onArrow');
            $('.policy-detail').css('max-height', '989px');
            $('.policy-detail').css('margin-bottom', '20px');
        } else {
            $('.arrow').removeClass('onArrow');
            $('.arrow').addClass('false');
            $('.policy-detail').css('max-height', '0px');
            $('.policy-detail').css('margin-bottom', '0px');
        }
    })

    // 내륙, 제주 지역 선택텝
    var $landLocation = $('.insurance-locations div').click(function () {
        var idx = $landLocation.index(this);
        $('.insurance-locations div').removeClass('locationClick');
        $(this).addClass('locationClick');

        $('.insurance-info').removeClass('showTale');
        $('.sections-con.show > section:nth-child(1) > table').eq(idx).addClass('showTale');
    });
    /*##################################################################################################################


    ##################################################################################################################*/
});
// if (idx === 0) {
//     $('.second-box-tabs-clicked-bar').css('margin-left', '0px')
// } else if (idx === 1) {
//     $('.second-box-tabs-clicked-bar').css('margin-left', '243px')
// } else {
//     $('.second-box-tabs-clicked-bar').css('margin-left', '486px')
// }

