package DTO;

public class nhanVienChucVuDTO {
    private String machucvu , manv , ngaybatdau , ngayketthuc ;
    public nhanVienChucVuDTO()
    {
    
    }

    public String getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(String machucvu) {
        this.machucvu = machucvu;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
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

    public nhanVienChucVuDTO(String machucvu, String manv, String ngaybatdau, String ngayketthuc) {
        this.machucvu = machucvu;
        this.manv = manv;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
    }
    
}
