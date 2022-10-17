/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chucNangBUS;
import BUS.quyenBUS;
import DTO.chucNangDTO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Minh Minion
 */
public class SuggestChucNang extends JDialog {

    private quyenBUS qBUS = new quyenBUS();
    private chucNangBUS cnBUS = new chucNangBUS();
    private String[] listChucNang, listMaChucNang;
    private ArrayList<String> chucNangDuocChon = new ArrayList<String>();
    private JCheckBox checkBoxChucNang[];
    private JLabel lbSubmitAddChucNang;

    public SuggestChucNang(String maquyen) {
        init(maquyen);
    }

    public void init(String maquyen) {
        setTitle("Danh sách chức năng");
        setSize(700, 700);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocationRelativeTo(null);
        listChucNang();
        checkBoxChucNang = new JCheckBox[listChucNang.length];
        int x = 230, y = 50;
//          for (int i = 0; i < listChucNang.length; i++) {
//              System.out.println(listChucNang[i]);
//          }
        for (int i = 0; i < listChucNang.length; i++) {
            checkBoxChucNang[i] = new JCheckBox(listChucNang[i]);
            checkBoxChucNang[i].setName(listMaChucNang[i]);
            checkBoxChucNang[i].setBounds(x, y, 300, 60);
            checkBoxChucNang[i].setMargin(new java.awt.Insets(10, 10, 10, 10));
            checkBoxChucNang[i].setFont(new java.awt.Font("dialog", 1, 20));
            checkBoxChucNang[i].setForeground(Color.white);
            checkBoxChucNang[i].setBackground(new Color(55, 63, 81));

            this.add(checkBoxChucNang[i]);
            y = y + 70;
        }

        lbSubmitAddChucNang = new JLabel("CHỌN", JLabel.CENTER);
        lbSubmitAddChucNang.setFont(new java.awt.Font("dialog", 1, 18));
        lbSubmitAddChucNang.setOpaque(true);
        lbSubmitAddChucNang.setBackground(Color.white);
        lbSubmitAddChucNang.setBounds(280, 560, 80, 40);
        lbSubmitAddChucNang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int j = 0;
                for (int i = 0; i < listChucNang.length; i++) {
                    if (checkBoxChucNang[i].isSelected()) {
                        chucNangDuocChon.add(checkBoxChucNang[i].getName());
                        j++;
                    }
                }
                for (int k = 0; k < chucNangDuocChon.size(); k++) {
                    System.out.println(chucNangDuocChon.get(k));
                }
                int confirmAdd = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chức năng", "", JOptionPane.YES_NO_OPTION);
                if (confirmAdd == 0) {
                    System.out.println(chucNangDuocChon);
                    qBUS.addChucNangQuyen(maquyen, getChucNangDuocChon());
                    JOptionPane.showMessageDialog(null, "Thêm chức năng thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });

        this.add(lbSubmitAddChucNang);

        setVisible(true);
    }

    public void listChucNang() {
        if (cnBUS.getList() == null) {
            cnBUS.listChucNang();
        }
        ArrayList<chucNangDTO> cn = cnBUS.getList();
        listChucNang = new String[cn.size()];
        listMaChucNang = new String[cn.size()];
        int i = 0;
        for (chucNangDTO chucnang : cn) {
            listChucNang[i] = chucnang.getTenchucnang();
            listMaChucNang[i] = chucnang.getMachucnang();
            i++;
        }
    }

    public String[] getChucNang() {
        listChucNang();
        return listChucNang;
    }

    public ArrayList<String> getChucNangDuocChon() {
        return chucNangDuocChon;
    }

    public void selectedChucNang(ArrayList<String> dscn) {
        for (int j = 0; j < dscn.size(); j++) {
            for (int i = 0; i < checkBoxChucNang.length; i++) {

                if (checkBoxChucNang[i].getName().equals(dscn.get(j))) {
                    System.out.println(checkBoxChucNang[i].getName());
                    checkBoxChucNang[i].setSelected(true);
                }
            }
        }
    }
}
