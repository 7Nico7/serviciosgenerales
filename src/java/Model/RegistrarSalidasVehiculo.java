package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarSalidasVehiculo {

    public String RegistrarSalida(VehiculoSalidas S) {
        String mensaje = "";
        final String sql = "INSERT salidas_vehiculo(cve_vehiculos, fecha, hora_salida, hora_entrada, tiempo_ruta, k_inicial, "
                + "k_final, g_inicial, g_final, conductor, usuarios, destino, asunto, observaciones, estatus)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;
        String id = String.valueOf(S.getCve_vehiculo());
        //Clase para validar los Campos
        ValidaCampos validar = new ValidaCampos();
        String validaCampos;

        //Se arreglo con los campos del formulario
        String[] campos = {id, S.getFecha(), S.getAsunto()};
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"EL campo Vehiculo es NULO", "EL campo Fecha es NULO", "El campo Asunto es NULO"};
        //Se valida los campos
        validaCampos = validar.valida(campos, campoNombre);

        //Se validad si los campos son correctos
        if ("correcto".equals(validaCampos)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(id));
                ps.setString(2, S.getFecha());
                ps.setString(3, " ");
                ps.setString(4, " ");
                ps.setString(5, " ");
                ps.setString(6, "0");
                ps.setString(7, "0");
                ps.setString(8, "0");
                ps.setString(9, "0");
                ps.setString(10, " ");
                ps.setString(11, " ");
                ps.setString(12, " ");
                ps.setString(13, S.getAsunto());
                ps.setString(14, " ");
                ps.setString(15, S.getStatus());
                
                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se Agendo la Salida
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose se Agendo la Salida, ERROR: """ + e + "\n" + "</div>";
                System.err.println("ERROR en RegistrarVehiculo : " + e);
            } finally {
                try {
                    if (cn != null && cn.isClosed() == false) {
                        cn.close();
                        cn = null;
                    }

                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }

                    if (con != null) {
                        con = null;
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR En Registrar recarga de Gasolina : " + ex);
                }
            }
        } else {
            mensaje = validaCampos;
        }

        return mensaje;
    }

}
