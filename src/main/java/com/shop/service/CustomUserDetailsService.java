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
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userRepository.findByLogin(login);

        if(user==null)
            throw new UsernameNotFoundException("Nie znaleziono uzytkownika");
        org.springframework.security.core.userdetails.User userDetails=
                new org.springframework.security.core.userdetails.User(user.getLogin(),
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

