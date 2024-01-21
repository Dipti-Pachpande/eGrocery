package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponseDTO {
    private int id;
    private String name;
    private double price;
    private int availableQuantity;

    public static ItemResponseDTO buildItemResponseDTO(Item item){
        return ItemResponseDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .availableQuantity(item.getAvailableQuantity())
                .build();
    }
}
