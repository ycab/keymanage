package com.example.keymanage.controller;

import com.example.keymanage.Util.TokenThread;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import com.example.keymanage.service.TemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @Autowired
    TemplateMsgService templateMsgService;
    /**
     * @Description: 验证登录信息$
     * @Param: $
     * @return: 正确返回ok，错误返回error$
     * @Author: yu peng
     * @date: 2018.8.14$
     */
    @PostMapping(value="/checkinfo")
    public String Login(HttpServletRequest request, HttpSession session, Model model)
    {
        String phone=request.getParameter("username");
        String pwd=request.getParameter("pwd");
        List<PeopleManage> peopleManageList=peopleManageRepository.findByPhoneAndPasswordAndIsConfirm(phone,pwd,"1");
        if(peopleManageList.size()==1)
        {
            PeopleManage peopleManage=peopleManageList.get(0);
            session.setAttribute("phone",phone);
            session.setAttribute("authority",peopleManage.getAuthority());
            session.setAttribute("name",peopleManage.getUserName());
            session.setAttribute("company",peopleManage.getCompany());
            model.addAttribute("authority",peopleManage.getAuthority());
            String token= TokenThread.accessToken.getAccess_token();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            templateMsgService.WeChatTemplateMsgService("olggXvxki3v2FA9KAh3YFf4UG9eY","通过",df.format(new Date()));
           return "ok";
        }
        else {
            return "error";
        }
    }
}
