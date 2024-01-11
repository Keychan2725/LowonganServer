package com.java.lowongan.lowongan_server.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "identitas_user")
public class IdentitasUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "no_telepon")
    private Integer noTelepon;

    @Column(name = "gender")
    private String gender;

    @Column(name = "agama")
    private String agama;

    @Column(name = "usia")
    private Integer usia;

    @Column(name = "no_nik")
    private String noNik;

    @Column(name = "no_kk")
    private String noKk;

    @Column(name = "alamatRumah")
    private String alamatRumah;

    @Column(name = "tentangSaya")
    private String tentangSaya;




    @ManyToOne
    @MapsId
    @JoinColumn(name = "user")
    private User user;

    public IdentitasUser() {

    }



    public IdentitasUser(Long id, String namaLengkap, Integer noTelepon, String gender, String agama, Integer usia, String noNik, String noKk ,String alamatRumah, String tentangSaya ) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.noTelepon = noTelepon;
        this.gender = gender;
        this.agama = agama;
        this.usia = usia;
        this.noNik = noNik;
        this.noKk = noKk;
        this.alamatRumah = alamatRumah;
        this.tentangSaya = tentangSaya;

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

    public Integer getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(Integer noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }

    public String getNoNik() {
        return noNik;
    }

    public void setNoNik(String noNik) {
        this.noNik = noNik;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAlamatRumah() {
        return alamatRumah;
    }

    public void setAlamatRumah(String alamatRumah) {
        this.alamatRumah = alamatRumah;
    }

    public String getTentangSaya() {
        return tentangSaya;
    }

    public void setTentangSaya(String tentangSaya) {
        this.tentangSaya = tentangSaya;
    }


}