/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.nhanVienBUS;
import DTO.nhanVienDTO;
import DTO.nhanVienDTO;
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
/**
 *
 * @author admin
 */
public class Suggestmanv extends JDialog{
    private nhanVienBUS qBUS = new nhanVienBUS();
    private JPanel pnDisplay, pnTable, pnOption;
    private JTable tbnhanVien   ;
    private DefaultTableModel model;
    JTextField[] txtnhanvien   ;
    JLabel[] lbnhanvien;
    Font font;

//  true: add || false: edit
    TableRowSorter<TableModel> rowSorter;

    public Suggestmanv() {
        setModal(true);
        init();
    }

    public void init() {
        font = new Font("Segoe UI", Font.BOLD, 14);
        setTitle("Danh sách nhân viên");
        setSize(1300, 700);

        setLayout(new BorderLayout(10, 10));
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 730));

        pnDisplay();
        pnTable();
        tbnhanVien.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbnhanVien.getSelectedRow();
                if(tbnhanVien.getRowSorter() != null)
                {
                    i = tbnhanVien.getRowSorter().convertRowIndexToModel(i);
                }
                txtnhanvien[0].setText(tbnhanVien.getModel().getValueAt(i, 0).toString());
                txtnhanvien[1].setText(tbnhanVien.getModel().getValueAt(i, 1).toString());

             }
        });

        this.add(pnDisplay, BorderLayout.NORTH);
        this.add(pnTable, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.setVisible(true);
    }

    public JPanel pnDisplay() {
        pnDisplay = new JPanel();
        pnDisplay.setLayout(new BorderLayout(10, 10));
        pnDisplay.setPreferredSize(new Dimension(1300, 730 / 3));
        
        JPanel pnDisplayTop = new JPanel();
        pnDisplayTop.setLayout(null);
        pnDisplayTop.setBackground(Color.pink);
        pnDisplayTop.setPreferredSize(new Dimension(1300, (730 / 3)-70));
        String[] arrQuyen = {"Mã nhân viên", "Tên nhân viên"};
        txtnhanvien = new JTextField[arrQuyen.length];
        lbnhanvien = new JLabel[arrQuyen.length];
        int xLb = 390, yLb = 40;
        int xTxt = 600, yTxt = 40;
        for (int i = 0; i < arrQuyen.length; i++) {
            lbnhanvien[i] = new JLabel(arrQuyen[i]);
            lbnhanvien[i].setBounds(xLb, yLb, 200, 40);
            lbnhanvien[i].setHorizontalAlignment(JLabel.CENTER);
            lbnhanvien[i].setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 17));
            lbnhanvien[i].setName("lb" + i);
            pnDisplayTop.add(lbnhanvien[i]);
            yLb = yLb + 70;
            txtnhanvien[i] = new JTextField();
            txtnhanvien[i].setName("txt" + i);
            txtnhanvien[i].setBounds(xTxt, yTxt, 240, 40);
            pnDisplayTop.add(txtnhanvien[i]);
            yTxt = yTxt + 70;
        }
        pnOption();
        pnDisplay.add(pnDisplayTop, BorderLayout.NORTH);
        pnDisplay.add(pnOption, BorderLayout.SOUTH);
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
        pnTable.setPreferredSize(new Dimension(1300, 540));
        /**
         * ************ TẠO MODEL VÀ HEADER ********************
         */
        Vector header = new Vector();
        header.add("Mã nhân viên");
        header.add("Tên nhân viên");
        model = new DefaultTableModel(header, 2);
        tbnhanVien = new JTable(model);
        rowSorter = new TableRowSorter<TableModel>(model);
        tbnhanVien.setRowSorter(rowSorter);
        listnhanVien(); //Đọc từ database lên table 

        /**
         * ******************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */
        // Custom table
        tbnhanVien.setFocusable(false);
        tbnhanVien.setIntercellSpacing(new Dimension(0, 0));
        tbnhanVien.getTableHeader().setFont(font);
        tbnhanVien.setRowHeight(30);
        tbnhanVien.getTableHeader().setOpaque(false);
        tbnhanVien.setFillsViewportHeight(true);
        tbnhanVien.getTableHeader().setBackground(new Color(232, 57, 99));
        tbnhanVien.getTableHeader().setForeground(Color.WHITE);
        tbnhanVien.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbnhanVien);
        scroll.setBounds(new Rectangle(0, 60, 1300, 540));
        scroll.setBackground(null);

        pnTable.add(scroll);
        return pnTable;
    }

    public void outModel(DefaultTableModel model, ArrayList<nhanVienDTO> q) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (nhanVienDTO nv : q) {
            data = new Vector();
            data.add(nv.getManhanvien());
            data.add(nv.getHoten());
            model.addRow(data);
        }
        tbnhanVien.setModel(model);
    }

    public void listnhanVien() // Chép ArrayList lên table
    {
        if (qBUS.getList() == null) {
            qBUS.listNhanVien();
        }

        ArrayList<nhanVienDTO> q = qBUS.getList();
        outModel(model, q);
    }

    public String getTextFieldContent() {
        return txtnhanvien[0].getText();
    }

}


