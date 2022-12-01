package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.util.StringUtil;
import org.joda.time.LocalDate;

public class RegistrarRecibo {

    public String registrarRecibo(Recibo R) {
        String mensaje = "";
        String sql = "INSERT recibo (cve_medidor, recibo_year, periodo, consumo, saldo, archivo, nombre_archivo, "
                + "periodo_inicio, periodo_final, archivo2, nombre_archivo2) values (?,?,?,?,?,?,?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;

        ValidaCampos valida = new ValidaCampos();

        String[] campos = {R.getYear(), R.getPeriodo(), R.getConsumo(), R.getSaldo(), R.getPeriodoInicio(), R.getPeriodoFinal()};
        String[] camposNombre = {"El campo año es NULO", "El campo Periodo es NULO", "El campo Consumo es NULO", "El campo Saldo es NULO", "El campo Periodo Inicial es NULO", "El campo Periodo Final es NULO"};

        String camposValidos = valida.valida(campos, camposNombre);

        if ("correcto".equals(camposValidos)) {
            try {
                con = new Conexion();
                cn = con.getConexion();
                ps = cn.prepareStatement(sql);
                ps.setInt(1, R.getCve_medidor());
                ps.setString(2, R.getYear());
                ps.setString(3, R.getPeriodo());
                ps.setString(4, R.getConsumo());
                ps.setString(5, R.getSaldo());
                if (!StringUtil.isBlank(R.getArchivo())) {
                    ps.setString(6, R.getArchivo());
                    ps.setString(7, R.getNombre_archivo());
                } else {
                    ps.setString(6, " ");
                    ps.setString(7, " ");
                }

                ps.setString(8, R.getPeriodoInicio());
                ps.setString(9, R.getPeriodoFinal());

                if (!StringUtil.isBlank(R.getArchivo_reporte())) {
                    ps.setString(10, R.getArchivo_reporte());
                    ps.setString(11, R.getNombre_archivoReporte());
                } else {
                    ps.setString(10, " ");
                    ps.setString(11, " ");
                }

                ps.executeUpdate();
                mensaje = """
                        <div class="alert alert-success" role="alert">
                        Se registro el Recibo
                        </div>""";
            } catch (SQLException e) {
                mensaje = """
                                 <div class="alert alert-danger" role="alert">
                                 No se regitro el Recibo Error: """ + e + "</div>";
                System.err.println("ERROR EN RegistrarRecibo : " + e);
            } finally {
                try {
                    if (cn != null && cn.isClosed() == false) {
                        cn.close();
                        cn = null;
                    }

                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }

                    if (con != null) {
                        con = null;
                    }
                } catch (SQLException ex) {
                    System.err.println("ERROR En RegistrarRecibo cerrar conexion : " + ex);
                }
            }
        } else {
            mensaje = camposValidos;
        }

        return mensaje;
    }

}
