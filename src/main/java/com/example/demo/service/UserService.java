package com.example.demo.service;

import com.example.demo.model.LoginUser;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginUserRepository loginUserRepository;

    public void register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User name already in use");
        }

        Roles role = roleRepository.findByRoleName(user.getRoles().getRoleName());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(role);
        userRepository.save(user);

    }

    public void login() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        LoginUser existing_user = loginUserRepository.findByUsername(name);
        if(existing_user != null){
            existing_user.setLoginTime(LocalDateTime.now());
            existing_user.setExpirationTime(LocalDateTime.now().plusMinutes(5));
            loginUserRepository.save(existing_user);
        }
        else {
            LoginUser user= new LoginUser();
            user.setUsername(name);
            user.setLoginTime(LocalDateTime.now());
            user.setExpirationTime(LocalDateTime.now().plusMinutes(5));
            loginUserRepository.save(user);
        }
    }

    public LoginUser getDetails() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String username = authenticate.getName();
        return loginUserRepository.findByUsername(username);

    }
    public void sessionCheck (){

        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        LoginUser user = loginUserRepository.findByUsername(name);
        if(user== null){
            throw new RuntimeException("Session not found");
        }
        else {
            if (user.getExpirationTime().isBefore(LocalDateTime.now())) {
                SecurityContextHolder.clearContext();
                throw new RuntimeException("user logged out");
            }
        }
    }

}

