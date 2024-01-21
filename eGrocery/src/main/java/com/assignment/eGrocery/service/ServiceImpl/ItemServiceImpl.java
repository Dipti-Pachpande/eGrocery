package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.common.Operation;
import com.assignment.eGrocery.dto.InventoryDTO;
import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ItemResponseDTO;
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
    public ItemResponseDTO addItem(ItemDTO itemDTO) throws GroceryException {
        Item item = itemDTO.itemDTOToEntity();
        try {
            item = itemRepository.save(item);
        } catch (Exception e) {
            throw new GroceryException("ItemService.ITEM_ADDITION_FAILED");
        }
        return ItemResponseDTO.buildItemResponseDTO(item);
    }

    @Override
    public String removeItem(Integer id) throws GroceryException {
        Item itemToDelete = findItem(id);
        itemToDelete.setIsDeleted(true);
        return "ItemAPI.ITEM_DELETION_SUCCESS";
    }

    @Override
    public ItemResponseDTO updateItem(Integer id, ModifyItemDTO itemDTO) throws GroceryException {
        Item itemToModify = findItem(id);
        if(itemDTO.getPrice() > 0.0) {
            itemToModify.setPrice(itemDTO.getPrice());
        }
        if(!StringUtils.isBlank(itemDTO.getName())) {
            itemToModify.setName(itemDTO.getName());
        }
        return ItemResponseDTO.buildItemResponseDTO(itemToModify);
    }

    @Override
    public List<ItemResponseDTO> getItemsForUser() throws GroceryException {
        List<Item> itemList = itemRepository.findByIsDeletedFalseAndAvailableQuantityGreaterThan(0);
        //List<ItemDTO> itemDTOList = new ArrayList<>();
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        if(itemList.isEmpty()) {
            throw new GroceryException("ItemService.ITEMS_NOT_FOUND");
        } else {
            itemList.forEach((item) ->{
                ItemResponseDTO itemResponseDTO = ItemResponseDTO.buildItemResponseDTO(item);
                itemResponseDTOList.add(itemResponseDTO);
            });
        }
         return itemResponseDTOList;
    }

    @Override
    public List<ItemResponseDTO> getItemsForAdmin() throws GroceryException {
        Iterable<Item> itemList = itemRepository.findByIsDeletedFalse();
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        itemList.forEach((item) ->{
            ItemResponseDTO itemResponseDTO = ItemResponseDTO.buildItemResponseDTO(item);
            itemResponseDTOList.add(itemResponseDTO);
        });
        if(itemResponseDTOList.isEmpty()) {
            throw new GroceryException("ItemService.ITEMS_NOT_FOUND");
        }
        return itemResponseDTOList;
    }

    @Override
    public ItemResponseDTO updateInventory(InventoryDTO inventoryDTO, int itemId) throws GroceryException {
        Item item = findItem(itemId);
        int updatedQuantity = getUpdatedQuantity(Operation.valueOf(inventoryDTO.getOperation()),
                item.getAvailableQuantity(),
                inventoryDTO.getQuantity());
        item.setAvailableQuantity(updatedQuantity);
        return ItemResponseDTO.buildItemResponseDTO(item);
    }

    private Item findItem(Integer id) throws GroceryException{

        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item = optionalItem.orElseThrow(() -> new GroceryException("ItemService.ITEM_NOT_FOUND"));
        if (item.getIsDeleted()) {
            throw new GroceryException("ItemService.ITEM_NOT_FOUND");
        }
        return item;
    }

    private int getUpdatedQuantity(Operation operationType, int currentValue, int updateBy) {
        switch(operationType) {

            case ADD -> {
                return currentValue + updateBy;
            }
            case REMOVE -> {
                return currentValue - updateBy;
            }
            case UPDATE -> {
                return updateBy;
            }
            default -> throw new IllegalStateException("Unexpected value: " + operationType);
        }

    }

 }
