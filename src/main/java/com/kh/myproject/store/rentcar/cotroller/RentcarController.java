package com.kh.myproject.store.rentcar.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentcarController {

@GetMapping("/rentcarMain")
    public String rentcarMain(){

    return "store/rentcar/rentcarMain";

}


@GetMapping("/rentcarReserve")
    public String rentcarReserve(){

    return "store/rentcar/rentcarReserve";
}


    @GetMapping("/rentcarChoice")
    public String rentcarChoice(){

        return "store/rentcar/rentcarChoice";
    }


}
