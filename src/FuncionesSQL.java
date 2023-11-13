package src;
import java.sql.*;
import javax.swing.*;


public class FuncionesSQL {

    static void GuardarEnBase(String dbNAme, String dbTable,Pestaña x){
        try{
            Connection NuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/"+ dbNAme,"root","");
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into " +dbTable + " values (?,?,?,?,?)" );
            //
            NuevoEstado.setString(1,"0");
            NuevoEstado.setString(2,"");
        }catch (Exception e){

        }
    }

    static void LeerDeBase(String dbNAme, String dbTable,Producto x,String id){
        try{
            Connection NuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/"+ dbNAme,"root","");
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

}
