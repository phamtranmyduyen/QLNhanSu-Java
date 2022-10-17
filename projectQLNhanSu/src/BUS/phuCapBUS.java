/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.phuCapDAO;
import DTO.phuCapDTO;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class phuCapBUS {

    public ArrayList<phuCapDTO> dsphucap;

    public phuCapBUS() {

    }

    public void listPhuCap() {
        phuCapDAO phuCapDAO = new phuCapDAO();
        dsphucap = new ArrayList<>();
        dsphucap = phuCapDAO.list();
        System.out.println(dsphucap);
    }

    public phuCapDTO get(String maphucap) {
        for (phuCapDTO phucap : dsphucap) {
            if (phucap.getMaphucap().equals(maphucap)) {
                return phucap;
            }
        }
        return null;
    }

    public void addPhuCap(phuCapDTO phucap)
    {
        dsphucap.add(phucap);
        phuCapDAO phuCapDAO = new phuCapDAO();
        phuCapDAO.add(phucap); 
    }
    public void deletePhuCap(String mapc) {
        for (phuCapDTO pc : dsphucap) {
            if (pc.getMaphucap().equals(mapc)) {
                dsphucap.remove(pc);
                phuCapDAO pcDAO = new phuCapDAO();
                pcDAO.delete(mapc);
                return;
            }
        }
    }
    public void setPhuCap(phuCapDTO pc) {
        for (int i = 0; i < dsphucap.size(); i++) {
            if (dsphucap.get(i).getMaphucap().equals(pc.getMaphucap())) {
                dsphucap.set(i, pc);
                phuCapDAO pcDAO = new phuCapDAO();
                pcDAO.set(pc);
                return;
            }
        }
    }
    public boolean checkMaPhuCap(String maphucap) {
        for (phuCapDTO pc : dsphucap) {
            if (pc.getMaphucap().equals(maphucap)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<phuCapDTO> getList() {
        return dsphucap;
    }
      public String maphucap() {
        phuCapDAO phucapDAO = new phuCapDAO();
        String maphucap = phucapDAO.maphucap();
        return maphucap;
    }
}
