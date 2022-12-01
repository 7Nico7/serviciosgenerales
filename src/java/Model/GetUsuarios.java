package Model;

import Conexion.Conexion;
import Model.Usuarios;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUsuarios {

    public String getUsuarios() {
        final String sql = "select cve_usuarios, nombre, correo, contra, tipoUsuario from usuarios";

        String json = null;
        Gson gson = new Gson();
        Conexion conecta = new Conexion();
        List<Usuarios> personas = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = conecta.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setCve_usuarios(rs.getInt("cve_usuarios"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setContra(rs.getString("contra"));
                u.setTipo_usuario(rs.getString("tipoUsuario"));
                personas.add(u);
            }
            json = gson.toJson(personas);
        } catch (SQLException e) {
            System.err.println("ERROR en GetUsuarios : " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e2) {
                System.err.println("ERROR en GetUsuarios Cerrar conección");
            }
        }
        return json;
    }
}
