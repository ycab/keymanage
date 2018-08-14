package com.example.keymanage.dao;

import com.example.keymanage.model.GoodsManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsManageRepository extends JpaRepository<GoodsManage,Integer>{
    public List<GoodsManage> findByid(Integer age);


}
