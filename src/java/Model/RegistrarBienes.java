package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

public class RegistrarBienes extends Conexion {

//Registra el Bien
    public String Registrar(Bienes b) {

        Conexion con = null;
        Connection cn = null;
        PreparedStatement ps = null;
        final String sql = "insert bienes(cve_tipoBien, cve_estado, marca, modelo, fecha_instalacion, "
                + "fecha_factura, num_inventario, unidad, serie)value (?,?,?,?,?,?,?,?,?);";
        ResultSet rs = null;
        con = new Conexion();
        String mensaje = "";
        int id;
        String[] respuesta = DatosObligatorios(b);

        if ("correcto".equals(respuesta[1])) {
            try {
                cn = con.getConexion();
                ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, b.getTipo_bien());
                ps.setString(2, b.getStatus());
                ps.setString(3, b.getMarca());
                ps.setString(4, b.getModelo());

                //EL bien tipo clima solo tiene estas fechas
                if ("1".equals(b.getTipo_bien())) {
                    if (b.getFecha_factura() == null || "".equals(b.getFecha_factura())) {
                        ps.setString(6, null);

                    } else {
                        ps.setString(6, b.getFecha_factura());
                    }
                    ps.setString(5, b.getFecha_instalacion());
                } else {
                    ps.setString(5, null);
                    ps.setString(6, null);
                }
                //Los extintores no tiene numero de Serie
                if ("2".equals(b.getTipo_bien())) {
                    ps.setString(9, " ");
                } else {
                    ps.setString(9, b.getSerie());
                }
                ps.setString(7, b.getNum_inventario());

                //Las Impresoras no Tienen nombre de Unidad
                if ("3".equals(b.getTipo_bien())) {
                    ps.setString(8, " ");
                } else {
                    ps.setString(8, b.getUnidad());
                }
                //Se Ejecuta la Consulta
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    //Se Obtiene el Id del Bien registrado
                    id = rs.getInt(1);
                    mensaje = "<div class=\"alert alert-success\" role=\"alert\">\n" + "Se registro el Bien Numero " + id + "\n" + "</div>";
                    //Se registra la ubicacion del Bien
                    ubicacion(b, id, cn);
                    //Si el bien es de tipo Impresora Se registra los Tonners
                    if ("3".equals(b.getTipo_bien())) {
                        tonners(b, id, cn);
                    }
                }
            } catch (SQLException ex) {
                mensaje = "<div class=\"alert alert-danger\" role=\"alert\">\n" + "Nose Registro el bien, ERROR: " + ex + "\n" + "</div>";
                System.err.println("ERROR SQL " + ex);
            } finally {
                try {
                    if (cn != null & cn.isClosed() == false) {
                        cn.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR en RegistrarBienes cn.close " + ex);
                }
            }
        } else {
            mensaje = respuesta[0];
        }
        return mensaje;
    }

    private void ubicacion(Bienes b, int id, Connection cn) {
        final String sql = "insert ubicaciones(cve_bienes, ubic_departamento, departamento, dependencia, area, responsable, observaciones) "
                + "values (?,?,?,?,?,?,?);";
        PreparedStatement ps;

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, b.getUbic_departamento());
            ps.setString(6, b.getResponsable());

            if ("2".equals(b.getTipo_bien()) || "3".equals(b.getTipo_bien())) {
                ps.setString(3, "");
                ps.setString(4, "");
                ps.setString(5, "");
                ps.setString(7, "");
            } else {
                ps.setString(3, b.getDepartamento());
                ps.setString(4, b.getDependencia());
                ps.setString(5, b.getArea());
                ps.setString(7, b.getUbicaObservaciones());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR EN ubicacion " + e);
        }
    }

    private void tonners(Bienes b, int id, Connection cn) {
        final String sql = "insert tonners(cve_bienes, tonner) values (?,?);";

        PreparedStatement ps;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, b.getTonner());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR EN tonners : " + e);
        }
    }

    private String[] DatosObligatorios(Bienes b) {
        String respuesta = "";
        String mensaje = "";
        String campo = "";
        String[] retorna = {"", ""};
        // Datos que contienen los tres bienes
        if (StringUtils.isBlank(b.getTipo_bien())) {
            campo = "El Tipo de Bien es Nulo";
        } else if (StringUtils.isBlank(b.getStatus())) {
            campo = "El Status Es Nulo";
        } else if (StringUtils.isBlank(b.getMarca())) {
            campo = "La Marca es Nula";
        } else if (StringUtils.isBlank(b.getUbic_departamento())) {
            campo = "La ubicación del Departamento es Nula";
        } else if (StringUtils.isBlank(b.getResponsable())) {
            campo = "EL Responsable Es Nulo";
        } else if (StringUtils.isBlank(b.getNum_inventario())) {
            campo = "El Numero de Inventario es Nulo";
        } else if (StringUtils.isBlank(b.getModelo())) {
            campo = "El Modelo es Nulo";
        } else {
            switch (b.getTipo_bien()) {
                case "1":
                    //Datos extras del Bien climas
                    if (StringUtils.isBlank(b.getUnidad())) {
                        campo = "Falto el Dato Descripcion";
                    } else if (StringUtils.isBlank(b.getSerie())) {
                        campo = "Falto El Dato Numero de Serie";
                    } else if (StringUtils.isBlank(b.getFecha_instalacion())) {
                        campo = "Falto EL Dato Fecha de Instalacion";
                    } else if (StringUtils.isBlank(b.getDependencia())) {
                        campo = "Falto El Dato Dependencia";
                    } else if (StringUtils.isBlank(b.getDepartamento())) {
                        campo = "Falto El Dato Departamento";
                    } else if (StringUtils.isBlank(b.getUbicaObservaciones())) {
                        campo = "Falto El Dato Observaiones de Ubicación";
                    } else if (StringUtils.isBlank(b.getArea())) {
                        campo = "Falto El Dato Área";
                    } else {
                        respuesta = "correcto";
                    }
                    break;
                case "2":
                    //Datos Extras del Bien Extintor
                    if (StringUtils.isBlank(b.getUnidad())) {
                        campo = "El campo de Descripción es Nulo";
                    } else {
                        respuesta = "correcto";
                    }
                    break;
                case "3":
                    //Datos Extras del Bien Impresoras
                    if (StringUtils.isBlank(b.getTonner())) {
                        campo = "El campo Tonner Es Nulo";
                    } else if (StringUtils.isBlank(b.getSerie())) {
                        campo = "El Campo Serie Es Nulo";
                    } else {
                        respuesta = "correcto";
                    }
                    break;
                default:
                    campo = "EL Tipo de Bien No Existe : " + b.getTipo_bien();
                    break;
            }
        }

        mensaje = "<div class=\"alert alert-danger\" role=\"alert\">\n" + "Nose Registro el bien, " + campo + "\n" + "</div>";
        retorna[0] = mensaje;
        retorna[1] = respuesta;
        return retorna;
    }
}
