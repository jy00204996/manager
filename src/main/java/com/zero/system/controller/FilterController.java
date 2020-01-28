package com.zero.system.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilterController {

    @RequestMapping("/")
    public String index() {
        return "manager/login";
    }
}
