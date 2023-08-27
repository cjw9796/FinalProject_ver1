package com.kh.myproject.store.tour.contoller;

import com.kh.myproject.api.CallOpenAPI;
import com.kh.myproject.api.blogsearch.BlogSearch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@Slf4j
public class TourController {


    final String TOURAPI_URL = "https://apis.data.go.kr/B551011/KorService1/";

    final String SERVICE_KEY = "serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D";
    final String LAST_URL = "&MobileOS=ETC&MobileApp=AppTest&_type=json";
    //    final String DETAILCOMMON_LAST_URL = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&numOfRows=10&pageNo=1";
    final String blogSearchUrl = "";


    @GetMapping("/tourism")
    public ModelAndView tourism(ModelAndView mav) {
        mav.setViewName("tourism/tourSearch");

        return mav;
    }

    @GetMapping("/cities")
    public ModelAndView cities(ModelAndView mav) {
        mav.setViewName("tourism/cities");

        return mav;
    }

    @GetMapping("/tourmain")
    public ModelAndView tourMain(ModelAndView mav) {
        mav.setViewName("tourism/tourmain");

        return mav;
    }

    @GetMapping("/tourDetail")
    public ModelAndView tourDetail(ModelAndView mav) {
        mav.setViewName("tourism/tourDetail");

        return mav;
    }

    @GetMapping("/tour/getArea")
    public String getArea() throws Exception {
        StringBuilder result = new StringBuilder();
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY + "&numOfRows=17" + LAST_URL;

        return CallOpenAPI.getData(url);
    }

    @GetMapping("/tour/getSigungu")
    public String getSigungu(@RequestParam String areaCode) throws Exception{
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY + "&areaCode=" + areaCode +
                    "&numOfRows=40" + LAST_URL;
        return CallOpenAPI.getData(url);
    }
//    @GetMapping("/tourismInfo")
//    public String tourismInfo(){
//        StringBuilder result = new StringBuilder();
//
//        try{
//            URL url = new URL();
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//
//            while((line = br.readLine()) != null){
//                result.append(line);
//            }
//            br.close();
//            conn.disconnect();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return result.toString();
//    }

    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("title") String title) {

        return BlogSearch.getSearch(title);
    }
}
