package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.Pelamar;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PekerjaanService {

    private final PekerjaanRepository pekerjaanRepository;



    public List<Pekerjaan> findAllByUserId(Long userId) {
        return pekerjaanRepository.findAllByUserId(userId);
    }
     public List<Pekerjaan> findByUserId(Long userId) {
        return pekerjaanRepository.findByUserId(userId);
    }


    public Optional<Pekerjaan> findByUserIdAndId(Long userId, Long id) {
        return pekerjaanRepository.findByUserIdAndId(userId, id);
    }



    @Transactional
    public void editPekerjaan(Long id, Pekerjaan pekerjaan) {
        Pekerjaan pekerjaanAsli = pekerjaanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pekerjaan tidak ditemukan dengan ID: " + id));

        pekerjaanAsli.setNamaPekerjaan(pekerjaan.getNamaPekerjaan());
        pekerjaanAsli.setAlamatPekerjaan(pekerjaan.getAlamatPekerjaan());
        pekerjaanAsli.setGajiPegawai(pekerjaan.getGajiPegawai());
        pekerjaanAsli.setEmail(pekerjaan.getEmail());
        pekerjaanAsli.setTentangPekerjaan(pekerjaan.getTentangPekerjaan());
        pekerjaanAsli.setFotoPekerjaan(pekerjaan.getFotoPekerjaan());

        pekerjaanRepository.save(pekerjaanAsli);
    }



    public PekerjaanService(PekerjaanRepository pekerjaanRepository) {
        this.pekerjaanRepository = pekerjaanRepository;
    }

    public List<Pekerjaan> findAll() {
        return pekerjaanRepository.findAll();
    }

    public Pekerjaan findById(Long id) {
        return pekerjaanRepository.findById(id).orElse(null);
    }

    public Pekerjaan save(Pekerjaan pekerjaan) {
        return pekerjaanRepository.save(pekerjaan);
    }

    public void deleteById(Long id) {
        pekerjaanRepository.deleteById(id);
    }


}