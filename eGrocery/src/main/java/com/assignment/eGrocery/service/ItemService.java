package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.InventoryDTO;
import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ItemResponseDTO;
import com.assignment.eGrocery.dto.ModifyItemDTO;
import com.assignment.eGrocery.entity.Item;
import com.assignment.eGrocery.exception.GroceryException;

import java.util.List;

public interface ItemService {
    ItemResponseDTO addItem(ItemDTO itemDTO) throws GroceryException;
    String removeItem(Integer id) throws GroceryException;

    ItemResponseDTO updateItem(Integer id, ModifyItemDTO itemDTO) throws GroceryException;

    List<ItemResponseDTO> getItemsForUser() throws GroceryException;

    List<ItemResponseDTO> getItemsForAdmin() throws GroceryException;

    ItemResponseDTO updateInventory(InventoryDTO inventoryDTO, int itemId) throws GroceryException;
}
