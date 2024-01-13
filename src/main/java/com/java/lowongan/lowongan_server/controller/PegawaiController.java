package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.Pegawai;
import com.java.lowongan.lowongan_server.service.PegawaiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PegawaiController {

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

    @DeleteMapping("/pegawai/{id}")
    public void delete(@PathVariable Long id) {
        pegawaiService.delete(pegawaiService.findById(id));
    }

}