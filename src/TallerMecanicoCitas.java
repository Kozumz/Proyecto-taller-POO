package src;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TallerMecanicoCitas {

    private JTextField nombreClienteField;
    private JTextField vehiculoField;
    private JComboBox<String> horasComboBox;
    private DatePicker datePicker;
    private JTable citasTable;
    private DefaultTableModel tableModel;

    private List<Cita> citas;

    public JPanel createAndShowCit() {
        citas = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(181, 207, 222));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nombreClienteField = new JTextField(10);
        nombreClienteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculoField.requestFocus(); // Mueve el enfoque al siguiente 
            }
        });
        vehiculoField = new JTextField(10);
        vehiculoField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	datePicker.getComponentDateTextField().requestFocus(); // Mueve el enfoque al siguiente 
            }
        });
        
     
        // Configuración del DatePicker
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        datePicker = new DatePicker(dateSettings);
        datePicker.getComponentDateTextField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horasComboBox.requestFocus(); // Mueve el enfoque al siguiente
            }
        });

        JButton crearCitaButton = new JButton("Crear Cita");
        crearCitaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        crearCitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCita();
            }
        });

        JButton verCitasButton = new JButton("Ver Citas del Día");
        verCitasButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        verCitasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCitasDelDia();
            }
        });

        // Crear la tabla de citas
        String[] columnas = {"Nombre Cliente", "Fecha", "Hora"};
        tableModel = new DefaultTableModel(columnas, 0);
        citasTable = new JTable(tableModel);

        JScrollPane scrollPaneCitas = new JScrollPane(citasTable);
        

        JButton verCalendarioButton = new JButton("Ver Calendario Semanal");
        verCalendarioButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        verCalendarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCalendarioSemanal();
            }
        });

        JButton verTodasLasCitas = new JButton("Mostrar todas las citas");
        verTodasLasCitas.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        verTodasLasCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTablaCitas();
            }
        });

        JButton modificarCitaButton = new JButton("Modificar Cita");
        modificarCitaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        modificarCitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCita(citasTable.getSelectedRow());
            }
        });

        JButton eliminarCitaButton = new JButton("Eliminar Cita");
        eliminarCitaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        eliminarCitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCita(citasTable.getSelectedRow());
            }
        });

        // Inicializar ComboBox de horas
        horasComboBox = new JComboBox<>();
        for (int i = 9; i <= 17; i++) {
            horasComboBox.addItem(String.format("%02d:00", i));
        }

        JLabel label_1 = new JLabel("Nombre Cliente:");
        label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_1);
        panel.add(nombreClienteField);
        JLabel label_2 = new JLabel("Vehículo:");
        label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_2);
        panel.add(vehiculoField);
        JLabel label = new JLabel("Fecha de la Cita:");
        label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label);
        panel.add(datePicker);
        JLabel label_3 = new JLabel("Hora de la Cita:");
        label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        panel.add(label_3);
        panel.add(horasComboBox);
        panel.add(crearCitaButton);
        panel.add(verCitasButton);
        panel.add(scrollPaneCitas);
        panel.add(verCalendarioButton);
        panel.add(modificarCitaButton);
        panel.add(eliminarCitaButton);
        panel.add(verTodasLasCitas);

        return panel;
    }

    private void crearCita() {
            String nombreCliente = nombreClienteField.getText();
            String Fecha = datePicker.getDateStringOrEmptyString();
            String Hora = horasComboBox.getSelectedItem().toString();


            //Verificar si existe una cita a la misma fecha y hora
            if (FuncionesSQL.existeCita(Fecha, Hora))
                return;



            if (FuncionesSQL.AgregarCita(nombreCliente, Fecha, Hora)) {
                Cita nuevaCita = new Cita(nombreCliente, Fecha, Hora);
                citas.add(nuevaCita);
                agregarFilaTabla(nuevaCita);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error al crear la cita.");
            }

            // Limpiar los campos después de crear la cita
            nombreClienteField.setText("");
            vehiculoField.setText("");
            datePicker.clear();
            horasComboBox.setSelectedIndex(0);
        }


    private void verCitasDelDia() {
        // Limpiar la tabla
        tableModel.setRowCount(0);
        String fechaActual = LocalDate.now().toString();
        // Obtener las citas del día utilizando la función SQL
        List <Cita> citasDelDia = FuncionesSQL.obtenerCitasDelDia(fechaActual);

        // Agregar las filas a la tabla
        for (Cita cita : citasDelDia) {
            Object[] fila = {cita.getNombreCliente(), cita.getVehiculo(), cita.getFechaHora()};
            tableModel.addRow(fila);
        }
    }

    private void verCalendarioSemanal() {
        tableModel.setRowCount(0);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtener las citas de la semana actual
        List<Cita> citasDeLaSemana = FuncionesSQL.obtenerCitasDeLaSemana(fechaActual.toString());

        for (Cita cita : citasDeLaSemana) {
            Object[] fila = {cita.getNombreCliente(), cita.getVehiculo(), cita.getFechaHora()};
            tableModel.addRow(fila);
        }
    }

    private void modificarCita(int filaSeleccionada) {
        if (filaSeleccionada >= 0) {
            // Obtener la información de la fila seleccionada
            String nombreCliente = (String) tableModel.getValueAt(filaSeleccionada, 0);
            String fecha = (String) tableModel.getValueAt(filaSeleccionada, 1);
            String hora = (String) tableModel.getValueAt(filaSeleccionada, 2);

            String nuevaFecha = JOptionPane.showInputDialog("Introduce la nueva fecha de la cita", fecha);
            String nuevaHora = JOptionPane.showInputDialog("Introduce la nueva hora de la cita", hora);
            // Modificar la cita en la base de datos
            FuncionesSQL.modificarCita(nombreCliente,fecha, hora, nuevaFecha, nuevaHora);

            // Actualizar la tabla después de modificar la cita
            tableModel.setRowCount(0);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para modificar.");
        }
    }

    private String obtenerNuevoDato(String mensaje, String valorActual) {
        return JOptionPane.showInputDialog(mensaje, String.valueOf(valorActual));
    }

    private void eliminarCita(int filaSeleccionada) {
        if (filaSeleccionada >= 0) {
            // Obtener la información de la fila seleccionada
            String nombreCliente = (String) tableModel.getValueAt(filaSeleccionada, 0);
            String fecha = (String) tableModel.getValueAt(filaSeleccionada, 1);
            String hora = (String) tableModel.getValueAt(filaSeleccionada, 2);

            // Eliminar la cita desde la base de datos
            FuncionesSQL.eliminarCitadeBase(nombreCliente, fecha, hora);

            // Actualizar la tabla después de eliminar la cita
            tableModel.setRowCount(0);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
        }
    }

    public void actualizarTablaCitas() {
        // Obtener todas las citas
        List<Cita> todasLasCitas = FuncionesSQL.mostrarTodasLasCitas();

        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Agregar las citas a la tabla
        for (Cita cita : todasLasCitas) {
            Object[] fila = {cita.getNombreCliente(), cita.getVehiculo(), cita.getFechaHora()};
            tableModel.addRow(fila);
        }
    }


    
    private void agregarFilaTabla(Cita cita) {
        Object[] fila = {cita.getNombreCliente(), cita.getVehiculo(), cita.getFechaHora()};
        tableModel.addRow(fila);
    }

    public static class Cita {
        private String nombreCliente;
        private String vehiculo;
        private String fechaHora;

        public Cita(String nombreCliente, String vehiculo, String fechaHora) {
            this.nombreCliente = nombreCliente;
            this.vehiculo = vehiculo;
            this.fechaHora = String.valueOf(fechaHora);
        }
        
        public String getNombreCliente() {
            return nombreCliente;
        }
        
         public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }
         
          public String getVehiculo() {
            return vehiculo;
        }

        public void setVehiculo(String vehiculo) {
            this.vehiculo = vehiculo;
        }
        
        public String getFechaHora() {
            return fechaHora;
        }
        public void setFechaHora(LocalDateTime nuevaFechaHora) {
			this.fechaHora = String.valueOf(nuevaFechaHora);
		}  
    }
}

