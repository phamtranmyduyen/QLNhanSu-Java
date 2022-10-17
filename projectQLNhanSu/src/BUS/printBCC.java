/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Shadow
 */
public class printBCC {

    private String file = "./report/test.pdf";
    private ArrayList<chiTietBangChamCongDTO> ctbcc = new ArrayList<chiTietBangChamCongDTO>();
    private bangChamCongDTO bcc;
    private BaseFont bf;
    String thoigian;

    public printBCC() {

    }

    public printBCC(bangChamCongDTO bcc, ArrayList<chiTietBangChamCongDTO> ctbcc) {
        this.bcc = bcc;
        thoigian = bcc.getThoigian().replace(" ", "");
        thoigian = thoigian.replace("/", "-");
        file = "./report/" + bcc.getMabangchamcong() + "-" + bcc.getManhanvien() + "-" + thoigian + ".pdf";
        this.ctbcc = ctbcc;
    }

    public void print() {
        String uderline = "*";
        try {
            //Tạo Font
            bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // Tạo tài liệu
            Document bangchamcong = new Document(PageSize.A4, 15, 15, 10, 10);

            String line = "";
            for (int i = 0; i < bangchamcong.getPageSize().getWidth() / 5; i++) {
                line += uderline;
            }
            //Tạo đối tượng writter
            PdfWriter.getInstance(bangchamcong, new FileOutputStream(file));

            //Mở document
            bangchamcong.open();

            Paragraph header = new Paragraph("BẢNG CHẤM CÔNG", new Font(bf, 35));
            header.setAlignment(1);
            bangchamcong.add(header);

            Paragraph info = new Paragraph("Bảng chấm công : " + bcc.getMabangchamcong() + "\nNhân viên: " + bcc.getManhanvien() + "\nThời gian: " + bcc.getThoigian(), new Font(bf, 15));
            bangchamcong.add(info);

            Paragraph l = new Paragraph(line);
            l.setAlignment(1);
            bangchamcong.add(l);

            String[] cellHeader = {"Ngày", "Giờ vào", "Giờ ra"};

            PdfPTable t = new PdfPTable(cellHeader.length);
            t.setSpacingAfter(10);
            t.setSpacingBefore(10);
            for (String s : cellHeader) {
                t.addCell(createCell(s, new Font(bf, 13)));
            }
            for (chiTietBangChamCongDTO ct : ctbcc) { 
                t.addCell(createCell(ct.getNgay()));
                t.addCell(createCell(ct.getGiovao()));
                t.addCell(createCell(ct.getGiora()));
            }
            bangchamcong.add(t);

            bangchamcong.add(l);

            Paragraph sogiotre = new Paragraph("Số giờ trễ : " + bcc.getSogiotre(), new Font(bf, 20));
            sogiotre.setAlignment(Element.ALIGN_LEFT);
            bangchamcong.add(sogiotre);
            Paragraph sogiolamthem = new Paragraph("Số giờ làm thêm : " + bcc.getSogiolamthem(), new Font(bf, 20));
            sogiolamthem.setAlignment(Element.ALIGN_LEFT);
            bangchamcong.add(sogiolamthem);

            bangchamcong.close();


            JOptionPane.showMessageDialog(null, "In hoàn tất");
            System.out.println("Done");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(printBCC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(printBCC.class.getName()).log(Level.SEVERE, null, ex);
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
