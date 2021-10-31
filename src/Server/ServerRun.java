/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Hoang Loc
 */
public class ServerRun {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerView view = new ServerView();
        ServerControl control = new ServerControl(view);
    }
}
