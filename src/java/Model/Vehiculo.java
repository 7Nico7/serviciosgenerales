package Model;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.util.StringUtil;

public class Vehiculo {

    private int cve_vehiculo;
    private String vehiculo;
    private String serie;
    private String inventario;
    private String marca;
    private String tipo;
    private String modelo;
    private String placa_actual;
    private String placa_anterio;
    private String responsable;
    private String departemento;
    private String tipo_gasolina;

    public int getCve_vehiculo() {
        return cve_vehiculo;
    }

    public void setCve_vehiculo(int cve_vehiculo) {
        this.cve_vehiculo = cve_vehiculo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca_actual() {
        return placa_actual;
    }

    public void setPlaca_actual(String placa_actual) {
        this.placa_actual = placa_actual;
    }

    public String getPlaca_anterio() {
        return placa_anterio;
    }

    public void setPlaca_anterio(String placa_anterio) {
        this.placa_anterio = placa_anterio;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDepartemento() {
        return departemento;
    }

    public void setDepartemento(String departemento) {
        this.departemento = departemento;
    }

    public String getTipo_gasolina() {
        return tipo_gasolina;
    }

    public void setTipo_gasolina(String tipo_gasolina) {
        this.tipo_gasolina = tipo_gasolina;
    }

    public Vehiculo getVehiculo(HttpServletRequest request) {
        Vehiculo V = new Vehiculo();

        V.setMarca(request.getParameter("marca"));
        V.setTipo(request.getParameter("tipo"));
        V.setSerie(request.getParameter("serie"));
        V.setModelo(request.getParameter("modelo"));
        V.setVehiculo(request.getParameter("vehiculo"));
        V.setInventario(request.getParameter("inventario"));
        V.setPlaca_actual("placa_actual");
        V.setPlaca_anterio(request.getParameter("placa_anterior"));
        V.setResponsable(request.getParameter("responsable"));
        V.setDepartemento(request.getParameter("departamento"));
        V.setTipo_gasolina(tipoGasolina(request.getParameter("tipo_gasolina")));

        return V;
    }

    public String tipoGasolina(String tipo) {

        if (!StringUtil.isBlank(tipo)) {

            switch (tipo) {
                case "1":
                    tipo = "Premium";
                    break;
                case "2":
                    tipo = "Magna";
                    break;
                case "3":
                    tipo = "Diesel";
                    break;
                default:
                    tipo = "0";
            }

        }

        return tipo;
    }

}
