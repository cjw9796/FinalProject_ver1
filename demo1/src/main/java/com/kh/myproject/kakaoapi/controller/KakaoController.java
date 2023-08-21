package com.kh.myproject.kakaoapi.controller;



import com.kh.myproject.kakaoapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KakaoController {



    // 1. 인증키가 필요함
    // 2. 인가코드가 필요함 .

    // restapi key :	d50ee5df80cd1482f2ef5c34906981cb

    // 카카오 서버가 redirect_url로 code를 전달한다. 인가코드.

    // 3. 실제 접속해서 정보를 가지고 오는 토큰키를ㅂㅂ
    // 4. acc`es`s_token을 서버로 전송한다.
    // 5. 서버에서 access_token을 이용해서 카카오 서버에서 사용자 장보를 받는다.
    // 6. 받은 사용자 장보를 이용해서 회원가입 또는 로그인을 진행한다

    // 본인코드로 작성 client_id~는 절대 띄어서 작성하지 말것.,

    // access token
    // refresh token이 필요하다.
    // 토큰 url ->

    @Autowired
    MemberService memberService;

    @RequestMapping("kakaologin")
    public String kakaologin(@RequestParam("code")String code){



        // 1. 인가코드
        System.out.println("code : " + code);

        // 2. 토큰 받기

        String access_Token = memberService.getAccessToken(code);
        System.out.println("accessToken : " + access_Token);

        // 3.정보를 출력하는 메서드를 호출
        System.out.println("test..");
        System.out.println("getUserInfo()" + memberService.getUserInfo(access_Token));

        return "kakaoLogin/login";


    }


}
