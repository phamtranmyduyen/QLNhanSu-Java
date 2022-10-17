/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucNangBUS;
import DTO.chucNangDTO;
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
public class ChucNangGUI extends JPanel {

    private chucNangBUS cnBUS = new chucNangBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbXemCTCN;
    private JTable tbChucNang;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtChucNang;
    JLabel[] lbChucNang;
    String machucnang;
    TableRowSorter<TableModel> rowSorter;

    public ChucNangGUI(int width) {
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {
        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 10, this.DEFALUT_WIDTH - 200, 680));

        pnDisplay();
        pnOption();
        // MouseClick btnADD
        lbAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditOrAdd = true;

                cleanView();

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);
                lbXemCTCN.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbChucNang.clearSelection();
                tbChucNang.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    cnBUS.deleteChucNang(machucnang);
                    cleanView();
                    tbChucNang.clearSelection();
                    outModel(model, cnBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtChucNang[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức năng cần sửa!");
                    return;
                }

                EditOrAdd = false;
                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);
                lbXemCTCN.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);

//                tbl.clearSelection();
                tbChucNang.setEnabled(false);
            }
        });
        //MouseClick lbXem
        lbXemCTCN.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (txtChucNang[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức năng cần xem!");
                    return;
                }
                chiTietChucNangGUI ctcn = new chiTietChucNangGUI(machucnang);
            }
        });
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanView();

                lbAdd.setVisible(true);
                lbEdit.setVisible(true);
                lbRemove.setVisible(true);
                lbXemCTCN.setVisible(true);

                btnConfirm.setVisible(false);
                btnBack.setVisible(false);

                tbChucNang.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chức năng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String machucnangAdd = cnBUS.machucnang();
                        String tenchucnang = txtChucNang[0].getText();
                        if (validate(machucnangAdd) == false) {
                            return;
                        }

                        //Upload sản phẩm lên DAO và BUS
                        chucNangDTO cn = new chucNangDTO(machucnangAdd, tenchucnang);
                        cnBUS.addChucNang(cn);

                        outModel(model, cnBUS.getList());// Load lại table

                        cleanView();
                    }
                } else // Edit
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa chức năng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField

                        String tenchucnang = txtChucNang[0].getText();
                        if (validate(machucnang) == false) {
                            return;
                        }
                        //Upload lên DAO và BUS
                        chucNangDTO cn = new chucNangDTO(machucnang, tenchucnang);
                        cnBUS.setChucNang(cn);

                        outModel(model, cnBUS.getList());// Load lại table

                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbChucNang.setEnabled(true);
                    }

                }

            }
        });

        pnTable();
        tbChucNang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbChucNang.getSelectedRow();
                machucnang = tbChucNang.getModel().getValueAt(i, 0).toString();
                txtChucNang[0].setText(tbChucNang.getModel().getValueAt(i, 1).toString());
            }
        });

//TEST
//
        this.add(pnDisplay, BorderLayout.WEST);
        this.add(pnOption, BorderLayout.CENTER);
        this.add(pnTable, BorderLayout.SOUTH);
//        this.setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension(((this.DEFALUT_WIDTH - 200) / 2) - 20, 240));
        pnDisplay.setBackground(Color.pink);
        String[] arrChucNang = {"Tên chức năng"};
        txtChucNang = new JTextField[arrChucNang.length];
        lbChucNang = new JLabel[arrChucNang.length];
        int xLb = 50, yLb = 80;
        int xTxt = 260, yTxt = 80;
        for (int i = 0; i < arrChucNang.length; i++) {
            lbChucNang[i] = new JLabel(arrChucNang[i]);
            lbChucNang[i].setBounds(xLb, yLb, 180, 30);
            lbChucNang[i].setHorizontalAlignment(JLabel.CENTER);
            lbChucNang[i].setName("lb" + i);
            pnDisplay.add(lbChucNang[i]);
            yLb = yLb + 50;
            txtChucNang[i] = new JTextField();
            txtChucNang[i].setName("txt" + i);
            txtChucNang[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtChucNang[i]);
            yTxt = yTxt + 50;
        }
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(null);
        pnOption.setBackground(Color.PINK);
        pnOption.setPreferredSize(new Dimension(530, 220));
        lbAdd = new JLabel(new ImageIcon("./src/img/add.png"), JLabel.CENTER);
        lbEdit = new JLabel(new ImageIcon("./src/img/edit.png"), JLabel.CENTER);
        lbRemove = new JLabel(new ImageIcon("./src/img/remove.png"), JLabel.CENTER);
        lbXemCTCN = new JLabel(new ImageIcon("./src/img/xemCN.png"), JLabel.CENTER);

        lbAdd.setBounds(40, 30, 200, 50);
        lbEdit.setBounds(40, 100, 200, 50);
        lbRemove.setBounds(270, 30, 200, 50);
        lbXemCTCN.setBounds(290, 100, 155, 50);
        pnOption.add(lbAdd);
        pnOption.add(lbEdit);
        pnOption.add(lbRemove);
        pnOption.add(lbXemCTCN);
        lbAdd.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbEdit.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbRemove.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbXemCTCN.setCursor(new Cursor((Cursor.HAND_CURSOR)));

        btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(80, 50, 155, 100);
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(280, 50, 155, 100);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnOption.add(btnConfirm);
        pnOption.add(btnBack);
        pnFind();
        pnOption.add(pnFind);
        return pnOption;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel();
        pnFind.setLayout(null);
        pnFind.setBounds(0, 190, 530, 40);
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã chức năng");
        cmbChoice.addItem("Tên chức năng");
        cmbChoice.setBounds(0, 0, 140, 40);

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(120, 0, 360, 40);
//        txtSearch.setPreferredSize(new Dimension(230, 30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
        searchIcon.setBounds(new Rectangle(480, 0, 50, 40));
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
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        return pnFind;

    }

    public JPanel pnTable() {
        pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setPreferredSize(new Dimension(1090, 440));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã chức năng");
        header.add("Tên chức năng");

        model = new DefaultTableModel(header, 2);
        tbChucNang = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbChucNang.setRowSorter(rowSorter);
        listChucNang(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbChucNang.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbChucNang.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbChucNang.setFocusable(false);
        tbChucNang.setIntercellSpacing(new Dimension(0, 0));
        tbChucNang.setRowHeight(30);
        tbChucNang.getTableHeader().setOpaque(false);
        tbChucNang.setFillsViewportHeight(true);
        tbChucNang.getTableHeader().setBackground(new Color(232, 57, 99));
        tbChucNang.getTableHeader().setForeground(Color.WHITE);
        tbChucNang.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbChucNang);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtChucNang[0].setEditable(true);
        for (int i = 0; i < txtChucNang.length; i++) {
            txtChucNang[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<chucNangDTO> cn) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (chucNangDTO n : cn) {
            data = new Vector();
            data.add(n.getMachucnang());
            data.add(n.getTenchucnang());
            model.addRow(data);
        }
        tbChucNang.setModel(model);
    }

    public void listChucNang() // Chép ArrayList lên table
    {
        if (cnBUS.getList() == null) {
            cnBUS.listChucNang();
        }
        ArrayList<chucNangDTO> cn = cnBUS.getList();
        model.setRowCount(0);
        outModel(model, cn);
    }

    public boolean validate(String machucnang) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtChucNang.length; i++) {
            if (txtChucNang[i].getText().equals("")) {
                validateFull = false;
            }
        }
        if (validateFull == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }
        return validate;
    }
}
