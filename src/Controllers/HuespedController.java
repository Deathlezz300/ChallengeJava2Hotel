/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jairo
 */
public class HuespedController {

    Conexion conexion=new Conexion();
    
    public void Guardar(Map<String,String> mapa){
        conexion.Guardar(mapa,"guardarHuesped",5);
    }
    
    public List<Map<String,String>> lista(){
        List<Map<String,String>> resultado2=new ArrayList<>();
            
            try{
                ResultSet resultado=conexion.Consultar("verHuespedes");
                while(resultado.next()){
                Map<String,String> fila=new HashMap<>();
                fila.put("ID_CLIENTE",String.valueOf(resultado.getInt("ID_CLIENTE")));
                fila.put("NOMBRE_CLIENTE",resultado.getString("NOMBRE_CLIENTE"));
                fila.put("APELLIDO_CLIENTE",resultado.getString("APELLIDO_CLIENTE"));
                fila.put("FECHA_NACIMIENTO", resultado.getString("FECHA_NACIMIENTO"));
                fila.put("NACIONALIDAD",resultado.getString("NACIONALIDAD"));
                fila.put("TELEFONO", resultado.getString("TELEFONO"));
                fila.put("FK_RESERVA",String.valueOf(resultado.getInt("FK_RESERVA")));
                resultado2.add(fila);
            }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }finally{
                conexion.Cerrar();
            }
            
            return resultado2;
    }
    
    public void Actualizar(String apellido,String nombre,String telefono,Integer id){
        Map<String,String> mapa=new HashMap<>();
        mapa.put("ID",String.valueOf(id));
        mapa.put("APELLIDO", apellido);
        mapa.put("NOMBRE",nombre);
        mapa.put("TELEFONO",telefono);
        conexion.Guardar(mapa,"ActualizarHuesped", 4);
    }
    
    public void Eliminar(Integer id){
        Map<String,String> mapa=new HashMap<>();
        mapa.put("id",String.valueOf(id));
        conexion.Guardar(mapa, "EliminarHuesped", 1);
    }
    
    
    
}
