/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import com.sun.javafx.binding.StringFormatter;
import BUS.bangChamCongBUS;
import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
import DTO.luongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class thongKeDAO {

    public thongKeDAO() {

    }

    public String StatisticNV(ArrayList<bangChamCongDTO> listBCC, String MaNV) {
        bangChamCongBUS bccBUS = new bangChamCongBUS();
        String s = "";
        float sogiotre = 0;
        float sogiolamthem = 0;

        MySQLConnect mySQL = new MySQLConnect();

        // Tổng tiền 
        String sql = "SELECT SUM(`Số giờ trễ`) AS `Số giờ trễ`, SUM(`Số giờ làm thêm`) AS `Số giờ làm thêm` "
                + "FROM bangchamcong WHERE ";
//        for (int i = 0; i < listBCC.size(); i++) {
//            if (i == (listBCC.size() - 1)) {
//                System.out.println("iffffffffffffffffffffffff");
//                sql += " `Mã nhân viên` ='" + MaNV + "') ";
//                break;
//            }
            sql += "`Mã nhân viên` ='" + MaNV + "'";
//        }
        System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                sogiotre += rs.getFloat("Số giờ trễ");
                sogiolamthem += rs.getFloat("Số giờ làm thêm");
            }
        } catch (SQLException ex) {
            Logger.getLogger(thongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        s+="-------------------------------------------------\n";
        s += "Số GIỜ ĐI TRỄ: " + sogiotre + "\n";
        s += "Số GIỜ LÀM THÊM: " + sogiolamthem + "\n";
        return s;
    }

    public String StatisticNV(String MaNV) {
        bangChamCongBUS bccBUS = new bangChamCongBUS();
        String s = "";
        float sogiotre = bccBUS.soGioTre(MaNV);
        float sogiolamthem = bccBUS.soGioLamThem(MaNV);
        s += "Số GIỜ ĐI TRỄ: " + sogiotre + "\n";
        s += "Số GIỜ LÀM THÊM: " + sogiolamthem + "\n";
        return s;
    }

    public ArrayList<luongDTO> StatisticL(int top, String rank) {
        MySQLConnect mySQL = new MySQLConnect();
        ArrayList<luongDTO> dsLuong = new ArrayList<>();
        String sql = "";
        if (rank.equals("thapnhat")) {
            sql = "SELECT l.`Mã lương`, l.`Mã bảng chấm công`, bcc.`Mã nhân viên`, "
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
                    + "ORDER BY `Lương chính thức` ASC "
                    + "LIMIT " + top;
            System.out.println(sql);

        } else {
            sql = "SELECT l.`Mã lương`, l.`Mã bảng chấm công`, bcc.`Mã nhân viên`, "
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
                    + "ORDER BY `Lương chính thức` DESC "
                    + "LIMIT " + top;
            System.out.println(sql);
        }
        System.out.println(sql);

        ResultSet rs = mySQL.executeQuery(sql);
        try {
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
                dsLuong.add(luong);

            }
        } catch (SQLException ex) {
            Logger.getLogger(thongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsLuong;
    }

}
