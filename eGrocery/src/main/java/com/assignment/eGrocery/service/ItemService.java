package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ModifyItemDTO;
import com.assignment.eGrocery.entity.Item;
import com.assignment.eGrocery.exception.GroceryException;

import java.util.List;

public interface ItemService {
    String addItem(ItemDTO itemDTO) throws GroceryException;
    String removeItem(Integer id) throws GroceryException;

    String updateItem(Integer id, ModifyItemDTO itemDTO) throws GroceryException;

    List<ItemDTO> getItemsForUser() throws GroceryException;

    List<ItemDTO> getItemsForAdmin() throws GroceryException;
}
