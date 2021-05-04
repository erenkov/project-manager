package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByLogin(username);

        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getLogin(),
                    user.get().getPassword(), Set.of(user.get().getRole()));
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }
}
