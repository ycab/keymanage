package com.example.keymanage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * @Description: 物品管理$
 * @Param: $
 * @return: $
 * @Author: yu peng
 * @date: 2018.8.13$
 */
@Entity
public class GoodsManage {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;         //物品名称
    private String mac;           //物品mac
    private Integer location;     //箱格位置
    private String needApproved;//是否需要审批
    private String rfid;         //rfid
    private String userName;     //当前使用者姓名
    private String userPhone;    //当前使用者电话
    private String goodsStatus; //物品状态 0为可借 1为成功申请还未使用 2为使用中
    private String doorStatus;  //箱格状态 0为门关闭 1为门打开

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getNeedApproved() {
        return needApproved;
    }

    public void setNeedApproved(String needApproved) {
        this.needApproved = needApproved;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }
}
