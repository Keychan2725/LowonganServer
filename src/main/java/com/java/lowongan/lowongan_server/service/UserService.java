package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.LoginRequest;
import com.java.lowongan.lowongan_server.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<Object, Object> login(LoginRequest loginRequest);
    User addUser(User user);

    User addAdmin(User user);
    User get(Long id);

    List<User> getAll();

    User edit(Long id, User user);

    Map<String, Boolean> delete(Long id);
}