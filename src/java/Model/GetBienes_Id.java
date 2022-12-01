package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBienes_Id extends Conexion {

    public Bienes buscarBien(int cve_bienes, String tipo_bien) {

        String sql = Consulta(tipo_bien);

        PreparedStatement ps = null;
        Connection con = null;
        Conexion cn = null;
        ResultSet rs = null;
        Bienes bien = new Bienes();
        try {
            cn = new Conexion();
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cve_bienes);
            rs = ps.executeQuery();
            if (rs.next()) {
                bien.setCve_bienes(rs.getString("cve_bienes"));
                bien.setTipo_bien(rs.getString("tipo"));
                bien.setMarca(rs.getString("marca"));
                bien.setModelo(rs.getString("modelo"));
                bien.setSerie(rs.getString("serie"));
                bien.setResponsable(rs.getString("responsable"));
                bien.setUbic_departamento(rs.getString("ubic_departamento"));
                bien.setStatus(rs.getString("estado"));
                bien.setNum_inventario(rs.getString("num_inventario"));
                if ("Climas".equals(tipo_bien)) {
                    bien.setUnidad(rs.getString("unidad"));
                    bien.setFecha_factura(rs.getString("fecha_factura"));
                    bien.setFecha_instalacion(rs.getString("fecha_instalacion"));
                    bien.setDepartamento(rs.getString("departamento"));
                    bien.setDependencia(rs.getString("dependencia"));
                    bien.setArea(rs.getString("area"));
                    bien.setUbicaObservaciones(rs.getString("observacion_ubicacion"));

                } else {
                    bien.setFecha_factura(" ");
                    bien.setFecha_instalacion(" ");
                    bien.setDepartamento(" ");
                    bien.setDependencia(" ");
                    bien.setArea(" ");
                    bien.setUbicaObservaciones(" ");
                }
                if ("Extintores".equals(tipo_bien)) {
                    bien.setUnidad(rs.getString("unidad"));
                }
                if ("Impresoras".equals(tipo_bien)) {
                    bien.setUnidad(" ");
                    bien.setTonner(rs.getString("tonners"));
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN BuscarBienes_Id.buscarBien : " + e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e2) {
                System.err.println("ERROR : " + e2);
            }
        }
        return bien;
    }

    public String Consulta(String tipo_bien) {
        String sql = "";
        final String climas = "select b.cve_bienes, tb.tipo, unidad, marca, modelo, serie, num_inventario, fecha_factura, fecha_instalacion, ubic_departamento,\n"
                + "departamento, dependencia, area, responsable, observaciones as observacion_ubicacion, estado from bienes as b\n"
                + "inner join tipobien as tb on b.cve_tipoBien=tb.cve_tipoBien\n"
                + "inner join estado as es on b.cve_estado=es.cve_estado\n"
                + "inner join ubicaciones as ub on b.cve_bienes=ub.cve_bienes\n"
                + "where b.cve_bienes = ?;";
        final String extintores = "select b.cve_bienes, tb.tipo, unidad, marca, modelo, serie, num_inventario, ubic_departamento, responsable, estado\n"
                + "from bienes as b \n"
                + "inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien\n"
                + "inner join ubicaciones as ub on b.cve_bienes=ub.cve_bienes\n"
                + "inner join estado as es on b.cve_estado=es.cve_estado\n"
                + "where b.cve_bienes = ?;";
        final String impresoras = "select b.cve_bienes, tipo, marca, modelo, serie, num_inventario, ubic_departamento, responsable, tonner as tonners, estado\n"
                + "from bienes as b\n"
                + "inner join tipoBien as tb on b.cve_tipoBien=tb.cve_tipoBien\n"
                + "inner join ubicaciones as ub on b.cve_bienes=ub.cve_bienes\n"
                + "inner join estado as es on b.cve_estado=es.cve_estado\n"
                + "inner join tonners as t on b.cve_bienes=t.cve_bienes\n"
                + "Where b.cve_bienes=?;";
        if ("Climas".equals(tipo_bien)) {
            sql = climas;
        }
        if ("Extintores".equals(tipo_bien)) {
            sql = extintores;
        }
        if ("Impresoras".equals(tipo_bien)) {
            sql = impresoras;
        }
        return sql;
    }
}
