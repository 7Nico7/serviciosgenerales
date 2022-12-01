package Model;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarVehiculo {

    public String RegistrarVehiculo(Vehiculo V) {
        String mensaje = "";
        final String sql = "INSERT vehiculos(inventario, serie, marca, tipo, modelo, vehiculo, placa_actual, placa_anterior, responsable, departamento, tipo_gasolina)"
                + " values (?,?,?,?,?,?,?,?,?,?,?);";
        Connection cn = null;
        PreparedStatement ps = null;
        Conexion con = null;

        //Se arreglo con los campos del formulario
        String[] campos = {V.getMarca(), V.getTipo(), V.getSerie(), V.getModelo(), V.getVehiculo(), V.getInventario(),
            V.getPlaca_actual(), V.getPlaca_anterio(), V.getResponsable(), V.getDepartemento(), V.getTipo_gasolina()};
        //arreglo con el mensaje si el campo es NULO
        String[] campoNombre = {"El Campo Marca es NULO", "El Campo Tipo es NULO", "El Campo Serie es NULO", "El Campo Modelo es NULO",
            "El Campo Vehiculo es NULO", "El Campo Inventario es NULO", "El Campo Placa Actual es NULO", "El Campo Placa Anterior es NULO",
            "El Campo Responsable es NULO", "El Campo departamento es NULO", "El Campo Tipo Gasolina es NULO"};
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
                ps.setString(1, V.getMarca());
                ps.setString(2, V.getTipo());
                ps.setString(3, V.getSerie());
                ps.setString(4, V.getModelo());
                ps.setString(5, V.getVehiculo());
                ps.setString(6, V.getInventario());
                ps.setString(7, V.getPlaca_actual());
                ps.setString(8, V.getPlaca_anterio());
                ps.setString(9, V.getResponsable());
                ps.setString(10, V.getDepartemento());
                ps.setString(11, V.getTipo_gasolina());

                ps.executeUpdate();
                mensaje = """
                          <div class="alert alert-success" role="alert">
                          Se registro el Vehiculo
                          </div>""";
            } catch (SQLException e) {
                mensaje = """
                          <div class="alert alert-danger" role="alert">
                          Nose Registro el Vehiculo, ERROR: """ + e + "\n" + "</div>";
                System.err.println("ERROR en RegistrarVehiculo : " + e);
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
                    System.err.println("ERROR En Registrar Vehiculo : " + ex);
                }
            }
        } else {
            mensaje = validaCampos;
        }

        return mensaje;
    }

}
