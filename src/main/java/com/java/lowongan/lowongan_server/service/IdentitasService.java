package com.java.lowongan.lowongan_server.service;

import com.java.lowongan.lowongan_server.exception.NotFoundException;
import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.repository.IdentitasRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface IdentitasService {




    List<IdentitasUser> getAllIdentitasUsers();



    IdentitasUser saveIdentitasUser(IdentitasUser identitasUser);


    List<IdentitasUser> updateIdentitasUser(Long userId, IdentitasUser identitasUser);

    void deleteIdentitasUser(Long id);
}
