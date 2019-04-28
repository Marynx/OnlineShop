package com.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id;

    private double price;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany(mappedBy = "orders",fetch = FetchType.EAGER)
    private List<Item>items;

    public Order(){}

    public Order(double price, Date orderDate, String orderStatus) {
        this.price = price;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", status='" + orderStatus + '\'' +
                ", user=" + user.getLogin() +
                '}';
    }
}
