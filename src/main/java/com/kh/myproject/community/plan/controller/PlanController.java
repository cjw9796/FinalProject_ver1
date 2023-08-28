package com.kh.myproject.community.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class PlanController {

    //일정 리스트(일정 메인)
    @GetMapping("/community/plan") // c
    public String communityplan() {

          return "community/plan/plan";

    }

    //일정 글 정보
    @GetMapping("/community/plan/detail") // http://localhost:8070/community/plan/detail
    public String communityplandetail() {


        return "community/plan/plan_detail";
    }

    //일정 글 쓰기
    @GetMapping("/community/plan/write") // http://localhost:8070/community/plan/write
    public String communityplanwrite() {


        return "community/plan/plan_write";
    }

    //일정 글 쓰기 카카오맵 실험
    @GetMapping("/community/plan/write_kakaomap") // http://localhost:8070/community/plan/write_kakaomap
    public String communityplanwritekakao() {


        return "community/plan/plan_write_kakaomap";
    }

    /*
    //일정 글 정보
    @GetMapping("/community/plan/search") // http://localhost:8070/community/plan/search
    public String communityplansearch() {

        return "community/plan/plan_search";
    }
     */





}
