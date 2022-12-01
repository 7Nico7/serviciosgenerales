package Model;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.util.StringUtil;

public class CargarGasolina extends Vehiculo {

    private String fecha;
    private String litros;
    private String precio;
    private String importe;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLitros() {
        return litros;
    }

    public void setLitros(String litros) {
        this.litros = litros;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    
    public CargarGasolina getCargarGasolina(HttpServletRequest request){
        CargarGasolina G = new CargarGasolina();
        int id =0;
        if (!StringUtil.isBlank(request.getParameter("vehiculo"))) {
               id     = Integer.parseInt(request.getParameter("vehiculo")) ;
        }
        
        G.setCve_vehiculo(id);
        G.setFecha(request.getParameter("fecha"));
        G.setLitros(request.getParameter("litros"));
        G.setPrecio(request.getParameter("precio"));
        G.setImporte(request.getParameter("importe"));
        
        
        return G;
    }
    
    
}
