/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.luongBUS;
import BUS.nhanVienBUS;
import BUS.thongKeBUS;
import DTO.luongDTO;
import DTO.nhanVienDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
//import javafx.scene.control.ToggleButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Shadow
 */
public final class ThongKeGUI extends JPanel implements ActionListener, ItemListener, ChangeListener {

    private JPanel pnTableLuong;
    private luongBUS lBUS = new luongBUS();
    private nhanVienBUS nvBUS = new nhanVienBUS();
    private JScrollPane scrollViewTable;
    private JTable tbl;
    private DefaultTableModel model;
    private JPanel paneTime = new JPanel();
    private JPanel paneTimeNV = new JPanel();
    private JPanel form;
    private JRadioButton ckMaL, ckMaNV, ckCaoNhat, ckThapNhat;
    private JLabel lbMa = new JLabel();
    private JTextField txtMa = new JTextField();
    private JTextArea viewStatistic;
    private JButton btnStatistic;
    private int DEFALUT_WIDTH;
    private JLabel lbTop, lbTrongThang;
    private JButton btnSuggest;
    private JScrollPane scrollViewALL;
    JXDatePicker picker1, picker2, picker3;
    private int top;
    private String rank;
    JComboBox cbTop;
    ButtonGroup btnGrRank;

    public ThongKeGUI(int width) {
        lBUS.listLuong();
        nvBUS.listNhanVien();
        DEFALUT_WIDTH = width;
        init();
    }

    public void init() {
        setLayout(null);
        setBackground(Color.pink);
        setBounds(new Rectangle(20, 0, this.DEFALUT_WIDTH - 220, 680));
        Font font0 = new Font("Segoe UI", Font.PLAIN, 20);
        Font font1 = new Font("Segoe UI", Font.BOLD, 13);
        /**
         * ************ PHẦN KIỄM KÊ ****************************************
         */
        JPanel control = new JPanel(null);
        control.setBackground(null);
        control.setBounds(new Rectangle(0, 0, this.DEFALUT_WIDTH - 220, 280));

        JPanel controlAll = new JPanel(new FlowLayout(1, 40, 0));
        controlAll.setBounds(new Rectangle(0, 50, DEFALUT_WIDTH - 220, 70));
        controlAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // CHỌN MÃ CẦN THỐNG KÊ
        ButtonGroup id = new ButtonGroup();
        ckMaNV = new JRadioButton("Nhân viên");
        ckMaNV.addItemListener(this);
        ckMaNV.setFont(font0);

        id.add(ckMaNV);
        ckMaL = new JRadioButton("Lương");
        ckMaL.addItemListener(this);
        ckMaL.setFont(font0);
        id.add(ckMaL);

        JLabel lbId = new JLabel("Chọn loại thông kê");
        lbId.setFont(font1);
        controlAll.add(lbId);
        controlAll.add(ckMaNV);
        controlAll.add(ckMaL);
        controlAll.setBackground(Color.pink);
        control.add(controlAll);

        //*********************** Panel điền thông tin ***********************//
        form = new JPanel(null);
        form.setBackground(null);
        form.setBounds(new Rectangle(350, 120, this.DEFALUT_WIDTH - 220, 400));

        lbMa.setBounds(new Rectangle(0, 0, 100, 30));
        txtMa.setBounds(new Rectangle(110, 0, 230, 30));
        txtMa.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnStatistic.doClick();
                }
            }
        });
        btnSuggest = new JButton("...");
        btnSuggest.setBounds(new Rectangle(340, 0, 30, 30));
        btnSuggest.setVisible(false);
        btnSuggest.addActionListener(this);

        form.add(lbMa);
        form.add(txtMa);
        form.add(btnSuggest);

        /**
         * ************** CHỌN TIME *******************************
         */
        paneTime = new JPanel(null);
        paneTime.setBackground(Color.pink);
        paneTime.setBounds(new Rectangle(0, 0, DEFALUT_WIDTH - 220, 70));

        // FROM
//        LƯƠNG
        lbTop = new JLabel("Top");
        lbTop.setBounds(new Rectangle(0, 10, 100, 30));
        String top[] = new String[5];
        for (int i = 0; i < 5; i++) {
            top[i] = String.valueOf(i + 1);
        }
        cbTop = new JComboBox(top);
        cbTop.setBounds(90, 10, 50, 40);
        btnGrRank = new ButtonGroup();
        JPanel selectRank = new JPanel(new FlowLayout(1, 40, 0));
        selectRank.setBackground(Color.pink);
        selectRank.setBounds(new Rectangle(50, 10, 500, 70));
        ckCaoNhat = new JRadioButton("Cao nhất");
        ckCaoNhat.addItemListener(this);
        ckCaoNhat.setFont(font0);
        ckCaoNhat.setSelected(true);
        btnGrRank.add(ckCaoNhat);
        ckThapNhat = new JRadioButton("Thấp nhất");
        ckThapNhat.addItemListener(this);
        ckThapNhat.setFont(font0);
        btnGrRank.add(ckThapNhat);
        selectRank.add(ckCaoNhat);
        selectRank.add(ckThapNhat);

        paneTime.add(lbTop);
        paneTime.add(cbTop);
        paneTime.add(selectRank);
        paneTime.setVisible(false);
//       NHÂN VIÊN
        paneTimeNV = new JPanel(null);
        paneTimeNV.setBackground(Color.pink);
        paneTimeNV.setBounds(new Rectangle(0, 40, DEFALUT_WIDTH - 220, 60));
        lbTrongThang = new JLabel("Trong tháng");
        lbTrongThang.setBounds(new Rectangle(0, 0, 100, 30));
        picker3 = new JXDatePicker();
        picker3.setFormats(new SimpleDateFormat("MM/yyyy"));
        picker3.setBounds(110, 0, 260, 30);
        paneTimeNV.add(lbTrongThang);
        paneTimeNV.add(picker3);
        paneTimeNV.setVisible(false);

        form.add(paneTime);
        form.add(paneTimeNV);
        /**
         * ********************************************************
         */

        /**
         * ********************************************************
         */
        btnStatistic = new JButton("Thống kê");
        btnStatistic.setFont(new Font("Segoe UI", Font.PLAIN, 15) {
        });
        btnStatistic.setBounds(new Rectangle(120, 100, 130, 40));
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStaticticAction(e);
            }
        });

        form.add(btnStatistic);

        control.add(form);
        add(control);
        /**
         * ******************************************************************
         */

        /**
         * ************* PHẦN HIỆN THÔNG TIN ********************************
         */
        viewStatistic = new JTextArea();
        viewStatistic.setEditable(false);
        viewStatistic.setFont(font0);
        scrollViewALL = new JScrollPane(viewStatistic);
        scrollViewALL.setBounds(new Rectangle(0, 280, DEFALUT_WIDTH - 220, 480));
        add(scrollViewALL);
        /**
         * ******************************************************************
         */

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1080, 730);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ThongKeGUI(1300));
        frame.setVisible(true);
    }

    public void btnStaticticAction(ActionEvent e) {
        thongKeBUS tk = new thongKeBUS();
        String ma = txtMa.getText();
        System.out.println(ma);
        Object obj = null;
        if (ckMaNV.isSelected()) {
            obj = new nhanVienDTO();
            obj = nvBUS.getNV(ma);
            if (obj == null) {
                JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên!");
                return;
            }
        }

        String trongthang = "";
        // THÔNG KÊ THEO NGÀY

        if (picker3.getDate() != null) {
            trongthang = picker3.getEditor().getText();
        }

        String result = "";

        top = Integer.parseInt(cbTop.getSelectedItem().toString());
        if (ckCaoNhat.isSelected()) {
            rank = "caonhat";
        } else {
            rank = "thapnhat";
        }
        if (ckMaL.isSelected()) {
            viewStatistic.setVisible(false);
            pnTableLuong(top, rank);
            add(pnTableLuong);
        } else if (ckMaNV.isSelected()) {
            System.out.println(picker3.getDate());
            if (picker3 == null || picker3.getEditor().getText().equals("")) {
                result = tk.StatisticNV(ma);
            } else {
                result = tk.StatisticNV(ma, trongthang);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
        if (picker3.getDate() == null) {
            viewStatistic.setText(outStatistic(obj, null, result));
        } else if (picker3.getDate() != null) {
            viewStatistic.setText(outStatistic(obj, trongthang, result));
        }

    }

    public String outStatistic(Object obj, String trongthang, String result) {
        String s = "";
        if (trongthang != null) {
            s += "Tháng : " + trongthang + "\n";
            s += "--------------------------------------------- \n";
        }

        if (ckMaL.isSelected()) {
//            SanPhamDTO sp = (SanPhamDTO) obj;
//            s += "Sản phẩm :" + sp.getMaSP() + "\t";
//            s += "Tên : " + sp.getTenSP() + "\n";
//            s += result;
        } else if (ckMaNV.isSelected()) {
            nhanVienDTO nv = (nhanVienDTO) obj;
            s += "Mã nhân viên : " + nv.getManhanvien() + "\n";
            s += "Họ và tên : " + nv.getHoten() + "\n";
            s += "Phòng ban : " + nv.getMaphongban() + "\n";
            s += result;
        }
        return s;
    }

    public JPanel pnTableLuong(int top, String rank) {
        pnTableLuong = new JPanel();
        pnTableLuong.setLayout(null);
        pnTableLuong.setBounds(new Rectangle(0, 280, DEFALUT_WIDTH - 220, 480));
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
        model = new DefaultTableModel(header, 7);
        tbl = new JTable(model);
        listLuong(top, rank); //Đọc từ database lên table 
//TẠO TABLE
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0, 0));
        tbl.setRowHeight(30);
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(232, 57, 99));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(0, 0, DEFALUT_WIDTH - 220, 480));
        scroll.setBackground(null);

        pnTableLuong.add(scroll);
        return pnTableLuong;
    }

    public void outModelLuong(DefaultTableModel modelLuong, ArrayList<luongDTO> luong) // Xuất ra Table từ ArrayList
    {
        Vector data;
        modelLuong.setRowCount(0);
        for (luongDTO n : luong) {
            data = new Vector();
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
        tbl.setModel(modelLuong);
    }

    public void listLuong(int top, String rank) // Chép ArrayList lên table
    {
        thongKeBUS tkBUS = new thongKeBUS();
        ArrayList<luongDTO> l = tkBUS.StatisticL(top, rank);
        outModelLuong(model, l);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(btnSuggest)) {
            if (ckMaL.isSelected()) {


            } else if (ckMaNV.isSelected()) {
                Suggestmanv nv = new Suggestmanv();
                String s = nv.getTextFieldContent();
                txtMa.setText(s);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        // Chọn Control MĂ
        if (ckMaNV.isSelected()) {
            paneTime.setVisible(false);
            paneTimeNV.setVisible(true);
            lbMa.setVisible(true);
            txtMa.setVisible(true);
            btnSuggest.setVisible(true);
            lbMa.setText("Mã nhân viên");
        } else if (ckMaL.isSelected()) {
            paneTime.setVisible(true);
            paneTimeNV.setVisible(false);
            lbMa.setVisible(false);
            txtMa.setVisible(false);
            btnSuggest.setVisible(false);
        } else {
            lbMa.setVisible(false);
            txtMa.setVisible(false);
            paneTimeNV.setVisible(false);
            paneTime.setVisible(false);
        }
    }

    // ChangeListener
    @Override
    public void stateChanged(ChangeEvent e) {
        txtMa.setVisible(false);

    }

}
