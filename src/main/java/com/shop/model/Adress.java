package com.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adress")
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adress")
    private Long id;
    @Pattern(regexp = "[A-Z][A-z]*",message = "Kraj musi byc napisany z wielkiej litery")
    private String country;
    @Pattern(regexp = "[A-Z][A-z]*",message = "Miasto musi byc napisane z wielkiej litery")
    private String city;
    @Pattern(regexp = "[A-Z][A-z]* [0-9]+",message = "Ulica musi byc napisana z wielkiej litery oraz posiadac numer lokalu")
    private String street;
    @Column(name="postal_code")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}",message = "Kod pocztowy musi miec format xx-xxx")
    private String postCode;
    @OneToMany(mappedBy = "adress",fetch = FetchType.EAGER)
    private List<User> users=new ArrayList<>();

    public Adress(){}

    public Adress(String country, String city, String street, String postCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", users=" + users.size() +
                '}';
    }
}
