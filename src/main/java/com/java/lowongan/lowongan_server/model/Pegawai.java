package com.java.lowongan.lowongan_server.model;


import javax.management.Notification;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "pegawai")
public class Pegawai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaPegawai")
    private String namaPegawai;

    @Column(name = "kontakPegawai")
    private String kontakPegawai;

    @Column(name = "status")
    private String status;

    @Column(name = "pengalamanBekerja")
    private String pengalamanBekerja;

    @Column(name = "alamatPegawai")
    private String alamatPegawai;

    @Column(name = "permintaanGaji")
    private String permintaanGaji;

    @Column(name = "jumlahPerekrut")
    private Integer jumlahPerekrut;

    @Column(name = "fotoPegawai")
    private String fotoPegawai;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user")
    private User user;



    public Pegawai() {
    }
    public Pegawai(Long id, String namaPegawai, String kontakPegawai, String status, String pengalamanBekerja, String alamatPegawai,Integer jumlahPerekrut, String permintaanGaji, String fotoPegawai  ,User user) {
        this.id = id;
        this.namaPegawai = namaPegawai;
        this.kontakPegawai = kontakPegawai;
        this.status = status;
        this.pengalamanBekerja = pengalamanBekerja;
        this.alamatPegawai = alamatPegawai;
        this.permintaanGaji = permintaanGaji;
        this.fotoPegawai = fotoPegawai;

        this.user = user;
        this.jumlahPerekrut = jumlahPerekrut;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getKontakPegawai() {
        return kontakPegawai;
    }

    public void setKontakPegawai(String kontakPegawai) {
        this.kontakPegawai = kontakPegawai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPengalamanBekerja() {
        return pengalamanBekerja;
    }

    public void setPengalamanBekerja(String pengalamanBekerja) {
        this.pengalamanBekerja = pengalamanBekerja;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
    }

    public String getPermintaanGaji() {
        return permintaanGaji;
    }

    public void setPermintaanGaji(String permintaanGaji) {
        this.permintaanGaji = permintaanGaji;
    }

    public String getFotoPegawai() {
        return fotoPegawai;
    }

    public void setFotoPegawai(String fotoPegawai) {
        this.fotoPegawai = fotoPegawai;
    }



    public Integer getJumlahPerekrut() {
        return jumlahPerekrut;
    }

    public void setJumlahPerekrut(Integer jumlahPerekrut) {
        this.jumlahPerekrut = jumlahPerekrut;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
