var areaText = '';

var areaCode = '';
var areaName = '';
var sigunguCode = '';
var sigunguName = '';

//지역 선택 모달
$('.search_area_btn').on('click', function(){
   $('.area_container').addClass('modal_on');

   $.ajax({
      url: 'tour/getArea',
      type: 'get',
      dataType: 'text',
      success: function(data){
         console.log(data);
         var item = JSON.parse(data).response.body.items.item;

         /* 시/도 리스트 생성 */
         for(var i = 0; i < item.length; i++){
            var content =
                           `<button class="modal_btn" onclick="insertArea(this)" value="${item[i].code}">` +
                           item[i].name + '</button>'

            if(i === Math.floor(item.length / 2)){

               var line = '<div class="btn_list" id="area_list2"></div>'
               $('#area_box').append(line);
            }

            if(i <= Math.floor(item.length/2)){
               $('#area_list1').append(content);
            } else {
               $('#area_list2').append(content);
            }
         }
      }
   });
});

function insertArea(e){
   areaCode = e.value;
   areaName = e.textContent;

   console.log('code=' + areaCode + ', name = ' + areaName);

   $.ajax({
      url: 'tour/getSigungu',
      type: 'get',
      data: {
         areaCode : areaCode
      },
      dataType: 'text',
      success: function(data){
         console.log(data);
         var item = JSON.parse(data).response.body.items.item;

         for(var i = 0; i < item.length; i++){
            var content = `<button class="modal_btn sigungu_btn" onclick = "insertAreaCode(this)" value="${item[i].code}">` +
                        item[i].name +'</button>';
            $('.sigungu_list').append(content);
         }
      }

   });
}

function insertAreaCode(e){
   sigunguCode = e.value;
   sigunguName = e.textContent;

   $('#area_area_code').text('광역시/도 : ' + areaName + ' , 시/군/구 : ' + sigunguName);
}
//관광 타입 모달
$('.search_content_btn').on('click', function(){
   $('.content_type_container').addClass('modal_on');
});

//서비스 분류 모달
$('.search_category_btn').on('click', function(){
   $('.service_container').addClass('modal_on');
});

//모달 창 닫기
$('.modal_cancel_btn').on('click', function(){
   closeModal();
});
$('.modal_close_btn').on('click', function(){
   closeModal();
});
function closeModal(){
   $('.modal_container').removeClass('modal_on');
}

//검색 타입 선택
$('.type_keyword_btn').on('click', function(){
   $('.type_keyword').css('display', 'block');
   $('.type_area').css('display', 'none');
});

$('.type_area_btn').on('click', function(){
   $('.type_area').css('display', 'block');
   $('.type_keyword').css('display', 'none');
});


/*
   $('#search_area_btn').on('click', function(){
   alert('모달');
});
   $(function () {
   $.ajax({
      url: '/tourismInfo',
      type: 'get',
      dataType: 'text',
      success: function (data) {
         let item = JSON.parse(data).response.body.items.item;
         for (let i = 0; i < item.length; i++) {
            let content = `<p>${item[i].title}</p>
                                 <p><img src="${item[i].firstimage}" width="500px" height="300px"></p>
                                 <p>${item[i].zipcode}</p>
                                 <p>${item[i].addr1}</p>
                                 <p>${item[i].overview}</p>`;
            $('.container1').append(content);
            blogSearchByContentId(item[i].title);

         }

      }
   });
});

   function blogSearchByContentId(title) {
   $.ajax({
      url: '/searchBlog',
      type: 'get',
      data: {
         title: title
      },
      dataType: 'text',
      success: function (data) {
         let items = JSON.parse(data).items;
         console.log(data);
         for (let i = 0; i < items.length; i++) {
            let content = `<p><a href="${items[i].link}">${items[i].title}</a></p>
                                  <p>${items[i].description}</p>
                                  <p><a href="${items[i].bloggerlink}">출처:${items[i].bloggername}</a></p>
                                  <p>작성일:${items[i].postdate}</p><hr/>`;
            $('.blog').append(content);
         }

      },
      error: function () {
         alert('블로그 오류');
      }
   });
}
*/
