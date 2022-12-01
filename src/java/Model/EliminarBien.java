package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.util.StringUtil;

public class EliminarBien extends Conexion {

    static final String sqlB = "DELETE from bienes WHERE cve_bienes= ?";
    static final String sqlU = "DELETE from ubicaciones Where cve_bienes=?";
    static final String sqlM = "DELETE from fecha_mante_camb where cve_bienes=?";
    static final String sqlT = "DELETE from tonners where cve_bienes=?";
    public String Eliminar(int cve_bienes, List<Archivo> lista) {
        String mensaje = null;

        PreparedStatement ps = null;
        Connection cn = null;
        Conexion con = null;
        try {
            con = new Conexion();
            cn = con.getConexion();

            ps = cn.prepareStatement(sqlT);
            ps.setInt(1, cve_bienes);
            ps.executeUpdate();
            ps = cn.prepareStatement(sqlU);
            ps.setInt(1, cve_bienes);
            ps.executeUpdate();
            ps = cn.prepareStatement(sqlM);
            ps.setInt(1, cve_bienes);
            ps.executeUpdate();
            ps = cn.prepareStatement(sqlB);
            ps.setInt(1, cve_bienes);
            
           int seElimino = ps.executeUpdate();
           
           EliminarArchivo eliminar = new EliminarArchivo();
            if (seElimino > 0) {
                for (Archivo archivo : lista) {
                    if (!StringUtil.isBlank(archivo.getRuta())) {
                        eliminar.eliminarArchivo(archivo.getRuta());
                    }
                }
                mensaje = "eliminado";
            } else {
                mensaje = " ";
            }
        } catch (SQLException e) {
            System.err.println("ERROR EN EliminarBien.Eliminar " + e);
        } finally {
            try {
                if (cn != null & cn.isClosed() == false) {
                    cn.close();
                    cn = null;
                }
                if (con != null){
                    con = null;
                }
                if ( ps != null && ps.isClosed() == false ){
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
