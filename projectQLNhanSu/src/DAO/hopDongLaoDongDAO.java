/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.hopDongLaoDongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class hopDongLaoDongDAO {
 private MySQLConnect mySQL = new MySQLConnect();

    public hopDongLaoDongDAO() {

    }
    public void set(hopDongLaoDongDTO hdld) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hopdonglaodong SET ";
            
            sql += "`Loại HĐLĐ`='"+hdld.getLoaihdld()+"', ";
            sql += "`Ngày bắt đầu`='"+hdld.getNgaybd()+"', ";
            sql += "`Ngày kết thúc`='"+hdld.getNgaykt()+"', ";
            sql += "`Địa điểm làm việc`='"+hdld.getDiadiemlamviec()+"', ";
            sql += "`Ngày kí`='"+hdld.getNgayki()+"', ";
            sql += "`Thời hạn hợp đồng`='"+hdld.getThoihanhopdong()+"', ";
            sql += "`Làm việc trong ngày từ`='"+hdld.getLamviectrongngaytu()+"', ";
            sql += "`Làm việc trong ngày đến`='"+hdld.getLamviectrongngayden()+"', ";
            sql += "`Làm việc trong tuần từ`='"+hdld.getLamviectrongtuantu()+"', ";
            sql += "`Làm việc trong tuần đến`='"+hdld.getLamviectrongtuanden()+"', ";
            sql += "`Mã lương căn bản`='"+hdld.getMaluongcanban()+"', ";
            sql += "`Mã thưởng`='"+hdld.getMathuong()+"', ";
            sql += "`Mã hệ số lương`='"+hdld.getMahesoluong()+"', ";         
            sql += "`Mã phụ cấp`='"+hdld.getMaphucap()+"', "; 
            sql += "`Ghi chú`='"+hdld.getGhichu()+"' ";
            sql += " WHERE `Mã HĐLĐ`='"+hdld.getMahdld()+"'";
            System.out.println(sql);            
            mySQL.executeUpdate(sql);
    }
    public void add(hopDongLaoDongDTO hdld) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hopdonglaodong VALUES (";
                sql += "'"+hdld.getMahdld()+"',";
                sql += "'"+hdld.getLoaihdld()+"',";
                sql += "'"+hdld.getNgaybd()+"',";
                sql += "'"+hdld.getNgaykt()+"',";
                sql += "'"+hdld.getDiadiemlamviec()+"',";
                sql += "'"+hdld.getNgayki()+"',";
                sql += "'"+hdld.getThoihanhopdong()+"',";
                sql += "'"+hdld.getLamviectrongngaytu()+"',";
                sql += "'"+hdld.getLamviectrongngayden()+"',";
                sql += "'"+hdld.getLamviectrongtuantu()+"',";
                sql += "'"+hdld.getLamviectrongtuanden()+"',";
                sql += "'"+hdld.getMaluongcanban()+"',";
                sql += "'"+hdld.getMathuong()+"',";
                sql += "'"+hdld.getMahesoluong()+"',";
                sql += "'"+hdld.getMaphucap()+"',";
                sql += "'"+hdld.getGhichu()+"')";
                
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
   public void delete(String mahdld) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chucnang WHERE `Mã chức năng`='" + mahdld + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public ArrayList<hopDongLaoDongDTO> list() {
        ArrayList<hopDongLaoDongDTO> dshopdonglaodong = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hopdonglaodong"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mahdld = rs.getString("Mã HĐLĐ");
                String loaihdld = rs.getString("Loại HĐLĐ");
                String ngaybd = rs.getString("Ngày bắt đầu");
                String ngaykt = rs.getString("Ngày kết thúc");
                String diadiemlamviec = rs.getString("Địa điểm làm việc");
                String ngayki = rs.getString("Ngày kí");
                String thoihanhopdong = rs.getString("Thời hạn hợp đồng");
                String lamviectrongngaytu = rs.getString("Làm việc trong ngày từ");
                String lamviectrongngayden = rs.getString("Làm việc trong ngày đến");
                String lamviectrongtuantu = rs.getString("Làm việc trong tuần từ");
                String lamviectrongtuanden = rs.getString("Làm việc trong tuần đến");
                String maluongcanban = rs.getString("Mã lương căn bản");
                String mathuong = rs.getString("Mã thưởng");
                String mahesoluong = rs.getString("Mã hệ số lương");
                String maphucap = rs.getString("Mã phụ cấp");
                String ghichu = rs.getString("Ghi chú");
                
                hopDongLaoDongDTO hdld = new hopDongLaoDongDTO(mahdld, loaihdld, ngaybd, ngaykt, diadiemlamviec, ngayki, thoihanhopdong, lamviectrongngaytu, lamviectrongngayden, lamviectrongtuantu, lamviectrongtuanden, maluongcanban, mathuong, mahesoluong, maphucap, ghichu);
                dshopdonglaodong.add(hdld);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dshopdonglaodong;
    }
    public ArrayList<hopDongLaoDongDTO> list(String manhanvien) {
        ArrayList<hopDongLaoDongDTO> dshopdonglaodong = new ArrayList<>();
        try {
            String sql = "SELECT * "
                    + "FROM hopdonglaodong hdld, nhanvien nv "
                    + "WHERE hdld.`Mã HĐLĐ` = nv.`Mã HĐLĐ`"
                    + "AND nv.`Mã nhân viên` = '"+manhanvien+"'"; 
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mahdld = rs.getString("Mã HĐLĐ");
                String loaihdld = rs.getString("Loại HĐLĐ");
                String ngaybd = rs.getString("Ngày bắt đầu");
                String ngaykt = rs.getString("Ngày kết thúc");
                String diadiemlamviec = rs.getString("Địa điểm làm việc");
                String ngayki = rs.getString("Ngày kí");
                String thoihanhopdong = rs.getString("Thời hạn hợp đồng");
                String lamviectrongngaytu = rs.getString("Làm việc trong ngày từ");
                String lamviectrongngayden = rs.getString("Làm việc trong ngày đến");
                String lamviectrongtuantu = rs.getString("Làm việc trong tuần từ");
                String lamviectrongtuanden = rs.getString("Làm việc trong tuần đến");
                String maluongcanban = rs.getString("Mã lương căn bản");
                String mathuong = rs.getString("Mã thưởng");
                String mahesoluong = rs.getString("Mã hệ số lương");
                String maphucap = rs.getString("Mã phụ cấp");
                String ghichu = rs.getString("Ghi chú");
                
                hopDongLaoDongDTO hdld = new hopDongLaoDongDTO(mahdld, loaihdld, ngaybd, ngaykt, diadiemlamviec, ngayki, thoihanhopdong, lamviectrongngaytu, lamviectrongngayden, lamviectrongtuantu, lamviectrongtuanden, maluongcanban, mathuong, mahesoluong, maphucap, ghichu);
                dshopdonglaodong.add(hdld);
            }
            
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dshopdonglaodong;
    }
    public String mahopdonglaodong() {
        String mahopdonglaodong = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã HĐLĐ` "
                    + "FROM `hopdonglaodong` "
                    + "ORDER BY `Mã HĐLĐ` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã HĐLĐ");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mahopdonglaodong = String.format("HDLD%03d", Integer.parseInt(stt) + 1);
        return mahopdonglaodong;
    }

}
   

