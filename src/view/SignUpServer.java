package view;

import controller.LogInService;
import controller.SignupService;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SignUpServer {
    public void ser() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(2607);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogInService login = new LogInService();
        SignupService signup = new SignupService();
        try {
            while(true) {
                Socket socket = server.accept();
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                User user = null;
                boolean signuped = false;
                while (!signuped) {
                    String hehe = bf.readLine();
                    String username = hehe;
                    hehe = bf.readLine();
                    String password = hehe;
                    user = login.login(username, password);
                    int temp;
                    if (user == null) {
                        signuped = signup.saveData(username, password);
                        temp = 1;
                        ps.println(temp);
                    } else {
                        //ps.println(0);
                        temp = 0;
                        ps.println(temp);
                        //ps.println("NOT_FOUND");
                    }
                    System.out.println(temp);
                }
            }
        } catch (IOException e) {
            System.out.println("Deo duoc");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new SignUpServer().ser();
        // ChatServerGUI().setVisible(true);
    }

}