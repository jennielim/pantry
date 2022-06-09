package com.jennie.pantry.service;

import com.jennie.pantry.model.Pantry;

import java.util.List;

// interface to control behavior

public interface PantryService {
    Pantry savePantry(Pantry pantry);
    List<Pantry> getAllItems();
    Pantry findOneByName(String name);
    void setItemInfoByName(int quantity, String name);
    void delete(Pantry pantry);
    void deleteAll();
    List<Pantry> findByOrderByNameAsc();
    List<Pantry> findByOrderByQuantityAsc();
    List<Pantry> findByOrderByQuantityDesc();
}