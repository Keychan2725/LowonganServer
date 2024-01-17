package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.service.PekerjaanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PekerjaanController {



    private PekerjaanService pekerjaanService;

    public PekerjaanController(PekerjaanService pekerjaanService) {
        this.pekerjaanService = pekerjaanService;
    }

    @GetMapping("/pekerjaan/all")
    public List<Pekerjaan> findAll() {
        return pekerjaanService.findAll();
    }

    @GetMapping("/pekerjaan/{id}")
    public Pekerjaan findById(@PathVariable Long id) {
        return pekerjaanService.findById(id);
    }


//    @GetMapping("/pekerjaan/user/{userId}")
//    public List<Pekerjaan> getPekerjaanByUserId(@PathVariable Long user) {
//        return pekerjaanService.getPekerjaanByUserId(user);
//    }

    @PostMapping("/pekerjaan/{id}/lamar")
    public ResponseEntity<String> melamarPekerjaan(@PathVariable Long id) {
        try {
            pekerjaanService.melamarPekerjaan(id);
            return ResponseEntity.ok("Berhasil melamar.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan: " + e.getMessage());
        }
    }


    @PostMapping("/pekerjaan/{id}")
    public Pekerjaan save(@RequestBody Pekerjaan pekerjaan) {
        return pekerjaanService.save(pekerjaan);
    }

    @DeleteMapping("/pekerjaan/{id}")
    public void delete(@PathVariable Long id) {
        pekerjaanService.delete(pekerjaanService.findById(id));
    }

}