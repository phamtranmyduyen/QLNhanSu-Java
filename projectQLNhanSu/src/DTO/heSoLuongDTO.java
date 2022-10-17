/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author trinh
 */
public class heSoLuongDTO {
    private String mahesoluong;
    float hesoluong;
 public heSoLuongDTO()
 {
     
 }   

    public heSoLuongDTO(String mahesoluong, float hesoluong) {
        this.mahesoluong = mahesoluong;
        this.hesoluong = hesoluong;
    }

    public String getMahesoluong() {
        return mahesoluong;
    }

    public void setMahesoluong(String mahesoluong) {
        this.mahesoluong = mahesoluong;
    }

    public float getHesoluong() {
        return hesoluong;
    }

    public void setHesoluong(float hesoluong) {
        this.hesoluong = hesoluong;
    }
 
}
