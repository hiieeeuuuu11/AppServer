package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import controller.LogInService;
import dao.ConfigBTL;
import model.User;

import javax.swing.*;

public class ServerGUI {
    public void ser() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(ConfigBTL.getSkPortLogin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogInService login = new LogInService();
        try {
            while (true)
            {
                Socket socket = server.accept();
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                User user=null;
                while(user==null) {
                    String hehe = bf.readLine();
                    String username = hehe;
                    hehe = bf.readLine();
                    String password = hehe;
                    user = login.login(username,password);
                    if(user!=null) {

                        ps.println(user.getId());
                        ps.println(user.getUsername());
                        ps.println(user.getPassword());
                    }else{
                        ps.println(0);
                        ps.println("NOT_FOUND");
                        ps.println("NOT_FOUND");
                    }
                    System.out.println(user);
                }
            }

        } catch (IOException e) {
            System.out.println("Deo duoc");
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Server.listSK = new ArrayList<>();
                Server server = new Server(ConfigBTL.getSkPortChat());
                try {
                    server.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //new ChatServerGUI().setVisible(true);
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                new SignUpServer().ser();
            }
        });
        t.start();
        t1.start();
        new ServerGUI().ser();


    }



}