package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

public class RegistrarUsuario extends Conexion {

    public String registrarUsuario(Usuarios usuario) throws Exception {
        Conexion con = null;
        Connection cn = null;

        PreparedStatement ps = null;
        final String sql = "insert usuarios(nombre, correo, contra)value (?,?,?)";

        String mensaje = "";
        mensaje = validar(usuario);
     
        if ("correcto".equals(mensaje)) {
                   try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContra());
            ps.executeUpdate();
            mensaje = "Se Registrado el Usuario";
        } catch (SQLException ex) {
            mensaje = "ERROR " + ex;

        } finally {
            if (cn != null & cn.isClosed() == false) {
                cn.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con = null;
            }
        }
        }
        return mensaje;
    }

    private String validar(Usuarios usuario) {
        String mensaje = null;
        if (StringUtils.isBlank(usuario.getNombre())) {
            mensaje = "No se realizo el Registro, Nombre Nulo";
        } else if (StringUtils.isBlank(usuario.getCorreo())) {
            mensaje = "No se realizo el Registro, Correo Nulo";
        } else if(StringUtils.isBlank(usuario.getContra())) {
            mensaje = "No se realizo el Registro, Contraseña Nula";
        } else {
            mensaje = "correcto";
        }
        return mensaje;
    }
}
