package Model;

import Conexion.Conexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Get_Id_Inventarios_Bienes {

    public String ObtenerIdInventarios_Bienes() {
        final String sql = """
                           select cve_bienes, num_inventario, tipo from bienes as b
                           inner join tipobien as tb on b.cve_tipoBien=tb.cve_tipoBien;""";
        Connection cn = null;
        Conexion con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String json = null;
        Gson gson = new Gson();
        List<Bienes> bienes = new ArrayList<>();

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Bienes b = new Bienes();
                b.setCve_bienes(rs.getString("cve_bienes"));
                b.setNum_inventario(rs.getString("num_inventario"));
                b.setTipo_bien(rs.getString("tipo"));
                bienes.add(b);
            }
            json = gson.toJson(bienes);

        } catch (SQLException e) {
            System.err.println("ERROR en : " + e);
        } finally {
            try {
                if (cn != null && cn.isClosed() == false) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null && rs.isClosed() == false) {
                    rs.close();
                }
                if (con != null) {
                    con = null;
                }
            } catch (SQLException ex) {
                System.err.println("ERROR en cerrar cn");
            }
        }
        return json;
    }
}
