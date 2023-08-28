


    $(function () {
    $.ajax({
        url: '/airportlist',
        type: 'get',
        dataType: 'text',
        success: function (data) {
            let item = JSON.parse(data).response.body.items.item;
            console.log(item);

            for (let i = 0; i < item.length; i++) {
                let option = `<option value=${item[i].airportId}>
                                  ${item[i].airportNm}
                                  </option>`;

                $('#start_airport').append(option);
                // document.getElementById("airport").innerHTML = option;
            }
        },
        error: function (request, status, error) {

            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

        }
    });


});
    $("#start_airport").change(function (e) {
    console.log(e.target.value);

    $.ajax({
    url: '/airportlist',
    type: 'get',
    dataType: 'text',
    success: function (data) {


    let item = JSON.parse(data).response.body.items.item;

    $('#end_airport').empty();
    for (let i = 0; i < item.length; i++) {
    if (e.target.value !== item[i].airportId) {
    let option = `<option value=${item[i].airportId}>${item[i].airportNm}</option>`;
    $('#end_airport').append(option);
}

    // document.getElementById("airport").innerHTML = option;
}
},
    error: function (request, status, error) {

    console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

}
});

});

    $('#flight_search_btn').click(function () {

    $.ajax({
        url: '/searchflight',
        type: 'post',
        data: {
            startAirport: $('#start_airport').val(),
            endAirport: $('#end_airport').val(),
            startDay: $('#flight_date').val()
        },
        dataType: 'text',
        success: function (data) {

            let item = JSON.parse(data).response.body.items.item;

            console.log(item);
            $('#flight_list').empty();
            if (item === undefined) {
                let flightInfo = "<tr><td colspan='8'>" + "해당 공항의 항공편이 없습니다." + "</td></tr>"
                $('#flight_list').append(flightInfo);
            } else {

                for (let i = 0; i < item.length; i++) {
                    let airlineNm = item[i].airlineNm; //항공사
                    let depAirportNm = item[i].depAirportNm; //출발공항
                    let arrAirportNm = item[i].arrAirportNm; //도착공항
                    let depPlandTime = item[i].depPlandTime; //출발시간
                    let arrPlandTime = item[i].arrPlandTime; //도착시간
                    let economyCharge = item[i].economyCharge; //이코노미
                    let prestigeCharge = item[i].prestigeCharge;//비즈니스
                    let vihicleId = item[i].vihicleId; //항공편

                    let flightInfo = `<tr>
                                      <td class="airlineNm">${airlineNm}</td>
                                      <td class="depAirportNm">${depAirportNm}</td>
                                      <td class="arrAirportNm">${arrAirportNm}</td>
                                      <td class="depPlandTime">${depPlandTime}</td>
                                      <td class="arrPlandTime">${arrPlandTime}</td>
                                      <td class="economyCharge">${economyCharge}</td>
                                      <td class="prestigeCharge">${prestigeCharge}</td>
                                      <td class="vihicleId">${vihicleId}</td>
                                      <td><button type="button" onclick="resFlight(${i})">예약하기</button></td>
                                      </tr>`;
                    $('#flight_list').append(flightInfo);
                }
            }

        },
        error: function (request, status, error) {

            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

        }
    });
});

    function resFlight(index) {
    $.ajax({
        url: '/saveFlight',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            ticFlightDepartureDate: $("#flight_list tr .depPlandTime")[flight].innerText,
            ticFlightArrivalDate: $("#flight_list tr .arrPlandTime")[flight].innerText,
            ticSeatGrade: "이코노미",
            ticAirlineName: $("#flight_list tr .airlineNm")[flight].innerText,
            ticFee: $("#flight_list tr .economyCharge")[flight].innerText,
            ticFromLocation: $("#flight_list tr .depAirportNm")[flight].innerText,
            ticToLocation: $("#flight_list tr .arrAirportNm")[flight].innerText,
        }),
        success: function (data) {
            alert("저장완료");

        },
        error: function (request, status, error) {

            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

        }
    });
}

    $('.flight_result_col').on('click', function () {
    alert('클릭');
});

