package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetMantenimiento_Id {

    public Mantenimientos GetMantnimientoID(String cve_mantenimiento, String tipo_bien, String num_inventario) {

        String sql = """
                     select cve_fecha_mante_camb, cve_bienes, tm.nombre as cve_tipoMantenimiento, f.nombre as cve_fallas, fecha, descripcion, archivo, nombre_archivo, archivo2, nombre_archivo2, cambio_et, tornner_cambiado  from fecha_mante_camb as fmc
                     inner join tipo_mantenimiento as tm on fmc.cve_tipoMantenimiento=tm.cve_tipoMantenimiento 
                     inner join fallas as f on fmc.cve_fallas=f.cve_fallas
                     where cve_fecha_mante_camb=""" + cve_mantenimiento + ";";

        PreparedStatement ps = null;
        Connection con = null;
        Conexion cn = null;
        ResultSet rs = null;
        Mantenimientos M = null;
        try {
            cn = new Conexion();
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                M = new Mantenimientos();
                M.setCve_bienes(rs.getString("cve_bienes"));
                M.setCve_mantenimiento(rs.getString("cve_fecha_mante_camb"));
                M.setTipoMantenimiento(rs.getString("cve_tipoMantenimiento"));
                M.setFallas(rs.getString("cve_fallas"));
                M.setTipo_bien(tipo_bien);
                M.setNum_inventario(num_inventario);
                M.setFecha_manteni(rs.getString("fecha"));
                M.setEvidenciaVideo(rs.getString("archivo"));
                M.setNombre_archivo(rs.getString("nombre_archivo"));
                
                M.setEvidenciaReporte(rs.getString("archivo2"));
                M.setNombre_archivo_reporte(rs.getString("nombre_archivo2"));
                
                M.setCambioET(rs.getString("cambio_et"));
                M.setMant_descripcion(rs.getString("descripcion"));
                M.setTonnersCambiados(rs.getString("tornner_cambiado"));

            }
        } catch (SQLException e) {
            System.err.println("ERROR EN GetMantenimiento_ID : " + e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }

                if (con != null) {
                    con.close();
                    con = null;
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (cn != null) {
                    cn = null;
                }

            } catch (SQLException e2) {
                System.err.println("ERROR : " + e2);
            }
        }

        return M;
    }

    public String GetDetalleBien_MantenimientoID(int cve_bien) {

        String sql = """
select cve_fecha_mante_camb, cve_bienes, tipoM.nombre as mantenimiento, fa.nombre as falla, fecha,(90 - DATEDIFF(CURDATE(), fecha)) as dias_restantes, DATE_ADD(fecha, INTERVAL 90 DAY) as proximo_mantenimiento, descripcion, archivo,  
                     nombre_archivo, cambio_et, tornner_cambiado from fecha_mante_camb as fechaM
                     inner join tipo_mantenimiento as tipoM on fechaM.cve_tipoMantenimiento=tipoM.cve_tipoMantenimiento
                     inner join fallas as fa on fechaM.cve_fallas=fa.cve_fallas
                     where cve_bienes= ?;""";
        Gson gson = new Gson();
        String json = "";
        PreparedStatement ps = null;
        Connection con = null;
        Conexion cn = null;
        ResultSet rs = null;

        List<Mantenimientos> mantenimientos = new ArrayList<>();
        try {
            cn = new Conexion();
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cve_bien);
            rs = ps.executeQuery();
            boolean datos = false;
            while (rs.next()) {
                Mantenimientos M = new Mantenimientos();
                M.setCve_mantenimiento(rs.getString("cve_fecha_mante_camb"));
                M.setCve_bienes(rs.getString("cve_bienes"));
                M.setTipoMantenimiento(rs.getString("mantenimiento"));
                M.setFallas(rs.getString("falla"));
                M.setFecha_manteni(rs.getString("fecha"));
                M.setMant_descripcion(rs.getString("descripcion"));

                M.setEvidenciaVideo(rs.getString("archivo"));
                M.setNombre_archivo(rs.getString("nombre_archivo"));
                M.setCambioET(rs.getString("cambio_et"));
                M.setTonnersCambiados(rs.getString("tornner_cambiado"));

                M.setProximo_mantenimiento(rs.getString("proximo_mantenimiento"));
                M.setDias_restantes(rs.getString("dias_restantes"));
                mantenimientos.add(M);
                datos = true;
            }

            if (datos == true) {
                json = gson.toJson(mantenimientos);

            } else {
                json = null;
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN GetDetalleBien_MantenimientoID : " + e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }

                if (con != null) {
                    con.close();
                    con = null;
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (cn != null) {
                    cn = null;
                }

            } catch (SQLException e2) {
                System.err.println("ERROR : " + e2);
            }
        }

        return json;
    }

}
