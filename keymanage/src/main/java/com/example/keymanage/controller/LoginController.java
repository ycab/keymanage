package com.example.keymanage.controller;

import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    /**
     * @Description: 验证登录信息$
     * @Param: $
     * @return: 正确返回ok，错误返回error$
     * @Author: yu peng
     * @date: 2018.8.14$
     */
    @PostMapping(value="/checkinfo")
    public String Login(HttpServletRequest request )
    {
        String phone=request.getParameter("username");
        String pwd=request.getParameter("pwd");
        List<PeopleManage> peopleList=peopleManageRepository.findByPhone(phone);
        if(peopleList.size()==1)
        {
            String pwdFromDatabase=peopleList.get(0).getPassword();
            if(pwd.equals(pwdFromDatabase))
            {
                return "ok";
            }
            else{
                return "error";
            }
        }
        else {
            return "error";
        }
    }
}
