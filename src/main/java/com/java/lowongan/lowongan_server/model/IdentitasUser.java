package com.java.lowongan.lowongan_server.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "identitas_user")
public class IdentitasUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaLengkap")
    private String namaLengkap;

    @Column(name = "noTelepon")
    private String noTelepon;

    @Column(name = "gender")
    private String gender;

    @Column(name = "agama")
    private String agama;

    @Column(name = "noNik")
    private String noNik;

    @Column(name = "noKk")
    private String noKk;

    @Column(name = "alamatRumah")
    private String alamatRumah;

    @Column(name = "tentangSaya")
    private String tentangSaya;


    @Column(name = "pekerjaanId")
    private Long pekerjaanId;

    @Column(name = "userId")
    private Long userId;


    public IdentitasUser() {

    }



    public IdentitasUser(Long id, String namaLengkap, String noTelepon, String gender, String agama, String noNik, String noKk ,String alamatRumah, String tentangSaya ) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.noTelepon = noTelepon;
        this.gender = gender;
        this.agama = agama;
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

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
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

    public Long getPekerjaanId() {
        return pekerjaanId;
    }

    public void setPekerjaanId(Long pekerjaanId) {
        this.pekerjaanId = pekerjaanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}