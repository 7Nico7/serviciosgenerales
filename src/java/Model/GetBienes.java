package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class GetBienes extends Conexion {

    public String getBienes(String tabla) {

        String json = null;
        Gson gson = new Gson();
        Conexion conecta = null;
        List<Bienes> bienes = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = null;
        String sql = Sql(tabla);
        try {
            conecta = new Conexion();
            con = conecta.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Bienes b = new Bienes();
                b.setCve_bienes(rs.getString("cve_bienes"));
                b.setTipo_bien(rs.getString("tipo"));
                b.setMarca(rs.getString("marca"));
                b.setModelo(rs.getString("modelo"));
                b.setNum_inventario(rs.getString("num_inventario"));
                b.setSerie(rs.getString("serie"));
                b.setUbic_departamento(rs.getString("ubic_departamento"));
                b.setResponsable(rs.getString("responsable"));
                b.setStatus(rs.getString("estado"));
                bienes.add(b);
            }

            json = gson.toJson(bienes);

        } catch (SQLException ex) {
            System.err.println("ERROR en getBienes : " + ex);
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
                System.err.println("ERROR " + e2);
            }
        }
        return json;
    }

    private String Sql(String tipoTabla) {
        String sql = "";

        switch (tipoTabla) {
            case "todos":
                final String todos1 = "select b.cve_bienes, tb.tipo, serie, marca, modelo, num_inventario, responsable, ubic_departamento, estado from bienes as b";
                final String todos2 = " inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien\n"
                        + "inner join ubicaciones as u on b.cve_bienes=u.cve_bienes\n"
                        + "inner join estado as e on b.cve_estado=e.cve_estado;";
                sql = todos1 + todos2;
                break;
            case "climas":
                final String climas1 = "select b.cve_bienes, tb.tipo, serie, marca, num_inventario, responsable, ubic_departamento, estado, modelo from bienes as b inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien";
                final String climas2 = " inner join ubicaciones as u on b.cve_bienes=u.cve_bienes inner join estado as e on b.cve_estado=e.cve_estado where tb.tipo='Climas';";
                sql = climas1 + climas2;
                break;
            case "extintores":
                final String extintores1 = "select b.cve_bienes, tb.tipo, serie, marca, num_inventario, responsable, ubic_departamento, estado, modelo from bienes as b inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien";
                final String extintores2 = " inner join ubicaciones as u on b.cve_bienes=u.cve_bienes inner join estado as e on b.cve_estado=e.cve_estado where tb.tipo='Extintores';";
                sql = extintores1 + extintores2;
                break;
            case "impresoras":
                final String impresoras1 = "select b.cve_bienes, tb.tipo, serie, marca, num_inventario, responsable, ubic_departamento, estado, modelo from bienes as b inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien";
                final String impresoras2 = " inner join ubicaciones as u on b.cve_bienes=u.cve_bienes inner join estado as e on b.cve_estado=e.cve_estado where tb.tipo='Impresoras';";
                sql = impresoras1 + impresoras2;
                break;
            default:
                throw new AssertionError();
        }
        return sql;
    }

    public String getMantenimientoId_MasReciente(int cve_bienes) {
        String sql = """
                     select cve_bienes, fecha, archivo, nombre_archivo, archivo2, nombre_archivo2 from fecha_mante_camb
                                           where cve_bienes= ? && (90 - DATEDIFF(CURDATE(), fecha)) > (-10) order by fecha asc limit 1;""";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Mantenimientos M = new Mantenimientos();
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_bienes);
            rs = ps.executeQuery();
            while (rs.next()) {
                M.setCve_bienes(rs.getString("cve_bienes"));
                M.setFecha_manteni(rs.getString("fecha"));
                M.setNombre_archivo(rs.getString("nombre_archivo"));
                M.setNombre_archivo_reporte(rs.getString("nombre_archivo2"));
            }

            json = gson.toJson(M);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getBienes por ID mas reciente : " + ex);
        } finally {
            try {
                if (con != null) {
                    con = null;
                }

                if (cn != null && cn.isClosed() == false) {
                    cn.close();
                    cn = null;
                }

                if (ps != null && ps.isClosed() == false) {
                    ps.close();
                    ps = null;
                }

                if (rs != null && rs.isClosed() == false) {
                    rs.close();
                    rs = null;
                }

            } catch (SQLException ex) {
                System.err.println("ERROR en cerrar conexion getRecibo : " + ex);
            }
        }

        return json;
    }

}
