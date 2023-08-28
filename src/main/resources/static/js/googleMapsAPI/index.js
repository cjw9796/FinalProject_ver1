// const initMap = () => {
//     const map = new google.maps.Map(
//         document.getElementById("map"), {
//         center: { lat: 37.5400456, lng: 126.9921017 },
//         zoom: 10,
//     });
//     console.log(map)
// };
//
// initMap()



/* 구글맵 기본
async function initMap() {
    const { Map } = await google.maps.importLibrary("maps");

    map = new Map(document.getElementById("map"), {
        center: { lat: 33.450701, lng: 126.570667 },
        zoom: 11,
    });

}

initMap();
*/




window.onload = () => {

    let map;
    /* 마우스 커서 올리면 구글맵에서 위치 보이기*/
    async function initMap() {
        const { Map } = await google.maps.importLibrary("maps");

        map = new Map(document.getElementById("map"), {
            center: { lat: 33.450701, lng: 126.570667 },
            zoom: 11,
        });

    }

    initMap();


    const mapsMouseEnter = document.querySelector("#path1")
    mapsMouseEnter.addEventListener("mouseenter", () => {
        map.setZoom(13);
        map.setCenter({lat:33.5067792 , lng:126.492901});
        console.log(map);

    });

    const mapsInput = document.querySelector("#placeSearchInput")

    const mapsBtn = document.querySelector("#placeSearchButton")

    mapsBtn.addEventListener("click", async () => {
        mapsInput.value
        map.setZoom(13);

        const request = {
            query: mapsInput.value,
            fields: ["ALL"],
        }; /* 여기까지 텍스트 넣어서 input */







        const {PlacesService} = await google.maps.importLibrary("places");


        const service = new PlacesService(map)
        //
        // PlacesService
        service.findPlaceFromQuery(request, (result, status) => {
            console.log('result : ',result);
            console.log(status);

            const info = result[0].geometry.location
            /* 여기 result[0]배열에 FOR문 돌려서 10개 정보 뽑아와야 한다. */
            const lat = result[0].geometry.location.lat()
            const lng = result[0].geometry.location.lng()
            console.log(lat, lng)

            /* 얘는 함수가 또 아님, 그래서 함수 아닌 건 보통처럼 가져오면 됨*/
            const photogoogle = result

            /* 사진, 리뷰, 정보 등 가져올 수 있음 result 로 찍힘 */


        })


    })

}










