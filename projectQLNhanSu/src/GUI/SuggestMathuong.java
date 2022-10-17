package GUI;

import BUS.thuongBUS;
import DTO.thuongDTO; 
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

public class SuggestMathuong extends JDialog {
    private thuongBUS thuongBUS = new thuongBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbthuong;
    private DefaultTableModel model;
    JTextField[] txtthuong;
    JLabel[] lbthuong;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestMathuong() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH THƯỞNG", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbthuong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbthuong.getSelectedRow();
                if (tbthuong.getRowSorter() != null) {
                    i = tbthuong.getRowSorter().convertRowIndexToModel(i);
                }
                txtthuong[0].setText(tbthuong.getModel().getValueAt(i, 0).toString());
                txtthuong[1].setText(tbthuong.getModel().getValueAt(i, 1).toString());
                txtthuong[2].setText(tbthuong.getModel().getValueAt(i, 2).toString());

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
        pnDisplayTop.setPreferredSize(new Dimension(1300, 300 - 70));
        String[] arrthuong = {"Mã thưởng", "Loại thưởng", "Tiền thưởng"};
        txtthuong = new JTextField[arrthuong.length];
        lbthuong = new JLabel[arrthuong.length];
        int xLb = 390, yLb = 60;
        int xTxt = 600, yTxt = 60;
        for (int i = 0; i < arrthuong.length; i++) {
            lbthuong[i] = new JLabel(arrthuong[i]);
            lbthuong[i].setBounds(xLb, yLb, 200, 30);
            lbthuong[i].setHorizontalAlignment(JLabel.CENTER);
            lbthuong[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbthuong[i].setName("lb" + i);
            pnDisplayTop.add(lbthuong[i]);
            yLb = yLb + 50;
            txtthuong[i] = new JTextField();
            txtthuong[i].setName("txt" + i);
            txtthuong[i].setBounds(xTxt, yTxt, 240, 35);
            pnDisplayTop.add(txtthuong[i]);
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
        header.add("Mã thưởng");
        header.add("Loại thưởng");
        header.add("Tiền thưởng");
        model = new DefaultTableModel(header, 2);
        tbthuong = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbthuong.setRowSorter(rowSorter);
        listThuong(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbthuong.setFocusable(false);
        tbthuong.setIntercellSpacing(new Dimension(0, 0));
        tbthuong.getTableHeader().setFont(font);
        tbthuong.setRowHeight(30);
        tbthuong.getTableHeader().setOpaque(false);
        tbthuong.setFillsViewportHeight(true);
        tbthuong.getTableHeader().setBackground(new Color(232, 57, 99));
        tbthuong.getTableHeader().setForeground(Color.WHITE);
        tbthuong.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbthuong);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<thuongDTO> t) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (thuongDTO thuong : t) {
            data = new Vector();
            data.add(thuong.getMathuong());
            data.add(thuong.getLoaithuong());
            data.add(thuong.getTienthuong());
            model.addRow(data);
        }
        tbthuong.setModel(model);
    }

    public void listThuong() // Chép ArrayList lên table
    {
        if (thuongBUS.getList() == null) {
            thuongBUS.listThuong();
        }

        ArrayList<thuongDTO> thuong = thuongBUS.getList();
        outModel(model, thuong);
    }

    public String getTextFieldContent() {
        return txtthuong[0].getText();
    }
}
