package BUS;

import DAO.baoHiemDAO;
import DAO.nhanVienBaoHiemDAO;
import DAO.nhanVienBaoHiemDAO;
import DTO.nhanVienBaoHiemDTO;
import java.util.ArrayList;

public class nhanVienBaoHiemBUS {

    public ArrayList<nhanVienBaoHiemDTO> dsnhanvienbaohiem;
    public ArrayList<String> dstenbaohiem;

    public nhanVienBaoHiemBUS() {

    }

    public void listNhanvienbaohiem() {
        nhanVienBaoHiemDAO nhanVienBaoHiemDAO = new nhanVienBaoHiemDAO();
        dsnhanvienbaohiem = new ArrayList<>();
        dsnhanvienbaohiem = nhanVienBaoHiemDAO.list();
        System.out.println(dsnhanvienbaohiem);
    }

    public void listNhanvienbaohiem(String manhanvien) {
        nhanVienBaoHiemDAO nhanVienBaoHiemDAO = new nhanVienBaoHiemDAO();
        dsnhanvienbaohiem = new ArrayList<>();
        dsnhanvienbaohiem = nhanVienBaoHiemDAO.list(manhanvien);
    }

    public nhanVienBaoHiemDTO get(String mabaohiem) {
        for (nhanVienBaoHiemDTO nhanvienbaohiem : dsnhanvienbaohiem) {
            if (nhanvienbaohiem.getMabaohiem().equals(mabaohiem)) {
                return nhanvienbaohiem;
            }
        }
        return null;
    }


    public void addNhanVienBaoHiem(nhanVienBaoHiemDTO nhanvienbaohiem) {
        dsnhanvienbaohiem.add(nhanvienbaohiem);
        nhanVienBaoHiemDAO nhanVienBaoHiemDAO = new nhanVienBaoHiemDAO();
        nhanVienBaoHiemDAO.add(nhanvienbaohiem);
    }

    public void deleteNhanVienBaoHiem(String mabh, String manhanvien, String ngaybatdau) {
        for (nhanVienBaoHiemDTO nvbh : dsnhanvienbaohiem) {
            if (nvbh.getMabaohiem().equals(mabh) && nvbh.getManhanvien().equals(manhanvien) && nvbh.getNgaybatdau().equals(ngaybatdau)) {
                dsnhanvienbaohiem.remove(nvbh);
                nhanVienBaoHiemDAO nvbhDAO = new nhanVienBaoHiemDAO();
                nvbhDAO.delete(mabh, manhanvien, ngaybatdau);
                return;
            }
        }
    }

    public void setNhanVienBaoHiem(nhanVienBaoHiemDTO nvbh) {
        for (int i = 0; i < dsnhanvienbaohiem.size(); i++) {
            if (dsnhanvienbaohiem.get(i).getMabaohiem().equals(nvbh.getMabaohiem())) {
                dsnhanvienbaohiem.set(i, nvbh);
                nhanVienBaoHiemDAO nvbhDAO = new nhanVienBaoHiemDAO();
                nvbhDAO.set(nvbh);
                return;
            }
        }
    }

    public boolean checkMaBaoHiem(String mabaohiem) {
        for (nhanVienBaoHiemDTO nvbh : dsnhanvienbaohiem) {
            if (nvbh.getMabaohiem().equals(mabaohiem)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<nhanVienBaoHiemDTO> getList() {
        return dsnhanvienbaohiem;
    }

    public ArrayList<nhanVienBaoHiemDTO> getList(String manhanvien) {
        listNhanvienbaohiem(manhanvien);
        System.out.println("dsnhanvienbaohiem"+dsnhanvienbaohiem);
        return dsnhanvienbaohiem;
    }

}
