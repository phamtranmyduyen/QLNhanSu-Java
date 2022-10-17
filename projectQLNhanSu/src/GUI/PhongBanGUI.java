/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.phongBanBUS;
import DTO.phongBanDTO;
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
 * @author funty
 */
public class PhongBanGUI extends JPanel {

    private phongBanBUS pbBUS = new phongBanBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbPhongBan;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtPhongBan;
    JLabel[] lbPhongBan;
    JLabel lbSuggestmanv, lbmanv;
    JTextField txtmanv;
    TableRowSorter<TableModel> rowSorter;
    String maphong;
    public PhongBanGUI(int width) {
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {
        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 0, this.DEFALUT_WIDTH - 200, 680));

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

                tbPhongBan.clearSelection();
                tbPhongBan.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    pbBUS.deletePhongBan(maphong);
                    cleanView();
                    tbPhongBan.clearSelection();
                    outModel(model, pbBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtPhongBan[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng ban cần sửa!");
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
                tbPhongBan.setEnabled(false);
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

                tbPhongBan.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm phòng ban", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maphongAdd = pbBUS.maphongban();
                        String tenphong = txtPhongBan[0].getText();
                        if (validate(maphongAdd) == false) {
                            return;
                        }
                        if (pbBUS.checkMaPhongBan(maphongAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã phòng ban đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        phongBanDTO pb = new phongBanDTO(maphongAdd, tenphong);
                        pbBUS.addPhongBan(pb);

                        outModel(model, pbBUS.getList());
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa phòng ban", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String tenphong = txtPhongBan[0].getText();
                        if (validate(maphong) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        phongBanDTO pb = new phongBanDTO(maphong, tenphong);
                        pbBUS.setPhongBan(pb);

                        outModel(model, pbBUS.getList());
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbPhongBan.setEnabled(true);
                    }
                }

            }
        });

        pnTable();
        tbPhongBan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbPhongBan.getSelectedRow();
                maphong = tbPhongBan.getModel().getValueAt(i, 0).toString();
                txtPhongBan[0].setText(tbPhongBan.getModel().getValueAt(i, 1).toString());
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
        String[] arrTaiKhoan = {"Tên phòng"};
        txtPhongBan = new JTextField[arrTaiKhoan.length];
        lbPhongBan = new JLabel[arrTaiKhoan.length];
        int xLb = 50, yLb = 80;
        int xTxt = 260, yTxt = 80;
        for (int i = 0; i < arrTaiKhoan.length; i++) {
            lbPhongBan[i] = new JLabel(arrTaiKhoan[i]);
            lbPhongBan[i].setBounds(xLb, yLb, 180, 30);
            lbPhongBan[i].setHorizontalAlignment(JLabel.CENTER);
            lbPhongBan[i].setName("lb" + i);
            pnDisplay.add(lbPhongBan[i]);
            yLb = yLb + 40;
            txtPhongBan[i] = new JTextField();
            txtPhongBan[i].setName("txt" + i);
            txtPhongBan[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtPhongBan[i]);
            yTxt = yTxt + 40;
        };

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
        cmbChoice.addItem("Mã phòng");
        cmbChoice.addItem("Tên phòng");
        cmbChoice.setBounds(0, 0, 120, 40);

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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        header.add("Mã phòng  ");
        header.add("Tên phòng ");
        model = new DefaultTableModel(header, 3);
        tbPhongBan = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbPhongBan.setRowSorter(rowSorter);
        listPhongBan(); //Đọc từ database lên table 

        // Custom table
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
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtPhongBan[0].setEditable(true);
        for (int i = 0; i < txtPhongBan.length; i++) {
            txtPhongBan[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<phongBanDTO> pb) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (phongBanDTO n : pb) {
            data = new Vector();
            data.add(n.getMaphong());
            data.add(n.getTenphong());
            model.addRow(data);
        }
        tbPhongBan.setModel(model);
    }

    public void listPhongBan() // Chép ArrayList lên table
    {
        if (pbBUS.getList() == null) {
            pbBUS.listPhongBan();
        }
        ArrayList<phongBanDTO> pb = pbBUS.getList();
        outModel(model, pb);
    }

    public boolean validate(String maphong) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtPhongBan.length; i++) {
            if (txtPhongBan[i].getText().equals("")) {
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
