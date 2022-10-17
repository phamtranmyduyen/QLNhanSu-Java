/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.bangChamCongBUS;
import BUS.chiTietBangChamCongBUS;
import BUS.nhanVienBUS;
import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
import DTO.nhanVienDTO;
import GUI.model.checkError;
import GUI.model.formatDate;
import GUI.model.ngayTrongThang;
import GUI.model.pnHHMMSS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.apache.poi.hslf.util.LocaleDateFormat;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author MaiThy
 */
public class taoBangChamCongDIALOG extends JDialog {

    checkError check = new checkError();
    private bangChamCongBUS bccBUS = new bangChamCongBUS();
    Font font;
    JPanel pnHeader, pnContent, pnOption;
    JLabel lbHeader, lbOption;
    JLabel[] lbContent;
    JTextField[] txtContent;
    UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    JXDatePicker picker;

    public taoBangChamCongDIALOG() {
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 20);
        setSize(500, 300);
        setLayout(new FlowLayout(1, 0, 0));
        setBackground(Color.white);
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
        setBackground(Color.pink);
        setVisible(true);
    }

    public JPanel pnHeader() {
        lbHeader = new JLabel("TẠO BẢNG CHẤM CÔNG", JLabel.CENTER);
        lbHeader.setPreferredSize(new Dimension(500, 40));
        lbHeader.setFont(font);
        pnHeader = new JPanel();
        pnHeader.setPreferredSize(new Dimension(500, 40));
        pnHeader.add(lbHeader);
        pnHeader.setBackground(Color.pink);
        return pnHeader;
    }

    public JPanel pnContent() {
        pnContent = new JPanel();
        pnContent.setLayout(null);
        pnContent.setPreferredSize(new Dimension(500, 130));
        pnContent.setBackground(Color.pink);
        String[] arrContent = {"Thời gian"};
        txtContent = new JTextField[arrContent.length];
        lbContent = new JLabel[arrContent.length];
        int xLb = 50, yLb = 50;
        int xTxt = 270, yTxt = 50;
        for (int i = 0; i < arrContent.length; i++) {
            lbContent[i] = new JLabel(arrContent[i]);
            lbContent[i].setBounds(xLb, yLb, 200, 30);
            lbContent[i].setHorizontalAlignment(JLabel.CENTER);
            lbContent[i].setFont(font);
            lbContent[i].setName("lb" + i);
            pnContent.add(lbContent[i]);
            yLb = yLb + 45;
            if (i == 0) {
                picker = new JXDatePicker();
                picker.setFormats(new SimpleDateFormat("MM/yyyy"));
                Calendar calendar = picker.getMonthView().getCalendar();
                calendar.setTime(new Date());
                picker.getMonthView().setLowerBound(calendar.getTime());
                picker.setBounds(xTxt, yTxt, 190, 30);
                pnContent.add(picker);
                yTxt = yTxt + 45;
            } else {
                txtContent[i] = new JTextField();
                txtContent[i].setName("txt" + i);
                txtContent[i].setBounds(xTxt, yTxt, 190, 30);
                pnContent.add(txtContent[i]);
                yTxt = yTxt + 45;
            }

        }
        return pnContent;

    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(new FlowLayout(1, 0, 10));
        pnOption.setPreferredSize(new Dimension(500, 80));
        lbOption = new JLabel(new ImageIcon("./src/img/done.png"), JLabel.CENTER);
        lbOption.setPreferredSize(new Dimension(155, 50));
        pnOption.add(lbOption);
        pnOption.setBackground(Color.pink);
        pnOption.addMouseListener(new MouseAdapter() {
            int i;

            public void mouseClicked(MouseEvent e) {
                i = JOptionPane.showConfirmDialog(null, "Xác nhận tạo bảng chấm công?", "", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    //Lấy dữ liệu từ TextField

                    String thoigian = picker.getEditor().getText();
                    bccBUS.listBangChamCong();
                    if (bccBUS.checkThoiGian(thoigian)) {
                        JOptionPane.showMessageDialog(null, "Bảng chấm công tháng này đã tồn tại!");
                        return;
                    }
                    nhanVienBUS nvBUS = new nhanVienBUS();
                    if (nvBUS.getList() == null) {
                        nvBUS.listNhanVien();
                    }
                    ArrayList<nhanVienDTO> nv = nvBUS.getList();
                    for (nhanVienDTO n : nv) {
                        String mabangchamcong = bccBUS.mabangchamcong();                  
                        bangChamCongDTO bcc = new bangChamCongDTO(mabangchamcong, n.getManhanvien(), thoigian, 0, 0);
                        bccBUS.addBangChamCong(bcc);
                        int month = Integer.parseInt(thoigian.split("/")[0]);
                        int year = Integer.parseInt(thoigian.split("/")[1]);
                        ngayTrongThang ngaytrongthang = new ngayTrongThang(month, year);
                        int ngay = ngaytrongthang.tinhNgayTrongThang(month, year);
                        for (int j = 0; j < ngay; j++) {
                            int date = j + 1;
                            String dateCTBCC = year + "-" + month + "-" + date;
                            chiTietBangChamCongDTO ctbcc = new chiTietBangChamCongDTO(mabangchamcong, dateCTBCC, "0:00:00", "0:00:00");
                            chiTietBangChamCongBUS ctbccBUS = new chiTietBangChamCongBUS();
                            ctbccBUS.addChiTietBangChamCong(ctbcc);
                        }

                    }

//                    chiTietBangChamCongGUI ctbcc = new chiTietBangChamCongGUI(mabangchamcong, manhanvien, thoigian);
                    dispose();
                }
            }
        });
        return pnOption;
    }

}
