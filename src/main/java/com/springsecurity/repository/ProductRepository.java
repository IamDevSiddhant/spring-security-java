package com.springsecurity.repository;

import com.springsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface ProductRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String name);
}
