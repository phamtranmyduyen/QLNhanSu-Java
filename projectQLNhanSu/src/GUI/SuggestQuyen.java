/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.quyenBUS;
import DTO.chucNangDTO;
import DTO.quyenDTO;
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
public class SuggestQuyen extends JDialog {

    private quyenBUS qBUS = new quyenBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbQuyen;
    private DefaultTableModel model;
    JTextField[] txtQuyen;
    JLabel[] lbQuyen;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestQuyen() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH QUYỀN", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbQuyen.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbQuyen.getSelectedRow();
                if (tbQuyen.getRowSorter() != null) {
                    i = tbQuyen.getRowSorter().convertRowIndexToModel(i);
                }
                txtQuyen[0].setText(tbQuyen.getModel().getValueAt(i, 0).toString());
                txtQuyen[1].setText(tbQuyen.getModel().getValueAt(i, 1).toString());

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
        pnDisplay.setPreferredSize(new Dimension(1300, 730 / 3));

        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, (730 / 3) - 70));
        String[] arrQuyen = {"Mã quyền", "Tên quyền"};
        txtQuyen = new JTextField[arrQuyen.length];
        lbQuyen = new JLabel[arrQuyen.length];
        int xLb = 390, yLb = 40;
        int xTxt = 600, yTxt = 40;
        for (int i = 0; i < arrQuyen.length; i++) {
            lbQuyen[i] = new JLabel(arrQuyen[i]);
            lbQuyen[i].setBounds(xLb, yLb, 200, 40);
            lbQuyen[i].setHorizontalAlignment(JLabel.CENTER);
            lbQuyen[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbQuyen[i].setName("lb" + i);
            pnDisplayTop.add(lbQuyen[i]);
            yLb = yLb + 70;
            txtQuyen[i] = new JTextField();
            txtQuyen[i].setName("txt" + i);
            txtQuyen[i].setBounds(xTxt, yTxt, 240, 40);
            pnDisplayTop.add(txtQuyen[i]);
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
        pnTable.setPreferredSize(new Dimension(1300, 420));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã quyền");
        header.add("Tên quyền");
        model = new DefaultTableModel(header, 2);
        tbQuyen = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbQuyen.setRowSorter(rowSorter);
        listQuyen(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbQuyen.setFocusable(false);
        tbQuyen.setIntercellSpacing(new Dimension(0, 0));
        tbQuyen.getTableHeader().setFont(font);
        tbQuyen.setRowHeight(30);
        tbQuyen.getTableHeader().setOpaque(false);
        tbQuyen.setFillsViewportHeight(true);
        tbQuyen.getTableHeader().setBackground(new Color(232, 57, 99));
        tbQuyen.getTableHeader().setForeground(Color.WHITE);
        tbQuyen.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbQuyen);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<quyenDTO> q) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (quyenDTO quyen : q) {
            data = new Vector();
            data.add(quyen.getMaquyen());
            data.add(quyen.getTenquyen());
            model.addRow(data);
        }
        tbQuyen.setModel(model);
    }

    public void listQuyen() // Chép ArrayList lên table
    {
        if (qBUS.getList() == null) {
            qBUS.listQuyen();
        }

        ArrayList<quyenDTO> q = qBUS.getList();
        outModel(model, q);
    }

    public String getTextFieldContent() {
        return txtQuyen[0].getText();
    }

}
