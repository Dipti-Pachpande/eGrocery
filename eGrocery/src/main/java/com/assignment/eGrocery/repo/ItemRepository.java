package com.assignment.eGrocery.repo;

import com.assignment.eGrocery.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    public List<Item> findByIsDeletedFalseAndAvailableQuantityGreaterThan(int quantity);

    public List<Item> findByIsDeletedFalse();
}
