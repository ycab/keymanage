package com.example.keymanage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: yu peng
 * @date: 2018.7.17
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping(value = "/say")
    public String say()
    {
        return "login.html";
    }
}
