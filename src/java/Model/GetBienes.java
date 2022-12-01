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
}
