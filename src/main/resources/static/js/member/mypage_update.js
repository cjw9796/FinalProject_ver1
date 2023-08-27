$(document).ready(function () {




    var user_name = $('[name=user_name]'); // name input

    var user_update_list = $('.user_info_input'); // info input

    var update_user_nick = document.querySelector(".update_user_nick");// name input div


    $('#name_update_btn1').click(() => { // 이름 수정 아이콘 클릭시

        // classList속성으로 추가제거해도 됨.
        user_name.prop('disabled', false);
        update_user_nick.removeAttribute("disabled");

    })

    $('#name_update_btn2').click(() => { // 회원 정보 수정 아이콘 클릭시.

        for (var i = 0; i < user_update_list.length; i++) {

            user_update_list[i].removeAttribute("disabled");


            // user_update_list[i].prop('disabled',false);
        }

    })


    $('.submit_btn').click(function () { // form 제출 시



        var query = {

            user_name: $('[name=user_name]').val(),
            user_gender: $('[name=user_gender]').val(),
            user_password: $('[name=user_password]').val(),
            user_year: $('[name=user_year]').val(),
            user_month: $('[name=user_month]').val(),
            user_day: $('[name=user_day]').val()
        }


        var check = true;

        for (var key in query) {
            if(query[key] === "" || query[key] == null){
                check = false;
                break;
            }
        }



        if(check) {

            update_user_nick.setAttribute("disabled",true);
            user_update_list.prop('disabled',true);
            user_name.prop('disabled',true);

            $.ajax({
                url: '/member/mypageUpdate',
                method: 'POST',
                data: query,
                success: function (data) {

                },
                error: function () {

                }
            });
        }
        else{
            alert("빈 칸 없이 입력하세요.");
        }


    })

})