package com.java.lowongan.lowongan_server.controller;


import com.java.lowongan.lowongan_server.exception.NotFoundException;
import com.java.lowongan.lowongan_server.dto.PekerjaanDTO;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.Pelamar;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import com.java.lowongan.lowongan_server.repository.PelamarRepository;
import com.java.lowongan.lowongan_server.service.PelamarService;
import org.springframework.beans.factory.annotation.Autowired;
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
    PekerjaanRepository pekerjaanRepository;

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

    @GetMapping("/pekerjaan/{pekerjaanId}/pelamar/{userId}")
    public ResponseEntity<PekerjaanDTO> getPekerjaanDetails(@PathVariable Long pekerjaanId, @PathVariable Long userId) {
        try {
            // Retrieve pekerjaan details based on pekerjaanId
            Pekerjaan pekerjaan = pekerjaanRepository.getById(pekerjaanId);

            // Check if pekerjaan exists
            if (pekerjaan == null) {
                return ResponseEntity.notFound().build();
            }

            // Additional logic to retrieve pelamar details based on userId and pekerjaanId
            Pelamar pelamar = (Pelamar) pelamarRepository.findByUserIdAndPekerjaanId(userId, pekerjaanId);

            // Map the pekerjaan and pelamar details to a DTO (Data Transfer Object)
            PekerjaanDTO pekerjaanDTO = mapToDTO(pekerjaan, pelamar);

            return ResponseEntity.ok(pekerjaanDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // Adjust this based on your error handling
        }
    }

    // You can define a method to map your entities to a DTO
    private PekerjaanDTO mapToDTO(Pekerjaan pekerjaan, Pelamar pelamar) {
        PekerjaanDTO pekerjaanDTO = new PekerjaanDTO();

        pekerjaanDTO.setId(pekerjaan.getId());
        pekerjaanDTO.setNamaPekerjaan(pekerjaan.getNamaPekerjaan());
        // Map other attributes from Pekerjaan entity

        pekerjaanDTO.setIdPelamar(pelamar.getId());
        pekerjaanDTO.setNamaPelamar(pelamar.getNamaLengkap());
        // Map other attributes from Pelamar entity

        return pekerjaanDTO;
    }


    @GetMapping("/pelamar/user/{userId}")
    public ResponseEntity<List<Pelamar>> getByUserId(@PathVariable("userId") Long userId) {
        List<Pelamar> pelamarList = pelamarRepository.findByUserId(userId);
        if (pelamarList.isEmpty()) {
            throw new NotFoundException("Pelamar dengan ID user " + userId + " tidak ditemukan");
        }

        return ResponseEntity.ok(pelamarList);
    }
    @GetMapping("/pelamar/byId/{id}")
    public ResponseEntity<Pelamar> getPelamarById(@PathVariable Long id) {
        return pelamarRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/pelamar/terima/{id}")
    public ResponseEntity<Pelamar> terima(@PathVariable("id") Long id) {
        return updateStatus(id, "diterima");
    }
    @PutMapping("/pelamar/tolak/{id}")
    public ResponseEntity<Pelamar> tolak(@PathVariable("id") Long id) {
        return updateStatus(id, "ditolak");
    }

    @PutMapping("/pelamar/batal/{id}")
    public ResponseEntity<Pelamar> batal(@PathVariable("id") Long id) {
        return updateStatus(id, "melamar");
    }


    private ResponseEntity<Pelamar> updateStatus(Long id, String newStatus) {
        try {
            Pelamar pelamar = pelamarRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Pelamar not found with ID: " + id));

            pelamar.setStatus(newStatus);

            // Pass ID and Pelamar separately to the service method
            pelamarService.UpdateStatusById(id, pelamar);

            return ResponseEntity.ok(pelamar);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
