package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

import static src.FuncionesSQL.NuevaConexion;

public class MySQLTableEditor {
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable  dataTable = new JTable(tableModel);
    private JScrollPane scrollPane = new JScrollPane(dataTable);
    static JPanel panel = new JPanel();
    private static JButton updateButton = new JButton("Actualizar Existencias");

    public MySQLTableEditor() {
        // Configurar la interfaz gráfica
        scrollPane = new JScrollPane(dataTable);
        updateButton.addActionListener(e -> updateExistencias());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(updateButton);

        try {
            Statement statement = NuevaConexion.createStatement();

            // Obtener datos de la tabla
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventarioproductos");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar columnas al modelo de la tabla
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }// Agregar filas al modelo de la tabla
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                tableModel.addRow(row);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateExistencias() {
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();

        // Obtener el nombre de la tabla
        String tableName = "inventarioproductos";

        // Conectar a la base de datos
        String url = "jdbc:mysql://localhost:3306/dbtaller";
        String user = "root";
        String password = "";

        // Iterar sobre las filas de la tabla
        for (int row = 0; row < rowCount; row++) {
            // Obtener el valor de la existencia (columna 5)
            Object existenciaValue = tableModel.getValueAt(row, 2);
            System.out.println(existenciaValue);
            System.out.println("Tipo de existenciaValue: " + existenciaValue.getClass().getName());


            // Obtener el ID del producto (suponiendo que es la primera columna)
            Object idProducto = tableModel.getValueAt(row, 0);


            // Actualizar la base de datos solo si existenciaValue no es nulo
            if (existenciaValue != null) {
                String updateQuery = "update " + tableName + " set Existencias = ? where ID = ?";
                existenciaValue = Integer.parseInt(existenciaValue.toString());
                try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(updateQuery)) {
                    // Asegurarse de que existenciaValue sea un valor numérico antes de intentar la actualización
                    if (existenciaValue instanceof Number) {
                        preparedStatement.setInt(1, ((Number) existenciaValue).intValue());
                        preparedStatement.setObject(2, idProducto);
                        preparedStatement.executeUpdate();
                    } else {
                        // Manejo de error, por ejemplo, mostrar un mensaje al usuario
                        JOptionPane.showMessageDialog(null, "El valor de existencias debe ser numérico.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Manejo de error, por ejemplo, mostrar un mensaje al usuario
                    JOptionPane.showMessageDialog(null, "Error al actualizar existencias.");
                }
            }
        }

        // Opcional: Puedes refrescar la tabla después de la actualización
        // tableModel.setRowCount(0);
        // // Luego, vuelve a cargar los datos desde la base de datos como hiciste inicialmente
        // // ...

    }


}