package com.java.lowongan.lowongan_server.controller;


import com.java.lowongan.lowongan_server.exception.NotFoundException;
import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.model.Pelamar;
import com.java.lowongan.lowongan_server.repository.PelamarRepository;
import com.java.lowongan.lowongan_server.service.PelamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PelamarController {
    @Autowired
    PelamarService pelamarService;

    @Autowired
    PelamarRepository pelamarRepository;


    @GetMapping("/pelamar/all")
    public ResponseEntity<List<Pelamar>> getAllPelamar() {
        return pelamarService.getAllPelamar();
    }

    @PostMapping("/pelamar/add")
    public ResponseEntity<Pelamar> createPelamar(@RequestBody Pelamar pelamar) {
        pelamarService.createPelamar(pelamar);
        return ResponseEntity.ok(pelamar);
    }

//    @GetMapping("/pelamar/{userId}")
//    public ResponseEntity<Pelamar> getLowonganByUserId(@PathVariable("userId") Long userId) {
//        return pelamarService.getLowonganByUserId(userId);
//    }

    @PutMapping("/pelamar/edit/{userId}")
    public ResponseEntity<Pelamar> updatePelamar(@PathVariable("userId") Long userId, @RequestBody Pelamar pelamar) {
        return pelamarService.updatePelamar(userId, pelamar);
    }


    @DeleteMapping("/pelamar/{id}")
    public ResponseEntity<?> deleteByUserId(@PathVariable Long id) {
        // Get the user by ID
        Pelamar pelamar = pelamarRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        pelamarRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/pelamar/getBy/{pekerjaanId}")
    public ResponseEntity<List<Pelamar>> getByPekerjaanId(@PathVariable("pekerjaanId") Long pekerjaanId) {
        List<Pelamar> pelamarList = pelamarRepository.findByPekerjaanId(pekerjaanId);
        if (pelamarList.isEmpty()) {
            throw new NotFoundException("Pelamar dengan ID pekerjaan " + pekerjaanId + " tidak ditemukan");
        }
        return ResponseEntity.ok(pelamarList);
    }


    @GetMapping("/pelamar/byUserId/{userId}")
    public ResponseEntity<List<Pelamar>> getByUserId(@PathVariable("userId") Long userId) {
        List<Pelamar> pelamarList = pelamarRepository.findByUserId(userId);
        if (pelamarList.isEmpty()) {
            throw new NotFoundException("Pelamar dengan ID user " + userId + " tidak ditemukan");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        return ResponseEntity.ok(pelamarList);
    }
    @GetMapping("/pelamar/byId/{id}")
    public ResponseEntity<Pelamar> getPelamarById(@PathVariable Long id) {
        return pelamarRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
