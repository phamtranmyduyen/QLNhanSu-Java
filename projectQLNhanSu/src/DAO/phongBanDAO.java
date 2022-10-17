/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.phongBanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author funty
 */
public class phongBanDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public phongBanDAO() {

    }

    public void set(phongBanDTO pb) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE phongban SET ";
        sql += "`Tên phòng ban`='" + pb.getTenphong() + "' ";
        sql += " WHERE `Mã phòng ban`='" + pb.getMaphong() + "'";

        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(phongBanDTO pb) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO phongban VALUES (";
        sql += "'" + pb.getMaphong() + "',";
        sql += "'" + pb.getTenphong() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String mapb) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phongban WHERE `Mã phòng ban`='" + mapb + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<phongBanDTO> list() {
        ArrayList<phongBanDTO> dsphongban = new ArrayList<>();
        try {
            String sql = "SELECT * FROM phongban";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maphong = rs.getString("Mã phòng ban");
                String tenphong = rs.getString("Tên phòng ban");
                phongBanDTO phongban = new phongBanDTO(maphong, tenphong);
                dsphongban.add(phongban);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsphongban;
    }
    public String maphongban() {
        String maphongban = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã phòng ban` "
                    + "FROM `phongban` "
                    + "ORDER BY `Mã phòng ban` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã phòng ban");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        maphongban = String.format("PB%03d", Integer.parseInt(stt) + 1);
        return maphongban;
    }

}
