package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.Pegawai;
import com.java.lowongan.lowongan_server.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    public PegawaiController(PegawaiService pegawaiService) {
        this.pegawaiService = pegawaiService;
    }

    @GetMapping("/pegawai/all")
    public List<Pegawai> findAll() {
        return pegawaiService.findAll();
    }

    @GetMapping("/pegawai/{id}")
    public Pegawai findById(@PathVariable Long id) {
        return pegawaiService.findById(id);
    }

    @PostMapping("/pegawai/{id}")
    public Pegawai save(@RequestBody Pegawai pegawai) {
        return pegawaiService.save(pegawai);
    }

    @PostMapping("/pegawai/{id}/rekrut")
    public ResponseEntity<String> rekrutPegawai(@PathVariable Long id) {
        try {
            pegawaiService.rekrutPegawai(id);
            return ResponseEntity.ok("Pegawai berhasil direkrut.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @DeleteMapping("/pegawai/{id}")
    public void delete(@PathVariable Long id) {
        pegawaiService.delete(pegawaiService.findById(id));
    }

}