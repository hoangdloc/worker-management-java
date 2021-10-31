/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Model.CongNhan;
import Model.QueHuong;
import Model.ThongDiep;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang Loc
 */
public class ClientControl {
    private ClientView view;
    private int serverPort = 8888;
    private int clientPort = 6666;
    private String serverHost = "localhost";
    private DatagramSocket myClient;

    public ClientControl(ClientView view) throws IOException, ClassNotFoundException {
        openConnection();
        this.view = view;
        sendData(new ThongDiep(100, null));
        List<QueHuong> qhList = (List<QueHuong>) receiveData();
        this.view.addThemListener(new ThemListener());
        this.view.addHienThiListener(new HienThiListener());
        this.view.addTimKiemListener(new TimKiemListener());
        this.view.addLietKeListener(new LietkeListener());
        this.view.loadCityList(qhList);
    }
       
    class ThemListener implements ActionListener {
         @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CongNhan cn = view.getWorkerInput();
                sendData(new ThongDiep(200,cn));
                Object result = receiveData();
                if (result.equals("OK")) {
                    view.showMessage("Them cong nhan thanh cong");
                }                     
                else {
                    view.showMessage("Them cong nhan that bai!");
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class HienThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                sendData(new ThongDiep(111, null));
                List<CongNhan> result = (List<CongNhan>) receiveData();
                view.displayWorker(result);
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
    
    class TimKiemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String tenCongNhan = view.getSearchText();
                if (tenCongNhan.equals("")) {
                    view.showMessage("Chua nhap ten cong nhan!");
                    return;
                }
                sendData(new ThongDiep(112, tenCongNhan));
                List<CongNhan> result = (List<CongNhan>) receiveData();
                if (result.isEmpty()) {
                    view.showMessage("Khong co cong nhan nay trong he thong!");
                }
                else {
                    view.displayWorker(result);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    class LietkeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String tenTP = view.getTPInput();
                sendData(new ThongDiep(113, tenTP));
                List<CongNhan> result = (List<CongNhan>) receiveData();
                view.displayWorker(result);
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    private void openConnection() throws SocketException {
        myClient = new DatagramSocket(clientPort);
    }
    
    private void closeConnection() {
        myClient.close();
    }
    
    private void sendData(Object o) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            
            InetAddress IPAdress = InetAddress.getByName(serverHost);
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, serverPort);
            myClient.send(sendPacket);
    }
    
    private Object receiveData() throws IOException, ClassNotFoundException{
        byte[] receiveData = new byte[1000000];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        myClient.receive(receivePacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}
