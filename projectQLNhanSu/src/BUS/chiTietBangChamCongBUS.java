/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.chiTietBangChamCongDAO;
import DTO.chiTietBangChamCongDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class chiTietBangChamCongBUS {

    public ArrayList<chiTietBangChamCongDTO> dschitietbangchamcong;

    public chiTietBangChamCongBUS() {

    }

    public void listChiTietBangChamCong() {
        chiTietBangChamCongDAO chitietbangchamcongDAO = new chiTietBangChamCongDAO();
        dschitietbangchamcong = new ArrayList<>();
        dschitietbangchamcong = chitietbangchamcongDAO.list();
    }

    public void listChiTietBangChamCong(String mabangchamcong) {
        chiTietBangChamCongDAO chitietbangchamcongDAO = new chiTietBangChamCongDAO();
        dschitietbangchamcong = new ArrayList<>();
        dschitietbangchamcong = chitietbangchamcongDAO.list(mabangchamcong);
    }


    public chiTietBangChamCongDTO get(String mabangchamcong, String ngay) {
        for (chiTietBangChamCongDTO chitietbangchamcong : dschitietbangchamcong) {
            if (chitietbangchamcong.getMabangchamcong().equals(mabangchamcong) && chitietbangchamcong.getNgay().equals(ngay)) {
                return chitietbangchamcong;
            }
        }
        return null;
    }

    public void addChiTietBangChamCong(chiTietBangChamCongDTO chitietbangchamcong) {
        dschitietbangchamcong = new ArrayList<>();
        dschitietbangchamcong.add(chitietbangchamcong);
        chiTietBangChamCongDAO chitietbangchamcongDAO = new chiTietBangChamCongDAO();
        chitietbangchamcongDAO.add(chitietbangchamcong);
    }

    public void deleteChiTietBangChamCong(String mabcc, String ngay) {
        for (chiTietBangChamCongDTO ctbcc : dschitietbangchamcong) {
            if (ctbcc.getMabangchamcong().equals(mabcc) && ctbcc.getNgay().equals(ngay)) {
                dschitietbangchamcong.remove(ctbcc);
                chiTietBangChamCongDAO ctbccDAO = new chiTietBangChamCongDAO();
                ctbccDAO.delete(mabcc, ngay);
                return;
            }
        }
    }

    public void setChiTietBangChamCong(chiTietBangChamCongDTO ctbcc) {
        for (int i = 0; i < dschitietbangchamcong.size(); i++) {
            if (dschitietbangchamcong.get(i).getMabangchamcong().equals(ctbcc.getMabangchamcong())
                    && dschitietbangchamcong.get(i).getNgay().equals(ctbcc.getNgay())) {
                dschitietbangchamcong.set(i, ctbcc);
                chiTietBangChamCongDAO ctbccDAO = new chiTietBangChamCongDAO();
                ctbccDAO.set(ctbcc);
                return;
            }
        }
    }

    public boolean checkNgay(String mabangchamcong, String thoigian) {
        for (chiTietBangChamCongDTO ctbcc : dschitietbangchamcong) {
            if (ctbcc.getMabangchamcong().equals(mabangchamcong) && ctbcc.getNgay().equals(thoigian)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<chiTietBangChamCongDTO> getList() {
        listChiTietBangChamCong();
        return dschitietbangchamcong;
    }

    public ArrayList<chiTietBangChamCongDTO> getList(String mabangchamcong) {
        listChiTietBangChamCong(mabangchamcong);
        return dschitietbangchamcong;
    }

}
