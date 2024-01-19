package com.assignment.eGrocery.controller;

import com.assignment.eGrocery.dto.ItemDTO;
import com.assignment.eGrocery.dto.ModifyItemDTO;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.service.ItemService;
import jakarta.validation.Valid;
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

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    Environment environment;

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@Valid @RequestBody ItemDTO itemDTO) throws GroceryException {
        String message= itemService.addItem(itemDTO);
        return new ResponseEntity<>(environment.getProperty(message), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<String> removeItem(@PathVariable Integer id) throws GroceryException {
        String message = itemService.removeItem(id);
        return new ResponseEntity<>(environment.getProperty(message), HttpStatus.OK);

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateItem(@PathVariable Integer id,
                                             @RequestBody ModifyItemDTO itemDTO)
            throws GroceryException {
        String message = itemService.updateItem(id, itemDTO);
        return new ResponseEntity<>(environment.getProperty(message), HttpStatus.OK);
    }

    @GetMapping("/getItems/user")
    public ResponseEntity<List<ItemDTO>> getItemsForUser() throws GroceryException {
        List<ItemDTO> itemList = itemService.getItemsForUser();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/getItems/admin")
    public ResponseEntity<List<ItemDTO>> getItemsForAdmin() throws GroceryException {
        List<ItemDTO> itemList = itemService.getItemsForAdmin();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
}
