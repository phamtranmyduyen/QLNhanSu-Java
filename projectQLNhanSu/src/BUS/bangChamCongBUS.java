/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.bangChamCongDAO;
import DTO.bangChamCongDTO;
import DTO.chiTietBangChamCongDTO;
import DTO.hopDongLaoDongDTO;
import DTO.nhanVienDTO;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaiThy
 */
public class bangChamCongBUS {

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    public ArrayList<bangChamCongDTO> dsbangchamcong, bccTrongThang;
    public ArrayList<hopDongLaoDongDTO> thoigianlamviec;
    public ArrayList<chiTietBangChamCongDTO> thoigianvaolam;

    public bangChamCongBUS() {

    }

    public bangChamCongDTO get(String mabangchamcong) {
        for (bangChamCongDTO bangchamcong : dsbangchamcong) {
            if (bangchamcong.getMabangchamcong().equals(mabangchamcong)) {
                return bangchamcong;
            }
        }
        return null;
    }

    public void addBangChamCong(bangChamCongDTO bangchamcong) {
        dsbangchamcong.add(bangchamcong);
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        bangchamcongDAO.add(bangchamcong);
    }

    public void deleteBangChamCong(String mabcc) {
        for (bangChamCongDTO bcc : dsbangchamcong) {
            if (bcc.getMabangchamcong().equals(mabcc)) {
                dsbangchamcong.remove(bcc);
                bangChamCongDAO bccDAO = new bangChamCongDAO();
                bccDAO.delete(mabcc);
                return;
            }
        }
    }

    public void setBangChamCong(bangChamCongDTO bcc) {
        for (int i = 0; i < dsbangchamcong.size(); i++) {
            if (dsbangchamcong.get(i).getManhanvien().equals(bcc.getManhanvien())
                    && dsbangchamcong.get(i).getThoigian().equals(bcc.getThoigian())) {
                dsbangchamcong.set(i, bcc);
                bangChamCongDAO bccDAO = new bangChamCongDAO();
                bccDAO.set(bcc);
//                return;
            }
        }

    }

    public boolean checkMaBangChamCong(String mabangchamcong) {
        for (bangChamCongDTO bcc : dsbangchamcong) {
            if (bcc.getMabangchamcong().equals(mabangchamcong)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkThoiGian(String thoigian) {
        for (bangChamCongDTO bcc : dsbangchamcong) {
            if (bcc.getThoigian().equals(thoigian)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<bangChamCongDTO> getList(String manhanvien) {
        listBangChamCong(manhanvien);
        return dsbangchamcong;
    }

    public ArrayList<bangChamCongDTO> getList() {
        return dsbangchamcong;
    }

    public ArrayList<hopDongLaoDongDTO> getListThoiGianLamViec(String manhanvien) {
        listThoiGianLamViec(manhanvien);
        return thoigianlamviec;
    }

    public ArrayList<chiTietBangChamCongDTO> getListThoiGianVaoLam(String manhanvien) {
        listThoiGianVaoLam(manhanvien);
        return thoigianvaolam;
    }

    public void listBangChamCong(String manhanvien) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        dsbangchamcong = new ArrayList<>();
        dsbangchamcong = bangchamcongDAO.list(manhanvien);
    }

    public void listBangChamCong() {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        dsbangchamcong = new ArrayList<>();
        dsbangchamcong = bangchamcongDAO.list();
    }

    public void listThoiGianLamViec(String manhanvien) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        thoigianlamviec = new ArrayList<>();
        thoigianlamviec = bangchamcongDAO.getThoiGianLamViec(manhanvien);

    }

    public void listThoiGianVaoLam(String bangchamcong) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        thoigianvaolam = new ArrayList<>();
        thoigianvaolam = bangchamcongDAO.getThoiGianVaoLam(bangchamcong);
    }

    public void listThoiGianVaoLam(String manhanvien, String thoigian) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        thoigianvaolam = new ArrayList<>();
        thoigianvaolam = bangchamcongDAO.getThoiGianVaoLam(manhanvien, thoigian);
    }

    public float soGioTre(String manhanvien, String bangchamcong, String thoigian) {
        float sogiotre1 = 0, sogiotre2 = 0, sogiotre3 = 0;

        listThoiGianLamViec(manhanvien);

        hopDongLaoDongDTO tglv = thoigianlamviec.get(0);
        String strLamViecTu = tglv.getLamviectrongngaytu();
        //        LẤY THỜI GIAN LÀM VIỆC TRONG TUẦN
        String strLamViecTrongTuanTu = tglv.getLamviectrongtuantu();
        String strLamViecTrongTuanDen = tglv.getLamviectrongtuanden();
        ArrayList<String> dsThoiGianLamViecTrongTuan = dsNgayLamViecTrongTuan(strLamViecTrongTuanTu, strLamViecTrongTuanDen);
        Date lamviectu = null;
        try {
            lamviectu = sdf.parse(strLamViecTu);
        } catch (ParseException ex) {
            Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String strLamViecDen = tglv.getLamviectrongngayden();
        Date lamviecden = null;
        try {
            lamviecden = sdf.parse(strLamViecDen);
        } catch (ParseException ex) {
            Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bangchamcong != null) {
            listThoiGianVaoLam(bangchamcong);
        } else {
            listThoiGianVaoLam(manhanvien, thoigian);
        }
//        TH1: vào trễ hơn so với giờ bắt đầu làm
        for (int i = 0; i < thoigianvaolam.size(); i++) {
            if (dsThoiGianLamViecTrongTuan.contains(convertEnglishToVN(convertDayOfWeek(thoigianvaolam.get(i).getNgay()))) == true) {
                chiTietBangChamCongDTO tgvl = thoigianvaolam.get(i);
                String strGioVao = tgvl.getGiovao();
                Date giovao = null;
                try {
                    giovao = sdf.parse(strGioVao);
                } catch (ParseException ex) {
//                Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (giovao.after(lamviectu) && !strGioVao.equals("00:00:00")) {  // [Giờ vào] sau [Giờ làm việc từ]
                    long diff = giovao.getTime() - lamviectu.getTime();
                    long diffMinutes = diff / (60 * 1000);
                    sogiotre1 += Float.parseFloat(String.valueOf(diffMinutes)) / 60;

                }
            }
        }
        //        TH2: về sớm hơn so với giờ làm việc kết thúc
        for (int i = 0; i < thoigianvaolam.size(); i++) {
            if (dsThoiGianLamViecTrongTuan.contains(convertEnglishToVN(convertDayOfWeek(thoigianvaolam.get(i).getNgay()))) == true) {
                chiTietBangChamCongDTO tgvl = thoigianvaolam.get(i);
                String strGioRa = tgvl.getGiora();
                Date giora = null;
                try {
                    giora = sdf.parse(strGioRa);
                } catch (ParseException ex) {
//                Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (giora.before(lamviecden) && !strGioRa.equals("00:00:00")) {  // [Giờ vào] sau [Giờ làm việc từ]
                    long diff = lamviecden.getTime() - giora.getTime();
                    long diffMinutes = diff / (60 * 1000);
                    sogiotre2 += Float.parseFloat(String.valueOf(diffMinutes)) / 60;
                }
            }
        }
        //        TH3: KHÔNG CHẤM CÔNG
        for (int i = 0; i < thoigianvaolam.size(); i++) {
            if (dsThoiGianLamViecTrongTuan.contains(convertEnglishToVN(convertDayOfWeek(thoigianvaolam.get(i).getNgay()))) == true) {
                chiTietBangChamCongDTO tgvl = thoigianvaolam.get(i);
                String strGioVao = tgvl.getGiora();
                String strGioRa = tgvl.getGiora();

                if (strGioVao.equals("00:00:00") || strGioRa.equals("00:00:00")) {
                    long diff = lamviecden.getTime() - lamviectu.getTime();
                    long diffMinutes = diff / (60 * 1000);
                    sogiotre3 += Float.parseFloat(String.valueOf(diffMinutes)) / 60;
                }
            }
        }
        float sogiotre = sogiotre1 + sogiotre2 + sogiotre3;
        return sogiotre;
    }

    public float soGioLamThem(String manhanvien, String bangchamcong, String thoigian) {
        float sogiolamthem1 = 0, sogiolamthem2 = 0;
        listThoiGianLamViec(manhanvien);
        hopDongLaoDongDTO tglv = thoigianlamviec.get(0);
        //        Lấy thời gian làm việc từ
        String strLamViecTu = tglv.getLamviectrongngaytu();
        Date lamviectu = null;
        try {
            lamviectu = sdf.parse(strLamViecTu);
        } catch (ParseException ex) {
            Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Lấy thời gian làm việc đến
        String strLamViecDen = tglv.getLamviectrongngayden();
        Date lamviecden = null;
        try {
            lamviecden = sdf.parse(strLamViecDen);
        } catch (ParseException ex) {
            Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
//        LẤY THỜI GIAN LÀM VIỆC TRONG TUẦN
        String strLamViecTrongTuanTu = tglv.getLamviectrongtuantu();
        String strLamViecTrongTuanDen = tglv.getLamviectrongtuanden();
        ArrayList<String> dsThoiGianLamViecTrongTuan = dsNgayLamViecTrongTuan(strLamViecTrongTuanTu, strLamViecTrongTuanDen);
/// HIỂN THỊ THEO MÃ BẢNG CHẤM CÔNG
        if (bangchamcong != null) {
            listThoiGianVaoLam(bangchamcong);
        } else { //HIỂN THỊ THEO MÃ NHÂN VIÊN VÀ THỜI GIAN
            listThoiGianVaoLam(manhanvien, thoigian);
        }
//        Làm nhiều hơn thời gian kết thúc làm việc

        for (int i = 0; i < thoigianvaolam.size(); i++) {
            chiTietBangChamCongDTO tgvl = thoigianvaolam.get(i);
            String strGioRa = tgvl.getGiora();
            Date giora = null;
            try {
                giora = sdf.parse(strGioRa);
            } catch (ParseException ex) {
//                Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            String strGioVao = tgvl.getGiovao();
            Date giovao = null;
            try {
                giovao = sdf.parse(strGioVao);
            } catch (ParseException ex) {
//                Logger.getLogger(bangChamCongBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dsThoiGianLamViecTrongTuan.contains(convertEnglishToVN(convertDayOfWeek(thoigianvaolam.get(i).getNgay()))) == true) {
                System.out.println("tìm thấy");
                if (giora.after(lamviecden)) {  // [Giờ vào] sau [Giờ làm việc từ]
                    long diff = giora.getTime() - lamviecden.getTime();
                    long diffMinutes = diff / (60 * 1000);
                    sogiolamthem1 += Float.parseFloat(String.valueOf(diffMinutes)) / 60;

                }
            } else if (!strGioVao.equals("00:00:00") && !strGioRa.equals("00:00:00")) {
                //          THỜI GIAN LÀM VIỆC NGOÀI GIỜ LÀM VIỆC TRONG TUẦN
                long diff = giora.getTime() - giovao.getTime();
                long diffMinutes = diff / (60 * 1000);
                sogiolamthem2 += Float.parseFloat(String.valueOf(diffMinutes)) / 60;
            }

        }

        System.out.println("sogiolamthem1:" + sogiolamthem1);
        System.out.println("sogiolamthem2:" + sogiolamthem2);
        float sogiolamthem = sogiolamthem1 + sogiolamthem2;
        return sogiolamthem;
    }

    public float soGioTre(String manhanvien) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        float sogiotre = bangchamcongDAO.sogiotre(manhanvien);
        return sogiotre;
    }

    public float soGioLamThem(String manhanvien) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        float sogiolamthem = bangchamcongDAO.sogiolamthem(manhanvien);
        return sogiolamthem;
    }

    public boolean checkTime(Calendar from, Calendar to, Calendar time) {
        if (time.after(from) && time.before(to)) {
            return true;
        }
        return false;
    }

    public void bangChamCongTrongThang(String thoigian) {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        bccTrongThang = new ArrayList<>();
        bccTrongThang = bangchamcongDAO.bangChamCongTrongThang(thoigian);
    }

    public ArrayList<bangChamCongDTO> getBangChamCongTrongThang(String thoigian) {
        bangChamCongTrongThang(thoigian);
        return bccTrongThang;
    }

    public String mabangchamcong() {
        bangChamCongDAO bangchamcongDAO = new bangChamCongDAO();
        String mabangchamcong = bangchamcongDAO.mabangchamcong();
        return mabangchamcong;
    }
//    public static void main(String[] args) {
//        bangChamCongBUS b = new bangChamCongBUS();
//        b.soGioLamThem("NV002", "BCC004");
//    }

    public String convertDayOfWeek(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localdate = LocalDate.parse(date, formatter);
        DayOfWeek day = localdate.getDayOfWeek();
        String dayofweek = day.getDisplayName(TextStyle.FULL, Locale.getDefault());
        return dayofweek;
    }

    public String convertEnglishToVN(String dayofweek) {
        String thu = "";
        switch (dayofweek) {
            case "Monday":
                thu = "Thứ hai";
                break;
            case "Tuesday":
                thu = "Thứ ba";
                break;
            case "Wednesday":
                thu = "Thứ tư";
                break;
            case "Thursday":
                thu = "Thứ năm";
                break;
            case "Friday":
                thu = "Thứ sáu";
                break;
            case "Saturday":
                thu = "Thứ bảy";
                break;
            case "Sunday":
                thu = "Chủ nhật";
                break;

        }
        return thu;
    }

   

    public ArrayList<String> dsNgayLamViecTrongTuan(String batdau, String ketthuc) {
        ArrayList<String> ds = new ArrayList<>();
        String[] thu = {"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"};
        for (int i = 0; i < thu.length; i++) {
            if (thu[i].equals(batdau)) {
                while (!thu[i].equals(ketthuc)) {
                    ds.add(thu[i]);
                    i++;
                }
                ds.add(ketthuc);
                break;

            }
        }
        return ds;
    }

//    public static void main(String[] args) {
//        bangChamCongBUS bccBUS = new bangChamCongBUS();
////        System.out.println(bccBUS.convertDayOfWeek("2022-05-21"));
//        ArrayList<String> ds = bccBUS.dsNgayLamViecTrongTuan("Thứ ba", "Thứ sáu");
//        for(String data: ds)
//        {
//            System.out.println(data);
//        }
//    }
}
