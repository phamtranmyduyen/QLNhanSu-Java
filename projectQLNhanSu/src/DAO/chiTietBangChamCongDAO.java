/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.chiTietBangChamCongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MaiThy
 */
public class chiTietBangChamCongDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public chiTietBangChamCongDAO() {

    }

    public void set(chiTietBangChamCongDTO ctbcc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitietbangchamcong SET ";
        sql += "`Mã bảng chấm công`='" + ctbcc.getMabangchamcong() + "', ";
        sql += "`Ngày`='" + ctbcc.getNgay() + "', ";
        sql += "`Giờ vào`='" + ctbcc.getGiovao() + "', ";
        sql += "`Giờ ra`='" + ctbcc.getGiora() + "' ";
        sql += " WHERE `Mã bảng chấm công`='" + ctbcc.getMabangchamcong() + "' "
                + "AND `Ngày`='" + ctbcc.getNgay() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(chiTietBangChamCongDTO ctbcc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitietbangchamcong VALUES (";
        sql += "'" + ctbcc.getMabangchamcong() + "',";
        sql += "'" + ctbcc.getNgay() + "',";
        sql += "'" + ctbcc.getGiovao() + "',";
        sql += "'" + ctbcc.getGiora() + "')";
        mySQL.executeUpdate(sql);
    }

    public void delete(String mactbcc, String ngay) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitietbangchamcong WHERE `Mã bảng chấm công`='" + mactbcc + "'"
                + "AND `Ngày`='" + ngay + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<chiTietBangChamCongDTO> list() {
        ArrayList<chiTietBangChamCongDTO> dschitietbangchamcong = new ArrayList<>();
        try {
            String sql = "SELECT ctbcc.`Mã bảng chấm công`, ctbcc.`Ngày`, ctbcc.`Giờ vào`, ctbcc.`Giờ ra`, "
                    + "nv.`Mã nhân viên`, nv.`Họ tên` "
                    + "FROM chitietbangchamcong ctbcc, bangchamcong bcc, nhanvien nv "
                    + "WHERE ctbcc.`Mã bảng chấm công` = bcc.`Mã bảng chấm công` "
                    + "AND bcc.`Mã nhân viên` = nv.`Mã nhân viên`";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String manhanvien = rs.getString("Mã nhân viên");
                String tennhanvien = rs.getString("Họ tên");
                String ngay = rs.getString("Ngày");
                String giovao = rs.getString("Giờ vào");
                String giora = rs.getString("Giờ ra");
                chiTietBangChamCongDTO chitietbangchamcong = new chiTietBangChamCongDTO(mabangchamcong, ngay, giovao, giora, tennhanvien, manhanvien);
                dschitietbangchamcong.add(chitietbangchamcong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng chấm công");
        }
        return dschitietbangchamcong;
    }

    public ArrayList<chiTietBangChamCongDTO> list(String mabangchamcongGET) {
        ArrayList<chiTietBangChamCongDTO> dschitietbangchamcong = new ArrayList<>();
        try {
            String sql = "SELECT ctbcc.`Mã bảng chấm công`, ctbcc.`Ngày`, ctbcc.`Giờ vào`, ctbcc.`Giờ ra`, "
                    + "nv.`Mã nhân viên`, nv.`Họ tên` "
                    + "FROM chitietbangchamcong ctbcc, bangchamcong bcc, nhanvien nv "
                    + "WHERE ctbcc.`Mã bảng chấm công` = bcc.`Mã bảng chấm công` "
                    + "AND bcc.`Mã nhân viên` = nv.`Mã nhân viên` "
                    + "AND ctbcc.`Mã bảng chấm công`= '" + mabangchamcongGET + "'";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String manhanvien = rs.getString("Mã nhân viên");
                String tennhanvien = rs.getString("Họ tên");
                String ngay = rs.getString("Ngày");
                String giovao = rs.getString("Giờ vào");
                String giora = rs.getString("Giờ ra");
                chiTietBangChamCongDTO chitietbangchamcong = new chiTietBangChamCongDTO(mabangchamcong, ngay, giovao, giora, tennhanvien, manhanvien);
                dschitietbangchamcong.add(chitietbangchamcong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng chấm công");
        }
        return dschitietbangchamcong;
    }

}
