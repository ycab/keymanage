package com.example.keymanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: 2018.7.30$
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping(value = "/index")
    public String say()
    {
        return "Home/index.html";
    }
    @GetMapping(value = "/login")
    public String login()
    {
        return "Home/login.html";
    }
    @GetMapping(value = "/supermanage")
    public String supermanage() { return "SuperManage/supermanage.html"; }
}
