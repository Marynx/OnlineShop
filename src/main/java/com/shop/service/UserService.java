package com.shop.service;

import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String DEFAULT_ROLE="ROLE_USER";
    private static final String WORKER_ROLE="ROLE_WORKER";
    private static final String ADMIN_ROLE="ROLE_ADMIN";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }


    public void addWithDefaultRole(User user){
        Role defaultRole=roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }

    public void addWithWorkerRole(User user){
        Role workerRole=roleRepository.findByRole(WORKER_ROLE);
        user.getRoles().add(workerRole);
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }

    public void addWithAdminRole(User user){
        Role adminRole=roleRepository.findByRole(ADMIN_ROLE);
        user.getRoles().add(adminRole);
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}
