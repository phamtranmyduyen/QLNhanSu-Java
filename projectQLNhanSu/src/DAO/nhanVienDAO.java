
package DAO;

import DTO.nhanVienDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author MaiThy
 */
public class nhanVienDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public nhanVienDAO() {

    }

    public void set(nhanVienDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET ";
        sql += "`Mã nhân viên`='" + nv.getManhanvien() + "', ";
        sql += "`Họ tên`='" + nv.getHoten() + "', ";
        sql += "`Email`='" + nv.getEmail() + "', ";
        sql += "`Số điện thoại`='" + nv.getSodienthoai() + "', ";
        sql += "`Ngày sinh`='" + nv.getNgaysinh() + "', ";
        sql += "`Địa chỉ`='" + nv.getDiachi() + "', ";
        sql += "`Giới tính`='" + nv.getGioitinh() + "', ";
        sql += "`CMND`='" + nv.getCmnd() + "', ";
        sql += "`Tên tài khoản`='" + nv.getTentaikhoan() + "', ";
        sql += "`Mã bằng cấp`='" + nv.getMabangcap() + "', ";
        sql += "`Mã HĐLĐ`='" + nv.getMahdld() + "', ";
        sql += "`Mã phòng ban`='" + nv.getMaphongban() + "' ";
        sql += " WHERE `Mã nhân viên`='" + nv.getManhanvien() + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void add(nhanVienDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO nhanvien VALUES (";
        sql += "'" + nv.getManhanvien() + "',";
        sql += "'" + nv.getHoten() + "',";
        sql += "'" + nv.getEmail() + "',";
        sql += "'" + nv.getSodienthoai() + "',";
        sql += "'" + nv.getNgaysinh() + "',";
        sql += "'" + nv.getDiachi() + "',";
        sql += "'" + nv.getGioitinh() + "',";
        sql += "'" + nv.getCmnd() + "',";
        sql += "'" + nv.getTentaikhoan() + "',";
        sql += "'" + nv.getMabangcap() + "',";
        sql += "'" + nv.getMahdld() + "',";
        sql += "'" + nv.getMaphongban() + "')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String manv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM nhanvien WHERE `Mã nhân viên`='" + manv + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<nhanVienDTO> list() {
        ArrayList<nhanVienDTO> dsnhanvien = new ArrayList<>();
        try {
            String sql = "SELECT * FROM nhanvien";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String manhanvien = rs.getString("Mã nhân viên");
                String hoten = rs.getString("Họ tên");
                String email = rs.getString("Email");
                String sodienthoai = rs.getString("Số điện thoại");
                String ngaysinh = rs.getString("Ngày sinh");
                String diachi = rs.getString("Địa chỉ");
                String gioitinh = rs.getString("Giới tính");
                String cmnd = rs.getString("CMND");
                String mataikhoan = rs.getString("Tên tài khoản");
                String mabangcap = rs.getString("Mã bằng cấp");
                String mahdld = rs.getString("Mã HĐLĐ");
                String maphongban = rs.getString("Mã phòng ban");
                nhanVienDTO nhanvien = new nhanVienDTO(manhanvien, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, mataikhoan, mabangcap, mahdld, maphongban);
                dsnhanvien.add(nhanvien);
            }

            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            System.out.println("Lỗi load nhân viên");
        }
        return dsnhanvien;
    }

    public void ExportExcelDatabase() {
        try {
            String sql = "SELECT * FROM nhanvien";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("DanhSachNhanVien");

            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("Họ tên");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("Email");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("Số điện thoại");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("Ngày sinh");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("Địa chỉ");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("Giới tính");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue("CMND");
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue("Tên tài khoản");
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue("Mã bằng cấp");
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue("Mã HĐLĐ");
            cell.setCellStyle(style);
            cell = row.createCell(11);            
            cell.setCellValue("Mã phòng ban");
            cell.setCellStyle(style);
            int i = 1;
            while (rs.next()) {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString("Mã nhân viên"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("Họ tên"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString("Email"));
                cell = row.createCell(3);
                cell.setCellValue(rs.getString("Số điện thoại"));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString("Ngày sinh"));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString("Địa chỉ"));
                cell = row.createCell(6);
                cell.setCellValue(rs.getString("Giới tính"));
                cell = row.createCell(7);
                cell.setCellValue(rs.getString("CMND"));
                cell = row.createCell(8);
                cell.setCellValue(rs.getString("Tên tài khoản"));
                cell = row.createCell(9);
                cell.setCellValue(rs.getString("Mã bằng cấp"));
                cell = row.createCell(10);
                cell.setCellValue(rs.getString("Mã HĐLĐ"));
                cell = row.createCell(11);
                cell.setCellValue(rs.getString("Mã phòng ban"));
                i++;
            }

            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                sheet.autoSizeColumn((short) (colNum));
            }

            FileOutputStream out = new FileOutputStream(new File("./report/DanhSachNhanVien.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Xuất file thành công");

        } catch (SQLException ex) {
            System.out.println("Lỗi SQL");
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi File");
        } catch (IOException ex) {
            System.out.println("Lỗi IO");
        }

    }

    public void ImportExcelDatabase(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                String manhanvien = row.getCell(0).getStringCellValue();
                String hoten = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String sodienthoai = row.getCell(3).getStringCellValue();
                String ngaysinh = row.getCell(4).getStringCellValue();
                String diachi = row.getCell(5).getStringCellValue();
                String gioitinh = row.getCell(6).getStringCellValue();
                String cmnd = row.getCell(7).getStringCellValue();
                String tentaikhoan = row.getCell(8).getStringCellValue();
                String mabangcap = row.getCell(9).getStringCellValue();
                String mahdld = row.getCell(10).getStringCellValue();
                String maphongban = row.getCell(11).getStringCellValue();

                String sql_check = "SELECT * FROM nhanvien WHERE `Mã nhân viên`='" + manhanvien + "'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if (!rs.next()) {
                    String sql = "INSERT INTO nhanvien VALUES (";
                    sql += "'" + manhanvien + "',";
                    sql += "'" + hoten + "',";
                    sql += "'" + email + "',";
                    sql += "'" + sodienthoai + "',";
                    sql += "'" + ngaysinh + "',";
                    sql += "'" + diachi + "',";
                    sql += "'" + gioitinh + "',";
                    sql += "'" + cmnd + "',";
                    sql += "'" + tentaikhoan + "',";
                    sql += "'" + mabangcap + "',";
                    sql += "'" + mahdld + "',";
                    sql += "'" + maphongban + "')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                } else {
                    String sql = "UPDATE nhanvien SET ";
                    sql += "`Mã nhân viên`='" + manhanvien + "', ";
                    sql += "`Họ tên`='" + hoten + "', ";
                    sql += "`Email`='" + email + "', ";
                    sql += "`Số điện thoại`='" + sodienthoai + "', ";
                    sql += "`Ngày sinh`='" + ngaysinh + "', ";
                    sql += "`Địa chỉ`='" + diachi + "', ";
                    sql += "`Giới tính`='" + gioitinh + "', ";
                    sql += "`CMND`='" + cmnd + "', ";
                    sql += "`Tên tài khoản`='" + tentaikhoan + "', ";
                    sql += "`Mã bằng cấp`='" + mabangcap + "', ";
                    sql += "`Mã HĐLĐ`='" + mahdld + "',";
                    sql += "`Mã phòng ban`='" + maphongban + "' ";
                    sql += " WHERE `Mã nhân viên`='" + manhanvien + "'";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();

        } catch (FileNotFoundException ex) {

        } catch (IOException | SQLException ex) {

        }
    }
  public String manhanvien() {
        String manhanvien = "";
        String stt = "";
        try {
            String sql = "SELECT `Mã nhân viên` "
                    + "FROM `nhanvien` "
                    + "ORDER BY `Mã nhân viên` DESC "
                    + "LIMIT 1";
            ResultSet rs = mySQL.executeQuery(sql);
            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                stt = "0";

            } else {
                while (rs.next()) {
                    stt = rs.getString("Mã nhân viên");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(bangChamCongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        stt = stt.replaceAll("[^0-9]", "");
        manhanvien = String.format("NV%03d", Integer.parseInt(stt) + 1);
        return manhanvien;
    }
}
