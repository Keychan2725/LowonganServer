package com.java.lowongan.lowongan_server.service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.java.lowongan.lowongan_server.model.User;
import com.java.lowongan.lowongan_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        if (userRepository.existsByEmail(username)){
            User user = userRepository.findByEmail(username).orElseThrow(() -> new  com.java.lowongan.lowongan_server.exception.NotFoundException("Id Not Found"));
            return UserDetail.buildUser(user);
        }
        throw new  com.java.lowongan.lowongan_server.exception.NotFoundException("Entity not found");
    }

}