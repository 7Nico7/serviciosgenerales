package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.util.StringUtil;

public class EliminarMedidor {

    static final String sqlM = "DELETE from medidor where cve_medidor=?;";
    static final String sqlR = "DELETE from recibo where cve_medidor=?;";

    public String Eliminar(int cve_medidor, List<Archivo> lista) {
        String mensaje = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Conexion con = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sqlR);
            ps.setInt(1, cve_medidor);
            ps.executeUpdate();
            ps = cn.prepareStatement(sqlM);
            ps.setInt(1, cve_medidor);
            int seElimino = ps.executeUpdate();

            System.err.println("S###### : " + seElimino + "tamño : " + lista.toString());

            EliminarArchivo eliminar = new EliminarArchivo();
            if (seElimino > 0) {
                for (Archivo archivo : lista) {
                    if (!StringUtil.isBlank(archivo.getRuta())) {
                        System.err.println("AAAAAAA : " + archivo.getRuta());
                        eliminar.eliminarArchivo(archivo.getRuta());
                    }
                }
                mensaje = "eliminado";
            } else {
                mensaje = " ";
            }

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
