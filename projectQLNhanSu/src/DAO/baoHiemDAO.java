package DAO;

import DAO.MySQLConnect;
import DTO.baoHiemDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class baoHiemDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public baoHiemDAO() {

    }
    public void set(baoHiemDTO bh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE baohiem SET ";
            sql += "`Tên bảo hiểm`='"+bh.getTenbaohiem()+"' ";
            sql += " WHERE `Mã bảo hiểm`='"+bh.getMabaohiem()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(baoHiemDTO bh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO baohiem VALUES (";
                sql += "'"+bh.getMabaohiem()+"',";
                sql += "'"+bh.getTenbaohiem()+"')";
                
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    public void delete(String mabh)
    {
        MySQLConnect mySQL = new MySQLConnect();
            String sql = "DELETE FROM baohiem WHERE `Mã bảo hiểm`='" + mabh + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<baoHiemDTO> list() {
        ArrayList<baoHiemDTO> dsbaohiem = new ArrayList<>();
        try {
            String sql = "SELECT * FROM baohiem"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabaohiem = rs.getString("Mã bảo hiểm");
                String tenbaohiem = rs.getString("Tên bảo hiểm");
                baoHiemDTO baohiem = new baoHiemDTO(mabaohiem , tenbaohiem);    
                dsbaohiem.add(baohiem);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsbaohiem;
    }
    public String mabaohiem() {
        String mabaohiem = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã bảo hiểm` "
                    + "FROM `baohiem` "
                    + "ORDER BY `Mã bảo hiểm` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã bảo hiểm");
                    System.out.println("Mã bảo hiểm!0:" + stt);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mabaohiem = String.format("BH%03d", Integer.parseInt(stt) + 1);
        return mabaohiem;
    }

    
}
