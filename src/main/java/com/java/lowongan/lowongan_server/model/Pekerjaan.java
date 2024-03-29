package com.java.lowongan.lowongan_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pekerjaan")
public class Pekerjaan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "namaPekerjaan")
    private String namaPekerjaan;

    @Column(name = "alamatPekerjaan")
    private String alamatPekerjaan;

    @Column(name = "gajiPegawai")
    private String gajiPegawai;

    @Column(name = "email")
    private String email;

    @Column(name = "tentangPekerjaan")
    private String tentangPekerjaan;

    @Column(name = "fotoPekerjaan")
    private String fotoPekerjaan;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalPost")
    private Date tanggalPost;

    @Column(name = "userId")
    private Long userId;

    public Pekerjaan(){

    }


    public Pekerjaan(Long id, String namaPekerjaan,  String alamatPekerjaan, String gajiPegawai,  String email, String tentangPekerjaan, String fotoPekerjaan) {
        this.id = id;
        this.namaPekerjaan = namaPekerjaan;
        this.alamatPekerjaan = alamatPekerjaan;
        this.gajiPegawai = gajiPegawai;
        this.email = email;
        this.tentangPekerjaan = tentangPekerjaan;
        this.fotoPekerjaan = fotoPekerjaan;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public String getAlamatPekerjaan() {
        return alamatPekerjaan;
    }

    public void setAlamatPekerjaan(String alamatPekerjaan) {
        this.alamatPekerjaan = alamatPekerjaan;
    }

    public String getGajiPegawai() {
        return gajiPegawai;
    }

    public void setGajiPegawai(String gajiPegawai) {
        this.gajiPegawai = gajiPegawai;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTentangPekerjaan() {
        return tentangPekerjaan;
    }

    public void setTentangPekerjaan(String tentangPekerjaan) {
        this.tentangPekerjaan = tentangPekerjaan;
    }

    public String getFotoPekerjaan() {
        return fotoPekerjaan;
    }

    public void setFotoPekerjaan(String fotoPekerjaan) {
        this.fotoPekerjaan = fotoPekerjaan;
    }

    public Date getTanggalPost() {
        return tanggalPost;
    }

    public void setTanggalPost(Date tanggalPost) {
        this.tanggalPost = tanggalPost;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
