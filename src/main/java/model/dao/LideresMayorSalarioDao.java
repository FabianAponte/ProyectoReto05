package model.dao;

/*Clase que se conecta a la base de datos ProyectosConstruccion.db a través del la clase JDBCutilities y que ejecuta las consultas preparadas definidas 
sobre la base de datos y obtiene un ArrayList respuesta con la colección de los objetos LideresMayorSalario que cumplen los criterios y estructura definida en la consulta, 
en caso que la consulta falle se captura el error de la consulta*/

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import util.JDBCUtilities;

//Encapsulamiento de los datos
import model.vo.LideresMayorSalario;

public class LideresMayorSalarioDao {

    //Obtener los 10 proyectos rankeados según las compras
    public ArrayList<LideresMayorSalario> rankingLideresMayorSalario() throws SQLException {

        ArrayList<LideresMayorSalario> respuesta = new ArrayList<LideresMayorSalario>();
        Connection conexion = JDBCUtilities.getConnection();

        try{       

            String consulta =   "SELECT ID_Lider, Nombre, Primer_Apellido "+
                                "FROM Lider "+
                                "WHERE Salario >= 300000 AND Ciudad_Residencia = 'Barranquilla' "+
                                "ORDER BY ID_Lider ";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while(resultSet.next()){
                LideresMayorSalario lideresMayorSalario = new LideresMayorSalario();
                lideresMayorSalario.setIdLider(resultSet.getInt("Id_Lider"));
                lideresMayorSalario.setNombre(resultSet.getString("Nombre"));
                lideresMayorSalario.setPrimerApellido(resultSet.getString("Primer_Apellido"));

                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(lideresMayorSalario);
            }

            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.err.println("Error consultando ranking de los lideres con mayor: "+e);
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;
    }  
}

