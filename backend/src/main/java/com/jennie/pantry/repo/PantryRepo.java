package com.jennie.pantry.repo;

import com.jennie.pantry.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

// accesses database

@Repository
@Transactional
public interface PantryRepo extends JpaRepository <Pantry, Long>{
    Pantry findOneByName(String name);
    List<Pantry> findByOrderByNameAsc();
    List<Pantry> findByOrderByQuantityAsc();
    List<Pantry> findByOrderByQuantityDesc();
    @Modifying
    @Query("UPDATE Pantry p SET p.quantity = ?1 WHERE p.name = ?2")
    void setItemInfoByName(int quantity, String name);
}