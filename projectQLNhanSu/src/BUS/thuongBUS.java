/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.thuongDAO;
import DTO.thuongDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class thuongBUS {

    public ArrayList<thuongDTO> dsthuong;

    public thuongBUS() {

    }

    public void listThuong() {
        thuongDAO thuongDAO = new thuongDAO();
        dsthuong = new ArrayList<>();
        dsthuong = thuongDAO.list();
    }


    public thuongDTO get(String mathuong) {
        listThuong();
        for (thuongDTO thuong : dsthuong) {
            if (thuong.getMathuong().equals(mathuong)) {
                return thuong;
            }
        }
        return null;
    }

    public void addThuong(thuongDTO thuong) {
        dsthuong.add(thuong);
        thuongDAO thuongDAO = new thuongDAO();
        thuongDAO.add(thuong);
    }

    public void deleteThuong(String mat) {
        for (thuongDTO t : dsthuong) {
            if (t.getMathuong().equals(mat)) {
                dsthuong.remove(t);
                thuongDAO thuongDAO = new thuongDAO();
                thuongDAO.delete(mat);
                return;
            }
        }
    }

    public void setThuong(thuongDTO t) {
        for (int i = 0; i < dsthuong.size(); i++) {
            if (dsthuong.get(i).getMathuong().equals(t.getMathuong())) {
                dsthuong.set(i, t);
                thuongDAO thuongDAO = new thuongDAO();
                thuongDAO.set(t);
                return;
            }
        }
    }

    public boolean checkMaThuong(String mathuong) {
        for (thuongDTO t : dsthuong) {
            if (t.getMathuong().equals(mathuong)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<thuongDTO> getList() {
        return dsthuong;
    }
      public String mathuong() {
        thuongDAO thuongDAO = new thuongDAO();
        String mathuong = thuongDAO.mathuong();
        return mathuong;
    }
}
