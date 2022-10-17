/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author funty
 */
public class bangCapDTO {
    private String mabangcap, tenbangcap, ngaybatdauhieuluc, ngayketthuchieuluc;

    public String getMabangcap() {
        return mabangcap;
    }

    public void setMabangcap(String mabangcap) {
        this.mabangcap = mabangcap;
    }

    public String getTenbangcap() {
        return tenbangcap;
    }

    public void setTenbangcap(String tenbangcap) {
        this.tenbangcap = tenbangcap;
    }

    public String getNgaybatdauhieuluc() {
        return ngaybatdauhieuluc;
    }

    public void setNgaybatdauhieuluc(String ngaybatdauhieuluc) {
        this.ngaybatdauhieuluc = ngaybatdauhieuluc;
    }

    public String getNgayketthuchieuluc() {
        return ngayketthuchieuluc;
    }

    public void setNgayketthuchieuluc(String ngayketthuchieuluc) {
        this.ngayketthuchieuluc = ngayketthuchieuluc;
    }

    public bangCapDTO(String mabangcap, String tenbangcap, String ngaybatdauhieuluc, String ngayketthuchieuluc) {
        this.mabangcap = mabangcap;
        this.tenbangcap = tenbangcap;
        this.ngaybatdauhieuluc = ngaybatdauhieuluc;
        this.ngayketthuchieuluc = ngayketthuchieuluc;
    }
}
