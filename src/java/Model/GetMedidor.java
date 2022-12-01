package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetMedidor {

    public String getMedidor() {
        String sql = "select cve_medidor, planta, partida, local_, medidor, servicio from medidor;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Medidor> medidor = new ArrayList<>();
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
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setPlanta(rs.getString("planta"));
                R.setPartida(rs.getString("partida"));
                R.setLocal(rs.getString("local_"));
                R.setMedidor(rs.getString("medidor"));
                R.setServicio(rs.getString("servicio"));

                medidor.add(R);
            }

            json = gson.toJson(medidor);

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

    public String getMedidorID(int cve_medidor) {
        String sql = "select cve_medidor, planta, partida, local_, medidor, servicio from medidor where cve_medidor = ?;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Medidor> medidor = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cve_medidor);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recibo R = new Recibo();
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setPlanta(rs.getString("planta"));
                R.setPartida(rs.getString("partida"));
                R.setLocal(rs.getString("local_"));
                R.setMedidor(rs.getString("medidor"));
                R.setServicio(rs.getString("servicio"));
                json = gson.toJson(R);
            }

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

    public String getNumMedidores() {
        String sql = "select cve_medidor, medidor from medidor;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Medidor> medidor = new ArrayList<>();
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
                R.setCve_medidor(rs.getInt("cve_medidor"));
                R.setMedidor(rs.getString("medidor"));

                medidor.add(R);
            }

            json = gson.toJson(medidor);

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

    public String getMedidorDetalles(int cve_medidor, int select) {
        String sql = "";
        if (select == 1) {
            sql = """
                     select me.cve_medidor, planta, partida, local_, medidor, servicio, re.cve_recibo, recibo_year, periodo, consumo,
                     saldo, archivo, nombre_archivo, periodo_inicio, periodo_final, archivo2, nombre_archivo2 from medidor as me
                     inner join recibo as re on me.cve_medidor=re.cve_medidor where me.cve_medidor= ?;""";

        }
        if (select == 2) {
            sql = "select * from medidor where cve_medidor=?;";
        }
        List<Recibo> medidor = new ArrayList<>();
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;
        int cve_me = 0;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);

                ps.setInt(1, cve_medidor);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                Recibo R = new Recibo();
                R.setCve_medidor(rs.getInt("cve_medidor"));

                R.setPlanta(rs.getString("planta"));
                R.setPartida(rs.getString("partida"));
                R.setLocal(rs.getString("local_"));
                R.setMedidor(rs.getString("medidor"));
                R.setServicio(rs.getString("servicio"));

                if (select == 1) {
                    R.setCve_recibo(rs.getString("cve_recibo"));
                    R.setYear(rs.getString("recibo_year"));
                    R.setPeriodo(rs.getString("periodo"));
                    R.setConsumo(rs.getString("consumo"));
                    R.setSaldo(rs.getString("saldo"));
                    R.setNombre_archivo(rs.getString("nombre_archivo"));
                    R.setArchivo(rs.getString("archivo"));
                    R.setPeriodoInicio(rs.getString("periodo_inicio"));
                    R.setPeriodoFinal(rs.getString("periodo_final"));
                    R.setArchivo_reporte(rs.getString("archivo2"));
                    R.setNombre_archivoReporte(rs.getString("nombre_archivo2"));
                    medidor.add(R);
                }
                if (select == 2) {
                    R.setCve_recibo("");
                    R.setYear("");
                    R.setPeriodo("");
                    R.setConsumo("");
                    R.setSaldo("");
                    R.setArchivo("");
                    R.setNombre_archivo("");
                    R.setPeriodoInicio("");
                    R.setPeriodoFinal("");
                    R.setArchivo_reporte("cccss");
                    R.setNombre_archivoReporte("");
                    medidor.add(R);
                }

                cve_me = R.getCve_medidor();

            }
            if (cve_me == 0) {
                json = null;

            } else {
                json = gson.toJson(medidor);
            }

        } catch (SQLException ex) {
            System.err.println("ERROR EN getMedidorDetalles : " + ex);
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
                System.err.println("ERROR en cerrar conexion getMedidorDetalles : " + ex);
            }
        }

        return json;
    }

}
