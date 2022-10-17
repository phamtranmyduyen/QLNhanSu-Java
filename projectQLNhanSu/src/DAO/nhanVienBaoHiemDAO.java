package DAO;

import DAO.MySQLConnect;
import DTO.chucNangDTO;
import DTO.nhanVienBaoHiemDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class nhanVienBaoHiemDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public nhanVienBaoHiemDAO() {

    }

    public void set(nhanVienBaoHiemDTO nvbh) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE `nhanvien-baohiem` SET ";
        sql += "`Mã nhân viên`='" + nvbh.getManhanvien() + "', ";
        sql += "`Ngày bắt đầu='" + nvbh.getNgaybatdau() + "', ";
        sql += "`Ngày kết thúc`='" + nvbh.getNgayketthuc() + "' ";
        sql += " WHERE `Mã bảo hiểm`='" + nvbh.getMabaohiem() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(nhanVienBaoHiemDTO nvbh) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO `nhanvien-baohiem` VALUES (";
        sql += "'" + nvbh.getMabaohiem() + "',";
        sql += "'" + nvbh.getManhanvien() + "',";
        sql += "'" + nvbh.getNgaybatdau() + "',";
        sql += "'" + nvbh.getNgayketthuc() + "')";
        mySQL.executeUpdate(sql);
    }

    public void delete(String mabh, String manhanvien, String ngaybatdau) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM `nhanvien-baohiem` "
                + "WHERE `Mã bảo hiểm`='" + mabh + "' "
                + "AND `Mã nhân viên`='" + manhanvien + "' "
                + "AND `Ngày bắt đầu` = '" + ngaybatdau + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<nhanVienBaoHiemDTO> list() {
        ArrayList<nhanVienBaoHiemDTO> dsnhanvienbaohiem = new ArrayList<>();
        try {
            String sql = "SELECT * FROM  `nhanvien-baohiem`";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabaohiem = rs.getString("Mã bảo hiểm");
                String manhanvien = rs.getString("Mã nhân viên");
                String ngaybatdau = rs.getString("Ngày bắt đầu");
                String ngayketthuc = rs.getString("Ngày hết hạn");
                nhanVienBaoHiemDTO nhanvienbaohiem = new nhanVienBaoHiemDTO(mabaohiem, manhanvien, ngaybatdau, ngayketthuc);
                dsnhanvienbaohiem.add(nhanvienbaohiem);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsnhanvienbaohiem;
    }

    public ArrayList<nhanVienBaoHiemDTO> list(String manhanvienGET) {
        ArrayList<nhanVienBaoHiemDTO> dsnhanvienbaohiem = new ArrayList<>();
        try {
            String sql = "SELECT * FROM  `nhanvien-baohiem` WHERE `Mã nhân viên` = '" + manhanvienGET + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabaohiem = rs.getString("Mã bảo hiểm");
                String manhanvien = rs.getString("Mã nhân viên");
                String ngaybatdau = rs.getString("Ngày bắt đầu");
                String ngayketthuc = rs.getString("Ngày hết hạn");
                nhanVienBaoHiemDTO nhanvienbaohiem = new nhanVienBaoHiemDTO(mabaohiem, manhanvien, ngaybatdau, ngayketthuc);
                dsnhanvienbaohiem.add(nhanvienbaohiem);               
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảo hiểm");
        }
        return dsnhanvienbaohiem;
    }

}
