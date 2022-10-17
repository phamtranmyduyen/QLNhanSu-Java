package GUI;

import BUS.bangCapBUS;
import DTO.bangCapDTO;
import DTO.bangCapDTO;
import GUI.model.headerSuggest;
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

public class SuggestMabangcap extends JDialog {
    private bangCapBUS bcBUS = new bangCapBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbbangCap;
    private DefaultTableModel model;
    JTextField[] txtbangCap;
    JLabel[] lbbangCap;
    Font font;   

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestMabangcap() {
        setModal(true);
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 14);

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 730));
 /**
         * ********** PHẦN HEADER ************************************
         */
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH BẰNG CẤP",1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbbangCap.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbbangCap.getSelectedRow();
                txtbangCap[0].setText(tbbangCap.getModel().getValueAt(i, 0).toString());
                txtbangCap[1].setText(tbbangCap.getModel().getValueAt(i, 1).toString());
                txtbangCap[2].setText(tbbangCap.getModel().getValueAt(i, 2).toString());
                txtbangCap[3].setText(tbbangCap.getModel().getValueAt(i, 3).toString());
            }
        });
        this.add(header, BorderLayout.NORTH);
        this.add(pnDisplay, BorderLayout.CENTER);
        this.add(pnTable, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
      
        pnDisplay.setLayout(new BorderLayout());
        pnDisplay.setPreferredSize(new Dimension(1300, 300));        
        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, 300-70));
        String[] arrbangCap = {"Mã bằng cấp", "Tên bằng cấp", "Ngày bắt đầu hiệu lực", "Ngày hết hạn hiệu lực"};
        txtbangCap = new JTextField[arrbangCap.length];
        lbbangCap = new JLabel[arrbangCap.length];
        int xLb = 390, yLb = 20;
        int xTxt = 600, yTxt = 20;
        for (int i = 0; i < arrbangCap.length; i++) {
            lbbangCap[i] = new JLabel(arrbangCap[i]);
            lbbangCap[i].setBounds(xLb, yLb, 200, 30);
            lbbangCap[i].setHorizontalAlignment(JLabel.CENTER);
            lbbangCap[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbbangCap[i].setName("lb" + i);
            pnDisplayTop.add(lbbangCap[i]);
            yLb = yLb + 50;
            txtbangCap[i] = new JTextField();
            txtbangCap[i].setName("txt" + i);
            txtbangCap[i].setBounds(xTxt, yTxt, 240, 30);
            pnDisplayTop.add(txtbangCap[i]);
            yTxt = yTxt + 50;
        }
        pnOption();
        pnDisplay.add(pnDisplayTop, BorderLayout.NORTH);
        pnDisplay.add(pnOption, BorderLayout.CENTER);
        return pnDisplay;
    }

    public JPanel pnOption() {
        pnOption = new JPanel();        
        pnOption.setLayout(new FlowLayout());
        pnOption.setPreferredSize(new Dimension(1300, 70));
        JLabel btnConfirm = new JLabel(new ImageIcon("./src/img/done.png"));
        btnConfirm.setPreferredSize(new Dimension(155, 70));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        JLabel btnBack = new JLabel(new ImageIcon("./src/img/back.png"));
        btnBack.setPreferredSize(new Dimension(155, 70));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        pnOption.add(btnConfirm);
        pnOption.add(btnBack);               
        pnOption.setBackground(Color.pink);
        return pnOption;
    }

    public JPanel pnTable() {
        pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setPreferredSize(new Dimension(1300, 360));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã bằng cấp");
        header.add("Tên bằng cấp");
        header.add("Ngày bắt đầu hiệu lực");
        header.add("Ngày hết hạn hiệu lực");
        model = new DefaultTableModel(header, 4);
        tbbangCap = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbbangCap.setRowSorter(rowSorter);
        listBangCap(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Chỉnh width các cột 
//        tbTaiKhoan.getColumnModel().getColumn(0).setPreferredWidth(40);
//        tbTaiKhoan.getColumnModel().getColumn(1).setPreferredWidth(40);
        // Custom table
        tbbangCap.setFocusable(false);
        tbbangCap.setIntercellSpacing(new Dimension(0, 0));
        tbbangCap.setRowHeight(30);
        tbbangCap.getTableHeader().setOpaque(false);
        tbbangCap.setFillsViewportHeight(true);
        tbbangCap.getTableHeader().setBackground(new Color(232, 57, 99));
        tbbangCap.getTableHeader().setForeground(Color.WHITE);
        tbbangCap.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbbangCap);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
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
        tbbangCap.setModel(model);
    }

    public void listBangCap() // Chép ArrayList lên table
    {
        if (bcBUS.getList() == null) {
            bcBUS.listBangCap();
        }
        ArrayList<bangCapDTO> bc = bcBUS.getList();
        outModel(model, bc);
    }
    public String getTextFieldContent() {
        return txtbangCap[0].getText();
    }
}

