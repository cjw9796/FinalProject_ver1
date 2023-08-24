package com.kh.myproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {


    @RequestMapping("/community/accompany")
    public String accompnay(){


        return "community/accompany";
    }


    @RequestMapping("/community/plan")
    public String plan(){


        return "community/plan";
    }
}
