/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.quyenBUS;
import BUS.taiKhoanBUS;
import DTO.chucNangDTO;
import DTO.quyenDTO;
import DTO.taiKhoanDTO;
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
public class SuggestTenTaiKhoan extends JDialog {
    
    private taiKhoanBUS tkBUS = new taiKhoanBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbTaiKhoan;
    private DefaultTableModel model;
    JTextField[] txtTaiKhoan;
    JLabel[] lbTaiKhoan;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;
    
    public SuggestTenTaiKhoan() {
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
        
        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH TÀI KHOẢN", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbTaiKhoan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbTaiKhoan.getSelectedRow();
                txtTaiKhoan[0].setText(tbTaiKhoan.getModel().getValueAt(i, 0).toString());
                txtTaiKhoan[1].setText(tbTaiKhoan.getModel().getValueAt(i, 1).toString());
                txtTaiKhoan[2].setText(tbTaiKhoan.getModel().getValueAt(i, 2).toString());
                txtTaiKhoan[3].setText(tbTaiKhoan.getModel().getValueAt(i, 3).toString());
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
        String[] arrTaiKhoan = {"Tên tài khoản", "Mật khẩu", "Trạng thái", "Mã quyền"};
        txtTaiKhoan = new JTextField[arrTaiKhoan.length];
        lbTaiKhoan = new JLabel[arrTaiKhoan.length];
        int xLb = 390, yLb = 20;
        int xTxt = 600, yTxt = 20;
        for (int i = 0; i < arrTaiKhoan.length; i++) {
            lbTaiKhoan[i] = new JLabel(arrTaiKhoan[i]);
            lbTaiKhoan[i].setBounds(xLb, yLb, 200, 30);
            lbTaiKhoan[i].setHorizontalAlignment(JLabel.CENTER);
            lbTaiKhoan[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbTaiKhoan[i].setName("lb" + i);
            pnDisplayTop.add(lbTaiKhoan[i]);
            yLb = yLb + 50;
            txtTaiKhoan[i] = new JTextField();
            txtTaiKhoan[i].setName("txt" + i);
            txtTaiKhoan[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtTaiKhoan[i]);
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
        header.add("Tên tài khoản");
        header.add("Mật khẩu");
        header.add("Trạng thái");
        header.add("Mã quyền");
        model = new DefaultTableModel(header, 4);
        tbTaiKhoan = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbTaiKhoan.setRowSorter(rowSorter);
        listTaiKhoan(); //Đọc từ database lên table 

        tbTaiKhoan.setFocusable(false);
        tbTaiKhoan.setIntercellSpacing(new Dimension(0, 0));
        tbTaiKhoan.setRowHeight(30);
        tbTaiKhoan.getTableHeader().setOpaque(false);
        tbTaiKhoan.setFillsViewportHeight(true);
        tbTaiKhoan.getTableHeader().setBackground(new Color(232, 57, 99));
        tbTaiKhoan.getTableHeader().setForeground(Color.WHITE);
        tbTaiKhoan.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbTaiKhoan);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);
        
        pnTable.add(scroll);
        return pnTable;
    }
    
    public void outModel(DefaultTableModel model, ArrayList<taiKhoanDTO> tk) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (taiKhoanDTO n : tk) {
            data = new Vector();
            data.add(n.getTentaikhoan());
            data.add(n.getMatkhau());
            if (n.getTrangthai().equals("0")) {
                data.add("Hoạt động");
            } else data.add("Không hoạt động");
                
            data.add(n.getMaquyen());
            model.addRow(data);
        }
        tbTaiKhoan.setModel(model);
    }
    
    public void listTaiKhoan() // Chép ArrayList lên table
    {
        if (tkBUS.getList() == null) {
            tkBUS.listTaiKhoan();
        }
        ArrayList<taiKhoanDTO> tk = tkBUS.getList();
        outModel(model, tk);
    }
    
    public String getTextFieldContent() {
        return txtTaiKhoan[0].getText();
    }
}
