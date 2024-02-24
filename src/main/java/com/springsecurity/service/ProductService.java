package com.springsecurity.service;


import com.springsecurity.dto.Product;
import com.springsecurity.entity.UserInfo;
import com.springsecurity.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();
    @Autowired
    private ProductRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void loadFromDB() {
        products = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("prdutc " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextDouble(10))
                        .build()).collect(Collectors.toList());

    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "USER ADDED SUCCESSFULLY!";
    }

    public List<Product> getALl() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product Not found"));
    }


}
