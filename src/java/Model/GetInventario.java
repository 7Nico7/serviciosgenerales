package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetInventario {

    public String getProductos() {
        String sql = "select cve_productos, codigo, descripcion, proyeccion_anual, entrada, salida from productos;";
        String json = null;
        Gson gson = new Gson();
        Conexion con = null;
        List<Inventario> productos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection cn = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inventario I = new Inventario();
                I.setCve_producto(rs.getInt("cve_productos"));
                I.setCodigo(rs.getString("codigo"));
                I.setDescripcion(rs.getString("descripcion"));
                I.setProyeccion_anual(rs.getInt("proyeccion_anual"));
                I.setEntrada(rs.getInt("entrada"));
                I.setSalida(rs.getInt("salida"));

                productos.add(I);
            }

            json = gson.toJson(productos);

        } catch (SQLException ex) {
            System.err.println("ERROR EN getInventario : " + ex);
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
