package com.namnd.bookingbe.controller;


import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

public class TestController {

    @GetMapping
    public String welcome(){
        return "Hello google";
    }

    @GetMapping("/user")
    public Principal user(Principal principal){
        return principal;
    }
}
