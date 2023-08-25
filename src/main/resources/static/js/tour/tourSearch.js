$('.search_area_btn').on('click', function(){
   $('.area_container').addClass('modal_on');
});
$('.search_content_btn').on('click', function(){
   $('.content_type_container').addClass('modal_on');
});
$('.search_category_btn').on('click', function(){
   $('.service_container').addClass('modal_on');
});
$('.modal_cancel_btn').on('click', function(){
   closeModal();
});
$('.modal_close_btn').on('click', function(){
   closeModal();
});
function closeModal(){
   $('.modal_container').removeClass('modal_on');
}
$('.type_keyword_btn').on('click', function(){
   $('.type_keyword').css('display', 'block');
   $('.type_area').css('display', 'none');
});

$('.type_area_btn').on('click', function(){
   $('.type_area').css('display', 'block');
   $('.type_keyword').css('display', 'none');
});