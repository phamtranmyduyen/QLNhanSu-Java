package BUS;

import DAO.chucVuDAO;
import DAO.chucVuDAO;
import DTO.chucVuDTO;
import java.util.ArrayList;

public class chucVuBUS {

    public ArrayList<chucVuDTO> dschucvu;
    

    public chucVuBUS() {

    }

    public void listChucVu() {
        chucVuDAO chucVuDAO = new chucVuDAO();
        dschucvu = new ArrayList<>();
        dschucvu = chucVuDAO.list();
        System.out.println(dschucvu);
    }

    

    public chucVuDTO get(String machucvu) {
        for (chucVuDTO chucvu : dschucvu) {
            if (chucvu.getMachucvu().equals(machucvu)) {
                return chucvu;
            }
        }
        return null;
    }

    public void addChucVu(chucVuDTO chucvu) {
        dschucvu.add(chucvu);
        chucVuDAO chucVuDAO = new chucVuDAO();
        chucVuDAO.add(chucvu);
    }

    public void deleteChucVu(String macv) {
        for (chucVuDTO cv : dschucvu) {
            if (cv.getMachucvu().equals(macv)) {
                dschucvu.remove(cv);
                chucVuDAO cvDAO = new chucVuDAO();
                cvDAO.delete(macv);
                return;
            }
        }
    }

    public void setChucVu(chucVuDTO cv) {
        for (int i = 0; i < dschucvu.size(); i++) {
            if (dschucvu.get(i).getMachucvu().equals(cv.getMachucvu())) {
                dschucvu.set(i, cv);
                chucVuDAO cvDAO = new chucVuDAO();
                cvDAO.set(cv);
                return;
            }
        }
    }

    public boolean checkMaChucVu(String machucvu) {
        for (chucVuDTO cv : dschucvu) {
            if (cv.getMachucvu().equals(machucvu)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<chucVuDTO> getList() {
        return dschucvu;
    }
      public String machucvu() {
        chucVuDAO chucvuDAO = new chucVuDAO();
        String machucvu = chucvuDAO.machucvu();
        return machucvu;
    }
}
