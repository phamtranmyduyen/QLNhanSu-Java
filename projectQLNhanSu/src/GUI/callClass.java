/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author MaiThy
 */
public class callClass {

    public static JPanel callClass(String s) {
        JPanel pn = new JPanel();
        pn.setLayout(null);
        if (s.equals(new ChucNangGUI(1300).getClass().getSimpleName())) {
            pn = new ChucNangGUI(1300);
        }
        if (s.equals(new BaoHiemGUI(1300).getClass().getSimpleName())) {
            pn = new BaoHiemGUI(1300);
        }
        if (s.equals(new BangCapGUI(1300).getClass().getSimpleName())) {
            pn = new BangCapGUI(1300);
        }
        if (s.equals(new HopDongLaoDongGUI(1300).getClass().getSimpleName())) {
            pn = new HopDongLaoDongGUI(1300);
        }
        if (s.equals(new ChucVuGUI(1300).getClass().getSimpleName())) {
            pn = new ChucVuGUI(1300);
        }
        if (s.equals(new NhanVienGUI(1300).getClass().getSimpleName())) {
            pn = new NhanVienGUI(1300);
        }
        if (s.equals(new PhongBanGUI(1300).getClass().getSimpleName())) {
            pn = new PhongBanGUI(1300);
        }
        if (s.equals(new QuyenGUI(1300).getClass().getSimpleName())) {
            pn = new QuyenGUI(1300);
        }
        if (s.equals(new BangChamCongGUI(1300).getClass().getSimpleName())) {
            pn = new BangChamCongGUI(1300);
        }
        if (s.equals(new TaiKhoanGUI(1300).getClass().getSimpleName())) {
            pn = new TaiKhoanGUI(1300);
        }
        if (s.equals(new LuongGUI(1300).getClass().getSimpleName())) {
            pn = new LuongGUI(1300);
        }
        if (s.equals(new PhuCapGUI(1300).getClass().getSimpleName())) {
            pn = new PhuCapGUI(1300);
        }
        if (s.equals(new ThuongGUI(1300).getClass().getSimpleName())) {
            pn = new ThuongGUI(1300);
        }
        if (s.equals(new HeSoLuongGUI(1300).getClass().getSimpleName())) {
            pn = new HeSoLuongGUI(1300);
        }
        if (s.equals(new ThongKeGUI(1300).getClass().getSimpleName())) {
            pn = new ThongKeGUI(1300);
        }
        if (s.equals(new LuongCanBanGUI(1300).getClass().getSimpleName())) {
            pn = new LuongCanBanGUI(1300);
        }
        return pn;

    }

}
