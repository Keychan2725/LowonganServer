package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.Pegawai;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PekerjaanService {

    private final PekerjaanRepository pekerjaanRepository;


    @Transactional
    public void melamarPekerjaan(Long id) {
        Pekerjaan pekerjaan = pekerjaanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pegawai tidak ditemukan dengan ID: " + id));

        if (pekerjaan.getJumlahPelamar() == null) {
            pekerjaan.setJumlahPelamar(0);
        }
        pekerjaan.setJumlahPelamar(pekerjaan.getJumlahPelamar() + 1);

        pekerjaanRepository.save(pekerjaan);
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

    public void delete(Pekerjaan pekerjaan) {
        pekerjaanRepository.delete(pekerjaan);
    }

}