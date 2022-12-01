package Model;

import jakarta.servlet.http.HttpServletRequest;

public class Salida extends Entrada {

    private String solicitante;

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public Salida getSalida(HttpServletRequest req) {
        Salida S = new Salida();
        
        S.setFolio(req.getParameter("folio"));
        S.setCodigo(req.getParameter("codigo"));
        S.setDescripcion(req.getParameter("descripcion"));
        S.setFecha(req.getParameter("fecha"));
        S.setCantidad(Integer.parseInt(req.getParameter("Cantidad")));
        S.setSolicitante("solicitante");

        return S;
    }

}
