/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucNangBUS;
import BUS.chiTietChucNangBUS;
import DTO.chiTietChucNangDTO;
import GUI.model.headerSuggest;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class chiTietChucNangGUI extends JDialog {

    private chiTietChucNangBUS ctcnBUS = new chiTietChucNangBUS();
    private JPanel header, pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbExit;
    private JTable tbChiTietChucNang;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtChiTietChucNang;
    JLabel[] lbChiTietChucNang;
    Font font;
    TableRowSorter<TableModel> rowSorter;
    String machucnang, machitietchucnang;

    public chiTietChucNangGUI(String machucnang) {
        this.machucnang = machucnang;
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

                tbChiTietChucNang.clearSelection();
                tbChiTietChucNang.setEnabled(false);
            }
        });

        pnTable();
        tbChiTietChucNang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbChiTietChucNang.getSelectedRow();
                machitietchucnang = tbChiTietChucNang.getModel().getValueAt(i, 0).toString();
                txtChiTietChucNang[0].setText(tbChiTietChucNang.getModel().getValueAt(i, 1).toString());
            }
        });

//      HEADER
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("CHI TIẾT CHỨC NĂNG " + machucnang, 1300, 40);
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
        String[] arrChiTietChucNang = {"Tên chi tiết chức năng"};
        txtChiTietChucNang = new JTextField[arrChiTietChucNang.length];
        lbChiTietChucNang = new JLabel[arrChiTietChucNang.length];
        int xLb = 50, yLb = 80;
        int xTxt = 320, yTxt = 80;
        for (int i = 0; i < arrChiTietChucNang.length; i++) {
            lbChiTietChucNang[i] = new JLabel(arrChiTietChucNang[i]);
            lbChiTietChucNang[i].setBounds(xLb, yLb, 250, 30);
            lbChiTietChucNang[i].setHorizontalAlignment(JLabel.CENTER);
            lbChiTietChucNang[i].setFont(font);
            lbChiTietChucNang[i].setName("lb" + i);
            pnDisplay.add(lbChiTietChucNang[i]);
            yLb = yLb + 50;
            txtChiTietChucNang[i] = new JTextField();
            txtChiTietChucNang[i].setName("txt" + i);
            txtChiTietChucNang[i].setBounds(xTxt, yTxt, 230, 30);
            pnDisplay.add(txtChiTietChucNang[i]);
            yTxt = yTxt + 50;
        }
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

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    ctcnBUS.deleteChiTietChucNang(machitietchucnang);
                    cleanView();
                    tbChiTietChucNang.clearSelection();
                    outModel(model, ctcnBUS.getList(machucnang));
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtChiTietChucNang[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chi tiết chức năng cần sửa!");
                    return;
                }

                EditOrAdd = false;

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbChiTietChucNang.setEnabled(false);
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

                tbChiTietChucNang.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chi tiết chức năng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String machitietchucnangAdd = ctcnBUS.machitietchucnang();
                        String tenchitietchucnang = txtChiTietChucNang[0].getText();
                        if (validate(machitietchucnangAdd) == false) {
                            return;
                        }
                        if (ctcnBUS.checkMaChiTietChucNang(machitietchucnangAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã chi tiết chức năng đã tồn tại!");
                            return;
                        }
                        //Upload lên DAO và BUS
                        chiTietChucNangDTO ctcn = new chiTietChucNangDTO(machitietchucnangAdd, tenchitietchucnang, machucnang);
                        ctcnBUS.addChiTietChucNang(ctcn);
                        outModel(model, ctcnBUS.getList(machucnang));// Load lại table
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa chi tiết chức năng", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String tenchitietchucnang = txtChiTietChucNang[0].getText();
                        if (validate(machitietchucnang) == false) {
                            return;
                        }
                        chiTietChucNangDTO ctcn = new chiTietChucNangDTO(machitietchucnang, tenchitietchucnang, machucnang);
                        ctcnBUS.setChiTietChucNang(ctcn);
                        outModel(model, ctcnBUS.getList(machucnang));
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbChiTietChucNang.setEnabled(true);
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
        cmbChoice.addItem("Mã chi tiết chức năng");
        cmbChoice.addItem("Tên chi tiết chức năng");
        cmbChoice.addItem("Mã chức năng");

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
        header.add("Mã chi tiết chức năng");
        header.add("Tên chi tiết chức năng");
        header.add("Mã chức năng");

        model = new DefaultTableModel(header, 4);
        tbChiTietChucNang = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbChiTietChucNang.setRowSorter(rowSorter);
        listChiTietChucNang(machucnang); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbChiTietChucNang.setFocusable(false);
        tbChiTietChucNang.setIntercellSpacing(new Dimension(0, 0));
        tbChiTietChucNang.setRowHeight(30);
        tbChiTietChucNang.getTableHeader().setOpaque(false);
        tbChiTietChucNang.setFillsViewportHeight(true);
        tbChiTietChucNang.getTableHeader().setBackground(new Color(232, 57, 99));
        tbChiTietChucNang.getTableHeader().setForeground(Color.WHITE);
        tbChiTietChucNang.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbChiTietChucNang);
        scroll.setBounds(new Rectangle(0, 0, 1300, 410));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtChiTietChucNang[0].setEditable(true);
        for (int i = 0; i < txtChiTietChucNang.length; i++) {
            txtChiTietChucNang[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<chiTietChucNangDTO> ctcn) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (chiTietChucNangDTO n : ctcn) {
            data = new Vector();
            data.add(n.getMachitietchucnang());
            data.add(n.getTenchitietchucnang());
            data.add(n.getMachucnang());
            model.addRow(data);
        }
        tbChiTietChucNang.setModel(model);
    }

    public void listChiTietChucNang(String machucnang) // Chép ArrayList lên table
    {
        if (ctcnBUS.getList(machucnang) == null) {
            ctcnBUS.listChiTietChucNang(machucnang);
        }
        ArrayList<chiTietChucNangDTO> ctcn = ctcnBUS.getList(machucnang);
        outModel(model, ctcn);
    }

    public boolean validate(String machitietchucnang) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtChiTietChucNang.length; i++) {
            if (txtChiTietChucNang[i].getText().equals("")) {
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
