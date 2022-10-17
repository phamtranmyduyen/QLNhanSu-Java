/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DTO.phuCapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class phuCapDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public phuCapDAO() {

    }
    public void set(phuCapDTO pc) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE phucap SET ";
            sql += "`Tên phụ cấp`='"+pc.getTenphucap()+"', ";
            sql += "`Tiền phụ cấp`='"+pc.getTienphucap()+"' ";
            sql += "WHERE `Mã phụ cấp`='"+pc.getMaphucap()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(phuCapDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO phucap VALUES (";
                sql += "'"+pc.getMaphucap()+"',";
                sql += "'"+pc.getTenphucap()+"',";
                sql += "'"+pc.getTienphucap()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
public void delete(String mapc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phucap WHERE `Mã phụ cấp`='" + mapc + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<phuCapDTO> list() {
        ArrayList<phuCapDTO> dsphucap = new ArrayList<>();
        try {
            String sql = "SELECT * FROM phucap"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maphucap = rs.getString("Mã phụ cấp");
                String tenphucap = rs.getString("Tên phụ cấp");
                float tienphucap=rs.getFloat("Tiền phụ cấp");
                phuCapDTO phucap = new phuCapDTO(maphucap, tenphucap,tienphucap);    
                dsphucap.add(phucap);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsphucap;
    }
    public String maphucap() {
        String maphucap = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã phụ cấp` "
                    + "FROM `phucap` "
                    + "ORDER BY `Mã phụ cấp` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã phụ cấp");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        maphucap = String.format("PC%03d", Integer.parseInt(stt) + 1);
        return maphucap;
    }

}
