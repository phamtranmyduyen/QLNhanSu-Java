/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.heSoLuongDAO;
import DTO.heSoLuongDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class heSoLuongBUS {

    public ArrayList<heSoLuongDTO> dshesoluong;

    public heSoLuongBUS() {

    }

    public void listHeSoLuong() {
        heSoLuongDAO hesoluongDAO = new heSoLuongDAO();
        dshesoluong = new ArrayList<>();
        dshesoluong = hesoluongDAO.list();
        System.out.println(dshesoluong);
    }

    public heSoLuongDTO get(String mahesoluong) {
        for (heSoLuongDTO hesoluong : dshesoluong) {
            if (hesoluong.getMahesoluong().equals(mahesoluong)) {
                return hesoluong;
            }
        }
        return null;
    }

    public void addHeSoLuong(heSoLuongDTO hesoluong)
    {
        dshesoluong.add(hesoluong);
        heSoLuongDAO heSoLuongDAO = new heSoLuongDAO();
        heSoLuongDAO.add(hesoluong); 
    }
    public void deleteHeSoLuong(String mahsl) {
        for (heSoLuongDTO hsl : dshesoluong) {
            if (hsl.getMahesoluong().equals(mahsl)) {
                dshesoluong.remove(hsl);
                heSoLuongDAO hslDAO = new heSoLuongDAO();
                hslDAO.delete(mahsl);
                return;
            }
        }
    }
    public void setHeSoLuong(heSoLuongDTO hsl) {
        for (int i = 0; i < dshesoluong.size(); i++) {
            if (dshesoluong.get(i).getMahesoluong().equals(hsl.getMahesoluong())) {
                dshesoluong.set(i, hsl);
                heSoLuongDAO cnDAO = new heSoLuongDAO();
                cnDAO.set(hsl);
                return;
            }
        }
    }
    public boolean checkHeSoLuong(float hesoluong) {
        for (heSoLuongDTO cn : dshesoluong) {
            if (cn.getHesoluong()==hesoluong) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<heSoLuongDTO> getList() {
        return dshesoluong;
    }
      public String mahesoluong() {
        heSoLuongDAO hesoluongDAO = new heSoLuongDAO();
        String mahesoluong = hesoluongDAO.mahesoluong();
        return mahesoluong;
    }
}
