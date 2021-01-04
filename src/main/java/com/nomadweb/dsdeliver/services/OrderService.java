package com.nomadweb.dsdeliver.services;

import com.nomadweb.dsdeliver.dtos.OrderDTO;
import com.nomadweb.dsdeliver.entities.Order;
import com.nomadweb.dsdeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findPendingOrdersWithProducts();

        return orderList.stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}