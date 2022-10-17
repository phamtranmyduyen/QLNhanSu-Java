/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.thongKeDAO;
import DTO.bangChamCongDTO;
import DTO.luongDTO;
import DTO.nhanVienDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Shadow
 */
public class thongKeBUS {

    private bangChamCongBUS bccBUS = new bangChamCongBUS();

    public thongKeBUS() {
        bccBUS.listBangChamCong();
    }

    public String StatisticNV(String Id, String trongthang) {
        ArrayList<bangChamCongDTO> dsBCC = new ArrayList<>();
        System.out.println(trongthang.toString());
        dsBCC = bccBUS.getBangChamCongTrongThang(trongthang);
        System.out.println(dsBCC);
        thongKeDAO tkDAO = new thongKeDAO();
        return tkDAO.StatisticNV(dsBCC, Id);
    }

    public String StatisticNV(String Id) {
        ArrayList<bangChamCongDTO> dsBCC = new ArrayList<>();
        thongKeDAO tkDAO = new thongKeDAO();
        return tkDAO.StatisticNV(Id);
    }
    public ArrayList<luongDTO> StatisticL(int top, String rank) {
        thongKeDAO tkDAO = new thongKeDAO();
        return tkDAO.StatisticL(top, rank);
    }

}
