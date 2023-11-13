package src;
import javax.swing.event.*;
import  javax.swing.*;
import java.awt.*;

public class MainFrame {
    MainFrame(){

        JFrame GUI = new JFrame("Taller Mecanico");
        Pestaña ventanaPdeVenta = new Pestaña();

        GUI.setLayout(null);
        GUI.setSize(1920,1080);
        GUI.setBackground(Color.white);
        GUI.setVisible(true);

        GUI.add(ventanaPdeVenta.MainPanel);

    }
    public static void main (String []args){
         MainFrame M  = new MainFrame();
    }
}
