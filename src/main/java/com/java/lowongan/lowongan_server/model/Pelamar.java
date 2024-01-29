package com.java.lowongan.lowongan_server.model;

import javax.persistence.*;

@Entity
@Table(name = "pelamar")
public class Pelamar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaLengkap")
    private  String namaLengkap;

    @Column(name = "email")
    private  String email;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "permintaanGaji")
    private String permintaanGaji;

    @Column(name = "pengalamanBekerja")
    private String pengalamanBekerja;

    @Column(name = "status")
    private  String status;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "pekerjaanId")
    private Long pekerjaanId;



    public Pelamar() {

    }


    public Pelamar(Long id, String namaLengkap, String email, String alamat, String permintaanGaji, String pengalamanBekerja , String status) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.alamat = alamat;
        this.permintaanGaji = permintaanGaji;
        this.pengalamanBekerja = pengalamanBekerja;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPermintaanGaji() {
        return permintaanGaji;
    }

    public void setPermintaanGaji(String permintaanGaji) {
        this.permintaanGaji = permintaanGaji;
    }

    public String getPengalamanBekerja() {
        return pengalamanBekerja;
    }

    public void setPengalamanBekerja(String pengalamanBekerja) {
        this.pengalamanBekerja = pengalamanBekerja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPekerjaanId() {
        return pekerjaanId;
    }

    public void setPekerjaanId(Long pekerjaanId) {
        this.pekerjaanId = pekerjaanId;
    }
}
