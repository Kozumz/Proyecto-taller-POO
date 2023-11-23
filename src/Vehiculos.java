package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static src.FuncionesSQL.NuevaConexion;


public class Vehiculos extends JPanel{
	JTextField[] campos = new JTextField[6];
	JLabel lblNombreVehiculos = new JLabel();
	 	
	
	    
	private JPanel listaPanel = new JPanel();
	private Clientes clientesPanel;
	private String textoLblNombre;
	
	int rowCount = 1;
	
	

	private static final long serialVersionUID = 1L;

	public Vehiculos(Clientes clientesPanel) {



		this.clientesPanel = clientesPanel;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{980, 0};
		gridBagLayout.rowHeights = new int[]{580, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
			 
			 JPanel mainPanel = new JPanel();
			 GridBagConstraints gbc_mainPanel = new GridBagConstraints();
			 gbc_mainPanel.fill = GridBagConstraints.BOTH;
			 gbc_mainPanel.gridx = 0;
			 gbc_mainPanel.gridy = 0;
			 add(mainPanel, gbc_mainPanel);
			 GridBagLayout gbl_mainPanel = new GridBagLayout();
			 gbl_mainPanel.columnWidths = new int[]{1000, 0};
			 gbl_mainPanel.rowHeights = new int[]{85, 40, 459, 0};
			 gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			 gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			 mainPanel.setLayout(gbl_mainPanel);
			 
			 JPanel infoClientePanel = new JPanel();
			 GridBagConstraints gbc_infoClientePanel = new GridBagConstraints();
			 gbc_infoClientePanel.fill = GridBagConstraints.BOTH;
			 gbc_infoClientePanel.insets = new Insets(0, 0, 5, 0);
			 gbc_infoClientePanel.gridx = 0;
			 gbc_infoClientePanel.gridy = 0;
			 mainPanel.add(infoClientePanel, gbc_infoClientePanel);
			 GridBagLayout gbl_infoClientePanel = new GridBagLayout();
			 gbl_infoClientePanel.columnWidths = new int[]{196, 446, 0, 100, 33, 100, 33, 100, 0};
			 gbl_infoClientePanel.rowHeights = new int[]{43, 27, 0};
			 gbl_infoClientePanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			 gbl_infoClientePanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			 infoClientePanel.setLayout(gbl_infoClientePanel);
			 
			 
			 
			 
			 lblNombreVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 26));
			 GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			 gbc_lblNombre.gridwidth = 2;
			 gbc_lblNombre.anchor = GridBagConstraints.WEST;
			 gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
			 gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			 gbc_lblNombre.gridx = 0;
			 gbc_lblNombre.gridy = 0;
			 infoClientePanel.add(lblNombreVehiculos, gbc_lblNombre);
			 
			 JButton btnRegresar = new JButton("Regresar");
			 btnRegresar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		regresarPanelClientes();
			 	}
			 });
			 GridBagConstraints gbc_btnRegresar = new GridBagConstraints();
			 gbc_btnRegresar.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnRegresar.gridheight = 2;
			 gbc_btnRegresar.insets = new Insets(0, 0, 5, 5);
			 gbc_btnRegresar.gridx = 2;
			 gbc_btnRegresar.gridy = 0;
			 infoClientePanel.add(btnRegresar, gbc_btnRegresar);
			 
			 JButton btnModificar = new JButton("Modificar");
			 GridBagConstraints gbc_btnModificar = new GridBagConstraints();
			 gbc_btnModificar.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnModificar.gridheight = 2;
			 gbc_btnModificar.insets = new Insets(0, 0, 5, 5);
			 gbc_btnModificar.gridx = 4;
			 gbc_btnModificar.gridy = 0;
			 infoClientePanel.add(btnModificar, gbc_btnModificar);
			 
			 JButton btnNuevo = new JButton("Nuevo");
			 btnNuevo.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		abrirVentanaNuevoAuto();
			 	}
			 });
			 GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
			 gbc_btnNuevo.fill = GridBagConstraints.HORIZONTAL;
			 gbc_btnNuevo.gridheight = 2;
			 gbc_btnNuevo.insets = new Insets(0, 0, 5, 5);
			 gbc_btnNuevo.gridx = 6;
			 gbc_btnNuevo.gridy = 0;
			 infoClientePanel.add(btnNuevo, gbc_btnNuevo);
			 
			 JLabel lblId = new JLabel("ID #");
			 lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
			 GridBagConstraints gbc_lblId = new GridBagConstraints();
			 gbc_lblId.anchor = GridBagConstraints.WEST;
			 gbc_lblId.fill = GridBagConstraints.VERTICAL;
			 gbc_lblId.insets = new Insets(0, 0, 0, 5);
			 gbc_lblId.gridx = 0;
			 gbc_lblId.gridy = 1;
			 infoClientePanel.add(lblId, gbc_lblId);
			  
			  JPanel titulosPanel = new JPanel();
			  GridBagConstraints gbc_titulosPanel = new GridBagConstraints();
			  gbc_titulosPanel.anchor = GridBagConstraints.NORTH;
			  gbc_titulosPanel.fill = GridBagConstraints.HORIZONTAL;
			  gbc_titulosPanel.insets = new Insets(0, 0, 5, 0);
			  gbc_titulosPanel.gridx = 0;
			  gbc_titulosPanel.gridy = 1;
			  mainPanel.add(titulosPanel, gbc_titulosPanel);
			  GridBagLayout gbl_titulosPanel = new GridBagLayout();
			  gbl_titulosPanel.columnWidths = new int[]{163, 163, 163, 163, 163, 163, 0};
			  gbl_titulosPanel.rowHeights = new int[]{40, 0};
			  gbl_titulosPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			  gbl_titulosPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			  titulosPanel.setLayout(gbl_titulosPanel);
			  
			  JLabel lblMarca = new JLabel("Marca");
			  lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
			  lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblMarca = new GridBagConstraints();
			  gbc_lblMarca.fill = GridBagConstraints.BOTH;
			  gbc_lblMarca.insets = new Insets(0, 0, 0, 5);
			  gbc_lblMarca.gridx = 0;
			  gbc_lblMarca.gridy = 0;
			  titulosPanel.add(lblMarca, gbc_lblMarca);
			  
			  JLabel lblModelo = new JLabel("Modelo");
			  lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
			  lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblModelo = new GridBagConstraints();
			  gbc_lblModelo.fill = GridBagConstraints.BOTH;
			  gbc_lblModelo.insets = new Insets(0, 0, 0, 5);
			  gbc_lblModelo.gridx = 1;
			  gbc_lblModelo.gridy = 0;
			  titulosPanel.add(lblModelo, gbc_lblModelo);
			  
			  JLabel lblColor = new JLabel("Color");
			  lblColor.setHorizontalAlignment(SwingConstants.CENTER);
			  lblColor.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblColor = new GridBagConstraints();
			  gbc_lblColor.fill = GridBagConstraints.BOTH;
			  gbc_lblColor.insets = new Insets(0, 0, 0, 5);
			  gbc_lblColor.gridx = 2;
			  gbc_lblColor.gridy = 0;
			  titulosPanel.add(lblColor, gbc_lblColor);
			  
			  JLabel lblPlacas = new JLabel("Placas");
			  lblPlacas.setHorizontalAlignment(SwingConstants.CENTER);
			  lblPlacas.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblPlacas = new GridBagConstraints();
			  gbc_lblPlacas.fill = GridBagConstraints.BOTH;
			  gbc_lblPlacas.insets = new Insets(0, 0, 0, 5);
			  gbc_lblPlacas.gridx = 3;
			  gbc_lblPlacas.gridy = 0;
			  titulosPanel.add(lblPlacas, gbc_lblPlacas);
			  
			  JLabel lblAño = new JLabel("Año");
			  lblAño.setHorizontalAlignment(SwingConstants.CENTER);
			  lblAño.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblAño = new GridBagConstraints();
			  gbc_lblAño.fill = GridBagConstraints.BOTH;
			  gbc_lblAño.insets = new Insets(0, 0, 0, 5);
			  gbc_lblAño.gridx = 4;
			  gbc_lblAño.gridy = 0;
			  titulosPanel.add(lblAño, gbc_lblAño);
			  
			  JLabel lblKm = new JLabel("KM");
			  lblKm.setHorizontalAlignment(SwingConstants.CENTER);
			  lblKm.setFont(new Font("Tahoma", Font.PLAIN, 25));
			  GridBagConstraints gbc_lblKm = new GridBagConstraints();
			  gbc_lblKm.fill = GridBagConstraints.BOTH;
			  gbc_lblKm.gridx = 5;
			  gbc_lblKm.gridy = 0;
			  titulosPanel.add(lblKm, gbc_lblKm);
			 
			 //JSCROLLPANE...........................................................................................
			  JScrollPane scrollPane = new JScrollPane();
			  scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			  scrollPane.setPreferredSize(new Dimension(0,500));
			  GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			  gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			  gbc_scrollPane_1.gridx = 0;
			  gbc_scrollPane_1.gridy = 2;
			  mainPanel.add(scrollPane, gbc_scrollPane_1);
			  scrollPane.setViewportView(listaPanel);
			  listaPanel.setLayout(null);
			  
			  
			 

	}
	
	public void mostrarVehiculos() {
		setVisible(true);
		obtenerNombre();
		listaPanel.removeAll();
		cargarAutosDesdeBD();
		
	}
	
	public void regresarPanelClientes() {
    	setVisible(false);
    	clientesPanel.regresarPanelClientes();
    	
    }
	
	private void abrirVentanaNuevoAuto() {
        // Crear un array de JTextField para los campos
		campos[0] = new JTextField(); // Marca
	    campos[1] = new JTextField(); // Modelo
	    campos[2] = new JTextField(); // Color
	    campos[3] = new JTextField(); // Placas
	    campos[4] = new JTextField(); // Año
	    campos[5] = new JTextField(); // Kilometraje

        // Crear un panel para colocar los campos
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Marca:"));
        panel.add(campos[0]);
        panel.add(new JLabel("Modelo:"));
        panel.add(campos[1]);
        panel.add(new JLabel("Color:"));
        panel.add(campos[2]);
        panel.add(new JLabel("Placas:"));
        panel.add(campos[3]);
        panel.add(new JLabel("Año:"));
        panel.add(campos[4]);
        panel.add(new JLabel("Kilometraje:"));
        panel.add(campos[5]);

        // Mostrar el JOptionPane con los campos
        int resultado = JOptionPane.showConfirmDialog(null, panel, "Nuevo Auto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        // guardar 
        try {
        	guardarAuto();
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	private void guardarAuto() {
		try {
	        
	        String marca = campos[0].getText();
	        String modelo = campos[1].getText();
	        String color = campos[2].getText();
	        String placas = campos[3].getText();
	        String año = campos[4].getText();
	        String kilometraje = campos[5].getText();
	     // Conectar a la base de datos (ajusta la URL, usuario y contraseña según tu configuración)
	        
	        

	        // Preparar la consulta SQL para insertar en la tabla 'autos'
	        String consulta = "INSERT INTO autos (Marca, Modelo, Color, Placas, Año, Km) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = NuevaConexion.prepareStatement(consulta);
	        preparedStatement.setString(1, marca);
	        preparedStatement.setString(2, modelo);
	        preparedStatement.setString(3, color);
	        preparedStatement.setString(4, placas);
	        preparedStatement.setString(5, año);
	        preparedStatement.setString(6, kilometraje);

	        // Ejecutar la consulta
	        preparedStatement.executeUpdate();

	        // Cerrar la conexión y el PreparedStatement
	        preparedStatement.close();

	        // Mensaje de éxito (puedes cambiarlo según tus necesidades)
	        JOptionPane.showMessageDialog(null, "Auto guardado exitosamente en la base de datos");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción según tus necesidades
	        JOptionPane.showMessageDialog(null, "Error al guardar el auto en la base de datos");
	    }
		
		listaPanel.removeAll();
        listaPanel.repaint();
        cargarAutosDesdeBD();
	}
	
	public void obtenerNombre() {
         textoLblNombre = clientesPanel.obtenerTextoLblNombre();
         if(!textoLblNombre.equals("Nombre")) {
         lblNombreVehiculos.setText("Automoviles de " + textoLblNombre);
         }else {
        	 lblNombreVehiculos.setText("Todos los automoviles");
         }
    }
	
	//Metodo para crear panel de Automovil.................................................................................
    private JPanel createPanelWithLabels(String marca, String modelo, String color, String placas, String año, String km) {
    	
    	 JPanel panel = new JPanel();
		 panel.setBackground(Color.WHITE);
		 panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		 listaPanel.add(panel);
		 GridBagLayout gbl_panel = new GridBagLayout();
		 gbl_panel.columnWidths = new int[]{55, 474, 239, 168, 98};
		 gbl_panel.rowHeights = new int[]{30, 0};
		 gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		 gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		 panel.setLayout(gbl_panel);
        
           	JLabel lblMarca = new JLabel(marca);
            lblMarca.setMinimumSize(new Dimension (0,25));
            
            JLabel lblModelo = new JLabel(modelo);
            lblModelo.setMinimumSize(new Dimension (0,25));
            
            JLabel lblColor = new JLabel(color);
            lblColor.setMinimumSize(new Dimension (0,25));
            
            JLabel lblPLacas = new JLabel(placas);
            lblPLacas.setMinimumSize(new Dimension (0,25));
            
            JLabel lblAño = new JLabel(año);
            lblAño.setMinimumSize(new Dimension (0,25));
            
            JLabel lblKm = new JLabel(km);
            lblKm.setMinimumSize(new Dimension(0,25));
            
            panel.setMinimumSize(new Dimension(0,30));
            panel.add(lblMarca);
            panel.add(lblModelo);
            panel.add(lblColor);
            panel.add(lblPLacas);
            panel.add(lblAño);
            panel.add(lblKm);
            
        return panel;
    }
    
    //Metodo para cargar autos desde la base de datos..........................................................
    private void cargarAutosDesdeBD() {
        try {
            
			String query = "SELECT Marca, Modelo, Color, Placas, Año, Km FROM autos";
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String marca = resultSet.getString("Marca");
                String modelo = resultSet.getString("Modelo");
                String color = resultSet.getString("Color");
                String placas = resultSet.getString("Placas");  
                String año = resultSet.getString("Año");
                String km = resultSet.getString("Km");
                createPanelWithLabels(marca, modelo, color, placas, año, km);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}


