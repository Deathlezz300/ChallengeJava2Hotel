/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author jairo
 */
public class Conexion {
    
    Connection conexion;
     int pos=0;
     boolean decision;
     
     private DataSource datasource;
     
     public Conexion(){
         var pooledDataSource= new ComboPooledDataSource();
         pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura");
         pooledDataSource.setUser("root");
         pooledDataSource.setPassword("alejandrotoledo300");
         pooledDataSource.setMaxPoolSize(10);
         this.datasource=pooledDataSource;
     }
     
    
    public boolean Conectar(){
        try{
            conexion=this.datasource.getConnection();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean Cerrar(){
        try{
            conexion.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ResultSet Consultar(String procedimiento){
        try{
            Conectar();
            CallableStatement statement=conexion.prepareCall("{call "+procedimiento+"()}");
            statement.execute();
            
            ResultSet resultado=statement.getResultSet();
            return resultado;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean Guardar(Map<String,String> producto,String procedimiento,int parametros){
        try{
            String cadena="{call "+procedimiento+"(";
            for (int i = 0; i < parametros; i++) {
               if(i==0){
                   cadena+="?";
               }else{
                   cadena+=",?";
               }
            }
            cadena+=")}";
            Conectar();
            PreparedStatement statement=conexion.prepareStatement(cadena);
            producto.forEach((key,value)->{
                pos++;
                try{
                    int numero=Integer.parseInt(value);
                    System.out.println("transformé la cantidad");
                    statement.setInt(pos,numero);
                }catch(SQLException | NumberFormatException e){
                    decision=false;
                    System.out.println(e.getMessage());
                }
                if(!decision){
                    try{
                        statement.setString(pos,value);
                    }catch(SQLException e){}
                }
            });
            System.out.println(statement);    
            statement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            pos=0;
        }
        return true;
    }
    
}
