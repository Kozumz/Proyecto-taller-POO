package src;

import src.TallerMecanicoCitas.Cita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FuncionesSQL {

    public static Connection NuevaConexion;

    static void GuardarEnBase(String dbNAme, String dbTable, Producto x) {
        if (dbTable.equals("ventas")) {
            try {

                PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " + dbTable + " values (?,?,?,?,?)");

                NuevoEstado.setString(1, "0");
                NuevoEstado.setString(2, x.NombreDeProducto);
                NuevoEstado.setInt(3, x.Existencias);
                NuevoEstado.setInt(4, x.Precio);
                NuevoEstado.setString(5, x.Descripcion);

            } catch (Exception e) {

            }
        } else if (dbTable.equals("inventarioproductos")) {
            try {

                PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " + dbTable + " values (?,?,?,?,?)");

                NuevoEstado.setString(1, "0");
                NuevoEstado.setString(2, x.NombreDeProducto);
                NuevoEstado.setInt(3, x.Existencias);
                NuevoEstado.setInt(4, x.Precio);
                NuevoEstado.setString(5, x.Descripcion);

            } catch (Exception e) {

            }
        } else if (dbTable.equals("clientes")) {
            try {

                PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " + dbTable + " values (?,?,?,?,?)");

                NuevoEstado.setString(1, "0");
                NuevoEstado.setString(2, x.NombreDeProducto);
                NuevoEstado.setInt(3, x.Existencias);
                NuevoEstado.setInt(4, x.Precio);
                NuevoEstado.setString(5, x.Descripcion);

            } catch (Exception e) {

            }
        } else if (dbTable.equals("citas")) {
            try {

                PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " + dbTable + " values (?,?,?,?,?)");

                NuevoEstado.setString(1, "0");
                NuevoEstado.setString(2, x.NombreDeProducto);
                NuevoEstado.setInt(3, x.Existencias);
                NuevoEstado.setInt(4, x.Precio);
                NuevoEstado.setString(5, x.Descripcion);

            } catch (Exception e) {

            }
        } else if (dbTable.equals("vehiculos")) {
            try {

                PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " + dbTable + " values (?,?,?,?,?)");

                NuevoEstado.setString(1, "0");
                NuevoEstado.setString(2, x.NombreDeProducto);
                NuevoEstado.setInt(3, x.Existencias);
                NuevoEstado.setInt(4, x.Precio);
                NuevoEstado.setString(5, x.Descripcion);

            } catch (Exception e) {

            }
        }
    }

    //region Clientes
    static boolean clienteExiste(String nombreCliente) {
        try {
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement("select * from clientes where NombreCliente = ?");
            preparedStatement.setString(1, nombreCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Si el resultSet tiene al menos una fila, el cliente existe
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Manejo de errores, por ejemplo, loggear el error
        }
    }

    private static int devolverIdCliente(String nombreCliente) {
        try {
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement("select * from clientes where NombreCliente = ?");
            preparedStatement.setString(1, nombreCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            } else {
                return -1; // Cliente no encontrado
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Manejo de errores, por ejemplo, loggear el error
        }
    }

    private static String devolverNombreCliente(int idCliente) {
        try {
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement("select * from clientes where ID = ?");
            preparedStatement.setInt(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NombreCliente");
            } else {
                return ""; // Cliente no encontrado
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Manejo de errores, por ejemplo, loggear el error
        }
    }

    static void registrarNuevoCliente(String nombreCliente, String telefono, String correo, String fecha) {
        try {
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement("insert into clientes (NombreCliente,TelefonoCliente,CorreoCliente,FechaRegistro) VALUES (?,?,?,?)");
            preparedStatement.setString(1, nombreCliente);
            preparedStatement.setString(2, telefono);
            preparedStatement.setString(3, correo);
            preparedStatement.setString(4, fecha);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar al cliente.");
        }
    }
    //endregion

    //region Citas
    static boolean AgregarCita(String nombreCliente, String fecha, String hora) {
        if (clienteExiste(nombreCliente)) {
            // Cliente existe, procede a crear la cita
            try {
                PreparedStatement preparedStatement = NuevaConexion.prepareStatement("insert into citas (ClienteID,Fecha,Hora) values (?,?,?)");
                preparedStatement.setInt(1, devolverIdCliente(nombreCliente));
                preparedStatement.setString(2, fecha);
                preparedStatement.setString(3, hora);
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cita creada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al crear la cita.");
            }
            return true;
        } else {
            // Cliente no existe, muestra mensaje de error y da la opción de registrar al cliente
            int opcion = JOptionPane.showConfirmDialog(null, "El cliente no existe. ¿Desea registrar al cliente?", "Cliente no encontrado", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del nuevo cliente:");
                String correo = JOptionPane.showInputDialog("Ingrese el correo del nuevo cliente:");
                registrarNuevoCliente(nombreCliente, telefono, correo, fecha);
                return true;
            } else
                return false;
        }
    }

    static void LeerCita(Venta x, String id) {
        try {
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("select * from citas where id = ? ");
            NuevoEstado.setString(1, id);
            ResultSet rs = NuevoEstado.executeQuery();

            if (rs.next()) {
                x.total = (rs.getInt("TotalTicket"));
                x.date = (rs.getString("FechaVenta"));
            }
        } catch (Exception e) {
        }
    }

    static boolean existeCita(String fecha, String hora) {
        try {
            PreparedStatement preparedStatement = NuevaConexion.prepareStatement("select * from citas where Fecha = ? and Hora = ?");
            preparedStatement.setString(1, fecha);
            preparedStatement.setString(2, hora);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Ya existe una cita programada para este dia y hora");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Manejo de errores, por ejemplo, loggear el error
        }
        return false;
    }

    public static List<Cita> obtenerCitasDelDia(String fecha) {
        List<Cita> citasDelDia = new ArrayList<>();

        // Consulta SQL para obtener las citas del día
        String sqlQuery = "SELECT ClienteID, Fecha, Hora FROM citas WHERE Fecha = ?";

        try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, fecha);

            // Ejecutar la consulta de citas
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Obtener datos de la cita
                    int clienteID = resultSet.getInt("ClienteID");
                    String fechaCita = resultSet.getString("Fecha");
                    String horaString = resultSet.getString("Hora");

                    // Obtener el nombre del cliente utilizando el clienteID
                    String nombreCliente = devolverNombreCliente(clienteID);

                    // Crear una nueva cita con los datos obtenidos
                    Cita cita = new Cita(nombreCliente, fechaCita, horaString);
                    citasDelDia.add(cita);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener las citas del día: " + e.getMessage());
        }

        return citasDelDia;
    }

    public static List<Cita> obtenerCitasDeLaSemana(String fecha) {
        List<Cita> citasDeLaSemana = new ArrayList<>();

        // Consulta SQL para obtener las citas de la semana
        String sqlQuery = "SELECT ClienteID, Fecha, Hora FROM citas WHERE WEEK(Fecha) = WEEK(?)";

        try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, fecha);

            // Ejecutar la consulta de citas
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Obtener datos de la cita
                    int clienteID = resultSet.getInt("ClienteID");
                    String fechaCita = resultSet.getString("Fecha");
                    String horaString = resultSet.getString("Hora");

                    // Obtener el nombre del cliente utilizando el clienteID
                    String nombreCliente = devolverNombreCliente(clienteID);

                    // Crear una nueva cita con los datos obtenidos
                    Cita cita = new Cita(nombreCliente, fechaCita, horaString);
                    citasDeLaSemana.add(cita);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener las citas de la semana: " + e.getMessage());
        }

        return citasDeLaSemana;
    }


    //endregion

    //region Productos e inventario
    static void ActualizarExistencias(String dbName, String dbTable, String idProducto, int nuevasExistencias) {
        try {
            PreparedStatement UpdateStatus = NuevaConexion.prepareStatement("update " + dbTable + " set Existencias = ? where ID = " + idProducto);

            UpdateStatus.setInt(1, nuevasExistencias);

            UpdateStatus.executeUpdate();
        } catch (Exception e) {
        }
    }

    static void LeerVenta(Venta x, String id) {
        try {
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("select * from ventas where id = ? ");
            NuevoEstado.setString(1, id);
            ResultSet rs = NuevoEstado.executeQuery();

            if (rs.next()) {
                x.total = (rs.getInt("TotalTicket"));
                x.date = (rs.getString("FechaVenta"));
            }
        } catch (Exception e) {
        }
    }

    static void LeerDeBase(String dbNAme, String dbTable, Producto x, String id) {
        try {
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("select * from " + dbTable + " where id = ? ");
            NuevoEstado.setString(1, id);
            ResultSet rs = NuevoEstado.executeQuery();

            if (rs.next()) {
                x.NombreDeProducto = (rs.getString("NombreProducto"));
                x.Descripcion = (rs.getString("Descripcion"));
                x.Existencias = (rs.getInt("Existencias"));
                x.Precio = (rs.getInt("Precio"));
            }
        } catch (Exception e) {
        }

    }

    static void AgregarVenta(String date, int total) {
        try {
            // Crear una nueva venta en la tabla de ventas
            PreparedStatement insertStatement = NuevaConexion.prepareStatement("insert into ventas (TotalTicket, FechaVenta) VALUES (?, ?)");

            // Establecer los valores de los parámetros
            insertStatement.setInt(1, total);
            insertStatement.setString(2, date);

            // Ejecutar la instrucción de inserción
            insertStatement.executeUpdate();

            // Cerrar el statement
            insertStatement.close();

            System.out.println("Nueva venta agregada a la tabla de ventas.");

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores, por ejemplo, mostrar un mensaje al usuario
            JOptionPane.showMessageDialog(null, "Error al agregar una nueva venta.");
        }
    }

    static void eliminarCitadeBase(String nombreCliente, String fecha, String hora) {
        // Consulta SQL para eliminar la cita
        String sqlQuery = "DELETE FROM citas WHERE ClienteID = ? AND Fecha = ? AND Hora = ?";

        int id = devolverIdCliente(nombreCliente);

        try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, fecha);
            preparedStatement.setString(3, hora);

            // Ejecutar la consulta de eliminación
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cita eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la cita: " + e.getMessage());
        }
    }

    public static List<Cita> mostrarTodasLasCitas() {
        List<Cita> todasLasCitas = new ArrayList<>();

        // Consulta SQL para obtener todas las citas
        String sqlQuery = "SELECT ClienteID, Fecha, Hora FROM citas";

        try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int clienteID = resultSet.getInt("ClienteID");
                String fechaCita = resultSet.getString("Fecha");
                String horaCita = resultSet.getString("Hora");

                // Obtener el nombre del cliente utilizando el clienteID
                String nombreCliente = devolverNombreCliente(clienteID);

                // Crear una nueva cita con los datos obtenidos
                Cita cita = new Cita(nombreCliente, fechaCita, horaCita);
                todasLasCitas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener todas las citas: " + e.getMessage());
        }

        return todasLasCitas;
    }

    public static void modificarCita(String nombreCliente, String fecha, String hora, String nuevaFecha, String nuevaHora) {
        // Consulta SQL para modificar la cita
        String sqlQuery = "update citas set Fecha = ?, Hora = ? where ClienteID = ? and Fecha = ? and Hora = ?";
        int idCita = devolverIdCliente(nombreCliente);

        try (PreparedStatement preparedStatement = NuevaConexion.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, nuevaFecha);
            preparedStatement.setString(2, nuevaHora);
            preparedStatement.setInt(3, idCita);

            // Ejecutar la consulta de modificación
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cita modificada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar la cita: " + e.getMessage());
        }

        //endregion
    }
}
