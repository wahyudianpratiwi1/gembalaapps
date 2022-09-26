package com.wahyudiapratiwi.gembalaapps.Model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class RequestRegister {
    @SerializedName("nama_mitra") String nama;
    @SerializedName("username") String username;
    @SerializedName("password") String password;
    @SerializedName("email") String email;
    @SerializedName("no_hp") String no_hp;
    @SerializedName("alamat") String alamat;
    @SerializedName("level") String level;


    public RequestRegister(String username, String email, String nama, String alamat, String no_hp, String password) {
        this.username = username;
        this.email = email;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.password = password;
    }

    public RequestRegister setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getLevel() {
        return level;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
