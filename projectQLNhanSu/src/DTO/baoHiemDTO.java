package DTO;

public class baoHiemDTO {
    private String mabaohiem , tenbaohiem;
    public baoHiemDTO()
    {
            
    }

    public String getMabaohiem() {
        return mabaohiem;
    }

    public void setMabaohiem(String mabaohiem) {
        this.mabaohiem = mabaohiem;
    }

    public String getTenbaohiem() {
        return tenbaohiem;
    }

    public void setTenbaohiem(String tenbaohiem) {
        this.tenbaohiem = tenbaohiem;
    }


    public baoHiemDTO(String mabaohiem, String tenbaohiem) {
        this.mabaohiem = mabaohiem;
        this.tenbaohiem = tenbaohiem;

    }
}
