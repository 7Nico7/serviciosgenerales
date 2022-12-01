package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetRecibo {

    public String getRecibo() {
        String sql = "select cve_recibo, cve_medidor, recibo_year, periodo, consumo, saldo, archivo, nombre_archivo, periodo_inicio, periodo_final, archivo2, nombre_archivo2 from recibo;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Recibo> recibos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recibo R = new Recibo();
                R.setCve_recibo(rs.getString("cve_recibo"));
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setYear(rs.getString("recibo_year"));
                R.setPeriodo(rs.getString("periodo"));
                R.setPeriodoInicio(rs.getString("periodo_inicio"));
                R.setPeriodoFinal(rs.getString("periodo_final"));
                R.setConsumo(rs.getString("consumo"));
                R.setSaldo(rs.getString("saldo"));
                R.setArchivo(rs.getString("archivo"));
                R.setNombre_archivo(rs.getString("nombre_archivo"));
                
                R.setArchivo_reporte(rs.getString("archivo2"));
                R.setNombre_archivoReporte(rs.getString("nombre_archivo2"));

                recibos.add(R);
            }

            json = gson.toJson(recibos);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getRecibo : " + ex);
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

    public String getReciboOrdenMasReciente() {
        String sql = "select cve_recibo, cve_medidor, recibo_year, periodo, consumo, saldo, archivo, nombre_archivo, periodo_inicio, periodo_final, archivo2, nombre_archivo2 from recibo order by periodo_inicio DESC;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Recibo> recibos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recibo R = new Recibo();
                R.setCve_recibo(rs.getString("cve_recibo"));
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setYear(rs.getString("recibo_year"));
                R.setPeriodo(rs.getString("periodo"));
                R.setPeriodoInicio(rs.getString("periodo_inicio"));
                R.setPeriodoFinal(rs.getString("periodo_final"));
                R.setConsumo(rs.getString("consumo"));
                R.setSaldo(rs.getString("saldo"));
                R.setArchivo(rs.getString("archivo"));
                R.setNombre_archivo(rs.getString("nombre_archivo"));
                
                R.setArchivo_reporte(rs.getString("archivo2"));
                R.setNombre_archivoReporte(rs.getString("nombre_archivo2"));

                recibos.add(R);
            }

            json = gson.toJson(recibos);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getRecibo : " + ex);
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

    public String getReciboId(int cve_recibo) {
        String sql = """
                     select re.cve_recibo, re.cve_medidor, recibo_year, periodo, consumo, saldo, 
                     archivo, nombre_archivo, periodo_inicio, periodo_final, medidor, archivo2, nombre_archivo2 from recibo as re
                     inner join medidor as me on re.cve_medidor=me.cve_medidor
                     where cve_recibo = ?;""";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Recibo R = new Recibo();
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_recibo);
            rs = ps.executeQuery();
            while (rs.next()) {
                R.setCve_recibo(rs.getString("cve_recibo"));
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setYear(rs.getString("recibo_year"));
                R.setPeriodo(rs.getString("periodo"));
                R.setPeriodoInicio(rs.getString("periodo_inicio"));
                R.setPeriodoFinal(rs.getString("periodo_final"));
                R.setConsumo(rs.getString("consumo"));
                R.setSaldo(rs.getString("saldo"));
                R.setArchivo(rs.getString("archivo"));
                R.setNombre_archivo(rs.getString("nombre_archivo"));
                R.setMedidor(rs.getString("medidor"));
                R.setArchivo_reporte(rs.getString("archivo2"));
                R.setNombre_archivoReporte(rs.getString("nombre_archivo2"));
            }

            json = gson.toJson(R);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getReciboID : " + ex);
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

    public String getReciboId_MasReciente(int cve_medidor) {
        String sql = """
                     select cve_medidor, periodo_inicio, archivo, nombre_archivo, archivo2, nombre_archivo2  from recibo where cve_medidor = ? order by periodo_inicio DESC limit 1;""";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Recibo R = new Recibo();
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_medidor);
            rs = ps.executeQuery();
            while (rs.next()) {
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setPeriodoInicio(rs.getString("periodo_inicio"));
                R.setNombre_archivo(rs.getString("nombre_archivo"));
                R.setNombre_archivoReporte(rs.getString("nombre_archivo2"));
            }

            json = gson.toJson(R);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getReciboID : " + ex);
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

    public String getReciboId_medidor(int cve_medidor) {
        String sql = "select cve_recibo, cve_medidor, recibo_year, periodo, consumo, saldo, archivo, nombre_archivo, archivo2, nombre_archivo2 from recibo where cve_medidor = ?;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        Recibo R = new Recibo();
        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_medidor);
            rs = ps.executeQuery();
            while (rs.next()) {

                R.setCve_recibo(rs.getString("cve_recibo"));
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setYear(rs.getString("recibo_year"));
                R.setPeriodo(rs.getString("periodo"));
                R.setConsumo(rs.getString("consumo"));
                R.setSaldo(rs.getString("saldo"));
                R.setArchivo(rs.getString("archivo"));
                R.setNombre_archivo(rs.getString("nombre_archivo"));
                R.setArchivo_reporte(rs.getString("archivo2"));
                R.setNombre_archivoReporte("nombre_archivo2");
            }
            json = gson.toJson(R);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getReciboID : " + ex);
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
