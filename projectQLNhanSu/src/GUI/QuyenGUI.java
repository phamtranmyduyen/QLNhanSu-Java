/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.quyenBUS;
import DTO.chucNangDTO;
import DTO.quyenDTO;
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
public class QuyenGUI extends JPanel {

    private quyenBUS qBUS = new quyenBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbQuyen;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    private boolean addChucNang = false;
    JTextField[] txtQuyen;
    JLabel[] lbQuyen;
    JLabel lbQuyenChucnang, listChucnang;
    ArrayList<String> listChucNang;
    TableRowSorter<TableModel> rowSorter;

    public QuyenGUI(int width) {
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

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbQuyen.clearSelection();
                tbQuyen.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    qBUS.deleteQuyen(txtQuyen[0].getText());
                    cleanView();
                    tbQuyen.clearSelection();
                    outModel(model, qBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtQuyen[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền cần sửa!");
                    return;
                }

                EditOrAdd = false;

                txtQuyen[0].setEditable(false);

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);

//                tbl.clearSelection();
                tbQuyen.setEnabled(false);
            }
        });

//        MouseClick lbAddChucNang 
        listChucnang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                THÊM GET CHỨC NĂNG
                if (txtQuyen[0].getText().equals("") && EditOrAdd) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền!");
                    return;
                }
                qBUS.listChucNang(txtQuyen[0].getText());
                ArrayList<String> cn = qBUS.getChucNang();
                SuggestChucNang scn = new SuggestChucNang(txtQuyen[0].getText());
                scn.selectedChucNang(cn);

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

                tbQuyen.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm quyền", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maquyen = txtQuyen[0].getText();
                        String tenquyen = txtQuyen[1].getText();
                        if (validate(maquyen) == false) {
                            return;
                        }
                        if (qBUS.checkMaQuyen(maquyen)) {
                            JOptionPane.showMessageDialog(null, "Mã quyền đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        quyenDTO q = new quyenDTO(maquyen, tenquyen);
                        qBUS.addQuyen(q);

                        outModel(model, qBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        cleanView();
                    }
                } else if (!EditOrAdd) // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa quyền", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maquyen = txtQuyen[0].getText();
                        String tenquyen = txtQuyen[1].getText();
                        if (validate(maquyen) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        quyenDTO q = new quyenDTO(maquyen, tenquyen);
                        qBUS.setQuyen(q);

                        outModel(model, qBUS.getList());// Load lại table

                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbQuyen.setEnabled(true);
                    }
                }

            }
        });

        pnTable();
        tbQuyen.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbQuyen.getSelectedRow();
                txtQuyen[0].setText(tbQuyen.getModel().getValueAt(i, 0).toString());
                txtQuyen[1].setText(tbQuyen.getModel().getValueAt(i, 1).toString());
            }
        });

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
        String[] arrQuyen = {"Mã quyền", "Tên quyền"};
        txtQuyen = new JTextField[arrQuyen.length];
        lbQuyen = new JLabel[arrQuyen.length];
        int xLb = 50, yLb = 70;
        int xTxt = 260, yTxt = 70;
        for (int i = 0; i < arrQuyen.length; i++) {
            lbQuyen[i] = new JLabel(arrQuyen[i]);
            lbQuyen[i].setBounds(xLb, yLb, 180, 30);
            lbQuyen[i].setHorizontalAlignment(JLabel.CENTER);

            lbQuyen[i].setName("lb" + i);
            pnDisplay.add(lbQuyen[i]);
            yLb = yLb + 40;
            txtQuyen[i] = new JTextField();
            txtQuyen[i].setName("txt" + i);
            txtQuyen[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtQuyen[i]);
            yTxt = yTxt + 40;
        }
//        LB THÊM CHỨC NĂNG
        lbQuyenChucnang = new JLabel("Chọn chức năng");
        lbQuyenChucnang.setBounds(50, 150, 180, 30);
        lbQuyenChucnang.setHorizontalAlignment(JLabel.CENTER);
        lbQuyenChucnang.setName("lbQuyenChucnang");
        pnDisplay.add(lbQuyenChucnang);
//        LB CHỌN CHỨC NĂNG
        listChucnang = new JLabel(new ImageIcon("./src/img/rules_30px.png"), JLabel.CENTER);
        listChucnang.setBounds(260, 150, 30, 30);
        listChucnang.setHorizontalAlignment(JLabel.CENTER);
        listChucnang.setOpaque(true);
        listChucnang.setName("listChucnang");
        listChucnang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnDisplay.add(listChucnang);

//        pnDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
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
        cmbChoice.addItem("Mã quyền");
        cmbChoice.addItem("Tên quyền");
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
        // Chỉnh width các cột 
        tbQuyen.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbQuyen.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbQuyen.setFocusable(false);
        tbQuyen.setIntercellSpacing(new Dimension(0, 0));
        tbQuyen.setRowHeight(30);
        tbQuyen.getTableHeader().setOpaque(false);
        tbQuyen.setFillsViewportHeight(true);
        tbQuyen.getTableHeader().setBackground(new Color(232, 57, 99));
        tbQuyen.getTableHeader().setForeground(Color.WHITE);
        tbQuyen.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbQuyen);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtQuyen[0].setEditable(true);
        for (int i = 0; i < txtQuyen.length; i++) {
            txtQuyen[i].setText("");
        }

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
//        model.setRowCount(0);
        outModel(model, q);
    }

    public boolean validate(String maquyen) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtQuyen.length; i++) {
            if (txtQuyen[i].getText().equals("")) {
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
