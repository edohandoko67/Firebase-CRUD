package com.example.testing;

public class Model {
    private String nama;
    private String nim;
    private String jurusan;
    private String key;

    public Model(){
        
    }

    public Model(String nama, String nim, String jurusan, String key) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.key = key;
    }

    public Model(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
