package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetMantenimientos {

    public String mantenimientos(String tabla) {
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Mantenimientos> mantenimientos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        String sql = SQL(tabla);
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mantenimientos M = new Mantenimientos();
                M.setCve_bienes(rs.getString("cve_bienes"));
                M.setCve_mantenimiento(rs.getString("cve_mantenimiento"));
                M.setFecha_manteni(rs.getString("fecha"));
                M.setTipo_bien(rs.getNString("tipo"));
                M.setNum_inventario(rs.getString("num_inventario"));
                M.setMant_descripcion(rs.getString("descripcion"));
                M.setEvidenciaVideo(rs.getString("archivo"));
                M.setNombre_archivo(rs.getString("nombre_archivo"));
                M.setDias_restantes(rs.getString("dias_restantes"));

                M.setEvidenciaReporte(rs.getString("archivo2"));
                M.setNombre_archivo_reporte(rs.getString("nombre_archivo2"));
                M.setProximo_mantenimiento(rs.getString("proximo_mantenimiento"));
                mantenimientos.add(M);
            }
            json = gson.toJson(mantenimientos);
        } catch (SQLException e) {
            System.err.println("ERROR EN GetMantenimientos mantenimientos :" + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                    cn = null;
                }
                if (con != null) {
                    con = null;
                }

                if (rs != null) {
                    rs.close();
                    rs = null;
                }

                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (SQLException ex) {
                System.err.println("Error en Cerrar cn GetMantenimientos : " + ex);
            }
        }
        return json;
    }

    private String SQL(String tabla) {
        String sql = """
Select b.cve_bienes, cve_fecha_mante_camb as cve_mantenimiento, tipo, num_inventario, fecha, 
                              (90 - DATEDIFF(CURDATE(), fecha)) as dias_restantes, descripcion, DATE_ADD(fecha, INTERVAL 90 DAY) as proximo_mantenimiento, 
                              archivo, nombre_archivo nombre_archivo, archivo2, nombre_archivo2 from bienes as b
                              inner join fecha_mante_camb as mante on b.cve_bienes=mante.cve_bienes
                              inner join tipoBien as tipoB on b.cve_tipoBien=tipoB.cve_tipoBien""";
        switch (tabla) {
            case "todos":
                sql += " where (90 - DATEDIFF(CURDATE(), fecha)) > (-10);";
                break;
            case "climas":
                sql += " where (90 - DATEDIFF(CURDATE(), fecha)) > (-10) && tipo= \"climas\";";
                break;
            case "extintores":
                sql += " where (90 - DATEDIFF(CURDATE(), fecha)) > (-10) && tipo= \"extintores\";";
                break;
            case "impresoras":
                sql += " where (90 - DATEDIFF(CURDATE(), fecha)) > (-10) && tipo= \"impresoras\";";
                break;
            case "historial":
                sql += ";";
                break;
            default:
                sql = null;
        }
        return sql;
    }
}
