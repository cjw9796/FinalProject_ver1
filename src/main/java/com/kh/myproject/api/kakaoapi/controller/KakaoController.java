package com.kh.myproject.api.kakaoapi.controller;


import com.kh.myproject.api.kakaoapi.service.MemberService;
import com.kh.myproject.api.kakaoapi.vo.MemberVO;
import com.kh.myproject.member.user.model.entity.User;
import com.kh.myproject.member.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("user")
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

    private MemberService memberService;

    private UserService userService;


    @Autowired
    public KakaoController(MemberService memberService, UserService userService){

        this.memberService = memberService;
        this.userService = userService;

    }


    @RequestMapping("kakaohome")
    public String kakaohome(){



        return "kakaoLogin/index";
    }



    @RequestMapping("kakaologout")
    public String kakaoLogout(){



        return "kakaoLogin/logout";
    }

    @RequestMapping("kakaologin")

    public RedirectView kakaologin(@RequestParam("code") String code,
                                   RedirectAttributes ra,
                                   RedirectView rv,
                                   ModelAndView modelAndView) {

        // 1. 인가코드
//        System.out.println("code : " + code);

        // 2. 토큰 받기

        String access_Token = memberService.getAccessToken(code);
//        System.out.println("accessToken : " + access_Token);

        // 3.정보를 출력하는 메서드를 호출
//        System.out.println("test..");
        MemberVO memberVO = memberService.getUserInfo(access_Token);
        System.out.println(memberVO);

        // 해당 정보를 가지고 db에 같은 아이디가 있는지 검사하고 바로 로그인 시키고
        // 정보가 없다면 join으로 이동

        User result = userService.getUserById(memberVO.getEmail());
        System.out.println("카카오 로그인 첫 result :" + result);
        if (result == null) { // kakao email과 일치하는 계정정보가 db에 없다면.

//            redirect_url = String.format("redirect:/member/join?email=%s&gender=%s&profile_img=%s",memberVO.getEmail(),memberVO.getGender(),memberVO.getProfile_img());
            // member/join에서 parameter를 받은 후 join.html의 value값으로 설정해준다.
            rv.setUrl("/member/join");

            System.out.println("gi");
            ra.addFlashAttribute("email", memberVO.getEmail());
            ra.addFlashAttribute("gender", memberVO.getGender());
            ra.addFlashAttribute("profile_img", memberVO.getProfile_img());

        } else { // kakao 로그인이 성공하고 그 이메일값이 db에있다면 해당 아이디로 로그인을 시킨다.


            rv.setUrl("/member/loginPro");
            ra.addFlashAttribute("memberVO",memberVO); // redirect한 페이지에서 user정보를 얻기 위해 설정.

        }

        return rv;


    }


}
