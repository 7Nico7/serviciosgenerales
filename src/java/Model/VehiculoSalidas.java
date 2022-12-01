package Model;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.util.StringUtil;

public class VehiculoSalidas extends Vehiculo {

    private String cve_salidas;
    private String fecha;
    private String hora_salida;
    private String hora_entrada;
    private String tiempo_ruta;
    private String kilometraje_inicial;
    private String kilometraje_final;
    private String gasolina_inicial;
    private String gasolina_final;
    private String conductor;
    private String usuarios;
    private String destino;
    private String asunto;
    private String observaciones;
    private String status;

    public String getCve_salidas() {
        return cve_salidas;
    }

    public void setCve_salidas(String cve_salidas) {
        this.cve_salidas = cve_salidas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getTiempo_ruta() {
        return tiempo_ruta;
    }

    public void setTiempo_ruta(String tiempo_ruta) {
        this.tiempo_ruta = tiempo_ruta;
    }

    public String getKilometraje_inicial() {
        return kilometraje_inicial;
    }

    public void setKilometraje_inicial(String kilometraje_inicial) {
        this.kilometraje_inicial = kilometraje_inicial;
    }

    public String getKilometraje_final() {
        return kilometraje_final;
    }

    public void setKilometraje_final(String kilometraje_final) {
        this.kilometraje_final = kilometraje_final;
    }

    public String getGasolina_inicial() {
        return gasolina_inicial;
    }

    public void setGasolina_inicial(String gasolina_inicial) {
        this.gasolina_inicial = gasolina_inicial;
    }

    public String getGasolina_final() {
        return gasolina_final;
    }

    public void setGasolina_final(String gasolina_final) {
        this.gasolina_final = gasolina_final;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VehiculoSalidas getVehiculoSalidas(HttpServletRequest request) {
        VehiculoSalidas VS = new VehiculoSalidas();

        int id = 0;
        if (!StringUtil.isBlank(request.getParameter("vehiculo"))) {
            id = Integer.parseInt(request.getParameter("vehiculo"));
        }

        VS.setCve_vehiculo(id);
        VS.setCve_salidas(request.getParameter("cve_salidas"));

        VS.setFecha(request.getParameter("fecha"));
        VS.setHora_salida(request.getParameter("hora_salida"));
        VS.setHora_entrada(request.getParameter("hora_entrada"));

        VS.setKilometraje_inicial(request.getParameter("k_inicial"));
        VS.setKilometraje_final(request.getParameter("k_final"));
        VS.setGasolina_inicial(request.getParameter("g_inicial"));
        VS.setGasolina_final(request.getParameter("g_final"));
        VS.setConductor(request.getParameter("conductor"));
        VS.setUsuarios(request.getParameter("usuarios"));
        VS.setDestino(request.getParameter("destino"));
        VS.setAsunto(request.getParameter("asunto"));
        VS.setObservaciones(request.getParameter("observaciones"));
        String s = Status(request.getParameter("status"));
        VS.setStatus(s);

        return VS;

    }

    private String Status(String status) {

        if (!StringUtil.isBlank(status)) {
            status = switch (status) {
                case "1" ->
                    "Sin Concretar";
                case "2" ->
                    "Concretado";
                default ->
                    "Sin Concretar";
            };
        } else {
            status = "0";
        }

        return status;
    }


}
