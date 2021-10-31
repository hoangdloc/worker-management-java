/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Model.CongNhan;
import Model.QueHuong;
import Model.ThongDiep;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Loc
 */
public class ServerControl {
    private ServerView view;
    private Connection con;
    private DatagramSocket myServer;
    private int serverPort = 8888;
    private DatagramPacket receivePacket = null;
    private List<QueHuong> qhlist;

    public ServerControl(ServerView view) throws IOException, ClassNotFoundException, SQLException {
        this.view = view;
        getDBConnection("workermanagement", "root", "");
        openServer(serverPort);
        view.showMessage("UDP server is running...");
        qhlist = getCityList();
        while (true) {
            listening();
        }
    }
    
    private void getDBConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, username, password);
        }
        catch (Exception ex) {
            view.showMessage(ex.getStackTrace().toString());
        }
    }
    
    private void openServer(int portNumber) throws SocketException {
            myServer = new DatagramSocket(portNumber);
    }
    
    private void sendData(Object result) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(result);
            oos.flush();
            
            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
            myServer.send(sendPacket);
    }
    
    private Object receiveData() throws IOException, ClassNotFoundException {
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        myServer.receive(receivePacket);
            
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
    
    private void listening() throws IOException, ClassNotFoundException, SQLException {
        ThongDiep thongDiep = (ThongDiep) receiveData();
        int kieu = thongDiep.getKieuThongDiep();
        Object result = null;
        result = switch (kieu) {
            case 200 -> themNhanVien((CongNhan) thongDiep.getObj());
            case 111 -> hienThiNhanVien();
            case 112 -> timKiemNhanVien((String) thongDiep.getObj());
            case 113 -> lietKeNhanVien((String) thongDiep.getObj());
            default -> qhlist;
        };
        sendData(result);
    }
    
    private List<QueHuong> getCityList() throws SQLException {
        List<QueHuong> cities = new ArrayList<>();
        String query = "SELECT * FROM tblcity";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            QueHuong city = new QueHuong(id, name);
            cities.add(city);
        }
        return cities;
    }
    
    private String themNhanVien(CongNhan cn) throws SQLException {
        String query = "INSERT INTO tblworker (id, fullname, dob, tblCityid, gender, coefficientSalary) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement prst = con.prepareStatement(query);
        prst.setInt(1, cn.getId());
        prst.setString(2, cn.getHoTen());
        prst.setInt(3, cn.getNamSinh());
        prst.setInt(4, cn.getQueHuong().getId());
        prst.setString(5, cn.getGioiTinh());
        prst.setDouble(6, cn.getHeSoLuong());
        prst.execute();
        prst.close();
        return "OK";
    }
    
    private List<CongNhan> hienThiNhanVien() throws SQLException {
        List<CongNhan> workers = new ArrayList<>();
        String query = "SELECT * FROM tblworker";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int congnhanid = rs.getInt(1);
            String hoten = rs.getString(2);
            int namsinh = rs.getInt(3);
            int queHuongid = rs.getInt(4);
            String gioitinh = rs.getString(5);
            double hesoluong = rs.getDouble(6);
            QueHuong quehuong = qhlist.get(queHuongid - 1);
            CongNhan worker = new CongNhan(congnhanid, hoten, namsinh, quehuong, gioitinh, hesoluong);
            workers.add(worker);
        }
        return workers;
    }
    
    private List<CongNhan> timKiemNhanVien(String keyword) throws SQLException {
        List<CongNhan> workers = new ArrayList<>();
        String query = "SELECT * FROM tblworker WHERE fullname LIKE '%" + keyword + "%'";
        PreparedStatement prst = con.prepareStatement(query);
        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            int congnhanid = rs.getInt(1);
            String hoten = rs.getString(2);
            int namsinh = rs.getInt(3);
            int queHuongid = rs.getInt(4);
            String gioitinh = rs.getString(5);
            double hesoluong = rs.getDouble(6);
            QueHuong quehuong = qhlist.get(queHuongid - 1);
            CongNhan worker = new CongNhan(congnhanid, hoten, namsinh, quehuong, gioitinh, hesoluong);
            workers.add(worker);
        }
        return workers;
    }
    
    private List<CongNhan> lietKeNhanVien(String queHuongName) throws SQLException {
        List<CongNhan> workers = new ArrayList<>();
        int queHuongID = 0;
        for (int i = 0; i < qhlist.size(); i++) {
            if (qhlist.get(i).getTenTP().equals(queHuongName)) {
                queHuongID = i + 1;
                break;
            }
        }
        String query = "SELECT * FROM tblworker WHERE tblCityid = ?";
        PreparedStatement prst = con.prepareStatement(query);
        prst.setInt(1, queHuongID);
        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            int congnhanid = rs.getInt(1);
            String hoten = rs.getString(2);
            int namsinh = rs.getInt(3);
            int queHuongid = rs.getInt(4);
            String gioitinh = rs.getString(5);
            double hesoluong = rs.getDouble(6);
            QueHuong quehuong = qhlist.get(queHuongid - 1);
            CongNhan worker = new CongNhan(congnhanid, hoten, namsinh, quehuong, gioitinh, hesoluong);
            workers.add(worker);
        }
        return workers;
    }
}
