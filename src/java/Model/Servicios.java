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
    private String archivo2;
    private String nombre_archivo2;
    private Part part2;
    private String eliminarEvidencia2;

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

    public String getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(String archivo2) {
        this.archivo2 = archivo2;
    }

    public String getNombre_archivo2() {
        return nombre_archivo2;
    }

    public void setNombre_archivo2(String nombre_archivo2) {
        this.nombre_archivo2 = nombre_archivo2;
    }

    public Part getPart2() {
        return part2;
    }

    public void setPart2(Part part2) {
        this.part2 = part2;
    }

    public String getEliminarEvidencia2() {
        return eliminarEvidencia2;
    }

    public void setEliminarEvidencia2(String eliminarEvidencia2) {
        this.eliminarEvidencia2 = eliminarEvidencia2;
    }
    
    

    public Servicios getServicios(HttpServletRequest request, boolean actualizar) {
        Servicios S = new Servicios();
        SetRutaArchivo guardar = new SetRutaArchivo();
        //Guardar el archivo y obtener la ruta
        Archivo archivo = new Archivo();
        Archivo archivo2 = new Archivo();
        

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
                    
                    S.setPart2(request.getPart("evidencia2"));
                    S.setEliminarEvidencia2("evidenciaEliminar2");
                    
                } catch (IOException | ServletException ex) {
                    System.err.println("ERROR AL OBTENER S.setPart() : " + ex);
                }
                archivo.setNombre(" ");
                archivo.setRuta(" ");
                
                archivo2.setNombre(" ");
                archivo2.setRuta(" ");
            }

            if (actualizar == false) {
                //Sino se esta actualizando entonces se esta Registrando
                try {
                    archivo = guardar.RutaArchivoGuardado(request.getPart("evidencia"));
                    
                    archivo2 = guardar.RutaArchivoGuardado(request.getPart("evidencia2"));
                } catch (IOException | ServletException ex) {
                    System.err.println("Error en Guardar Ruta :" + ex);
                }
            }

        }

        S.setArchivo(archivo.getRuta());
        S.setNombre_archivo(archivo.getNombre());
        
        S.setArchivo2(archivo2.getRuta());
        S.setNombre_archivo2(archivo2.getNombre());

        return S;
    }
}
