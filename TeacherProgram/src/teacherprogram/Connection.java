/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherprogram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Hello (test)
 * @author Sam
 */
public class Connection {

    Socket socket;
    BufferedReader input;
    String username;
    //Teacher output example:
    public Connection(Socket s, int n) {
        socket = s;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {  
                    try {
                        String read = input.readLine();
                        System.out.println(read);
                        processInput(read);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        Thread thread2 = new Thread() {
            @Override
            public void run(){
                while (true){
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(" ");
                    } catch (Exception ex) {
                        System.out.println("DISCONNECTED");
                    }
                }
            }
        };
        thread2.start();
    }
    
    public void processInput(String in){
        String txt = MainClass.textField.getText();
        if(in.equals("DOWN")){
            txt = txt.substring(0, txt.indexOf(username)) + txt.substring(txt.indexOf(username) + username.length() + 2);
            MainClass.textField.setText(txt);
        }
        if(in.equals("UP")){
            txt = txt + username + "\n\r";
            MainClass.textField.setText(txt);
        }
        if(in.equals("QUIT")){
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
        if(in.contains("USERNAME:")){
            username = in.substring(in.indexOf(":") + 1);
        }
    }
}
