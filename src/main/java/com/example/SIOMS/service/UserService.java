package com.example.SIOMS.service;

import com.example.SIOMS.dto.UserRequest;
import com.example.SIOMS.entity.User;
import com.example.SIOMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signin(UserRequest loginRequest) {
        User user=new User();
        user.setName(loginRequest.getName());
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        user.setRoles(Set.of("ADMIN"));
        try {
            User savedUser = userRepository.save(user);
            if (savedUser != null && savedUser.getId() != null) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error while saving user: ");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error"+e.getMessage());
        }
    }

    public void login(UserRequest loginRequest) {
//        userRepository.findByName
    }
}
