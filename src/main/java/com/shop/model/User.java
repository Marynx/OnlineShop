package com.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private String login;
    private String password;
    @Column(name = "firstName")
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Email
    private String email;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Order> orders=new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Vote> votes=new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id_user")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id_role")})
    private Set<Role> roles=new HashSet<>();

    public User(){}

    public User(String login, String password, String firstName, String lastName, @Email String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                //sif(adress!=null) {
                    ", adress=" +adress//+ "{Country=" + adress.getCountry() + " ,City=" + adress.getCity() + " ,Street=" + adress.getStreet() + " ,Post-Code=" + adress.getPostCode() + "}"
                //}
                        +", votes="+votes+'}';
    }
}
