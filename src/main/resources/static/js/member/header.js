
$(document).ready(function () {


    const choice_post = document.querySelector(".choice_post");
    const nav_community = document.querySelector("#nav_community");
    const nav_trip = document.querySelector("#nav_trip");
    const community_under_bar = $('.community'); // community 아래쪽 div
    const store_under_bar = $('.store'); //store 아래쪽 div

//     const menu_point = document.querySelector(".menu_point"); // 커뮤니티의 홈
//     const accompany = document.querySelector(".accompany"); // 커뮤니티의 동행
//     const plan = document.querySelector(".plan"); // 커뮤니티의 일정
//
//     const community = document.querySelector(".community");
//     const store = document.querySelector(".store");
//
//     const menu_point_store = document.querySelector(".menu_point_store");// 여행상점의 홈
//     const area = document.querySelector(".area"); // 여행상점의 여행지 둘러보기
//     const plane = document.querySelector(".plane"); // 여행상점의 항공권
//     const rental = document.querySelector(".rental"); // 여행상점의 렌터카
//
// // btn
//     const login_btn = document.querySelector(".login_btn");
//     const join_btn = document.querySelector(".join_btn");
//
// // icon (mypage, cart)
//     const mypage = document.querySelector(".mypage");


// location.href처리에 따라 스타일 처리하기
    var url = window.location.pathname;
    var url_list = url.substring(1).split("/");

    if(url_list[0] === ''){
        $(".home").addClass('menu_underline');
    }



    var context_path = url_list[0]; // store/community
    var menu = url_list[1]; // 세부적으로 나뉘는 서브 메뉴

    if (context_path === "community") {
        //스타일 처리


        nav_community.classList.add('nav_underline');
        nav_trip.classList.remove('nav_underline');
        $(`.${menu}`).addClass('menu_underline');

        // 커뮤니티/여행상점 선택에 따른 underbar disappear선택
        community_under_bar.removeClass('disappear');
        store_under_bar.addClass('disappear');


    } else if (context_path === "store") {

        nav_community.classList.remove('nav_underline');
        nav_trip.classList.add('nav_underline');
        $(`.${menu}`).addClass('menu_underline');

        // 커뮤니티/여행상점 선택에 따른 underbar disappear선택
        community_under_bar.addClass('disappear');
        store_under_bar.removeClass('disappear');


    }

    $('#write_btn').click(()=>{
        if (choice_post.classList.contains("disappear")) {
            choice_post.classList.remove("disappear");
        } else {
            choice_post.classList.add("disappear");
        }

    })



// 커뮤니티 클릭
// nav_community.addEventListener("click",()=>{
//     // nav_trip.classList.remove("nav_underline");
//     // nav_community.classList.add("nav_underline");
//     // community.classList.remove("disappear");
//     // store.classList.add("disappear");
//     // area.classList.remove("menu_underline");
//     // plane.classList.remove("menu_underline");
//     // plane.classList.remove("menu_underline");
//     // rental.classList.remove("menu_underline");
//     // menu_point_store.classList.add("menu_underline");
//     window.location.href="../community/home";
// })
// // 여행상점 클릭
// nav_trip.addEventListener("click",()=>{
//     // nav_trip.classList.add("nav_underline");
//     // nav_community.classList.remove("nav_underline");
//     // community.classList.add("disappear");
//     // store.classList.remove("disappear");
//     // accompany.classList.remove("menu_underline");
//     // plan.classList.remove("menu_underline");
//     // menu_point.classList.add("menu_underline");
//     window.location.href="../store/home";
// })
//
// // community nav
// menu_point.addEventListener("click",()=>{
//     // accompany.classList.remove("menu_underline");
//     // plan.classList.remove("menu_underline");
//     // menu_point.classList.add("menu_underline");
//     window.location.href="../community/home";
// })
// accompany.addEventListener("click",()=>{
//     // menu_point.classList.remove("menu_underline");
//     // plan.classList.remove("menu_underline");
//     // accompany.classList.add("menu_underline");
//     window.location.href="../community/accompany";
// })
// plan.addEventListener("click",()=>{
//     // accompany.classList.remove("menu_underline");
//     // menu_point.classList.remove("menu_underline");
//     // plan.classList.add("menu_underline");
//     window.location.href="../community/plan";
// })
//
// // store nav
// menu_point_store.addEventListener("click",()=>{
//     area.classList.remove("menu_underline");
//     plane.classList.remove("menu_underline");
//     rental.classList.remove("menu_underline");
//     menu_point_store.classList.add("menu_underline");
//     window.location.href="../store/home";
//
// })
// area.addEventListener("click",()=>{
//     plane.classList.remove("menu_underline");
//     menu_point_store.classList.remove("menu_underline");
//     rental.classList.remove("menu_underline");
//     area.classList.add("menu_underline");
//     window.location.href="../store/area";
// })
// plane.addEventListener("click",()=>{
//     area.classList.remove("menu_underline");
//     menu_point_store.classList.remove("menu_underline");
//     rental.classList.remove("menu_underline");
//     plane.classList.add("menu_underline");
//     window.location.href="../store/plane";
// })
// rental.addEventListener("click",()=>{
//     area.classList.remove("menu_underline");
//     plane.classList.remove("menu_underline");
//     menu_point_store.classList.remove("menu_underline");
//     rental.classList.add("menu_underline");
//     window.location.href="../store/rental";
// })
//
// mypage.addEventListener("click",()=>{
//     window.location.href="../member/mypage";
// })

    $(function () {

        console.log('hi')
        $('html').removeClass('no-js');

    });


})

