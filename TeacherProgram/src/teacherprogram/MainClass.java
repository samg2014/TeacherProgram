/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherprogram;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sam
 */
public class MainClass {

    public static JTextArea textField;
    private static boolean used = false;
    private static char ch = 'd';
    private static int num = 0, numSocks;
    private static ServerSocket ss;
    public static boolean removeFirstInLine = false;
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
        jframe.setSize(400, 600);
        jframe.add(textField);
        
        jframe.setVisible(true);

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!used) {
                        if(ch == ' ')
                        {
                            //removeFirstInLine = true;
                            used = true;
                            //System.out.println("Remove 1st in line");
                        }
                    }
                }
            }
        };
        thread.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    ss = new ServerSocket(42421);
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