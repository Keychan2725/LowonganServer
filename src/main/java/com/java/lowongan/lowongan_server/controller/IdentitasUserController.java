package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.IdentitasUser;

import com.java.lowongan.lowongan_server.service.IdentitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class IdentitasUserController {

    @Autowired
    private IdentitasService identitasUserService;


    @GetMapping("/identitasUsers/allUser")
    public List<IdentitasUser> getAllIdentitasUsers() {
        return identitasUserService.getAllIdentitasUsers();
    }

    @GetMapping("/identitasUsers/{userId}")
    public ResponseEntity<List<IdentitasUser>> getIdentitasUsersByUserId(@PathVariable Long userId) {
        List<IdentitasUser> identitasUsers = identitasUserService.getIdentitasUsersByUserId(userId);
        return ResponseEntity.ok(identitasUsers);
    }

    @PostMapping("/identitasUsers/add")
    public IdentitasUser saveIdentitasUser(@RequestBody IdentitasUser identitasUser) {
        return identitasUserService.saveIdentitasUser(identitasUser);
    }

    @PutMapping("/identitasUsers/edit/{userId}")
    public List<IdentitasUser> updateIdentitasUserByUserId(@PathVariable Long userId, @RequestBody IdentitasUser identitasUser) {
 return identitasUserService.updateIdentitasUser(userId, identitasUser);
    }
    @DeleteMapping("/identitasUsers/{id}")
    public void deleteIdentitasUser(@PathVariable Long id) {
        identitasUserService.deleteIdentitasUser(id);
    }
}
