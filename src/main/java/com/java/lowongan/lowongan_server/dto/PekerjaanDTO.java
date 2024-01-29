package com.java.lowongan.lowongan_server.dto;

public class PekerjaanDTO {

    private Long id;
    private String namaPekerjaan;

    private String alamatPekerjaan;

    private String gajiPegawai;

    private String email;

    private String tentangPekerjaan;


    private Long idPelamar;

    private String namaPelamar;



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

    public Long getIdPelamar() {
        return idPelamar;
    }

    public void setIdPelamar(Long idPelamar) {
        this.idPelamar = idPelamar;
    }

    public String getNamaPelamar() {
        return namaPelamar;
    }

    public void setNamaPelamar(String namaPelamar) {
        this.namaPelamar = namaPelamar;
    }

    @Override
    public String toString() {
        return "PekerjaanDTO{" +
                "id=" + id +
                ", namaPekerjaan='" + namaPekerjaan + '\'' +
                ", alamatPekerjaan='" + alamatPekerjaan + '\'' +
                ", gajiPegawai='" + gajiPegawai + '\'' +
                ", email='" + email + '\'' +
                ", tentangPekerjaan='" + tentangPekerjaan + '\'' +
                ", idPelamar='" + idPelamar + '\'' +
                ", namaPelamar='" + namaPelamar + '\'' +
                '}';
    }

}
