/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MySQLConnect;
import DTO.luongDTO;
import DTO.phuCapDTO;
import DTO.thuongDTO;
import DTO.heSoLuongDTO;
import DTO.nhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class luongDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public luongDAO() {

    }

    public void set(luongDTO l) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE luong SET ";
        sql += "`Mã lương`='" + l.getMaluong() + "', ";
        sql += "`Mã bảng chấm công`='" + l.getMabangchamcong() + "', ";
        sql += "`Lương chính thức`='" + l.getLuongchinhthuc() + "', ";
        sql += " WHERE `Mã lương`='" + l.getMaluong() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(luongDTO l) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO luong VALUES (";
        sql += "'" + l.getMaluong() + "',";
        sql += "'" + l.getMabangchamcong() + "',";
        sql += "'" + l.getLuongchinhthuc() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String mal) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM luong WHERE `Mã lương`='" + mal + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public void delete() {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM luong";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<luongDTO> list(String manhanvienGET, String thoigianGET) {
        ArrayList<luongDTO> dsluong = new ArrayList<>();
        try {
            String sql = "SELECT l.`Mã lương`, l.`Mã bảng chấm công`, bcc.`Mã nhân viên`, "
                    + "nv.`Họ tên`, lcb.`Lương căn bản`, hsl.`Hệ số lương`, t.`Tiền thưởng`, pc.`Tiền phụ cấp`, "
                    + "l.`Lương chính thức`, bcc.`Số giờ trễ`, bcc.`Số giờ làm thêm`, bcc.`Thời gian` "
                    + "FROM luong l, bangchamcong bcc, nhanvien nv, hopdonglaodong hdld, thuong t, luongcanban lcb, hesoluong hsl, phucap pc "
                    + "WHERE l.`Mã bảng chấm công` = bcc.`Mã bảng chấm công` "
                    + "AND bcc.`Mã nhân viên` = nv.`Mã nhân viên` "
                    + "AND nv.`Mã HĐLĐ` = hdld.`Mã HĐLĐ` "
                    + "AND lcb.`Mã lương căn bản` = hdld.`Mã lương căn bản` "
                    + "AND hsl.`Mã hệ số lương` = hdld.`Mã hệ số lương` "
                    + "AND pc.`Mã phụ cấp` = hdld.`Mã phụ cấp` "
                    + "AND t.`Mã thưởng` = hdld.`Mã thưởng` "
                    + "AND bcc.`Mã nhân viên` = '" + manhanvienGET + "' "
                    + "AND bcc.`Thời gian` = '" + thoigianGET + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maluong = rs.getString("Mã lương");
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String thoigian = rs.getString("Thời gian");
                String manhanvien = rs.getString("Mã nhân viên");
                String tennhanvien = rs.getString("Họ tên");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                float luongcanban = rs.getFloat("Lương căn bản");
                float thuong = rs.getFloat("Tiền thưởng");
                float hesoluong = rs.getFloat("Hệ số lương");
                float phucap = rs.getFloat("Tiền phụ cấp");
                double luongchinhthuc = rs.getDouble("Lương chính thức");
                luongDTO luong = new luongDTO(maluong, mabangchamcong, thoigian, manhanvien, tennhanvien, sogiotre, sogiolamthem, luongcanban, thuong, hesoluong, phucap, luongchinhthuc);
                dsluong.add(luong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng lương");
        }
        return dsluong;
    }

    public ArrayList<luongDTO> list(String manhanvienGET) {
        ArrayList<luongDTO> dsluong = new ArrayList<>();
        try {
            String sql = "SELECT l.`Mã lương`, l.`Mã bảng chấm công`, bcc.`Mã nhân viên`, "
                    + "nv.`Họ tên`, lcb.`Lương căn bản`, hsl.`Hệ số lương`, t.`Tiền thưởng`, pc.`Tiền phụ cấp`, "
                    + "l.`Lương chính thức`, bcc.`Số giờ trễ`, bcc.`Số giờ làm thêm`, bcc.`Thời gian` "
                    + "FROM luong l, bangchamcong bcc, nhanvien nv, hopdonglaodong hdld, thuong t, luongcanban lcb, hesoluong hsl, phucap pc "
                    + "WHERE l.`Mã bảng chấm công` = bcc.`Mã bảng chấm công` "
                    + "AND bcc.`Mã nhân viên` = nv.`Mã nhân viên` "
                    + "AND nv.`Mã HĐLĐ` = hdld.`Mã HĐLĐ` "
                    + "AND lcb.`Mã lương căn bản` = hdld.`Mã lương căn bản` "
                    + "AND hsl.`Mã hệ số lương` = hdld.`Mã hệ số lương` "
                    + "AND pc.`Mã phụ cấp` = hdld.`Mã phụ cấp` "
                    + "AND t.`Mã thưởng` = hdld.`Mã thưởng` "
                    + "AND bcc.`Mã nhân viên` = '" + manhanvienGET + "' ";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
               String maluong = rs.getString("Mã lương");
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String thoigian = rs.getString("Thời gian");
                String manhanvien = rs.getString("Mã nhân viên");
                String tennhanvien = rs.getString("Họ tên");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                float luongcanban = rs.getFloat("Lương căn bản");
                float thuong = rs.getFloat("Tiền thưởng");
                float hesoluong = rs.getFloat("Hệ số lương");
                float phucap = rs.getFloat("Tiền phụ cấp");
                double luongchinhthuc = rs.getDouble("Lương chính thức");
                luongDTO luong = new luongDTO(maluong, mabangchamcong, thoigian, manhanvien, tennhanvien, sogiotre, sogiolamthem, luongcanban, thuong, hesoluong, phucap, luongchinhthuc);
                dsluong.add(luong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng lương");
        }
        return dsluong;
    }

    public ArrayList<luongDTO> list() {
        ArrayList<luongDTO> dsluong = new ArrayList<>();
        try {
            String sql = "SELECT l.`Mã lương`, l.`Mã bảng chấm công`, bcc.`Mã nhân viên`, "
                    + "nv.`Họ tên`, lcb.`Lương căn bản`, hsl.`Hệ số lương`, t.`Tiền thưởng`, pc.`Tiền phụ cấp`, "
                    + "l.`Lương chính thức`, bcc.`Số giờ trễ`, bcc.`Số giờ làm thêm`, bcc.`Thời gian` "
                    + "FROM luong l, bangchamcong bcc, nhanvien nv, hopdonglaodong hdld, thuong t, luongcanban lcb, hesoluong hsl, phucap pc "
                    + "WHERE l.`Mã bảng chấm công` = bcc.`Mã bảng chấm công` "
                    + "AND bcc.`Mã nhân viên` = nv.`Mã nhân viên` "
                    + "AND nv.`Mã HĐLĐ` = hdld.`Mã HĐLĐ` "
                    + "AND lcb.`Mã lương căn bản` = hdld.`Mã lương căn bản` "
                    + "AND hsl.`Mã hệ số lương` = hdld.`Mã hệ số lương` "
                    + "AND pc.`Mã phụ cấp` = hdld.`Mã phụ cấp` "
                    + "AND t.`Mã thưởng` = hdld.`Mã thưởng` ";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
               String maluong = rs.getString("Mã lương");
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String thoigian = rs.getString("Thời gian");
                String manhanvien = rs.getString("Mã nhân viên");
                String tennhanvien = rs.getString("Họ tên");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                float luongcanban = rs.getFloat("Lương căn bản");
                float thuong = rs.getFloat("Tiền thưởng");
                float hesoluong = rs.getFloat("Hệ số lương");
                float phucap = rs.getFloat("Tiền phụ cấp");
                double luongchinhthuc = rs.getDouble("Lương chính thức");
                luongDTO luong = new luongDTO(maluong, mabangchamcong, thoigian, manhanvien, tennhanvien, sogiotre, sogiolamthem, luongcanban, thuong, hesoluong, phucap, luongchinhthuc);
                dsluong.add(luong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng lương");
        }
        return dsluong;
    }

    public ArrayList<nhanVienDTO> layDsNhanVien() {
        ArrayList<nhanVienDTO> dsNhanVien = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT `nv`.`Mã nhân viên`,`Họ tên`\n"
                    + "FROM `luong` l, `nhanvien` nv\n"
                    + "WHERE `l`.`Mã nhân viên` = `nv`.`Mã nhân viên`";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String manhanvien = rs.getString("Mã nhân viên");
                String hoten = rs.getString("Họ tên");
                nhanVienDTO nvDTO = new nhanVienDTO(manhanvien, hoten);
                dsNhanVien.add(nvDTO);

            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy danh sách nhân viên");
        }
        return dsNhanVien;
    }

    public String maluong() {
        String maluong = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã lương` "
                    + "FROM `luong` "
                    + "ORDER BY `Mã lương` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã lương");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        maluong = String.format("L%03d", Integer.parseInt(stt) + 1);
        return maluong;
    }

}
