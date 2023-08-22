const choice_post = document.querySelector(".choice_post");
const nav_community = document.querySelector("#nav_community");
const nav_trip = document.querySelector("#nav_trip");
const menu_point = document.querySelector(".menu_point");
const accompany = document.querySelector(".accompany");
const plan = document.querySelector(".plan");

const community = document.querySelector(".community");
const store = document.querySelector(".store");

const menu_point_store = document.querySelector(".menu_point_store");
const area = document.querySelector(".area");
const plane = document.querySelector(".plane");
const rental = document.querySelector(".rental");

// btn
const login_btn = document.querySelector(".login_btn");
const join_btn = document.querySelector(".join_btn");
function appear_menu() {
    if (choice_post.classList.contains("disappear")) {
        choice_post.classList.remove("disappear");
    } else {
        choice_post.classList.add("disappear");
    }
}
// 커뮤니티 클릭
nav_community.addEventListener("click",()=>{
    nav_trip.classList.remove("nav_underline");
    nav_community.classList.add("nav_underline");
    community.classList.remove("disappear");
    store.classList.add("disappear");
    area.classList.remove("menu_underline");
    plane.classList.remove("menu_underline");
    rental.classList.remove("menu_underline");
    menu_point_store.classList.add("menu_underline");
    window.location.href="../community/home";
})
// 여행상점 클릭
nav_trip.addEventListener("click",()=>{
    nav_trip.classList.add("nav_underline");
    nav_community.classList.remove("nav_underline");
    community.classList.add("disappear");
    store.classList.remove("disappear");
    accompany.classList.remove("menu_underline");
    plan.classList.remove("menu_underline");
    menu_point.classList.add("menu_underline");
    window.location.href="../store/home";
})

// community nav
menu_point.addEventListener("click",()=>{
    accompany.classList.remove("menu_underline");
    plan.classList.remove("menu_underline");
    menu_point.classList.add("menu_underline");
    window.location.href="../community/home";
})
accompany.addEventListener("click",()=>{
    menu_point.classList.remove("menu_underline");
    plan.classList.remove("menu_underline");
    accompany.classList.add("menu_underline");
    window.location.href="../community/accompany";
})
plan.addEventListener("click",()=>{
    accompany.classList.remove("menu_underline");
    menu_point.classList.remove("menu_underline");
    plan.classList.add("menu_underline");
    window.location.href="../community/plan";
})

// store nav
menu_point_store.addEventListener("click",()=>{
    area.classList.remove("menu_underline");
    plane.classList.remove("menu_underline");
    rental.classList.remove("menu_underline");
    menu_point_store.classList.add("menu_underline");
    window.location.href="../store/home";

})
area.addEventListener("click",()=>{
    plane.classList.remove("menu_underline");
    menu_point_store.classList.remove("menu_underline");
    rental.classList.remove("menu_underline");
    area.classList.add("menu_underline");
    window.location.href="../store/area";
})
plane.addEventListener("click",()=>{
    area.classList.remove("menu_underline");
    menu_point_store.classList.remove("menu_underline");
    rental.classList.remove("menu_underline");
    plane.classList.add("menu_underline");
    window.location.href="../store/plane";
})
rental.addEventListener("click",()=>{
    area.classList.remove("menu_underline");
    plane.classList.remove("menu_underline");
    menu_point_store.classList.remove("menu_underline");
    rental.classList.add("menu_underline");
    window.location.href="../store/rental";
})
login_btn.addEventListener("click",()=>{
    nav_trip.classList.remove("nav_underline");
    nav_community.classList.remove("nav_underline");
    community.classList.add("disappear");
    store.classList.add("disappear");
})
join_btn.addEventListener("click",()=>{
    nav_trip.classList.remove("nav_underline");
    nav_community.classList.remove("nav_underline");
    community.classList.add("disappear");
    store.classList.add("disappear");
})