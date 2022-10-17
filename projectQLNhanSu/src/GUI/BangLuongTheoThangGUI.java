/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.luongBUS;
import BUS.chucNangBUS;
import DTO.luongDTO;
import GUI.model.DateValidator;
import GUI.model.DateValidatorUsingDateFormat;
import GUI.model.headerSuggest;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author MaiThy
 */
public class BangLuongTheoThangGUI extends JDialog {

    DateValidator validatorDATE = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    private luongBUS lBUS = new luongBUS();
    private JPanel header, pnDisplay, pnFind, pnTable, pnOption;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbExit, lbSuggestChucVu;
    private JTable tbLuong;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField txtLuong1, txtLuong2;
    JLabel lbLuong1, lbLuong2;
    Font font;
    TableRowSorter<TableModel> rowSorter;
    JXDatePicker picker;
    String manhanvien, mabangluong, thoigian, mabangchamcong, tennhanvien;
    float luongcanban, thuong, hesoluong, phucap, sogiotre, sogiolamthem;
    double luongchinhthuc;

    public BangLuongTheoThangGUI() {
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 18);
        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 10, 1300, 730));
        setLocationRelativeTo(null);
        pnDisplay();
        pnTable(picker.getEditor().getText());
        tbLuong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbLuong.getSelectedRow();
                mabangluong = tbLuong.getModel().getValueAt(i, 0).toString();
                mabangchamcong = tbLuong.getModel().getValueAt(i, 1).toString();
                thoigian = tbLuong.getModel().getValueAt(i, 2).toString();
                manhanvien = tbLuong.getModel().getValueAt(i, 3).toString();
                tennhanvien = tbLuong.getModel().getValueAt(i, 4).toString();
                sogiotre = Float.parseFloat(tbLuong.getModel().getValueAt(i, 5).toString());
                sogiolamthem = Float.parseFloat(tbLuong.getModel().getValueAt(i, 6).toString());
                luongcanban = Float.parseFloat(tbLuong.getModel().getValueAt(i, 7).toString());
                thuong = Float.parseFloat(tbLuong.getModel().getValueAt(i, 8).toString());
                hesoluong = Float.parseFloat(tbLuong.getModel().getValueAt(i, 9).toString());
                phucap = Float.parseFloat(tbLuong.getModel().getValueAt(i, 10).toString());
                luongchinhthuc = Float.parseFloat(tbLuong.getModel().getValueAt(i, 11).toString());
            }
        });

//      HEADER
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("BẢNG CHẤM CÔNG THEO NGÀY", 1300, 40);
        header.add(headerSuggest);
//      
        this.add(header, BorderLayout.NORTH);
        this.add(pnDisplay, BorderLayout.CENTER);
        this.add(pnTable, BorderLayout.SOUTH);
        setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension(1300, 110));
        pnDisplay.setBackground(Color.pink);
        lbLuong1 = new JLabel("Thời gian");
        lbLuong1.setBounds(200, 20, 150, 30);
        lbLuong1.setHorizontalAlignment(JLabel.CENTER);
        lbLuong1.setFont(font);
        pnDisplay.add(lbLuong1);
        picker = new JXDatePicker();
        picker.setFormats(new SimpleDateFormat("MM/yyyy"));
        picker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outModel(model, lBUS.getList(), picker.getEditor().getText());
            }
        });

        picker.setBounds(350, 20, 200, 30);
        pnDisplay.add(picker);

        pnFind();
        pnDisplay.add(pnFind);
        pnOption();
        pnDisplay.add(pnOption);
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(new FlowLayout(1, 30, 0));
        pnOption.setBounds(0, 70, 1300, 70);
//        pnOption.setPreferredSize(new Dimension(1300, 70));
        JLabel lbXem = new JLabel(new ImageIcon("./src/img/xemBangLuong.png"));
        lbXem.setPreferredSize(new Dimension(155, 70));
        lbXem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbXem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (mabangluong == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng lương cần xem!");
                }
                xemLuongDIALOG xemluong = new xemLuongDIALOG(mabangluong, mabangchamcong, thoigian, manhanvien, tennhanvien, luongcanban, thuong, hesoluong, phucap, luongchinhthuc, sogiotre, sogiolamthem);
            }
        });

        JLabel btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setPreferredSize(new Dimension(155, 70));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                setVisible(false);
                dispose();
            }
        });
        pnOption.add(lbXem);
        pnOption.add(btnBack);

        pnOption.setBackground(Color.pink);
        return pnOption;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel();
        pnFind.setLayout(null);
        pnFind.setBounds(650, 20, 420, 30);
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã lương");
        cmbChoice.addItem("Mã bảng chấm công");
        cmbChoice.addItem("Thời gian");
        cmbChoice.addItem("Mã nhân viên");
        cmbChoice.addItem("Tên nhân viên");
        cmbChoice.setBounds(0, 0, 160, 30);

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(160, 0, 210, 30);
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
        searchIcon.setBounds(new Rectangle(370, -10, 50, 50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add tất cả vào search box
        pnFind.add(cmbChoice);
        pnFind.add(txtSearch);
        pnFind.add(searchIcon);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchIcon.setIcon(new ImageIcon("./src/img/search_24px.png")); //Đổi màu icon
                pnFind.setBorder(createLineBorder(new Color(52, 152, 219))); // Đổi màu viền 
            }

            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
                searchIcon.setIcon(new ImageIcon("./src/img/search_24px.png"));
                pnFind.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        return pnFind;

    }

    public JPanel pnTable(String ngay) {
        pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setPreferredSize(new Dimension(1300, 520));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã Lương");
        header.add("Mã bảng chấm công");
        header.add("Thời gian");
        header.add("Mã nhân viên");
        header.add("Tên nhân viên");
        header.add("Số giờ trễ");
        header.add("Số giờ làm thêm");
        header.add("Luơng căn bản");
        header.add("Thưởng");
        header.add("Hệ số lương");
        header.add("Phụ cấp");
        header.add("Lương chính thức");

        model = new DefaultTableModel(header, 4);
        tbLuong = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbLuong.setRowSorter(rowSorter);
        if (ngay.equals("")) {
            listLuong();
        } else {
            listLuong(ngay); //Đọc từ database lên table 
        }
        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbLuong.setFocusable(false);
        tbLuong.setIntercellSpacing(new Dimension(0, 0));
        tbLuong.setRowHeight(30);
        tbLuong.getTableHeader().setOpaque(false);
        tbLuong.setFillsViewportHeight(true);
        tbLuong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbLuong.getTableHeader().setForeground(Color.WHITE);
        tbLuong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbLuong);
        scroll.setBounds(new Rectangle(0, 0, 1300, 520));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<luongDTO> l, String ngay) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (luongDTO n : l) {
            if (n.getThoigian().equals(ngay)) {
                data = new Vector();
                data.add(n.getMaluong());
                data.add(n.getMabangchamcong());
                data.add(n.getThoigian());
                data.add(n.getManhanvien());
                data.add(n.getTennhanvien());
                data.add(n.getSogiotre());
                data.add(n.getSogiolamthem());
                data.add(n.getLuongcanban());
                data.add(n.getThuong());
                data.add(n.getHesoluong());
                data.add(n.getPhucap());
                data.add(n.getLuongchinhthuc());
                model.addRow(data);
            }
        }
        tbLuong.setModel(model);
    }

    public void outModel(DefaultTableModel model, ArrayList<luongDTO> l) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (luongDTO n : l) {
            data = new Vector();
            data.add(n.getMaluong());
            data.add(n.getMabangchamcong());
            data.add(n.getThoigian());
            data.add(n.getManhanvien());
            data.add(n.getTennhanvien());
            data.add(n.getSogiotre());
            data.add(n.getSogiolamthem());
            data.add(n.getLuongcanban());
            data.add(n.getThuong());
            data.add(n.getHesoluong());
            data.add(n.getPhucap());
            data.add(n.getLuongchinhthuc());
            model.addRow(data);

        }
        tbLuong.setModel(model);
    }

    public void listLuong(String ngay) // Chép ArrayList lên table
    {
        lBUS.listLuong();
        ArrayList<luongDTO> l = lBUS.getList();
        outModel(model, l, ngay);
    }

    public void listLuong() // Chép ArrayList lên table
    {
        lBUS.listLuong();
        ArrayList<luongDTO> l = lBUS.getList();
        outModel(model, l);
    }

}
