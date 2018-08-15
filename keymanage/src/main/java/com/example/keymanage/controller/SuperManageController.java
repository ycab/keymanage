package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * @Description: 超级管理员$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/supermanage")
public class SuperManageController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @GetMapping("/getlist")
    public String getlist()
    {
        List<PeopleManage> list=peopleManageRepository.findAll();
        String json=JSON.toJSONString(list);
       // String a="[{\"id\":\"1\",\"name\":\"管理员3\",\"password\":\"123\",\"department\":\"南京理工大学\",\"phone\":\"1216679910\",\"company\":\"南理工\",\"authority\":\"管理员\"}]";
       return json;
    }
}
