package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import com.example.keymanage.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 管理员管理$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @Autowired
    private PhoneService phoneService;
    @GetMapping("/getlist")
    public String getlist(HttpSession session)
    {
        String company=session.getAttribute("company").toString();
        List<PeopleManage> list=peopleManageRepository.findByCompanyAndAuthority(company,"管理员");
        String json= JSON.toJSONString(list);
        // String a="[{\"id\":\"1\",\"name\":\"管理员3\",\"password\":\"123\",\"department\":\"南京理工大学\",\"phone\":\"1216679910\",\"company\":\"南理工\",\"authority\":\"管理员\"}]";
        return json;
    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request,HttpSession session )
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {
            String name=request.getParameter("userName");
            String password=request.getParameter("password");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String company=session.getAttribute("company").toString();
            boolean phoneExist=phoneService.assertPhoneExist(phone);
            if(phoneExist==true)
            {
                return "phoneExists";
            }
            else
            {
                String authority="管理员";
                PeopleManage user=new PeopleManage();
                user.setUserName(name);
                user.setPassword(password);
                user.setCompany(company);
                user.setDepartment(department);
                user.setPhone(phone);
                user.setAuthority(authority);
                user.setIsConfirm("1");
                peopleManageRepository.save(user);
            }
        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String name=request.getParameter("userName");
            String password=request.getParameter("password");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String authority="管理员";
            boolean phoneExist=phoneService.assertPhoneExist(phone);
            PeopleManage user=peopleManageRepository.findById(Integer.parseInt(id)).orElse(null);
            if(phoneExist==true&&(phone.equals(user.getPhone())==false))
            {
                return "phoneExists";
            }
            else{
                user.setUserName(name);
                user.setPassword(password);
                user.setDepartment(department);
                user.setPhone(phone);
                peopleManageRepository.save(user);
            }

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
}
