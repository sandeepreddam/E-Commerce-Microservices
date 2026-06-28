package com.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    @GetMapping("/customer")
    public String customer(){
        return "Customer Access";
    }

    @GetMapping("/store")
    public String store(){
        return "Store Access";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin Access";
    }
}
