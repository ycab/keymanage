package com.example.keymanage.dao;

import com.example.keymanage.model.GoodsManage;
import com.example.keymanage.model.PeopleManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleManageRepository extends JpaRepository<PeopleManage,String> {
    public List<PeopleManage> findByAuthority(String authority);
    @Query(value = "select * from people_manage",nativeQuery = true)
    public List<PeopleManage> selectall();
    public List<PeopleManage> findByPhone(String phone);
}
