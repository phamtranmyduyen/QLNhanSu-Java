/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author trinh
 */
public class luongDTO {

    public String maluong, mabangchamcong, manhanvien, tennhanvien, thoigian;
    float luongcanban, thuong, hesoluong, phucap, sogiotre, sogiolamthem;
    double luongchinhthuc;

    public luongDTO() {
    }

    public luongDTO(String maluong, String mabangchamcong, String thoigian, String manhanvien, String tennhanvien, float sogiotre, float sogiolamthem, float luongcanban, float thuong, float hesoluong, float phucap, double luongchinhthuc) {
        this.maluong = maluong;
        this.mabangchamcong = mabangchamcong;
        this.thoigian = thoigian;
        this.manhanvien = manhanvien;
        this.sogiotre = sogiotre;
        this.sogiolamthem = sogiolamthem;
        this.tennhanvien = tennhanvien;
        this.luongcanban = luongcanban;
        this.thuong = thuong;
        this.hesoluong = hesoluong;
        this.phucap = phucap;
        this.luongchinhthuc = luongchinhthuc;
    }

    public float getSogiotre() {
        return sogiotre;
    }

    public void setSogiotre(float sogiotre) {
        this.sogiotre = sogiotre;
    }

    public float getSogiolamthem() {
        return sogiolamthem;
    }

    public void setSogiolamthem(float sogiolamthem) {
        this.sogiolamthem = sogiolamthem;
    }

    public String getMaluong() {
        return maluong;
    }

    public void setMaluong(String maluong) {
        this.maluong = maluong;
    }

    public String getMabangchamcong() {
        return mabangchamcong;
    }

    public void setMabangchamcong(String mabangchamcong) {
        this.mabangchamcong = mabangchamcong;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public float getLuongcanban() {
        return luongcanban;
    }

    public void setLuongcanban(float luongcanban) {
        this.luongcanban = luongcanban;
    }

    public float getThuong() {
        return thuong;
    }

    public void setThuong(float thuong) {
        this.thuong = thuong;
    }

    public float getHesoluong() {
        return hesoluong;
    }

    public void setHesoluong(float hesoluong) {
        this.hesoluong = hesoluong;
    }

    public float getPhucap() {
        return phucap;
    }

    public void setPhucap(float phucap) {
        this.phucap = phucap;
    }

    public double getLuongchinhthuc() {
        return luongchinhthuc;
    }

    public void setLuongchinhthuc(double luongchinhthuc) {
        this.luongchinhthuc = luongchinhthuc;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

  
}
