package com.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_image")
    private Long id;
    private String path;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "image_item",
            joinColumns = {@JoinColumn(name="image_id", referencedColumnName="id_image")},
            inverseJoinColumns = {@JoinColumn(name="item_id", referencedColumnName="id_item")})
    private Set<Item> items=new HashSet<>();

    public Image(){}

    public Image(String path){
        this.path=path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", items=" + items.size() +
                '}';
    }
}
