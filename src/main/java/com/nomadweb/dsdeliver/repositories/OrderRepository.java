package com.nomadweb.dsdeliver.repositories;

import com.nomadweb.dsdeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
