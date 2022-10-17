/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.chucNangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class chucNangDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public chucNangDAO() {

    }

    public void set(chucNangDTO cn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chucnang SET ";
        sql += "`Tên chức năng`='" + cn.getTenchucnang() + "' ";
        sql += " WHERE `Mã chức năng`='" + cn.getMachucnang() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(chucNangDTO cn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chucnang VALUES (";
        sql += "'" + cn.getMachucnang() + "',";
        sql += "'" + cn.getTenchucnang() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String macn) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chucnang WHERE `Mã chức năng`='" + macn + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<chucNangDTO> list() {
        ArrayList<chucNangDTO> dschucnang = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chucnang";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String machucnang = rs.getString("Mã chức năng");
                String tenchucnang = rs.getString("Tên chức năng");
                chucNangDTO chucnang = new chucNangDTO(machucnang, tenchucnang);
                dschucnang.add(chucnang);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dschucnang;
    }

    public ArrayList<String> getChiTietChucNangKhongThuoc(String macn) {
        ArrayList<String> dschitietchucnang = new ArrayList<>();
        String sql = "SELECT `Tên chi tiết chức năng`\n"
                + "FROM `chitietchucnang` \n"
                + "WHERE `Mã chức năng` != '" + macn + "'";
        System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                dschitietchucnang.add(rs.getString("Tên chi tiết chức năng"));
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy danh sách chi tiết chức năng không thuộc");
        }

        return dschitietchucnang;
    }

    public String layTenCNThoaSL(int soluong) {
        String tenchucnang = "";
        String sql = "SELECT `Tên chức năng`\n"
                + "FROM(\n"
                + "SELECT `Tên chức năng`, COUNT(*) AS SL\n"
                + "FROM chitietchucnang ctcn, chucnang cn\n"
                + "WHERE `ctcn`.`Mã chức năng` = `cn`.`Mã chức năng`\n"
                + "GROUP BY ctcn.`Mã chức năng`) slcn\n"
                + "WHERE slcn.`SL` = '" + soluong + "'";
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                tenchucnang = rs.getString("Tên chức năng");
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(chucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenchucnang;
    }

    public ArrayList<String> layTenCNCoCTCN() {
        ArrayList<String> dschucnang = new ArrayList<>();
        String sql = "SELECT `Tên chức năng`\n"
                + "FROM chucnang cn\n"
                + "WHERE `cn`.`Mã chức năng` IN\n"
                + "(SELECT `Mã chức năng`\n"
                + " FROM chitietchucnang)";
        System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                dschucnang.add(rs.getString("Tên chức năng"));
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy danh sách chức năng không có chi tiết chức năng");
        }

        return dschucnang;
    }
    public String machucnang() {
        String machucnang = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã chức năng` "
                    + "FROM `chucnang` "
                    + "ORDER BY `Mã chức năng` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã chức năng");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        machucnang = String.format("CN%03d", Integer.parseInt(stt) + 1);
        return machucnang;
    }


}
