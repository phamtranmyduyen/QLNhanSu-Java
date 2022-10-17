/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.nhanVienBUS;
import BUS.taiKhoanBUS;
import DTO.nhanVienDTO;
import DTO.taiKhoanDTO;
import GUI.model.header;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Shadow
 */
public class Login extends JFrame {

    private quanLyNhanSu qlns;
    private taiKhoanBUS tkBUS = new taiKhoanBUS();
    private nhanVienBUS nvBUS = new nhanVienBUS();
    private JTextField tk;
    private JPasswordField pass;
    private int DEFAULT_HEIGHT = 600, DEFAULT_WIDTH = 400;
    private JLabel lbLogin;

    public Login() {
        init();
    }

    public void init() {

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        header hd = new header(this, 400, 60);
        hd.setBackground(new Color(0, 0, 0, 0));
        hd.setBounds(50, 80, 300, 60);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font font1 = new Font("Segoe UI", Font.BOLD, 15);

        JPanel background = new JPanel();
        background.setLayout(null);
        background.setBackground(new Color(55, 63, 81));
        background.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        lbLogin = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        lbLogin.setBorder(null);
        lbLogin.setOpaque(true);
        lbLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbLogin.setFont(font1);
        lbLogin.setBounds(100, 400, 200, 40);

        JLabel exit = new JLabel(new ImageIcon("./src/img/close_window_26px.png"), JLabel.CENTER);
        exit.setBounds(DEFAULT_WIDTH - 40, 10, 30, 30);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lbUser = new JLabel(new ImageIcon("./src/img/user_30px.png"), JLabel.CENTER);
        lbUser.setBounds(80, 197, 30, 30);
        JLabel lbPass = new JLabel(new ImageIcon("./src/img/pwd_30px.png"), JLabel.CENTER);
        lbPass.setBounds(80, 277, 30, 30);

        tk = new JTextField("Username");
        tk.setFont(font);
        tk.setForeground(Color.WHITE);
        tk.setCaretColor(Color.WHITE);
        tk.setBounds(120, 200, 180, 30);
        tk.setBorder(null);
        tk.setOpaque(false);

        pass = new JPasswordField("Password");
        pass.setFont(font);
        pass.setForeground(Color.WHITE);
        pass.setCaretColor(Color.WHITE);
        pass.setBorder(null);
        pass.setBounds(120, 280, 150, 30);
        pass.setOpaque(false);

        JSeparator sp1 = new JSeparator();
        sp1.setBounds(80, 230, 220, 10);

        JSeparator sp2 = new JSeparator();
        sp2.setBounds(80, 310, 220, 10);

        setUndecorated(true);
        background.add(exit);
        background.add(hd);
        background.add(lbUser);
        background.add(tk);
        background.add(sp1);
        background.add(lbPass);
        background.add(pass);
        background.add(sp2);
        background.add(lbLogin);
        add(background);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        tk.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                tk.setText("");
            }
        }));

        pass.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pass.setText("");
            }
        }));
        pass.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                pass.setText("");
            }
        });
        lbLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tkBUS.getList() == null) {
                    tkBUS.listTaiKhoan();
                }
                String tkname = tk.getText();
                char[] passwd = pass.getPassword();
//                KTRA TRẠNG THÁI HOẠT ĐỘNG
                if (tkBUS.checkTrangThai(tkname) == false) {
                    JOptionPane.showMessageDialog(null, "Tài khoản của bạn hiện không hoạt động");
                    return;
                }
//                KTRA TÊN ĐĂNG NHẬP VÀ MẬT KHẨU
                taiKhoanDTO tk = tkBUS.checkLogin(tkname, passwd);
                if (tk == null) {
                    JOptionPane.showMessageDialog(null, "Sai tên tài khoản hoặc mật khẩu");
                    return;
                }
//                LẤY TÀI KHOẢN
                if (nvBUS.getList() == null) {
                    nvBUS.listNhanVien();
                }
                nhanVienDTO nv = new nhanVienDTO();
                System.out.println(tk.getTentaikhoan());
                nv = nvBUS.get(tk.getTentaikhoan());
                qlns = new quanLyNhanSu(nv.getManhanvien(), nv.getHoten(), tk.getMaquyen());
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        Login lg = new Login();
    }

}
