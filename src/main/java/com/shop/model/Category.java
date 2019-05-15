package com.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;
    private String type;
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Product> products;

    public Category (){}

    public Category(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", products=" + products.size() +
                '}';
    }
}
