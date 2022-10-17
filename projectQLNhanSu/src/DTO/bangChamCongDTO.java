/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author MaiThy
 */
public class bangChamCongDTO {
    String mabangchamcong, manhanvien, thoigian;
    float sogiotre, sogiolamthem;

    public bangChamCongDTO() {
    }

    public bangChamCongDTO(String mabangchamcong, String manhanvien, String thoigian, float sogiotre, float sogiolamthem) {
        this.mabangchamcong = mabangchamcong;
        this.manhanvien = manhanvien;
        this.thoigian = thoigian;
        this.sogiotre = sogiotre;
        this.sogiolamthem = sogiolamthem;
    }
    public bangChamCongDTO(String manhanvien, String thoigian, float sogiotre, float sogiolamthem) {
        this.manhanvien = manhanvien;
        this.thoigian = thoigian;
        this.sogiotre = sogiotre;
        this.sogiolamthem = sogiolamthem;
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

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
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

}
