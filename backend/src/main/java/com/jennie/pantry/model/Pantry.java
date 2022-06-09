package com.jennie.pantry.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

// initializes pantry object with all the getters and setters for the object

@Entity
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pantry_seq")
    @SequenceGenerator(name = "pantry_seq", sequenceName = "pantry_seq", allocationSize = 100)
    private long id;
    private String name;
    private boolean option; // true -> add, false -> remove
    private int quantity;
    private String category;

    public Pantry() {}

    public Pantry(String name, boolean option, int quantity, String category) {
        this.name = name;
        this.option = option;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Pantry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", option=" + option +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
