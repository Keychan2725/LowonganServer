package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.exception.NotFoundException;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.Pelamar;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import com.java.lowongan.lowongan_server.repository.PelamarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PelamarService {
    @Autowired
    private PelamarRepository pelamarRepository;


    public ResponseEntity<List<Pelamar>> getAllPelamar() {
        List<Pelamar> pelamarList = pelamarRepository.findAll();
        return ResponseEntity.ok(pelamarList);
    }

    public ResponseEntity<Pelamar> createPelamar(@RequestBody Pelamar pelamar) {
        pelamar.setStatus("melamar");
        pelamarRepository.save(pelamar);
        return ResponseEntity.ok(pelamar);
    }

    public List<Pelamar> getPelamarByPekerjaanId(Long pekerjaanId) {
        return pelamarRepository.findByPekerjaanId(pekerjaanId);
    }


    @Transactional
    public ResponseEntity<Pelamar> updatePelamar(@PathVariable("userId") Long userId, @RequestBody Pelamar pelamar ) {
        Pelamar pelamarr = (Pelamar) pelamarRepository.findByUserId(userId);


        pelamarr.setNamaLengkap(pelamar.getNamaLengkap());
        pelamarr.setAlamat(pelamar.getAlamat());
        pelamarr.setPermintaanGaji(pelamar.getPermintaanGaji());
        pelamarr.setEmail(pelamar.getEmail());
        pelamarr.setPengalamanBekerja(pelamar.getPengalamanBekerja());
        pelamarRepository.save(pelamar);
        return ResponseEntity.ok(pelamar);
    }
  @Transactional
    public ResponseEntity<Pelamar> UpdateStatusById( @PathVariable("id") Long id  ,@RequestBody Pelamar pelamar ) {
        Pelamar pelamarr = pelamarRepository.findById(id).orElseThrow(() -> new NotFoundException("Pelamar not found with ID: " + id));



        pelamarr.setStatus(pelamar.getStatus());
        pelamarRepository.save(pelamar);
        return ResponseEntity.ok(pelamar);
    }





}