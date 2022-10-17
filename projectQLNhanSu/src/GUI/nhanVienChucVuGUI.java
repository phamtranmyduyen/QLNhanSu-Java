/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucNangBUS;
import BUS.nhanVienChucVuBUS;
import DTO.nhanVienChucVuDTO;
import GUI.model.DateValidator;
import GUI.model.DateValidatorUsingDateFormat;
import GUI.model.headerSuggest;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class nhanVienChucVuGUI extends JDialog {

    DateValidator validatorDATE = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    private nhanVienChucVuBUS ctcvBUS = new nhanVienChucVuBUS();
    private chucNangBUS cnBUS = new chucNangBUS();
    private JPanel header, pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbExit, lbSuggestChucVu;
    private JTable tbNhanVienChucVu;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtNhanVienChucVu;
    JLabel[] lbNhanVienChucVu;
    Font font;
    TableRowSorter<TableModel> rowSorter;
    String manhanvien;
    JXDatePicker[] picker;

    public nhanVienChucVuGUI(String manhanvien) {
        this.manhanvien = manhanvien;
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 18);
        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 10, 1300, 730));
        setLocationRelativeTo(null);
        pnDisplay();
        pnEast();
        pnOption();

        pnTable();
        tbNhanVienChucVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbNhanVienChucVu.getSelectedRow();
                txtNhanVienChucVu[0].setText(tbNhanVienChucVu.getModel().getValueAt(i, 0).toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                NGÀY BẮT ĐẦU
                String dateBatDauInString = tbNhanVienChucVu.getModel().getValueAt(i, 1).toString();
                Date dateBatDau = null;
                try {
                    dateBatDau = formatter.parse(dateBatDauInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[1].setDate(dateBatDau);
//                NGÀY KẾT THÚC
                String dateKetThucInString = tbNhanVienChucVu.getModel().getValueAt(i, 2).toString();
                Date dateKetThuc = null;
                try {
                    dateKetThuc = formatter.parse(dateKetThucInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[2].setDate(dateKetThuc);
            }
        });

//      HEADER
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("CHỨC VỤ CỦA NHÂN VIÊN " + manhanvien, 1300, 40);
        header.add(headerSuggest);
//  
        this.add(header, BorderLayout.NORTH);
        this.add(pnDisplay, BorderLayout.WEST);
        this.add(pnOption, BorderLayout.CENTER);
        this.add(pnEast, BorderLayout.EAST);
        this.add(pnTable, BorderLayout.SOUTH);
        setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension((1300 / 2) - 20, 300));
        pnDisplay.setBackground(Color.pink);
        String[] arrNhanVienChucVu = {"Mã chức vụ", "Ngày bắt đầu", "Ngày kết thúc"};
        txtNhanVienChucVu = new JTextField[arrNhanVienChucVu.length];
        lbNhanVienChucVu = new JLabel[arrNhanVienChucVu.length];
        picker = new JXDatePicker[arrNhanVienChucVu.length];
        int xLb = 50, yLb = 70;
        int xTxt = 290, yTxt = 70;
        for (int i = 0; i < arrNhanVienChucVu.length; i++) {
            lbNhanVienChucVu[i] = new JLabel(arrNhanVienChucVu[i]);
            lbNhanVienChucVu[i].setBounds(xLb, yLb, 220, 30);
            lbNhanVienChucVu[i].setHorizontalAlignment(JLabel.CENTER);
            lbNhanVienChucVu[i].setFont(font);
            lbNhanVienChucVu[i].setName("lb" + i);
            pnDisplay.add(lbNhanVienChucVu[i]);
            yLb = yLb + 50;
            txtNhanVienChucVu[i] = new JTextField();
            txtNhanVienChucVu[i].setName("txt" + i);
            if (i != 0) {
                picker[i] = new JXDatePicker();
                picker[i].setFormats(new SimpleDateFormat("yyyy-MM-dd"));
                picker[i].setBounds(xTxt, yTxt, 230, 30);
                pnDisplay.add(picker[i]);
                yTxt = yTxt + 50;
            } else {
                txtNhanVienChucVu[i].setBounds(xTxt, yTxt, 200, 30);
                pnDisplay.add(txtNhanVienChucVu[i]);
                yTxt = yTxt + 50;
            }

        }
        lbSuggestChucVu = new JLabel("...", JLabel.CENTER);
        lbSuggestChucVu.setBounds(490, 70, 30, 30);
        lbSuggestChucVu.setOpaque(true);
        lbSuggestChucVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestChucVu sgcv = new SuggestChucVu();
                String s = sgcv.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienChucVu[0].setText(s);
                }
            }
        });
        pnDisplay.add(lbSuggestChucVu);
        return pnDisplay;
    }

    public JPanel pnEast() {
        pnEast = new JPanel();
        pnEast.setLayout(new FlowLayout(1, 0, 40));
        pnEast.setBackground(Color.pink);
        pnEast.setPreferredSize(new Dimension(320, 200));
        lbExit = new JLabel(new ImageIcon("./src/img/Close_150px.png"), JLabel.CENTER);
        lbExit.setPreferredSize(new Dimension(150, 150));
        lbExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        pnEast.add(lbExit);
        return pnEast;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(null);
        pnOption.setBackground(Color.PINK);
        pnOption.setPreferredSize(new Dimension(320, 200));
        lbAdd = new JLabel(new ImageIcon("./src/img/add.png"), JLabel.CENTER);
        lbEdit = new JLabel(new ImageIcon("./src/img/edit.png"), JLabel.CENTER);
        lbRemove = new JLabel(new ImageIcon("./src/img/remove.png"), JLabel.CENTER);

        lbAdd.setBounds(60, 30, 200, 50);
        lbEdit.setBounds(60, 90, 200, 50);
        lbRemove.setBounds(60, 150, 200, 50);
        pnOption.add(lbAdd);
        pnOption.add(lbEdit);
        pnOption.add(lbRemove);
        lbAdd.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbEdit.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbRemove.setCursor(new Cursor((Cursor.HAND_CURSOR)));

        btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(80, 40, 155, 100);
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(80, 120, 155, 100);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnOption.add(btnConfirm);
        pnOption.add(btnBack);
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

                tbNhanVienChucVu.clearSelection();
                tbNhanVienChucVu.setEnabled(false);
            }
        });
        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    ctcvBUS.deleteNhanVienChucVu(txtNhanVienChucVu[0].getText(), manhanvien, txtNhanVienChucVu[1].getText());
                    cleanView();

                    tbNhanVienChucVu.clearSelection();
                    outModel(model, ctcvBUS.getList(manhanvien));
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtNhanVienChucVu[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cần sửa!");
                    return;
                }

                EditOrAdd = false;

                txtNhanVienChucVu[0].setEditable(false);

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbNhanVienChucVu.setEnabled(false);
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

                tbNhanVienChucVu.setEnabled(true);
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
                        String machucvu = txtNhanVienChucVu[0].getText();
                        String ngaybatdau = picker[1].getEditor().getText();
                        String ngayketthuc = picker[2].getEditor().getText();
                        if (ctcvBUS.checkMaChucVu(machucvu)) {
                            JOptionPane.showMessageDialog(null, "Mã chức vụ đã tồn tại!");
                            return;
                        }
                        //Upload lên DAO và BUS
                        nhanVienChucVuDTO ctcn = new nhanVienChucVuDTO(machucvu, manhanvien, ngaybatdau, ngayketthuc);
                        ctcvBUS.addNhanVienChucVu(ctcn);
                        outModel(model, ctcvBUS.getList(manhanvien));// Load lại table
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa chức vụ", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String machucvu = txtNhanVienChucVu[0].getText();
                        String ngaybatdau = picker[1].getEditor().getText();
                        String ngayketthuc = picker[2].getEditor().getText();
                        if (ngaybatdau == null) {
                            ngaybatdau = "0000-00-00";
                        }
                        if (ngayketthuc == null) {
                            ngayketthuc = "0000-00-00";
                        }
                        nhanVienChucVuDTO ctcn = new nhanVienChucVuDTO(machucvu, manhanvien, ngaybatdau, ngayketthuc);
                        ctcvBUS.setNhanVienChucVu(ctcn);
                        outModel(model, ctcvBUS.getList(manhanvien));
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbNhanVienChucVu.setEnabled(true);
                    }
                }

            }
        });
        return pnOption;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel(new FlowLayout());
        pnFind.setPreferredSize(new Dimension(200, 100));
//        pnFind.setBounds(new Rectangle(50, 120, 300, 30));
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã chức vụ");
        cmbChoice.addItem("Ngày bắt đầu");
        cmbChoice.addItem("Ngày kết thúc");

        cmbChoice.setPreferredSize(new Dimension(120, 30));
//        cmbChoice.setBounds(new Rectangle(70,0,120,30));

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(275, 30));
//        txtSearch.setBounds(new Rectangle(195, 0, 275, 30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
        searchIcon.setPreferredSize(new Dimension(50, 30));
        searchIcon.setBounds(new Rectangle(345, -10, 50, 50));
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
        pnTable.setPreferredSize(new Dimension(1300, 410));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã chức vụ");
        header.add("Ngày bắt đầu");
        header.add("Ngày kết thúc");

        model = new DefaultTableModel(header, 4);
        tbNhanVienChucVu = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbNhanVienChucVu.setRowSorter(rowSorter);
        listNhanVienChucVu(manhanvien); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbNhanVienChucVu.setFocusable(false);
        tbNhanVienChucVu.setIntercellSpacing(new Dimension(0, 0));
        tbNhanVienChucVu.setRowHeight(30);
        tbNhanVienChucVu.getTableHeader().setOpaque(false);
        tbNhanVienChucVu.setFillsViewportHeight(true);
        tbNhanVienChucVu.getTableHeader().setBackground(new Color(232, 57, 99));
        tbNhanVienChucVu.getTableHeader().setForeground(Color.WHITE);
        tbNhanVienChucVu.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbNhanVienChucVu);
        scroll.setBounds(new Rectangle(0, 0, 1300, 410));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtNhanVienChucVu[0].setEditable(true);
        for (int i = 0; i < txtNhanVienChucVu.length; i++) {
            if (i == 1 || i == 2) {
                picker[i].setDate(null);
            } else {
                txtNhanVienChucVu[i].setText("");
            }
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<nhanVienChucVuDTO> ctcn) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (nhanVienChucVuDTO n : ctcn) {
            data = new Vector();
            data.add(n.getMachucvu());
            data.add(n.getNgaybatdau());
            data.add(n.getNgayketthuc());
            model.addRow(data);
        }
        tbNhanVienChucVu.setModel(model);
    }

    public void listNhanVienChucVu(String manhanvien) // Chép ArrayList lên table
    {
        if (ctcvBUS.getList(manhanvien) == null) {
            ctcvBUS.listNhanVienChucVu(manhanvien);
        }
        ArrayList<nhanVienChucVuDTO> ctcn = ctcvBUS.getList(manhanvien);
        outModel(model, ctcn);
    }

}
