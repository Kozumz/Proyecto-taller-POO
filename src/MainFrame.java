package src;
import javax.swing.event.*;
import  javax.swing.*;
import java.awt.*;

public class MainFrame {
    MainFrame(){
        JFrame GUI = new JFrame("Taller Mecanico");
        GUI.setLayout(null);
        GUI.setSize(1920,1080);

        Pestaña ventanaPdeVenta = new Pestaña();
        GUI.add(ventanaPdeVenta.panel);

        ventanaPdeVenta.panel.setBounds(0,0,1920,1080);
        GUI.getContentPane().setBackground(Color.decode("#023047"));
        GUI.setVisible(true);

    }
    public static void main (String []args){
         MainFrame M  = new MainFrame();
    }
}
