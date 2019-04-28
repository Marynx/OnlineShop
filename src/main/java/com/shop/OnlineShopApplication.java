package com.shop;

import com.shop.model.*;
import com.shop.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class OnlineShopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx=SpringApplication.run(OnlineShopApplication.class, args);
        User user=new User("ktos","qwerty123","Andrzej","Nowak","andrzej@ktos.pl");
        Order order= new Order(23.3, Date.valueOf("2011-01-20"),"Dobry");





        UserRepository userRepository= ctx.getBean(UserRepository.class);
        userRepository.save(user);

        OrderRepository orderRepository= ctx.getBean(OrderRepository.class);
        order.setUser(user);
        orderRepository.save(order);

        Adress adress= new Adress("Poland","Gdansk","Morawiecka 12","12-123");
        AdressRepository adressRepository= ctx.getBean(AdressRepository.class);
        adressRepository.save(adress);
        user.setAdress(adress);
        userRepository.save(user);

        Producer producer= new Producer("Helios",2.3,2);
        ProducerRepository producerRepository=ctx.getBean(ProducerRepository.class);
        producerRepository.save(producer);

        Category category=new Category("Elektornics","Electronics things");
        CategoryRepository categoryRepository=ctx.getBean(CategoryRepository.class);
        categoryRepository.save(category);

        Product product=new Product("Dell","Very Fast",3);
        product.setProducer(producer);
        product.setCategory(category);

        ProductRepository productRepository=ctx.getBean(ProductRepository.class);
        productRepository.save(product);


        User u1= userRepository.findById(1L).get();
        System.out.println(u1);
        System.out.println(productRepository.findById(1L).get());

        ctx.close();

    }

}
