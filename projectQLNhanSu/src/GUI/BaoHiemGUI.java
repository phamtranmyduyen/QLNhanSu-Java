package GUI;

import BUS.baoHiemBUS;
import DTO.baoHiemDTO;
import GUI.model.DateValidator;
import GUI.model.DateValidatorUsingDateFormat;
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

public class BaoHiemGUI extends JPanel {

    DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    private baoHiemBUS bhBUS = new baoHiemBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbBaoHiem;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtBaoHiem;
    JLabel[] lbBaoHiem;
    TableRowSorter<TableModel> rowSorter;
    String mabaohiem;

    public BaoHiemGUI(int width) {
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

                tbBaoHiem.clearSelection();
                tbBaoHiem.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    bhBUS.deleteBaoHiem(mabaohiem);
                    cleanView();
                    tbBaoHiem.clearSelection();
                    outModel(model, bhBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtBaoHiem[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảo hiểm cần sửa!");
                    return;
                }

                EditOrAdd = false;

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                tbBaoHiem.setEnabled(false);
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
                tbBaoHiem.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm bảo hiểm", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String mabaohiemAdd = bhBUS.mabaohiem();
                        System.out.println(mabaohiemAdd);
                        String tenbaohiem = txtBaoHiem[0].getText();
                        if (validate(mabaohiemAdd) == false) {
                            return;
                        }
                        if (bhBUS.checkMaBaoHiem(mabaohiemAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã bảo hiểm đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        baoHiemDTO bh = new baoHiemDTO(mabaohiemAdd, tenbaohiem);
                        bhBUS.addBaoHiem(bh);

                        outModel(model, bhBUS.getList());
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa bảo hiểm", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String tenbaohiem = txtBaoHiem[0].getText();
                        if (validate(mabaohiem) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        baoHiemDTO bh = new baoHiemDTO(mabaohiem, tenbaohiem);
                        bhBUS.setBaoHiem(bh);

                        outModel(model, bhBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbBaoHiem.setEnabled(true);
                    }
                }

            }
        });

        pnTable();
        tbBaoHiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbBaoHiem.getSelectedRow();
                mabaohiem = tbBaoHiem.getModel().getValueAt(i, 0).toString();
                txtBaoHiem[0].setText(tbBaoHiem.getModel().getValueAt(i, 1).toString());
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
        String[] arrBaoHiem = {"Tên bảo hiểm"};
        txtBaoHiem = new JTextField[arrBaoHiem.length];
        lbBaoHiem = new JLabel[arrBaoHiem.length];
        int xLb = 50, yLb = 80;
        int xTxt = 260, yTxt = 80;
        for (int i = 0; i < arrBaoHiem.length; i++) {
            lbBaoHiem[i] = new JLabel(arrBaoHiem[i]);
            lbBaoHiem[i].setBounds(xLb, yLb, 180, 30);
            lbBaoHiem[i].setHorizontalAlignment(JLabel.CENTER);
            lbBaoHiem[i].setName("lb" + i);
            pnDisplay.add(lbBaoHiem[i]);
            yLb = yLb + 45;
            txtBaoHiem[i] = new JTextField();
            txtBaoHiem[i].setName("txt" + i);
            txtBaoHiem[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtBaoHiem[i]);
            yTxt = yTxt + 45;
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
        cmbChoice.addItem("Mã bảo hiểm");
        cmbChoice.addItem("Tên bảo hiểm");
        cmbChoice.addItem("Ngày bắt đầu");
        cmbChoice.addItem("Ngày hết hạn");
        cmbChoice.addItem("Nơi cấp");
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
        header.add("Mã bảo hiểm");
        header.add("Tên bảo hiểm");
        model = new DefaultTableModel(header, 2);
        tbBaoHiem = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbBaoHiem.setRowSorter(rowSorter);
        listBaoHiem(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbBaoHiem.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbBaoHiem.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbBaoHiem.setFocusable(false);
        tbBaoHiem.setIntercellSpacing(new Dimension(0, 0));
        tbBaoHiem.setRowHeight(30);
        tbBaoHiem.getTableHeader().setOpaque(false);
        tbBaoHiem.setFillsViewportHeight(true);
        tbBaoHiem.getTableHeader().setBackground(new Color(232, 57, 99));
        tbBaoHiem.getTableHeader().setForeground(Color.WHITE);
        tbBaoHiem.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbBaoHiem);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtBaoHiem[0].setEditable(true);
        for (int i = 0; i < txtBaoHiem.length; i++) {
            txtBaoHiem[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<baoHiemDTO> bh) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (baoHiemDTO n : bh) {
            data = new Vector();
            data.add(n.getMabaohiem());
            data.add(n.getTenbaohiem());
            model.addRow(data);
        }
        tbBaoHiem.setModel(model);
    }

    public void listBaoHiem() // Chép ArrayList lên table
    {
        if (bhBUS.getList() == null) {
            bhBUS.listBaoHiem();
        }
        ArrayList<baoHiemDTO> bh = bhBUS.getList();
//        model.setRowCount(0);
        outModel(model, bh);
    }

    public boolean validate(String mabaohiem) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtBaoHiem.length; i++) {
            if (txtBaoHiem[i].getText().equals("")) {
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
