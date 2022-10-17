/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.chucNangDAO;
import DTO.chucNangDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class chucNangBUS {

    public ArrayList<chucNangDTO> dschucnang;
    private ArrayList<String> dsChiTietChucNangKhongThuoc, dsCNCoCTCN;
    private String[] listTenChucNang;

    public chucNangBUS() {

    }

    public void listTenChucNang() {
        if (getList() == null) {
            listChucNang();
        }
        ArrayList<chucNangDTO> cn = getList();
        listTenChucNang = new String[cn.size()];
        int i = 0;
        for (chucNangDTO chucnang : cn) {
            listTenChucNang[i] = chucnang.getTenchucnang();
            i++;
        }
    }

    public String[] getTenChucNang() {
        listTenChucNang();
        return listTenChucNang;
    }

    public void listChucNang() {
        chucNangDAO chucnangDAO = new chucNangDAO();
        dschucnang = new ArrayList<>();
        dschucnang = chucnangDAO.list();
    }

    public chucNangDTO get(String machucnang) {
        for (chucNangDTO chucnang : dschucnang) {
            if (chucnang.getMachucnang().equals(machucnang)) {
                return chucnang;
            }
        }
        return null;
    }

    public void addChucNang(chucNangDTO chucnang) {
        dschucnang.add(chucnang);
        chucNangDAO chucnangDAO = new chucNangDAO();
        chucnangDAO.add(chucnang);
    }

    public void deleteChucNang(String macn) {
        for (chucNangDTO cn : dschucnang) {
            if (cn.getMachucnang().equals(macn)) {
                dschucnang.remove(cn);
                chucNangDAO cnDAO = new chucNangDAO();
                cnDAO.delete(macn);
                return;
            }
        }
    }

    public void setChucNang(chucNangDTO cn) {
        System.out.println(dschucnang.size());
        for (int i = 0; i < dschucnang.size(); i++) {
            if (dschucnang.get(i).getMachucnang().equals(cn.getMachucnang())) {
                dschucnang.set(i, cn);
                chucNangDAO cnDAO = new chucNangDAO();
                cnDAO.set(cn);
                return;
            }
        }
    }

    public boolean checkMaChucNang(String machucnang) {
        for (chucNangDTO cn : dschucnang) {
            if (cn.getMachucnang().equals(machucnang)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<chucNangDTO> getList() {
        return dschucnang;
    }

    public ArrayList<String> getChiTietChucNangKhongThuoc(String macn) {
        chucNangDAO cnDAO = new chucNangDAO();
        dsChiTietChucNangKhongThuoc = new ArrayList<>();
        dsChiTietChucNangKhongThuoc = cnDAO.getChiTietChucNangKhongThuoc(macn);
        System.out.println(dsChiTietChucNangKhongThuoc);
        return dsChiTietChucNangKhongThuoc;
    }

    public String layTenCNThoaSL(int soluong) {
        chucNangDAO cnDAO = new chucNangDAO();
        String tenchucnang = cnDAO.layTenCNThoaSL(soluong);
        return tenchucnang;

    }
    public ArrayList<String> layTenCNThuocCTCN() {
        chucNangDAO cnDAO = new chucNangDAO();
        dsCNCoCTCN = new ArrayList<>();
        dsCNCoCTCN = cnDAO.layTenCNCoCTCN();
        return dsCNCoCTCN;
    }
  public String machucnang() {
        chucNangDAO chucnangDAO = new chucNangDAO();
        String machucnang = chucnangDAO.machucnang();
        return machucnang;
    }
}
