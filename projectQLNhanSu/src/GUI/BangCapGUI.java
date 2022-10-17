/*

 */
package GUI;

import BUS.bangCapBUS;
import DTO.bangCapDTO;
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
 * @author funty
 */
public class BangCapGUI extends JPanel {

    DateValidator validatorDATE = new DateValidatorUsingDateFormat("yyyy-MM-dd");
    private bangCapBUS bcBUS = new bangCapBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack;
    private JTable tbBangCap;
    private DefaultTableModel model;
    private boolean EditOrAdd = true;
    JTextField[] txtBangCap;
    JLabel[] lbBangCap;
    TableRowSorter<TableModel> rowSorter;
    JXDatePicker[] picker;
String mabangcap;
    public BangCapGUI(int width) {
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

                tbBangCap.clearSelection();
                tbBangCap.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    bcBUS.deleteBangCap(mabangcap);
                    cleanView();
                    tbBangCap.clearSelection();
                    outModel(model, bcBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtBangCap[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bằng cấp cần sửa!");
                    return;
                }

                EditOrAdd = false;

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                tbBangCap.setEnabled(false);
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
                tbBangCap.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm bằng cấp", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String mabangcapAdd = bcBUS.mabangcap();
                        String tenbangcap = txtBangCap[0].getText();
                        String ngaybatdauhieuluc = picker[1].getEditor().getText();
                        String ngayketthuchieuluc = picker[2].getEditor().getText();
                        if (validate(mabangcapAdd) == false) {
                            return;
                        }
                        if (bcBUS.checkMaBangCap(mabangcapAdd)) {
                            JOptionPane.showMessageDialog(null, "Mã bằng cấp đã tồn tại!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        bangCapDTO bc = new bangCapDTO(mabangcapAdd, tenbangcap, ngaybatdauhieuluc, ngayketthuchieuluc);
                        bcBUS.addBangCap(bc);

                        outModel(model, bcBUS.getList());// Load lại table
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa bằng cấp", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
                        String tenbangcap = txtBangCap[0].getText();
                        String ngaybatdauhieuluc = picker[1].getEditor().getText();
                        String ngayketthuchieuluc = picker[2].getEditor().getText();
                        if (validatorDATE.isValid(ngaybatdauhieuluc) == false) {
                            JOptionPane.showMessageDialog(null, "Ngày bắt đầu hiệu lực không đúng định dạng!");
                            return;
                        }
                        if (validatorDATE.isValid(ngayketthuchieuluc) == false) {
                            JOptionPane.showMessageDialog(null, "Ngày kết thúc hiệu lực không đúng định dạng!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        bangCapDTO bc = new bangCapDTO(mabangcap, tenbangcap, ngaybatdauhieuluc, ngayketthuchieuluc);
                        bcBUS.setBangCap(bc);

                        outModel(model, bcBUS.getList());// Load lại table
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbBangCap.setEnabled(true);
                    }
                }

            }
        });

        pnTable();
        tbBangCap.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbBangCap.getSelectedRow();
                mabangcap=tbBangCap.getModel().getValueAt(i, 0).toString();
                txtBangCap[0].setText(tbBangCap.getModel().getValueAt(i, 1).toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                NGÀY HIỆU LỰC
                String dateHieuLucInString = tbBangCap.getModel().getValueAt(i, 2).toString();
                Date dateHieuLuc = null;
                try {
                    dateHieuLuc = formatter.parse(dateHieuLucInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[1].setDate(dateHieuLuc);
//                NGÀY HẾT HẠN
                String dateHetHanInString = tbBangCap.getModel().getValueAt(i, 3).toString();
                Date dateHetHan = null;
                try {
                    dateHetHan = formatter.parse(dateHetHanInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker[2].setDate(dateHetHan);

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
        String[] arrBangCap = {"Tên bằng cấp", "Ngày bắt đầu hiệu lực", "Ngày kết thúc hiệu lực"};
        txtBangCap = new JTextField[arrBangCap.length];
        lbBangCap = new JLabel[arrBangCap.length];
        picker = new JXDatePicker[arrBangCap.length];
        int xLb = 50, yLb = 50;
        int xTxt = 260, yTxt = 50;
        for (int i = 0; i < arrBangCap.length; i++) {
            lbBangCap[i] = new JLabel(arrBangCap[i]);
            lbBangCap[i].setBounds(xLb, yLb, 180, 30);
            lbBangCap[i].setHorizontalAlignment(JLabel.CENTER);

            lbBangCap[i].setName("lb" + i);
            pnDisplay.add(lbBangCap[i]);
            yLb = yLb + 40;
            if (i !=0) {
                picker[i] = new JXDatePicker();
                picker[i].setFormats(new SimpleDateFormat("yyyy-MM-dd"));
                picker[i].setBounds(xTxt, yTxt, 220, 30);
                pnDisplay.add(picker[i]);
                yTxt = yTxt + 40;
            } else {
                txtBangCap[i] = new JTextField();
                txtBangCap[i].setName("txt" + i);
                txtBangCap[i].setBounds(xTxt, yTxt, 220, 30);
                pnDisplay.add(txtBangCap[i]);
                yTxt = yTxt + 40;
            }

        }
        pnDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
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
        cmbChoice.addItem("Mã bằng cấp");
        cmbChoice.addItem("Tên bằng cấp");
        cmbChoice.addItem("Ngày bắt đầu hiệu lực");
        cmbChoice.addItem("Ngày kết thúc hiệu lực");
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
        header.add("Mã bằng cấp");
        header.add("Tên bằng cấp");
        header.add("Ngày bắt đầu hiệu lực");
        header.add("Ngày kết thúc hiệu lực");
        model = new DefaultTableModel(header, 2);
        tbBangCap = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbBangCap.setRowSorter(rowSorter);
        listBangCap(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
        tbBangCap.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbBangCap.getColumnModel().getColumn(1).setPreferredWidth(40);

        // Custom table
        tbBangCap.setFocusable(false);
        tbBangCap.setIntercellSpacing(new Dimension(0, 0));
        tbBangCap.setRowHeight(30);
        tbBangCap.getTableHeader().setOpaque(false);
        tbBangCap.setFillsViewportHeight(true);
        tbBangCap.getTableHeader().setBackground(new Color(232, 57, 99));
        tbBangCap.getTableHeader().setForeground(Color.WHITE);
        tbBangCap.setSelectionBackground(new Color(52, 152, 219));
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbBangCap);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 440));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtBangCap[0].setEditable(true);
        for (int i = 0; i < txtBangCap.length; i++) {
            if (i >0) {
                picker[i].setDate(null);
            } else {
                txtBangCap[i].setText("");
            }

        }

    }

    public void outModel(DefaultTableModel model, ArrayList<bangCapDTO> bc) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (bangCapDTO n : bc) {
            data = new Vector();
            data.add(n.getMabangcap());
            data.add(n.getTenbangcap());
            data.add(n.getNgaybatdauhieuluc());
            data.add(n.getNgayketthuchieuluc());
            model.addRow(data);
        }
        tbBangCap.setModel(model);
    }

    public void listBangCap() // Chép ArrayList lên table
    {
        if (bcBUS.getList() == null) {
            bcBUS.listBangCap();
        }
        ArrayList<bangCapDTO> bc = bcBUS.getList();
//        model.setRowCount(0);
        outModel(model, bc);
    }

    public boolean validate(String mabangcap) {
        boolean validate = true;
        boolean validateFull = true;
        for (int i = 0; i < txtBangCap.length; i++) {
            if (i >0) {
                if (picker[i].getEditor().getText().equals("")) {
                    validateFull = false;
                }
            } else if (txtBangCap[i].getText().equals("")) {
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
