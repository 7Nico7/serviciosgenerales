package Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import org.apache.poi.util.StringUtil;

public class Servicios extends Vehiculo {

    private String fecha;
    private String descripcion;
    private String archivo;
    private String nombre_archivo;
    private Part part;
    private String eliminarEvidencia;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getEliminarEvidencia() {
        return eliminarEvidencia;
    }

    public void setEliminarEvidencia(String eliminarEvidencia) {
        this.eliminarEvidencia = eliminarEvidencia;
    }

    public Servicios getServicios(HttpServletRequest request, boolean actualizar) {
        Servicios S = new Servicios();
        SetRutaArchivo guardar = new SetRutaArchivo();
        //Guardar el archivo y obtener la ruta
        Archivo archivo = new Archivo();

        int id = 0;
        if (!StringUtil.isBlank(request.getParameter("vehiculo"))) {
            id = Integer.parseInt(request.getParameter("vehiculo"));
        }

        S.setCve_vehiculo(id);
        S.setFecha(request.getParameter("fecha"));
        S.setDescripcion(request.getParameter("descripcion"));

        if (!StringUtil.isBlank(S.getFecha()) && !StringUtil.isBlank(S.getDescripcion())) {

            //Valida si se esta actualizando
            if (actualizar == true) {
                try {
                    S.setPart(request.getPart("evidencia"));
                    S.setEliminarEvidencia(request.getParameter("evidenciaEliminar"));
                } catch (IOException | ServletException ex) {
                    System.err.println("ERROR AL OBTENER S.setPart() : " + ex);
                }
                archivo.setNombre(" ");
                archivo.setRuta(" ");
            }

            if (actualizar == false) {
                //Sino se esta actualizando entonces se esta Registrando
                try {
                    archivo = guardar.RutaArchivoGuardado(request.getPart("evidencia"));
                    //La ruta del archivo guardado
                } catch (IOException | ServletException ex) {
                    System.err.println("Error en Guardar Ruta :" + ex);
                }
            }

        }

        S.setArchivo(archivo.getRuta());
        S.setNombre_archivo(archivo.getNombre());

        return S;
    }
}
