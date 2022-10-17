/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DTO.luongCanBanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class luongCanBanDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public luongCanBanDAO() {

    }
    public void set(luongCanBanDTO lcb) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE luongcanban SET ";
            sql += "`Lương căn bản`='"+lcb.getLuongcanban()+"' ";
            sql += " WHERE `Mã lương căn bản`='"+lcb.getMaluongcanban()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(luongCanBanDTO lcb) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO luongcanban VALUES (";
                sql += "'"+lcb.getMaluongcanban()+"',";
                sql += "'"+lcb.getLuongcanban()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }

    public void delete(String malcb) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM luongcanban WHERE `Mã lương căn bản`='" + malcb + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<luongCanBanDTO> list() {
        ArrayList<luongCanBanDTO> dsluongcanban = new ArrayList<>();
        try {
            String sql = "SELECT * FROM luongcanban"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maluongcanban = rs.getString("Mã lương căn bản");
                float lcb = rs.getFloat("Lương căn bản");
                luongCanBanDTO luongcanban = new luongCanBanDTO(maluongcanban, lcb);    
                dsluongcanban.add(luongcanban);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsluongcanban;
    }
    public String maluongcanban() {
        String maluongcanban = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã lương căn bản` "
                    + "FROM `luongcanban` "
                    + "ORDER BY `Mã lương căn bản` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã lương căn bản");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        maluongcanban = String.format("LCB%03d", Integer.parseInt(stt) + 1);
        return maluongcanban;
    }


}
