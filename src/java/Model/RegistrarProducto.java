package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.joda.time.LocalDate;

public class RegistrarProducto {

    public String registrarProducto(Inventario P) {
        String mensaje = "";
        String sql = "INSERT productos(codigo, descripcion, proyeccion_anual, entrada, salida) values (?, ?, ?, ?, ?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;

        ValidaCampos valida = new ValidaCampos();

        String[] campos = {P.getCodigo(), P.getDescripcion(), String.valueOf(P.getProyeccion_anual())};
        String[] camposNombre = {"El campo codigo es NULO", "El campo Descripcion es NULO", "El campo Proyeccion Anual es NULO"};

        String camposValidos = valida.valida(campos, camposNombre);
        LocalDate date = LocalDate.now();
        if ("correcto".equals(camposValidos)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setString(1, P.getCodigo());
                ps.setString(2, P.getDescripcion());
                ps.setInt(3, P.getProyeccion_anual());
                ps.setInt(4, 0);
                ps.setInt(5, 0);

                ps.executeUpdate();
                mensaje = """
                   <div class="alert alert-success alert-dismissible fade show" role="alert">
                     <strong>¡Correcto!</strong>
                     El Producto ha sido registrado satisfactoriamente.							<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                     </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger alert-dismissible fade show" role="alert">
                          <strong>¡ERROR!</strong>
                          """
                        + e
                        + "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                        + "</div>";

                System.err.println("ERROR EN RegistrarRecibo : " + e);
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
                    System.err.println("ERROR En RegistrarRecibo cerrar conexion : " + ex);
                }
            }
        } else {
            mensaje = camposValidos;
        }

        return mensaje;
    }

}
