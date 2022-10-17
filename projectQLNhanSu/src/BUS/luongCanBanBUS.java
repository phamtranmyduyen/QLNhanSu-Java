/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.luongCanBanDAO;
import DTO.luongCanBanDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class luongCanBanBUS {

    public ArrayList<luongCanBanDTO> dsluongcanban;

    public luongCanBanBUS() {

    }

    public void listLuongCanBan() {
        luongCanBanDAO luongcanbanDAO = new luongCanBanDAO();
        dsluongcanban = new ArrayList<>();
        dsluongcanban = luongcanbanDAO.list();
    }

    public luongCanBanDTO get(String maluongcanban) {
        
        for (luongCanBanDTO luongcanban : dsluongcanban) {
            if (luongcanban.getMaluongcanban().equals(maluongcanban)) {
                return luongcanban;
            }
        }
        return null;
    }

    public void addLuongCanBan(luongCanBanDTO luongcanban) {
        dsluongcanban.add(luongcanban);
        luongCanBanDAO luongCanBanDAO = new luongCanBanDAO();
        luongCanBanDAO.add(luongcanban);
    }

    public void deleteLuongCanBan(String malcb) {
        for (luongCanBanDTO lcb : dsluongcanban) {
            if (lcb.getMaluongcanban().equals(malcb)) {
                dsluongcanban.remove(lcb);
                luongCanBanDAO lcbDAO = new luongCanBanDAO();
                lcbDAO.delete(malcb);
                return;
            }
        }
    }

    public void setLuongCanBan(luongCanBanDTO lcb) {
        for (int i = 0; i < dsluongcanban.size(); i++) {
            if (dsluongcanban.get(i).getMaluongcanban().equals(lcb.getMaluongcanban())) {
                dsluongcanban.set(i, lcb);
                luongCanBanDAO cnDAO = new luongCanBanDAO();
                cnDAO.set(lcb);
                return;
            }
        }
    }

    public boolean checkMaLuongCanBan(String maluongcanban) {
        for (luongCanBanDTO cn : dsluongcanban) {
            if (cn.getMaluongcanban().equals(maluongcanban)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<luongCanBanDTO> getList() {
        return dsluongcanban;
    }
  public String maluongcanban() {
        luongCanBanDAO luongcanbanDAO = new luongCanBanDAO();
        String maluongcanban = luongcanbanDAO.maluongcanban();
        return maluongcanban;
    }

}
