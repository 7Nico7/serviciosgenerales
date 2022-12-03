package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetArchivos {

    public List<Archivo> getArchivos(int ID, String sql) {
        Archivo list = new Archivo();
        Conexion con = null;
        Connection cn = null;
        List<Archivo> archivos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Archivo A = new Archivo();
                A.setRuta(rs.getString("archivo"));
                archivos.add(A);
                
                Archivo r = new Archivo();
                r.setRuta(rs.getString("archivo2"));
                archivos.add(r);
            }
        } catch (SQLException e) {
            System.err.println("ERROR en obtener archivos : " + e);
        } finally {
            try {
                if (cn != null & cn.isClosed() == false) {
                    cn.close();
                    cn = null;
                }
                if (con != null) {
                    con = null;
                }
                if (ps != null && ps.isClosed() == false) {
                    ps.close();
                    ps = null;
                }
            } catch (SQLException ex) {
                System.err.println("ERROR en Eliminar : " + ex);
            }
        }

        return archivos;
    }

}
