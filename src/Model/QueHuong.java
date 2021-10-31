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
public class QueHuong implements Serializable {
    private int id;
    private String tenTP;

    public QueHuong() {
    }

    public QueHuong(int id, String tenTP) {
        this.id = id;
        this.tenTP = tenTP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTP() {
        return tenTP;
    }

    public void setTenTP(String tenTP) {
        this.tenTP = tenTP;
    }

    
    public Object[] toObjects() {
        return new Object[] {
            id, tenTP
        };
    }
}
