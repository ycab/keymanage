package com.example.keymanage.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanage.dao.CabinetRepository;
import com.example.keymanage.dao.GoodsManageRepository;
import com.example.keymanage.model.Cabinet;
import com.example.keymanage.model.GoodsManage;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date: 2018.8.18$
 */
@RestController
@RequestMapping("/rfid")
public class RFIDController {
    @Autowired
    private GoodsManageRepository goodsManageRepository;
    @Autowired
    private CabinetRepository cabinetRepository;
    @GetMapping("/getlist")
    public String getlist(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Cabinet cabinets=cabinetRepository.findById(Integer.parseInt(id)).orElse(null);
        String mac=cabinets.getMac();
        List<GoodsManage> list= goodsManageRepository.findByMac(mac);
        String json= JSON.toJSONString(list);
        return json;

    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request)
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {
            String id=request.getParameter("id");
            String location=request.getParameter("location");
            String rfid=request.getParameter("rfid");
            GoodsManage goodsManage=new GoodsManage();
            goodsManage.setLocation(location);
            goodsManage.setRfid(rfid);
            goodsManageRepository.save(goodsManage);
        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String location=request.getParameter("location");
            String rfid=request.getParameter("rfid");
            GoodsManage goodsManage=goodsManageRepository.findById(Integer.parseInt(id)).orElse(null);
            goodsManage.setLocation(location);
            goodsManage.setRfid(rfid);
            goodsManageRepository.save(goodsManage);
        }
        else if(oper.equals("del"))
        {
            String[] idArray=null;
            idArray=request.getParameter("id").split(",");
            for(int i=0;i<idArray.length;i++)
            {
                goodsManageRepository.deleteById(Integer.parseInt(idArray[i]));
            }
            //int id=Integer.parseInt(request.getParameter("id"));
            //peopleManageRepository.deleteById(id);
        }
        return "ok";
    }
}
