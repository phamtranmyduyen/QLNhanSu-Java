package BUS;

import DAO.baoHiemDAO;
import DAO.baoHiemDAO;
import DTO.baoHiemDTO;
import java.util.ArrayList;

public class baoHiemBUS {

    public ArrayList<baoHiemDTO> dsbaohiem;
  

    public baoHiemBUS() {

    }

    public void listBaoHiem() {
        baoHiemDAO baoHiemDAO = new baoHiemDAO();
        dsbaohiem = new ArrayList<>();
        dsbaohiem = baoHiemDAO.list();
    }

   

    public baoHiemDTO get(String mabaohiem) {
        for (baoHiemDTO baohiem : dsbaohiem) {
            if (baohiem.getMabaohiem().equals(mabaohiem)) {
                return baohiem;
            }
        }
        return null;
    }

    public void addBaoHiem(baoHiemDTO baohiem) {
        dsbaohiem.add(baohiem);
        baoHiemDAO baoHiemDAO = new baoHiemDAO();
        baoHiemDAO.add(baohiem);
    }

    public void deleteBaoHiem(String mabh) {
        for (baoHiemDTO bh : dsbaohiem) {
            if (bh.getMabaohiem().equals(mabh)) {
                dsbaohiem.remove(bh);
                baoHiemDAO bhDAO = new baoHiemDAO();
                bhDAO.delete(mabh);
                return;
            }
        }
    }

    public void setBaoHiem(baoHiemDTO bh) {
        for (int i = 0; i < dsbaohiem.size(); i++) {
            if (dsbaohiem.get(i).getMabaohiem().equals(bh.getMabaohiem())) {
                dsbaohiem.set(i, bh);
                baoHiemDAO bhDAO = new baoHiemDAO();
                bhDAO.set(bh);
                return;
            }
        }
    }

    public boolean checkMaBaoHiem(String mabaohiem) {
        for (baoHiemDTO bh : dsbaohiem) {
            if (bh.getMabaohiem().equals(mabaohiem)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<baoHiemDTO> getList() {
        return dsbaohiem;
    }
  public String mabaohiem() {
        baoHiemDAO baohiemDAO = new baoHiemDAO();
        String mabaohiem = baohiemDAO.mabaohiem();
        return mabaohiem;
    }    
    
}
