package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.exception.CommonResponse;
import com.java.lowongan.lowongan_server.exception.ResponseHelper;
import com.java.lowongan.lowongan_server.model.LoginRequest;
import com.java.lowongan.lowongan_server.model.User;
import com.java.lowongan.lowongan_server.repository.UserRepository;
import com.java.lowongan.lowongan_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public CommonResponse<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok( userService.login(loginRequest));
    }

    @PostMapping("/user/addUser")
    public CommonResponse<User> addUser(@RequestBody User user){

        return ResponseHelper.ok( userService.addUser(user));
    }

    @PostMapping("/user/checkEmail")
    public ResponseEntity<Boolean> checkEmailExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        boolean emailExists = userRepository.existsByEmail(email);

        return ResponseEntity.ok(emailExists);
    }
    @PostMapping("/user/addAdmin")
    public CommonResponse<User> addAdmin(@RequestBody User user){
        return ResponseHelper.ok( userService.addAdmin(user));
    }
    @GetMapping("/user/{id}")
    public CommonResponse <User> get(@PathVariable("id") Long id){
        return ResponseHelper.ok( userService.get(id));
    }

    @GetMapping("/user/all")
    public CommonResponse<List<User>> getAll(){
        return ResponseHelper.ok( userService.getAll());
    }
    @PutMapping("/user/{id}")
    public CommonResponse<User> put(@PathVariable("id") Long id , @RequestBody User user){
        return ResponseHelper.ok( userService.edit(id, user));
    }
    @DeleteMapping("/user/{id}")
    public CommonResponse<?> delete(@PathVariable("id")  Long id) {
        return ResponseHelper.ok( userService.delete(id));
    }

}