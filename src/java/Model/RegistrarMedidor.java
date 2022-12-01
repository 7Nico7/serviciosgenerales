package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarMedidor {

    public String RegistrarMedidor(Medidor M) {
        String mensaje = "";
        String sql = "INSERT medidor(planta, partida, local_, medidor, servicio) values (?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;
        
        //Se arreglo con los campos del formulario
        String[] campos = {M.getPartida(), M.getPlanta(), M.getLocal(), M.getMedidor(), M.getServicio()};
       //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El Campo Partida es NULO", "El Campo Planta es NULO", "El Campo Local es NULO", "El Campo Medidor es NULO", "El Campo Servicio es NULO"};
        //Clase para validar los Campos
        ValidaCampos validar = new ValidaCampos();
        //Se valida los campos
        String validaCampos = validar.valida(campos, campoNombre);
        
        //Se validad si los campos son correctos
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
                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se registro el Medidor
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Registro el Medidor, ERROR: """ + e + "\n" + "</div>";
                System.err.println("ERROR en RegistrarMedidor : " + e);
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
                    System.err.println("ERROR En RegistrarMedidor : " + ex);
                }
            }
        } else {
            mensaje = validaCampos;
        }

        return mensaje;
    }

}
