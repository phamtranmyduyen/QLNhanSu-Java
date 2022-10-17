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
public class nhanVienDTO {

    String manhanvien, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, tentaikhoan, mabangcap, mahdld, maphongban;

    public nhanVienDTO() {
    }

    public nhanVienDTO(String manhanvien, String hoten) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
    }

    public nhanVienDTO(String manhanvien, String hoten, String email, String sodienthoai, String ngaysinh, String diachi, String gioitinh, String cmnd, String tentaikhoan, String mabangcap, String mahdld, String maphongban) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.cmnd = cmnd;
        this.tentaikhoan = tentaikhoan;
        this.mabangcap = mabangcap;
        this.mahdld = mahdld;
        this.maphongban = maphongban;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setMataikhoan(String mataikhoan) {
        this.tentaikhoan = mataikhoan;
    }

    public String getMabangcap() {
        return mabangcap;
    }

    public void setMabangcap(String mabangcap) {
        this.mabangcap = mabangcap;
    }

    public String getMahdld() {
        return mahdld;
    }

    public void setMahdld(String mahdld) {
        this.mahdld = mahdld;
    }

    public String getMaphongban() {
        return maphongban;
    }

    public void setMaphongban(String maphongban) {
        this.maphongban = maphongban;
    }

}
