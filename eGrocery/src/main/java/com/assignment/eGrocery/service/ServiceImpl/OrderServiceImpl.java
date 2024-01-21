package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.dto.OrderDTO;
import com.assignment.eGrocery.dto.OrderItemDTO;
import com.assignment.eGrocery.dto.OrderResponseDTO;
import com.assignment.eGrocery.entity.Item;
import com.assignment.eGrocery.entity.Order;
import com.assignment.eGrocery.entity.OrderItem;
import com.assignment.eGrocery.entity.User;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.ItemRepository;
import com.assignment.eGrocery.repo.OrderRepository;
import com.assignment.eGrocery.repo.UsersRepository;
import com.assignment.eGrocery.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) throws  GroceryException{
        Optional<User> optionalUser = usersRepository.findById(orderDTO.getUserId());
        User user = optionalUser.orElseThrow(() -> new GroceryException("ABC"));

        List<OrderItemDTO> orderItemDTOS = orderDTO.getOrderItems();
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        AtomicReference<Double> totalCost = new AtomicReference<>(0.0);
        orderItemDTOS.forEach((orderItemDTO) -> {
            try {
                OrderItem orderItem = new OrderItem();
                Optional<Item> optionalItem = itemRepository.findById(orderItemDTO.getItemId());
                Item item = optionalItem.orElseThrow(() -> new GroceryException("ABC"));
                if(item.getAvailableQuantity()<orderItemDTO.getQuantity()) {
                    throw new GroceryException("items quantity not availble");
                }
                Double cost = item.getPrice() * orderItemDTO.getQuantity();
                totalCost.updateAndGet(v -> v + cost);
                orderItem.setCost(cost);
                orderItem.setQuantity(orderItemDTO.getQuantity());
                orderItem.setItem(item);
                item.setAvailableQuantity(item.getAvailableQuantity()- orderItemDTO.getQuantity());
                orderItems.add(orderItem);
            } catch (GroceryException e) {
                throw new RuntimeException(e);
            }
        });

        order.setUser(user);
        order.setTotalCost(totalCost.get());
        order.setOrderItems(orderItems);
        order = orderRepository.save(order);

        return OrderResponseDTO.buildOrderResponseDTO(order);

    }
}
