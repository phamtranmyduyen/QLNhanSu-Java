/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author MaiThy
 */
public class luongCanBanDTO {
    String maluongcanban;
    float luongcanban;

    public luongCanBanDTO() {
    }

    public luongCanBanDTO(String maluongcanban, float luongcanban) {
        this.maluongcanban = maluongcanban;
        this.luongcanban = luongcanban;
    }

    public String getMaluongcanban() {
        return maluongcanban;
    }

    public void setMaluongcanban(String maluongcanban) {
        this.maluongcanban = maluongcanban;
    }

    public float getLuongcanban() {
        return luongcanban;
    }

    public void setLuongcanban(float luongcanban) {
        this.luongcanban = luongcanban;
    }
    
}
