package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetVehiculos {
    
    
    
    public String getVehiculosIds(){
        String json = "";
        String sql = "select cve_vehiculos, marca, modelo, vehiculo from vehiculos;";
        PreparedStatement ps = null;
        Connection cn = null;
        Conexion con = null;
        ResultSet rs = null;
        
        List<Vehiculo> vehiculos = new ArrayList<>();
        Gson gson = new Gson();
        try {
           con = new Conexion();
           cn = con.getConexion();
           ps = cn.prepareStatement(sql);
           rs = ps.executeQuery();
           
            while (rs.next()) {
                Vehiculo V = new Vehiculo();
                V.setCve_vehiculo(rs.getInt("cve_vehiculos"));
                V.setMarca(rs.getString("marca"));
                V.setModelo(rs.getString("modelo"));
                V.setVehiculo(rs.getString("vehiculo"));
                vehiculos.add(V);
            }
            json = gson.toJson(vehiculos);
            
        } catch (SQLException e) {
            System.err.println("Error en getVehiculosCargarCombustible : " + e);
        }
        
        
        
        return json;
    }
    
    
}
