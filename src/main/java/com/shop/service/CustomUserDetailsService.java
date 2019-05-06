package com.shop.service;

import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user==null)
            throw new UsernameNotFoundException("Nie znaleziono uzytkownika");
        org.springframework.security.core.userdetails.User userDetails=
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),convertAuthorities(user.getRoles()));
        return userDetails;
    }

    private Set<GrantedAuthority>convertAuthorities(Set<Role>userRoles){
        Set<GrantedAuthority>authorities=new HashSet<>();
        for(Role roles: userRoles){
            authorities.add(new SimpleGrantedAuthority(roles.getRole()));
        }
        return authorities;
    }


}

