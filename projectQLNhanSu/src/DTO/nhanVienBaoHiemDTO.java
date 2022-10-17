package DTO;

public class nhanVienBaoHiemDTO {
    private String mabaohiem,manhanvien, ngaybatdau, ngayketthuc;
    public nhanVienBaoHiemDTO(){
    }

    public nhanVienBaoHiemDTO(String mabaohiem, String manhanvien, String ngaybatdau, String ngayketthuc) {
        this.mabaohiem = mabaohiem;
        this.manhanvien = manhanvien;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
    }

    public String getMabaohiem() {
        return mabaohiem;
    }

    public void setMabaohiem(String mabaohiem) {
        this.mabaohiem = mabaohiem;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

   
}
