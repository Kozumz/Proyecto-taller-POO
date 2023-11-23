package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;

import static src.FuncionesSQL.NuevaConexion;

public class Clientes extends JFrame {
	//RowCount para paneles de clientes
    int rowCount = 1;
	
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JPanel mainPanel;
	private JPanel listaPanel;
	private JPanel clientesPanel;
	
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField telefonoTextField;
	private JTextField correoTextField;
	
	private JLabel lblId = new JLabel("ID");
	public  JLabel lblNombre = new JLabel("Nombre");
	private JLabel lblTelefono = new JLabel("Telefono");
	private JLabel lblCorreo = new JLabel("Correo");
	private JLabel lblFechaRegistro = new JLabel("Fecha de registro");
	
	private Vehiculos vehiculosPanel = new Vehiculos(this);


	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Clientes() {
		iniciarUI();
	}
	
	private void iniciarUI() {
		// Content Pane
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1169, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
		crearMainPanel();
		crearInputPanel();
		crearInfoPanel();
		crearClientesPanel();
				
	}
				
	private void crearMainPanel(){
		
		// Main Panel
		mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mainPanel.gridheight = 2;
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		contentPane.add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0,1.0};
		gbl_mainPanel.rowWeights = new double[]{1.0, 1.0,0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
	
			// Logo Panel.........................................................................................
			JPanel logoPanel = new JPanel();
			GridBagConstraints gbc_logoPanel = new GridBagConstraints();
			gbc_logoPanel.fill = GridBagConstraints.BOTH;
			gbc_logoPanel.gridwidth = 2;
			gbc_logoPanel.weighty = 0.2;
			gbc_logoPanel.insets = new Insets(0, 0, 5, 5);
			gbc_logoPanel.gridx = 0;
			gbc_logoPanel.gridy = 0;
			mainPanel.add(logoPanel, gbc_logoPanel);
			GridBagLayout gbl_logoPanel = new GridBagLayout();
			gbl_logoPanel.columnWidths = new int[]{0, 0};
			gbl_logoPanel.rowHeights = new int[]{0, 0};
			gbl_logoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_logoPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			logoPanel.setLayout(gbl_logoPanel);
				
				// logoLabel de logoPanel.........................................................................
				//JLabel logoLabel = new JLabel("New label");
				GridBagConstraints gbc_logoLabel = new GridBagConstraints();
				gbc_logoLabel.gridx = 0;
				gbc_logoLabel.gridy = 0;
	}
				
	private void crearInputPanel() {
		// Input Panel.........................................................................................
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		inputPanel.setBackground(new Color(240, 240, 240));
		
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.insets = new Insets(0, 0, 5, 5);
		gbc_inputPanel.gridx = 0;
		gbc_inputPanel.gridy = 1;
		mainPanel.add(inputPanel, gbc_inputPanel);
		
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{107, 145, 145, 145, 0};
		gbl_inputPanel.rowHeights = new int[]{34, 34, 34, 34, 39, 0};
		gbl_inputPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
			
			// idLabel de inputPanel..............................................................
			JLabel idLabel = new JLabel("ID:");
			idLabel.setHorizontalAlignment(SwingConstants.LEFT);
			idLabel.setFont(new Font("Yu Gothic UI Semibold", idLabel.getFont().getStyle(), 25));
			GridBagConstraints gbc_idLabel = new GridBagConstraints();
			gbc_idLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_idLabel.insets = new Insets(0, 0, 5, 5);
			gbc_idLabel.gridx = 0;
			gbc_idLabel.gridy = 0;
			inputPanel.add(idLabel, gbc_idLabel);
			
			// idTextField de inputPanel..............................................................
			idTextField = new JTextField();
			idTextField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			GridBagConstraints gbc_idTextField = new GridBagConstraints();
			gbc_idTextField.gridwidth = 3;
			gbc_idTextField.fill = GridBagConstraints.BOTH;
			gbc_idTextField.insets = new Insets(0, 0, 5, 0);
			gbc_idTextField.gridx = 1;
			gbc_idTextField.gridy = 0;
			inputPanel.add(idTextField, gbc_idTextField);
			idTextField.setColumns(10);
			
			// nombreLabel de inputPanel..............................................................
			JLabel nombreLabel = new JLabel("Nombre:");
			nombreLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
			GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
			gbc_nombreLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nombreLabel.gridx = 0;
			gbc_nombreLabel.gridy = 1;
			inputPanel.add(nombreLabel, gbc_nombreLabel);
			
			// nombreTextField de inputPanel..............................................................
			nombreTextField = new JTextField();
			nombreTextField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			nombreTextField.setColumns(10);
			GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
			gbc_nombreTextField.gridwidth = 3;
			gbc_nombreTextField.fill = GridBagConstraints.BOTH;
			gbc_nombreTextField.insets = new Insets(0, 0, 5, 0);
			gbc_nombreTextField.gridx = 1;
			gbc_nombreTextField.gridy = 1;
			inputPanel.add(nombreTextField, gbc_nombreTextField);
			
			// telefonoLabel de inputPanel..............................................................
			JLabel telefonoLabel = new JLabel("Telefono:");
			telefonoLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
			GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
			gbc_telefonoLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_telefonoLabel.gridx = 0;
			gbc_telefonoLabel.gridy = 2;
			inputPanel.add(telefonoLabel, gbc_telefonoLabel);
			
			// telefonoTextField de inputPanel..............................................................
			telefonoTextField = new JTextField();
			telefonoTextField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			telefonoTextField.setColumns(10);
			GridBagConstraints gbc_telefonoTextField = new GridBagConstraints();
			gbc_telefonoTextField.gridwidth = 3;
			gbc_telefonoTextField.fill = GridBagConstraints.BOTH;
			gbc_telefonoTextField.insets = new Insets(0, 0, 5, 0);
			gbc_telefonoTextField.gridx = 1;
			gbc_telefonoTextField.gridy = 2;
			inputPanel.add(telefonoTextField, gbc_telefonoTextField);
			
			// correoLabel de inputPanel..............................................................
			JLabel correoLabel = new JLabel("Correo:");
			correoLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
			GridBagConstraints gbc_correoLabel = new GridBagConstraints();
			gbc_correoLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_correoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_correoLabel.gridx = 0;
			gbc_correoLabel.gridy = 3;
			inputPanel.add(correoLabel, gbc_correoLabel);
			
			// correoTextField de inputPanel..............................................................
			correoTextField = new JTextField();
			correoTextField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			correoTextField.setColumns(10);
			GridBagConstraints gbc_correoTextField = new GridBagConstraints();
			gbc_correoTextField.gridwidth = 3;
			gbc_correoTextField.fill = GridBagConstraints.BOTH;
			gbc_correoTextField.insets = new Insets(0, 0, 5, 0);
			gbc_correoTextField.gridx = 1;
			gbc_correoTextField.gridy = 3;
			inputPanel.add(correoTextField, gbc_correoTextField);
			
			// buscarButtonPanel de inputPanel............................................................
			JPanel buscarButtonPanel = new JPanel();
			buscarButtonPanel.setBackground(Color.LIGHT_GRAY);
			GridBagConstraints gbc_buscarButtonPanel = new GridBagConstraints();
			gbc_buscarButtonPanel.insets = new Insets(0, 0, 0, 5);
			gbc_buscarButtonPanel.fill = GridBagConstraints.BOTH;
			gbc_buscarButtonPanel.gridx = 2;
			gbc_buscarButtonPanel.gridy = 4;
			inputPanel.add(buscarButtonPanel, gbc_buscarButtonPanel);
			buscarButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
				
				// buscarButton de buscarButtonPanel.......................................................
				JButton buscarButton = new JButton("Buscar");
				buscarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String campo = ""; 
		                String valor = ""; 
		
		                
		                if (!idTextField.getText().trim().isEmpty()) {
		                    campo = "id";
		                    valor = idTextField.getText().trim();
		                }else if (!nombreTextField.getText().trim().isEmpty()) {
		                    campo = "nombre";
		                    valor = nombreTextField.getText().trim();
		                } else if (!telefonoTextField.getText().trim().isEmpty()) {
		                    campo = "telefono";
		                    valor = telefonoTextField.getText().trim();
		                } else if (!correoTextField.getText().trim().isEmpty()) {
		                    campo = "correo";
		                    valor = correoTextField.getText().trim();
		                } else {
		                    
		                    JOptionPane.showMessageDialog(null, "Introduzca un valor de búsqueda válido.");
		                    return;
		                }
		
		                buscarPersona(campo, valor);
		            }
		        });
				
				buscarButtonPanel.add(buscarButton);
			
			// guardarButtonPanel de inputPanel............................................................
			JPanel guardarButtonPanel = new JPanel();
			guardarButtonPanel.setBackground(Color.LIGHT_GRAY);
			GridBagConstraints gbc_guardarButtonPanel = new GridBagConstraints();
			gbc_guardarButtonPanel.fill = GridBagConstraints.BOTH;
			gbc_guardarButtonPanel.gridx = 3;
			gbc_guardarButtonPanel.gridy = 4;
			inputPanel.add(guardarButtonPanel, gbc_guardarButtonPanel);
			guardarButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
			
				// guardarButton de guardarButtonPanel.....................................................
				JButton guardarButton = new JButton("Guardar");
				guardarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 guardarPersona();
					}
				});
				
				guardarButtonPanel.add(guardarButton);
				}
	
	private void crearInfoPanel() {
		// infoPanel...........................................................................................
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_infoPanel.gridx = 1;
		gbc_infoPanel.gridy = 1;
		mainPanel.add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{265, 132, 160, 0};
		gbl_infoPanel.rowHeights = new int[]{38, 38, 38, 38, 38};
		gbl_infoPanel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		infoPanel.setLayout(gbl_infoPanel);
				
		//JLABEL Id..................................................................................
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		infoPanel.add(lblId, gbc_lblId);
		
		//JLABEL AutomovilesRegistrados..................................................................................
		JLabel lblAutomovilesRegistrados = new JLabel("Automoviles registrados");
		lblAutomovilesRegistrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutomovilesRegistrados.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAutomovilesRegistrados = new GridBagConstraints();
		gbc_lblAutomovilesRegistrados.fill = GridBagConstraints.BOTH;
		gbc_lblAutomovilesRegistrados.insets = new Insets(0, 0, 5, 0);
		gbc_lblAutomovilesRegistrados.gridwidth = 2;
		gbc_lblAutomovilesRegistrados.gridx = 1;
		gbc_lblAutomovilesRegistrados.gridy = 0;
		infoPanel.add(lblAutomovilesRegistrados, gbc_lblAutomovilesRegistrados);
		
		//JLABEL Nombre..................................................................................
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		infoPanel.add(lblNombre, gbc_lblNombre);
		
		//JBUTTON VerAutomoviles.................................................................................
		JButton btnVerAutomoviles = new JButton("Ver automoviles");
		GridBagConstraints gbc_btnVerAutomoviles = new GridBagConstraints();
		gbc_btnVerAutomoviles.fill = GridBagConstraints.VERTICAL;
		gbc_btnVerAutomoviles.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerAutomoviles.gridx = 2;
		gbc_btnVerAutomoviles.gridy = 1;
		infoPanel.add(btnVerAutomoviles, gbc_btnVerAutomoviles);
		
		btnVerAutomoviles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanelVehiculos();
				
			}
		});
		
		
	
		//JLABEL NumDeAutomoviles..................................................................................
		JLabel lblNumDeAutomoviles = new JLabel("0");
		lblNumDeAutomoviles.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumDeAutomoviles.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNumDeAutomoviles = new GridBagConstraints();
		gbc_lblNumDeAutomoviles.fill = GridBagConstraints.BOTH;
		gbc_lblNumDeAutomoviles.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumDeAutomoviles.gridx = 1;
		gbc_lblNumDeAutomoviles.gridy = 1;
		infoPanel.add(lblNumDeAutomoviles, gbc_lblNumDeAutomoviles);
		
		//JLABEL Telefono..................................................................................
		lblTelefono.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 0;
		gbc_lblTelefono.gridy = 2;
		infoPanel.add(lblTelefono, gbc_lblTelefono);
		
		//JLABEL CitasAgendadas..................................................................................
		JLabel lblCitasAgendadas = new JLabel("Citas agendadas");
		lblCitasAgendadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitasAgendadas.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCitasAgendadas = new GridBagConstraints();
		gbc_lblCitasAgendadas.fill = GridBagConstraints.BOTH;
		gbc_lblCitasAgendadas.insets = new Insets(0, 0, 5, 0);
		gbc_lblCitasAgendadas.gridwidth = 2;
		gbc_lblCitasAgendadas.gridx = 1;
		gbc_lblCitasAgendadas.gridy = 2;
		infoPanel.add(lblCitasAgendadas, gbc_lblCitasAgendadas);
		
		//JLABEL Correo..................................................................................
		lblCorreo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 0;
		gbc_lblCorreo.gridy = 3;
		infoPanel.add(lblCorreo, gbc_lblCorreo);
		
		//JLABEL NumDeCitas..................................................................................
		JLabel lblNumDeCitas = new JLabel("0");
		lblNumDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumDeCitas.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNumDeCitas = new GridBagConstraints();
		gbc_lblNumDeCitas.fill = GridBagConstraints.BOTH;
		gbc_lblNumDeCitas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumDeCitas.gridx = 1;
		gbc_lblNumDeCitas.gridy = 3;
		infoPanel.add(lblNumDeCitas, gbc_lblNumDeCitas);
		
		//JBUTTON btnVerCitas................................................................................
		JButton btnVerCitas = new JButton("Ver citas");
		GridBagConstraints gbc_btnVerCitas = new GridBagConstraints();
		gbc_btnVerCitas.fill = GridBagConstraints.VERTICAL;
		gbc_btnVerCitas.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerCitas.gridx = 2;
		gbc_btnVerCitas.gridy = 3;
		infoPanel.add(btnVerCitas, gbc_btnVerCitas);
		
		//JLABEL FechaRegistro..................................................................................
		lblFechaRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRegistro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblFechaRegistro = new GridBagConstraints();
		gbc_lblFechaRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFechaRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_lblFechaRegistro.gridx = 0;
		gbc_lblFechaRegistro.gridy = 4;
		infoPanel.add(lblFechaRegistro, gbc_lblFechaRegistro);
		
		//JBUTTON btnHistorialCitas..........................................................................
		JButton btnHistorialCitas = new JButton("Historial de citas");
		GridBagConstraints gbc_btnHistorialCitas = new GridBagConstraints();
		gbc_btnHistorialCitas.fill = GridBagConstraints.VERTICAL;
		gbc_btnHistorialCitas.gridx = 2;
		gbc_btnHistorialCitas.gridy = 4;
		infoPanel.add(btnHistorialCitas, gbc_btnHistorialCitas);
		}
			
	private void crearClientesPanel() {
		// clientesPanel .............................................................................................
		clientesPanel = new JPanel();
		 
		GridBagConstraints gbc_clientesPanel = new GridBagConstraints();
		gbc_clientesPanel.fill = GridBagConstraints.BOTH;
		gbc_clientesPanel.gridx = 0;
		gbc_clientesPanel.gridy = 2;
		contentPane.add(clientesPanel, gbc_clientesPanel);
		
		clientesPanel.setBackground(UIManager.getColor("Panel.background"));
		clientesPanel.setBorder(new EmptyBorder(10, 10, 10, 0));
		
		GridBagLayout gbl_clientesPanel = new GridBagLayout();
		gbl_clientesPanel.columnWidths = new int[]{63, 480, 240, 160, 123};
		gbl_clientesPanel.rowHeights = new int[]{25, 160, 0};
		gbl_clientesPanel.columnWeights = new double[]{ 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_clientesPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		clientesPanel.setLayout(gbl_clientesPanel);
		 
			 // lblID de clientesPanel................................................................
			 JLabel lblID = new JLabel("ID");
			 lblID.setHorizontalAlignment(SwingConstants.CENTER);
			 lblID.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
			 GridBagConstraints gbc_lblID = new GridBagConstraints();
			 gbc_lblID.insets = new Insets(0, 0, 5, 5);
			 gbc_lblID.gridx = 0;
			 gbc_lblID.gridy = 0;
			 clientesPanel.add(lblID, gbc_lblID);
			 
			 // lblNOMBRE de clientesPanel................................................................
			 JLabel lblNOMBRE = new JLabel("Nombre");
			 lblNOMBRE.setHorizontalAlignment(SwingConstants.CENTER);
			 lblNOMBRE.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
			 GridBagConstraints gbc_lblNOMBRE = new GridBagConstraints();
			 gbc_lblNOMBRE.insets = new Insets(0, 0, 5, 5);
			 gbc_lblNOMBRE.gridx = 1;
			 gbc_lblNOMBRE.gridy = 0;
			 clientesPanel.add(lblNOMBRE, gbc_lblNOMBRE);
			 
			 // lblTELEFONO de clientesPanel................................................................
			 JLabel lblTELEFONO = new JLabel("Telefono");
			 lblTELEFONO.setHorizontalAlignment(SwingConstants.CENTER);
			 lblTELEFONO.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
			 GridBagConstraints gbc_lblTELEFONO = new GridBagConstraints();
			 gbc_lblTELEFONO.insets = new Insets(0, 0, 5, 5);
			 gbc_lblTELEFONO.gridx = 2;
			 gbc_lblTELEFONO.gridy = 0;
			 clientesPanel.add(lblTELEFONO, gbc_lblTELEFONO);
			 
			 // lblCITAS de clientesPanel................................................................
			 JLabel lblCITAS = new JLabel("Citas");
			 lblCITAS.setHorizontalAlignment(SwingConstants.CENTER);
			 lblCITAS.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
			 GridBagConstraints gbc_lblCITAS = new GridBagConstraints();
			 gbc_lblCITAS.insets = new Insets(0, 0, 5, 5);
			 gbc_lblCITAS.gridx = 3;
			 gbc_lblCITAS.gridy = 0;
			 clientesPanel.add(lblCITAS, gbc_lblCITAS);
			 
			 // lblMAS de clientesPanel................................................................
			 JLabel lblMAS = new JLabel("Mas");
			 lblMAS.setHorizontalAlignment(SwingConstants.CENTER);
			 lblMAS.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
			 GridBagConstraints gbc_lblMAS = new GridBagConstraints();
			 gbc_lblMAS.insets = new Insets(0, 0, 5, 0);
			 gbc_lblMAS.gridx = 4;
			 gbc_lblMAS.gridy = 0;
			 clientesPanel.add(lblMAS, gbc_lblMAS);
			 
			 //JSCROLLPANE...........................................................................................
			 JScrollPane scrollPane = new JScrollPane(listaPanel);
			 scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			 GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			 gbc_scrollPane.gridwidth = 5;
			 gbc_scrollPane.insets = new Insets(0, 0, 0, 10);
			 gbc_scrollPane.fill = GridBagConstraints.BOTH;
			 gbc_scrollPane.gridx = 0;
			 gbc_scrollPane.gridy = 1;	
			 scrollPane.setPreferredSize(new Dimension(0,500));
			 clientesPanel.add(scrollPane, gbc_scrollPane);
			 
			 //JPANEL listaPanel de scrollPane..........................................................................................
			 listaPanel = new JPanel();	
			 scrollPane.setViewportView(listaPanel);
			 listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.X_AXIS));
				 
			 cargarClientesDesdeBD();
			
		}
		
	//Metodo para guardar cliente.................................................................................
    private void guardarPersona() {
    	Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
    	if(!nombreTextField.getText().isEmpty() && !telefonoTextField.getText().isEmpty()) {
    		try {
            	PreparedStatement pst = NuevaConexion.prepareStatement("insert into clientes values(?,?,?,?,?)");
            	
            	pst.setString(1, "0");
            	pst.setString(2, nombreTextField.getText().trim());
            	pst.setString(3, telefonoTextField.getText().trim());
            	pst.setString(4, correoTextField.getText().trim());
            	pst.setDate(5, fechaActual);
            	pst.executeUpdate();

            	 nombreTextField.setText("");
                 telefonoTextField.setText("");
                 correoTextField.setText("");  
                 idTextField.setText("");
    		
    		}catch(Exception e) {
            	
            }
    		
    		}else {
    		JOptionPane.showMessageDialog(null, "Introduzca al menos un nombre y un telefono.");
    		
    	}
    	
    	
    	
    	listaPanel.removeAll();
        listaPanel.repaint();
        cargarClientesDesdeBD();
       
    	
    }
    
    //Metodo para agregar panel de cliente.........................................................................
    private void addPanel(String id, String nombre, String telefono, String correo) {
    	
		JPanel panel = createPanelWithLabels(id, nombre, telefono, correo);
        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 30));
        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));

        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.add(panel);

        rowCount++;
        listaPanel.revalidate();
        listaPanel.repaint();
    }
    
    //Metodo para buscar cliente..................................................................................
    private void buscarPersona(String campo, String valor) {
        try {
            PreparedStatement pst = null;
            ResultSet rs = null;

            if (campo.equalsIgnoreCase("id")) {
                pst = NuevaConexion.prepareStatement("SELECT * FROM clientes WHERE ID = ?");
            } else if (campo.equalsIgnoreCase("nombre")) {
                pst = NuevaConexion.prepareStatement("SELECT * FROM clientes WHERE NombreCliente = ?");
            } else if (campo.equalsIgnoreCase("telefono")) {
                pst = NuevaConexion.prepareStatement("SELECT * FROM clientes WHERE TelefonoCliente = ?");
            } else if (campo.equalsIgnoreCase("correo")) {
                pst = NuevaConexion.prepareStatement("SELECT * FROM clientes WHERE CorreoCliente = ?");
            } else {
                
                System.out.println("Campo de búsqueda no válido.");
                return;
            }

            pst.setString(1, valor);
            rs = pst.executeQuery();

            if (rs.next()) {
                lblId.setText("ID   #" + rs.getString("ID"));
            	lblNombre.setText(rs.getString("NombreCliente"));
                lblTelefono.setText(rs.getString("TelefonoCliente"));
                lblCorreo.setText(rs.getString("CorreoCliente"));
                lblFechaRegistro.setText("Cliente desde:  " + rs.getString("FechaRegistro"));
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no registrado.");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Metodo para guardar cliente.................................................................................
    private JPanel createPanelWithLabels(String id, String nombre, String telefono, String correo) {
    	
    	 JPanel panel = new JPanel();
		 panel.setBackground(Color.WHITE);
		 panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		 listaPanel.add(panel);
		 GridBagLayout gbl_panel = new GridBagLayout();
		 gbl_panel.columnWidths = new int[]{55, 474, 239, 168, 98};
		 gbl_panel.rowHeights = new int[]{30, 0};
		 gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		 gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		 panel.setLayout(gbl_panel);
        
            JLabel lblId = new JLabel(id);
            lblId.setMinimumSize(new Dimension (0,25));
            
            JLabel lblNombre = new JLabel(nombre);
            lblNombre.setMinimumSize(new Dimension (0,25));
            
            JLabel lblTelefono = new JLabel(telefono);
            lblTelefono.setMinimumSize(new Dimension (0,25));
            
            JLabel lblCitas = new JLabel("0");
            lblCitas.setMinimumSize(new Dimension (0,25));
            
            JLabel lblMas = new JLabel("Ver mas");
            lblMas.setMinimumSize(new Dimension (0,25));
            
            panel.setMinimumSize(new Dimension(0,30));
            panel.add(lblId);
            panel.add(lblNombre);
            panel.add(lblTelefono);
            panel.add(lblCitas);
            panel.add(lblMas);
            
        return panel;
    }
    
    //Metodo para cargar clientes desde la base de datos..........................................................
    private void cargarClientesDesdeBD() {
        try {
            String query = "SELECT ID, NombreCliente, TelefonoCliente, CorreoCliente FROM clientes";
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String nombre = resultSet.getString("NombreCliente");
                String telefono = resultSet.getString("TelefonoCliente");
                String correo = resultSet.getString("CorreoCliente");   
                addPanel(id, nombre, telefono, correo);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Metodo para mostrar panel Vehiculos
    private void mostrarPanelVehiculos() {
    	mainPanel.setVisible(false);
    	clientesPanel.setVisible(false);
    	
    	GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
    	
    	add(vehiculosPanel, gbc);
    	vehiculosPanel.mostrarVehiculos();
    	
    }
    
    public Clientes obtenerClientesPanel() {
    	return this;
    }
    
    public void regresarPanelClientes() {
    	vehiculosPanel.setVisible(false);
    	mainPanel.setVisible(true);
    	clientesPanel.setVisible(true);
    }
    
    public String obtenerTextoLblNombre() {
    	return lblNombre.getText();
      
        
    }
}