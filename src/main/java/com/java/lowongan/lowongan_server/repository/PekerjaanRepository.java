package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PekerjaanRepository extends JpaRepository<Pekerjaan, Long> {
//    List<Pekerjaan> findByPekerjaanUserId(Long user);
List<Pekerjaan> findByIdentitasUser(IdentitasUser identitasUser);


}