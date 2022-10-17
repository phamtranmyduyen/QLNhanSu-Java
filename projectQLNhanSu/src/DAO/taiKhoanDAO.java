/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.taiKhoanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class taiKhoanDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public taiKhoanDAO() {

    }

    public void set(taiKhoanDTO tk) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE taikhoan SET ";
        sql += "`Tên tài khoản`='" + tk.getTentaikhoan() + "', ";
        sql += "`Mật khẩu`='" + tk.getMatkhau() + "', ";
        sql += "`Trạng thái`='" + tk.getTrangthai() + "', ";
        sql += "`Mã quyền`='" + tk.getMaquyen() + " ";
        sql += " WHERE `Tên tài khoản`='" + tk.getTentaikhoan() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(taiKhoanDTO tk) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO taikhoan VALUES (";
        sql += "'" + tk.getTentaikhoan() + "',";
        sql += "'" + tk.getMatkhau() + "',";
        sql += "'" + tk.getTrangthai() + "',";
        sql += "'" + tk.getMaquyen() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String matk) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM taikhoan WHERE `Tên tài khoản`='" + matk + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<taiKhoanDTO> list() {
        ArrayList<taiKhoanDTO> dstaikhoan = new ArrayList<>();
        try {
            String sql = "SELECT * FROM taikhoan";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String tentaikhoan = rs.getString("Tên tài khoản");
                String matkhau = rs.getString("Mật khẩu");
                String trangthai = rs.getString("Trạng thái");
                String maquyen = rs.getString("Mã quyền");
                taiKhoanDTO taikhoan = new taiKhoanDTO(tentaikhoan, matkhau, trangthai, maquyen);
                dstaikhoan.add(taikhoan);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load tài khoản");
        }
        return dstaikhoan;
    }
    public ArrayList<taiKhoanDTO> list(String tentaikhoanGET) {
        ArrayList<taiKhoanDTO> dstaikhoan = new ArrayList<>();
        try {
            String sql = "SELECT * FROM taikhoan WHERE `Tên tài khoản`='"+tentaikhoanGET+"'";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String tentaikhoan = rs.getString("Tên tài khoản");
                String matkhau = rs.getString("Mật khẩu");
                String trangthai = rs.getString("Trạng thái");
                String maquyen = rs.getString("Mã quyền");
                taiKhoanDTO taikhoan = new taiKhoanDTO(tentaikhoan, matkhau, trangthai, maquyen);
                dstaikhoan.add(taikhoan);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load tài khoản");
        }
        return dstaikhoan;
    }

    public ArrayList<String> getChucNangKhongThuocQuyen(String maquyen) {
        ArrayList<String> dschucnang = new ArrayList<>();
        String sql = "SELECT `Tên chức năng`\n"
                + "FROM `chucnang`\n"
                + "WHERE `Tên chức năng` NOT IN\n"
                + "(\n"
                + "SELECT `Tên chức năng`\n"
                + "FROM `quyen-chucnang` qcn, `chucnang` cn\n"
                + "WHERE `qcn`.`Mã chức năng` = `cn`.`Mã chức năng`\n"
                + "AND `Mã quyền` = '" + maquyen + "')";
        System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                dschucnang.add(rs.getString("Tên chức năng"));
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy danh sách chức năng không thuộc quyền");
        }

        return dschucnang;
    }

    public void KhoaTaiKhoan(String tentaikhoan) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE taikhoan SET ";
        sql += "`Trạng thái`='1' ";
        sql += " WHERE `Tên tài khoản`='" + tentaikhoan + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void MoKhoaTaiKhoan(String tentaikhoan) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE taikhoan SET ";
        sql += "`Trạng thái`='0' ";
        sql += " WHERE `Tên tài khoản`='" + tentaikhoan + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }


}

