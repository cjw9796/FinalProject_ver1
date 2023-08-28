package com.kh.myproject.api.example;// 네이버 검색 API 예제 - 블로그 검색

import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiExamSearchNaverAPI {


    public static void main(String[] args) {

        // 내클라이언트 id : VLPSH2_tnUSwtIT1ah0I
        // 내 클라이언트 시크릿 : 05bjKDDAG_
        String clientId = "VLPSH2_tnUSwtIT1ah0I"; //애플리케이션 클라이언트 아이디
        String clientSecret = "05bjKDDAG_"; //애플리케이션 클라이언트 시크릿

        
//        String apiURL = "https://openapi.naver.com/v1/search/news.json";  // 뉴스 JSON 결과
//        String query = "코로나";  // 검색어
        
        String apiURL = "https://openapi.naver.com/v1/search/shop.json";  // 쇼핑 JSON 결과
        String query = "배고프다";  // 검색어

        String display = "12";  // 보여질 페이지 갯수, 최대 100개
        String start = "2";     // 페이지 시작 위치
        
        
        try {
        	query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
        StringBuilder url = new StringBuilder();

        url.append(apiURL);
        url.append("?query="+query);
        url.append("&display="+display);
        url.append("&start="+start);


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(url.toString(), requestHeaders);


        System.out.println(responseBody);
        
//        try {
//			List<News> list = NaverApiParser.parseNews(responseBody);
//			for(News item : list) {
//				System.out.println(item.toString());
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
        try {
			List<Product> list = NaverApiParser.parseShop(responseBody);
			for(Product item : list) {
				System.out.println(item.toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}