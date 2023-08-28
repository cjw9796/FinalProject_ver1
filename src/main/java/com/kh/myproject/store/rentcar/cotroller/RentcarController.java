package com.kh.myproject.store.rentcar.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class RentcarController {




@GetMapping("/store/rentcar/rentcarMain")
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
