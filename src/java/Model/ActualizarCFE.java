package Model;

import Conexion.Conexion;
import jakarta.servlet.http.Part;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;

public class ActualizarCFE {

    String mensaje = "";
    Connection cn = null;
    Conexion con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public String UpedateMedidor(Medidor M) {
        String sql = "UPDATE medidor SET planta =?, partida=?, local_=?, medidor=?, servicio=? where cve_medidor=?;";
        String mensaje = "";
        ValidaCampos valida = new ValidaCampos();
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Se arreglo con los campos del formulario
        String[] campos = {M.getPartida(), M.getPlanta(), M.getLocal(), M.getMedidor(), M.getServicio()};
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El Campo Partida es NULO", "El Campo Planta es NULO", "El Campo Local es NULO", "El Campo Medidor es NULO", "El Campo Servicio es NULO"};
        //Clase para validar los Campos
        ValidaCampos validar = new ValidaCampos();
        //Se valida los campos
        String validaCampos = validar.valida(campos, campoNombre);

        if ("correcto".equals(validaCampos)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setString(1, M.getPlanta());
                ps.setString(2, M.getPartida());
                ps.setString(3, M.getLocal());
                ps.setString(4, M.getMedidor());
                ps.setString(5, M.getServicio());
                ps.setInt(6, M.getCve_medidor());
                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se Actualizo el Medidor
                          </div>""";
            } catch (SQLException e) {
                System.err.println("Error en UpedateMedidor : " + e);
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Actualizo el Medidor, ERROR: """ + e + "\n" + "</div>";
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
            mensaje = validaCampos;
        }

        return mensaje;
    }

    public String UpedateRecibo(Recibo R) {
        String mensaje = "";
        String sql = "";
        Archivo nuevoArchivo = new Archivo();
        Archivo nuevoArchivoR = new Archivo();
        SetRutaArchivo guardar = new SetRutaArchivo();
        ValidaCampos valida = new ValidaCampos();
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EliminarArchivo archivo = new EliminarArchivo();
        //Se validan los Campos
        String[] campos = {R.getYear(), R.getPeriodo(), R.getConsumo(), R.getSaldo()};
        String[] camposNombre = {"El campo AÑO es NULO", "El campo Periodo es NULO", "El campo Consumo es NULO", "El campo Saldo es NULO"};
        String camposValidos = valida.valida(campos, camposNombre);

        //Se valida si los campos son Correctos
        if ("correcto".equals(camposValidos)) {

            boolean existeArchivo = hayPart(R.getPart());
            boolean archivoReporte = hayPart(R.getPartReporte());
            //Se valida si hay un nuevo recibo o reporte para actualizar

            if (existeArchivo == true && archivoReporte == true) {
                sql = """
                  UPDATE recibo SET cve_medidor = ?, recibo_year = ?, periodo = ?, consumo = ?, saldo= ?, archivo = ?, 
                      nombre_archivo = ?, periodo_inicio = ?, periodo_final = ?, archivo2 = ?, nombre_archivo2 = ?
                  where cve_recibo = ?;""";

                archivo.eliminarArchivo(R.getEliminarArchivo());
                nuevoArchivo = guardar.RutaArchivoGuardado(R.getPart());
                R.setNombre_archivo(nuevoArchivo.getNombre());
                R.setArchivo(nuevoArchivo.getRuta());

                archivo.eliminarArchivo(R.getEliminarArchivoReporte());
                nuevoArchivoR = guardar.RutaArchivoGuardado(R.getPartReporte());
                R.setNombre_archivoReporte(nuevoArchivoR.getNombre());
                R.setArchivo_reporte(nuevoArchivoR.getRuta());
            } else if (existeArchivo == true) {
                sql = """
                  UPDATE recibo SET cve_medidor = ?, recibo_year = ?, periodo = ?, consumo = ?, saldo= ?, archivo = ?, 
                      nombre_archivo = ?, periodo_inicio = ?, periodo_final = ?
                  where cve_recibo = ?;""";
                
                archivo.eliminarArchivo(R.getEliminarArchivo());
                nuevoArchivo = guardar.RutaArchivoGuardado(R.getPart());
                R.setNombre_archivo(nuevoArchivo.getNombre());
                R.setArchivo(nuevoArchivo.getRuta());
            } else if (archivoReporte == true) {
                sql = """
                  UPDATE recibo SET cve_medidor = ?, recibo_year = ?, periodo = ?, consumo = ?, saldo= ?, archivo2 = ?, 
                      nombre_archivo2 = ?, periodo_inicio = ?, periodo_final = ?
                  where cve_recibo = ?;""";
                archivo.eliminarArchivo(R.getEliminarArchivoReporte());
                nuevoArchivoR = guardar.RutaArchivoGuardado(R.getPartReporte());
                R.setNombre_archivoReporte(nuevoArchivoR.getNombre());
                R.setArchivo_reporte(nuevoArchivoR.getRuta());
            } else {
                sql = """
                  UPDATE recibo SET cve_medidor = ?, recibo_year = ?, periodo = ?, consumo = ?, saldo= ?, periodo_inicio = ?, periodo_final = ?
                  where cve_recibo = ?;""";
            }

            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setInt(1, R.getCve_medidor());
                ps.setString(2, R.getYear());
                ps.setString(3, R.getPeriodo());
                ps.setString(4, R.getConsumo());
                ps.setString(5, R.getSaldo());

                if (!StringUtil.isBlank(R.getNombre_archivoReporte()) && !StringUtil.isBlank(R.getNombre_archivo())) {
                    ps.setString(6, R.getArchivo());
                    ps.setString(7, R.getNombre_archivo());
                    ps.setString(8, R.getPeriodoInicio());
                    ps.setString(9, R.getPeriodoFinal());

                    ps.setString(10, R.getArchivo_reporte());
                    ps.setString(11, R.getNombre_archivoReporte());

                    ps.setString(12, R.getCve_recibo());
                } else if (!StringUtil.isBlank(R.getNombre_archivo())) {
                    ps.setString(6, R.getArchivo());
                    ps.setString(7, R.getNombre_archivo());
                    ps.setString(8, R.getPeriodoInicio());
                    ps.setString(9, R.getPeriodoFinal());
                    ps.setString(10, R.getCve_recibo());
                } else if (!StringUtil.isBlank(R.getNombre_archivoReporte())) {
                    ps.setString(6, R.getArchivo_reporte());
                    ps.setString(7, R.getNombre_archivoReporte());
                    ps.setString(8, R.getPeriodoInicio());
                    ps.setString(9, R.getPeriodoFinal());
                    ps.setString(10, R.getCve_recibo());
                } else {
                    ps.setString(6, R.getPeriodoInicio());
                    ps.setString(7, R.getPeriodoFinal());
                    ps.setString(8, R.getCve_recibo());
                }

                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                            Se actualizo el Recibo!
                          </div>""";
            } catch (SQLException e) {

                mensaje = """
                      <div class="alert alert-danger" role="alert">
                      Nose Actualizo el Recibo, ERROR: """ + e + "\n" + "</div>";

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
            mensaje = camposValidos;
        }

        return mensaje;
    }

    private boolean hayPart(Part evidencia) {
        Part part = evidencia;
        Path path = Paths.get(part.getSubmittedFileName());
        String name = path.getFileName().toString();

        return !StringUtil.isBlank(name);
    }

}
