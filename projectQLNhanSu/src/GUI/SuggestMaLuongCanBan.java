package GUI;

import BUS.luongCanBanBUS;
import DTO.luongCanBanDTO; 
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

public class SuggestMaLuongCanBan extends JDialog {
    private luongCanBanBUS lcbBUS = new luongCanBanBUS();
    private JPanel pnDisplay, pnTable, pnOption, header;
    private JTable tbluongcanban;
    private DefaultTableModel model;
    JTextField[] txtluongcanban;
    JLabel[] lbluongcanban;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public SuggestMaLuongCanBan() {
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

        headerSuggest headerSuggest = new headerSuggest("DANH SÁCH LƯƠNG CĂN BẢN", 1300, 40);
        header.add(headerSuggest);
//        DISPLAY
        pnDisplay();
        pnTable();
        tbluongcanban.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbluongcanban.getSelectedRow();
                if (tbluongcanban.getRowSorter() != null) {
                    i = tbluongcanban.getRowSorter().convertRowIndexToModel(i);
                }
                txtluongcanban[0].setText(tbluongcanban.getModel().getValueAt(i, 0).toString());
                txtluongcanban[1].setText(tbluongcanban.getModel().getValueAt(i, 1).toString());

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
        String[] arrluongcanban = {"Mã lương căn bản", "Lương căn bản"};
        txtluongcanban = new JTextField[arrluongcanban.length];
        lbluongcanban = new JLabel[arrluongcanban.length];
        int xLb = 390, yLb = 40;
        int xTxt = 600, yTxt = 40;
        for (int i = 0; i < arrluongcanban.length; i++) {
            lbluongcanban[i] = new JLabel(arrluongcanban[i]);
            lbluongcanban[i].setBounds(xLb, yLb, 200, 40);
            lbluongcanban[i].setHorizontalAlignment(JLabel.CENTER);
            lbluongcanban[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbluongcanban[i].setName("lb" + i);
            pnDisplayTop.add(lbluongcanban[i]);
            yLb = yLb + 70;
            txtluongcanban[i] = new JTextField();
            txtluongcanban[i].setName("txt" + i);
            txtluongcanban[i].setBounds(xTxt, yTxt, 240, 40);
            pnDisplayTop.add(txtluongcanban[i]);
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
        header.add("Mã lương căn bản");
        header.add("Lương căn bản");
        model = new DefaultTableModel(header, 2);
        tbluongcanban = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbluongcanban.setRowSorter(rowSorter);
        listLuongCanBan(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbluongcanban.setFocusable(false);
        tbluongcanban.setIntercellSpacing(new Dimension(0, 0));
        tbluongcanban.getTableHeader().setFont(font);
        tbluongcanban.setRowHeight(30);
        tbluongcanban.getTableHeader().setOpaque(false);
        tbluongcanban.setFillsViewportHeight(true);
        tbluongcanban.getTableHeader().setBackground(new Color(232, 57, 99));
        tbluongcanban.getTableHeader().setForeground(Color.WHITE);
        tbluongcanban.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbluongcanban);
        scroll.setBounds(new Rectangle(0, 0, 1300, 730 - (730 / 3)));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<luongCanBanDTO> lcb) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (luongCanBanDTO luongcanban : lcb) {
            data = new Vector();
            data.add(luongcanban.getMaluongcanban());
            data.add(luongcanban.getLuongcanban());
            model.addRow(data);
        }
        tbluongcanban.setModel(model);
    }

    public void listLuongCanBan() // Chép ArrayList lên table
    {
        if (lcbBUS.getList() == null) {
            lcbBUS.listLuongCanBan();
        }

        ArrayList<luongCanBanDTO> lcb = lcbBUS.getList();
        outModel(model, lcb);
    }

    public String getTextFieldContent() {
        return txtluongcanban[0].getText();
    }
}
