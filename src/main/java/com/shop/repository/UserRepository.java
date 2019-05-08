package com.shop.repository;

import com.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByLogin(String login);
}
