package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.CabinetRepository;
import com.example.keymanage.dao.GoodsManageRepository;
import com.example.keymanage.dao.PeopleManageRepository;
import com.example.keymanage.model.Cabinet;
import com.example.keymanage.model.GoodsManage;
import com.example.keymanage.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: 2018.8.15$
 */
@RestController
@RequestMapping("/cabinet")
public class CabinetController {
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    private GoodsManageRepository goodsManageRepository;
    @GetMapping("/getlist")
    public String getlist()
    {
        List<Cabinet> list=cabinetRepository.findAll();
        String json= JSON.toJSONString(list);
        return json;
    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request )
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {
            String mac=request.getParameter("mac");
            String company=request.getParameter("company");
            String name=request.getParameter("name");
            String location=request.getParameter("location");
            String numofdoor=request.getParameter("numofdoor");
            Cabinet cabinet=new Cabinet();
            cabinet.setMac(mac);
            //cabinet.setCompany(company);
            cabinet.setName(name);
            cabinet.setLocation(location);
            cabinet.setNumofdoor(Integer.parseInt(numofdoor));
            cabinetRepository.save(cabinet);
            for(int i=0;i<Integer.parseInt(numofdoor);i++)
            {
                GoodsManage goodsManage=new GoodsManage();
                goodsManage.setMac(mac);
                goodsManage.setLocation(String.valueOf(i));
                goodsManageRepository.save(goodsManage);
            }

        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String mac=request.getParameter("mac");
            String company=request.getParameter("company");
            String name=request.getParameter("name");
            String location=request.getParameter("location");
            String numofdoor=request.getParameter("numofdoor");

            Cabinet cabinetfromdatabase=cabinetRepository.findById(Integer.parseInt(id)).orElse(null);
            String  macfromdatabase=cabinetfromdatabase.getMac();
            Cabinet cabinet=new Cabinet();
            cabinet.setId(Integer.parseInt(id)); //该行代码是为了实现修改数据，去掉后会变成新增数据
            cabinet.setMac(mac);
            cabinet.setCompany(company);
            cabinet.setName(name);
            cabinet.setLocation(location);
            cabinet.setNumofdoor(Integer.parseInt(numofdoor));
            cabinetRepository.save(cabinet);
            int b=0;
            b++;
            if(macfromdatabase.equals(mac))
            {

            }
            else
            {
                List<GoodsManage> list= goodsManageRepository.findByMac(macfromdatabase);
                for(int i=0;i<list.size();i++)
                {
                    list.get(i).setMac(mac);
                    goodsManageRepository.save(list.get(i));
                }
            }
        }
        else if(oper.equals("del"))
        {
            String[] idArray=null;
            idArray=request.getParameter("id").split(",");
            for(int i=0;i<idArray.length;i++)
            {
                Cabinet cabinet=cabinetRepository.findById(Integer.parseInt(idArray[i])).orElse(null);
                cabinetRepository.deleteById(Integer.parseInt(idArray[i]));
                String mac=cabinet.getMac();
                List<GoodsManage> list= goodsManageRepository.findByMac(mac);
                for(int j=0;j<list.size();j++)
                {
                    goodsManageRepository.deleteById(list.get(j).getId());
                }
            }
            //int id=Integer.parseInt(request.getParameter("id"));
            //peopleManageRepository.deleteById(id);
        }
        return "ok";
    }
}