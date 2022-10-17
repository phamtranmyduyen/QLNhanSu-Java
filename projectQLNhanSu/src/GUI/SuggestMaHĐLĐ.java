package GUI;

import BUS.hopDongLaoDongBUS;
import DTO.hopDongLaoDongDTO;
import DTO.hopDongLaoDongDTO;
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

public class SuggestMaHĐLĐ extends JDialog {
    private hopDongLaoDongBUS hdldBUS = new hopDongLaoDongBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbhdld;
    private DefaultTableModel model;
    JTextField[] txthdldright, txthdldleft;
    JLabel[] lbhdldleft, lbhdldright;
    Font font;   

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestMaHĐLĐ() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH HỢP ĐỒNG LAO ĐỘNG",1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbhdld.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbhdld.getSelectedRow();
                txthdldleft[0].setText(tbhdld.getModel().getValueAt(i, 0).toString());
                txthdldleft[1].setText(tbhdld.getModel().getValueAt(i, 1).toString());
                txthdldleft[2].setText(tbhdld.getModel().getValueAt(i, 2).toString());
                txthdldleft[3].setText(tbhdld.getModel().getValueAt(i, 3).toString());
                txthdldleft[4].setText(tbhdld.getModel().getValueAt(i, 4).toString());
                txthdldright[0].setText(tbhdld.getModel().getValueAt(i, 0).toString());
                txthdldright[1].setText(tbhdld.getModel().getValueAt(i, 1).toString());
                txthdldright[2].setText(tbhdld.getModel().getValueAt(i, 2).toString());
                txthdldright[3].setText(tbhdld.getModel().getValueAt(i, 3).toString());
                txthdldright[4].setText(tbhdld.getModel().getValueAt(i, 4).toString());
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
//      PnDisplayLEFT
        String[] arrhdld = {"Mã HĐLĐ", "Loại HĐLĐ", "Ngày bắt đầu", "Ngày kết thúc", "Địa điểm làm việc"};
        txthdldleft = new JTextField[arrhdld.length];
        lbhdldleft = new JLabel[arrhdld.length];
        int xLb = 100, yLb = 15;
        int xTxt = 250, yTxt = 15;
        for (int i = 0; i < arrhdld.length; i++) {
            lbhdldleft[i] = new JLabel(arrhdld[i]);
            lbhdldleft[i].setBounds(xLb, yLb, 150, 30);
            lbhdldleft[i].setHorizontalAlignment(JLabel.CENTER);
            lbhdldleft[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbhdldleft[i].setName("lb" + i);
            pnDisplayTop.add(lbhdldleft[i]);
            yLb = yLb + 40;
            txthdldleft[i] = new JTextField();
            txthdldleft[i].setName("txt" + i);
            txthdldleft[i].setBounds(xTxt, yTxt, 230, 30);
            pnDisplayTop.add(txthdldleft[i]);
            yTxt = yTxt + 40;
        }
//       PnDisplayRIGHT
        String[] arrhdld2 = {"Ngày kí", "Thời hạn hợp đồng", "Làm việc trong ngày từ", "Làm việc trong ngày đến", "Ghi chú"};
        txthdldright = new JTextField[arrhdld2.length];
        lbhdldright = new JLabel[arrhdld2.length];
        int xLb2 = 700, yLb2 = 15;
        int xTxt2 = 950, yTxt2 = 15;
//        int xLbSg2 = 780, yLbSg2 = 40;
        for (int i = 0; i < arrhdld2.length; i++) {
            lbhdldright[i] = new JLabel(arrhdld2[i]);
            lbhdldright[i].setBounds(xLb2, yLb2, 240, 30);
            lbhdldright[i].setHorizontalAlignment(JLabel.CENTER);
            lbhdldright[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbhdldright[i].setName("lb" + i);
            pnDisplayTop.add(lbhdldright[i]);
            yLb2 = yLb2 + 40;
            txthdldright[i] = new JTextField();
            txthdldright[i].setName("txt" + i);
            txthdldright[i].setBounds(xTxt2, yTxt2, 230, 30);
            pnDisplayTop.add(txthdldright[i]);
            yTxt2 = yTxt2 + 40;
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
        header.add("Mã HĐLĐ");
        header.add("Loại HĐLĐ");
        header.add("Ngày bắt đầu");
        header.add("Ngày kết thúc");
        header.add("Địa điểm làm việc");
        header.add("Ngày kí");
        header.add("Thời hạn hợp đồng");
        header.add("Làm việc trong ngày từ");
        header.add("Làm việc trong ngày đến");
        header.add("Ghi chú");
        model = new DefaultTableModel(header, 4);
        tbhdld = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbhdld.setRowSorter(rowSorter);
        listMaHĐLĐ(); //Đọc từ database lên table 

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
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
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
            data.add(n.getGhichu());
            model.addRow(data);
        }
        tbhdld.setModel(model);
    }

    public void listMaHĐLĐ() // Chép ArrayList lên table
    {
        if (hdldBUS.getList() == null) {
            hdldBUS.listhdld();
        }
        ArrayList<hopDongLaoDongDTO> hdld = hdldBUS.getList();
        outModel(model, hdld);
    }
    public String getTextFieldContent() {
        return txthdldleft[0].getText();
    }

}
    
