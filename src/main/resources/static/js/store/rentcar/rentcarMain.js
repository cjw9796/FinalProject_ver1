




$(document).ready(function() {
    // 웹페이지 로드 시 초기화
    $("#result_location").hide();
    $("#result_depart_date").hide();

    $("#input_location").click(function() {
        if ($("#result_depart_date").is(":visible")) {
            $("#result_depart_date").hide();
        }
        $("#result_location").toggle();
    });

    $("#depart_date").click(function() {
        if ($("#result_location").is(":visible")) {
            $("#result_location").hide();
        }
        $("#result_depart_date").toggle();
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



            //input을 datepicker로 선언

            $("#depart_datepicker").datepicker({

                dateFormat: 'mm.dd' //Input Display Format 변경

                ,nextText: "다음"

                ,inline: true

                ,range: true

                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시

                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시

                ,changeYear: true //콤보박스에서 년 선택 가능

                ,changeMonth: true //콤보박스에서 월 선택 가능

              /* ,selectOtherMonths: ture // 다른 달도 선택가능 */


            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트

            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트

            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트

            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트

            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트

            ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)

            ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)




        });

    $("#depart_datepicker").on("change", function() {
        const selectedDate = $(this).val();

        console.log(selectedDate);
        $("#depart_date_check").text(selectedDate);


    });


        //초기값을 오늘 날짜로 설정
        $('#depart_datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)




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
