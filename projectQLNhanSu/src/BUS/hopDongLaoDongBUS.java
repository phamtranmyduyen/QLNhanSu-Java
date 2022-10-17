/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.hopDongLaoDongDAO;
import DTO.hopDongLaoDongDTO;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class hopDongLaoDongBUS {
     public ArrayList<hopDongLaoDongDTO> dshdld;

    public hopDongLaoDongBUS() {

    }

    public void listhdld() {
        hopDongLaoDongDAO hdldDAO = new hopDongLaoDongDAO();
        dshdld = new ArrayList<>();
        dshdld = hdldDAO.list();

    }
     public void listhdld(String manhanvien) {
        hopDongLaoDongDAO hdldDAO = new hopDongLaoDongDAO();
        dshdld = new ArrayList<>();
        dshdld = hdldDAO.list(manhanvien);

    }

    public hopDongLaoDongDTO get(String mahdld) {
        for (hopDongLaoDongDTO hdld : dshdld) {
            if (hdld.getMahdld().equals(mahdld)) {
                return hdld;
            }
        }
        return null;
    }

    public void addHdld(hopDongLaoDongDTO hdld)
    {
        dshdld.add(hdld);
        hopDongLaoDongDAO hdldDAO = new hopDongLaoDongDAO();
        hdldDAO.add(hdld); 
    }
    public void deleteHdld(String mahdld) {
        for (hopDongLaoDongDTO hdld : dshdld) {
            if (hdld.getMahdld().equals(mahdld)) {
                dshdld.remove(hdld);
                hopDongLaoDongDAO hdldDAO = new hopDongLaoDongDAO();
                hdldDAO.delete(mahdld);
                return;
            }
        }
    }
    public void setHdld(hopDongLaoDongDTO hdld) {
        for (int i = 0; i < dshdld.size(); i++) {
            if (dshdld.get(i).getMahdld().equals(hdld.getMahdld())) {
                dshdld.set(i, hdld);
                hopDongLaoDongDAO hdldDAO = new hopDongLaoDongDAO();
                hdldDAO.set(hdld);
                return;
            }
        }
    }
    public boolean checkMahdld(String mahdld) {
        for (hopDongLaoDongDTO hdld : dshdld) {
            if (hdld.getMahdld().equals(mahdld)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<hopDongLaoDongDTO> getList() {
        return dshdld;
    }
    public ArrayList<hopDongLaoDongDTO> getList(String manhanvien) {
        listhdld(manhanvien);
        return dshdld;
    }
      public String mahopdonglaodong() {
        hopDongLaoDongDAO hopdonglaodongDAO = new hopDongLaoDongDAO();
        String mahopdonglaodong = hopdonglaodongDAO.mahopdonglaodong();
        return mahopdonglaodong;
    }
}
