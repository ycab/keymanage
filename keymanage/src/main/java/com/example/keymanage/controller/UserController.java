package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 人员管理$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @PostMapping("/usercodeedit")
    public String usercodemanage(HttpServletRequest request)
    {
        String result="";
       String phone=request.getParameter("phone");
       phone="18351930228";
       String oldpassword=request.getParameter("oldpassword");
       String newpassword=request.getParameter("newpassword");
       List<PeopleManage> peopleManages=peopleManageRepository.findByPhone(phone);
       if(peopleManages.size()==1)
       {
           String pwd=peopleManages.get(0).getPassword();
           if(pwd.equals(oldpassword))
           {
               peopleManages.get(0).setPassword(newpassword);
               peopleManageRepository.save(peopleManages.get(0));
               result="success";
           }
       }
       return result;
    }
    @GetMapping("/getlist")
    public String getlist()
    {
        List<PeopleManage> list=peopleManageRepository.findByAuthorityAndIscomfirm("用户","1");
        String json= JSON.toJSONString(list);
        return json;
    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request )
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String company=request.getParameter("company");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String authority="用户";
            PeopleManage user=new PeopleManage();
            user.setName(name);
            user.setPassword(password);
            user.setCompany(company);
            user.setDepartment(department);
            user.setPhone(phone);
            user.setAuthority(authority);
            peopleManageRepository.save(user);
        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String company=request.getParameter("company");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String authority="用户";
            PeopleManage user=new PeopleManage();
            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setPassword(password);
            user.setCompany(company);
            user.setDepartment(department);
            user.setPhone(phone);
            user.setAuthority(authority);
            peopleManageRepository.save(user);
        }
        else if(oper.equals("del"))
        {
            String[] idArray=null;
            idArray=request.getParameter("id").split(",");
            for(int i=0;i<idArray.length;i++)
            {
                peopleManageRepository.deleteById(Integer.parseInt(idArray[i]));
            }
            //int id=Integer.parseInt(request.getParameter("id"));
            //peopleManageRepository.deleteById(id);
        }
        return "ok";
    }
    @PostMapping("/pass")
    public String pass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        PeopleManage peopleManage=peopleManageRepository.findById(Integer.parseInt(id)).orElse(null);
        peopleManage.setIscomfirm("1");
        peopleManageRepository.save(peopleManage);
        return "ok";
    }
    @PostMapping("/notpass")
    public String notpass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        peopleManageRepository.deleteById(Integer.parseInt(id));
        return "ok";
    }
    @GetMapping("/getapplylist")
    public String getapplylist(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        List<PeopleManage> list=peopleManageRepository.findByAuthorityAndIscomfirm("用户","0");
        String json= JSON.toJSONString(list);
        return json;
    }

}
