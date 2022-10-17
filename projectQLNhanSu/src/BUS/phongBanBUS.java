/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.phongBanDAO;
import DTO.phongBanDTO;
import java.util.ArrayList;
/**
 *
 * @author funty
 */
public class phongBanBUS {
    public ArrayList<phongBanDTO> dsphongban;

    public phongBanBUS() {

    }
public void listPhongBan() {
        phongBanDAO phongbanDAO = new phongBanDAO();
        dsphongban = new ArrayList<>();
        dsphongban = phongbanDAO.list();
        System.out.println(dsphongban);
    }
public phongBanDTO get(String maphong) {
        for (phongBanDTO phongban : dsphongban) {
            if (phongban.getMaphong().equals(maphong)) {
                return phongban;
            }
        }
        return null;
    }
public void addPhongBan(phongBanDTO phongban)
    {
        dsphongban.add(phongban);
        phongBanDAO phongbanDAO = new phongBanDAO();
        phongbanDAO.add(phongban); 
    }
    public void deletePhongBan(String maphong) {
        for (phongBanDTO pb : dsphongban) {
            if (pb.getMaphong().equals(maphong)) {
                dsphongban.remove(pb);
                phongBanDAO pbDAO = new phongBanDAO();
                pbDAO.delete(maphong);
                return;
            }
        }
    }
public void setPhongBan(phongBanDTO pb) {
        for (int i = 0; i < dsphongban.size(); i++) {
            if (dsphongban.get(i).getMaphong().equals(pb.getMaphong())) {
                dsphongban.set(i, pb);
                phongBanDAO pbDAO = new phongBanDAO();
                pbDAO.set(pb);
                return;
            }
        }
    }
    public boolean checkMaPhongBan(String maphong) {
        for (phongBanDTO pb : dsphongban) {
            if (pb.getMaphong().equals(maphong)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<phongBanDTO> getList() {
        return dsphongban;
    }
      public String maphongban() {
        phongBanDAO phongbanDAO = new phongBanDAO();
        String maphongban = phongbanDAO.maphongban();
        return maphongban;
    }
}

