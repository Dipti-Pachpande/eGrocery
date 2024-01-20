package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ModifyItemDTO;
import com.assignment.eGrocery.entity.Item;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.ItemRepository;
import com.assignment.eGrocery.service.ItemService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Override
    public String addItem(ItemDTO itemDTO) throws GroceryException {
        Item item = itemDTO.itemDTOToEntity();
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new GroceryException("ItemService.ITEM_ADDITION_FAILED");
        }
        return "ItemAPI.ITEM_ADDITION_SUCCESS";
    }

    @Override
    public String removeItem(Integer id) throws GroceryException {
        Item itemToDelete = findItem(id);
        itemToDelete.setIsDeleted(true);
        return "ItemAPI.ITEM_DELETION_SUCCESS";
    }

    @Override
    public String updateItem(Integer id, ModifyItemDTO itemDTO) throws GroceryException {
        Item itemToModify = findItem(id);
        if(itemDTO.getPrice() > 0.0) {
            itemToModify.setPrice(itemDTO.getPrice());
        }
        if(!StringUtils.isBlank(itemDTO.getName())) {
            itemToModify.setName(itemDTO.getName());
        }
        return "ItemAPI.ITEM_UPDATE_SUCCESS";
    }

    @Override
    public List<ItemDTO> getItemsForUser() throws GroceryException {
        List<Item> itemList = itemRepository.findByIsDeletedFalseAndAvailableQuantityGreaterThan(0);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        if(itemList.isEmpty()) {
            throw new GroceryException("ItemService.ITEM_NOT_PRESENT");
        } else {
            itemList.forEach((item) ->{
                ItemDTO itemDTO = item.itemEntityToDTO();
                itemDTOList.add(itemDTO);
            });
        }
         return itemDTOList;
    }

    @Override
    public List<ItemDTO> getItemsForAdmin() throws GroceryException {
        Iterable<Item> itemList = itemRepository.findByIsDeletedFalse();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        itemList.forEach((item) ->{
            ItemDTO itemDTO = item.itemEntityToDTO();
            itemDTOList.add(itemDTO);
        });
        if(itemDTOList.isEmpty()) {
            throw new GroceryException("ItemService.ITEM_NOT_PRESENT");
        }
        return itemDTOList;
    }

    private Item findItem(Integer id) throws GroceryException{

        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item = optionalItem.orElseThrow(() -> new GroceryException("ItemService.ITEM_NOT_FOUND"));
        if (item.getIsDeleted()) {
            throw new GroceryException("ItemService.ITEM_NOT_FOUND");
        }
        return item;
    }

 }
