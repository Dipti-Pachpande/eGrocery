package com.assignment.eGrocery.entity;

import com.assignment.eGrocery.dto.ItemDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "Items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int availableQuantity;
    private Boolean isDeleted = false;
    public ItemDTO itemEntityToDTO() {
        ItemDTO item = new ItemDTO();
        item.setId(this.id);
        item.setName(this.name);
        item.setPrice(this.price);
        item.setAvailableQuantity(this.availableQuantity);
        return item;
    }

}
