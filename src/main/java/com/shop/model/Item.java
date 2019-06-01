package com.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;
    private double price;
    private double rating;
    private String version;
    @Column(name = "description")
    private String desc;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER)
    private Set<Vote>votes=new HashSet<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "order_item",
//            joinColumns = {@JoinColumn(name="item_id", referencedColumnName="id_item")},
//            inverseJoinColumns = {@JoinColumn(name="order_id", referencedColumnName="id_order")})
//    private Set<Order> orders;

    @OneToMany(mappedBy = "item")
    Set<OrderItem> orderItems=new HashSet<>();
    @ManyToMany(mappedBy = "items",fetch = FetchType.EAGER)
    private Set<Image>images=new HashSet<>();

    public Item(){}

    public Item(double price, double rating, String version, String desc, int quantity) {
        this.price = price;
        this.rating = rating;
        this.version = version;
        this.desc = desc;
        this.quantity = quantity;
    }

    public void addOrder(OrderItem order){
        this.orderItems.add(order);
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public String getFirstPhoto(){
        String path="";
        for(Image image:images){
            path=image.getPath();
            break;
        }
        return path;
    }

//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems= orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", price=" + price +
                ", rating=" + rating +
                ", version='" + version + '\'' +
                ", desc='" + desc + '\'' +
                ", quantity=" + quantity +
                ", product=" + product +
                ", votes=" + votes.size() +
                '}';
    }
}
