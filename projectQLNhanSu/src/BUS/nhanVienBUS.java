/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.nhanVienDAO;
import DTO.nhanVienDTO;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class nhanVienBUS {

    public ArrayList<nhanVienDTO> dsnhanvien;

    public nhanVienBUS() {

    }

    public void listNhanVien() {
        nhanVienDAO nhanvienDAO = new nhanVienDAO();
        dsnhanvien = new ArrayList<>();
        dsnhanvien = nhanvienDAO.list();
    }

    public nhanVienDTO get(String tentaikhoan) {
        for (nhanVienDTO nhanvien : dsnhanvien) {
            if (nhanvien.getTentaikhoan().equals(tentaikhoan)) {
                return nhanvien;
            }
        }
        return null;
    }

    public nhanVienDTO getNV(String manhanvien) {
        for (nhanVienDTO nhanvien : dsnhanvien) {
            if (nhanvien.getManhanvien().equals(manhanvien)) {
                return nhanvien;
            }
        }
        return null;
    }

    public void addNhanVien(nhanVienDTO nhanvien) {
        dsnhanvien.add(nhanvien);
        nhanVienDAO nhanvienDAO = new nhanVienDAO();
        nhanvienDAO.add(nhanvien);
    }

    public void deleteNhanVien(String manv) {
        for (nhanVienDTO nv : dsnhanvien) {
            if (nv.getManhanvien().equals(manv)) {
                dsnhanvien.remove(nv);
                nhanVienDAO nvDAO = new nhanVienDAO();
                nvDAO.delete(manv);
                return;
            }
        }
    }

    public void setNhanVien(nhanVienDTO nv) {
        for (int i = 0; i < dsnhanvien.size(); i++) {
            if (dsnhanvien.get(i).getManhanvien().equals(nv.getManhanvien())) {
                dsnhanvien.set(i, nv);
                nhanVienDAO nvDAO = new nhanVienDAO();
                nvDAO.set(nv);
                return;
            }
        }
    }

    public boolean checkMaNhanVien(String manhanvien) {
        for (nhanVienDTO nv : dsnhanvien) {
            if (nv.getManhanvien().equals(manhanvien)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<nhanVienDTO> getList() {
        return dsnhanvien;
    }

    public void ExportExcelDatabase() {
        nhanVienDAO nvDAO = new nhanVienDAO();
        nvDAO.ExportExcelDatabase();
    }

    public void ImportExcelDatabase(File file) {
        nhanVienDAO nvDAO = new nhanVienDAO();
        nvDAO.ImportExcelDatabase(file);
    }
      public String manhanvien() {
        nhanVienDAO nhanvienDAO = new nhanVienDAO();
        String manhanvien = nhanvienDAO.manhanvien();
        return manhanvien;
    }

}
