package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentitasRepository extends JpaRepository<IdentitasUser, Long> {


   public List<IdentitasUser> findByUserId(Long userId);
    void deleteById(Long id);

}
