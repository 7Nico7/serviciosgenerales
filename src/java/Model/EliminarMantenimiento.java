package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class EliminarMantenimiento {

    static final String sql = "DELETE from fecha_mante_camb where cve_fecha_mante_camb=?;";

    public String Eliminar(int cve_mantenimiento, String archivo, String reporte) {
        String mensaje = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Conexion con = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_mantenimiento);
            ps.executeUpdate();
            EliminarArchivo eliminar = new EliminarArchivo();
            if (!StringUtil.isBlank(archivo)) {
                eliminar.eliminarArchivo(archivo);
            }
            
            if (!StringUtil.isBlank(reporte)) {
                eliminar.eliminarArchivo(reporte);
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
