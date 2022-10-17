/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.bangChamCongBUS;
import BUS.heSoLuongBUS;
import BUS.nhanVienBUS;
import BUS.printBCC;
import BUS.phuCapBUS;
import BUS.printBangLuong;
import BUS.thuongBUS;
import DTO.bangChamCongDTO;
import DTO.heSoLuongDTO;
import DTO.nhanVienDTO;
import DTO.phuCapDTO;
import DTO.thuongDTO;
import GUI.model.checkError;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MaiThy
 */
public class xemLuongDIALOG extends JDialog {

    checkError check = new checkError();
    private bangChamCongBUS bccBUS = new bangChamCongBUS();
    Font font;
    JPanel pnHeader, pnContent, pnOption;
    JLabel lbHeader, lbOption, lbIn;
    JLabel[] lbContent;
    JTextField[] txtContent;
    String mabangluong, manhanvien, tennhanvien, mabangchamcong, thoigian;
    float sogiotre, sogiolamthem, luongcanban, thuong, hesoluong, phucap;
    double luongchinhthuc;

    public xemLuongDIALOG(String mabangluong, String mabangchamcong, String thoigian, String manhanvien, String tennhanvien, float luongcanban, float thuong, float hesoluong, float phucap, double luongchinhthuc, float sogiotre, float sogiolamthem) {
        this.mabangluong = mabangluong;
        this.mabangchamcong = mabangchamcong;
        this.thoigian = thoigian;
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.sogiotre = sogiotre;
        this.sogiolamthem = sogiolamthem;
        this.luongcanban = luongcanban;
        this.thuong = thuong;
        this.hesoluong = hesoluong;
        this.phucap = phucap;
        this.luongchinhthuc = luongchinhthuc;
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 20);
        setSize(500, 700);
        setLayout(new FlowLayout(1, 0, 0));
        setBackground(Color.pink);

//        HEADER
        pnHeader();
//       CONTENT
        pnContent();
//        OPTION
        pnOption();
//        ADD
        add(pnHeader);
        add(pnContent);
        add(pnOption);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Color.pink);
        setVisible(true);
    }

    public JPanel pnHeader() {
        lbHeader = new JLabel("Bảng lương " + manhanvien + " " + thoigian, JLabel.CENTER);
        lbHeader.setPreferredSize(new Dimension(500, 40));
        lbHeader.setFont(font);
        pnHeader = new JPanel();
        pnHeader.setPreferredSize(new Dimension(500, 40));
        pnHeader.add(lbHeader);
        pnHeader.setBackground(Color.pink);
        return pnHeader;
    }

    public JPanel pnContent() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        pnContent = new JPanel();
        pnContent.setLayout(null);
        pnContent.setPreferredSize(new Dimension(500, 580));
        pnContent.setBackground(Color.pink);
        String[] arrContent = {"Mã bảng lương", "Mã bảng chấm công", "Thời gian", "Mã nhân viên", "Tên nhân viên", "Lương căn bản", "Tiền thưởng", "Hệ số lương", "Tiền phụ cấp", "Số giờ trễ", "Số giờ làm thêm", "Tổng lương"};
        txtContent = new JTextField[arrContent.length];
        lbContent = new JLabel[arrContent.length];
        int xLb = 50, yLb = 25;
        int xTxt = 270, yTxt = 25;
        for (int i = 0; i < arrContent.length; i++) {
            lbContent[i] = new JLabel(arrContent[i]);
            lbContent[i].setBounds(xLb, yLb, 200, 30);
            lbContent[i].setHorizontalAlignment(JLabel.CENTER);
            lbContent[i].setFont(font);
            lbContent[i].setName("lb" + i);
            pnContent.add(lbContent[i]);
            yLb = yLb + 45;
            txtContent[i] = new JTextField();
            txtContent[i].setName("txt" + i);
            txtContent[i].setBounds(xTxt, yTxt, 190, 30);
            txtContent[i].setEditable(false);
            pnContent.add(txtContent[i]);
            yTxt = yTxt + 45;
        }
        txtContent[0].setText(mabangluong);
        txtContent[1].setText(mabangchamcong);
        txtContent[2].setText(thoigian);
        txtContent[3].setText(manhanvien);
        txtContent[4].setText(tennhanvien);
//        FORMAT LƯƠNG CĂN BẢN
        String luongcanbanStr = currencyVN.format((new Float(luongcanban)).longValue());
        txtContent[5].setText(luongcanbanStr);
//        FORMAT THƯỞNG
        String thuongStr = currencyVN.format((new Float(thuong)).longValue());
        txtContent[6].setText(thuongStr);
        txtContent[7].setText(Float.toString(hesoluong));
//        FORMAT PHỤ CẤP
        String phucapStr = currencyVN.format((new Float(phucap)).longValue());
        txtContent[8].setText(phucapStr);
        txtContent[9].setText(Float.toString(sogiotre));
        txtContent[10].setText(Float.toString(sogiolamthem));

        String luongchinhthucStr = currencyVN.format((new Double(luongchinhthuc)).longValue());
        txtContent[11].setText(luongchinhthucStr);
        return pnContent;

    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(new FlowLayout(1, 0, 10));
        pnOption.setPreferredSize(new Dimension(500, 80));

        lbOption = new JLabel(new ImageIcon("./src/img/back.png"), JLabel.CENTER);
        lbOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbOption.setPreferredSize(new Dimension(155, 50));
        pnOption.add(lbOption);
        pnOption.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        lbIn = new JLabel(new ImageIcon("./src/img/in.png"));
        lbIn.setPreferredSize(new Dimension(160, 55));
        lbIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOption.add(lbIn);
        lbIn.setBackground(Color.white);
        lbIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                printBangLuong printbl = new printBangLuong(mabangluong, mabangchamcong, thoigian, manhanvien, tennhanvien, luongcanban, thuong, hesoluong, phucap, sogiotre, sogiolamthem, luongchinhthuc);
                printbl.print();
            }
        });
        pnOption.setBackground(Color.pink);
        return pnOption;
    }

}
