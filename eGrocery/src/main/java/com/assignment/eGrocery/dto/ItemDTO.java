package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {
    private int id;

    @NotNull(message = "Name Required")
    private String name;

    @NotNull(message = "Price Required")
    private double price;

    @NotNull(message = "Available Quantity Required")
    private int availableQuantity;

    @NotNull(message = "Auth Details Required")
    private AuthDTO auth;

    public Item itemDTOToEntity() {
        Item item = new Item();
        item.setId(this.id);
        item.setName(this.name);
        item.setPrice(this.price);
        item.setAvailableQuantity(this.availableQuantity);
        return item;
    }
}
