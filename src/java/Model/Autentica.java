package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Autentica extends Conexion {

    public Usuarios Auntenticar(Usuarios usuario) {
        Usuarios usu = null;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        PreparedStatement ps;
        ResultSet rs = null;
        String sql = "select nombre, correo, contra from usuarios Where correo = ? and contra = ?";
        con = new Conexion();
        try {
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getContra());
            rs = ps.executeQuery();

            if (rs.next() == true) {
                usu = new Usuarios();
                usu.setNombre(rs.getString("nombre"));
                usu.setCorreo(rs.getString("correo"));
                usu.setContra(rs.getString("contra"));
                usu.setTipo_usuario(rs.getString("tipo_usuario"));
            }

        } catch (SQLException ex) {

            System.err.println("Auntenticar #### " + ex);
        } finally {
            try {
                if (cn != null & cn.isClosed() == false) {
                    cn.close();
                }
                if (rs != null && rs.isClosed() == false) {
                    rs.close();
                }

                if (st != null && st.isClosed() == false) {
                    st.close();
                }

            } catch (SQLException ex) {
                System.err.println("ERROR EN AUTENTICAR " + ex);
            }
        }
        return usu;
    }
}
