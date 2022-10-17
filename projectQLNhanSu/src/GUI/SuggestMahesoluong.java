package GUI;

import BUS.heSoLuongBUS;
import DTO.heSoLuongDTO; 
import GUI.model.headerSuggest;
import GUI.model.navItem;
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

public class SuggestMahesoluong extends JDialog {
    private heSoLuongBUS hslBUS = new heSoLuongBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbhesoluong;
    private DefaultTableModel model;
    JTextField[] txthesoluong;
    JLabel[] lbhesoluong;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestMahesoluong() {
        setModal(true);
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 14);
        setSize(1300, 700);

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 730));
        /**
         * ********** PHẦN HEADER ************************************
         */
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(1300, 40));

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH HỆ SỐ LƯƠNG", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbhesoluong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbhesoluong.getSelectedRow();
                if (tbhesoluong.getRowSorter() != null) {
                    i = tbhesoluong.getRowSorter().convertRowIndexToModel(i);
                }
                txthesoluong[0].setText(tbhesoluong.getModel().getValueAt(i, 0).toString());
                txthesoluong[1].setText(tbhesoluong.getModel().getValueAt(i, 1).toString());

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
        pnDisplay.setPreferredSize(new Dimension(1300, 730 / 3));

        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, (730 / 3) - 70));
        String[] arrhesoluong = {"Mã hệ số lương", "Hệ số lương"};
        txthesoluong = new JTextField[arrhesoluong.length];
        lbhesoluong = new JLabel[arrhesoluong.length];
        int xLb = 390, yLb = 40;
        int xTxt = 600, yTxt = 40;
        for (int i = 0; i < arrhesoluong.length; i++) {
            lbhesoluong[i] = new JLabel(arrhesoluong[i]);
            lbhesoluong[i].setBounds(xLb, yLb, 200, 40);
            lbhesoluong[i].setHorizontalAlignment(JLabel.CENTER);
            lbhesoluong[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbhesoluong[i].setName("lb" + i);
            pnDisplayTop.add(lbhesoluong[i]);
            yLb = yLb + 70;
            txthesoluong[i] = new JTextField();
            txthesoluong[i].setName("txt" + i);
            txthesoluong[i].setBounds(xTxt, yTxt, 240, 40);
            pnDisplayTop.add(txthesoluong[i]);
            yTxt = yTxt + 70;
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
        pnTable.setPreferredSize(new Dimension(1300, 420));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã hệ số lương");
        header.add("Hệ số lương");
        model = new DefaultTableModel(header, 2);
        tbhesoluong = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbhesoluong.setRowSorter(rowSorter);
        listHeSoLuong(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbhesoluong.setFocusable(false);
        tbhesoluong.setIntercellSpacing(new Dimension(0, 0));
        tbhesoluong.getTableHeader().setFont(font);
        tbhesoluong.setRowHeight(30);
        tbhesoluong.getTableHeader().setOpaque(false);
        tbhesoluong.setFillsViewportHeight(true);
        tbhesoluong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbhesoluong.getTableHeader().setForeground(Color.WHITE);
        tbhesoluong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbhesoluong);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<heSoLuongDTO> hsl) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (heSoLuongDTO hesoluong : hsl) {
            data = new Vector();
            data.add(hesoluong.getMahesoluong());
            data.add(hesoluong.getHesoluong());
            model.addRow(data);
        }
        tbhesoluong.setModel(model);
    }

    public void listHeSoLuong() // Chép ArrayList lên table
    {
        if (hslBUS.getList() == null) {
            hslBUS.listHeSoLuong();
        }

        ArrayList<heSoLuongDTO> hsl = hslBUS.getList();
        outModel(model, hsl);
    }

    public String getTextFieldContent() {
        return txthesoluong[0].getText();
    }
}
