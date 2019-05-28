package com.shop.repository;

import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    @Query(value="SELECT order_item.* FROM order_item join `order` " +
            "on order_item.id_order=`order`.id_order where user_id=:id",
    nativeQuery = true)
    List<OrderItem> findByUser(@Param("id") Long id);

    @Query(value = "SELECT order_item.* FROM order_item join `order` on order_item.id_order=`order`.id_order" +
            " where order_status='Nie potwierdzono' and user_id=:id",
    nativeQuery = true)
    List<OrderItem> findByCart(@Param("id") Long id);
}
