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
public class ReservaController {
    
    Conexion conexion=new Conexion();
    
    public void Guardar(Map<String,String> mapa){
        conexion.Guardar(mapa,"guardarReserva",3);
    }
    
    public List<Map<String,String>> lista(){
        List<Map<String,String>> resultado2=new ArrayList<>();
            
            try{
                ResultSet resultado=conexion.Consultar("verReservas");
                while(resultado.next()){
                Map<String,String> fila=new HashMap<>();
                fila.put("ID_RESERVA",String.valueOf(resultado.getInt("ID_RESERVA")));
                fila.put("FECHA_ENTRADA",resultado.getString("FECHA_ENTRADA"));
                fila.put("FECHA_SALIDA",resultado.getString("FECHA_SALIDA"));
                fila.put("VALOR",String.valueOf(resultado.getInt("VALOR_TOTAL")));
                fila.put("TIPO_PAGO",resultado.getString("TIPO_PAGO"));
                resultado2.add(fila);
            }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }finally{
                conexion.Cerrar();
            }
            
            return resultado2;
    }
    
    public void Actualizar(String tipo_pago,String fecha_entrada,String fecha_salida,Integer id){
        Map<String,String> mapa=new HashMap<>();
        mapa.put("ID",String.valueOf(id));
        mapa.put("fecha_salida", fecha_salida);
        mapa.put("fecha_entrada",fecha_entrada);
        mapa.put("tipo_pago",tipo_pago);
        conexion.Guardar(mapa,"ActualizarReserva", 4);
    }
    
    public void Eliminar(Integer id){
        Map<String,String> mapa=new HashMap<>();
        mapa.put("id",String.valueOf(id));
        conexion.Guardar(mapa, "EliminarReserva", 1);
    }
    
}
