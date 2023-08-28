package com.kh.myproject.store.tour.contoller;

import com.kh.myproject.store.tour.CallTourAPI;
import com.kh.myproject.api.naverBlog.BlogSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@SessionAttributes("user")
public class TourController {


    final String TOURAPI_URL = "https://apis.data.go.kr/B551011/KorService1/";

    final String SERVICE_KEY = "serviceKey=ZgRTKBFIJGjeIJ14VHOZrP9UMtis8xSBTJvnPqQIigzUQ4aIL8V03y5XCVZ5B8GAKHaJX%2FOz2UpnX%2FvgKqv38w%3D%3D";
    final String LAST_URL = "&MobileOS=ETC&MobileApp=AppTest&_type=json";
    //    final String DETAILCOMMON_LAST_URL = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&numOfRows=10&pageNo=1";
    final String blogSearchUrl = "";


    @GetMapping("store/home")
    public ModelAndView storeHome(ModelAndView mav) {
        mav.setViewName("store/tour/tourmain");
        return mav;
    }

    @GetMapping("/store/tour/tourSearch")
    public ModelAndView tourism(ModelAndView mav) {
        mav.setViewName("store/tour/tourSearch");

        return mav;
    }

    @GetMapping("/store/tour/cities")
    public ModelAndView cities(ModelAndView mav) {
        mav.setViewName("store/tour/cities");

        return mav;
    }


    @GetMapping("/store/tour/tourDetail")
    public ModelAndView tourDetail(ModelAndView mav) {
        mav.setViewName("store/tour/tourDetail");

        return mav;
    }

    @GetMapping("/tour/getArea")
    public String getArea() throws Exception {
        StringBuilder result = new StringBuilder();
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY + "&numOfRows=17" + LAST_URL;

        return CallTourAPI.getData(url);
    }

    @GetMapping("/tour/getSigungu")
    public String getSigungu(@RequestParam String areaCode) throws Exception{
        String url = TOURAPI_URL + "areaCode1?" + SERVICE_KEY + "&areaCode=" + areaCode +
                    "&numOfRows=40" + LAST_URL;
        return CallTourAPI.getData(url);
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
