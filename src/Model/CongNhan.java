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
public class CongNhan implements Serializable {
    private int id;
    private String hoTen;
    private int namSinh;
    private QueHuong queHuong;
    private String gioiTinh;
    private double heSoLuong;

    public CongNhan() {
    }

    public CongNhan(int id, String hoTen, int namSinh, QueHuong queHuong, String gioiTinh, double heSoLuong) {
        this.id = id;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.queHuong = queHuong;
        this.gioiTinh = gioiTinh;
        this.heSoLuong = heSoLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public QueHuong getQueHuong() {
        return queHuong;
    }

    public void setQueHuong(QueHuong queHuong) {
        this.queHuong = queHuong;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }
}
