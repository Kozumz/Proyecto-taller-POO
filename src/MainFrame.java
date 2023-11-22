package src;

import javax.swing.*;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.FuncionesSQL.NuevaConexion;

public class MainFrame {
public static JFrame GUI = new JFrame("Mecanica");
public  JTabbedPane tabbedPane = new JTabbedPane();
static MySQLTableEditor panel2;

    MainFrame() {
        try {
            NuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/dbtaller" , "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Pestaña ventanaPdeVenta = new Pestaña();
        JPanel fondo = new JPanel();
        MySQLTableEditor panel2 = new MySQLTableEditor();
        TallerMecanicoCotizacion ventanaCotizaciones = new TallerMecanicoCotizacion();
        TallerMecanicoCitas ventanaCitas = new TallerMecanicoCitas();
        fondo.setBounds(0, 0, 1920, 1080);
        fondo.setBackground(Color.decode("#FF8764"));
        tabbedPane.setBounds(200, 150, 1300, 800);



        GUI.setLayout(null);
        GUI.setSize(1920, 1080);
        GUI.setBackground(Color.white);
        GUI.setVisible(true);
        GUI.add(tabbedPane);
        ventanaPdeVenta.panelCompras.setBounds(1550, 150, 300, 680);
        GUI.add(ventanaPdeVenta.panelCompras);
        GUI.add(fondo);
        tabbedPane.addTab("Punto de venta", ventanaPdeVenta.scrollPane);
        tabbedPane.addTab("Cotizaciones ",ventanaCotizaciones.createAndShowCot());
        tabbedPane.addTab("Citas ",ventanaCitas.createAndShowCit() );
        tabbedPane.addTab("Inventario", MySQLTableEditor.panel);

        //GUI.add(ventanaPdeVenta.MainPanel);
    }

        // Agregar pestañas al TabbedPane
    public static void main(String []args){
        new MainFrame();
    }

}



