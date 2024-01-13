package com.java.lowongan.lowongan_server.controller;

import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.service.PekerjaanService;
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

    @GetMapping("/pekerjaan")
    public List<Pekerjaan> findAll() {
        return pekerjaanService.findAll();
    }

    @GetMapping("/pekerjaan/{id}")
    public Pekerjaan findById(@PathVariable Long id) {
        return pekerjaanService.findById(id);
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