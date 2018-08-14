package com.example.keymanage.dao;

import com.example.keymanage.model.GoodsManage;
import com.example.keymanage.model.PeopleManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleManageRepository extends JpaRepository<PeopleManage,Integer> {
    public List<PeopleManage> findByPhoneAndPassword(String phone,String password);
    @Query(value = "select * from people_manage",nativeQuery = true)
    List<PeopleManage> selectall();
}
