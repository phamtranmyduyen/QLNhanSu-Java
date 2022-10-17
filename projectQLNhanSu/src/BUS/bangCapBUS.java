/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.bangCapDAO;
import DTO.bangCapDTO;
import java.util.ArrayList;

/**
 *
 * @author funty
 */
public class bangCapBUS {

    public ArrayList<bangCapDTO> dsbangcap;
 

    public bangCapBUS() {

    }

    public void listBangCap() {
        bangCapDAO bangcapDAO = new bangCapDAO();
        dsbangcap = new ArrayList<>();
        dsbangcap = bangcapDAO.list();
        System.out.println(dsbangcap);
    }

    

    

    public bangCapDTO get(String mabangcap) {
        for (bangCapDTO bangcap : dsbangcap) {
            if (bangcap.getMabangcap().equals(mabangcap)) {
                return bangcap;
            }
        }
        return null;
    }

    public void addBangCap(bangCapDTO bangcap) {
        dsbangcap.add(bangcap);
        bangCapDAO bangcapDAO = new bangCapDAO();
        bangcapDAO.add(bangcap);
    }

    public void deleteBangCap(String mabc) {
        for (bangCapDTO bc : dsbangcap) {
            if (bc.getMabangcap().equals(mabc)) {
                dsbangcap.remove(bc);
                bangCapDAO bcDAO = new bangCapDAO();
                bcDAO.delete(mabc);
                return;
            }
        }
    }

    public void setBangCap(bangCapDTO bc) {
        for (int i = 0; i < dsbangcap.size(); i++) {
            if (dsbangcap.get(i).getMabangcap().equals(bc.getMabangcap())) {
                dsbangcap.set(i, bc);
                bangCapDAO bcDAO = new bangCapDAO();
                bcDAO.set(bc);
                return;
            }
        }
    }

    public boolean checkMaBangCap(String mabangcap) {
        for (bangCapDTO bc : dsbangcap) {
            if (bc.getMabangcap().equals(mabangcap)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<bangCapDTO> getList() {
        return dsbangcap;
    }
      public String mabangcap() {
        bangCapDAO bangcapDAO = new bangCapDAO();
        String mabangcap = bangcapDAO.mabangcap();
        return mabangcap;
    }

}
