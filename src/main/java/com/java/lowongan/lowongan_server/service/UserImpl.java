package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.LoginRequest;
import com.java.lowongan.lowongan_server.model.User;
import com.java.lowongan.lowongan_server.repository.UserRepository;
import com.java.lowongan.lowongan_server.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Map<Object, Object> login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new com.java.lowongan.lowongan_server.exception.NotFoundException("Username not found"));
        if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken(authentication);
            user.setLast_login(new Date());
            userRepository.save(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedLastLogin = sdf.format(user.getLast_login());
            Map<Object, Object> response = new HashMap<>();
            response.put("data", user);
            response.put("id", user.getId());
            response.put("token", jwt);
            response.put("last_login", formattedLastLogin);
            response.put("type_token", "User");

            return response;
        }
        throw new  com.java.lowongan.lowongan_server.exception.NotFoundException("Password not found");
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new com.java.lowongan.lowongan_server.exception.NotFoundException("Email sudah digunakan");
        }

        // Validate age
        int usia = Integer.parseInt(user.getUsia()); // Assuming usia is a string representation of an integer
        if (usia < 18) {
            throw new IllegalStateException("Usia belum mencapai 18 tahun, tidak dapat melakukan registrasi");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("user");
        return userRepository.save(user);
    }

    @Override
    public User addAdmin(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new  com.java.lowongan.lowongan_server.exception.NotFoundException("Email sudah digunakan");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("admin");
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new   com.java.lowongan.lowongan_server.exception.NotFoundException("Id Not Found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User edit(Long id, User user) {
        User update = userRepository.findById(id).orElseThrow(() -> new  com.java.lowongan.lowongan_server.exception.NotFoundException("Id Not Found"));
        update.setPassword(user.getPassword());
        update.setUsername(user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            userRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
}