package com.jennie.pantry.controller;

import com.jennie.pantry.model.Pantry;
import com.jennie.pantry.service.PantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.List;

// how to deal with inputs

@CrossOrigin
@RestController
@RequestMapping("/pantry")
public class PantryController {

    @Autowired
    private PantryService pantryService;

    @PostMapping("/add")
    public void add(@RequestBody Pantry pantry) throws Exception {
        try {
            Pantry originalItem = pantryService.findOneByName(pantry.getName());
            if (originalItem == null) {
                if (!pantry.isOption()) {
                    throw new Exception("Can't remove something you never had");
                }
                pantryService.savePantry(pantry);
            }
            else {
                int originalQuantity = originalItem.getQuantity();
                if (!pantry.isOption() && originalQuantity < pantry.getQuantity()) {
                    throw new Exception("Can't remove more than you have");
                }
                else if (!pantry.isOption() && originalQuantity == pantry.getQuantity()) {
                    delete(originalItem);
                }
                int newQuantity;
                if (!pantry.isOption()) {
                    newQuantity = originalQuantity - pantry.getQuantity();
                }
                else {
                    newQuantity = originalQuantity + pantry.getQuantity();
                }
                pantryService.setItemInfoByName(newQuantity, originalItem.getName());
            }
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("/addExamples")
    public void addExamples() throws Exception {
        Pantry example1 = new Pantry("Apple", true, 12, "Fruit");
        Pantry example2 = new Pantry("Rice", true, 20, "Other");
        Pantry example3 = new Pantry("Chicken", true, 4, "Meat");
        add(example1);
        add(example2);
        add(example3);
    }

    @GetMapping("/getAll")
    public List<Pantry> getAllItems() {
        return pantryService.getAllItems();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Pantry pantry) {
        pantryService.delete(pantry);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        pantryService.deleteAll();
    }

    @GetMapping("/sortNameAsc")
    public List<Pantry> findByOrderByNameAsc() {
        return pantryService.findByOrderByNameAsc();
    }

    @GetMapping("/sortQuantityAsc")
    public List<Pantry> findByOrderByQuantityAsc() {
        return pantryService.findByOrderByQuantityAsc();
    }

    @GetMapping("/sortQuantityDesc")
    public List<Pantry> findByOrderByQuantityDesc() {
        return pantryService.findByOrderByQuantityDesc();
    }
}