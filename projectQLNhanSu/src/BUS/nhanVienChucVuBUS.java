package BUS;

import DAO.nhanVienChucVuDAO;
import DTO.nhanVienChucVuDTO;
import java.util.ArrayList;

public class nhanVienChucVuBUS {

    public ArrayList<nhanVienChucVuDTO> dsnhanvienchucvu;

    public nhanVienChucVuBUS() {

    }

    public void listNhanVienChucVu(String manhanvien) {
        nhanVienChucVuDAO nvcvDAO = new nhanVienChucVuDAO();
        dsnhanvienchucvu = new ArrayList<>();
        dsnhanvienchucvu = nvcvDAO.list(manhanvien);
    }

    public void listNhanVienChucVu() {
        nhanVienChucVuDAO nhanVienChucVuDAO = new nhanVienChucVuDAO();
        dsnhanvienchucvu = new ArrayList<>();
        dsnhanvienchucvu = nhanVienChucVuDAO.list();
    }

    public nhanVienChucVuDTO get(String machucvu) {
        for (nhanVienChucVuDTO nhanvienchucvu : dsnhanvienchucvu) {
            if (nhanvienchucvu.getMachucvu().equals(machucvu)) {
                return nhanvienchucvu;
            }
        }
        return null;
    }

    public void addNhanVienChucVu(nhanVienChucVuDTO nhanvienchucvu) {
        dsnhanvienchucvu.add(nhanvienchucvu);
        nhanVienChucVuDAO nhanVienChucVuDAO = new nhanVienChucVuDAO();
        nhanVienChucVuDAO.add(nhanvienchucvu);
    }



    public void deleteNhanVienChucVu(String macv, String manhanvien, String ngaybatdau) {
        for (nhanVienChucVuDTO cv : dsnhanvienchucvu) {
            if (cv.getMachucvu().equals(macv)&& cv.getManv().equals(manhanvien) && cv.getNgaybatdau().equals(ngaybatdau)) {
                dsnhanvienchucvu.remove(cv);
                nhanVienChucVuDAO cvDAO = new nhanVienChucVuDAO();
                cvDAO.delete(macv, manhanvien, ngaybatdau);
                return;
            }
        }
    }

    public void setNhanVienChucVu(nhanVienChucVuDTO cv) {
        for (int i = 0; i < dsnhanvienchucvu.size(); i++) {
            if (dsnhanvienchucvu.get(i).getMachucvu().equals(cv.getMachucvu())) {
                dsnhanvienchucvu.set(i, cv);
                nhanVienChucVuDAO cvDAO = new nhanVienChucVuDAO();
                cvDAO.set(cv);
                return;
            }
        }
    }

    public boolean checkMaChucVu(String machucvu) {
        for (nhanVienChucVuDTO cn : dsnhanvienchucvu) {
            if (cn.getMachucvu().equals(machucvu)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<nhanVienChucVuDTO> getList() {
        return dsnhanvienchucvu;
    }

    public ArrayList<nhanVienChucVuDTO> getList(String manhanvien) {
        listNhanVienChucVu(manhanvien);
        return dsnhanvienchucvu;
    }

}
