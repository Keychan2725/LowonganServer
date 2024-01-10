package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.repository.IdentitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentitasImpl implements IdentitasService {

    @Autowired
    private IdentitasRepository identitasUserRepository;

    @Override
    public List<IdentitasUser> getAllIdentitasUsers() {
        return identitasUserRepository.findAll();
    }

    @Override
    public IdentitasUser getIdentitasUserById(Long id) {
        return identitasUserRepository.findById(id).orElse(null);
    }

    @Override
    public IdentitasUser saveIdentitasUser(IdentitasUser identitasUser) {
        return identitasUserRepository.save(identitasUser);
    }

    @Override
    public IdentitasUser updateIdentitasUser(Long id, IdentitasUser identitasUser) {
        Optional<IdentitasUser> existingIdentitasUser = identitasUserRepository.findById(id);
        if (existingIdentitasUser.isPresent()) {
            identitasUser.setId(id);
            return identitasUserRepository.save(identitasUser);
        }
        return null;
    }

    @Override
    public void deleteIdentitasUser(Long id) {
        identitasUserRepository.deleteById(id);
    }
}
