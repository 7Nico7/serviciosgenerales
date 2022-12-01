package Model;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.util.StringUtil;

public class Medidor {

    private int cve_medidor;
    private String planta;
    private String partida;
    private String local;
    private String medidor;
    private String servicio;

    public Medidor() {
    }

    public int getCve_medidor() {
        return cve_medidor;
    }

    public void setCve_medidor(int cve_medidor) {
        this.cve_medidor = cve_medidor;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Medidor GetMedidor(HttpServletRequest request) {
        Medidor medidor = new Medidor();
        isNumeric cadena = new isNumeric();
        
        boolean numero = cadena.num(request.getParameter("cve_medidor"));
        
        if (!StringUtil.isBlank(request.getParameter("cve_medidor"))) {
            int id = Integer.parseInt(request.getParameter("cve_medidor"));
            if(numero == true && id > 0){
                    medidor.setCve_medidor(id);
            } else {
                medidor.setCve_medidor(0);
            }
        } else {
            medidor.setCve_medidor(0);
        }
        medidor.setPlanta(request.getParameter("planta"));
        medidor.setPartida(request.getParameter("partida"));
        medidor.setLocal(request.getParameter("local"));
        medidor.setMedidor(request.getParameter("medidor"));
        medidor.setServicio(request.getParameter("servicio"));

        return medidor;
    }

}
