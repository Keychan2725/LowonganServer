package com.java.lowongan.lowongan_server.repository;

import com.java.lowongan.lowongan_server.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

}
