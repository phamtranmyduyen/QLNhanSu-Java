/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author trinh
 */
public class thuongDTO {
    private String mathuong,loaithuong;
    float tienthuong; 

public thuongDTO()
{
    
}

    public thuongDTO(String mathuong, String loaithuong, float tienthuong) {
        this.mathuong = mathuong;
        this.loaithuong = loaithuong;
        this.tienthuong = tienthuong;
        
    }

   
  public String getLoaithuong(){
      return loaithuong;
  }
  public void setLoaithuong(String loaithuong){
      this.loaithuong = loaithuong;
  }

    public String getMathuong() {
        return mathuong;
    }

    public void setMathuong(String mathuong) {
        this.mathuong = mathuong;
    }

    public float getTienthuong() {
        return tienthuong;
    }

    public void setTienthuong(float tienthuong) {
        this.tienthuong = tienthuong;
    }

}


