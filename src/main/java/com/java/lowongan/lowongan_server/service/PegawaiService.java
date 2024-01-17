package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.Pegawai;
import com.java.lowongan.lowongan_server.repository.PegawaiRepository;
import org.springframework.stereotype.Service;
import javax.management.Notification;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PegawaiService {

    private PegawaiRepository pegawaiRepository;



    @Transactional
    public void rekrutPegawai(Long id) {
        Pegawai pegawai = pegawaiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pegawai tidak ditemukan dengan ID: " + id));

        if (pegawai.getJumlahPerekrut() == null) {
            pegawai.setJumlahPerekrut(0);
        }
        pegawai.setJumlahPerekrut(pegawai.getJumlahPerekrut() + 1);

        pegawaiRepository.save(pegawai);
    }





    public PegawaiService(PegawaiRepository pegawaiRepository) {
        this.pegawaiRepository = pegawaiRepository;
    }

    public List<Pegawai> findAll() {
        return pegawaiRepository.findAll();
    }

    public Pegawai findById(Long id) {
        return pegawaiRepository.findById(id).orElse(null);
    }
    public Pegawai save(Pegawai pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    public void delete(Pegawai pegawai) {
        pegawaiRepository.delete(pegawai);
    }

}