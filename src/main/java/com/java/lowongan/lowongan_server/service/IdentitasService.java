package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.model.IdentitasUser;

import java.util.List;

public interface IdentitasService {
    List<IdentitasUser> getAllIdentitasUsers();

    IdentitasUser getIdentitasUserById(Long id);

    IdentitasUser saveIdentitasUser(IdentitasUser identitasUser);

    IdentitasUser updateIdentitasUser(Long id, IdentitasUser identitasUser);

    void deleteIdentitasUser(Long id);
}
