/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherprogram;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sam
 */
public class MainClass {

    public static JTextArea textField;
    public static boolean used = false;
    public static char ch = ' ';
    public static int num = 0;
    public static ServerSocket ss;
    public static int numSocks;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        textField = new JTextArea();
        textField.setEditable(false);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                ch = event.getKeyChar();
                used = false;
            }
        });
        JFrame jframe = new JFrame();
        jframe.add(textField);
        jframe.setSize(400, 100);
        jframe.setVisible(true);

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!used) {
//                        String txt = textField.getText();
//                        txt = txt + ch;
//                        used = true;
//                        textField.setText(txt);
                    }
                }
            }
        };
        thread.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    ss = new ServerSocket(42420);
                } catch (BindException e) {
                    System.out.println(e);
                }
                catch(IOException e) {
                    System.out.println(e);
                }
                while (true) {
                    try
                     {
                         Socket socket = ss.accept();
                         Connection con = new Connection(socket, numSocks);
                         numSocks++;
                     }
                     catch(Exception e)
                     {
                     }
                }
            }
        };
        thread2.start();
    }
}