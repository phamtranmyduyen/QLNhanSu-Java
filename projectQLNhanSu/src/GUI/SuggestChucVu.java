/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucVuBUS;

import DTO.chucVuDTO;
import GUI.model.headerSuggest;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MaiThy
 */
public class SuggestChucVu extends JDialog {

    private chucVuBUS cvBUS = new chucVuBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbChucVu;
    private DefaultTableModel model;
    JTextField[] txtChucVu;
    JLabel[] lbChucVu;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestChucVu() {
        setModal(true);
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 14);

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 730));
        /**
         * ********** PHẦN HEADER ************************************
         */
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH CHỨC VỤ", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbChucVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbChucVu.getSelectedRow();
                txtChucVu[0].setText(tbChucVu.getModel().getValueAt(i, 0).toString());
                txtChucVu[1].setText(tbChucVu.getModel().getValueAt(i, 1).toString());
                
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
        pnDisplay.setPreferredSize(new Dimension(1300, 300));
        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, 300 - 70));
        String[] arrChucVu = {"Mã chức vụ", "Tên chức vụ"};
        txtChucVu = new JTextField[arrChucVu.length];
        lbChucVu = new JLabel[arrChucVu.length];
        int xLb = 390, yLb = 60;
        int xTxt = 600, yTxt = 60;
        for (int i = 0; i < arrChucVu.length; i++) {
            lbChucVu[i] = new JLabel(arrChucVu[i]);
            lbChucVu[i].setBounds(xLb, yLb, 200, 30);
            lbChucVu[i].setHorizontalAlignment(JLabel.CENTER);
            lbChucVu[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbChucVu[i].setName("lb" + i);
            pnDisplayTop.add(lbChucVu[i]);
            yLb = yLb + 70;
            txtChucVu[i] = new JTextField();
            txtChucVu[i].setName("txt" + i);
            txtChucVu[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtChucVu[i]);
            yTxt = yTxt + 70;
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
        pnTable.setPreferredSize(new Dimension(1300, 360));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã chức vụ");
        header.add("Tên chức vụ");
        model = new DefaultTableModel(header, 4);
        tbChucVu = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbChucVu.setRowSorter(rowSorter);
        listChucVu(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        tbChucVu.setFocusable(false);
        tbChucVu.setIntercellSpacing(new Dimension(0, 0));
        tbChucVu.setRowHeight(30);
        tbChucVu.getTableHeader().setOpaque(false);
        tbChucVu.setFillsViewportHeight(true);
        tbChucVu.getTableHeader().setBackground(new Color(232, 57, 99));
        tbChucVu.getTableHeader().setForeground(Color.WHITE);
        tbChucVu.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbChucVu);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<chucVuDTO> tk) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (chucVuDTO n : tk) {
            data = new Vector();
            data.add(n.getMachucvu());
            data.add(n.getTenchucvu());
            model.addRow(data);
        }
        tbChucVu.setModel(model);
    }

    public void listChucVu() // Chép ArrayList lên table
    {
        if (cvBUS.getList() == null) {
            cvBUS.listChucVu();
        }
        ArrayList<chucVuDTO> tk = cvBUS.getList();
        outModel(model, tk);
    }

    public String getTextFieldContent() {
        return txtChucVu[0].getText();
    }
}
