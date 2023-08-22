package com.kh.myproject.controller;


import com.kh.myproject.model.dto.UserForm;
import com.kh.myproject.model.entity.User;
import com.kh.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("login")
    public String login() {


        return "login";
    }


    @PostMapping("loginPro")
    public ModelAndView loginPro(@RequestParam("user_id") String user_id,
                           @RequestParam("user_password") String user_password,
                                 ModelAndView modelAndView) {

        System.out.println(user_id);
        System.out.println(user_password);

        int result = userService.checkLogin(user_id,user_password);
        String msg = result == 1? String.format("반갑습니다 %s님",user_id) : "아이디 혹은 비밀번호를 확인해주세요.";
        modelAndView.addObject("msg",msg);
        modelAndView.setViewName("loginPro");


        return modelAndView;
    }


    //비밀번호 찾기
    @GetMapping("findPw")
    public String findPw() {

        return "findPw";
    }



    // join 회원가입


    @GetMapping("join")
    public String join() {

        return "join";
    }


    // 회원가입 완료
    @PostMapping("joinPro")
    public String joinPro(UserForm userForm, @RequestParam("user_year") int user_year,
                          @RequestParam("user_month") int user_month,
                          @RequestParam("user_day") int user_day,
                          ModelAndView modelAndView) {



        System.out.println("user의 값 :" + userForm);
        // year, month ,day 값을 date타입으로 포매팅

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date.setYear(user_year - 1900);
        date.setMonth(user_month - 1);
        date.setDate(user_day);
        String formatted_date = sdf.format(date);
        System.out.println("formatted_ddate = " + formatted_date);
        System.out.println("date = " + date);
        userForm.setUser_date(date);
        User user = userForm.toEntity();

        int join_result = userService.joinUser(user); // 회원가입 결과.

        String msg = join_result == 1 ? "회원가입이 완료됐습니다" : "회원가입에 실패했습니다.(관리자 문의)";

//        modelAndView.setViewName("index");
//        modelAndView.addObject("msg",msg);


//        return "redirect:/joinSuccess?msg="+msg;
        return "index";
    }


    // 로그아웃
    @GetMapping("logout")
    public String logout() {


        return "logout";
    }


}
