package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.IdentitasUser;

import com.java.lowongan.lowongan_server.service.IdentitasService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/identitasUsers/{id}")
    public IdentitasUser getIdentitasUserById(@PathVariable Long id) {
        return identitasUserService.getIdentitasUserById(id);
    }

    @PostMapping("/identitasUsers/{id}")
    public IdentitasUser saveIdentitasUser(@RequestBody IdentitasUser identitasUser) {
        return identitasUserService.saveIdentitasUser(identitasUser);
    }

    @PutMapping("/identitasUsers/{id}")
    public IdentitasUser updateIdentitasUser(@PathVariable Long id, @RequestBody IdentitasUser identitasUser) {
        return identitasUserService.updateIdentitasUser(id, identitasUser);
    }

    @DeleteMapping("/identitasUsers/{id}")
    public void deleteIdentitasUser(@PathVariable Long id) {
        identitasUserService.deleteIdentitasUser(id);
    }
}
