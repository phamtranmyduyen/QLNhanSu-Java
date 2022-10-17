/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.luongDAO;
import DTO.luongDTO;
import DTO.bangChamCongDTO;
import DAO.bangChamCongDAO;
import DTO.nhanVienDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class luongBUS {

//    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    public ArrayList<luongDTO> dsluong;
//    public ArrayList<hopDongLaoDongDTO> thoigianlamviec;
//    public ArrayList<chiTietLuongDTO> thoigianvaolam;

    public luongBUS() {

    }

    public luongDTO get(String maluong) {
        for (luongDTO luong : dsluong) {
            if (luong.getMaluong().equals(maluong)) {
                return luong;
            }
        }
        return null;
    }

    public void addLuong(luongDTO luong) {
        dsluong.add(luong);
        luongDAO luongDAO = new luongDAO();
        luongDAO.add(luong);
    }

    public void deleteLuong(String mal) {
        for (luongDTO l : dsluong) {
            if (l.getMaluong().equals(mal)) {
                dsluong.remove(l);
                luongDAO lDAO = new luongDAO();
                lDAO.delete(mal);
                return;
            }
        }
    }

    public void deleteLuong() {
        for (luongDTO l : dsluong) {
            dsluong.remove(l);
            luongDAO lDAO = new luongDAO();
            lDAO.delete();
            return;
        }
    }

    public void setLuong(luongDTO l) {
        for (int i = 0; i < dsluong.size(); i++) {
            if (dsluong.get(i).getMaluong().equals(l.getMaluong())) {
                dsluong.set(i, l);
                luongDAO lDAO = new luongDAO();
                lDAO.set(l);
                return;
            }
        }
    }

    public boolean checkMaLuong(String maluong) {
        for (luongDTO l : dsluong) {
            if (l.getMaluong().equals(maluong)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<luongDTO> getList(String manhanvien) {
        listLuong(manhanvien);
        return dsluong;
    }

    public ArrayList<luongDTO> getList() {
        return dsluong;
    }

//    public ArrayList<luongDTO> getListThoiGianLamViec(String manhanvien) {
//        listThoiGianLamViec(manhanvien);
//        return thoigianlamviec;
//    }
//    public ArrayList<chiTietLuongDTO> getListThoiGianVaoLam(String manhanvien) {
//        listThoiGianVaoLam(manhanvien);
//        return thoigianvaolam;
//    }
    public void listLuong(String manhanvien) {
        luongDAO luongDAO = new luongDAO();
        dsluong = new ArrayList<>();
        dsluong = luongDAO.list(manhanvien);
    }

    public void listLuong() {
        luongDAO luongDAO = new luongDAO();
        dsluong = new ArrayList<>();
        dsluong = luongDAO.list();
    }

    public String maluong() {
        luongDAO luongDAO = new luongDAO();
        String maluong = luongDAO.maluong();
        return maluong;
    }
}

//    public static void main(String[] args) {
//        luongBUS b = new luongBUS();
//        b.soGioLamThem("NV002", "BCC004");
//    }

