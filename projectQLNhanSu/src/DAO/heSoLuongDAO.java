/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DTO.heSoLuongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class heSoLuongDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public heSoLuongDAO() {

    }
    public void set(heSoLuongDTO cn) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hesoluong SET ";
            sql += "`Hệ số lương`='"+cn.getHesoluong()+"' ";
            sql += "WHERE `Mã hệ số lương`='"+cn.getMahesoluong()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(heSoLuongDTO hsl) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hesoluong VALUES (";
                sql += "'"+hsl.getMahesoluong()+"',";
                sql += "'"+hsl.getHesoluong()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }

    public void delete(String mahsl) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM hesoluong WHERE `Mã hệ số lương`='" + mahsl + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<heSoLuongDTO> list() {
        ArrayList<heSoLuongDTO> dshesoluong = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hesoluong"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mahesoluong = rs.getString("Mã hệ số lương");
                float hsl = rs.getFloat("Hệ số lương");
                heSoLuongDTO hesoluong = new heSoLuongDTO(mahesoluong, hsl);    
                dshesoluong.add(hesoluong);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dshesoluong;
    }
    public String mahesoluong() {
        String mahesoluong = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã hệ số lương` "
                    + "FROM `hesoluong` "
                    + "ORDER BY `Mã hệ số lương` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã hệ số lương");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mahesoluong = String.format("HSL%03d", Integer.parseInt(stt) + 1);
        return mahesoluong;
    }

}
