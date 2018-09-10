package com.example.keymanage.dao;

import com.example.keymanage.model.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinetRepository extends JpaRepository<Cabinet,Integer> {
   public List<Cabinet> findByCompany(String company);
}
