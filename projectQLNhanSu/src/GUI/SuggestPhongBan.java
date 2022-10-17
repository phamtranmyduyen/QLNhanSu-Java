/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.quyenBUS;
import BUS.phongBanBUS;
import DTO.chucNangDTO;
import DTO.quyenDTO;
import DTO.phongBanDTO;
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
public class SuggestPhongBan extends JDialog {
    
    private phongBanBUS tkBUS = new phongBanBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbPhongBan;
    private DefaultTableModel model;
    JTextField[] txtPhongBan;
    JLabel[] lbPhongBan;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;
    
    public SuggestPhongBan() {
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
        
        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH PHÒNG BAN", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbPhongBan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbPhongBan.getSelectedRow();
                txtPhongBan[0].setText(tbPhongBan.getModel().getValueAt(i, 0).toString());
                txtPhongBan[1].setText(tbPhongBan.getModel().getValueAt(i, 1).toString());
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
        String[] arrPhongBan = {"Mã phòng", "Tên phòng"};
        txtPhongBan = new JTextField[arrPhongBan.length];
        lbPhongBan = new JLabel[arrPhongBan.length];
        int xLb = 390, yLb = 20;
        int xTxt = 600, yTxt = 20;
        for (int i = 0; i < arrPhongBan.length; i++) {
            lbPhongBan[i] = new JLabel(arrPhongBan[i]);
            lbPhongBan[i].setBounds(xLb, yLb, 200, 30);
            lbPhongBan[i].setHorizontalAlignment(JLabel.CENTER);
            lbPhongBan[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbPhongBan[i].setName("lb" + i);
            pnDisplayTop.add(lbPhongBan[i]);
            yLb = yLb + 50;
            txtPhongBan[i] = new JTextField();
            txtPhongBan[i].setName("txt" + i);
            txtPhongBan[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtPhongBan[i]);
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
        header.add("Mã phòng");
        header.add("Tên phòng");
        model = new DefaultTableModel(header, 4);
        tbPhongBan = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbPhongBan.setRowSorter(rowSorter);
        listPhongBan(); //Đọc từ database lên table 

        tbPhongBan.setFocusable(false);
        tbPhongBan.setIntercellSpacing(new Dimension(0, 0));
        tbPhongBan.setRowHeight(30);
        tbPhongBan.getTableHeader().setOpaque(false);
        tbPhongBan.setFillsViewportHeight(true);
        tbPhongBan.getTableHeader().setBackground(new Color(232, 57, 99));
        tbPhongBan.getTableHeader().setForeground(Color.WHITE);
        tbPhongBan.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbPhongBan);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);
        
        pnTable.add(scroll);
        return pnTable;
    }
    
    public void outModel(DefaultTableModel model, ArrayList<phongBanDTO> tk) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (phongBanDTO n : tk) {
            data = new Vector();
            data.add(n.getMaphong());
            data.add(n.getTenphong());
            model.addRow(data);
        }
        tbPhongBan.setModel(model);
    }
    
    public void listPhongBan() // Chép ArrayList lên table
    {
        if (tkBUS.getList() == null) {
            tkBUS.listPhongBan();
        }
        ArrayList<phongBanDTO> tk = tkBUS.getList();
        outModel(model, tk);
    }
    
    public String getTextFieldContent() {
        return txtPhongBan[0].getText();
    }
}
