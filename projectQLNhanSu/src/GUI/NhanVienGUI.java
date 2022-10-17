
package GUI;

import BUS.nhanVienBUS;
import BUS.nhanVienBaoHiemBUS;
import BUS.nhanVienChucVuBUS;
import DTO.nhanVienDTO;
import GUI.model.DateValidator;
import GUI.model.DateValidatorUsingDateFormat;
import GUI.model.checkError;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author MaiThy
 */
public class NhanVienGUI extends JPanel {

    DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

    checkError check = new checkError();
    private nhanVienBUS nvBUS = new nhanVienBUS();
    private nhanVienChucVuBUS nvcvBUS = new nhanVienChucVuBUS();
    private nhanVienBaoHiemBUS nvbhBUS = new nhanVienBaoHiemBUS();
    private int DEFALUT_WIDTH;
    private JPanel pnDisplay, pnOption, pnFind, pnTable;
    JLabel lbAdd, lbEdit, lbRemove, btnConfirm, btnBack, lbXemChucVu, lbXemBaoHiem, lbNhapExcel, lbXuatExcel;
    private JTable tbNhanVien;
    private DefaultTableModel tbModel;
    private boolean EditOrAdd = true;
    JTextField[] txtNhanVienLeft, txtNhanVienRight, txtNhanVienCenter;
    JLabel[] lbNhanVienLeft, lbNhanVienRight, lbNhanVienCenter, lbSuggestMa;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<String> listTenBaoHiem, listTenChucVu;
    boolean flagAddBaoHiem, flagAddBangCap;
    JXDatePicker picker;
    String manhanvien;

    public NhanVienGUI(int width) {
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 10, this.DEFALUT_WIDTH - 200, 710));

        pnDisplay();
        pnOption();
        // MouseClick btnADD
        lbAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditOrAdd = true;

                cleanView();
//                txtNhanVienLeft[4].setText("yyyy-mm-dd");

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);
                lbNhapExcel.setVisible(false);
                lbXuatExcel.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbNhanVien.clearSelection();
                tbNhanVien.setEnabled(false);
            }
        });

        // MouseClick btnDelete
        lbRemove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    nvBUS.deleteNhanVien(manhanvien);
                    cleanView();
                    tbNhanVien.clearSelection();
                    outModel(tbModel, nvBUS.getList());
                }
            }
        });

        // MouseClick btnEdit
        lbEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtNhanVienLeft[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa!");
                    return;
                }

                EditOrAdd = false;

                txtNhanVienLeft[0].setEditable(false);

                lbAdd.setVisible(false);
                lbEdit.setVisible(false);
                lbRemove.setVisible(false);
                lbNhapExcel.setVisible(false);
                lbXuatExcel.setVisible(false);

                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);

//                tbl.clearSelection();
                tbNhanVien.setEnabled(false);
            }
        });
        // MouseClick lbXemChucVu
        lbXemChucVu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtNhanVienLeft[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xem!");
                    return;
                }

                nhanVienChucVuGUI nvcv = new nhanVienChucVuGUI(txtNhanVienLeft[0].getText());
            }
        });
        // MouseClick lbXemBaoHiem
        lbXemBaoHiem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (txtNhanVienLeft[0].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xem!");
                    return;
                }

                nhanVienBaoHiemGUI nvbh = new nhanVienBaoHiemGUI(txtNhanVienLeft[0].getText());
            }
        });
        // MouseClick lbXuatExcel
        lbXuatExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                nvBUS.ExportExcelDatabase();
                JOptionPane.showMessageDialog(null, "Xuất file excel thành công");
            }
        });
        lbNhapExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Excel", "xlsx");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile(); //Lấy URL
                    nvBUS.ImportExcelDatabase(file);
                    nvBUS.listNhanVien();
                    outModel(tbModel, nvBUS.getList());
                    JOptionPane.showMessageDialog(null, "Nhập file excel thành công");
                }
            }
        });
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanView();
//                txtNhanVienLeft[4].setText("yyyy-mm-dd");
                lbAdd.setVisible(true);
                lbEdit.setVisible(true);
                lbRemove.setVisible(true);
                lbNhapExcel.setVisible(true);
                lbXuatExcel.setVisible(true);

                btnConfirm.setVisible(false);
                btnBack.setVisible(false);

                tbNhanVien.setEnabled(true);
            }
        });

        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i;
                if (EditOrAdd) //Thêm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField
//                        String manhanvien = txtNhanVienLeft[0].getText();
                        String manhanvienAdd = nvBUS.manhanvien();
                        String hoten = txtNhanVienLeft[0].getText();
                        String email = txtNhanVienLeft[1].getText();
                        String sodienthoai = txtNhanVienLeft[2].getText();
                        String ngaysinh = picker.getEditor().getText();
                        String diachi = txtNhanVienLeft[4].getText();
                        String gioitinh = txtNhanVienLeft[5].getText();
                        String cmnd = txtNhanVienCenter[0].getText();
                        String mataikhoan = txtNhanVienRight[0].getText();
                        String mabangcap = txtNhanVienRight[1].getText();
                        String mahdld = txtNhanVienRight[2].getText();
                        String maphongban = txtNhanVienRight[3].getText();
                        if (validateNhanVien(manhanvienAdd, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, mataikhoan, mabangcap, mahdld) == false) {
                            return;
                        }                        
                        //Upload sản phẩm lên DAO và BUS
                        nhanVienDTO nv = new nhanVienDTO(manhanvienAdd, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, mataikhoan, mabangcap, mahdld, maphongban);
                        nvBUS.addNhanVien(nv);

                        outModel(tbModel, nvBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        cleanView();
                    }
                } else // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa nhân viên", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
//                        Mã nhân viên", "Họ tên", "Email", "Số điện thoại", "Ngày sinh", "Địa chỉ", "Giới tính", "CMN
                        //Lấy dữ liệu từ TextField
 
                        String hoten = txtNhanVienLeft[0].getText();
                        String email = txtNhanVienLeft[1].getText();
                        String sodienthoai = txtNhanVienLeft[2].getText();
                        String ngaysinh = picker.getEditor().getText();
                        String diachi = txtNhanVienLeft[4].getText();
                        String gioitinh = txtNhanVienLeft[5].getText();
                        String cmnd = txtNhanVienCenter[0].getText();
                        String mataikhoan = txtNhanVienRight[0].getText();
                        String mabangcap = txtNhanVienRight[1].getText();
                        String mahdld = txtNhanVienRight[2].getText();
                        String maphongban = txtNhanVienRight[3].getText();
                        if (validateNhanVien(manhanvien, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, mataikhoan, mabangcap, mahdld) == false) {
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        nhanVienDTO nv = new nhanVienDTO(manhanvien, hoten, email, sodienthoai, ngaysinh, diachi, gioitinh, cmnd, mataikhoan, mabangcap, mahdld, maphongban);
                        nvBUS.setNhanVien(nv);

                        outModel(tbModel, nvBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        tbNhanVien.setEnabled(true);
                    }
                }

            }
        });

//        pnFind();
        pnTable();
        tbNhanVien.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbNhanVien.getSelectedRow();
                manhanvien = tbNhanVien.getModel().getValueAt(i, 0).toString();
                txtNhanVienLeft[0].setText(tbNhanVien.getModel().getValueAt(i, 1).toString());
                txtNhanVienLeft[1].setText(tbNhanVien.getModel().getValueAt(i, 2).toString());
                txtNhanVienLeft[2].setText(tbNhanVien.getModel().getValueAt(i, 3).toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                String dateInString = tbNhanVien.getModel().getValueAt(i, 4).toString();
                Date date = null;
                try {
                    date = formatter.parse(dateInString);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                picker.setDate(date);
                
                txtNhanVienLeft[4].setText(tbNhanVien.getModel().getValueAt(i, 5).toString());
                txtNhanVienLeft[5].setText(tbNhanVien.getModel().getValueAt(i, 6).toString());
                txtNhanVienCenter[0].setText(tbNhanVien.getModel().getValueAt(i, 7).toString());

                txtNhanVienRight[0].setText(tbNhanVien.getModel().getValueAt(i, 8).toString());
                txtNhanVienRight[1].setText(tbNhanVien.getModel().getValueAt(i, 9).toString());
                txtNhanVienRight[2].setText(tbNhanVien.getModel().getValueAt(i, 10).toString());
                txtNhanVienRight[3].setText(tbNhanVien.getModel().getValueAt(i, 11).toString());
            }
        });

// CLICK CHỌN TÊN TÀI KHOẢN
        lbSuggestMa[0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestTenTaiKhoan sttk = new SuggestTenTaiKhoan();
                String s = sttk.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienRight[0].setText(s);
                }
            }
        });
        ////CLICK CHỌN MÃ BẰNG CẤP
        lbSuggestMa[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestMabangcap stbc = new SuggestMabangcap();
                String s = stbc.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienRight[1].setText(s);
                }
            }
        });

//// CLICK CHỌN MÃ HĐLĐ
        lbSuggestMa[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestMaHĐLĐ sttk = new SuggestMaHĐLĐ();
                String s = sttk.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienRight[2].setText(s);
                }
            }
        });
        //// CLICK CHỌN MÃ HĐLĐ
        lbSuggestMa[3].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SuggestPhongBan sgpb = new SuggestPhongBan();
                String s = sgpb.getTextFieldContent();
                if (!s.equals("")) {
                    txtNhanVienRight[3].setText(s);
                }
            }
        });

        this.add(pnDisplay, BorderLayout.WEST);
        this.add(pnOption, BorderLayout.EAST);
        this.add(pnTable, BorderLayout.SOUTH);
//        this.setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(null);
        pnDisplay.setPreferredSize(new Dimension((this.DEFALUT_WIDTH / 3) * 2, 480));
        pnDisplay.setBackground(Color.pink);
//        PnDisplayLEFT
        String[] arrNhanVien1 = {"Họ tên", "Email", "Số điện thoại", "Ngày sinh", "Địa chỉ", "Giới tính"};
        txtNhanVienLeft = new JTextField[arrNhanVien1.length];
        lbNhanVienLeft = new JLabel[arrNhanVien1.length];
        int xLb1 = 20, yLb1 = 40;
        int xTxt1 = 150, yTxt1 = 40;
        for (int i = 0; i < arrNhanVien1.length; i++) {
            lbNhanVienLeft[i] = new JLabel(arrNhanVien1[i]);
            lbNhanVienLeft[i].setBounds(xLb1, yLb1, 150, 30);
            lbNhanVienLeft[i].setName("lbl" + i);
            pnDisplay.add(lbNhanVienLeft[i]);
            yLb1 = yLb1 + 60;
            if (i != 3) {
                txtNhanVienLeft[i] = new JTextField();
                txtNhanVienLeft[i].setName("txt1" + i);
                txtNhanVienLeft[i].setBounds(xTxt1, yTxt1, 230, 30);
                pnDisplay.add(txtNhanVienLeft[i]);
                yTxt1 = yTxt1 + 60;
            } else {
                picker = new JXDatePicker();
                picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
                picker.setBounds(xTxt1, yTxt1, 230, 30);
                pnDisplay.add(picker);
                yTxt1 = yTxt1 + 60;
            }

        }
//        txtNhanVienLeft[4].setText("yyyy-mm-dd");
//        pnDisPlayRIGHT
        String[] arrNhanVien3 = {"CMND"};
        txtNhanVienCenter = new JTextField[arrNhanVien3.length];
        lbNhanVienCenter = new JLabel[arrNhanVien3.length];
        int xLb3 = 430, yLb3 = 40;
        int xTxt3 = 550, yTxt3 = 40;
        for (int i = 0; i < arrNhanVien3.length; i++) {
            lbNhanVienCenter[i] = new JLabel(arrNhanVien3[i]);
            lbNhanVienCenter[i].setBounds(xLb3, yLb3, 150, 30);
            lbNhanVienCenter[i].setName("lb3" + i);
            pnDisplay.add(lbNhanVienCenter[i]);
            yLb3 = yLb3 + 60;
            txtNhanVienCenter[i] = new JTextField();
            txtNhanVienCenter[i].setName("txt3" + i);
            txtNhanVienCenter[i].setBounds(xTxt3, yTxt3, 230, 30);
            pnDisplay.add(txtNhanVienCenter[i]);
            yTxt3 = yTxt3 + 60;
        }
        String[] arrNhanVien2 = {"Tên tài khoản", "Mã bằng cấp", "Mã HĐLĐ", "Mã phòng ban"};
        txtNhanVienRight = new JTextField[arrNhanVien2.length];
        lbNhanVienRight = new JLabel[arrNhanVien2.length];
        lbSuggestMa = new JLabel[arrNhanVien2.length];
        int xLb2 = 430, yLb2 = 100;
        int xTxt2 = 550, yTxt2 = 100;
        int xLbSg2 = 750, yLbSg2 = 100;
        for (int i = 0; i < arrNhanVien2.length; i++) {
            lbNhanVienRight[i] = new JLabel(arrNhanVien2[i]);
            lbNhanVienRight[i].setBounds(xLb2, yLb2, 180, 30);
            lbNhanVienRight[i].setName("lbr" + i);
            pnDisplay.add(lbNhanVienRight[i]);
            yLb2 = yLb2 + 60;
            txtNhanVienRight[i] = new JTextField();
            txtNhanVienRight[i].setName("txtr" + i);
            txtNhanVienRight[i].setBounds(xTxt2, yTxt2, 200, 30);
            pnDisplay.add(txtNhanVienRight[i]);
            yTxt2 = yTxt2 + 60;
            lbSuggestMa[i] = new JLabel("...", JLabel.CENTER);
            lbSuggestMa[i].setBounds(xLbSg2, yLbSg2, 30, 30);
            lbSuggestMa[i].setHorizontalAlignment(JLabel.CENTER);
            lbSuggestMa[i].setOpaque(true);
            lbSuggestMa[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            pnDisplay.add(lbSuggestMa[i]);
            yLbSg2 = yLbSg2 + 60;
        }
        lbXemChucVu = new JLabel(new ImageIcon("./src/img/xemChucVu.png"), JLabel.CENTER);
        lbXemChucVu.setBounds(400, 330, 200, 50);
        lbXemChucVu.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        pnDisplay.add(lbXemChucVu);

        lbXemBaoHiem = new JLabel(new ImageIcon("./src/img/xemBaoHiem.png"), JLabel.CENTER);
        lbXemBaoHiem.setBounds(610, 330, 200, 50);
        lbXemBaoHiem.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        pnDisplay.add(lbXemBaoHiem);

        pnFind();
        pnDisplay.add(pnFind);
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();
        pnOption.setLayout(null);
        pnOption.setBackground(Color.pink);
        pnOption.setPreferredSize(new Dimension(220, 480));
        lbAdd = new JLabel(new ImageIcon("./src/img/add.png"), JLabel.CENTER);
        lbEdit = new JLabel(new ImageIcon("./src/img/edit.png"), JLabel.CENTER);
        lbRemove = new JLabel(new ImageIcon("./src/img/remove.png"), JLabel.CENTER);
        lbXuatExcel = new JLabel(new ImageIcon("./src/img/xuatExcel.png"), JLabel.CENTER);
        lbNhapExcel = new JLabel(new ImageIcon("./src/img/nhapExcel.png"), JLabel.CENTER);

        lbAdd.setBounds(0, 40, 200, 50);
        lbEdit.setBounds(0, 120, 200, 50);
        lbRemove.setBounds(0, 200, 200, 50);
        lbXuatExcel.setBounds(0, 290, 200, 50);
        lbNhapExcel.setBounds(0, 370, 200, 50);

        pnOption.add(lbAdd);
        pnOption.add(lbEdit);
        pnOption.add(lbRemove);
        pnOption.add(lbXuatExcel);
        pnOption.add(lbNhapExcel);
        lbAdd.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbEdit.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbRemove.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbXuatExcel.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        lbNhapExcel.setCursor(new Cursor((Cursor.HAND_CURSOR)));

        btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(20, 150, 155, 50);
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(20, 250, 155, 50);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnOption.add(btnConfirm);
        pnOption.add(btnBack);

        return pnOption;
    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel();
        pnFind.setLayout(null);
        pnFind.setBounds(280, 400, 350, 30);
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã nhân viên");
        cmbChoice.addItem("Họ tên");
        cmbChoice.addItem("Email");
        cmbChoice.addItem("Số điện thoại");
        cmbChoice.addItem("Ngày sinh");
        cmbChoice.addItem("Địa chỉ");
        cmbChoice.addItem("Giới tính");
        cmbChoice.addItem("CMND");
        cmbChoice.addItem("Tên tài khoản");
        cmbChoice.addItem("Mã bằng cấp");
        cmbChoice.addItem("Mã HĐLĐ");
        cmbChoice.addItem("Mã phòng ban");
        cmbChoice.setBounds(0, 0, 120, 30);

        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(120, 0, 180, 30);
//        txtSearch.setPreferredSize(new Dimension(230, 30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/img/search_24px.png"));
        searchIcon.setBounds(new Rectangle(300, -10, 50, 50));
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
        pnTable.setPreferredSize(new Dimension(1090, 250));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã nhân viên");
        header.add("Họ tên");
        header.add("Email");
        header.add("Số điện thoại");
        header.add("Ngày sinh");
        header.add("Địa chỉ");
        header.add("Giới tính");
        header.add("CMND");
        header.add("Tên tài khoản");
        header.add("Mã bằng cấp");
        header.add("Mã HĐLĐ");
        header.add("Mã phòng ban");
        tbModel = new DefaultTableModel(header, 11);
        tbNhanVien = new JTable(tbModel);
        rowSorter = new TableRowSorter<TableModel>(tbModel);
        tbNhanVien.setRowSorter(rowSorter);
        listNhanVien(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbNhanVien.setFocusable(false);
        tbNhanVien.setIntercellSpacing(new Dimension(0, 0));
        tbNhanVien.setRowHeight(30);
        tbNhanVien.getTableHeader().setOpaque(false);
        tbNhanVien.setFillsViewportHeight(true);
        tbNhanVien.getTableHeader().setBackground(new Color(232, 57, 99));
        tbNhanVien.getTableHeader().setForeground(Color.WHITE);
        tbNhanVien.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbNhanVien);
        scroll.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 230, 250));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtNhanVienLeft[0].setEditable(true);
        for (int i = 0; i < txtNhanVienLeft.length; i++) {
            if (i == 3) {
                picker.setDate(null);
            } else {
                txtNhanVienLeft[i].setText("");
            }
        }
        for (int i = 0; i < txtNhanVienRight.length; i++) {
            txtNhanVienRight[i].setText("");
        }
        for (int i = 0; i < txtNhanVienCenter.length; i++) {
            txtNhanVienCenter[i].setText("");
        }

    }

    public void outModel(DefaultTableModel model, ArrayList<nhanVienDTO> nv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (nhanVienDTO n : nv) {
            data = new Vector();
            data.add(n.getManhanvien());
            data.add(n.getHoten());
            data.add(n.getEmail());
            data.add("0" + n.getSodienthoai());
            data.add(n.getNgaysinh());
            data.add(n.getDiachi());
            data.add(n.getGioitinh());
            data.add(n.getCmnd());
            data.add(n.getTentaikhoan());
            data.add(n.getMabangcap());
            data.add(n.getMahdld());
            data.add(n.getMaphongban());
            model.addRow(data);
        }
        tbNhanVien.setModel(model);
    }

    public void listNhanVien() // Chép ArrayList lên table
    {
        if (nvBUS.getList() == null) {
            nvBUS.listNhanVien();
        }
        ArrayList<nhanVienDTO> nv = nvBUS.getList();
        outModel(tbModel, nv);
    }

    public boolean validateNhanVien(String manhanvien, String hoten, String email, String sodienthoai, String ngaysinh, String diachi, String gioitinh, String cmnd, String mataikhoan, String mabangcap, String mahdld) {
        boolean validate = true;

        if (manhanvien.equals("") || hoten.equals("") || email.equals("") || sodienthoai.equals("") || ngaysinh.equals("") || diachi.equals("") || gioitinh.equals("") || cmnd.equals("") || mataikhoan.equals("") || mabangcap.equals("") || mahdld.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            validate = false;
            return false;
        }
        if (check.check_Email(email)) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng!");
            validate = false;
            return false;
        }
        if (check.check_Phone(sodienthoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng!");
            validate = false;
            return false;
        }
        if (check.check_CMND(cmnd)) {
            JOptionPane.showMessageDialog(null, "Chứng minh nhân dân không đúng định dạng!");
            validate = false;
            return false;
        }
        return validate;
    }

}
