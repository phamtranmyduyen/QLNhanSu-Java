/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chiTietChucNangBUS;
import BUS.chucNangBUS;
import BUS.taiKhoanBUS;
import DTO.chiTietChucNangDTO;
import DTO.chucNangDTO;
import GUI.model.convertString;
import GUI.model.header;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import GUI.model.navItem;
import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Shadow
 */
public class quanLyNhanSu extends JFrame implements MouseListener {

    private String userID;
    private String userName;
    private String role;
    private ArrayList<String> dsChucNangKhongThuocQuyen;
    JScrollPane scroll;
    private boolean flag = true;
    private JPanel header, nav, main;
    private int DEFAULT_HEIGHT = 730, DEFALUT_WIDTH = 1300;
    private ArrayList<String> navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm
    private ArrayList<navItem> navObj = new ArrayList<>();  //Chứa cái button trên thanh menu
    

    public quanLyNhanSu(String userID, String userName, String role) {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
        Toolkit screen = Toolkit.getDefaultToolkit();
        init();
    }

    public quanLyNhanSu() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        init();
    }

    public void init() {
        setTitle("Quản Lý nhân sự");
        ImageIcon logo = new ImageIcon("/img/title.png");
        setIconImage(logo.getImage());
        setLayout(new BorderLayout());
        setSize(DEFALUT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        /**
         * ********** PHẦN HEADER ************************************
         */
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(DEFALUT_WIDTH, 40));

        header headermain = new header(this, DEFALUT_WIDTH, 40);

        if (userName != null) {
            if (role.equals("Admin")) {
                userName = "Admin";
            }
            JLabel user = new JLabel("Chào, " + userName);
            user.setForeground(Color.WHITE);
            user.setBounds(new Rectangle(DEFALUT_WIDTH - 300, -7, 150, 50));
            headermain.add(user);
            //Tạo btn Logout
            navItem btnLogOut = new navItem("", new Rectangle(DEFALUT_WIDTH - 150, -8, 50, 50), "Logout_24px.png", "Logout_24px.png", "Logout_24px.png", new Color(80, 80, 80));
            headermain.add(btnLogOut.isButton());
            btnLogOut.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Login lg = new Login();
                    dispose();
                }
            });
        }

        header.add(headermain);

        /**
         * ********** PHẦN NAVIGATION ( MENU ) *************************
         */
        menu();
        /**
         * ********** PHẦN MAIN ( HIỂN THỊ ) *************************
         */
        

        main = new JPanel(null);
        main.removeAll();
        main.add(new MainGUI(1300));
        main.repaint();
        main.revalidate();
/**
         * ********** PHẦN ADD *************************
         */
        
        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        quanLyNhanSu ql = new quanLyNhanSu();

    }

    public void menu() {

        nav = new JPanel(new FlowLayout(0));
        nav.setBackground(new Color(55, 63, 81));
        nav.setPreferredSize(new Dimension(220, DEFAULT_HEIGHT));

        scroll = new JScrollPane(nav);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(1, 600));
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);

        //Thêm item vào thanh menu (Tên item : icon : icon hover)
        navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm ( Tên btn : icon : icon hover )   
        navItem.add("Bảng chấm công:clipboard_24px_noactive.png:clipboard_24px.png");
        navItem.add("Quản lý nhân viên:profile_24px_noactive.png:profile_24px.png");
        navItem.add("Quản lý lương:money_24px_noactive.png:money_24px.png");
        navItem.add("Quản lý chức năng:list_24px_noactive.png:list_24px.png");
        navItem.add("Quản lý quyền:checked_user_24px_noactive.png:checked_user_24px.png");
        navItem.add("Quản lý tài khoản:users_settings_24px_noactive.png:users_settings_24px.png");
        navItem.add("Thống kê:users_settings_24px_noactive.png:users_settings_24px.png");
        System.out.println(role);

        outNav();
    }

    public boolean ktraChucNangChon(String tencndangmo) {
        System.out.println("ĐANG KTRAAAAAAAAAA");
        boolean ktraChucNangChon = true;
        chucNangBUS cnBUS = new chucNangBUS();
        ArrayList<String> dsTenCNCoCTCN = cnBUS.layTenCNThuocCTCN();
        for (String dscncoctcn : dsTenCNCoCTCN) {
            if (tencndangmo.equals(dscncoctcn)) {
                ktraChucNangChon = false;
            }
        }
        return ktraChucNangChon;
    }

    public int kTraFlag(int i) {
        chucNangBUS cnBUS = new chucNangBUS();
        int vitri = 0;
        String tencndangmo = "";
        String[] dsTenChucNang = cnBUS.getTenChucNang();
        if (navObj.size() > dsTenChucNang.length) {
            int tmp = navObj.size() - dsTenChucNang.length;
            tencndangmo = cnBUS.layTenCNThoaSL(tmp);
            for (int j = 0; j < navObj.size(); j++) {
                navItem item = navObj.get(j);
                if (tencndangmo.equals(item.getToolTipText())) {
                    vitri = j;
                }

            }
            if (vitri != i) {

                int u = vitri + 1;
                flag = true;
                chiTietChucNangBUS ctcnBUS = new chiTietChucNangBUS();
                ArrayList<String> dsTenChiTietChucNang = ctcnBUS.getTenchitietchucnang(tencndangmo);
                int dem = 0; //Đếm số lượng hiển thị chi tiết chức năng để remove khi !flag1
                while (dem < dsTenChiTietChucNang.size()) {
                    navItem.remove(u);
                    dem++;
                }
                if (i > tmp) {
                    i = i - tmp;
                }
                outNav();
            }
        }
        return i;
    }

    public void hienThiChiTietChucNang(navItem item, int i) {
        System.out.println("item.getToolTipText(): " + item.getToolTipText());
        i = kTraFlag(i);

        System.out.println("i:" + i);
        ArrayList<String> ctcn = new ArrayList<>();
        ctcn.add(item.getToolTipText());
        chucNangBUS cnBUS = new chucNangBUS();
        String[] dsTenChucNang = cnBUS.getTenChucNang();
//                Lọc danh sách tên chức năng => so sánh với chức năng đang chọn
//                => Hiển thị ra chi tiết chức năng     

        for (int j = 0; j < dsTenChucNang.length; j++) {
            for (int k = 0; k < ctcn.size(); k++) {
                if (ctcn.get(k).equals(dsTenChucNang[j])) {
                    chiTietChucNangBUS ctcnBUS = new chiTietChucNangBUS();
                    ArrayList<String> dsTenChiTietChucNang = ctcnBUS.getTenchitietchucnang(dsTenChucNang[j]);
                    int vtthemcn = i + 1; //Thêm chi tiết chức năng vào vị trí 
                    // vị trí chức năng hiện tại + 1

                    if (flag) {
                        for (String tenctcn : dsTenChiTietChucNang) {
                            navItem.add(vtthemcn, tenctcn + ":circle_16px_noactive.png:circle_16px.png");
                            vtthemcn++;
                            flag = false;
                        }

                    } else {
                        int dem = 0; //Đếm số lượng hiển thị chi tiết chức năng để remove khi !flag1
                        while (dem < dsTenChiTietChucNang.size()) {
                            navItem.remove(vtthemcn);
                            flag = true;
                            dem++;
                        }
                    }
                }
            }

        }
        outNav();

    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        for (int i = 0; i < navObj.size(); i++) {
            navItem item = navObj.get(i); // lấy vị trí item trong menu     
            if (e.getSource() == item) {
//               HIỂN THỊ MAIN
                item.doActive(); // Active NavItem đc chọn 
                main.removeAll();
                String s = item.getName();
                main.add(callClass.callClass(s));
                main.repaint();
                main.revalidate();
//                CHECK CHI TIẾT CHỨC NĂNG
                if (!ktraChucNangChon(item.getToolTipText())) {
                    hienThiChiTietChucNang(item, i);
                }
            } else {
                item.noActive();
            }

        }

    }

    public String convertTenMenu(String s) {
        s = convertString.removeAccent(s);
        s = convertString.vietHoaChuCaiDau(s);
        s = s.replaceAll(" ", "");
        s = s.replaceAll("QuanLy", "");
        s = s.concat("GUI");
        return s;
    }

    public void outNav() {
        //Gắn các NavItem vào NavOBJ
        navObj.clear();
        for (int i = 0; i < navItem.size(); i++) {
            String s = navItem.get(i).split(":")[0];
            String icon = navItem.get(i).split(":")[1];
            String iconActive = navItem.get(i).split(":")[2];
            navObj.add(new navItem(s, 220, 40, icon, iconActive));
            navObj.get(i).setToolTipText(s);
            s = convertTenMenu(s);
            navObj.get(i).setName(s);
            navObj.get(i).addMouseListener(this);
            if (iconActive.equals("circle_16px.png")) {
                navObj.get(i).setColorNormal(new Color(86, 94, 127));
            }

        }

        if (role != null) {
            taiKhoanBUS tkBUS = new taiKhoanBUS();
            dsChucNangKhongThuocQuyen = new ArrayList<>();
            dsChucNangKhongThuocQuyen = tkBUS.getChucNangKhongThuocQuyen(role);
            for (int j = 0; j < dsChucNangKhongThuocQuyen.size(); j++) {
                for (int i = 0; i < navItem.size(); i++) {
                    String s = navItem.get(i).split(":")[0];
                    if (s.equals(dsChucNangKhongThuocQuyen.get(j))) {
                        navObj.get(i).setVisible(false);
                    }
                }

            }
        }

        //Xuất ra Naigation
        nav.removeAll();
        JLabel profile = new JLabel(new ImageIcon("./src/img/profile_200px.png"));
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                main.removeAll();
                main.add(new MainGUI(1300));
                main.repaint();
                main.revalidate();
            }
        });
        profile.setBounds(0, 0, 220, 200);
        nav.add(profile);
        for (navItem n : navObj) {
            nav.add(n);
        }
        repaint();
        revalidate();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

}
