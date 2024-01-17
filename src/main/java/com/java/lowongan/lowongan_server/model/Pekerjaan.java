package com.java.lowongan.lowongan_server.model;

import javax.persistence.*;


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

    @Column(name = "kontakPemilik")
    private String kontakPemilik;


    @Column(name = "jumlahPelamar")
    private Integer jumlahPelamar;
    @Column(name = "tentangPekerjaan")
    private String tentangPekerjaan;

    @Column(name = "fotoPekerjaan")
    private String fotoPekerjaan;

    @Column(name = "status")
    private String status;



    @ManyToOne
    @MapsId
    @JoinColumn(name = "user")
    private User user;



    public Pekerjaan(){

    }
    public Pekerjaan(Long id, String namaPekerjaan, String alamatPekerjaan, String gajiPegawai, String kontakPemilik, Integer jumlahPelamar,String tentangPekerjaan, String fotoPekerjaan, String status, User user) {
        this.id = id;
        this.namaPekerjaan = namaPekerjaan;
        this.alamatPekerjaan = alamatPekerjaan;
        this.gajiPegawai = gajiPegawai;
        this.kontakPemilik = kontakPemilik;
        this.tentangPekerjaan = tentangPekerjaan;
        this.fotoPekerjaan = fotoPekerjaan;
        this.status = status;
        this.user = user;
        this.jumlahPelamar = jumlahPelamar;
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

    public String getKontakPemilik() {
        return kontakPemilik;
    }

    public void setKontakPemilik(String kontakPemilik) {
        this.kontakPemilik = kontakPemilik;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJumlahPelamar() {
        return jumlahPelamar;
    }

    public void setJumlahPelamar(Integer jumlahPelamar) {
        this.jumlahPelamar = jumlahPelamar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
