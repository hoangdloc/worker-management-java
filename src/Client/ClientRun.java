/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.IOException;

/**
 *
 * @author Hoang Loc
 */
public class ClientRun {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientView view = new ClientView();
        ClientControl control = new ClientControl(view);
        view.setVisible(true);
    }
}
