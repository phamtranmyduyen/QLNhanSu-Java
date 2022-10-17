/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucNangBUS;
import BUS.nhanVienBaoHiemBUS;
import DTO.nhanVienBaoHiemDTO;
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
public class nhanVienBaoHiemGUI extends JDialog {

    DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    private nhanVienBaoHiemBUS nvbhBUS = new nhanVienBaoHiemBUS();
    private chucNangBUS cnBUS = new chucNangBUS();
    private JPanel header, pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbExit, lbSuggestBaoHiem;
    private JTable tbNhanVienBaoHiem;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtNhanVienBaoHiem;
    JLabel[] lbNhanVienBaoHiem;
    Font font;
    TableRowSorter<TableModel> rowSorter;
    String manhanvien;
    JXDatePicker[] picker;

    public nhanVienBaoHiemGUI(String manhanvien) {
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
        // MouseClick btnADD
        lbAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditOrAdd = true;

                cleanView();
                txtNhanVienBaoHiem[1].setText("yyyy-MM-dd");
                txtNhanVienBaoHiem[2].setText("yyyy-MM-dd");
                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbNhanVienBaoHiem.clearSelection();
                tbNhanVienBaoHiem.setEnabled(false);
            }
        });

        pnTable();
        tbNhanVienBaoHiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbNhanVienBaoHiem.getSelectedRow();
                txtNhanVienBaoHiem[0].setText(tbNhanVienBaoHiem.getModel().getValueAt(i, 0).toString());                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                NGÀY BẮT ĐẦU
                String dateBatDauInString = tbNhanVienBaoHiem.getModel().getValueAt(i, 1).toString();
                Date dateBatDau = null;
                try {
                    dateBatDau = formatter.parse(dateBatDauInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[1].setDate(dateBatDau);
//                NGÀY KẾT THÚC
                String dateKetThucInString = tbNhanVienBaoHiem.getModel().getValueAt(i, 2).toString();
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

        headerSuggest headerSuggest = new headerSuggest("BẢO HIỂM CỦA NHÂN VIÊN " + manhanvien, 1300, 40);
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
        String[] arrNhanVienBaoHiem = {"Mã bảo hiểm", "Ngày bắt đầu", "Ngày kết thúc"};
        txtNhanVienBaoHiem = new JTextField[arrNhanVienBaoHiem.length];
        lbNhanVienBaoHiem = new JLabel[arrNhanVienBaoHiem.length];
        picker = new JXDatePicker[arrNhanVienBaoHiem.length];
        int xLb = 50, yLb = 70;
        int xTxt = 290, yTxt = 70;
        for (int i = 0; i < arrNhanVienBaoHiem.length; i++) {
            lbNhanVienBaoHiem[i] = new JLabel(arrNhanVienBaoHiem[i]);
            lbNhanVienBaoHiem[i].setBounds(xLb, yLb, 220, 30);
            lbNhanVienBaoHiem[i].setHorizontalAlignment(JLabel.CENTER);
            lbNhanVienBaoHiem[i].setFont(font);
            lbNhanVienBaoHiem[i].setName("lb" + i);
            pnDisplay.add(lbNhanVienBaoHiem[i]);
            yLb = yLb + 50;
            txtNhanVienBaoHiem[i] = new JTextField();
            txtNhanVienBaoHiem[i].setName("txt" + i);
            if (i != 0) {

                picker[i] = new JXDatePicker();
                picker[i].setFormats(new SimpleDateFormat("yyyy-MM-dd"));
                picker[i].setBounds(xTxt, yTxt, 230, 30);
                pnDisplay.add(picker[i]);
                yTxt = yTxt + 50;

            } else {
                txtNhanVienBaoHiem[i].setBounds(xTxt, yTxt, 200, 30);
                pnDisplay.add(txtNhanVienBaoHiem[i]);
                yTxt = yTxt + 50;
            }

        }
        lbSuggestBaoHiem = new JLabel("...", JLabel.CENTER);
        lbSuggestBaoHiem.setBounds(490, 70, 30, 30);
        lbSuggestBaoHiem.setOpaque(true);
        lbSuggestBaoHiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestBaoHiem sgbh = new SuggestBaoHiem();
                String s = sgbh.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienBaoHiem[0].setText(s);
                }
            }
        });
        pnDisplay.add(lbSuggestBaoHiem);
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
                    nvbhBUS.deleteNhanVienBaoHiem(txtNhanVienBaoHiem[0].getText(), manhanvien, txtNhanVienBaoHiem[1].getText());
                    cleanView();
                    tbNhanVienBaoHiem.clearSelection();
                    outModel(model, nvbhBUS.getList(manhanvien));
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtNhanVienBaoHiem[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảo hiểm cần sửa!");
                    return;
                }

                EditOrAdd = false;

                txtNhanVienBaoHiem[0].setEditable(false);

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbNhanVienBaoHiem.setEnabled(false);
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

                tbNhanVienBaoHiem.setEnabled(true);
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
                        String mabaohiem = txtNhanVienBaoHiem[0].getText();
                        String ngaybatdau = picker[1].getEditor().getText();
                        String ngayketthuc = picker[2].getEditor().getText();
                        if (validate(mabaohiem, ngaybatdau, ngayketthuc) == false) {
                            return;
                        }
                        if (nvbhBUS.checkMaBaoHiem(mabaohiem)) {
                            JOptionPane.showMessageDialog(null, "Mã bảo hiểm đã tồn tại!");
                            return;
                        }
                        //Upload lên DAO và BUS
                        nhanVienBaoHiemDTO nvbhDTO = new nhanVienBaoHiemDTO(mabaohiem, manhanvien, ngaybatdau, ngayketthuc);
                        nvbhBUS.addNhanVienBaoHiem(nvbhDTO);
                        outModel(model, nvbhBUS.getList(manhanvien));// Load lại table
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa bảo hiểm", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String mabaohiem = txtNhanVienBaoHiem[0].getText();
                        String ngaybatdau = picker[1].getEditor().getText();
                        String ngayketthuc = picker[2].getEditor().getText();
                        if (validate(mabaohiem, ngaybatdau, ngayketthuc) == false) {
                            return;
                        }
                        nhanVienBaoHiemDTO nvbhDTO = new nhanVienBaoHiemDTO(mabaohiem, manhanvien, ngaybatdau, ngayketthuc);
                        nvbhBUS.setNhanVienBaoHiem(nvbhDTO);
                        outModel(model, nvbhBUS.getList(manhanvien));
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbNhanVienBaoHiem.setEnabled(true);
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
        cmbChoice.addItem("Mã bảo hiểm");
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
        header.add("Mã bảo hiểm");
        header.add("Ngày bắt đầu");
        header.add("Ngày kết thúc");

        model = new DefaultTableModel(header, 4);
        tbNhanVienBaoHiem = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbNhanVienBaoHiem.setRowSorter(rowSorter);
        listNhanVienBaoHiem(manhanvien); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbNhanVienBaoHiem.setFocusable(false);
        tbNhanVienBaoHiem.setIntercellSpacing(new Dimension(0, 0));
        tbNhanVienBaoHiem.setRowHeight(30);
        tbNhanVienBaoHiem.getTableHeader().setOpaque(false);
        tbNhanVienBaoHiem.setFillsViewportHeight(true);
        tbNhanVienBaoHiem.getTableHeader().setBackground(new Color(232, 57, 99));
        tbNhanVienBaoHiem.getTableHeader().setForeground(Color.WHITE);
        tbNhanVienBaoHiem.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbNhanVienBaoHiem);
        scroll.setBounds(new Rectangle(0, 0, 1300, 410));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtNhanVienBaoHiem[0].setEditable(true);
        for (int i = 0; i < txtNhanVienBaoHiem.length; i++) {
            if (i == 1 || i == 2) {
                picker[i].setDate(null);
            } else {
                txtNhanVienBaoHiem[i].setText("");
            }
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<nhanVienBaoHiemDTO> nvbhDTO) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (nhanVienBaoHiemDTO n : nvbhDTO) {
            data = new Vector();
            data.add(n.getMabaohiem());
            data.add(n.getNgaybatdau());
            data.add(n.getNgayketthuc());
            model.addRow(data);
        }
        tbNhanVienBaoHiem.setModel(model);
    }

    public void listNhanVienBaoHiem(String manhanvien) // Chép ArrayList lên table
    {
        if (nvbhBUS.getList(manhanvien) == null) {
            nvbhBUS.listNhanvienbaohiem(manhanvien);
        }
        ArrayList<nhanVienBaoHiemDTO> nvbhDTO = nvbhBUS.getList(manhanvien);
        outModel(model, nvbhDTO);
    }

    public boolean validate(String mabaohiem, String ngaybatdau, String ngayketthuc) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtNhanVienBaoHiem.length; i++) {
            if (txtNhanVienBaoHiem[i].getText().equals("")) {
                validateFull = false;
            }
        }
        if (validateFull == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }

        if (validator.isValid(ngaybatdau) == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày bắt đầu: yyyy-MM-dd!");
            validate = false;
            return false;
        }
        if (validator.isValid(ngayketthuc) == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày kết thúc: yyyy-MM-dd!");
            validate = false;
            return false;
        }
        return validate;
    }

}
