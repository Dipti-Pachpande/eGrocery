package com.assignment.eGrocery.controller;

import com.assignment.eGrocery.common.RoleType;
import com.assignment.eGrocery.dto.AuthDTO;
import com.assignment.eGrocery.dto.InventoryDTO;
import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ItemResponseDTO;
import com.assignment.eGrocery.dto.ModifyItemDTO;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.service.AuthorisationService;
import com.assignment.eGrocery.service.ItemService;
import com.assignment.eGrocery.service.UserService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    Environment environment;

    @Autowired
    AuthorisationService authorisationService;

    @Autowired
    UserService userService;

    @PostMapping("/addItem")
    public ResponseEntity<ItemResponseDTO> addItem(@Valid @RequestBody ItemDTO itemDTO) throws GroceryException {
        authorisationService.authorise(itemDTO.getAuth().getUserId(), RoleType.ADMIN.name());
        ItemResponseDTO itemResponseDTO = itemService.addItem(itemDTO);
        return new ResponseEntity<>(itemResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<String> removeItem(@PathVariable Integer id, @Valid @RequestBody AuthDTO auth)
            throws GroceryException {
        authorisationService.authorise(auth.getUserId(), RoleType.ADMIN.name());
        String message  = itemService.removeItem(id);
        return new ResponseEntity<>("Item Id : "+ id +" "+ environment.getProperty(message), HttpStatus.OK);

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable Integer id,
                                             @Valid @RequestBody ModifyItemDTO itemDTO)
            throws GroceryException {
        authorisationService.authorise(itemDTO.getAuth().getUserId(), RoleType.ADMIN.name());
        ItemResponseDTO itemResponseDTO = itemService.updateItem(id, itemDTO);
        return new ResponseEntity<>(itemResponseDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}/updateInventory")
    public ResponseEntity<ItemResponseDTO> updateInventory(@PathVariable Integer id,
                                                  @Valid @RequestBody InventoryDTO inventoryDTO)
            throws GroceryException {
        authorisationService.authorise(inventoryDTO.getAuth().getUserId(), RoleType.ADMIN.name());
        ItemResponseDTO itemResponseDTO = itemService.updateInventory(inventoryDTO, id);
        return new ResponseEntity<>(itemResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getItems")
    public ResponseEntity<List<ItemResponseDTO>> getItemsForUser(@Valid @RequestBody AuthDTO auth)
            throws GroceryException {
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        String role = userService.getRole(auth.getUserId());
        if(StringUtils.equals(role, RoleType.ADMIN.name())) {
            itemResponseDTOList = itemService.getItemsForAdmin();
        }
        if(StringUtils.equals(role, RoleType.USER.name())) {
            itemResponseDTOList = itemService.getItemsForUser();
        }
        return new ResponseEntity<>(itemResponseDTOList, HttpStatus.OK);
    }
}
