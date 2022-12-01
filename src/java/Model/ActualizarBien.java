package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActualizarBien {

    public String Actualizar(Bienes b) {
        final String sql = """
                           UPDATE bienes SET cve_tipoBien = ?, cve_estado = ?, marca = ?, modelo = ?, fecha_instalacion = ?, fecha_factura = ?, num_inventario = ?, unidad = ?, serie = ?
                           Where cve_bienes=?""";
        String mensaje = "";
        int id;
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, b.getTipo_bien());
            ps.setString(2, b.getStatus());
            ps.setString(3, b.getMarca());
            ps.setString(4, b.getModelo());
            if ("2".equals(b.getTipo_bien()) || "3".equals(b.getTipo_bien())) {
                ps.setString(5, null);
                ps.setString(6, null);
            } else {
                ps.setString(5, b.getFecha_instalacion());
                ps.setString(6, b.getFecha_factura());
            }

            ps.setString(7, b.getNum_inventario());
            ps.setString(8, b.getUnidad());
            ps.setString(9, b.getSerie());
            ps.setString(10, b.getCve_bienes());
            ps.executeUpdate();

            mensaje = """
                      <div class="alert alert-success" role="alert">
                      Se Actualizo el Bien Numero """ + b.getCve_bienes() + "\n" + "</div>";
            //Se registra la ubicacion del Bien
            ubicacion(b, cn);
            //Si el bien es de tipo Impresora Se registra los Tonners
            if ("3".equals(b.getTipo_bien())) {
                tonners(b, cn);

            }
            System.err.println("SE ACTUALIZO");
        } catch (SQLException e) {
            mensaje = """
                      <div class="alert alert-danger" role="alert">
                      Nose Actualizo el bien, ERROR: """ + e + "\n" + "</div>";
            System.err.println("ERROR EN AcualizarBien : " + e);
        } finally {
            try {
                if (cn != null & cn.isClosed() == false) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.getConexion().close();
                }
            } catch (SQLException ex) {
                System.err.println("ERROR : " + ex);
            }
        }
        return mensaje;
    }

    private void ubicacion(Bienes b, Connection cn) {
        final String sql = """
                           UPDATE ubicaciones SET ubic_departamento = ?, departamento = ?, dependencia = ?, area = ?, responsable = ?, observaciones = ?
                           Where cve_bienes = ?;""";
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, b.getUbic_departamento());
            ps.setString(2, b.getDepartamento());
            ps.setString(3, b.getDependencia());
            ps.setString(4, b.getArea());
            ps.setString(5, b.getResponsable());
            ps.setString(6, b.getUbicaObservaciones());
            ps.setString(7, b.getCve_bienes());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR EN Actualizar Ubicacion" + e);
        }

    }

    private void tonners(Bienes b, Connection cn) {
        final String sql = "UPDATE tonners SET tonner = ? Where cve_bienes = ?;";

        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, b.getTonner());
            ps.setString(2, b.getCve_bienes());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR EN actualizar tonners : " + e);
        }
    }
}
