package com.Blog.User.config;

import com.Blog.User.entity.User;
import com.Blog.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> email = userRepository.findByEmail(username);
        return email.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found" + username));

    }
}
