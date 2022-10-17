package GUI;

import BUS.chucVuBUS;
import DTO.chucVuDTO;
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

public class ChucVuGUI extends JPanel {

    private chucVuBUS cvBUS = new chucVuBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbChucVu;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtChucVu;
    JLabel[] lbChucVu;
    String machucvu;
    TableRowSorter<TableModel> rowSorter;

    public ChucVuGUI(int width) {
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

                tbChucVu.clearSelection();
                tbChucVu.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    cvBUS.deleteChucVu(machucvu);
                    cleanView();
                    tbChucVu.clearSelection();
                    outModel(model, cvBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtChucVu[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cần sửa!");
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
                tbChucVu.setEnabled(false);
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

                tbChucVu.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chức vụ", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String machucvuAdd = cvBUS.machucvu();
                        String tenchucvu = txtChucVu[0].getText();
                        if (validate(machucvuAdd) == false) {
                            return;
                        }
                        if (cvBUS.checkMaChucVu(machucvuAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã chức vụ đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        chucVuDTO cv = new chucVuDTO(machucvuAdd, tenchucvu);
                        cvBUS.addChucVu(cv);

                        outModel(model, cvBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa chức vụ", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String tenchucvu = txtChucVu[0].getText();
                        if (validate(machucvu) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        chucVuDTO cv = new chucVuDTO(machucvu, tenchucvu);
                        cvBUS.setChucVu(cv);

                        outModel(model, cvBUS.getList());
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbChucVu.setEnabled(true);
                    }

                }

            }
        });

        pnTable();
        tbChucVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbChucVu.getSelectedRow();
                machucvu = tbChucVu.getModel().getValueAt(i, 0).toString();
                txtChucVu[0].setText(tbChucVu.getModel().getValueAt(i, 1).toString());
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
        String[] arrChucVu = {"Tên chức vụ"};
        txtChucVu = new JTextField[arrChucVu.length];
        lbChucVu = new JLabel[arrChucVu.length];
        int xLb = 50, yLb = 80;
        int xTxt = 260, yTxt = 80;
        for (int i = 0; i < arrChucVu.length; i++) {
            lbChucVu[i] = new JLabel(arrChucVu[i]);
            lbChucVu[i].setBounds(xLb, yLb, 180, 30);
            lbChucVu[i].setHorizontalAlignment(JLabel.CENTER);
            lbChucVu[i].setName("lb" + i);
            pnDisplay.add(lbChucVu[i]);
            yLb = yLb + 40;
            txtChucVu[i] = new JTextField();
            txtChucVu[i].setName("txt" + i);
            txtChucVu[i].setBounds(xTxt, yTxt, 220, 30);
            pnDisplay.add(txtChucVu[i]);
            yTxt = yTxt + 40;
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
        cmbChoice.addItem("Mã chức vụ");
        cmbChoice.addItem("Tên chức vụ");
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
        header.add("Mã chức vụ");
        header.add("Tên chức vụ");
        model = new DefaultTableModel(header, 2);
        tbChucVu = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbChucVu.setRowSorter(rowSorter);
        listChucVu(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbChucVu.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbChucVu.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbChucVu.setFocusable(false);
        tbChucVu.setIntercellSpacing(new Dimension(0, 0));
        tbChucVu.setRowHeight(30);
        tbChucVu.getTableHeader().setOpaque(false);
        tbChucVu.setFillsViewportHeight(true);
        tbChucVu.getTableHeader().setBackground(new Color(232, 57, 99));
        tbChucVu.getTableHeader().setForeground(Color.WHITE);
        tbChucVu.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbChucVu);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtChucVu[0].setEditable(true);
        for (int i = 0; i < txtChucVu.length; i++) {
            txtChucVu[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<chucVuDTO> cv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (chucVuDTO n : cv) {
            data = new Vector();
            data.add(n.getMachucvu());
            data.add(n.getTenchucvu());
            model.addRow(data);
        }
        tbChucVu.setModel(model);
    }

    public void listChucVu() // Chép ArrayList lên table
    {
        if (cvBUS.getList() == null) {
            cvBUS.listChucVu();
        }
        ArrayList<chucVuDTO> cv = cvBUS.getList();
//        model.setRowCount(0);
        outModel(model, cv);
    }

    public boolean validate(String machucvu) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtChucVu.length; i++) {
            if (txtChucVu[i].getText().equals("")) {
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
