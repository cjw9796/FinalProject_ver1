

$(function (){

$('.qna_content_container .qna_title_wrap').click(function (){
    $(this).next().stop().slideToggle(300)
})

    $('.qna_content_container .qna_content_accordion').click(function(){
        $(this).stop().slideUp(300)
    })

});