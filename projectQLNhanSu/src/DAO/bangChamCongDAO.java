/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
import DTO.hopDongLaoDongDTO;
import DTO.nhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class bangChamCongDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public bangChamCongDAO() {

    }

    public void set(bangChamCongDTO bcc) {        
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE bangchamcong SET ";
        sql += "`Mã bảng chấm công`='" + bcc.getMabangchamcong() + "', ";
        sql += "`Mã nhân viên`='" + bcc.getManhanvien() + "', ";
        sql += "`Thời gian`='" + bcc.getThoigian() + "', ";
        sql += "`Số giờ trễ`='" + bcc.getSogiotre() + "', ";
        sql += "`Số giờ làm thêm`='" + bcc.getSogiolamthem() + "' ";
        sql += " WHERE `Mã bảng chấm công`='" + bcc.getMabangchamcong() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }


    public void add(bangChamCongDTO bcc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO bangchamcong VALUES (";
        sql += "'" + bcc.getMabangchamcong() + "',";
        sql += "'" + bcc.getManhanvien() + "',";
        sql += "'" + bcc.getThoigian() + "',";
        sql += "'" + bcc.getSogiotre() + "',";
        sql += "'" + bcc.getSogiolamthem() + "'); ";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String mabcc) {
        MySQLConnect mySQL = new MySQLConnect();
        String sqlL = "DELETE FROM luong WHERE `Mã bảng chấm công`='" + mabcc + "'";
        mySQL.executeUpdate(sqlL);
        System.out.println(sqlL);
        String sqlCT = "DELETE FROM chitietbangchamcong WHERE `Mã bảng chấm công`='" + mabcc + "'";
        mySQL.executeUpdate(sqlCT);
        System.out.println(sqlCT);
        String sql = "DELETE FROM bangchamcong WHERE `Mã bảng chấm công`='" + mabcc + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<bangChamCongDTO> list(String manhanvienGET) {
        ArrayList<bangChamCongDTO> dsbangchamcong = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bangchamcong where `Mã nhân viên` = '" + manhanvienGET + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String manhanvien = rs.getString("Mã nhân viên");
                String thoigian = rs.getString("Thời gian");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                System.out.println("mabangchamcongDAO:"+mabangchamcong);
                bangChamCongDTO bangchamcong = new bangChamCongDTO(mabangchamcong, manhanvien, thoigian, sogiotre, sogiolamthem);
                dsbangchamcong.add(bangchamcong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng chấm công");
        }
        return dsbangchamcong;
    }

    public ArrayList<bangChamCongDTO> list() {
        ArrayList<bangChamCongDTO> dsbangchamcong = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bangchamcong";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String manhanvien = rs.getString("Mã nhân viên");
                String thoigian = rs.getString("Thời gian");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                bangChamCongDTO bangchamcong = new bangChamCongDTO(mabangchamcong, manhanvien, thoigian, sogiotre, sogiolamthem);
                dsbangchamcong.add(bangchamcong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng chấm công");
        }
        return dsbangchamcong;
    }

    public ArrayList<bangChamCongDTO> bangChamCongTrongThang(String thoigianGET) {
        ArrayList<bangChamCongDTO> dsbangchamcong = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bangchamcong WHERE `Thời gian`='" + thoigianGET + "' ";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String mabangchamcong = rs.getString("Mã bảng chấm công");
                String manhanvien = rs.getString("Mã nhân viên");
                String thoigian = rs.getString("Thời gian");
                float sogiotre = rs.getFloat("Số giờ trễ");
                float sogiolamthem = rs.getFloat("Số giờ làm thêm");
                bangChamCongDTO bangchamcong = new bangChamCongDTO(mabangchamcong, manhanvien, thoigian, sogiotre, sogiolamthem);
                dsbangchamcong.add(bangchamcong);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load bảng chấm công");
        }
        return dsbangchamcong;
    }

    public ArrayList<nhanVienDTO> layDsNhanVien() {
        ArrayList<nhanVienDTO> dsNhanVien = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT `nv`.`Mã nhân viên`,`Họ tên`\n"
                    + "FROM `bangchamcong` bcc, `nhanvien` nv\n"
                    + "WHERE `bcc`.`Mã nhân viên` = `nv`.`Mã nhân viên`";
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

    public ArrayList<hopDongLaoDongDTO> getThoiGianLamViec(String manhanvien) {
        ArrayList<hopDongLaoDongDTO> dsThoiGianLamViec = new ArrayList<>();
        try {

            String sql = "SELECT `nv`.`Mã nhân viên`, `hdld`.`Làm việc trong ngày từ`, `hdld`.`Làm việc trong ngày đến`, "
                    + "`hdld`.`Làm việc trong tuần từ`, `hdld`.`Làm việc trong tuần đến` "
                    + "FROM `hopdonglaodong` hdld, `nhanvien` nv\n"
                    + "WHERE `hdld`.`Mã HĐLĐ` = `nv`.`Mã HĐLĐ`\n"
                    + "AND `nv`.`Mã nhân viên` = '" + manhanvien + "'";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String batdau = rs.getString("Làm việc trong ngày từ");
                String ketthuc = rs.getString("Làm việc trong ngày đến");
                String batdautrongtuan = rs.getString("Làm việc trong tuần từ");
                String ketthuctrongtuan = rs.getString("Làm việc trong tuần đến");
                hopDongLaoDongDTO hdldDTO = new hopDongLaoDongDTO(batdau, ketthuc, batdautrongtuan, ketthuctrongtuan);
                dsThoiGianLamViec.add(hdldDTO);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsThoiGianLamViec;
    }

    public ArrayList<chiTietBangChamCongDTO> getThoiGianVaoLam(String bangchamcong) {
        ArrayList<chiTietBangChamCongDTO> dsThoiGianVaoLam = new ArrayList<>();
        try {

            String sql = "SELECT `ctbcc`.`Ngày`, `Giờ vào`, `Giờ ra`\n"
                    + "FROM bangchamcong bcc, chitietbangchamcong ctbcc\n"
                    + "WHERE `bcc`.`Mã bảng chấm công` = `ctbcc`.`Mã bảng chấm công`\n"
                    + "AND `ctbcc`.`Mã bảng chấm công` = '" + bangchamcong + "';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String ngay = rs.getString("Ngày");
                String giovao = rs.getString("Giờ vào");
                String giora = rs.getString("Giờ ra");
                chiTietBangChamCongDTO ctbccDTO = new chiTietBangChamCongDTO(ngay, giovao, giora);
                dsThoiGianVaoLam.add(ctbccDTO);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsThoiGianVaoLam;
    }

    public ArrayList<chiTietBangChamCongDTO> getThoiGianVaoLam(String manhanvien, String thoigian) {
        ArrayList<chiTietBangChamCongDTO> dsThoiGianVaoLam = new ArrayList<>();
        try {

            String sql = "SELECT `Ngày`, `Giờ vào`, `Giờ ra`\n"
                    + "FROM bangchamcong bcc, chitietbangchamcong ctbcc\n"
                    + "WHERE `bcc`.`Mã bảng chấm công` = `ctbcc`.`Mã bảng chấm công`\n"
                    + "AND `bcc`.`Mã nhân viên` = '" + manhanvien + "' "
                    + "AND `bcc`.`Thời gian` = '" + thoigian + "';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String ngay = rs.getString("Ngày");
                String giovao = rs.getString("Giờ vào");
                String giora = rs.getString("Giờ ra");
                chiTietBangChamCongDTO ctbccDTO = new chiTietBangChamCongDTO(ngay, giovao, giora);
                dsThoiGianVaoLam.add(ctbccDTO);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsThoiGianVaoLam;
    }

    public Float sogiotre(String manhanvien) {
        float sogiotre = 0;
        try {

            String sql = "SELECT `Số giờ trễ`, `Số giờ làm thêm`\n"
                    + "FROM bangchamcong bcc, nhanvien nv\n"
                    + "WHERE `bcc`.`Mã nhân viên` = `nv`.`Mã nhân viên`\n"
                    + "AND `nv`.`Mã nhân viên` = '" + manhanvien + "';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                sogiotre += rs.getFloat("Số giờ trễ");
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sogiotre;
    }

    public Float sogiolamthem(String manhanvien) {
        float sogiolamthem = 0;
        try {

            String sql = "SELECT `Số giờ làm thêm`\n"
                    + "FROM bangchamcong bcc, nhanvien nv\n"
                    + "WHERE `bcc`.`Mã nhân viên` = `nv`.`Mã nhân viên`\n"
                    + "AND `nv`.`Mã nhân viên` = '" + manhanvien + "';";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                sogiolamthem += rs.getFloat("Số giờ làm thêm");
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sogiolamthem;
    }

    public String mabangchamcong() {
        String mabangchamcong = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã bảng chấm công` "
                    + "FROM `bangchamcong` "
                    + "ORDER BY `Mã bảng chấm công` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã bảng chấm công");
                    System.out.println("Mã bảng chấm công!0:" + stt);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        mabangchamcong = String.format("BCC%03d", Integer.parseInt(stt) + 1);
        return mabangchamcong;
    }

}
