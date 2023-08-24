package com.kh.myproject.controller;


import com.kh.myproject.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// 유저 세션을 가지고 와야한다.
@SessionAttributes("user")
public class CommunityController {



    @GetMapping("community/home")
    public String communityHome() {
        return "community/home";
    }

    @RequestMapping("/community/accompany")
    public String accompnay(){


        return "community/accompany";
    }


    @RequestMapping("/community/plan")
    public String plan(){


        return "community/plan";
    }
}
