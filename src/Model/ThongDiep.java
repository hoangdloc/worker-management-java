/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Hoang Loc
 */
public class ThongDiep implements Serializable {
    private int kieuThongDiep;
    private Object obj;

    public ThongDiep() {
    }

    public ThongDiep(int kieuThongDiep, Object obj) {
        this.kieuThongDiep = kieuThongDiep;
        this.obj = obj;
    }

    public int getKieuThongDiep() {
        return kieuThongDiep;
    }

    public void setKieuThongDiep(int kieuThongDiep) {
        this.kieuThongDiep = kieuThongDiep;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
