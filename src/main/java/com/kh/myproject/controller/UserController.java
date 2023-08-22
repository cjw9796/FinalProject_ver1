package com.kh.myproject.controller;


import com.kh.myproject.model.entity.User;
import com.kh.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "/community/home";
    }

    @GetMapping("/community/home")
    public String communityHome() {
        return "/community/home";
    }
    @GetMapping("/store/home")
    public String storeHome() {
        return "/store/home";
    }
    @GetMapping("/memer/login")
    public String login() {
        System.out.println("userContoller login 메서드 실행");
        List<User> userList = userService.index();

        return "/member/login";
    }

    @PostMapping("/loginPro")
    public String loginPro() {


        return "login";
    }

    // join 회원가입
    @GetMapping("/member/join")
    public String join() {

        return "/member/join";
    }

    // 회원가입 완료
    @PostMapping("/joinPro")
    public String joinPro(User user, @RequestParam("user_year") int user_year,
                          @RequestParam("user_month") int user_month,
                          @RequestParam("user_day") int user_day) {


        System.out.println("user의 값 :" + user);
        System.out.println(user_year);
        System.out.println(user_month);
        System.out.println(user_day);

        userService.joinUser(user);


        return "joinPro";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {


        return "logout";
    }

    @GetMapping("/member/mypage")
    public String mypage() {

        return "/member/mypage";
    }

}
