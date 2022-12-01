
package Model;

import jakarta.servlet.http.HttpServletRequest;

public class Entrada extends Inventario{
    
    private String folio;
    private String fecha;
    private int cantidad;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public Entrada getEntrada(HttpServletRequest req){
    Entrada E = new Entrada();
    
    E.setFolio(req.getParameter("folio"));
    E.setCodigo(req.getParameter("codigo"));
    E.setDescripcion(req.getParameter("descripcion"));
    E.setFecha(req.getParameter("fecha"));
    E.setCantidad(Integer.parseInt(req.getParameter("cantidad")));
    
    return E;
        
    }
    
}
