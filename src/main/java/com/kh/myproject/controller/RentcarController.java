package com.kh.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentcarController {

@GetMapping("/rentcarMain")
    public String rentcarMain(){

    return "rentcarMain";

}


@GetMapping("/rentcarReserve")
    public String rentcarReserve(){

    return "rentcarReserve";
}


    @GetMapping("/rentcarChoice")
    public String rentcarChoice(){

        return "rentcarChoice";
    }


}
