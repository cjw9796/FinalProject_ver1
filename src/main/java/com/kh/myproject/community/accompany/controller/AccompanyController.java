package com.kh.myproject.community.accompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class AccompanyController {

    //여행커뮤니티 홈(메인페이지 병합 전 삭제)
    @GetMapping("/community/home") //http://localhost:8070/community/home
    public String communityhome() {

        System.out.println("donghangMain2 테스트..");

        return "community/home";
    }


    //동행 리스트(동행 메인)
    @GetMapping("/community/accompany") // http://localhost:8070/community/accompany
    public String communityaccompany() {


        return "community/accompany/accompany";
    }

    //동행 글 정보
    @GetMapping("/community/accompany/detail") // http://localhost:8070/community/accompany/detail
    public String communityaccompanydetail() {


        return "community/accompany/accompany_detail";
    }

    //동행 글 쓰기
    @GetMapping("/community/accompany/write") // http://localhost:8070/community/accompany/write
    public String communityaccompanywrite() {


        return "community/accompany/accompany_write";
    }



}
