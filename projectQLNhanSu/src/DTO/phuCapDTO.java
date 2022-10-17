/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author trinh
 */
public class phuCapDTO {
    private String maphucap,tenphucap;
    float tienphucap;
    public phuCapDTO()
    {
        
    }

    public phuCapDTO(String maphucap, String tenphucap, float tienphucap) {
        this.maphucap = maphucap;
        this.tenphucap = tenphucap;
        this.tienphucap = tienphucap;
    }

    public float getTienphucap(){
        return tienphucap;
    }
    public void setTienphucap(float tienphucap){
        this.tienphucap = tienphucap;
        
    }

    public String getMaphucap() {
        return maphucap;
    }

    public void setMaphucap(String maphucap) {
        this.maphucap = maphucap;
    }

    public String getTenphucap() {
        return tenphucap;
    }

    public void setTenphucap(String tenphucap) {
        this.tenphucap = tenphucap;
    }
    
}
