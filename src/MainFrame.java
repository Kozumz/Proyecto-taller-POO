package src;

import javax.swing.*;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.FuncionesSQL.NuevaConexion;

public class MainFrame {
    public static JFrame GUI = new JFrame("Mecanica");
    public JTabbedPane tabbedPane = new JTabbedPane();
    static MySQLTableEditor panel2;

    MainFrame() {
        try {
            NuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/dbtaller", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        Pestaña ventanaPdeVenta = new Pestaña();
        JPanel fondo = new JPanel();
        Clientes ventanaClientes = new Clientes();
        MySQLTableEditor panel2 = new MySQLTableEditor();
        TallerMecanicoCotizacion ventanaCotizaciones = new TallerMecanicoCotizacion();
        TallerMecanicoCitas ventanaCitas = new TallerMecanicoCitas();

        // Utilizar un LayoutManager apropiado
        GUI.setLayout(new BorderLayout());

        fondo.setBackground(Color.decode("#FF8764"));
        tabbedPane.setPreferredSize(new Dimension(1300, 800));

        GUI.add(tabbedPane, BorderLayout.CENTER);
        ventanaPdeVenta.PanelDerecho.setPreferredSize(new Dimension(300, 680));
        GUI.add(ventanaPdeVenta.PanelDerecho, BorderLayout.EAST);
        GUI.add(fondo, BorderLayout.NORTH);
        tabbedPane.addTab("Punto de venta", ventanaPdeVenta.scrollPane);
        tabbedPane.addTab("Cotizaciones ", ventanaCotizaciones.createAndShowCot());
        tabbedPane.addTab("Citas ", ventanaCitas.createAndShowCit());
        tabbedPane.addTab("Inventario", MySQLTableEditor.panel);
        tabbedPane.addTab("Clientes", ventanaClientes.contentPane);
        tabbedPane.addTab("Vehiculos", new Vehiculos(ventanaClientes));
        Vehiculos vehiculosPanel = (Vehiculos) tabbedPane.getComponentAt(tabbedPane.getTabCount() - 1);
        vehiculosPanel.mostrarVehiculos();


        // Permitir redimensionar la ventana
        GUI.setResizable(true);

        // Configurar el cierre de la aplicación
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        GUI.pack();
        GUI.setLocationRelativeTo(null); // Centrar en la pantalla
        GUI.setVisible(true);
    }

    public static void main(String[] args) {
            new MainFrame();
        }
    }
