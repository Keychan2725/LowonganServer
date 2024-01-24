package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.repository.IdentitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class IdentitasImpl implements IdentitasService {


    @Autowired
    private IdentitasRepository identitasUserRepository;

    private final IdentitasRepository identitasRepository;





    @Autowired
    public IdentitasImpl(IdentitasRepository identitasRepository) {
        this.identitasRepository = identitasRepository;
    }






    @Override
    public List<IdentitasUser> getAllIdentitasUsers() {
        return identitasUserRepository.findAll();
    }



    @Override
    public IdentitasUser saveIdentitasUser(IdentitasUser identitasUser) {
        return identitasUserRepository.save(identitasUser);
    }




    @Override
    public List<IdentitasUser> updateIdentitasUser(Long userId, @RequestBody IdentitasUser newData) {
        List<IdentitasUser> existingData = identitasUserRepository.findByUserId(userId);
        for (IdentitasUser data : existingData) {
            // Lakukan pembaruan data hanya jika nilai baru tidak null
            if (newData.getNamaLengkap() != null) {
                data.setNamaLengkap(newData.getNamaLengkap());
            }
            if (newData.getAgama() != null) {
                data.setAgama(newData.getAgama());
            }
            if (newData.getNoTelepon() != null) {
                data.setNoTelepon(newData.getNoTelepon());
            }
            if (newData.getNoKk() != null) {
                data.setNoKk(newData.getNoKk());
            }
            if (newData.getGender() != null) {
                data.setGender(newData.getGender());
            }
            if (newData.getNoNik() != null) {
                data.setNoNik(newData.getNoNik());
            }
            if (newData.getAlamatRumah() != null) {
                data.setAlamatRumah(newData.getAlamatRumah());
            }
            if (newData.getTentangSaya() != null) {
                data.setTentangSaya(newData.getTentangSaya());
            }
        }

        return identitasUserRepository.saveAll(existingData);
    }



@Override
    public void deleteIdentitasUser(Long id) {
        identitasUserRepository.deleteById(id);
    }
}
