/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.taiKhoanDAO;
import DTO.taiKhoanDTO;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author MaiThy
 */
public class taiKhoanBUS {

    public ArrayList<taiKhoanDTO> dstaikhoan;
    public ArrayList<String> dsChucNangKhongThuocQuyen;

    public taiKhoanBUS() {

    }

    public void listTaiKhoan() {
        taiKhoanDAO taikhoanDAO = new taiKhoanDAO();
        dstaikhoan = new ArrayList<>();
        dstaikhoan = taikhoanDAO.list();
        System.out.println(dstaikhoan);
    }

    public void listTaiKhoan(String tentaikhoan) {
        taiKhoanDAO taikhoanDAO = new taiKhoanDAO();
        dstaikhoan = new ArrayList<>();
        dstaikhoan = taikhoanDAO.list(tentaikhoan);
        System.out.println(dstaikhoan);
    }

    public taiKhoanDTO get(String tentaikhoan) {
        for (taiKhoanDTO taikhoan : dstaikhoan) {
            if (taikhoan.getTentaikhoan().equals(tentaikhoan)) {
                return taikhoan;
            }
        }
        return null;
    }

    public void addTaiKhoan(taiKhoanDTO taikhoan) {
        dstaikhoan.add(taikhoan);
        taiKhoanDAO taikhoanDAO = new taiKhoanDAO();
        taikhoanDAO.add(taikhoan);
    }

    public void deleteTaiKhoan(String matk) {
        for (taiKhoanDTO tk : dstaikhoan) {
            if (tk.getTentaikhoan().equals(matk)) {
                dstaikhoan.remove(tk);
                taiKhoanDAO tkDAO = new taiKhoanDAO();
                tkDAO.delete(matk);
                return;
            }
        }
    }

    public void setTaiKhoan(taiKhoanDTO tk) {
        for (int i = 0; i < dstaikhoan.size(); i++) {
            if (dstaikhoan.get(i).getTentaikhoan().equals(tk.getTentaikhoan())) {
                dstaikhoan.set(i, tk);
                taiKhoanDAO tkDAO = new taiKhoanDAO();
                tkDAO.set(tk);
                return;
            }
        }
    }

    public boolean checkTenTaiKhoan(String tentaikhoan) {
        for (taiKhoanDTO tk : dstaikhoan) {
            if (tk.getTentaikhoan().equals(tentaikhoan)) {
                return true;
            }
        }
        return false;
    }

    public taiKhoanDTO checkLogin(String userName, char[] pass) {

        for (taiKhoanDTO tk : dstaikhoan) {
            char[] correctPass = tk.getMatkhau().toCharArray();
            if (tk.getTentaikhoan().equals(userName) && Arrays.equals(pass, correctPass)) {
                return tk;
            }

        }
        return null;
    }

    public boolean checkTrangThai(String userName) {
        boolean flag = true;
        for (taiKhoanDTO tk : dstaikhoan) {
            if (tk.getTentaikhoan().equals(userName) && tk.getTrangthai().equals("1")) {
                System.out.println(tk.getTrangthai());
                flag = false;
            }

        }
        return flag;
    }

    public ArrayList<taiKhoanDTO> getList() {
        return dstaikhoan;
    }

    public ArrayList<String> getChucNangKhongThuocQuyen(String maquyen) {
        taiKhoanDAO tkDAO = new taiKhoanDAO();
        dsChucNangKhongThuocQuyen = new ArrayList<>();
        dsChucNangKhongThuocQuyen = tkDAO.getChucNangKhongThuocQuyen(maquyen);
        System.out.println(dsChucNangKhongThuocQuyen);
        return dsChucNangKhongThuocQuyen;
    }

    public void KhoaTaiKhoan(String tentaikhoan) {
        taiKhoanDAO taikhoanDAO = new taiKhoanDAO();
        taikhoanDAO.KhoaTaiKhoan(tentaikhoan);
    }

    public void MoKhoaTaiKhoan(String tentaikhoan) {
        taiKhoanDAO taikhoanDAO = new taiKhoanDAO();
        taikhoanDAO.MoKhoaTaiKhoan(tentaikhoan);
    }
}
