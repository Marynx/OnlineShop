package com.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producer")
public class Producer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producer")
    private Long id;
    private String name;
    private double rating;
    @Column(name = "product_count")
    private int producCount;
    @OneToMany(mappedBy = "producer",fetch = FetchType.EAGER)
    private List<Product> products;

    public Producer(){}

    public Producer(String name, double rating, int producCount) {
        this.name = name;
        this.rating = rating;
        this.producCount = producCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getProducCount() {
        return producCount;
    }

    public void setProducCount(int producCount) {
        this.producCount = producCount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", producCount=" + producCount +
                ", products=" + products.size() +
                '}';
    }
}
