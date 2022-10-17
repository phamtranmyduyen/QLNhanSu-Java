/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.hopDongLaoDongBUS;
import DTO.hopDongLaoDongDTO;
import GUI.model.DateValidator;
import GUI.model.DateValidatorUsingDateFormat;
import GUI.model.navItem;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
 * @author admin
 */
public class HopDongLaoDongGUI extends JPanel {

    DateValidator validatorDate = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    DateValidator validatorHour = new DateValidatorUsingDateFormat("HH:mm:ss");
    private hopDongLaoDongBUS hdldBUS = new hopDongLaoDongBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnEast, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbhdld;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txthdldleft, txthdldright;
    JLabel[] lbhdldleft, lbhdldright, lbSuggestMa;
    TableRowSorter<TableModel> rowSorter;
    JXDatePicker[] picker;
    String mahdld;

    public HopDongLaoDongGUI(int width) {
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

              txthdldleft[6].setText("HH:mm:ss");
               txthdldleft[7].setText("HH:mm:ss");
                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbhdld.clearSelection();
                tbhdld.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    hdldBUS.deleteHdld(mahdld);
                    cleanView();
                    tbhdld.clearSelection();
                    outModel(model, hdldBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txthdldleft[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hợp đồng lao động cần sửa!");
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
                tbhdld.setEnabled(false);
            }
        });
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanView();

                txthdldleft[6].setText("HH:mm:ss");
               txthdldleft[7].setText("HH:mm:ss");
                lbAdd.setVisible(true);
                lbEdit.setVisible(true);
                lbRemove.setVisible(true);

                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
//                btnFile.setVisible(false);

                tbhdld.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm hợp đồng lao động", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String mahdldAdd = hdldBUS.mahopdonglaodong();
                        String loaihdld = txthdldleft[0].getText();
                        String ngaybd = picker[1].getEditor().getText();
                        String ngaykt = picker[2].getEditor().getText();
                        String diadiemlamviec = txthdldleft[3].getText();
                        String ngayki = picker[4].getEditor().getText();
                        String thoihanhopdong = txthdldleft[5].getText();
                        String lamviectrongngaytu = txthdldleft[6].getText();
                        String lamviectrongngayden = txthdldleft[7].getText();
                        String lamviectrongtuantu = txthdldright[0].getText();
                        String lamviectrongtuanden = txthdldright[1].getText();
                        String maluongcanban = txthdldright[2].getText();
                        String mathuong = txthdldright[3].getText();
                        String mahesoluong = txthdldright[4].getText();
                        String maphucap = txthdldright[5].getText();
                        String ghichu = txthdldright[6].getText();
                        if (validate(mahdldAdd, lamviectrongngaytu, lamviectrongngayden) == false) {
                            return;
                        }
                        if (hdldBUS.checkMahdld(mahdldAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã hợp đồng lao động đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        hopDongLaoDongDTO hdld = new hopDongLaoDongDTO(mahdldAdd, loaihdld, ngaybd, ngaykt, diadiemlamviec, ngayki, thoihanhopdong, lamviectrongngaytu, lamviectrongngayden, lamviectrongtuantu, lamviectrongtuanden, maluongcanban, mathuong, mahesoluong, maphucap, ghichu);
                        hdldBUS.addHdld(hdld);

                        outModel(model, hdldBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa hợp đồng lao động", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String loaihdld = txthdldleft[0].getText();
                        String ngaybd = picker[1].getEditor().getText();
                        String ngaykt = picker[2].getEditor().getText();
                        String diadiemlamviec = txthdldleft[3].getText();
                        String ngayki = picker[4].getEditor().getText();
                        String thoihanhopdong = txthdldleft[5].getText();
                        String lamviectrongngaytu = txthdldleft[6].getText();
                        String lamviectrongngayden = txthdldleft[7].getText();
                        String lamviectrongtuantu = txthdldright[0].getText();
                        String lamviectrongtuanden = txthdldright[1].getText();
                        String maluongcanban = txthdldright[2].getText();
                        String mathuong = txthdldright[3].getText();
                        String mahesoluong = txthdldright[4].getText();
                        String maphucap = txthdldright[5].getText();
                        String ghichu = txthdldright[6].getText();
                        if (validate(mahdld, lamviectrongngaytu, lamviectrongngayden) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        hopDongLaoDongDTO hdld = new hopDongLaoDongDTO(mahdld, loaihdld, ngaybd, ngaykt, diadiemlamviec, ngayki, thoihanhopdong, lamviectrongngaytu, lamviectrongngayden, lamviectrongtuantu, lamviectrongtuanden, maluongcanban, mathuong, mahesoluong, maphucap, ghichu);
                        hdldBUS.setHdld(hdld);

                        outModel(model, hdldBUS.getList());
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbhdld.setEnabled(true);
                    }

                }

            }
        });

        pnTable();
        tbhdld.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbhdld.getSelectedRow();
                mahdld = tbhdld.getModel().getValueAt(i, 0).toString();
                txthdldleft[0].setText(tbhdld.getModel().getValueAt(i, 1).toString());
//                txthdldleft[2].setText(tbhdld.getModel().getValueAt(i, 2).toString());
//                txthdldleft[3].setText(tbhdld.getModel().getValueAt(i, 3).toString());
                txthdldleft[3].setText(tbhdld.getModel().getValueAt(i, 4).toString());
//                txthdldleft[5].setText(tbhdld.getModel().getValueAt(i, 5).toString());
                txthdldleft[5].setText(tbhdld.getModel().getValueAt(i, 6).toString());
                txthdldleft[6].setText(tbhdld.getModel().getValueAt(i, 7).toString());
                txthdldleft[7].setText(tbhdld.getModel().getValueAt(i, 8).toString());
                txthdldright[0].setText(tbhdld.getModel().getValueAt(i, 9).toString());
                txthdldright[1].setText(tbhdld.getModel().getValueAt(i, 10).toString());
                txthdldright[2].setText(tbhdld.getModel().getValueAt(i, 11).toString());
                txthdldright[3].setText(tbhdld.getModel().getValueAt(i, 12).toString());
                txthdldright[4].setText(tbhdld.getModel().getValueAt(i, 13).toString());
                txthdldright[5].setText(tbhdld.getModel().getValueAt(i, 14).toString());
                txthdldright[6].setText(tbhdld.getModel().getValueAt(i, 15).toString());

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                NGÀY BẮT ĐẦU
                String dateBatDauInString = tbhdld.getModel().getValueAt(i, 2).toString();
                Date dateBatDau = null;
                try {
                    dateBatDau = formatter.parse(dateBatDauInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[1].setDate(dateBatDau);
//                NGÀY KẾT THÚC
                String dateKetThucInString = tbhdld.getModel().getValueAt(i, 3).toString();
                Date dateKetThuc = null;
                try {
                    dateKetThuc = formatter.parse(dateKetThucInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[2].setDate(dateKetThuc);
//                 NGÀY KÝ
                String dateKyInString = tbhdld.getModel().getValueAt(i, 3).toString();
                Date dateKy = null;
                try {
                    dateKy = formatter.parse(dateKyInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[4].setDate(dateKy);
            }
        });

//TEST
//
        this.add(pnDisplay, BorderLayout.WEST);
        this.add(pnOption, BorderLayout.EAST);
        this.add(pnTable, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(null);
        pnOption.setBackground(Color.pink);
        pnOption.setPreferredSize(new Dimension(220, 500));
        lbAdd = new JLabel(new ImageIcon("./src/img/add.png"), JLabel.CENTER);
        lbEdit = new JLabel(new ImageIcon("./src/img/edit.png"), JLabel.CENTER);
        lbRemove = new JLabel(new ImageIcon("./src/img/remove.png"), JLabel.CENTER);

        lbAdd.setBounds(0, 80, 200, 50);
        lbEdit.setBounds(0, 150, 200, 50);
        lbRemove.setBounds(0, 220, 200, 50);
        pnOption.add(lbAdd);
        pnOption.add(lbEdit);
        pnOption.add(lbRemove);
        lbAdd.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbEdit.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbRemove.setCursor(new Cursor((Cursor.HAND_CURSOR)));

        btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(20, 120, 155, 50);
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(20, 200, 155, 50);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnOption.add(btnConfirm);
        pnOption.add(btnBack);

        return pnOption;
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension((this.DEFALUT_WIDTH / 3) * 2, 500));
        pnDisplay.setBackground(Color.pink);
//        PnDisplayLEFT
        String[] arrhdld = {"Loại HĐLĐ", "Ngày bắt đầu", "Ngày kết thúc", "Địa điểm làm việc", "Ngày kí",
            "Thời hạn hợp đồng", "Làm việc trong ngày từ", "Làm việc trong ngày đến"};
        txthdldleft = new JTextField[arrhdld.length];
        lbhdldleft = new JLabel[arrhdld.length];
        picker = new JXDatePicker[arrhdld.length];
        int xLb1 = 10, yLb1 = 20;
        int xTxt1 = 190, yTxt1 = 20;
        for (int i = 0; i < arrhdld.length; i++) {
            lbhdldleft[i] = new JLabel(arrhdld[i]);
            lbhdldleft[i].setBounds(xLb1, yLb1, 190, 30);
            lbhdldleft[i].setName("lbl" + i);
            pnDisplay.add(lbhdldleft[i]);
            yLb1 = yLb1 + 45;
            if (i == 1 || i == 2 || i == 4) {
                picker[i] = new JXDatePicker();
                picker[i].setFormats(new SimpleDateFormat("yyyy-MM-dd"));
                picker[i].setBounds(xTxt1, yTxt1, 220, 30);
                pnDisplay.add(picker[i]);
                yTxt1 = yTxt1 + 45;
            } else {
                txthdldleft[i] = new JTextField();
                txthdldleft[i].setName("txt1" + i);
                txthdldleft[i].setBounds(xTxt1, yTxt1, 220, 30);
                pnDisplay.add(txthdldleft[i]);
                yTxt1 = yTxt1 + 45;
            }

        }
        txthdldleft[6].setText("HH:mm:ss");
        txthdldleft[7].setText("HH:mm:ss");

//        pnDisPlayRIGHT
        String[] arrhdld2 = {"Làm việc trong tuần từ", "Làm việc trong tuần đến", "Mã lương căn bản",
            "Mã thưởng", "Mã hệ số lương", "Mã phụ cấp", "Ghi chú"};
        txthdldright = new JTextField[arrhdld2.length];
        lbhdldright = new JLabel[arrhdld2.length];
        lbSuggestMa = new JLabel[arrhdld2.length];

        int xLb2 = 430, yLb2 = 20;
        int xTxt2 = 610, yTxt2 = 20;
        int xLbSg2 = 800, yLbSg2 = 20;
        for (int i = 0; i < arrhdld2.length; i++) {
            lbhdldright[i] = new JLabel(arrhdld2[i]);
            lbhdldright[i].setBounds(xLb2, yLb2, 250, 30);
            lbhdldright[i].setName("lbr" + i);
            pnDisplay.add(lbhdldright[i]);
            yLb2 = yLb2 + 45;
            if (i > 1 && i < 6) {
                txthdldright[i] = new JTextField();
                txthdldright[i].setName("txtr" + i);
                txthdldright[i].setBounds(xTxt2, yTxt2, 190, 30);
                pnDisplay.add(txthdldright[i]);
                yTxt2 = yTxt2 + 45;
                lbSuggestMa[i] = new JLabel("...", JLabel.CENTER);
                lbSuggestMa[i].setBounds(xLbSg2, yLbSg2, 30, 30);
                lbSuggestMa[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                lbSuggestMa[i].setOpaque(true);
                System.out.println("lbSuggestMa[" + i + "]");
                pnDisplay.add(lbSuggestMa[i]);

            } else {
                txthdldright[i] = new JTextField();
                txthdldright[i].setName("txtr" + i);
                txthdldright[i].setBounds(xTxt2, yTxt2, 220, 30);
                pnDisplay.add(txthdldright[i]);
                yTxt2 = yTxt2 + 45;
            }
            yLbSg2 = yLbSg2 + 45;

        }
        System.out.println(lbhdldright[arrhdld2.length - 1].getBounds());

//        LƯƠNG CĂN BẢN
        lbSuggestMa[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestMaLuongCanBan sglcb = new SuggestMaLuongCanBan();
                String s = sglcb.getTextFieldContent();
                if (!s.equals("")) {
                    txthdldright[2].setText(s);
                }
            }
        });
        //        MÃ THƯỞNG
        lbSuggestMa[3].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestMathuong sgt = new SuggestMathuong();
                String s = sgt.getTextFieldContent();
                if (!s.equals("")) {
                    txthdldright[3].setText(s);
                }
            }
        });
        //        MÃ HỆ SỐ LƯƠNG
        lbSuggestMa[4].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestMahesoluong sghsl = new SuggestMahesoluong();
                String s = sghsl.getTextFieldContent();
                if (!s.equals("")) {
                    txthdldright[4].setText(s);
                }
            }
        });
        //        MÃ PHỤ CẤP
        lbSuggestMa[5].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestPhuCap sgpc = new SuggestPhuCap();
                String s = sgpc.getTextFieldContent();
                if (!s.equals("")) {
                    txthdldright[5].setText(s);
                }
            }
        });
        pnFind();
        pnDisplay.add(pnFind);
        return pnDisplay;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel();
        pnFind.setLayout(null);
        pnFind.setBounds(430, 335, 400, 30);
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã HĐLĐ");
        cmbChoice.addItem("Loại HĐLĐ");
        cmbChoice.addItem("Ngày bắt đầu");
        cmbChoice.addItem("Ngày kết thúc");
        cmbChoice.addItem("Địa điểm làm viêc");
        cmbChoice.addItem("Ngày kí");
        cmbChoice.addItem("Thời hạn hợp đồng");
        cmbChoice.addItem("Làm việc trong ngày từ");
        cmbChoice.addItem("Làm việc trong ngày đến");
        cmbChoice.addItem("Làm việc trong tuần từ");
        cmbChoice.addItem("Làm việc trong tuần đến");
        cmbChoice.addItem("Mã lương căn bản");
        cmbChoice.addItem("Mã thưởng");
        cmbChoice.addItem("Mã hệ số lương");
        cmbChoice.addItem("Mã phụ cấp");
        cmbChoice.addItem("Ghi chú");
        cmbChoice.setBounds(0, 0, 120, 30);

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(120, 0, 230, 30);
//        txtSearch.setPreferredSize(new Dimension(230, 30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
        searchIcon.setBounds(new Rectangle(350, -5, 50, 40));
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
        pnTable.setPreferredSize(new Dimension(1090, 590));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã HĐLĐ");
        header.add("Loại HĐLĐ");
        header.add("Ngày bđ");
        header.add("Ngày kt");
        header.add("Địa điểm làm việc");
        header.add("Ngày kí");
        header.add("Thời hạn hợp đồng");
        header.add("Làm việc trong ngày từ");
        header.add("Làm việc trong ngày đến");
        header.add("Làm việc trong tuần từ");
        header.add("Làm việc trong tuần đến");
        header.add("Mã lương căn bản");
        header.add("Mã thưởng");
        header.add("Mã hệ số lương");
        header.add("Mã phụ cấp");
        header.add("Ghi chú");
        model = new DefaultTableModel(header, 2);
        tbhdld = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbhdld.setRowSorter(rowSorter);
        listhdld(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbhdld.setFocusable(false);
        tbhdld.setIntercellSpacing(new Dimension(0, 0));
        tbhdld.setRowHeight(30);
        tbhdld.getTableHeader().setOpaque(false);
        tbhdld.setFillsViewportHeight(true);
        tbhdld.getTableHeader().setBackground(new Color(232, 57, 99));
        tbhdld.getTableHeader().setForeground(Color.WHITE);
        tbhdld.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbhdld);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 470));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txthdldleft[0].setEditable(true);
        for (int i = 0; i < txthdldleft.length; i++) {
            if (i == 1 || i == 2 || i == 4) {
                picker[i].setDate(null);
            } else {
                txthdldleft[i].setText("");
            }
        }
        for (int i = 0; i < txthdldright.length; i++) {

            txthdldright[i].setText("");

        }

    }

    public void outModel(DefaultTableModel model, ArrayList<hopDongLaoDongDTO> hdld) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (hopDongLaoDongDTO n : hdld) {
            data = new Vector();
            data.add(n.getMahdld());
            data.add(n.getLoaihdld());
            data.add(n.getNgaybd());
            data.add(n.getNgaykt());
            data.add(n.getDiadiemlamviec());
            data.add(n.getNgayki());
            data.add(n.getThoihanhopdong());
            data.add(n.getLamviectrongngaytu());
            data.add(n.getLamviectrongngayden());
            data.add(n.getLamviectrongtuantu());
            data.add(n.getLamviectrongtuanden());
            data.add(n.getMaluongcanban());
            data.add(n.getMathuong());
            data.add(n.getMahesoluong());
            data.add(n.getMaphucap());
            data.add(n.getGhichu());

            model.addRow(data);
        }
        tbhdld.setModel(model);
    }

    public void listhdld() // Chép ArrayList lên table
    {
        if (hdldBUS.getList() == null) {
            hdldBUS.listhdld();
        }
        ArrayList<hopDongLaoDongDTO> hdld = hdldBUS.getList();
        outModel(model, hdld);
    }

    public boolean validate(String mahdld, String lamviectrongngaytu, String lamviectrongngayden) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txthdldleft.length; i++) {
            if (i == 1 || i == 2 || i == 4) {
                if (picker[i].getDate() == null) {
                    validateFull = false;
                }
            } else {
                if (txthdldleft[i].getText().equals("")) {
                    validateFull = false;
                }
            }

        }
        for (int i = 0; i < txthdldright.length - 1; i++) {
            if (txthdldright[i].getText().equals("")) {
                validateFull = false;
            }
        }
        if (validateFull == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }

        if (validatorHour.isValid(lamviectrongngaytu) == false) {
            JOptionPane.showMessageDialog(null, "Thời gian làm việc trong ngày từ không đúng định dạng!");
            validate = false;
            return false;
        }
        if (validatorHour.isValid(lamviectrongngayden) == false) {
            JOptionPane.showMessageDialog(null, "Thời gian làm việc trong ngày đến không đúng định dạng!");
            validate = false;
            return false;
        }
        return validate;
    }
}
