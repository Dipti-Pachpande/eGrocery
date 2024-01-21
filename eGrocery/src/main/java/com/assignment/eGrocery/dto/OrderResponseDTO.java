package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class OrderResponseDTO {
    private Integer orderId;
    private Double totalCost;
    private List<OrderItemResponseDTO> orderItems;

    public static OrderResponseDTO buildOrderResponseDTO(Order order) {
        List<OrderItemResponseDTO> orderItemResponseList = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            OrderItemResponseDTO orderItemResponseDTO = OrderItemResponseDTO
                    .builder()
                    .id(orderItem.getId())
                    .itemId(orderItem.getItem().getId())
                    .name(orderItem.getItem().getName())
                    .cost(orderItem.getCost())
                    .quantity(orderItem.getQuantity())
                    .build();
            orderItemResponseList.add(orderItemResponseDTO);
        });

        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .totalCost(order.getTotalCost())
                .orderItems(orderItemResponseList)
                .build();
    }

}
