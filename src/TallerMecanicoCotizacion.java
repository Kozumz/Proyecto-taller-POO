package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TallerMecanicoCotizacion {

    private JTextField nombreField;
    private JTextField costoHoraField;
    private JTextField horasTrabajoField;
    private JTextField costoPiezasField;
    private JTable productosTable;
    private DefaultTableModel tableModel;

    private Map<String, Double> preciosProductos;
    private Map<String, Integer> inventarioProductos;

    /**
     * @wbp.parser.entryPoint
     */
    public JPanel createAndShowCot() {
        // Inicializar precios y cantidades en almacén de productos
        preciosProductos = new HashMap<>();
        inventarioProductos = new HashMap<>();

        preciosProductos.put("Aceite de motor", 20.0);
        preciosProductos.put("Filtro de aceite", 10.0);
        preciosProductos.put("Batería", 50.0);

        inventarioProductos.put("Aceite de motor", 100);
        inventarioProductos.put("Filtro de aceite", 50);
        inventarioProductos.put("Batería", 20);

        // Crear la interfaz gráfica
        //frame = new JFrame("Taller Mecánico");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        panel.setBackground(new Color(181, 207, 222));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nombreField = new JTextField(10);
        nombreField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	costoHoraField.requestFocus(); // Mueve el enfoque al siguiente 
            }
        });
        
        costoHoraField = new JTextField(10);
        costoHoraField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	horasTrabajoField.requestFocus(); // Mueve el enfoque al siguiente 
            }
        });
        
        horasTrabajoField = new JTextField(10);
        horasTrabajoField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	costoPiezasField.requestFocus(); // Mueve el enfoque al siguiente 
            }
        });
        
        costoPiezasField = new JTextField(10);

        // Crear la tabla de productos
        String[] columnasProductos = {"Producto", "Precio", "Cantidad a Comprar", "Inventario"};
        Object[][] dataProductos = obtenerDatosProductos();
        DefaultTableModel productosTableModel = new DefaultTableModel(dataProductos, columnasProductos) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 4;
            }
        };
        productosTable = new JTable(productosTableModel);
        productosTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());

        JScrollPane scrollPaneProductos = new JScrollPane(productosTable);

        JButton calcularButton = new JButton("Calcular Cotización");
        calcularButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularCotizacion();
            }
        });

        // Crear la tabla de cotizaciones
        String[] columnas = {"Nombre", "Compras", "Costo por hora", "Horas de trabajo", "Costo de las piezas", "Costo de los productos", "Cotización total"};
        tableModel = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPaneCotizaciones = new JScrollPane(table);

        JLabel label = new JLabel("Nombre del cliente:");
        label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label);
        panel.add(nombreField);
        JLabel label_1 = new JLabel("Productos disponibles:");
        label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_1);
        panel.add(scrollPaneProductos);
        JLabel label_2 = new JLabel("Costo por hora de mano de obra:");
        label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_2);
        panel.add(costoHoraField);
        JLabel label_3 = new JLabel("Horas de trabajo:");
        label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_3);
        panel.add(horasTrabajoField);
        JLabel label_4 = new JLabel("Costo de las piezas:");
        label_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_4);
        panel.add(costoPiezasField);
        panel.add(calcularButton);
        panel.add(scrollPaneCotizaciones);

        return panel;
    }

    private Object[][] obtenerDatosProductos() {
        Object[][] data = new Object[preciosProductos.size()][5];
        int i = 0;

        for (Map.Entry<String, Double> entry : preciosProductos.entrySet()) {
            String producto = entry.getKey();
            double precio = entry.getValue();
            int cantidad = inventarioProductos.get(producto);
            data[i][0] = producto;
            data[i][1] = precio;
            data[i][2] = 0;  // Cantidad a comprar (inicialmente 0)
            data[i][3] = cantidad;  // Inventario           
            i++;
        }

        return data;
    }

    private void calcularCotizacion() {
        String nombre = nombreField.getText();
        double costoHora = Double.parseDouble(costoHoraField.getText());
        int horasTrabajo = Integer.parseInt(horasTrabajoField.getText());
        double costoPiezas = Double.parseDouble(costoPiezasField.getText());

        // Obtener productos seleccionados y cantidades
        DefaultTableModel productosTableModel = (DefaultTableModel) productosTable.getModel();
        StringBuilder compras = new StringBuilder();
        double costoProductos = 0.0;      
        
     // Restablecer la cantidad a comprar a 0 en la tabla de productos
        for (int i = 0; i < productosTableModel.getRowCount(); i++) {
            String producto = (String) productosTableModel.getValueAt(i, 0);
            int cantidadCompra = (int) productosTableModel.getValueAt(i, 2);
            int inventario = (int) productosTableModel.getValueAt(i, 3);

            if (cantidadCompra > 0 && cantidadCompra <= inventario) {
                compras.append(producto).append(" (").append(cantidadCompra).append(" unidades), ");
                costoProductos += preciosProductos.get(producto) * cantidadCompra;

                // Actualizar inventario
                inventario -= cantidadCompra;
                productosTableModel.setValueAt(inventario, i, 3);
            }
         // Limpiar los campos después de crear la cita
            nombreField.setText("");
            costoHoraField.setText("");
            horasTrabajoField.setText("");
            costoPiezasField.setText("");  
            productosTableModel.setValueAt(0, i, 2);
        }

        double costoTotal = calcularCostoTotal(costoHora, horasTrabajo, costoPiezas, costoProductos);

        // Agregar datos a la tabla de cotizaciones
        agregarFilaTabla(nombre, compras.toString(), costoHora, horasTrabajo, costoPiezas, costoProductos, costoTotal);

        JOptionPane.showMessageDialog(null, "La cotización total es: $" + costoTotal);

        // Guardar la información en el archivo "cotizaciones.txt"
        guardarInformacionEnArchivo("cotizaciones.txt", nombre, compras.toString(), costoHora, horasTrabajo, costoPiezas, costoProductos, costoTotal);
    }

    private double calcularCostoTotal(double costoHora, int horasTrabajo, double costoPiezas, double costoProductos) {
        return costoHora * horasTrabajo + costoPiezas + costoProductos;
    }

    private void agregarFilaTabla(String nombre, String compras, double costoHora, int horasTrabajo, double costoPiezas, double costoProductos, double costoTotal) {
        Object[] fila = {nombre, compras, costoHora, horasTrabajo, costoPiezas, costoProductos, costoTotal};
        tableModel.addRow(fila);
    }

    private void guardarInformacionEnArchivo(String nombreArchivo, String nombre, String compras, double costoHora, int horasTrabajo, double costoPiezas, double costoProductos, double costoTotal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Nombre: " + nombre + ", Compras: " + compras +
                    ", Costo por hora de mano de obra: " + costoHora +
                    ", Horas de trabajo: " + horasTrabajo +
                    ", Costo de las piezas: " + costoPiezas +
                    ", Costo de los productos: " + costoProductos +
                    ", Cotización total: " + costoTotal + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class SpinnerEditor extends DefaultCellEditor {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JSpinner spinner;

        public SpinnerEditor() {
            super(new JCheckBox());
            spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            setClickCountToStart(1);

            // Escuchar cambios en el spinner y actualizar la tabla
            spinner.addChangeListener(e -> {
                stopCellEditing();
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }
}