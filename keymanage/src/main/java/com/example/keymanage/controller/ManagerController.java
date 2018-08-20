package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/getlist")
    public String getlist()
    {
        List<PeopleManage> list=peopleManageRepository.findByAuthority("管理员");
        String json= JSON.toJSONString(list);
        // String a="[{\"id\":\"1\",\"name\":\"管理员3\",\"password\":\"123\",\"department\":\"南京理工大学\",\"phone\":\"1216679910\",\"company\":\"南理工\",\"authority\":\"管理员\"}]";
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
            String authority="管理员";
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
            String authority="管理员";
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
}