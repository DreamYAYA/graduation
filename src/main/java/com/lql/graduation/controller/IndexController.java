package com.lql.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/sayHello")
    @ResponseBody
    public String SatHello(){

        return "SayHello";
    }



}
