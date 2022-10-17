package DAO;

import DAO.MySQLConnect;
import DTO.chucVuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chucVuDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public chucVuDAO() {

    }
    public void set(chucVuDTO cv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE chucvu SET ";
            sql += "`Tên chức vụ`='"+cv.getTenchucvu()+"' ";
            sql += " WHERE `Mã chức vụ`='"+cv.getMachucvu()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(chucVuDTO cv) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chucvu VALUES (";
                sql += "'"+cv.getMachucvu()+"',";
                sql += "'"+cv.getTenchucvu()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    public void delete(String macv)
    {
        MySQLConnect mySQL = new MySQLConnect();
            String sql = "DELETE FROM chucvu WHERE `Mã chức vụ`='"+macv+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<chucVuDTO> list() {
        ArrayList<chucVuDTO> dschucvu = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chucvu"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machucvu = rs.getString("Mã chức vụ");
                String tenchucvu = rs.getString("Tên chức vụ");
                chucVuDTO chucvu = new chucVuDTO(machucvu, tenchucvu);    
                dschucvu.add(chucvu);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dschucvu;
    }
    public String machucvu() {
        String machucvu = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã chức vụ` "
                    + "FROM `chucvu` "
                    + "ORDER BY `Mã chức vụ` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã chức vụ");
                    System.out.println("Mã chức vụ!0:" + stt);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        machucvu = String.format("CV%03d", Integer.parseInt(stt) + 1);
        return machucvu;
    }

   
}
