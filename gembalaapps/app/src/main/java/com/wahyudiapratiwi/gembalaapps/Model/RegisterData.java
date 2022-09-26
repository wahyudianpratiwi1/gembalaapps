package com.wahyudiapratiwi.gembalaapps.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("nama_mitra")
    private String nama;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("username")
    private int username;

    @SerializedName("email")
    private String email;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("alamat")
    private String alamat;
    @SerializedName("password")
    private String password;
    @SerializedName("level")
    private String level;

    public RegisterData() {
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
