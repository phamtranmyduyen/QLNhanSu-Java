/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.bangChamCongBUS;
import DTO.bangChamCongDTO; 
import GUI.model.headerSuggest;
import GUI.model.navItem;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MaiThy
 */
public class SuggestBangChamCong extends JDialog {

    private bangChamCongBUS bccBUS = new bangChamCongBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbBangChamCong;
    private DefaultTableModel model;
    JTextField[] txtBangChamCong;
    JLabel[] lbBangChamCong;
    Font font;
    TableRowSorter<TableModel> rowSorter;
    String manhanvien;

    public SuggestBangChamCong(String manhanvien) {
        this.manhanvien = manhanvien;
        setModal(true);
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 14);
        setSize(1300, 700);

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 730));
        /**
         * ********** PHẦN HEADER ************************************
         */
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH BẢNG CHẤM CÔNG", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbBangChamCong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbBangChamCong.getSelectedRow();
                if (tbBangChamCong.getRowSorter() != null) {
                    i = tbBangChamCong.getRowSorter().convertRowIndexToModel(i);
                }
                txtBangChamCong[0].setText(tbBangChamCong.getModel().getValueAt(i, 0).toString());
                txtBangChamCong[1].setText(tbBangChamCong.getModel().getValueAt(i, 1).toString());
                txtBangChamCong[2].setText(tbBangChamCong.getModel().getValueAt(i, 2).toString());
                txtBangChamCong[3].setText(tbBangChamCong.getModel().getValueAt(i, 3).toString());
                txtBangChamCong[4].setText(tbBangChamCong.getModel().getValueAt(i, 4).toString());

            }
        });
        this.add(header, BorderLayout.NORTH);
        this.add(pnDisplay, BorderLayout.CENTER);
        this.add(pnTable, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(new BorderLayout());
        pnDisplay.setPreferredSize(new Dimension(1300, 280));

        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, 280 - 60));
        String[] arrBangChamCong = {"Mã bảng chấm công", "Mã nhân viên", "Thời gian", "Số giờ trễ", "Số giờ làm thêm"};
        txtBangChamCong = new JTextField[arrBangChamCong.length];
        lbBangChamCong = new JLabel[arrBangChamCong.length];
        int xLb = 390, yLb = 5;
        int xTxt = 600, yTxt = 5;
        for (int i = 0; i < arrBangChamCong.length; i++) {
            lbBangChamCong[i] = new JLabel(arrBangChamCong[i]);
            lbBangChamCong[i].setBounds(xLb, yLb, 200, 30);
            lbBangChamCong[i].setHorizontalAlignment(JLabel.CENTER);
            lbBangChamCong[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbBangChamCong[i].setName("lb" + i);
            pnDisplayTop.add(lbBangChamCong[i]);
            yLb = yLb +45;
            txtBangChamCong[i] = new JTextField();
            txtBangChamCong[i].setName("txt" + i);
            txtBangChamCong[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtBangChamCong[i]);
            yTxt = yTxt + 45;
        }
        pnOption();
        pnDisplay.add(pnDisplayTop, BorderLayout.NORTH);
        pnDisplay.add(pnOption, BorderLayout.CENTER);
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(new FlowLayout());
        pnOption.setPreferredSize(new Dimension(1300, 70));
        JLabel btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setPreferredSize(new Dimension(155, 70));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        JLabel btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setPreferredSize(new Dimension(155, 70));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        pnOption.add(btnConfirm);
        pnOption.add(btnBack);

        pnOption.setBackground(Color.pink);
        return pnOption;
    }

    public JPanel pnTable() {
        pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setPreferredSize(new Dimension(1300, 380));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã bảng chấm công");
        header.add("Mã nhân viên");
        header.add("Thời gian");
        header.add("Số giờ trễ");
        header.add("Số giờ làm thêm");
        model = new DefaultTableModel(header, 2);
        tbBangChamCong = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbBangChamCong.setRowSorter(rowSorter);
        listBangChamCong(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbBangChamCong.setFocusable(false);
        tbBangChamCong.setIntercellSpacing(new Dimension(0, 0));
        tbBangChamCong.getTableHeader().setFont(font);
        tbBangChamCong.setRowHeight(30);
        tbBangChamCong.getTableHeader().setOpaque(false);
        tbBangChamCong.setFillsViewportHeight(true);
        tbBangChamCong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbBangChamCong.getTableHeader().setForeground(Color.WHITE);
        tbBangChamCong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbBangChamCong);
        scroll.setBounds(new Rectangle(0, 0, 1300, 380));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<bangChamCongDTO> bcc) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (bangChamCongDTO bccong : bcc) {
            data = new Vector();
            data.add(bccong.getMabangchamcong());
            data.add(bccong.getManhanvien());
            data.add(bccong.getThoigian());
            data.add(bccong.getSogiotre());
            data.add(bccong.getSogiolamthem());
            model.addRow(data);
        }
        tbBangChamCong.setModel(model);
    }

    public void listBangChamCong() // Chép ArrayList lên table
    {
        if (bccBUS.getList(manhanvien)== null) {
            bccBUS.listBangChamCong(manhanvien);
        }

        ArrayList<bangChamCongDTO> bcc = bccBUS.getList(manhanvien);
        outModel(model, bcc);
    }

    public String getTextFieldContent() {
        return txtBangChamCong[0].getText();
    }

}
