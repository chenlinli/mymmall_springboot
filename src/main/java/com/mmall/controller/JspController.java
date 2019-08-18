package com.mmall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class JspController {

    @GetMapping("/index")
    public String index(){
        log.error("error:{}","error");
        log.info("info:{}","info");
        log.debug("info:{}","debug");
        log.warn("warn:{}","warn");
        return "index";
    }
}
