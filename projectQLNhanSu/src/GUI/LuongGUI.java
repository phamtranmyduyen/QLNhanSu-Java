/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.bangChamCongBUS;
import BUS.heSoLuongBUS;
import BUS.hopDongLaoDongBUS;
import BUS.luongBUS;
import BUS.luongCanBanBUS;
import BUS.nhanVienBUS;
import BUS.phuCapBUS;
import BUS.thuongBUS;
import DTO.bangChamCongDTO;
import DTO.hopDongLaoDongDTO;
import DTO.luongCanBanDTO;
import DTO.luongDTO;
import DTO.nhanVienDTO;
import DTO.thuongDTO;
import DTO.heSoLuongDTO;
import DTO.phuCapDTO;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
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
public class LuongGUI extends JPanel {

    private luongBUS blBUS = new luongBUS();
    private nhanVienBUS nvBUS = new nhanVienBUS();
    Font font;
    private int DEFALUT_WIDTH;
    private JPanel pnTableDsNhanVien, pnOptionTao, pnOptionXem, pnFind, pnTableLuong, pnSouth, pnNorth;
    private JTable tbLuong, tbDsNhanVien;
    private DefaultTableModel modelLuong, modelDsNhanVien;
    private JLabel lbXem, lbThem, lbSua, lbXemTheoThang, lbXoa, lbHeaderLuong, lbHeaderDsNhanVien;
    TableRowSorter<TableModel> rowSorterLuong, rowSorterDsNhanVien;
    String manhanvien, mabangluong, thoigian, mabangchamcong, tennhanvien;
    float luongcanban, thuong, hesoluong, phucap, sogiotre, sogiolamthem;
    double luongchinhthuc;
    long luongchinhthucLong;
    private JTextField txtNhanVien;
    private JLabel lbNhanVien, lbSuggestMaNV, lbHeaderNhanVien;
    public LuongGUI(int width) {
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
//        DANH SÁCH BẢNG LƯƠNG
        pnTableLuong();
        pnOptionXem();
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        pnSouth.setBackground(Color.pink);
        pnSouth.setPreferredSize(new Dimension(1090, 550));
        pnSouth.add(pnTableLuong, BorderLayout.NORTH);
        pnSouth.add(pnOptionXem, BorderLayout.CENTER);
        tbLuong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbLuong.getSelectedRow();
                mabangluong = tbLuong.getModel().getValueAt(i, 0).toString();
                mabangchamcong = tbLuong.getModel().getValueAt(i, 1).toString();
                thoigian = tbLuong.getModel().getValueAt(i, 2).toString();
                manhanvien = tbLuong.getModel().getValueAt(i, 3).toString();
                tennhanvien = tbLuong.getModel().getValueAt(i, 4).toString();
                sogiotre = Float.parseFloat(tbLuong.getModel().getValueAt(i, 5).toString());
                sogiolamthem = Float.parseFloat(tbLuong.getModel().getValueAt(i, 6).toString());
                luongcanban = Float.parseFloat(tbLuong.getModel().getValueAt(i, 7).toString());
                thuong = Float.parseFloat(tbLuong.getModel().getValueAt(i, 8).toString());
                hesoluong = Float.parseFloat(tbLuong.getModel().getValueAt(i, 9).toString());
                phucap = Float.parseFloat(tbLuong.getModel().getValueAt(i, 10).toString());
                luongchinhthuc = Float.parseFloat(tbLuong.getModel().getValueAt(i, 11).toString());
            }
        });
//        ADD
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnSouth, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JPanel pnOptionTao() {
        pnOptionTao = new JPanel();
        pnOptionTao.setLayout(new FlowLayout(1, 20, 0));
        pnOptionTao.setPreferredSize(new Dimension(360, 55));
        lbThem = new JLabel(new ImageIcon("./src/img/taoBangLuong.png"));
        lbThem.setPreferredSize(new Dimension(160, 55));
        lbThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionTao.add(lbThem);
        lbXemTheoThang = new JLabel(new ImageIcon("./src/img/xemLuongTheoThang.png"));
        lbXemTheoThang.setPreferredSize(new Dimension(160, 55));
        lbXemTheoThang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionTao.add(lbXemTheoThang);
        pnOptionTao.setBackground(null);
        lbThem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                blBUS.listLuong();
                blBUS.deleteLuong();
                bangChamCongBUS bccBUS = new bangChamCongBUS();
                if (bccBUS.getList() == null) {
                    bccBUS.listBangChamCong();
                }
                ArrayList<bangChamCongDTO> bcc = bccBUS.getList();
                for (bangChamCongDTO dataBCC : bcc) {
                    mabangluong = blBUS.maluong();
                    thoigian = dataBCC.getThoigian();
                    sogiotre = dataBCC.getSogiotre();
                    sogiolamthem = dataBCC.getSogiolamthem();
                    hopDongLaoDongBUS hdldBUS = new hopDongLaoDongBUS();
                    ArrayList<hopDongLaoDongDTO> hdld = hdldBUS.getList(dataBCC.getManhanvien());

                    for (hopDongLaoDongDTO dataHDLD : hdld) {
//                        LẤY LƯƠNG CĂN BẢN
                        luongCanBanBUS lcbBUS = new luongCanBanBUS();
                        lcbBUS.listLuongCanBan();
                        luongCanBanDTO luongcbDTO = lcbBUS.get(dataHDLD.getMaluongcanban());
                        luongcanban = luongcbDTO.getLuongcanban();
//                        LẤY TIỀN THƯỞNG
                        thuongBUS tBUS = new thuongBUS();
                        tBUS.listThuong();
                        thuongDTO thuongDTO = tBUS.get(dataHDLD.getMathuong());
                        thuong = thuongDTO.getTienthuong();
//                        LẤY HỆ SỐ LƯƠNG
                        heSoLuongBUS hslBUS = new heSoLuongBUS();
                        hslBUS.listHeSoLuong();
                        heSoLuongDTO hslDTO = hslBUS.get(dataHDLD.getMahesoluong());
                        hesoluong = hslDTO.getHesoluong();
//                        LẤY TIỀN PHỤ CẤP
                        phuCapBUS pcBUS = new phuCapBUS();
                        pcBUS.listPhuCap();
                        phuCapDTO pcDTO = pcBUS.get(dataHDLD.getMaphucap());
                        phucap = pcDTO.getTienphucap();
                    }
//                    LẤY TÊN NHÂN VIÊN
                    nhanVienBUS nvBUS = new nhanVienBUS();
                    nvBUS.listNhanVien();
                    nhanVienDTO nvDTO = nvBUS.getNV(dataBCC.getManhanvien());
                    tennhanvien = nvDTO.getHoten();
                    luongchinhthuc = (luongcanban * hesoluong) + thuong + phucap - (10 * sogiotre) + (30 * sogiolamthem);
                    luongchinhthucLong = (new Double(luongchinhthuc)).longValue();
                    luongDTO luongDTO = new luongDTO(mabangluong, dataBCC.getMabangchamcong(), thoigian, manhanvien, tennhanvien, sogiotre, sogiolamthem, luongcanban, thuong, hesoluong, phucap, luongchinhthuc);

                    blBUS.addLuong(luongDTO);
                }
                JOptionPane.showMessageDialog(null, "ĐÃ CẬP NHẬT");
            }
        });
        lbXemTheoThang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                BangLuongTheoThangGUI bltt = new BangLuongTheoThangGUI();
            }
        });

        return pnOptionTao;

    }

    public JPanel pnOptionXem() {
        pnOptionXem = new JPanel();
        pnOptionXem.setLayout(new FlowLayout(1, 0, 0));
        pnOptionXem.setPreferredSize(new Dimension(160, 55));
        lbXem = new JLabel(new ImageIcon("./src/img/xemBangLuong.png"));
        lbXem.setPreferredSize(new Dimension(160, 55));
        lbXem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnOptionXem.add(lbXem);
        pnOptionXem.setBackground(Color.white);
        pnOptionXem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (mabangluong == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng lương cần xem!");
                }
                xemLuongDIALOG xemluong = new xemLuongDIALOG(mabangluong, mabangchamcong, thoigian, manhanvien, tennhanvien, luongcanban, thuong, hesoluong, phucap, luongchinhthuc, sogiotre, sogiolamthem);
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
        cmbChoice.addItem("Mã lương");
        cmbChoice.addItem("Mã bảng chấm công");
        cmbChoice.addItem("Thời gian");
        cmbChoice.addItem("Mã nhân viên");
        cmbChoice.addItem("Tên nhân viên");

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
                    rowSorterLuong.setRowFilter(null);
                } else {
                    rowSorterLuong.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();

                if (text.trim().length() == 0) {
                    rowSorterLuong.setRowFilter(null);
                } else {
                    rowSorterLuong.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        return pnFind;

    }

    public JPanel pnTableLuong() {
        pnTableLuong = new JPanel();
        pnTableLuong.setLayout(null);
        pnTableLuong.setPreferredSize(new Dimension(1090, 490));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã Lương");
        header.add("Mã bảng chấm công");
        header.add("Thời gian");
        header.add("Mã nhân viên");
        header.add("Tên nhân viên");
        header.add("Số giờ trễ");
        header.add("Số giờ làm thêm");
        header.add("Luơng căn bản");
        header.add("Thưởng");
        header.add("Hệ số lương");
        header.add("Phụ cấp");
        header.add("Lương chính thức");
        modelLuong = new DefaultTableModel(header, 7);
        tbLuong = new JTable(modelLuong);
        rowSorterLuong = new TableRowSorter<TableModel>(modelLuong);
        tbLuong.setRowSorter(rowSorterLuong);
        listLuong(manhanvien); //Đọc từ database lên table 
//TẠO TABLE
        tbLuong.setFocusable(false);
        tbLuong.setIntercellSpacing(new Dimension(0, 0));
        tbLuong.setRowHeight(30);
        tbLuong.getTableHeader().setOpaque(false);
        tbLuong.setFillsViewportHeight(true);
        tbLuong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbLuong.getTableHeader().setForeground(Color.WHITE);
        tbLuong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbLuong);
        scroll.setBounds(new Rectangle(0, 30, this.DEFALUT_WIDTH - 230, 490));
        scroll.setBackground(null);

        pnTableLuong.add(scroll);
        return pnTableLuong;
    }

    public JPanel pnTableDsNhanVien() {
        pnTableDsNhanVien = new JPanel();
        pnTableDsNhanVien.setLayout(null);
        pnTableDsNhanVien.setBackground(Color.pink);
        pnTableDsNhanVien.setPreferredSize(new Dimension(1090, 90));
        lbHeaderNhanVien = new JLabel("DANH SÁCH BẢNG LƯƠNG THEO NHÂN VIÊN", JLabel.CENTER);
        lbHeaderNhanVien.setFont(font);
        lbHeaderNhanVien.setBounds(0, 0, 1090, 30);
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
                listLuong(txtNhanVien.getText());
            }
        });

        pnTableDsNhanVien.add(lbSuggestMaNV);
        pnTableDsNhanVien.add(lbHeaderNhanVien);
        return pnTableDsNhanVien;
    }

    public void outModelLuong(DefaultTableModel modelLuong, ArrayList<luongDTO> bcc) // Xuất ra Table từ ArrayList
    {
        Vector data;
        modelLuong.setRowCount(0);

//        Locale localeVN = new Locale("vi", "VN");
//        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        for (luongDTO n : bcc) {
            data = new Vector();
//            long luongcanbanLong = Long.parseLong(n.getLuongcanban());
//            String luongcanban = currencyVN.format(luongcanbanLong);
            data.add(n.getMaluong());
            data.add(n.getMabangchamcong());
            data.add(n.getThoigian());
            data.add(n.getManhanvien());
            data.add(n.getTennhanvien());
            data.add(n.getSogiotre());
            data.add(n.getSogiolamthem());
            data.add(n.getLuongcanban());
            data.add(n.getThuong());
            data.add(n.getHesoluong());
            data.add(n.getPhucap());
            data.add(n.getLuongchinhthuc());
            modelLuong.addRow(data);
        }
        tbLuong.setModel(modelLuong);
    }

    public void listLuong(String manhanvien) // Chép ArrayList lên table
    {
        if (blBUS.getList(manhanvien) == null) {
            blBUS.listLuong(manhanvien);
        }
        ArrayList<luongDTO> bcc = blBUS.getList(manhanvien);
        outModelLuong(modelLuong, bcc);
    }

    public void outModelDsNhanVien(DefaultTableModel modelDsNhanVien, ArrayList<nhanVienDTO> nv) {
        Vector data;
        modelDsNhanVien.setRowCount(0);
        for (nhanVienDTO n : nv) {
            data = new Vector();
            data.add(n.getManhanvien());
            data.add(n.getHoten());
            modelDsNhanVien.addRow(data);
        }
        tbDsNhanVien.setModel(modelDsNhanVien);
    }

    public void listNhanVien() // Chép ArrayList lên table
    {
        if (nvBUS.getList() == null) {
            nvBUS.listNhanVien();
        }
        ArrayList<nhanVienDTO> nv = nvBUS.getList();
        outModelDsNhanVien(modelDsNhanVien, nv);
    }

}
