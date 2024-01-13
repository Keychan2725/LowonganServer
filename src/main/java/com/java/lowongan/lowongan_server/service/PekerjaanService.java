package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PekerjaanService {

    private PekerjaanRepository pekerjaanRepository;

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