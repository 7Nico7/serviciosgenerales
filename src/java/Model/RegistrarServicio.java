package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class RegistrarServicio {

    public String RegistrarServicio(Servicios S) {
        String mensaje = "";
        final String sql = "INSERT servicios(cve_vehiculos, descripcion, fecha, archivo, nombre_archivo, archivo2, nombre_archivo2)"
                + " values (?,?,?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;
        String id = String.valueOf(S.getCve_vehiculo());
        //Se arreglo con los campos del formulario
        String[] campos = {id, S.getDescripcion(), S.getFecha()};
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El Campo Vehiculo es NULO", "El Campo Descripcion es NULO", "El Campo Fecha es NULO"};
        //Clase para validar los Campos
        ValidaCampos validar = new ValidaCampos();
        //Se valida los campos
        String validaCampos = validar.valida(campos, campoNombre);

        //Se validad si los campos son correctos
        if ("correcto".equals(validaCampos)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(id));
                ps.setString(2, S.getDescripcion());
                ps.setString(3, S.getFecha());

                if (!StringUtil.isBlank(S.getArchivo())) {
                    ps.setString(4, S.getArchivo());
                    ps.setString(5, S.getNombre_archivo());
                } else {
                    ps.setString(4, " ");
                    ps.setString(5, " ");
                }

                if (!StringUtil.isBlank(S.getArchivo2())) {
                    ps.setString(6, S.getArchivo2());
                    ps.setString(7, S.getNombre_archivo2());
                } else {
                    ps.setString(6, " ");
                    ps.setString(7, " ");
                }

                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se registro el Servicio
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Registro el Servicio, ERROR: """ + e + "\n" + "</div>";
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
