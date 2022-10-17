package BUS;

import DAO.chiTietChucNangDAO;
import DAO.chucNangDAO;
import DTO.chiTietChucNangDTO;
import java.util.ArrayList;

public class chiTietChucNangBUS {

    public ArrayList<chiTietChucNangDTO> dschitietchucnang;
    public ArrayList<String> tenchitietchucnang;
    public chiTietChucNangBUS() {

    }

    public void listChiTietChucNang() {
        chiTietChucNangDAO chiTietChucNangDAO = new chiTietChucNangDAO();
        dschitietchucnang = new ArrayList<>();
        dschitietchucnang = chiTietChucNangDAO.list();
        System.out.println(dschitietchucnang);
    }

    public void listChiTietChucNang(String machucnang) {
        chiTietChucNangDAO chiTietChucNangDAO = new chiTietChucNangDAO();
        dschitietchucnang = new ArrayList<>();
        dschitietchucnang = chiTietChucNangDAO.list(machucnang);
    }

    public void listTenChiTietChucNang(String tenchucnang) {
        chiTietChucNangDAO chiTietChucNangDAO = new chiTietChucNangDAO();
        tenchitietchucnang = new ArrayList<>();
        tenchitietchucnang = chiTietChucNangDAO.listTenChiTietChucNang(tenchucnang);
    }

    public chiTietChucNangDTO get(String machitietchucnang) {
        for (chiTietChucNangDTO chitietchucnang : dschitietchucnang) {
            if (chitietchucnang.getMachitietchucnang().equals(machitietchucnang)) {
                return chitietchucnang;
            }
        }
        return null;
    }

    public void addChiTietChucNang(chiTietChucNangDTO chitietchucnang) {
        dschitietchucnang.add(chitietchucnang);
        chiTietChucNangDAO chiTietChucNangDAO = new chiTietChucNangDAO();
        chiTietChucNangDAO.add(chitietchucnang);
    }

    public void deleteChiTietChucNang(String mactcn) {
        for (chiTietChucNangDTO ctcn : dschitietchucnang) {
            if (ctcn.getMachitietchucnang().equals(mactcn)) {
                dschitietchucnang.remove(ctcn);
                chiTietChucNangDAO ctcnDAO = new chiTietChucNangDAO();
                ctcnDAO.delete(mactcn);
                return;
            }
        }
    }

    public void setChiTietChucNang(chiTietChucNangDTO ctcn) {
        for (int i = 0; i < dschitietchucnang.size(); i++) {
            if (dschitietchucnang.get(i).getMachitietchucnang().equals(ctcn.getMachitietchucnang())) {
                dschitietchucnang.set(i, ctcn);
                chiTietChucNangDAO ctcnDAO = new chiTietChucNangDAO();
                ctcnDAO.set(ctcn);
                return;
            }
        }
    }

    public boolean checkMaChiTietChucNang(String machitietchucnang) {
        for (chiTietChucNangDTO ctcn : dschitietchucnang) {
            if (ctcn.getMachitietchucnang().equals(machitietchucnang)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getTenchitietchucnang(String tenchucnang) {
        listTenChiTietChucNang(tenchucnang);
        return tenchitietchucnang;
    }

    public ArrayList<chiTietChucNangDTO> getList(String machucnang) {
        listChiTietChucNang(machucnang);
        return dschitietchucnang;
    }

    public ArrayList<chiTietChucNangDTO> getList() {
        return dschitietchucnang;
    }
      public String machitietchucnang() {
        chiTietChucNangDAO chitietchucnangDAO = new chiTietChucNangDAO();
        String machitietchucnang = chitietchucnangDAO.machitietchucnang();
        return machitietchucnang;
    }


}
