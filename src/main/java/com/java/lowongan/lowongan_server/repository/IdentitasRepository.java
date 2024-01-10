package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentitasRepository extends JpaRepository<IdentitasUser, Long> {

}
