package com.example.keymanage.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    public String say(Model model, HttpSession session)
    {
        String authority=session.getAttribute("authority").toString();
        model.addAttribute("authority",authority);
        return "Home/index.html";
    }
    @GetMapping(value = "/login")
    public String login()
    {
        return "Home/login.html";
    }
    @GetMapping(value = "/password")
    public String password() { return "password/password.html"; }
    @GetMapping(value="/cabinet")
    public String cabinet() { return "Cabinet/cabinet.html";}
    @GetMapping(value="/rfid")
    public String rfid(Model model, @RequestParam(value = "id", required = false, defaultValue = "") String id) {
        String a=id;
        model.addAttribute("id", id);
        return "/RFID/rfid.html";
    }
    @GetMapping(value ="/usercodemanage")
    public String usercodemanage()
    {
        return "UserManage/usercodemanage.html";
    }
    @GetMapping(value ="/supermanage")
    public String supermanage()
    {
        return "SuperManage/supermanage.html";
    }
    @GetMapping(value ="/user")
    public String user()
    {
        return "UserManage/user.html";
    }
    @GetMapping(value ="/manager")
    public String manager()
    {
        return "UserManage/manager.html";
    }
    @GetMapping(value = "/userapply")
    public String userapply(){return "/UserManage/userapply.html";}
    @GetMapping(value="/cabinetforgoods")
    public String cabinetforgoods() { return "/Goods/cabinetforgoods.html"; }
    @GetMapping(value="/goods")
    public String goods(Model model, @RequestParam(value = "id", required = false, defaultValue = "") String id) {
        String a=id;
        model.addAttribute("id", id);
        return "/Goods/goods.html";
    }
    @GetMapping(value="/goodsapply")
    public String goodsapply()
    {return "/Goods/goodsapply.html";}
}
