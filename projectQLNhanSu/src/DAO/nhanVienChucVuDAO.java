package DAO;

import DAO.MySQLConnect;
import DTO.nhanVienChucVuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class nhanVienChucVuDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public nhanVienChucVuDAO() {

    }

    public void set(nhanVienChucVuDTO nvcv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE `nhanvien-chucvu` SET ";
        sql += "`Mã nhân viên`='" + nvcv.getManv() + "',";
        sql += " WHERE `Mã chức vụ`='" + nvcv.getMachucvu() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(nhanVienChucVuDTO nvcv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "";
        if (nvcv.getNgayketthuc().equals("")) {
            sql = "INSERT INTO `nhanvien-chucvu`(`Mã chức vụ`, `Mã nhân viên`, `Ngày bắt đầu`) VALUES (";
            sql += "'" + nvcv.getMachucvu() + "',";
            sql += "'" + nvcv.getManv() + "',";
            sql += "'" + nvcv.getNgaybatdau() + "')";
            System.out.println(sql);
        } else {
            sql = "INSERT INTO `nhanvien-chucvu` VALUES (";
            sql += "'" + nvcv.getMachucvu() + "',";
            sql += "'" + nvcv.getManv() + "',";
            sql += "'" + nvcv.getNgaybatdau() + "',";
            sql += "'" + nvcv.getNgayketthuc() + "')";
            System.out.println(sql);

        }
        mySQL.executeUpdate(sql);
    }

    public void delete(String macv, String manhanvien, String ngaybatdau) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM `nhanvien-chucvu` "
                + "WHERE `Mã chức vụ`='" + macv + "' "
                + "AND `Mã nhân viên`='" + manhanvien + "' "
                + "AND `Ngày bắt đầu` = '" + ngaybatdau + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<nhanVienChucVuDTO> list() {
        ArrayList<nhanVienChucVuDTO> dsnhanvienchucvu = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `nhanvien-chucvu`";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machucvu = rs.getString("Mã chức vụ");
                String manv = rs.getString("Mã nhân viên");
                String ngaybatdau = rs.getString("Ngày bắt đầu");
                String ngayketthuc = rs.getString("Ngày kết thúc");
                nhanVienChucVuDTO nhanvienchucvu = new nhanVienChucVuDTO(machucvu, manv, ngaybatdau, ngayketthuc);
                dsnhanvienchucvu.add(nhanvienchucvu);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsnhanvienchucvu;
    }

    public ArrayList<nhanVienChucVuDTO> list(String manhanvien) {
        ArrayList<nhanVienChucVuDTO> dsnhanvienchucvu = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `nhanvien-chucvu` WHERE `Mã nhân viên` = '" + manhanvien + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machucvu = rs.getString("Mã chức vụ");
                String manv = rs.getString("Mã nhân viên");
                String ngaybatdau = rs.getString("Ngày bắt đầu");
                String ngayketthuc = rs.getString("Ngày kết thúc");
                nhanVienChucVuDTO nhanvienchucvu = new nhanVienChucVuDTO(machucvu, manv, ngaybatdau, ngayketthuc);
                dsnhanvienchucvu.add(nhanvienchucvu);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsnhanvienchucvu;
    }

}
