/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.quyenDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class quyenDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public quyenDAO() {

    }

    public void set(quyenDTO q) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE quyen SET ";
        sql += "`Tên quyền`='" + q.getTenquyen() + "', ";
        sql += " WHERE `Mã quyền`='" + q.getMaquyen() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(quyenDTO q) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO quyen VALUES (";
        sql += "'" + q.getMaquyen() + "',";
        sql += "'" + q.getTenquyen() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void addChucNangQuyen(String maquyen, ArrayList<String> listChucNang) {
        MySQLConnect mySQL = new MySQLConnect();
        String sqlReset = "DELETE FROM `quyen-chucnang` WHERE `Mã quyền` = '" + maquyen + "'";
        mySQL.executeUpdate(sqlReset);
        for (int i = 0; i < listChucNang.size(); i++) {
            String sql = "INSERT INTO `quyen-chucnang` VALUES (";
            sql += "'" + maquyen + "',";
            sql += "'" + listChucNang.get(i) + "')";
            System.out.println(sql);
            mySQL.executeUpdate(sql);
        }
    }

    public void delete(String maq) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE quyen SET enable = 0 WHERE `Mã quyền`='" + maq + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<String> getChucNang(String maquyen) {
        ArrayList<String> dschucnang = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT qcn.`Mã chức năng`";
            sql += " FROM `quyen-chucnang` qcn";
            sql += " WHERE `Mã quyền` = '" + maquyen + "'";
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                dschucnang.add(rs.getString("Mã chức năng"));
            }
            System.out.println("DANH SÁCH CHỨC NĂNG" + dschucnang);
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi");
        }
        return dschucnang;
    }

    public ArrayList<quyenDTO> list() {
        ArrayList<quyenDTO> dsquyen = new ArrayList<>();
        try {
            String sql = "SELECT * FROM quyen";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String maquyen = rs.getString("Mã quyền");
                String tenquyen = rs.getString("Tên quyền");
                quyenDTO quyen = new quyenDTO(maquyen, tenquyen);
                dsquyen.add(quyen);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load");
        }
        return dsquyen;
    }
  

}
