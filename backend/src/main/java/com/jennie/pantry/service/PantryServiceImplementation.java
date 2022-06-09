package com.jennie.pantry.service;

import com.jennie.pantry.model.Pantry;
import com.jennie.pantry.repo.PantryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

// uses multiple repositories to deal with more business things, whereas the repository deals with more technical things with the database

@Service("pantryService")
public class PantryServiceImplementation implements PantryService{
    public List<Pantry> allItems = new ArrayList<>();

    @Autowired
    private PantryRepo pantryRepo;

    @Override
    public Pantry savePantry(Pantry pantry) {
        return pantryRepo.save(pantry);
    }

    @Override
    public List<Pantry> getAllItems() {
        return pantryRepo.findAll();
    }

    @Override
    public Pantry findOneByName(String name) {
        return pantryRepo.findOneByName(name);
    }

    @Override
    public void setItemInfoByName(int quantity, String name) {
        pantryRepo.setItemInfoByName(quantity, name);
    }

    @Override
    public void delete(Pantry pantry) {
        pantryRepo.delete(pantry);
    }

    @Override
    public void deleteAll() {
        pantryRepo.deleteAll();
    }

    @Override
    public List<Pantry> findByOrderByNameAsc() {
        return pantryRepo.findByOrderByNameAsc();
    }

    @Override
    public List<Pantry> findByOrderByQuantityAsc() {
        return pantryRepo.findByOrderByQuantityAsc();
    }

    @Override
    public List<Pantry> findByOrderByQuantityDesc() {
        return pantryRepo.findByOrderByQuantityDesc();
    }

}