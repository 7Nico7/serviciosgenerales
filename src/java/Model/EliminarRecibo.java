package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class EliminarRecibo {

    static final String sql = "DELETE from recibo where cve_recibo=?;";

    public String Eliminar(int cve_recibo, String archivo, String archivoR) {
        String mensaje = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Conexion con = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_recibo);
            ps.executeUpdate();
            EliminarArchivo eliminar = new EliminarArchivo();
            if (!StringUtil.isBlank(archivo)) {
                eliminar.eliminarArchivo(archivo);
            }
            
            if (!StringUtil.isBlank(archivoR)) {
                eliminar.eliminarArchivo(archivoR);
            }
            mensaje = "eliminado";
        } catch (SQLException e) {
            System.err.println("ERROR en EliminarMantenimiento : " + e);
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

        return mensaje;
    }

}
