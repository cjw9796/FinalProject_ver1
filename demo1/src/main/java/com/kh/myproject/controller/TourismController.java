package com.kh.myproject.controller;

import com.kh.myproject.blogsearch.BlogSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class TourismController {

    final String tourTestUrl = "https://apis.data.go.kr/B551011/KorService1/detailCommon1?serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=2901530&contentTypeId=39&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&numOfRows=10&pageNo=1";
    final String blogSearchUrl = "";
    @GetMapping("/tourism")
    public ModelAndView tourism(ModelAndView mav){
        mav.setViewName("tourism/tourism");

        return mav;
    }
    @GetMapping("/cities")
    public ModelAndView cities(ModelAndView mav){
        mav.setViewName("tourism/cities");

        return mav;
    }

    @GetMapping("/tourmain")
    public ModelAndView tourmain(ModelAndView mav){
        mav.setViewName("tourism/tourmain");

        return mav;
    }

    @GetMapping("/tourismInfo")
    public String tourismInfo(){
        StringBuilder result = new StringBuilder();

        try{
            URL url = new URL(tourTestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                result.append(line);
            }
            br.close();
            conn.disconnect();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }

    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("title") String title){

        return BlogSearch.getSearch(title);
    }
}
