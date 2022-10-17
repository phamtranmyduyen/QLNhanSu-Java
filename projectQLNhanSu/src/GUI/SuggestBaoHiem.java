/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.baoHiemBUS;

import DTO.baoHiemDTO;
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
public class SuggestBaoHiem extends JDialog {

    private baoHiemBUS tkBUS = new baoHiemBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbBaoHiem;
    private DefaultTableModel model;
    JTextField[] txtBaoHiem;
    JLabel[] lbBaoHiem;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestBaoHiem() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH BẢO HIỂM", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbBaoHiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbBaoHiem.getSelectedRow();
                txtBaoHiem[0].setText(tbBaoHiem.getModel().getValueAt(i, 0).toString());
                txtBaoHiem[1].setText(tbBaoHiem.getModel().getValueAt(i, 1).toString());
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
        String[] arrBaoHiem = {"Mã bảo hiểm", "Tên bảo hiểm"};
        txtBaoHiem = new JTextField[arrBaoHiem.length];
        lbBaoHiem = new JLabel[arrBaoHiem.length];
        int xLb = 390, yLb = 60;
        int xTxt = 600, yTxt = 60;
        for (int i = 0; i < arrBaoHiem.length; i++) {
            lbBaoHiem[i] = new JLabel(arrBaoHiem[i]);
            lbBaoHiem[i].setBounds(xLb, yLb, 200, 30);
            lbBaoHiem[i].setHorizontalAlignment(JLabel.CENTER);
            lbBaoHiem[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbBaoHiem[i].setName("lb" + i);
            pnDisplayTop.add(lbBaoHiem[i]);
            yLb = yLb + 70;
            txtBaoHiem[i] = new JTextField();
            txtBaoHiem[i].setName("txt" + i);
            txtBaoHiem[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtBaoHiem[i]);
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
        header.add("Mã bảo hiểm");
        header.add("Tên bảo hiểm");
        model = new DefaultTableModel(header, 4);
        tbBaoHiem = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbBaoHiem.setRowSorter(rowSorter);
        listBaoHiem(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        tbBaoHiem.setFocusable(false);
        tbBaoHiem.setIntercellSpacing(new Dimension(0, 0));
        tbBaoHiem.setRowHeight(30);
        tbBaoHiem.getTableHeader().setOpaque(false);
        tbBaoHiem.setFillsViewportHeight(true);
        tbBaoHiem.getTableHeader().setBackground(new Color(232, 57, 99));
        tbBaoHiem.getTableHeader().setForeground(Color.WHITE);
        tbBaoHiem.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbBaoHiem);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<baoHiemDTO> tk) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (baoHiemDTO n : tk) {
            data = new Vector();
            data.add(n.getMabaohiem());
            data.add(n.getTenbaohiem());
            model.addRow(data);
        }
        tbBaoHiem.setModel(model);
    }

    public void listBaoHiem() // Chép ArrayList lên table
    {
        if (tkBUS.getList() == null) {
            tkBUS.listBaoHiem();
        }
        ArrayList<baoHiemDTO> tk = tkBUS.getList();
        outModel(model, tk);
    }

    public String getTextFieldContent() {
        return txtBaoHiem[0].getText();
    }
}
