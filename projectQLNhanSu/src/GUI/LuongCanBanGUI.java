/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.luongCanBanBUS;
import DTO.luongCanBanDTO;
import GUI.model.checkError;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author trinh
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class LuongCanBanGUI extends JPanel {

    checkError check = new checkError();
    private luongCanBanBUS lcbBUS = new luongCanBanBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbLuongCanBan;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtLuongCanBan;
    JLabel[] lbLuongCanBan;
    String maluongcanban;
    TableRowSorter<TableModel> rowSorter;

    public LuongCanBanGUI(int width) {
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

                tbLuongCanBan.clearSelection();
                tbLuongCanBan.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    lcbBUS.deleteLuongCanBan(maluongcanban);
                    cleanView();
                    tbLuongCanBan.clearSelection();
                    outModel(model, lcbBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtLuongCanBan[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lương căn bản cần sửa!");
                    return;
                }

                EditOrAdd = false;

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);

//                tbl.clearSelection();
                tbLuongCanBan.setEnabled(false);
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
//                btnFile.setVisible(false);

                tbLuongCanBan.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm lương căn bản", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maluongcanbanAdd = lcbBUS.maluongcanban();
                        if (validate(txtLuongCanBan[0].getText()) == false) {
                            return;
                        }
                        float luongcanban = Float.parseFloat(txtLuongCanBan[0].getText());

                        //Upload sản phẩm lên DAO và BUS
                        luongCanBanDTO lcb = new luongCanBanDTO(maluongcanbanAdd, luongcanban);
                        lcbBUS.addLuongCanBan(lcb);

                        outModel(model, lcbBUS.getList());
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa lương căn bản", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        if (validate(txtLuongCanBan[0].getText()) == false) {
                            return;
                        }
                        float luongcanban = Float.parseFloat(txtLuongCanBan[0].getText());
                        luongCanBanDTO lcb = new luongCanBanDTO(maluongcanban, luongcanban);
                        lcbBUS.setLuongCanBan(lcb);

                        outModel(model, lcbBUS.getList());
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbLuongCanBan.setEnabled(true);
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
        tbLuongCanBan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbLuongCanBan.getSelectedRow();
                maluongcanban = tbLuongCanBan.getModel().getValueAt(i, 0).toString();
                txtLuongCanBan[0].setText(tbLuongCanBan.getModel().getValueAt(i, 1).toString());
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
        String[] arrChucNang = {"Lương căn bản"};
        txtLuongCanBan = new JTextField[arrChucNang.length];
        lbLuongCanBan = new JLabel[arrChucNang.length];
        int xLb = 30, yLb = 80;
        int xTxt = 230, yTxt = 80;
        for (int i = 0; i < arrChucNang.length; i++) {
            lbLuongCanBan[i] = new JLabel(arrChucNang[i]);
            lbLuongCanBan[i].setBounds(xLb, yLb, 180, 30);
            lbLuongCanBan[i].setHorizontalAlignment(JLabel.CENTER);
            lbLuongCanBan[i].setOpaque(false);
            lbLuongCanBan[i].setName("lb" + i);
            pnDisplay.add(lbLuongCanBan[i]);
            yLb = yLb + 50;
            txtLuongCanBan[i] = new JTextField();
            txtLuongCanBan[i].setName("txt" + i);
            txtLuongCanBan[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtLuongCanBan[i]);
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
        cmbChoice.addItem("Mã lương căn bản");
        cmbChoice.addItem("Lương căn bản");
//        cmbChoice.setPreferredSize(new Dimension(120, 30));
        cmbChoice.setBounds(new Rectangle(0, 0, 150, 40));

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(150, 0, 310, 40));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
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
        header.add("Mã lương căn bản");
        header.add("Lương căn bản");
        model = new DefaultTableModel(header, 2);
        tbLuongCanBan = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbLuongCanBan.setRowSorter(rowSorter);
        listLuongCanBan(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbLuongCanBan.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbLuongCanBan.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbLuongCanBan.setFocusable(false);
        tbLuongCanBan.setIntercellSpacing(new Dimension(0, 0));
        tbLuongCanBan.setRowHeight(30);
        tbLuongCanBan.getTableHeader().setOpaque(false);
        tbLuongCanBan.setFillsViewportHeight(true);
        tbLuongCanBan.getTableHeader().setBackground(new Color(232, 57, 99));
        tbLuongCanBan.getTableHeader().setForeground(Color.WHITE);
        tbLuongCanBan.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbLuongCanBan);
        scroll.setBounds(new Rectangle(0, 10, this.DEFALUT_WIDTH - 230, 470));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtLuongCanBan[0].setEditable(true);
        for (int i = 0; i < txtLuongCanBan.length; i++) {
            txtLuongCanBan[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<luongCanBanDTO> cn) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (luongCanBanDTO n : cn) {
            data = new Vector();
            data.add(n.getMaluongcanban());
            data.add(n.getLuongcanban());
            model.addRow(data);
        }
        tbLuongCanBan.setModel(model);
    }

    public void listLuongCanBan() // Chép ArrayList lên table
    {
        if (lcbBUS.getList() == null) {
            lcbBUS.listLuongCanBan();
        }
        ArrayList<luongCanBanDTO> cn = lcbBUS.getList();
//        model.setRowCount(0);
        outModel(model, cn);
    }

    public boolean validate(String luongcanban) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtLuongCanBan.length; i++) {
            if (txtLuongCanBan[i].getText().equals("")) {
                validateFull = false;
            }
        }
        if (validateFull == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }
        if (check.check_Number(luongcanban)) {
            JOptionPane.showMessageDialog(null, "Lương căn bản phải là số!");
            validate = false;
            return false;
        }
        return validate;
    }

}
