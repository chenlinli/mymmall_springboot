package com.my_mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class JspController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
