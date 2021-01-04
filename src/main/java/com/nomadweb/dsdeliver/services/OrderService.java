package com.nomadweb.dsdeliver.services;

import com.nomadweb.dsdeliver.dtos.OrderDTO;
import com.nomadweb.dsdeliver.dtos.ProductDTO;
import com.nomadweb.dsdeliver.entities.Order;
import com.nomadweb.dsdeliver.entities.OrderStatus;
import com.nomadweb.dsdeliver.entities.Product;
import com.nomadweb.dsdeliver.repositories.OrderRepository;
import com.nomadweb.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findPendingOrdersWithProducts();

        return orderList.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order(null,
                orderDTO.getAddress(),
                orderDTO.getLatitude(),
                orderDTO.getLongitude(),
                Instant.now(),
                OrderStatus.PENDING);

        for (ProductDTO p : orderDTO.getProducts()) {
            Product product = productRepository.getOne(p.getId());

            order.getProducts().add(product);
        }

        order = orderRepository.save(order);

        return new OrderDTO(order);
    }
}
