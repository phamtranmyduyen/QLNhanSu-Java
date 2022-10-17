package DAO;

import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DAO.MySQLConnect;
import DTO.thuongDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trinh
 */
public class thuongDAO {
    private MySQLConnect mySQL= new MySQLConnect();
    public thuongDAO(){
        
    }
        public void set(thuongDTO t) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE thuong SET ";
            sql += "`Tiền thưởng`='"+t.getTienthuong()+"', ";
            
            sql += " WHERE `Mã thưởng`='"+t.getMathuong()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(thuongDTO t) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO thuong VALUES (";
                sql += "'"+t.getMathuong()+"',";
                sql +="'"+t.getLoaithuong()+"',";
                sql += "'"+t.getTienthuong()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
public void delete(String mat) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM thuong WHERE `Mã thưởng`='" + mat + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
   public ArrayList<thuongDTO> list() {
        ArrayList<thuongDTO> dsthuong= new ArrayList<>();
        try{
            String sql="SELECT * FROM thuong";
            ResultSet rs=mySQL.executeQuery(sql);
            while (rs.next()){
                String mathuong= rs.getString("Mã thưởng");
                String loaithuong=rs.getString("Loại thưởng");
                float tienthuong=rs.getFloat("Tiền thưởng");
                  
                thuongDTO thuong= new thuongDTO(mathuong,loaithuong,tienthuong);
                dsthuong.add(thuong);
            }
            rs.close();
            mySQL.disConnect();
        }catch (SQLException ex){
            System.out.println("Lỗi load");
        }
        return dsthuong;
    }
public String mathuong() {
        String mathuong = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã thưởng` "
                    + "FROM `thuong` "
                    + "ORDER BY `Mã thưởng` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã thưởng");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mathuong = String.format("T%03d", Integer.parseInt(stt) + 1);
        return mathuong;
    }

 
}

