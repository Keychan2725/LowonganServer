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
    public ResponseEntity<List<Pekerjaan>> findAll() {
        List<Pekerjaan> pekerjaan = pekerjaanService.findAll();
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }
    @GetMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> findById(@PathVariable Long id) {
        Pekerjaan pekerjaan = pekerjaanService.findById(id);
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }

    @PostMapping("/pekerjaan/{id}/lamar")
    public ResponseEntity<String> melamarPekerjaan(@PathVariable Long id) {
        try {
            pekerjaanService.melamarPekerjaan(id);
            return ResponseEntity.ok("Berhasil melamar.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan: " + e.getMessage());
        }
    }
    @PutMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> editPekerjaan(@PathVariable Long id, @RequestBody Pekerjaan pekerjaan) {
        pekerjaanService.editPekerjaan(id, pekerjaan);

        return ResponseEntity.ok(pekerjaan);
    }


    @PostMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> save(@RequestBody Pekerjaan pekerjaan) {
        Pekerjaan pekerjaanBaru = pekerjaanService.save(pekerjaan);
        return new ResponseEntity<>(pekerjaanBaru, HttpStatus.CREATED);
    }
    @DeleteMapping("/pekerjaan/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        pekerjaanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}