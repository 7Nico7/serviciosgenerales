package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarCargarGasolina {
    
     public String RegistrarCargarGasolina(CargarGasolina G) {
        String mensaje = "";
        final String sql = "INSERT recarga_combustible(cve_vehiculos, litros, precio, importe, fecha)"
                + " values (?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;
        String id = String.valueOf(G.getCve_vehiculo());
        //Se arreglo con los campos del formulario
        String[] campos = {id, G.getLitros(), G.getPrecio(), G.getImporte(), G.getFecha()};
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El Campo Vehiculo es NULO", "El Campo Litros es NULO", "El Campo Precio es NULO", "El Campo Importe es NULO"
        ,"El Campo Fecha es NULO"};
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
                ps.setInt(1, G.getCve_vehiculo());
                ps.setString(2, G.getLitros());
                ps.setString(3, G.getPrecio());
                ps.setString(4,G.getImporte());
                ps.setString(5,G.getFecha());

                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se registro la recarga de Gasolina
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Registro la recarga de Gasolina, ERROR: """ + e + "\n" + "</div>";
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
