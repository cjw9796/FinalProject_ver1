


/*
$(document).ready(function() {
    // 웹페이지 로드 시 초기화
    $("#result_location").hide();

    $("#input_location").click(function() {
        $("#result_location").toggle();
    });
});

$(document).ready(function() {
    // 웹페이지 로드 시 초기화
    $("#result_date").hide();

    $("#input_date").click(function() {
        $("#result_date").toggle();
    });
});

*/

$(document).ready(function() {
    // 웹페이지 로드 시 초기화
    $("#result_location").hide();
    $("#result_date").hide();

    $("#input_location").click(function() {
        if ($("#result_date").is(":visible")) {
            $("#result_date").hide();
        }
        $("#result_location").toggle();
    });

    $("#input_date").click(function() {
        if ($("#result_location").is(":visible")) {
            $("#result_location").hide();
        }
        $("#result_date").toggle();
    });
});







    /*qna 아코디언 폼 자바스크립트*/

$(function() {
    $('.qna_content_container .qna_title_wrap').click(function(event) {
        event.stopPropagation();

        var $clickedContainer = $(this).closest('.qna_content_container');
        var $accordion = $clickedContainer.find('.qna_line .qna_content_accordion');

        // 다른 아코디언 닫기 및 스타일 초기화
        $('.qna_content_container').not($clickedContainer).removeClass('on').css('margin-bottom', '0');
        $('.qna_line .qna_content_accordion').not($accordion).slideUp(200);

        // 현재 클릭한 아코디언 열기/닫기
        $accordion.stop().slideToggle(200, function() {
            if ($accordion.is(':visible')) {
                $clickedContainer.addClass('on');
                $clickedContainer.css('margin-bottom', $accordion.outerHeight() + 'px');
            } else {
                $clickedContainer.removeClass('on').css('margin-bottom', '0');
            }
        });
    });

    $('.qna_content_container .qna_open_icon').click(function(event) {
        event.stopPropagation();
    });

    $('.qna_content_container .qna_content_accordion').click(function(event) {
        event.stopPropagation();
    });




});

/*qna 카테고리별 게시글 변경 자바스크립트*/

document.addEventListener("DOMContentLoaded", function() {
    // 웹페이지 로드 시 초기화
    const defaultAnswer = document.getElementById('qnaBtnAnswer1');
    defaultAnswer.style.display = 'block';
    document.getElementById('qnaBtnAnswer2').style.display = 'none';
    document.getElementById('qnaBtnAnswer3').style.display = 'none';

    // 버튼 클릭에 따른 요소 토글
    function toggleAnswer(btnId) {
        const answerId = btnId.replace('qnaBtn', 'qnaBtnAnswer');
        const answerElement = document.getElementById(answerId);

        // 모든 답변 요소 숨기기
        const allAnswerElements = document.querySelectorAll('.qna_category_content');
        allAnswerElements.forEach(function(element) {
            element.style.display = 'none';
        });

        // 클릭한 버튼에 해당하는 답변 요소 보이기
        answerElement.style.display = 'block';
    }

    document.getElementById('qnaBtn1').addEventListener('click', function() {
        toggleAnswer('qnaBtn1');
    });

    document.getElementById('qnaBtn2').addEventListener('click', function() {
        toggleAnswer('qnaBtn2');
    });

    document.getElementById('qnaBtn3').addEventListener('click', function() {
        toggleAnswer('qnaBtn3');
    });

});
