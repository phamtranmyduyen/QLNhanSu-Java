package DTO;

public class chiTietChucNangDTO {
  private String   machitietchucnang, tenchitietchucnang, machucnang;
  public chiTietChucNangDTO()
  {
  
  }

    public String getMachitietchucnang() {
        return machitietchucnang;
    }

    public void setMachitietchucnang(String machitietchucnang) {
        this.machitietchucnang = machitietchucnang;
    }

    public String getTenchitietchucnang() {
        return tenchitietchucnang;
    }

    public void setTenchitietchucnang(String tenchitietchucnang) {
        this.tenchitietchucnang = tenchitietchucnang;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public chiTietChucNangDTO(String machitietchucnang, String tenchitietchucnang, String machucnang) {
        this.machitietchucnang = machitietchucnang;
        this.tenchitietchucnang = tenchitietchucnang;
        this.machucnang = machucnang;
    }
}
