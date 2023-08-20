package com.kh.myproject.controller;

import com.kh.myproject.dto.FlightTicketDto;
import com.kh.myproject.entity.FlightTicket;
import com.kh.myproject.repository.FlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class FlightController {

    @Autowired
    FlightTicketRepository repository;
    //공항리스트
    final String airportUrl = "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D&_type=json";
    //노선목록
    final String flightOpratInfoUrl = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D&";
    @GetMapping("/airportlist")
    public String flight(){
        StringBuilder result = new StringBuilder();
        try{
            URL url = new URL(airportUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                result.append(line);
            }
            br.close();
            conn.disconnect();
        } catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    @PostMapping("/searchflight")
    public String searchFlight(@RequestParam("startAirport") String startAirport,
                               @RequestParam("endAirport") String endAirport,
                               @RequestParam("startDay") String startDay){

        System.out.println(startDay.replace("-",""));
        String flightInfoUrl = flightOpratInfoUrl;
        flightInfoUrl += "depAirportId=" + startAirport + "&arrAirportId=" + endAirport +
                        "&depPlandTime=" + startDay.replace("-","") + "&_type=json";

        StringBuilder result = new StringBuilder();

        try{
            URL url = new URL(flightInfoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                result.append(line);
            }
            br.close();
            conn.disconnect();
            System.out.println(result);
        } catch(Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }

    @PostMapping("/saveFlight")
    public String saveFlight(@RequestBody FlightTicketDto ticket){
        System.out.println(ticket);
        FlightTicket entity = ticket.toEntity();

        repository.save(entity);
        return "";
    }
}
