package com.springsecurity.controller;

import com.springsecurity.dto.AuthRequest;
import com.springsecurity.entity.UserInfo;
import com.springsecurity.service.JWTService;
import com.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class UserController {
    @Autowired
    private ProductService service;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    JWTService jwtService;


    @PostMapping("/new")
    public ResponseEntity<?> addUserToDb(@RequestBody UserInfo userInfo) {
        return new ResponseEntity<>(service.addUser(userInfo), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getALl(), HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getProductById(@RequestParam("id") int id) {
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String createToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
