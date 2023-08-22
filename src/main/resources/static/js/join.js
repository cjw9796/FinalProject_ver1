// 유효성 검사 실시. 나중
$(document).ready(function () {


    // mbti 버튼 클릭시 토글기능 추가
    var btn1 = false;
    var btn2 = false;
    var btn3 = false;
    var btn4 = false;
    var btn5 = false;
    var btn6 = false;
    var btn7 = false;
    var btn8 = false;

    var query = {

        user_id: $("#user_id").val(),
        user_password: $("#user_password").val(),
        user_name: $("#user_name").val(),
        user_phone: $("#user_phone").val(),
        user_gender: $("#user_gender").val(),
        user_year: $("#user_year").val(),
        user_month: $("#user_month").val(),
        user_day: $("#user_day").val(),

    };


    $('#btn1').click(() => {

        console.log("btn1 " + btn1);

        if (!btn1) {

            $('#btn1').addClass('active');
            $('#btn2').removeClass('active');

            btn1 = true; // btn1 켜짐
            btn2 = false; // btn2 꺼짐

        } else { // btn이 켜진 상태에서 btn1을 누르면 btn1은 끈다.

            $('#btn1').removeClass('active');
            btn1 = false;

        }
    })

    $('#btn2').click(() => {

        if (!btn2) { //
            $('#btn2').addClass('active');
            $('#btn1').removeClass('active');

            btn2 = true;
            btn1 = false;

        } else {
            $('#btn2').removeClass('active');
            btn2 = false;
        }
    })

    $('#btn3').click(() => {


        if (!btn3) { //
            $('#btn3').addClass('active');
            $('#btn4').removeClass('active');

            btn3 = true;
            btn4 = false;

        } else {
            $('#btn3').removeClass('active');
            btn3 = false;
        }
    })

    $('#btn4').click(() => {


        if (!btn4) { //
            $('#btn4').addClass('active');
            $('#btn3').removeClass('active');

            btn4 = true;
            btn3 = false;

        } else {
            $('#btn4').removeClass('active');
            btn4 = false;
        }
    })

    $('#btn5').click(() => {


        if (!btn5) { //
            $('#btn5').addClass('active');
            $('#btn6').removeClass('active');

            btn5 = true;
            btn6 = false;

        } else {
            $('#btn5').removeClass('active');
            btn5 = false;
        }
    })


    $('#btn6').click(() => {


        if (!btn6) { //
            $('#btn6').addClass('active');
            $('#btn5').removeClass('active');

            btn6 = true;
            btn5 = false;

        } else {
            $('#btn6').removeClass('active');
            btn6 = false;
        }
    })


    $('#btn7').click(() => {


        if (!btn7) { //
            $('#btn7').addClass('active');
            $('#btn8').removeClass('active');

            btn7 = true;
            btn8 = false;

        } else {
            $('#btn7').removeClass('active');
            btn7 = false;
        }
    })

    $('#btn8').click(() => {


        if (!btn8) { //
            $('#btn8').addClass('active');
            $('#btn7').removeClass('active');

            btn8 = true;
            btn7 = false;

        } else {
            $('#btn8').removeClass('active');
            btn8 = false;
        }
    })

    $('#submit_btn').click(() => {

        var check = true; // ajax통신을 위해 가지고 있어야할 체크 값.


        // 중복된 아이디가 있는지 검사하는 기능 추가해야함.


        if (query.user_id === "") {

            alert("아이디를 입력하세요.");
            check = false;
        } else if (query.user_password === "") {
            alert("비밀번호를 입력하세요.");
            check = false;

        } else if (query.user_password.length < 8 || query.user_password.length > 12) { // 비밀번호가 8자미만, 12자 초과일떄

            alert("비밀번호는 8자 이상 12자 이하여야합니다.");
            check = false;
        } else if ("") {
        } // 특수문자 확인 조건부 표현식.


        if (check) { // 유효성 검사를 실시한 후에 이상이 없을 경우에 아래 ajax통신을 실시한다.

            $.ajax({
                url: '/joinPro',
                method: 'POST',
                data: query,
                success: function (data) {

                    console.log("ajax통신 성공")
                    window.location.href = "/";

                },
                error: function () {
                    console.log("ajax통신 실패")
                }
            });


        }

    });


// ajax통신방법 1.


})

