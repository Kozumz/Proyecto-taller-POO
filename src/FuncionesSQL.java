package src;
import java.security.PublicKey;
import java.sql.*;
import src.Producto;
import javax.swing.*;


public class FuncionesSQL {

    public static Connection NuevaConexion;
    static void GuardarEnBase(String dbNAme, String dbTable,Producto x){
        try{

            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " +dbTable + " values (?,?,?,?,?)" );
            //
            NuevoEstado.setString(1,"0");
            NuevoEstado.setString(2,x.NombreDeProducto);
            NuevoEstado.setInt(3,x.Existencias);
            NuevoEstado.setInt(4,x.Precio);
            NuevoEstado.setString(5,x.Descripcion);

        }catch (Exception e){

        }
    }


    static void LeerDeBase(String dbNAme, String dbTable,Producto x,String id){
        try{
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("select * from "+dbTable + " where id = ? " );
            NuevoEstado.setString(1, id);
            ResultSet rs = NuevoEstado.executeQuery();

            if (rs.next()){
                x.NombreDeProducto = (rs.getString("NombreProducto"));
                x.Descripcion = (rs.getString("Descripcion"));
                x.Existencias = (rs.getInt("Existencias"));
                x.Precio = (rs.getInt("Precio"));
            }
        }catch (Exception e){

        }

    }

    static void ActualizarExistencias(String dbName, String dbTable, String idProducto, int nuevasExistencias) {
        try {
            PreparedStatement UpdateStatus = NuevaConexion.prepareStatement("update " + dbTable + " set Existencias = ? where ID = " + idProducto);

                UpdateStatus.setInt(1, nuevasExistencias);

                UpdateStatus.executeUpdate();
        } catch (Exception e) {
        }
    }
}
