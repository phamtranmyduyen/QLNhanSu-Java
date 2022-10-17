/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Shadow
 */
public class printBangLuong {

    private String file = "./report/test.pdf";
    private BaseFont bf;
    String mabangluong, mabangchamcong, thoigian, manhanvien, tennhanvien;
    float luongcanban, hesoluong, tienthuong, tienphucap, sogiotre, sogiolamthem;
    double tongluong;
    String thoigiantmp;
    String luongcanbanGET, thuongGET, phucapGET, tongluongGET;

    public printBangLuong(String mabangluong, String mabangchamcong, String thoigian, String manhanvien, String tennhanvien, float luongcanban, float tienthuong, float hesoluong, float tienphucap, float sogiotre, float sogiolamthem, double tongluong) {
        this.mabangluong = mabangluong;
        this.mabangchamcong = mabangchamcong;
        this.thoigian = thoigian;
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.luongcanban = luongcanban;
        this.hesoluong = hesoluong;
        this.tienthuong = tienthuong;
        this.tienphucap = tienphucap;
        this.tongluong = tongluong;
        this.sogiotre = sogiotre;
        this.sogiolamthem = sogiolamthem;
        thoigiantmp = thoigian.replace(" ", "");
        thoigiantmp = thoigian.replace("/", "-");
        file = "./report/" + mabangluong + "-" + manhanvien + "-" + thoigiantmp + ".pdf";
    }

    public void print() {
        String uderline = "*";
        try {
            //Tạo Font
            bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // Tạo tài liệu
            Document bangluong = new Document(PageSize.A4, 15, 15, 10, 10);

            String line = "";
            for (int i = 0; i < bangluong.getPageSize().getWidth() / 5; i++) {
                line += uderline;
            }
            //Tạo đối tượng writter
            PdfWriter.getInstance(bangluong, new FileOutputStream(file));

            //Mở document
            bangluong.open();

            Paragraph header = new Paragraph("BẢNG LƯƠNG", new Font(bf, 35));
            header.setAlignment(1);
            bangluong.add(header);

            Paragraph info = new Paragraph(""
                    + "Bảng lương : "
                    + mabangluong
                    + "                                                               Thời gian: "
                    + thoigian
                    + "\nNhân viên: "
                    + manhanvien, new Font(bf, 15));

            bangluong.add(info);

            Paragraph l = new Paragraph(line);
            l.setAlignment(1);
            bangluong.add(l);
//            CONVERT TIỀN
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
            luongcanbanGET = currencyVN.format((new Float(luongcanban)).longValue());
            thuongGET = currencyVN.format((new Float(tienthuong)).longValue());
            phucapGET = currencyVN.format((new Float(tienphucap)).longValue());
            phucapGET = currencyVN.format((new Float(tienphucap)).longValue());
            tongluongGET = currencyVN.format((new Double(tongluong)).longValue());
            Paragraph main = new Paragraph(""
                    + "Họ tên nhân viên : "
                    + tennhanvien
                    + "\nLương căn bản: "
                    + luongcanbanGET
                    + "\nHệ số lương: "
                    + hesoluong
                    + "\nTiền thưởng: "
                    + thuongGET
                    + "\nTiền phụ cấp: "
                    + phucapGET
                    + "\nSố giờ đi trễ: "
                    + sogiotre
                    + "\nSố giờ làm thêm: "
                    + sogiolamthem, new Font(bf, 15));
            main.setAlignment(Element.ALIGN_LEFT);
            bangluong.add(main);
            bangluong.add(l);

            Paragraph luongchinhthuc = new Paragraph("Lương chính thức : " + tongluongGET, new Font(bf, 20));
            luongchinhthuc.setAlignment(Element.ALIGN_RIGHT);
            bangluong.add(luongchinhthuc);

            bangluong.close();

            JOptionPane.showMessageDialog(null, "In hoàn tất");
            System.out.println("Done");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(printBangLuong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(printBangLuong.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PdfPCell createCell(String s) {
        PdfPCell cell = new PdfPCell(new Phrase(s, new Font(bf, 13)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);

        return cell;
    }

    public PdfPCell createCell(String s, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);
        return cell;
    }
}
