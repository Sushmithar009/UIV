package com.example.SIOMS.controller;
import com.example.SIOMS.dto.UserRequest;
import com.example.SIOMS.entity.User;
import com.example.SIOMS.repository.RoleRepository;
import com.example.SIOMS.service.JwtService;
import com.example.SIOMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sioms")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<String> user(@RequestBody UserRequest loginRequest){
        return userService.signin(loginRequest);
    }

    @PostMapping("/hi")
//    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String welcome(){
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(),loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginRequest.getName());
        }
        else{
            throw new UsernameNotFoundException("invalid user request!");
        }
    }
}
