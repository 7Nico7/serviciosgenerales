package Model;

import jakarta.servlet.http.HttpServletRequest;

public class Bienes {

    private String cve_bienes;
    private String tipo_bien;
    private String marca;
    private String modelo;
    private String num_inventario;
    private String fecha_factura;
    private String ubic_departamento;
    private String fecha_instalacion;
    private String dependencia;
    private String departamento;
    private String ubicaObservaciones;
    private String area;
    private String responsable;
    private String descripcion;
    private String status;
    private String tonner;
    private String serie;
    private String unidad;

    public Bienes() {
    }

    public String getCve_bienes() {
        return cve_bienes;
    }

    public void setCve_bienes(String cve_bienes) {
        this.cve_bienes = cve_bienes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNum_inventario() {
        return num_inventario;
    }

    public void setNum_inventario(String num_invetario) {
        this.num_inventario = num_invetario;
    }

    public String getTipo_bien() {
        return tipo_bien;
    }

    public void setTipo_bien(String tipo_bien) {
        this.tipo_bien = tipo_bien;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getUbic_departamento() {
        return ubic_departamento;
    }

    public void setUbic_departamento(String ubic_departamento) {
        this.ubic_departamento = ubic_departamento;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUbicaObservaciones() {
        return ubicaObservaciones;
    }

    public void setUbicaObservaciones(String UbicaObservaciones) {
        this.ubicaObservaciones = UbicaObservaciones;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTonner() {
        return tonner;
    }

    public void setTonner(String tonner) {
        this.tonner = tonner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }

    public Bienes Bien(HttpServletRequest request) {
        Bienes bien = new Bienes();
        bien.setCve_bienes(request.getParameter("cve_bienes"));
        bien.setUnidad(request.getParameter("objetoDescripcion"));

        bien.setMarca(request.getParameter("marca"));
        bien.setModelo(request.getParameter("modelo"));
        bien.setSerie(request.getParameter("serie"));
        bien.setNum_inventario(request.getParameter("inventario"));
        bien.setTipo_bien(request.getParameter("tipoBien"));
        bien.setFecha_factura(request.getParameter("fechaFactura"));
        bien.setFecha_instalacion(request.getParameter("fechaAltaSistema"));
        bien.setUbic_departamento(request.getParameter("ubicacionTxt"));

        bien.setResponsable(request.getParameter("responsable"));
        bien.setDependencia(request.getParameter("dependencia"));
        bien.setDepartamento(request.getParameter("departamento"));
        bien.setUbicaObservaciones(request.getParameter("ubicacionObservacion"));

        bien.setTonner(request.getParameter("tonner"));
        bien.setArea(request.getParameter("area"));
        bien.setStatus(request.getParameter("status"));

        //bien.setEvidenciaVideo(request.getParameter("evidencia"));
        return bien;

    }
}
