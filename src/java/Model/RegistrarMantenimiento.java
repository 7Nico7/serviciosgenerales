package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class RegistrarMantenimiento extends Conexion {

    public String Mantenimiento(Mantenimientos M) {
        String mensaje = "";
        final String sql = "insert fecha_mante_camb(cve_bienes, cve_tipoMantenimiento, cve_fallas, fecha, descripcion, "
                + "archivo, nombre_archivo, cambio_et, tornner_cambiado, archivo2, nombre_archivo2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;
        String validar = respuestaValida(M);
        if ("correcto".equals(validar)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setString(1, M.getCve_bienes());
                ps.setString(2, M.getTipoMantenimiento());
                //Tipo de Bien
                if ("2".equals(M.getTipo_bien()) || "3".equals(M.getTipo_bien())) {
                    ps.setInt(3, 5);

                    // respuesta si se cambio de tonner o extintor
                    if ("1".equals(M.getCambioET())) {
                        ps.setString(8, "Si");

                        if ("3".equals(M.getTipo_bien())) {
                            ps.setString(9, M.getTonnersCambiados());
                        } else {
                            ps.setString(9, " ");
                        }
                    } else if ("2".equals(M.getCambioET())) {
                        // sino se cambio el tonner o extintor
                        ps.setString(8, "No");
                        ps.setString(9, " ");
                    }

                } else {
                    //Si el bien es clima
                    ps.setString(3, M.getFallas());
                    ps.setString(8, " ");
                    ps.setString(9, " ");
                }

                ps.setString(4, M.getFecha_manteni());
                ps.setString(5, M.getMant_descripcion());

                if (!StringUtil.isBlank(M.getEvidenciaVideo())) {
                    ps.setString(6, M.getEvidenciaVideo());
                    ps.setString(7, M.getNombre_archivo());
                } else {
                    ps.setString(6, " ");
                    ps.setString(7, " ");

                }

                if (!StringUtil.isBlank(M.getEvidenciaReporte())) {
                    ps.setString(10, M.getEvidenciaReporte());
                    ps.setString(11, M.getNombre_archivo_reporte());
                } else {
                    ps.setString(10, " ");
                    ps.setString(11, " ");
                }

                ps.executeUpdate();
                mensaje = """
                           <div class="alert alert-success alert-dismissible fade show" role="alert">
                             <strong>Correcto! </strong> Se Registro El Mantenimiento  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                           </div>""";
            } catch (SQLException e) {
                mensaje = """
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                              <strong>Error! </strong> """ + e + ".\n"
                        + "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                        + "</div>";
                System.err.println("ERROR EN RegistrarMantenimiento : " + e);
            } finally {
                try {
                    if (cn != null && cn.isClosed() == false) {
                        cn.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con = null;
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR en cerrar cn RegistrarMantenimiento : " + ex);
                }
            }
        } else {
            mensaje = validar;
        }
        return mensaje;
    }

    public String respuestaValida(Mantenimientos M) {
        String respuesta = null;
        String[] campos = {};
        String[] camposNombre = {};

        if (!StringUtil.isBlank(M.getTipo_bien())) {
            if ("1".equals(M.getTipo_bien())) {
                String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getTipoMantenimiento(), M.getFallas()};
                String[] campoNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Tipo de Mantenimiento NULO", "Falla NULA"};
                campos = campos1;
                camposNombre = campoNombre1;
            } else if ("2".equals(M.getTipo_bien())) {
                String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getCambioET(), M.getTipoMantenimiento()};
                String[] campoNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Responda si, se Cambio el extintor", "Tipo de Mantenimiento NULO"};
                campos = campos1;
                camposNombre = campoNombre1;
            } else if ("3".equals(M.getTipo_bien())) {

                if ("1".equals(M.getCambioET())) {
                    String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getCambioET(), M.getTipoMantenimiento()};
                    String[] campoNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Responda si, se Cambio el Tonner", "Tipo de Mantenimiento NULO"};
                    campos = campos1;
                    camposNombre = campoNombre1;
                } else {
                    String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getCambioET(), M.getTipoMantenimiento(), M.getTonnersCambiados()};
                    String[] campoNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Responda si, se Cambio el Tonner", "Tipo de Mantenimiento NULO", "Los tonners cambiados son NULOS"};
                    campos = campos1;
                    camposNombre = campoNombre1;
                }

            } else {
                String[] campos1 = {M.getTipo_bien()};
                String[] campoNombre1 = {"El Tipo de Bien es Incorrecto"};
                campos = campos1;
                camposNombre = campoNombre1;
            }
        } else {
            String[] campos1 = {M.getTipo_bien()};
            String[] campoNombre1 = {"El Tipo de Bien NULO"};
            campos = campos1;
            camposNombre = campoNombre1;
        }

        ValidaCampos Campos = new ValidaCampos();

        String validar = Campos.valida(campos, camposNombre);
        respuesta = validar;
        return respuesta;
    }

    private String valida(String[] campos, String[] campoNombre) {
        String respuesta = null;

        for (int i = 0; i < campos.length; i++) {
            if (StringUtil.isBlank(campos[i])) {
                respuesta = "<div class=\"alert alert-danger\" role=\"alert\"> " + campoNombre[i] + "! </div>";
                i = campos.length;
            } else {
                respuesta = "correcto";
            }
        }
        return respuesta;
    }

}
