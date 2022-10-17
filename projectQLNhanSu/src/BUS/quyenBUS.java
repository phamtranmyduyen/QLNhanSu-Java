/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.quyenDAO;
import DTO.quyenDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class quyenBUS {

    public ArrayList<quyenDTO> dsquyen;
    public ArrayList<String> dschucnang;

    public quyenBUS() {

    }

    public void listQuyen() {
        quyenDAO quyenDAO = new quyenDAO();
        dsquyen = new ArrayList<>();
        dsquyen = quyenDAO.list();
        System.out.println(dsquyen);
    }

    public quyenDTO get(String maquyen) {
        for (quyenDTO quyen : dsquyen) {
            if (quyen.getMaquyen().equals(maquyen)) {
                return quyen;
            }
        }
        return null;
    }

    public void addQuyen(quyenDTO quyen) {
        dsquyen.add(quyen);
        quyenDAO quyenDAO = new quyenDAO();
        quyenDAO.add(quyen);
    }

    public void addChucNangQuyen(String maquyen, ArrayList<String> listChucNang) {
        quyenDAO quyenDAO = new quyenDAO();
        quyenDAO.addChucNangQuyen(maquyen, listChucNang);
    }

    public void listChucNang(String maquyen) {
        quyenDAO quyenDAO = new quyenDAO();
        dschucnang = new ArrayList<>();
        dschucnang= quyenDAO.getChucNang(maquyen);
    }
    public ArrayList<String> getChucNang() {
        return dschucnang;
    }

    public void deleteQuyen(String maq) {
        for (quyenDTO q : dsquyen) {
            if (q.getMaquyen().equals(maq)) {
                dsquyen.remove(q);
                quyenDAO qDAO = new quyenDAO();
                qDAO.delete(maq);
                return;
            }
        }
    }

    public void setQuyen(quyenDTO q) {
        for (int i = 0; i < dsquyen.size(); i++) {
            if (dsquyen.get(i).getMaquyen().equals(q.getMaquyen())) {
                dsquyen.set(i, q);
                quyenDAO qDAO = new quyenDAO();
                qDAO.set(q);
                return;
            }
        }
    }

    public boolean checkMaQuyen(String maquyen) {
        for (quyenDTO q : dsquyen) {
            if (q.getMaquyen().equals(maquyen)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<quyenDTO> getList() {
        return dsquyen;
    }

}
