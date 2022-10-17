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
public class taiKhoanDTO {
    String tentaikhoan, matkhau, trangthai, maquyen;

    public taiKhoanDTO() {
    }

    public taiKhoanDTO(String tentaikhoan, String matkhau, String trangthai, String maquyen) {
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.trangthai = trangthai;
        this.maquyen = maquyen;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(String maquyen) {
        this.maquyen = maquyen;
    }
    
}
