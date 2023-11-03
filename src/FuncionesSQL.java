package src;
import java.sql.*;

public class FuncionesSQL {

    static void GuardarEnBase(String dbNAme, String dbTable,String values,Pestaña x){
        try{
            Connection NuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/"+ dbNAme,"root","");
            PreparedStatement NuevoEstado = NuevaConexion.prepareStatement("insert into "+dbTable + values);

            NuevoEstado.setString(1,"0");
            NuevoEstado.setString(2,"");
        }catch (Exception e){

        }
    }
}
