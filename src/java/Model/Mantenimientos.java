package Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;

public class Mantenimientos extends Bienes {

    private String cve_mantenimiento;
    private String tipoMantenimiento;
    private String fallas;
    private String fecha_manteni;
    private String mant_descripcion;
    private String nombre_archivo;
    private String evidenciaVideo;
    private String evidenciaFoto;
    private String cambioET;
    private String tonnersCambiados;
    private String dias_restantes;
    private String proximo_mantenimiento;
    private Part part;
    private String evidenciaEliminar;
    private String nombre_archivo_reporte;
    private String evidenciaReporte;
    private Part partReporte;
    private String evidenciaEliminarReporte;

    public Mantenimientos() {
    }

    public String getCve_mantenimiento() {
        return cve_mantenimiento;
    }

    public void setCve_mantenimiento(String cve_mantenimiento) {
        this.cve_mantenimiento = cve_mantenimiento;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public String getFallas() {
        return fallas;
    }

    public void setFallas(String fallas) {
        this.fallas = fallas;
    }

    public String getFecha_manteni() {
        return fecha_manteni;
    }

    public void setFecha_manteni(String fecha_manteni) {
        this.fecha_manteni = fecha_manteni;
    }

    public String getMant_descripcion() {
        return mant_descripcion;
    }

    public void setMant_descripcion(String mant_descripcion) {
        this.mant_descripcion = mant_descripcion;
    }

    public String getEvidenciaVideo() {
        return evidenciaVideo;
    }

    public void setEvidenciaVideo(String evidenciaVideo) {
        this.evidenciaVideo = evidenciaVideo;
    }

    public String getEvidenciaFoto() {
        return evidenciaFoto;
    }

    public void setEvidenciaFoto(String evidenciaFoto) {
        this.evidenciaFoto = evidenciaFoto;
    }

    public String getCambioET() {
        return cambioET;
    }

    public void setCambioET(String cambioET) {
        this.cambioET = cambioET;
    }

    public String getTonnersCambiados() {
        return tonnersCambiados;
    }

    public void setTonnersCambiados(String tonnersCambiados) {
        this.tonnersCambiados = tonnersCambiados;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getDias_restantes() {
        return dias_restantes;
    }

    public void setDias_restantes(String dias_restantes) {
        this.dias_restantes = dias_restantes;
    }

    public String getProximo_mantenimiento() {
        return proximo_mantenimiento;
    }

    public void setProximo_mantenimiento(String proximo_mantenimiento) {
        this.proximo_mantenimiento = proximo_mantenimiento;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    public String getEvidenciaEliminar() {
        return evidenciaEliminar;
    }

    public void setEvidenciaEliminar(String evidenciaEliminar) {
        this.evidenciaEliminar = evidenciaEliminar;
    }

    public String getNombre_archivo_reporte() {
        return nombre_archivo_reporte;
    }

    public void setNombre_archivo_reporte(String nombre_archivo_reporte) {
        this.nombre_archivo_reporte = nombre_archivo_reporte;
    }


    public String getEvidenciaReporte() {
        return evidenciaReporte;
    }

    public void setEvidenciaReporte(String evidenciaReporte) {
        this.evidenciaReporte = evidenciaReporte;
    }

    public Part getPartReporte() {
        return partReporte;
    }

    public void setPartReporte(Part partReporte) {
        this.partReporte = partReporte;
    }

    public String getEvidenciaEliminarReporte() {
        return evidenciaEliminarReporte;
    }

    public void setEvidenciaEliminarReporte(String evidenciaEliminarReporte) {
        this.evidenciaEliminarReporte = evidenciaEliminarReporte;
    }

    public Mantenimientos ObtenerDatos(HttpServletRequest request, boolean esActualizar) {
        Mantenimientos datos = new Mantenimientos();
        RegistrarMantenimiento valida = new RegistrarMantenimiento();
        String validarCampos;
        datos.setCve_bienes(request.getParameter("cve_bienes"));
        //System.err.println("CVE_BIENES :  " + datos.getCve_bienes());
        datos.setCve_mantenimiento(request.getParameter("num_mantenimiento"));
        datos.setTipo_bien(request.getParameter("tipoBien"));
        datos.setNum_inventario(request.getParameter("numInventario"));
        datos.setFecha_manteni(request.getParameter("fechaMantenimiento"));
        datos.setTipoMantenimiento(request.getParameter("tipoMantenimiento"));
        datos.setFallas(request.getParameter("fallas"));
        datos.setMant_descripcion(request.getParameter("mantenimientObservacion"));
        datos.setCambioET(request.getParameter("seCambio"));
        datos.setTonnersCambiados("tonnerCambiados");
        validarCampos = valida.respuestaValida(datos);
        SetRutaArchivo guardar = new SetRutaArchivo();
        //Guardar el archivo y obtener la ruta
        Archivo archivo = new Archivo();
        Archivo archivo_reporte = new Archivo();
        //Si el mantenimiento es actualizara
        if (esActualizar == true) {
            try {
                if ("correcto".equals(validarCampos)) {
                    datos.setPart(request.getPart("evidencia"));
                    datos.setEvidenciaEliminar(request.getParameter("evidenciaEliminar"));

                    datos.setPartReporte(request.getPart("evidenciaReporte"));
                    datos.setEvidenciaEliminarReporte(request.getParameter("evidenciaReporteEliminar"));
                }

            } catch (IOException | ServletException ex) {
                System.err.println("ERROR AL OBTENER datos.setPart() : " + ex);
            }
            archivo.setNombre(" ");
            archivo.setRuta(" ");
        } else {
            //Sino se esta actualizando entonces se esta Registrando
            try {
                //Se guarda el archivo si existe
                if ("correcto".equals(validarCampos)) {
                archivo = guardar.RutaArchivoGuardado(request.getPart("evidencia"));
                archivo_reporte = guardar.RutaArchivoGuardado(request.getPart("evidenciaReporte"));  
                }


            } catch (IOException | ServletException ex) {
                System.err.println("Error en Guardar Ruta :" + ex);
            }
        }
        datos.setEvidenciaVideo(archivo.getRuta());
        datos.setNombre_archivo(archivo.getNombre());

        datos.setEvidenciaReporte(archivo_reporte.getRuta());
        datos.setNombre_archivo_reporte(archivo_reporte.getNombre());

        return datos;

    }

}
