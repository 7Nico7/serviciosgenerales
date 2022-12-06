package Model;

import Conexion.Conexion;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class ActualizarMantenimiento {

    public String Actualizar(Mantenimientos M) {
        Archivo archivo = new Archivo();
        SetRutaArchivo guardar = new SetRutaArchivo();

        String mensaje = "";
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String camposValidados = respuestaValida(M);
//Se valida si los campos no fueron nulos
        if ("correcto".equals(camposValidados)) {

            //Si hay nueva evidencia
            boolean hayEvidencia = hayPart(M.getPart());
            EliminarArchivo eliminar = new EliminarArchivo();
            if (hayEvidencia == true) {
                //SE elimina la evidencia anterior

                eliminar.eliminarArchivo(M.getEvidenciaEliminar());

                //Se guarda la Nueva evidencia
                archivo = guardar.RutaArchivoGuardado(M.getPart());
                M.setNombre_archivo(archivo.getNombre());
                M.setEvidenciaVideo(archivo.getRuta());
            }

            boolean hayEvidenciaReporte = hayPart(M.getPartReporte());
            if (hayEvidenciaReporte == true) {

                eliminar.eliminarArchivo(M.getEvidenciaEliminarReporte());

                archivo = guardar.RutaArchivoGuardado(M.getPartReporte());
                M.setNombre_archivo_reporte(archivo.getNombre());
                M.setEvidenciaReporte(archivo.getRuta());
            }

            String sql = SQL(M.getEvidenciaVideo(), M.getEvidenciaReporte());

            try {
                con = new Conexion();
                cn = con.getConexion();

                ps = cn.prepareStatement(sql);

                ps.setString(1, M.getCve_bienes());
                ps.setString(2, M.getTipoMantenimiento());
                ps.setString(4, M.getFecha_manteni());
                ps.setString(5, M.getMant_descripcion());

                //Si hay un archivo que actualizar
                if (!StringUtil.isBlank(M.getEvidenciaVideo()) && !StringUtil.isBlank(M.getEvidenciaReporte())) {

                    ps.setString(8, M.getEvidenciaVideo());
                    ps.setString(9, M.getNombre_archivo());
                    ps.setString(10, M.getEvidenciaVideo());
                    ps.setString(11, M.getNombre_archivo());
                    ps.setString(12, M.getCve_mantenimiento());

                } else if (!StringUtil.isBlank(M.getEvidenciaVideo())) {
                    ps.setString(8, M.getEvidenciaVideo());
                    ps.setString(9, M.getNombre_archivo());
                    ps.setString(10, M.getCve_mantenimiento());

                } else if (!StringUtil.isBlank(M.getEvidenciaReporte())) {
                    ps.setString(8, M.getEvidenciaReporte());
                    ps.setString(9, M.getNombre_archivo_reporte());
                    ps.setString(10, M.getCve_mantenimiento());
                } else {
                    ps.setString(8, M.getCve_mantenimiento());
                }

                if ("1".equals(M.getTipo_bien())) {
                    //Si es un clima No contiene TonnersCambiados, getCambioET
                    ps.setString(7, " ");
                    ps.setString(6, " ");
                    //El clima si contiene las fallas
                    ps.setString(3, M.getFallas());
                } else {
                    //Si no es un climas no tiene las Fallas comunes
                    ps.setInt(3, 5);
                    if (!StringUtil.isBlank(M.getCambioET())) {
                        ps.setString(6, M.getCambioET());
                    } else {
                        ps.setString(6, " ");
                    }
                    if (!StringUtil.isBlank(M.getTonnersCambiados())) {
                        ps.setString(7, M.getTonnersCambiados());
                    } else {
                        ps.setString(7, " ");
                    }
                }

                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                            Se actualizo el Mantenimiento!
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                      <div class="alert alert-danger" role="alert">
                      Nose Actualizo el Recibo, ERROR: """ + e + "\n" + "</div>";
                System.err.println("ERROR en Actualizar Mantenimiento : " + e);
            } finally {
                try {
                    if (cn != null) {
                        cn.close();
                    }

                    if (con != null) {
                        con = null;
                    }

                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }

                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR cerrar Conexiones : " + ex);
                }
            }
        } else {
            mensaje = camposValidados;
        }

        return mensaje;
    }

    private String SQL(String archivo1, String archivo2) {
        String sql = "";

        if (!StringUtil.isBlank(archivo1) && !StringUtil.isBlank(archivo2)) {
            sql = """
                     UPDATE fecha_mante_camb SET cve_bienes = ?, cve_tipoMantenimiento = ?, cve_fallas = ?, 
                     fecha = ?, descripcion = ?, cambio_et = ?, tornner_cambiado=?,archivo = ?, nombre_archivo= ?,
                     archivo2 = ?, nombre_archivo2= ?
                     where cve_fecha_mante_camb=?""";
        } else if (!StringUtil.isBlank(archivo1)) {
            sql = """
                     UPDATE fecha_mante_camb SET cve_bienes = ?, cve_tipoMantenimiento = ?, cve_fallas = ?, 
                     fecha = ?, descripcion = ?, cambio_et = ?, tornner_cambiado=?,archivo = ?, nombre_archivo= ?
                     where cve_fecha_mante_camb=?""";
        } else if (!StringUtil.isBlank(archivo2)) {
            sql = """
                     UPDATE fecha_mante_camb SET cve_bienes = ?, cve_tipoMantenimiento = ?, cve_fallas = ?, 
                     fecha = ?, descripcion = ?, cambio_et = ?, tornner_cambiado=?, archivo2 = ?, nombre_archivo2= ?
                     where cve_fecha_mante_camb=?""";
        } else {
            sql = """
                     UPDATE fecha_mante_camb SET cve_bienes = ?, cve_tipoMantenimiento = ?, cve_fallas = ?, 
                     fecha = ?, descripcion = ?, cambio_et = ?, tornner_cambiado=?
                     where cve_fecha_mante_camb=?""";
        }
        return sql;
    }

    private String respuestaValida(Mantenimientos M) {
        String respuesta = null;
        String[] campos = {};
        String[] camposNombre = {};
        if (!StringUtil.isBlank(M.getTipo_bien())) {
            if ("1".equals(M.getTipo_bien())) {
                String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getTipoMantenimiento(), M.getFallas()};
                String[] camposNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Tipo de Mantenimiento NULO", "Falla NULA"};
                campos = campos1;
                camposNombre = camposNombre1;
            }
            if ("2".equals(M.getTipo_bien())) {
                String[] campos1 = {M.getCve_bienes(), M.getNum_inventario(), M.getFecha_manteni(), M.getCambioET(), M.getTipoMantenimiento()};
                String[] campoNombre1 = {"El Numero de Bien es NULO", "Campo Inventario NULO", "Fecha de Mantenimiento NULA", "Responda si, se Cambio el extintor", "Tipo de Mantenimiento NULO"};
                campos = campos1;
                camposNombre = campoNombre1;
            }

            if ("3".equals(M.getTipo_bien())) {

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

            }

            ValidaCampos Campos = new ValidaCampos();

            String validar = Campos.valida(campos, camposNombre);
            respuesta = validar;
        }

        return respuesta;
    }

    private boolean hayPart(Part evidencia) {
        Part part = evidencia;
        Path path = Paths.get(part.getSubmittedFileName());
        String name = path.getFileName().toString();

        return !StringUtil.isBlank(name);
    }

}
