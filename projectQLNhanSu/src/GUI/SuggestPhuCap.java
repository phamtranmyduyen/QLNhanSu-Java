/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.luongBUS;
import BUS.phuCapBUS;
import DTO.phuCapDTO;
import DTO.luongDTO;
//import DTO.taiKhoanDTO;
import GUI.model.headerSuggest;
import GUI.model.navItem;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
public class SuggestPhuCap extends JDialog {

    private phuCapBUS tkBUS = new phuCapBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbPhuCap;
    private DefaultTableModel model;
    JTextField[] txtPhuCap;
    JLabel[] lbPhuCap;
    Font font;   

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestPhuCap() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH PHỤ CẤP",1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbPhuCap.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbPhuCap.getSelectedRow();
                txtPhuCap[0].setText(tbPhuCap.getModel().getValueAt(i, 0).toString());
                txtPhuCap[1].setText(tbPhuCap.getModel().getValueAt(i, 1).toString());
                txtPhuCap[2].setText(tbPhuCap.getModel().getValueAt(i, 2).toString());
//                txtPhuCap[3].setText(tbPhuCap.getModel().getValueAt(i, 3).toString());
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
        pnDisplayTop.setPreferredSize(new Dimension(1300, 300-70));
        String[] arrPhuCap = {"Mã phụ cấp","Tên phụ cấp","Tiền phụ cấp"};
        txtPhuCap = new JTextField[arrPhuCap.length];
        lbPhuCap = new JLabel[arrPhuCap.length];
        int xLb = 390, yLb = 20;
        int xTxt = 600, yTxt = 20;
        for (int i = 0; i < arrPhuCap.length; i++) {
            lbPhuCap[i] = new JLabel(arrPhuCap[i]);
            lbPhuCap[i].setBounds(xLb, yLb, 200, 30);
            lbPhuCap[i].setHorizontalAlignment(JLabel.CENTER);
            lbPhuCap[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbPhuCap[i].setName("lb" + i);
            pnDisplayTop.add(lbPhuCap[i]);
            yLb = yLb + 50;
            txtPhuCap[i] = new JTextField();
            txtPhuCap[i].setName("txt" + i);
            txtPhuCap[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtPhuCap[i]);
            yTxt = yTxt + 50;
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
        header.add("Mã phụ cấp");
        header.add("Tên phụ cấp");
        header.add("Tiền phụ cấp");
        
        model = new DefaultTableModel(header, 4);
        tbPhuCap = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbPhuCap.setRowSorter(rowSorter);
        listPhuCap(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
//        tbPhuCap.getColumnModel().getColumn(0).setPreferredWidth(40);
//        tbPhuCap.getColumnModel().getColumn(1).setPreferredWidth(40);
        // Custom table
        tbPhuCap.setFocusable(false);
        tbPhuCap.setIntercellSpacing(new Dimension(0, 0));
        tbPhuCap.setRowHeight(30);
        tbPhuCap.getTableHeader().setOpaque(false);
        tbPhuCap.setFillsViewportHeight(true);
        tbPhuCap.getTableHeader().setBackground(new Color(232, 57, 99));
        tbPhuCap.getTableHeader().setForeground(Color.WHITE);
        tbPhuCap.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbPhuCap);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<phuCapDTO> tk) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (phuCapDTO n : tk) {
            data = new Vector();
            data.add(n.getMaphucap());
            data.add(n.getTenphucap());
            data.add(n.getTienphucap());
            
            model.addRow(data);
        }
        tbPhuCap.setModel(model);
    }

    public void listPhuCap() // Chép ArrayList lên table
    {
        if (tkBUS.getList() == null) {
            tkBUS.listPhuCap();
        }
        ArrayList<phuCapDTO> tk = tkBUS.getList();
        outModel(model, tk);
    }
    public String getTextFieldContent() {
        return txtPhuCap[0].getText();
    }
}