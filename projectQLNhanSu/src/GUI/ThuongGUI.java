/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.thuongBUS;
import DTO.thuongDTO;
import GUI.model.checkError;
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
public class ThuongGUI extends JPanel {

    checkError check = new checkError();
    private thuongBUS tBUS = new thuongBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbthuong;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtthuong;
    JLabel[] lbthuong;
    String mathuong;
    TableRowSorter<TableModel> rowSorter;

    public ThuongGUI(int width) {
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setBackground(null);
        setBounds(new Rectangle(10, 0, this.DEFALUT_WIDTH - 200, 1000));

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

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbthuong.clearSelection();
                tbthuong.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    tBUS.deleteThuong(mathuong);
                    cleanView();
                    tbthuong.clearSelection();
                    outModel(model, tBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtthuong[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn thưởng cần sửa!");
                    return;
                }

                EditOrAdd = false;

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                tbthuong.setEnabled(false);
            }
        });
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanView();

                lbAdd.setVisible(true);
                lbEdit.setVisible(true);
                lbRemove.setVisible(true);

                btnConfirm.setVisible(false);
                btnBack.setVisible(false);

                tbthuong.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm thưởng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String mathuongAdd = tBUS.mathuong();
                        String loaithuong = txtthuong[0].getText();
                        if (validate(txtthuong[1].getText()) == false) {
                            return;
                        }
                        float tienthuong = Float.parseFloat(txtthuong[1].getText());
                        
                        //Upload sản phẩm lên DAO và BUS
                        thuongDTO t = new thuongDTO(mathuongAdd, loaithuong, tienthuong);
                        tBUS.addThuong(t);

                        outModel(model, tBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa tiền thưởng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String loaithuong = txtthuong[0].getText();
                        if (validate(txtthuong[1].getText()) == false) {
                            return;
                        }
                        float tienthuong = Float.parseFloat(txtthuong[1].getText());

                        thuongDTO t = new thuongDTO(mathuong, loaithuong, tienthuong);
                        tBUS.setThuong(t);

                        outModel(model, tBUS.getList());// Load lại table
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbthuong.setEnabled(true);
                    }
                }

            }
        });

        pnFind();
        pnEast = new JPanel();
        pnEast.setLayout(new BorderLayout(10, 10));
        pnEast.setPreferredSize(new Dimension(((this.DEFALUT_WIDTH - 200) / 2) - 10, 1000 / 3));
        pnEast.add(pnOption, BorderLayout.NORTH);
        pnEast.add(pnFind, BorderLayout.SOUTH);
        pnTable();
        tbthuong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbthuong.getSelectedRow();
                mathuong = tbthuong.getModel().getValueAt(i, 0).toString();
                txtthuong[1].setText(tbthuong.getModel().getValueAt(i, 1).toString());
                txtthuong[2].setText(tbthuong.getModel().getValueAt(i, 2).toString());
            }
        });

//TEST
//
        this.add(pnDisplay, BorderLayout.WEST);
        this.add(pnEast, BorderLayout.EAST);
        this.add(pnTable, BorderLayout.SOUTH);
//        this.setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension(((this.DEFALUT_WIDTH - 200) / 2) - 20, 1000 / 3));
        pnDisplay.setBackground(Color.pink);
        String[] arrthuong = {"Loại thưởng", "Tiền thưởng"};
        txtthuong = new JTextField[arrthuong.length];
        lbthuong = new JLabel[arrthuong.length];
        int xLb = 30, yLb = 50;
        int xTxt = 230, yTxt = 50;
        for (int i = 0; i < arrthuong.length; i++) {
            lbthuong[i] = new JLabel(arrthuong[i]);
            lbthuong[i].setBounds(xLb, yLb, 180, 30);
            lbthuong[i].setHorizontalAlignment(JLabel.CENTER);
            lbthuong[i].setOpaque(false);
            lbthuong[i].setName("lb" + i);
            pnDisplay.add(lbthuong[i]);
            yLb = yLb + 50;
            txtthuong[i] = new JTextField();
            txtthuong[i].setName("txt" + i);
            txtthuong[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtthuong[i]);
            yTxt = yTxt + 50;
        }
        pnDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(null);
        pnOption.setBackground(Color.PINK);
        pnOption.setPreferredSize(new Dimension(400, 200));
        lbAdd = new JLabel(new ImageIcon("./src/img/add.png"), JLabel.CENTER);
        lbEdit = new JLabel(new ImageIcon("./src/img/edit.png"), JLabel.CENTER);
        lbRemove = new JLabel(new ImageIcon("./src/img/remove.png"), JLabel.CENTER);

        lbAdd.setBounds(155, 10, 200, 50);
        lbEdit.setBounds(155, 65, 200, 50);
        lbRemove.setBounds(155, 120, 200, 50);
        pnOption.add(lbAdd);
        pnOption.add(lbEdit);
        pnOption.add(lbRemove);
        lbAdd.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbEdit.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbRemove.setCursor(new Cursor((Cursor.HAND_CURSOR)));

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

        return pnOption;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel(null);
        pnFind.setPreferredSize(new Dimension(500, 40));
//        pnFind.setBounds(new Rectangle(50, 120, 300, 30));
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã thưởng");
        cmbChoice.addItem("Loại thưởng");
        cmbChoice.addItem("Tên thưởng");

//        cmbChoice.setPreferredSize(new Dimension(120, 30));
        cmbChoice.setBounds(new Rectangle(0, 0, 120, 40));

        //Phần TextField
        JTextField txtSearch = new JTextField();
//        txtSearch.setPreferredSize(new Dimension(275, 30));
        txtSearch.setBounds(new Rectangle(120, 0, 340, 30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
//        searchIcon.setPreferredSize(new Dimension(50, 30));
        searchIcon.setBounds(new Rectangle(460, 0, 40, 40));
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

    public JPanel pnTable() {
        pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setPreferredSize(new Dimension(1090, 750));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã thưởng");
        header.add("Loại thưởng");
        header.add("Tiền thưởng");
        model = new DefaultTableModel(header, 3);
        tbthuong = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbthuong.setRowSorter(rowSorter);
        listthuong(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbthuong.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbthuong.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbthuong.getColumnModel().getColumn(2).setPreferredWidth(40);

        // Custom table
        tbthuong.setFocusable(false);
        tbthuong.setIntercellSpacing(new Dimension(0, 0));
        tbthuong.setRowHeight(30);
        tbthuong.getTableHeader().setOpaque(false);
        tbthuong.setFillsViewportHeight(true);
        tbthuong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbthuong.getTableHeader().setForeground(Color.WHITE);
        tbthuong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbthuong);
        scroll.setBounds(new Rectangle(0, 10, this.DEFALUT_WIDTH - 230, 470));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtthuong[0].setEditable(true);
        for (int i = 0; i < txtthuong.length; i++) {
            txtthuong[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<thuongDTO> thuong) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (thuongDTO t : thuong) {
            data = new Vector();
            data.add(t.getMathuong());
            data.add(t.getLoaithuong());
            data.add(t.getTienthuong());

            model.addRow(data);
        }
        tbthuong.setModel(model);
    }

    public void listthuong() // Chép ArrayList lên table
    {
        if (tBUS.getList() == null) {
            tBUS.listThuong();
        }
        ArrayList<thuongDTO> t = tBUS.getList();
//        model.setRowCount(0);
        outModel(model, t);
    }

    public boolean validate(String tienthuong) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtthuong.length; i++) {
            if (txtthuong[i].getText().equals("")) {
                validateFull = false;
            }
        }
        if (validateFull == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }
        if (check.check_Number(tienthuong)) {
            JOptionPane.showMessageDialog(null, "Tiền thưởng phải là số!");
            validate = false;
            return false;
        }
        return validate;
    }

}
