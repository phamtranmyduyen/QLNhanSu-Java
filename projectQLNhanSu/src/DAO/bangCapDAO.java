/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.bangCapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author funty
 */
public class bangCapDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public bangCapDAO() {

    }

    public void set(bangCapDTO bc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE bangcap SET ";
        sql += "`Tên bằng cấp`='" + bc.getTenbangcap() + "',";
        sql += "`Ngày bắt đầu hiệu lực`='" + bc.getNgaybatdauhieuluc() + "',";
        sql += "`Ngày hết hạn hiệu lực`='" + bc.getNgayketthuchieuluc() + "' ";
        sql += " WHERE `Mã bằng cấp`='" + bc.getMabangcap() + "' ";

        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(bangCapDTO bc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO bangcap VALUES (";
        sql += "'" + bc.getMabangcap() + "',";
        sql += "'" + bc.getTenbangcap() + "',";
        sql += "'" + bc.getNgaybatdauhieuluc() + "',";
        sql += "'" + bc.getNgayketthuchieuluc() + "')";

        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String mabangcap) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM bangcap WHERE `Mã bằng cấp`='" + mabangcap + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<bangCapDTO> list() {
        ArrayList<bangCapDTO> dsbangcap = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bangcap";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangcap = rs.getString("Mã bằng cấp");
                String tenbangcap = rs.getString("Tên bằng cấp");
                String ngaybatdauhieuluc = rs.getString("Ngày bắt đầu hiệu lực");
                String ngayketthuchieuluc = rs.getString("Ngày hết hạn hiệu lực");
                bangCapDTO bangcap = new bangCapDTO(mabangcap, tenbangcap, ngaybatdauhieuluc, ngayketthuchieuluc);
                dsbangcap.add(bangcap);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsbangcap;
    }

    public String mabangcap() {
        String mabangcap = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã bằng cấp` "
                    + "FROM `bangcap` "
                    + "ORDER BY `Mã bằng cấp` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã bằng cấp");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mabangcap = String.format("BC%03d", Integer.parseInt(stt) + 1);
        return mabangcap;
    }

}
