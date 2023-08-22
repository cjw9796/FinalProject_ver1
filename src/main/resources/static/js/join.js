
// 유효성 검사 실시. 나중
$(document).ready(function(){



    $('#submit_btn').click(function() { // form태그의 submit버튼 아님.
        // 유효성 검사를 실시하기 위해서는 ajaxForm이 아닌 ajax를 이용해야 하고 그를 위해서는
        // form태그에서 데이터를 바당오는 것이 아닌 js에서 모든 데이터를 받아와야한다.


        const user_id = $('#user_id').val;
        console.log(user_id);

        var check = true; // ajax통신을 위해 가지고 있어야할 체크 값.

        // var query = {
        //
        //     user_id : $("#user_id").val(),
        //     user_password : $("#user_password").val(),
        //     user_name : $("#user_name").val(),
        //     user_phone : $("#user_phone").val(),
        //     user_gender : $("#user_gender").val(),
        //     user_year : $("#user_year").val(),
        //     user_month : $("#user_month").val(),
        //     user_day : $("#user_day").val(),
        //
        // };

        console.log()
        if(query.user_id === ""){

            alert("아이디를 입력하세요.");
            check = false;
        }else if(query.user_password === ""){
            alert("비밀번호를 입력하세요.");
            check = false;

        }else if(query.user_password.length < 8 || query.user_password.length > 12){ // 비밀번호가 8자미만, 12자 초과일떄

            alert("비밀번호는 8자 이상 12자 이하여야합니다.");
        }else if(""){} // 특수문자 확인 조건부 표현식.


        if(check) { // 유효성 검사를 실시한 후에 이상이 없을 경우에 아래 ajax통신을 실시한다.
            //
            // $.ajax({
            //     url: '/joinPro',
            //     method: 'POST',
            //     data:query,
            //     success: function(data) {
            //
            //         $('#').text(data);
            //     },
            //     error: function() {
            //         $('#dataResult').text('Error occurred');
            //     }
            // });

            $('#join_form').ajaxForm({
                success: function (response) {
                    location.href = "/joinPro";
                },
                error: function () {
                    console.log("에러발생.");
                }
            });
        }

    });





    // ajax통신방법 1.




})

