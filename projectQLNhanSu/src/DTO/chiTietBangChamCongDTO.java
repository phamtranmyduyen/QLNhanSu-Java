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
public class chiTietBangChamCongDTO {

    String mabangchamcong, ngay, giovao, giora, tennhanvien, manhanvien;

    public chiTietBangChamCongDTO() {
    }

    public chiTietBangChamCongDTO(String mabangchamcong, String ngay, String giovao, String giora) {
        this.mabangchamcong = mabangchamcong;
        this.ngay = ngay;
        this.giovao = giovao;
        this.giora = giora;
    }

    public chiTietBangChamCongDTO(String mabangchamcong, String ngay, String giovao, String giora, String tennhanvien, String manhanvien) {
        this.mabangchamcong = mabangchamcong;
        this.ngay = ngay;
        this.giovao = giovao;
        this.giora = giora;
        this.tennhanvien = tennhanvien;
        this.manhanvien = manhanvien;
    }

    public chiTietBangChamCongDTO(String ngay, String giovao, String giora) {
        this.ngay = ngay;
        this.giovao = giovao;
        this.giora = giora;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }




    public String getMabangchamcong() {
        return mabangchamcong;
    }

    public void setMabangchamcong(String mabangchamcong) {
        this.mabangchamcong = mabangchamcong;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGiovao() {
        return giovao;
    }

    public void setGiovao(String giovao) {
        this.giovao = giovao;
    }

    public String getGiora() {
        return giora;
    }

    public void setGiora(String giora) {
        this.giora = giora;
    }

}
