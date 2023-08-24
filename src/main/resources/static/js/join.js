// 유효성 검사 실시. 나중
$(document).ready(function () {


    //회원가입시 휴대폰 인증요청에 대한 로직 처리

    var auth_num = ""; // 전역변수로 설정 후 사용자에게 간 문자값을 통신으로 받고나서 변수값에 저장한다.
    var auth_check = false; // 휴대폰 인증여부 체크.

    //인증번호 요청, 재요청 클릭시
    $('#auth_request_btn').click(function () {


        const phoneNumberPattern = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/; // 휴대폰 형식검사 정규 표현식
        const user_phone = $('#user_phone').val();


        if (phoneNumberPattern.test(user_phone)) {
            $.ajax({
                url: '/member/joinAuth',
                method: 'POST',
                data: user_phone,
                success: function (data) {

                    $('#auth_block').removeClass('disappear');
                    $('#auth_request_btn').text("재요청");

                    console.log(data); // controller에서 넘긴 data를 받아온다.
                    auth_num = data;

                    // 인증번호 칸 열기

                },
                error: function () {

                }
            });

        } else {

            alert("올바른 휴대폰 번호를 입력하세요.");

        }
    })
    // 인증하기 클릭시
    $('#auth_submit_btn').click(function () {

        console.log(auth_num);

        const input_auth_num = $('#auth_num').val();

        if (auth_num === input_auth_num) {// input값의 value와 아까 생성한 난수를 비교한다.

            alert('인증이 완료됐습니다.');
            auth_check = true;

        } else {
            alert('인증번호를 확인해주세요.');
        }

    })


    // mbti 버튼 클릭시 토글기능 추가
    var btn1 = false;
    var btn2 = false;
    var btn3 = false;
    var btn4 = false;
    var btn5 = false;
    var btn6 = false;
    var btn7 = false;
    var btn8 = false;

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


    // 회원가입 버튼 클릭시
    $('#submit_btn').click(() => {


        // 선택된 mbti 버튼들을 불러온다. --> mbti button이 선택되지 않았을 경우의 예외사항을 처리하기 위해서.
        var mbti = document.querySelectorAll("[name=mbti]");
        var mbti_result = "";
        var check = true; // ajax통신을 위해 가지고 있어야할 체크 값.


        for (var i = 0; i < 8; i++) {


            var mbti_val = mbti[i].value;

            var mbti_classNameList = mbti[i].classList;
            for (var j = 0; j < mbti_classNameList.length; j++) {

                if (mbti_classNameList[j] === "active") { // 버튼이 활성화돼있다면 해당 버튼의 value값을 더해준다.
                    mbti_result += mbti_val;
                }
            }

        }

        var user_img = "";
        if ($('#user_img').val() != "" && $('#user_img').val() != null) {

            user_img = $('#user_img').val();
            console.log("img있음.")
        }


        var query = {

            user_id: $("#user_id").val(),
            user_password: $("#user_password").val(),
            user_name: $("#user_name").val(),
            user_phone: $("#user_phone").val(),
            user_gender: $("#user_gender").val(),
            user_year: $("#user_year").val(),
            user_month: $("#user_month").val(),
            user_day: $("#user_day").val(),
            user_mbti: mbti_result,
            user_img: user_img  // 카카오로 로그인하지 않았을 경우. null 발생


        };

        console.log(query)


        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 형식검사 정규 표현식
        const phoneNumberPattern = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/; // 휴대폰 형식검사 정규 표현식
        const pwd_pattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/; // 패스워드 정규 표현식


        // 중복된 아이디가 있는지 검사하는 기능 추가해야함.


        if (query.user_id === "") {

            alert("아이디를 입력하세요.");

            check = false;
        } else if (!emailPattern.test(query.user_id)) { // 이메일 형식에 맞게 작성되지 않았다면

            alert("이메일 형식에 맞게 아이디를 작성해주세요.(ex : kh1234@naver.com");
            check = false;

        } else if (query.user_password === "") {
            alert("비밀번호를 입력하세요.");
            check = false;

        } else if (query.user_password.length < 8 || query.user_password.length > 16) { // 비밀번호가 8자미만, 12자 초과일떄

            alert("비밀번호는 8자 이상 16자 이하여야합니다.");
            check = false;
        } else if (!pwd_pattern.test(query.user_password)) { // 비밀번호가 8자미만, 12자 초과일떄

            alert("비밀번호는 영문, 숫자, 특수문자의 조합으로 이루어져야 합니다.");
            check = false;
        } else if (query.user_name === "") {
            alert("성함을 입력해주세요.");
            check = false;

        } else if (query.user_phone === "") {
            alert("휴대폰 번호를 입력해주세요.");
            check = false;

        } else if (!phoneNumberPattern.test(query.user_phone)) {
            alert("휴대폰 번호를 올바르게 입력해주세요.");
            check = false;

        } else if (query.user_gender === "") {
            alert("성별을 선택해주세요.");
            check = false;

        } else if (query.user_year === "" || query.user_month === "" || query.user_day === "") {
            alert("생년월일을 입력해주세요.");
            check = false;
        } else if (query.user_year.length < 4 || query.user_year.value > 2023) {
            alert("연도를 올바르게 입력해주세요");
            check = false;
        } else if (mbti_result.length < 4) { // mbti를 선택한 버튼이 4개 미만이라면


            alert("mbti를 선택해주세요.");
            check = false;


        }


        if (check && auth_check) { // 유효성 검사를 실시한 후에 이상이 없을 경우에 아래 ajax통신을 실시한다.


            $.ajax({
                url: '/member/joinPro',
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


        }else if(!auth_check){

            alert("휴대폰 인증을 완료해주세요.");
        }

    });


// ajax통신방법 1.


})


class Test {

    yeongsub1 = 30;

}


let date = new Date();

function abc() {

    console.log(date);

}


let test = document.getElementById("#abc");
let document2 = new Document();
document2.getElementById('#abc');



