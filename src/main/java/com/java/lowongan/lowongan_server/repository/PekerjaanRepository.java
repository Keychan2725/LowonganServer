package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.IdentitasUser;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.Pelamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PekerjaanRepository extends JpaRepository<Pekerjaan, Long> {
//    List<Pekerjaan> findByPekerjaanUserId(Long user);

//    @Query("SELECT p FROM pekerjaan  WHERE userId = :userId")
    List<Pekerjaan> findAllByUserId(Long userId);




    Optional<Pekerjaan> findByUserIdAndId(Long userId, Long id);


    List<Pekerjaan> findByUserId(Long userId);


}