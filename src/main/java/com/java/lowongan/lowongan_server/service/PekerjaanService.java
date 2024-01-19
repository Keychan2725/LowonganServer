package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
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
        pekerjaanAsli.setStatus(pekerjaan.getStatus());

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

    public List<Pekerjaan> findByIdentitasUser(IdentitasUser identitasUser) {
        return pekerjaanRepository.findByIdentitasUser(identitasUser);
    }

}