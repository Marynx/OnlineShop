package com.shop.repository;

import com.shop.model.Order;
import com.shop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    List<Order> findByUser(User user);
    Order findByOrderStatusAndUser(String orderStatus,User user);
}
