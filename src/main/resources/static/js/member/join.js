// 유효성 검사 실시. 나중
$(document).ready(function () {




    //월선택에 따른 일자 출력 변경

    $('#user_day').click(function(){
        console.log('gg')

        console.log($('#user_month option:selected').val());

        var month = $('#user_month option:selected').val();


        var month_list = [[1,3,5,7,8,10,12],[2],[4,6,11]];

        out: for(var i = 0 ; i < month_list.length ; i++){

            for(var j = 0 ; j <month_list[i].length ; j++){

                if(month_list[i][j] == month){
                    // 31일, 29일, 30일을 선택한다.
                    if(i == 0){
                        // 31일로 바꾸게끔 처리.
                        console.log('31');
                        break out;
                    }else if(i == 1){
                        // 29일로 바꾸게끔 처리
                        console.log('29');
                        break out;
                    }else if(i == 2){
                        // 30일로 바꾸게끔 처리.
                        console.log('30');
                        break out;

                    }
                }
            }
        }

        console.log(document.getElementById("day_sequence"));
        console.log($('#day_sequence2'));


        // 로직처리.

        // switch(month){
        //
        //     case 1,3,5,7,8,10,12:
        //         console.log("31일");
        //         break;
        //     case 2:
        //         console.log('28일');
        //         break;
        //
        //     case 4,6,11:
        //         break;
        //         console.log('30일');
        // }
    })



    //회원가입시 휴대폰 인증요청에 대한 로직 처리

    var auth_num = ""; // 전역변수로 설정 후 사용자에게 간 문자값을 통신으로 받고나서 변수값에 저장한다.
    var auth_check = false; // 휴대폰 인증여부 체크.
    var id_check = false; // 아이디 중복 여부 체크.



    //인증번호 요청, 재요청 클릭시
    $('#auth_request_btn').click(function () {


        const phoneNumberPattern = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/; // 휴대폰 형식검사 정규 표현식
        const user_phone = $('#user_phone').val();


        if (phoneNumberPattern.test(user_phone)) {


            $('#auth_block').removeClass('disappear');
            $('#auth_request_btn').text("재요청");


        } else {

            alert("올바른 휴대폰 번호를 입력하세요.");

        }

        if(!auth_check) { // 인증완료가 아직 안됐을 경우.

            $.ajax({
                url: '/member/joinAuth',
                method: 'POST',
                data: {user_phone: user_phone},
                success: function (data) {


                    console.log(data); // controller에서 넘긴 data를 받아온다.
                    auth_num = data;

                    // 인증번호 칸 열기

                },
                error: function () {

                }

            });
        }else {

            alert("이미 인증이 완료됐습니다.");
        }
    })



    // 휴대폰 인증번호를 입력하고 인증하기 클릭시
    $('#auth_submit_btn').click(function () {

        if (auth_num === $('#auth_num').val() || auth_check) { // 처음 눌렸을 경우에는 난수와 input.val()의 입력값을 비교하고(첫번째조건)
            // 이후부터는 input.val()의 값이 바뀌기 ㅈ때문에 인증이 완료된 check값(auth_check)를 이용한다.

            alert('인증이 완료됐습니다.');
            auth_check = true;
            $('#auth_num').val("인증완료");
            $('#auth_num').prop('disabled',true);
            $('#auth_num').css('color','green');
            $('#user_phone').prop('disabled',true);


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
    // mbti 버튼 로직끝

    // id중복확인 로직



    $('#user_id').on('input',(e)=>{

        $('#id_check').removeClass("disappear");

        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 형식검사 정규 표현식

        var user_value = e.target.value;

        if (emailPattern.test(user_value)) { // 이메일형식이 맞으면 db와 아이디 중복 검사를 한다.

            $.ajax({
                url:"/member/checkId",
                method:"POST",
                data:{user_id:user_value},
                success:function(data){
                    id_check = data === "success" ? true : false; // true:중복아이디 없음, false:중복아이디있음.


                    if(id_check){

                        $('#id_check').text("사용가능한 이메일입니다.");
                        $('#id_check').css("color","green");

                    }else{

                        $('#id_check').text("중복되는 아이디입니다.");
                        $('#id_check').css("color","red");



                    }
                },
                error:function(){

                }
            })

        } else if (!emailPattern.test(user_value)) { // 이메일 형식에 맞게 작성되지 않았다면 이메일 형식에 맞게 하라고 한다.

            $('#id_check').text("이메일 형식에 맞게 아이디를 작성해주세요.") ;
            $('#id_check').css("color","red");



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


        const phoneNumberPattern = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/; // 휴대폰 형식검사 정규 표현식
        const pwd_pattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/; // 패스워드 정규 표현식
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 형식검사 정규 표현식


        // 중복된 아이디가 있는지 검사하는 기능 추가해야함.


        if (query.user_id === "") {

            alert("아이디를 입력하세요.");

            check = false;

        } else if (!emailPattern.test(query.user_id)) { // 이메일 형식에 맞게 작성되지 않았다면

            alert("이메일 형식에 맞게 아이디를 작성해주세요.(ex : kh1234@naver.com");
            check = false;

        } else if(!id_check){
            alert("중복되는 아이디입니다. 다른 아이디를 사용해주세요.");
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


        }else if(!auth_check){

            alert("휴대폰 인증을 완료해주세요.");
        }


        console.log(auth_check);


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


        }
    });



})




let test = document.getElementById("#abc");
let document2 = new Document();
document2.getElementById('#abc');