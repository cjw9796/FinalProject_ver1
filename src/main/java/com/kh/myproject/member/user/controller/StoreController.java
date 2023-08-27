package com.kh.myproject.member.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class StoreController {




    @RequestMapping("/store/area")
    public String area(){


        return "store/area";
    }


    @RequestMapping("/store/plane")
    public String plane(){


        return "/store/plane";
    }

    @RequestMapping("/store/rental")
    public String rental(){

        return "/store/rental";
    }

}
