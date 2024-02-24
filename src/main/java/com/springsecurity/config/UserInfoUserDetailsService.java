package com.springsecurity.config;

import com.springsecurity.entity.UserInfo;
import com.springsecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = productRepository.findByName(username);
        return user.map(UserInfoUserDetails::new)
                .orElseThrow(()->new RuntimeException("user not found::"+username));
    }
}
