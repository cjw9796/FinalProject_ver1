package com.kh.myproject.kakaoapi.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;



@Service
public class MemberService {


    //인가 코드를 이용해 토큰값(accessToken)을 가져오는 메서드
    public String getAccessToken(String authorize_code) {

        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            // url 객체 생성
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Post요청을 위해 기본값이 false setDoOutput을 true로 변경

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Post요청에 필요로 요구하는 파라미터 스트림을 통해 전송한다.
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

            // 파라미터 저장해서 전송!
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=d50ee5df80cd1482f2ef5c34906981cb");
            sb.append("&redirect_uri=http://localhost:8080/kakaologin");
            sb.append("&code=" + authorize_code);

            bw.write(sb.toString());
            bw.flush(); // 내보내기 (전송)

            // 만약 전송이 성공적으로 왔다면 200 코드를 확인하는 메서드
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 데이터 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = ""; // 한줄씩 읽어오기
            String result = ""; // 읽어온 값이 있으면 문자열로 연결하기 위해서 만든 변수

            while ((line = br.readLine()) != null) {

                result += line;
            }
            System.out.println(); // 공백
            System.out.println("response body: " + result);

            // response body(result)반환 값
//            {
//                "access_token": "z1QLl9OaCeKa-y-LXuZ_rwr6SKbF6jIQRch4XlknCj10lwAAAYoCQMTZ",
//                    "token_type": "bearer",
//                    "refresh_token": "adbwr4K4yhWyH-CXOnJmtJ72xY-eOPPaTgw_TGIOCj10lwAAAYoCQMTY",
//                    "expires_in": 21599,
//                    "refresh_token_expires_in": 5183999
//            }


            // GSON 라이브러리에 포함된 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println();
            System.out.println("access_Token : " + access_Token);

            System.out.println();
            System.out.println("refresh_Token : " + refresh_Token);

            // 스트림 쓸 필요 없으니
            br.close();
            bw.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    // 토큰값을 이용해 로그인 한 유저의 정보를 가져오는 메서드
    public HashMap<String, Object> getUserInfo(String access_Token) {
        // 요청하는 클라이언트 마다 가진 정보가 다를 수 있기 때문에 hashMap이용해서
        // 데이터를 저장한다.

        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            // 실제 요청에 필요한 헤더에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            System.out.println(access_Token);

            // 만약 전송이 성공적으로 왔다면 200 코드를 확인하는 메서드
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 데이터 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));

            String line = ""; // 한줄씩 읽어오기
            String result = ""; // 읽어온 값이 있으면 문자열로 연결하기 위해서 만든 변수

            while ((line = br.readLine()) != null) {

                result += line;
            }
            System.out.println(); // 공백
            System.out.println("response body: " + result);

            // GSON 라이브러리에 포함된 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            System.out.println();
            System.out.println("kakao_account : " + kakao_account);

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String user_email = kakao_account.getAsJsonObject().get("email").getAsString();
            String gender = kakao_account.getAsJsonObject().get("gender").getAsString();


            System.out.println();
            System.out.println("파싱 후 nickname: " + nickname);
            System.out.println("파싱 후 email: " + user_email);
            System.out.println("파싱 후 gender: " + gender);

            userInfo.put("nickname", nickname);
            userInfo.put("email", user_email);
            userInfo.put("gender", gender);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;

    }

}


/*
 *
 * 데이터 파싱할 때 두가지 방법으로 할 수 있다.
 *
 * json 자바스크립트 객체를 표현하는 방법 다양한 프로그램에서 데이터를 주고받고 데이터를 꺼낼때 사용 JSON.parse(JSON으로
 * 변환할 문자열 )
 *
 * Gson 자바 객체를 JSON으로 바꾸는 작업 GSON 패키지 이걸 이용하면 쉽게 자바객체를 JSON으로 쉽게 변경해 준다.
 *
 *
 * <dependency> <groupId>com.google.code.gson</groupId>
 * <artifactId>gson</artifactId> <version>2.8.5</version> </dependency> <!--기본
 * org.json --> <dependency> <groupId>org.json</groupId>
 * <artifactId>json</artifactId> <version>20180813</version> </dependency>
 *
 *
 *
 * dependencies { implementation 'com.google.code.gson:gson:2.8.6' }
 *
 *
 */
