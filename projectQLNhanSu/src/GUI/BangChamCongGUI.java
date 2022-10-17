/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.bangChamCongBUS;
import BUS.chiTietBangChamCongBUS;
import BUS.nhanVienBUS;
import BUS.printBCC;
import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
import DTO.nhanVienDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class BangChamCongGUI extends JPanel {

    private bangChamCongBUS bccBUS = new bangChamCongBUS();
    private nhanVienBUS nvBUS = new nhanVienBUS();
    Font font;
    private int DEFALUT_WIDTH;
    private JPanel pnTableDsNhanVien, pnOptionTao, pnOptionXem, pnFind, pnTableBangChamCong, pnSouth, pnNorth;
    private JTable tbBangChamCong, tbDsNhanVien;
    private DefaultTableModel modelBangChamCong, modelDsNhanVien;
    private JLabel lbXem, lbThem, lbChamCong, lbXoa, lbIn, lbHeaderBangChamCong, lbHeaderDsNhanVien;
    TableRowSorter<TableModel> rowSorterBangChamCong, rowSorterDsNhanVien;
    String manhanvien, mabangchamcong, thoigian;
    float sogiotre, sogiolamthem;
    private JTextField txtNhanVien;
    private JLabel lbNhanVien, lbSuggestMaNV;

    public BangChamCongGUI(int width) {
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {
        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(10, 10, this.DEFALUT_WIDTH - 200, 680));
        font = new Font("Segoe UI", Font.BOLD, 14);
        pnTableDsNhanVien();
//        DANH SÁCH NHÂN VIÊN
        pnTableDsNhanVien();
        pnOptionTao();
        pnNorth = new JPanel();
        pnNorth.setLayout(new BorderLayout(0,10));
        pnNorth.setBackground(Color.pink);
        pnNorth.setPreferredSize(new Dimension(1090, 150));
        pnNorth.add(pnTableDsNhanVien, BorderLayout.CENTER);
        pnNorth.add(pnOptionTao, BorderLayout.NORTH);
//        DANH SÁCH BẢNG CHẤM CÔNG
        pnTableBangChamCong();
        pnOptionXem();
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        pnSouth.setBackground(Color.pink);
        pnSouth.setPreferredSize(new Dimension(1090, 550));
        pnSouth.add(pnTableBangChamCong, BorderLayout.NORTH);
        pnSouth.add(pnOptionXem, BorderLayout.CENTER);

        tbBangChamCong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbBangChamCong.getSelectedRow();
                mabangchamcong = tbBangChamCong.getModel().getValueAt(i, 0).toString();
                manhanvien = tbBangChamCong.getModel().getValueAt(i, 1).toString();
                thoigian = tbBangChamCong.getModel().getValueAt(i, 2).toString();
                sogiotre = Float.parseFloat(tbBangChamCong.getModel().getValueAt(i, 3).toString());
                sogiolamthem = Float.parseFloat(tbBangChamCong.getModel().getValueAt(i, 4).toString());

            }
        });
//        ADD
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnSouth, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JPanel pnOptionTao() {
        pnOptionTao = new JPanel();
        pnOptionTao.setLayout(new FlowLayout(1, 0, 0));
        pnOptionTao.setPreferredSize(new Dimension(350, 55));
        lbThem = new JLabel(new ImageIcon("./src/img/taoBCC.png"));
        lbThem.setPreferredSize(new Dimension(160, 55));
        lbThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionTao.add(lbThem);
        lbChamCong = new JLabel(new ImageIcon("./src/img/chamcong.png"));
        lbChamCong.setPreferredSize(new Dimension(160, 55));
        lbChamCong.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionTao.add(lbChamCong);
        pnOptionTao.setBackground(null);
        lbThem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                taoBangChamCongDIALOG taoBCC = new taoBangChamCongDIALOG();
            }
        });
        lbChamCong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                chamCongDIALOG cc = new chamCongDIALOG();
            }
        });
        return pnOptionTao;

    }

    public JPanel pnOptionXem() {
        pnOptionXem = new JPanel();
        pnOptionXem.setLayout(new FlowLayout(1, 10, 0));
        pnOptionXem.setPreferredSize(new Dimension(500, 55));
        lbIn = new JLabel(new ImageIcon("./src/img/in.png"));
        lbIn.setPreferredSize(new Dimension(160, 55));
        lbIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionXem.add(lbIn);
        lbIn.setBackground(Color.white);
        lbIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (mabangchamcong == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng chấm công cần in!");
                    return;
                }
                bangChamCongDTO bcc = new bangChamCongDTO(mabangchamcong, manhanvien, thoigian, sogiotre, sogiolamthem);
                chiTietBangChamCongBUS ctbccBUS = new chiTietBangChamCongBUS();
                ArrayList<chiTietBangChamCongDTO> ctbcc = ctbccBUS.getList(mabangchamcong);
                printBCC bill = new printBCC(bcc, ctbcc);
                bill.print();
            }
        });
        lbXem = new JLabel(new ImageIcon("./src/img/xemBCC.png"));
        lbXem.setPreferredSize(new Dimension(160, 55));
        lbXem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionXem.add(lbXem);
        lbXem.setBackground(Color.white);
        lbXem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (mabangchamcong == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng chấm công cần xem!");
                    return;
                }
                chiTietBangChamCongGUI ctbcc = new chiTietBangChamCongGUI(mabangchamcong, manhanvien, thoigian);
            }
        });

        return pnOptionXem;

    }

    public JPanel pnFind() {
        /**
         * ********************* SORT TABLE ****************************
         */
        pnFind = new JPanel();
        pnFind.setLayout(null);
        pnFind.setBounds(430, 340, 350, 30);
        pnFind.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbChoice.addItem("Mã bảng chấm công");
        cmbChoice.addItem("Mã nhân viên");
        cmbChoice.addItem("Thời gian");
        cmbChoice.addItem("Số giờ trễ");
        cmbChoice.addItem("Số giờ làm thêm");

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
                    rowSorterBangChamCong.setRowFilter(null);
                } else {
                    rowSorterBangChamCong.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();

                if (text.trim().length() == 0) {
                    rowSorterBangChamCong.setRowFilter(null);
                } else {
                    rowSorterBangChamCong.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        return pnFind;

    }

    public JPanel pnTableBangChamCong() {
        pnTableBangChamCong = new JPanel();
        pnTableBangChamCong.setLayout(null);
        pnTableBangChamCong.setPreferredSize(new Dimension(1090, 490));

        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã bảng chấm công");
        header.add("Mã nhân viên");
        header.add("Thời gian");
        header.add("Số giờ trễ");
        header.add("Số giờ làm thêm");
        modelBangChamCong = new DefaultTableModel(header, 5);
        tbBangChamCong = new JTable(modelBangChamCong);
        rowSorterBangChamCong = new TableRowSorter<TableModel>(modelBangChamCong);
        tbBangChamCong.setRowSorter(rowSorterBangChamCong);
        listBangChamCong(manhanvien); //Đọc từ database lên table 
//TẠO TABLE
        tbBangChamCong.setFocusable(false);
        tbBangChamCong.setIntercellSpacing(new Dimension(0, 0));
        tbBangChamCong.setRowHeight(30);
        tbBangChamCong.getTableHeader().setOpaque(false);
        tbBangChamCong.setFillsViewportHeight(true);
        tbBangChamCong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbBangChamCong.getTableHeader().setForeground(Color.WHITE);
        tbBangChamCong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbBangChamCong);
        scroll.setBounds(new Rectangle(0, 30, this.DEFALUT_WIDTH - 230, 490));
        scroll.setBackground(null);

        pnTableBangChamCong.add(scroll);
        return pnTableBangChamCong;
    }

    public JPanel pnTableDsNhanVien() {
        pnTableDsNhanVien = new JPanel();
        pnTableDsNhanVien.setLayout(null);
        pnTableDsNhanVien.setBackground(Color.pink);
        pnTableDsNhanVien.setPreferredSize(new Dimension(1090, 90));
        lbHeaderBangChamCong = new JLabel("DANH SÁCH BẢNG CHẤM CÔNG THEO NHÂN VIÊN", JLabel.CENTER);
        lbHeaderBangChamCong.setFont(font);
        lbHeaderBangChamCong.setBounds(0, 0, 1090, 30);
        txtNhanVien = new JTextField();
        txtNhanVien.setBounds(500, 40, 200, 30);
        pnTableDsNhanVien.add(txtNhanVien);
        lbNhanVien = new JLabel("Mã nhân viên");
        lbNhanVien.setBounds(380, 40, 180, 30);
        pnTableDsNhanVien.add(lbNhanVien);
        lbSuggestMaNV = new JLabel("...", JLabel.CENTER);
        lbSuggestMaNV.setBounds(700, 40, 30, 30);
        lbSuggestMaNV.setOpaque(true);
        lbSuggestMaNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbSuggestMaNV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Suggestmanv nv = new Suggestmanv();
                String s = nv.getTextFieldContent();
                txtNhanVien.setText(s);
                listBangChamCong(txtNhanVien.getText());
            }
        });

        pnTableDsNhanVien.add(lbSuggestMaNV);
        pnTableDsNhanVien.add(lbHeaderBangChamCong);
        return pnTableDsNhanVien;
    }

    public void outModelBangChamCong(DefaultTableModel modelBangChamCong, ArrayList<bangChamCongDTO> bcc) // Xuất ra Table từ ArrayList
    {
        Vector data;
        modelBangChamCong.setRowCount(0);
        for (bangChamCongDTO n : bcc) {
            System.out.println("OUTMODEL:" + n.getMabangchamcong());
            data = new Vector();
            data.add(n.getMabangchamcong());
            data.add(n.getManhanvien());
            data.add(n.getThoigian());
            data.add(n.getSogiotre());
            data.add(n.getSogiolamthem());
            modelBangChamCong.addRow(data);
        }
        tbBangChamCong.setModel(modelBangChamCong);
    }

    public void listBangChamCong(String manhanvien) // Chép ArrayList lên table
    {
        if (bccBUS.getList(manhanvien) == null) {
            bccBUS.listBangChamCong(manhanvien);
        }
        ArrayList<bangChamCongDTO> bcc = bccBUS.getList(manhanvien);

        for (int i = 0; i < bcc.size(); i++) {
            SetBangChamCong(bcc.get(i).getMabangchamcong(), bcc.get(i).getManhanvien(), bcc.get(i).getThoigian());
        }
        outModelBangChamCong(modelBangChamCong, bcc);
    }

    public void SetBangChamCong(String mabangchamcongSET, String manhanvienSET, String thoigianSET) {
        float sogiotre = bccBUS.soGioTre(manhanvienSET, null, thoigianSET);
        float sogiolamthem = bccBUS.soGioLamThem(manhanvienSET, null, thoigianSET);
        bangChamCongDTO bcc = new bangChamCongDTO(mabangchamcongSET, manhanvienSET, thoigianSET, sogiotre, sogiolamthem);
        bccBUS.setBangChamCong(bcc);
    }

}
