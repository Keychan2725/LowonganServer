package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.Pelamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PelamarRepository extends JpaRepository<Pelamar , Long> {
       public List<Pelamar> findByUserId(@Param("userId") Long userId);
       public void deleteById(@Param("id") Long id);

       public List<Pelamar> findByPekerjaanId(Long pekerjaanId);




}
