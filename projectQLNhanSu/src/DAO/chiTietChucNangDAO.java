package DAO;

import DTO.chiTietChucNangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chiTietChucNangDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public chiTietChucNangDAO() {

    }

    public void set(chiTietChucNangDTO ctcn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitietchucnang SET ";
        sql += "`Tên chi tiết chức năng`='" + ctcn.getTenchitietchucnang() + "' ";
        sql += " WHERE `Mã chi tiết chức năng`='" + ctcn.getMachitietchucnang() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(chiTietChucNangDTO ctcn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitietchucnang VALUES (";
        sql += "'" + ctcn.getMachitietchucnang() + "',";
        sql += "'" + ctcn.getTenchitietchucnang() + "',";
        sql += "'" + ctcn.getMachucnang() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String mactcn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitietchucnang WHERE `Mã chi tiết chức năng`='" + mactcn + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<chiTietChucNangDTO> list() {
        ArrayList<chiTietChucNangDTO> dschitietchucnang = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chitietchucnang";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machitietchucnang = rs.getString("Mã chi tiết chức năng");
                String tenchitietchucnang = rs.getString("Tên chi tiết chức năng");
                String machucnang = rs.getString("Mã chức năng");
                chiTietChucNangDTO chitietchucnang = new chiTietChucNangDTO(machitietchucnang, tenchitietchucnang, machucnang);
                dschitietchucnang.add(chitietchucnang);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dschitietchucnang;
    }

    public ArrayList<chiTietChucNangDTO> list(String machucnangGET) {
        ArrayList<chiTietChucNangDTO> dschitietchucnang = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chitietchucnang where `Mã chức năng` = '" + machucnangGET + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machitietchucnang = rs.getString("Mã chi tiết chức năng");
                String tenchitietchucnang = rs.getString("Tên chi tiết chức năng");
                String machucnang = rs.getString("Mã chức năng");
                chiTietChucNangDTO chitietchucnang = new chiTietChucNangDTO(machitietchucnang, tenchitietchucnang, machucnang);
                dschitietchucnang.add(chitietchucnang);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dschitietchucnang;
    }

    public ArrayList<String> listTenChiTietChucNang(String tenchucnang) {
        ArrayList<String> dstenchitietchucnang = new ArrayList<>();
        try {
            String sql = "SELECT `Tên chi tiết chức năng`\n"
                    + "FROM `chitietchucnang` ctcn, `chucnang` cn\n"
                    + "WHERE `ctcn`.`Mã chức năng` = `cn`.`Mã chức năng`\n"
                    + "AND `cn`.`Tên chức năng` = '" + tenchucnang + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String tenchitietchucnang = rs.getString("Tên chi tiết chức năng");
                dstenchitietchucnang.add(tenchitietchucnang);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dstenchitietchucnang;
    }
    public String machitietchucnang() {
        String machitietchucnang = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã chi tiết chức năng` "
                    + "FROM `chitietchucnang` "
                    + "ORDER BY `Mã chi tiết chức năng` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã chi tiết chức năng");
                    System.out.println("Mã chi tiết chức năng!0:" + stt);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        machitietchucnang = String.format("CTCN%03d", Integer.parseInt(stt) + 1);
        return machitietchucnang;
    }


    
}
