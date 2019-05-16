package com.shop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private Long id;
    @Column(name = "item_quantity")
    private int itemQuantity;
    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    public OrderItem(){}

    public OrderItem(int itemQuantity){
        this.itemQuantity=itemQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemQuantity=" + itemQuantity +
                ", order=" + order +
                ", item=" + item +
                '}';
    }
}

