package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActualizarSalidasVehiculo {

    //   validaCampos = validar.valida(campos, campoNombre);
    public String ObtenerSalidasParaConcretar() {
        String sql = "select cve_salidas_vehiculo, cve_vehiculos, fecha, hora_salida, hora_entrada, tiempo_ruta, k_inicial, k_final, g_inicial, g_final, conductor, usuarios, destino, asunto, observaciones, estatus from salidas_vehiculo;";
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String json = null;
        Gson gson = new Gson();
        List<VehiculoSalidas> Salidas = new ArrayList<>();

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                VehiculoSalidas s = new VehiculoSalidas();
                s.setCve_vehiculo(rs.getInt("cve_vehiculos"));

                s.setCve_salidas(rs.getString("cve_salidas_vehiculo"));
                s.setFecha(rs.getString("fecha"));
                s.setHora_salida(rs.getString("hora_salida"));
                s.setHora_entrada(rs.getString("hora_entrada"));
                s.setTiempo_ruta(rs.getString("tiempo_ruta"));
                s.setKilometraje_inicial(rs.getString("k_inicial"));
                s.setKilometraje_final(rs.getString("k_final"));
                s.setGasolina_inicial(rs.getString("g_inicial"));
                s.setGasolina_final(rs.getString("g_final"));
                s.setConductor(rs.getString("conductor"));
                s.setUsuarios(rs.getString("usuarios"));
                s.setDestino(rs.getString("destino"));
                s.setAsunto(rs.getString("asunto"));
                s.setObservaciones(rs.getString("observaciones"));
                s.setStatus(rs.getString("estatus"));
               
                Salidas.add(s);
            }
            json = gson.toJson(Salidas);

        } catch (SQLException e) {
            System.err.println("ERROR en : " + e);
        } finally {
            try {
                if (cn != null && cn.isClosed() == false) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null && rs.isClosed() == false) {
                    rs.close();
                }
                if (con != null) {
                    con = null;
                }
            } catch (SQLException ex) {
                System.err.println("ERROR en cerrar cn");
            }
        }
        return json;
    }

    public String ConcretarSalida(VehiculoSalidas S) {
        
        String sql = "UPDATE salidas_vehiculo SET cve_vehiculos =?,  fecha=?, hora_salida=?, hora_entrada=?, tiempo_ruta=?,"
                + "k_inicial=?, k_final=?, g_inicial=?, g_final=?, conductor=?, usuarios=?, destino=?, asunto=?, observaciones=?, estatus=? where cve_salidas_vehiculo=?;";
        String mensaje = "";
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id = String.valueOf(S.getCve_vehiculo());
        //Se arreglo con los campos del formulario
        String[] campos = {S.getCve_salidas(),id, S.getFecha(), S.getHora_salida(), S.getHora_entrada(), S.getKilometraje_inicial(),
            S.getKilometraje_final(), S.getGasolina_inicial(), S.getGasolina_final(), S.getConductor(), S.getUsuarios(), S.getDestino(), S.getAsunto(), S.getObservaciones()};
        String tiempo_ruta = "";
    //arreglo con el mensaje si el campo es NULO
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El campo Salida es NULO", "El campo Vehiculo es NULO", "El campo Fecha es NULO", "El campo Hora de Salida es NULO", "El campo Hora de Entrada es NULO", "El campo Kilometraje Inicial es NULO",
            "El campo Kilometraje Final es NULO", "El campo Gasolina Inicial es NULO", "El campo Gasolina Final es NULO", "El campo Conductor es NULO", "El campo Usuarios es NULO", "El campo Destino es NULO", "El campo Asunto es NULO", "El campo Observaciones es NULO"};
        //Clase para validar los Campos
        ValidaCampos validar = new ValidaCampos();
        //Se valida los campos
        String validaCampos = validar.valida(campos, campoNombre);
        
        if ("correcto".equals(validaCampos)) {
            String tiempoRuta =  "SELECT TIMEDIFF('" +S.getHora_entrada()+ "','" + S.getHora_salida()+ "') as tiempoRuta;";
            try { 
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(tiempoRuta);
                rs = ps.executeQuery();
                
                while (rs.next()) {                    
                    tiempo_ruta = rs.getString("tiempoRuta");
                }
                
                ps = cn.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, S.getFecha());
                ps.setString(3, S.getHora_salida());
                ps.setString(4, S.getHora_entrada());
                ps.setString(5, tiempo_ruta);
                ps.setString(6, S.getKilometraje_inicial());
                ps.setString(7, S.getKilometraje_final());
                ps.setString(8, S.getGasolina_inicial());
                ps.setString(9, S.getGasolina_final());
                ps.setString(10, S.getConductor());
                ps.setString(11, S.getUsuarios());
                ps.setString(12, S.getDestino());
                ps.setString(13, S.getAsunto());
                ps.setString(14, S.getObservaciones());
                ps.setString(15,S.getStatus());               
                ps.setString(16, S.getCve_salidas());
                
                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se Actualizo La Salida
                          </div>""";
            } catch (SQLException e) {
                System.err.println("Error en UpedateMedidor : " + e);
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Actualizo La Salida, ERROR: """ + e + "\n" + "</div>";
            } finally {
                try {
                    if (cn != null) {
                        cn.close();
                    }

                    if (con != null) {
                        con = null;
                    }

                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }

                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR cerrar Conexiones : " + ex);
                }
            }

        } else {
            mensaje = validaCampos;
        }
        return mensaje;
    }

}
